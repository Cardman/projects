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
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ReachCaseCondition extends ReachSwitchPartBlock {
    private final OperationNode root;
    private final int valueOffset;
    private final String value;
    private final String importedType;
    private final CaseCondition meta;
    private final boolean instance;

    protected ReachCaseCondition(CaseCondition _info) {
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
        _page.setGlobalOffset(valueOffset);
        _page.zeroOffset();
        ReachBracedBlock par_ = getParent();
        if (!(par_ instanceof ReachSwitchBlock)&&!(par_ instanceof ReachSwitchMethodBlock)) {
            return;
        }
        AnaClassArgumentMatching resSwitch_;
        boolean instance_;
        String type_;
        if (par_ instanceof ReachSwitchBlock) {
            ReachSwitchBlock sw_ = (ReachSwitchBlock) par_;
            resSwitch_ = sw_.getResult();
            instance_ = sw_.isInstance();
        } else {
            ReachSwitchMethodBlock sw_ = (ReachSwitchMethodBlock) par_;
            resSwitch_ = sw_.getResult();
            instance_ = sw_.isInstance();
        }
        type_ = resSwitch_.getSingleNameOrEmpty();
        EnumBlock e_ = getEnumType(type_, _page);
        if (e_ != null) {
            CustList<IndexStrPart> values_ = meta.getOffsetsEnum().getValues();
            for (IndexStrPart v: values_) {
                boolean added_ = false;
                if (StringUtil.quickEq(v.getPart(),_page.getKeyWords().getKeyWordNull())) {
                    checkDuplicateListedValue(_page,Argument.createVoid());
                    meta.getStdValues().add(Argument.createVoid());
                    added_ = true;
                } else {
                    for (InnerTypeOrElement f: e_.getEnumBlocks()) {
                        if (StringUtil.contains(f.getFieldName(), v.getPart())) {
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
                    un_.setFileName(getFile().getFileName());
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
//            for (InnerTypeOrElement f: e_.getEnumBlocks()) {
//                if (!StringUtil.contains(f.getFieldName(), value.trim())) {
//                    continue;
//                }
//                meta.setBuiltEnum(true);
//                checkDuplicateEnumCase(_page);
//                return;
//            }
//            meta.setArgument(ReachOperationUtil.tryCalculate(root, _page));
//            if (Argument.isNullValue(meta.getArgument())) {
//                meta.setBuiltEnum(true);
//                meta.setNullCaseEnum(true);
//            }
//            processNullValue(_page);
            return;
        }
        if (instance) {
            if (meta.getImportedType().isEmpty()) {
                meta.getStdValues().add(Argument.createVoid());
            }
            return;
        }
        ReachOperationUtil.tryCalculate(root, _page);
        processNumValues(instance_,resSwitch_, _page);
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
                un_.setFileName(getFile().getFileName());
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
                        un_.setFileName(getFile().getFileName());
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
                un_.setFileName(getFile().getFileName());
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
                        un_.setFileName(getFile().getFileName());
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
                un_.setFileName(getFile().getFileName());
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
            un_.setFileName(getFile().getFileName());
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
        if (!s_.isInstance()) {
            super.reach(_anEl, _page);
            return;
        }
        if (meta.isNullCase()) {
            boolean nullCase_ = nullCase();
            if (!nullCase_) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
            return;
        }
        if (instance) {
            processInstance(_anEl, _page);
            return;
        }
        processValues(_anEl, _page);
    }

    private void processValues(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        StringList classes_ = previous();
        boolean reachCatch_ = true;
        CustList<OperationNode> childrenNodes_;
        if (root instanceof DeclaringOperation) {
            childrenNodes_ = ((DeclaringOperation) root).getChildrenNodes();
        } else {
            childrenNodes_ = new CustList<OperationNode>(root);
        }
        for (OperationNode o: childrenNodes_) {
            _anEl.setArgMapping(o.getResultClass().getSingleNameOrEmpty());
            for (String c: classes_) {
                _anEl.setParamMapping(c);
                if (_anEl.isCorrectMapping(_page)) {
                    reachCatch_ = false;
                    break;
                }
            }
            if (!reachCatch_) {
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    private void processInstance(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        StringList classes_ = previous();
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

    private boolean nullCase() {
        ReachBlock b_ = getPreviousSibling();
        boolean nullCase_ = false;
        while (b_ != null) {
            if (b_ instanceof ReachCaseCondition && ((ReachCaseCondition) b_).meta.isNullCase()) {
                nullCase_ = true;
            }
            b_ = b_.getPreviousSibling();
        }
        return nullCase_;
    }

    private StringList previous() {
        StringList classes_ = new StringList();
        ReachBlock b_ = getPreviousSibling();
        while (b_ != null) {
            if (b_ instanceof ReachCaseCondition&&!((ReachCaseCondition)b_).importedType.isEmpty()) {
                classes_.add(((ReachCaseCondition)b_).importedType);
            }
            b_ = b_.getPreviousSibling();
        }
        return classes_;
    }

    public int getValueOffset() {
        return valueOffset;
    }
}
