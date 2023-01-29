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
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ReachCaseCondition extends ReachSwitchPartBlock implements ReachBuildableElMethod,ReachFilterContent{
    private final FilterContent filterContent;

    public ReachCaseCondition(CaseCondition _info) {
        super(_info);
        filterContent = _info.getFilterContent();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(filterContent.getValueOffset());
        _page.zeroOffset();
        ReachBracedBlock par_ = getParent();
        if (!(par_ instanceof ReachSwitchBlock)&&!(par_ instanceof ReachSwitchMethodBlock)) {
            return;
        }
        EnumBlock e_ = filterContent.getEnumBlock();
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
        buildExpressionLanguageReadOnly(_page, resSwitch_, instance_,this,this);
    }

    static void buildExpressionLanguageReadOnly(AnalyzedPageEl _page, AnaClassArgumentMatching _resSwitch, boolean _instance, ReachBlock _bl, ReachFilterContent _current) {
        FilterContent filter_ = _current.getFilterContent();
        if (filter_.isInstance()) {
            if (filter_.getRoot() != null) {
                ReachOperationUtil.tryCalculate(filter_.getRoot(), _page);
            }
            return;
        }
        ReachOperationUtil.tryCalculate(filter_.getRoot(), _page);
        processNumValues(_instance, _resSwitch, _page,_bl,_current);
    }

    public FilterContent getFilterContent() {
        return filterContent;
    }

    private void elementEnum(AnalyzedPageEl _page, EnumBlock _e) {
        CustList<IndexStrPart> values_ = filterContent.getOffsetsEnum().getValues();
        for (IndexStrPart v: values_) {
            boolean added_ = false;
            if (StringUtil.quickEq(v.getPart(), _page.getKeyWords().getKeyWordNull())) {
                checkDuplicateListedValue(_page,Argument.createVoid(),this,this);
                filterContent.getStdValues().add(Argument.createVoid());
                added_ = true;
            } else {
                for (InnerTypeOrElement f: _e.getEnumBlocks()) {
                    if (StringUtil.contains(f.getElements().getFieldName(), v.getPart())) {
                        ClassField pair_ = new ClassField(f.getImportedClassName(), v.getPart());
                        checkDuplicateListedEnum(_page, pair_, StringUtil.concat(pair_.getClassName(), ".", pair_.getFieldName()),this,this);
                        filterContent.getEnumValues().add(pair_);
                        added_ = true;
                        break;
                    }
                }
            }
            if (!added_) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(getFile());
                un_.setIndexFile(filterContent.getValueOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        filterContent.getKeyWord(),
                        filterContent.getValue());
                _page.addLocError(un_);
                addErrorBlock(un_.getBuiltError());
                break;
            }
        }
    }

    static void processNumValues(boolean _instance, AnaClassArgumentMatching _resSwitch, AnalyzedPageEl _page, ReachBlock _bl, ReachFilterContent _current) {
        FilterContent filter_ = _current.getFilterContent();
        if (filter_.getRoot() instanceof DeclaringOperation) {
            CustList<OperationNode> childrenNodes_ = ((DeclaringOperation) filter_.getRoot()).getChildrenNodes();
            StrTypes children_ = ((DeclaringOperation) filter_.getRoot()).getChildren();
            int len_ = childrenNodes_.size();
            for (int i = 0; i < len_; i++) {
                OperationNode ch_ = childrenNodes_.get(i);
                String value_ = StrTypes.value(children_, i);
                checkRetrieve(_instance,_resSwitch, _page, ch_, value_, _bl, _current);
            }
        } else {
            checkRetrieve(_instance,_resSwitch, _page, filter_.getRoot(), filter_.getValue(), _bl, _current);
        }
    }

    static void checkRetrieve(boolean _instance, AnaClassArgumentMatching _resSwitch, AnalyzedPageEl _page, OperationNode _ch, String _value, ReachBlock _bl, ReachFilterContent _current) {
        ClassField classField_ = ElUtil.tryGetAccess(_ch);
        AnaClassArgumentMatching rCase_ = _ch.getResultClass();
        if (classField_ != null) {
            checkDuplicateListedEnum(_page, classField_, StringUtil.concat(classField_.getClassName(), ".", classField_.getFieldName()),_bl,_current);
            checkInh(_instance, _resSwitch, rCase_, _page, StringUtil.concat(classField_.getClassName(), ".", classField_.getFieldName()), _current.getFilterContent(), _bl);
            _current.getFilterContent().getEnumValues().add(classField_);
        } else {
            Argument argument_ = _ch.getArgument();
            if (argument_ != null) {
                checkDuplicateListedValue(_page,argument_,_bl,_current);
                checkInh(_instance, _resSwitch, rCase_, _page, AnaApplyCoreMethodUtil.getString(ArgumentListCall.toStr(argument_), _page), _current.getFilterContent(), _bl);
                _current.getFilterContent().getStdValues().add(argument_);
            } else {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_bl.getFile());
                un_.setIndexFile(_current.getFilterContent().getValueOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        _current.getFilterContent().getKeyWord(),
                        _value);
                _page.addLocError(un_);
                _bl.addErrorBlock(un_.getBuiltError());
            }
        }
    }

    static void checkDuplicateListedEnum(AnalyzedPageEl _page, ClassField _classField, String _display, ReachBlock _bl, ReachFilterContent _current) {
        ReachBracedBlock par_ = _bl.getParent();
        ReachBlock first_ = par_.getFirstChild();
        while (first_ != _bl) {
            if (first_ instanceof ReachFilterContent) {
                ReachFilterContent c_ = (ReachFilterContent) first_;
                for (ClassField p: c_.getFilterContent().getEnumValues()) {
                    if (_classField.eq(p)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_bl.getFile());
                        un_.setIndexFile(_current.getFilterContent().getValueOffset()+ _bl.getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _current.getFilterContent().getKeyWord(),
                                _display,
                                _current.getFilterContent().getKeyWordContainer());
                        _page.addLocError(un_);
                        _bl.addErrorBlock(un_.getBuiltError());
                        return;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
        for (ClassField p: _current.getFilterContent().getEnumValues()) {
            if (_classField.eq(p)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_bl.getFile());
                un_.setIndexFile(_current.getFilterContent().getValueOffset()+ _bl.getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                        _current.getFilterContent().getKeyWord(),
                        _display,
                        _current.getFilterContent().getKeyWordContainer());
                _page.addLocError(un_);
                _bl.addErrorBlock(un_.getBuiltError());
                return;
            }
        }
    }
    static void checkDuplicateListedValue(AnalyzedPageEl _page, Argument _value, ReachBlock _bl, ReachFilterContent _current) {
        ReachBracedBlock par_ = _bl.getParent();
        ReachBlock first_ = par_.getFirstChild();
        while (first_ != _bl) {
            if (first_ instanceof ReachFilterContent) {
                ReachFilterContent c_ = (ReachFilterContent) first_;
                for (Argument p: c_.getFilterContent().getStdValues()) {
                    if (_value.getStruct().sameReference(p.getStruct())) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_bl.getFile());
                        un_.setIndexFile(_current.getFilterContent().getValueOffset()+ _bl.getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _current.getFilterContent().getKeyWord(),
                                AnaApplyCoreMethodUtil.getString(ArgumentListCall.toStr(_value), _page),
                                _current.getFilterContent().getKeyWordContainer());
                        _page.addLocError(un_);
                        _bl.addErrorBlock(un_.getBuiltError());
                        return;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
        for (Argument p: _current.getFilterContent().getStdValues()) {
            if (_value.getStruct().sameReference(p.getStruct())) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_bl.getFile());
                un_.setIndexFile(_current.getFilterContent().getValueOffset()+ _bl.getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                        _current.getFilterContent().getKeyWord(),
                        AnaApplyCoreMethodUtil.getString(ArgumentListCall.toStr(_value), _page),
                        _current.getFilterContent().getKeyWordContainer());
                _page.addLocError(un_);
                _bl.addErrorBlock(un_.getBuiltError());
                return;
            }
        }
    }

    static void checkInh(boolean _instance, AnaClassArgumentMatching _resSwitch, AnaClassArgumentMatching _resCase, AnalyzedPageEl _page, String _string, FilterContent _filter, ReachBlock _bl) {
        Mapping m_ = new Mapping();
        m_.setArg(_resCase);
        m_.setParam(_resSwitch);
        if (!_instance && !AnaInherits.isCorrectOrNumbers(m_, _page)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_bl.getFile());
            un_.setIndexFile(_filter.getValueOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseValue(),
                    _filter.getKeyWord(),
                    _string,
                    StringUtil.join(_resSwitch.getNames(), ExportCst.JOIN_TYPES));
            _page.addLocError(un_);
            _bl.addErrorBlock(un_.getBuiltError());
        }
    }

    public void reachCase(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBracedBlock par_ = getParent();
        if (!(par_ instanceof ReachSwitchBlock)) {
            reachBaseBraced(_anEl);
            return;
        }
        ReachSwitchBlock s_ = (ReachSwitchBlock) par_;
        if (!s_.isInstance()) {
            reachBaseBraced(_anEl);
            return;
        }
        processFilter(_anEl, _page,this,this);
    }

    static void processFilter(AnalyzingEl _anEl, AnalyzedPageEl _page, ReachBlock _bl, ReachFilterContent _current) {
        if (_current.getFilterContent().isInstance()) {
            processInstance(_anEl, _page, _bl, _current);
            return;
        }
        processValues(_anEl, _page,  _bl, _current);
    }

    private static void processValues(AnalyzingEl _anEl, AnalyzedPageEl _page, ReachBlock _bl, ReachFilterContent _current) {
        CustList<ReachFilterContent> classes_ = previous(_bl);
        boolean reachCatch_ = true;
        CustList<OperationNode> childrenNodes_ = childrenNodes(_current);
        for (OperationNode o: childrenNodes_) {
            if (!reachCatch(classes_,o,_anEl,_page)) {
                reachCatch_ = false;
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(_bl);
        } else {
            _anEl.unreach(_bl);
        }
    }
    private static boolean reachCatch(CustList<ReachFilterContent> _classes, OperationNode _o, AnalyzingEl _anEl, AnalyzedPageEl _page) {
        String argType_ = _o.getResultClass().getSingleNameOrEmpty();
        if (argType_.isEmpty()) {
            return true;
        }
        boolean reachCatch_ = true;
        _anEl.setArgMapping(argType_);
        for (ReachFilterContent c: _classes) {
            if (c.getFilterContent().getRoot() == null) {
                _anEl.setParamMapping(c.getFilterContent().getImportedType());
                if (_anEl.isCorrectMapping(_page)) {
                    reachCatch_ = false;
                    break;
                }
            }
        }
        return reachCatch_;
    }

    private static CustList<OperationNode> childrenNodes(ReachFilterContent _r) {
        CustList<OperationNode> childrenNodes_;
        if (_r.getFilterContent().getRoot() instanceof DeclaringOperation) {
            childrenNodes_ = ((DeclaringOperation) _r.getFilterContent().getRoot()).getChildrenNodes();
        } else if (_r.getFilterContent().getRoot() != null){
            childrenNodes_ = new CustList<OperationNode>(_r.getFilterContent().getRoot());
        } else {
            childrenNodes_ = new CustList<OperationNode>();
        }
        return childrenNodes_;
    }

    private static void processInstance(AnalyzingEl _anEl, AnalyzedPageEl _page, ReachBlock _bl, ReachFilterContent _current) {
        CustList<ReachFilterContent> reachCaseConditions_ = previous(_bl);
        _anEl.setArgMapping(_current.getFilterContent().getImportedType());
        boolean reachCatch_ = true;
        for (ReachFilterContent c : reachCaseConditions_) {
            if (ko(_anEl, _page,_current, c)) {
                reachCatch_ = false;
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(_bl);
        } else {
            _anEl.unreach(_bl);
        }
    }

    private static boolean ko(AnalyzingEl _anEl, AnalyzedPageEl _page, ReachFilterContent _current, ReachFilterContent _other) {
        if (_current.isAll()) {
            return false;
        }
        if (!StringUtil.quickEq(_other.getFilterContent().getImportedType(), _current.getFilterContent().getImportedType())) {
            _anEl.setParamMapping(_other.getFilterContent().getImportedType());
            return _anEl.isCorrectMapping(_page);
        }
        return _other.getFilterContent().getRoot() == null;
    }

    private static CustList<ReachFilterContent> previous(ReachBlock _bl) {
        CustList<ReachFilterContent> classes_ = new CustList<ReachFilterContent>();
        if (_bl instanceof ReachCatchEval) {
            ReachBlock b_ = _bl.getPreviousSibling();
            while (b_ instanceof ReachFilterContent) {
                if (!((ReachFilterContent) b_).getFilterContent().getImportedType().isEmpty()) {
                    classes_.add((ReachFilterContent) b_);
                }
                b_ = b_.getPreviousSibling();
            }
            return classes_;
        }
        ReachBlock b_ = _bl.getPreviousSibling();
        while (b_ != null) {
            if (b_ instanceof ReachFilterContent&&!((ReachFilterContent) b_).getFilterContent().getImportedType().isEmpty()) {
                classes_.add((ReachFilterContent) b_);
            }
            b_ = b_.getPreviousSibling();
        }
        return classes_;
    }

    @Override
    public boolean isAll() {
        return false;
    }
}
