package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.calls.util.CustomReflectConstructor;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ExpDebGuiImpl extends AbsDebuggerGui {
    private FormFindReplaceExpression findReplaceExpression;
    private AbsButton selectClass;
    private AbsTextArea text;
    private AbsTextArea textOutput;
    private Struct instance = NullStruct.NULL_VALUE;
    private String typed = EMPTY_STRING;
    private final CustList<SegmentFindPart> found = new CustList<SegmentFindPart>();
    private int currentIndex;
    private AbsSpinner minValue;
    private AbsSpinner maxValue;
    private int fromIndex = -1;
    private int toIndex = -1;
    private int occ = -1;
    private StringBuilder copy = new StringBuilder();

    public ExpDebGuiImpl(AbsOpenFrameInteract _m, AbsResultContextNext _a, AbstractProgramInfos _list, CdmFactory _fact) {
        super(_m,_a, _list, _fact);
        getCommonFrame().addWindowListener(new CloseDbgFrame(this));
    }

    @Override
    protected AbsPanel buildPart() {
        AbstractProgramInfos pr_ = getFrames();
        StringMap<String> mes_ = MessagesIde.valForms(pr_.currentLg());
        findReplaceExpression = new FormFindReplaceExpression(pr_);
        AbsPanel page_ = pr_.getCompoFactory().newPageBox();
        page_.add(findReplaceExpression.getFinderExpClasses());
        selectClass = pr_.getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_FORMS_CLASS)));
        selectClass.addActionListener(new SelectClassDbgEvent(findReplaceExpression));
        page_.add(selectClass);
        text = pr_.getCompoFactory().newTextArea();
        page_.add(getCompoFactory().newAbsScrollPane(text));
        textOutput = pr_.getCompoFactory().newTextArea();
        page_.add(getCompoFactory().newAbsScrollPane(textOutput));
        minValue = pr_.getCompoFactory().newSpinner(-1,-1,-1,1);
        page_.add(minValue);
        maxValue = pr_.getCompoFactory().newSpinner(-1,-1,-1,1);
        page_.add(maxValue);
        currentIndex = 0;
        fromIndex = -1;
        toIndex = -1;
        occ = -1;
        found.clear();
        copy = new StringBuilder();
        return page_;
    }

    @Override
    public void update(ResultContext _res, StringMap<String> _src) {
        super.update(_res, _src);
        if (_res.getPageEl().notAllEmptyErrors()) {
            return;
        }
        findReplaceExpression.refresh(_res,_res.getContext());
    }

    @Override
    protected CallingState look(ResultContext _res) {
        ExecConstructorOverrideInfo info_ = findReplaceExpression.getTargetMethodView();
        if (info_ == null) {
            return null;
        }
        if (instance == NullStruct.NULL_VALUE) {
            setStackCall(null);
            _res.getContext().getInterrupt().set(false);
            getStoppedClick().set(false);
            typed = text.getText();
            ContextEl rCont_ = _res.getContext();
            ArrayStruct empty_ = new ArrayStruct(0, StringExpUtil.getPrettyArrayType(rCont_.getStandards().getCoreNames().getAliasObject()));
            ArrayRefState a_ = ArrayRefState.tryWrap(empty_,0);
            return new CustomReflectConstructor(info_.getMetaInfo(),a_);
        }
        if (currentIndex >= 0) {
            ExecOverrideInfo targetMethod_ = info_.getOverrideInfo();
            ExecNamedFunctionBlock fct_ = targetMethod_.getPair().getFct();
            String nameOne_ = fct_.getParametersName(0);
            String nameTwo_ = fct_.getParametersName(1);
            Parameters p_ = new Parameters();
            p_.getRefParameters().addEntry(nameOne_,new VariableWrapper(LocalVariable.newLocalVariable(new StringStruct(typed), _res.getContext())));
            p_.getRefParameters().addEntry(nameTwo_,new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(currentIndex), _res.getContext())));
            return new CustomFoundMethod(instance,targetMethod_.getClassName(), targetMethod_.getPair(), p_);
        }
        if (fromIndex == -1) {
            int v_ = minValue.getValue();
            if (v_ == -1) {
                fromIndex = 0;
            } else {
                fromIndex = v_;
            }
        }
        if (toIndex == -1) {
            int v_ = maxValue.getValue();
            if (v_ == -1) {
                toIndex = maxValue.getMax();
            } else {
                toIndex = v_;
            }
            occ = toIndex;
        }
        ExecOverrideInfo targetMethod_ = findReplaceExpression.getTargetMethodReplace().getOverrideInfo();
        if (fromIndex > toIndex) {
            occ = -1;
            targetMethod_ = null;
        }
        if (targetMethod_ == null) {
            getStopStack().setEnabled(false);
            setStackCall(null);
            getAnalyzeMenu().setEnabled(true);
            instance = NullStruct.NULL_VALUE;
            found.clear();
            fromIndex = -1;
            toIndex = -1;
            copy = new StringBuilder();
            return null;
        }
        ExecNamedFunctionBlock fct_ = targetMethod_.getPair().getFct();
        String nameOne_ = fct_.getParametersName(0);
        String nameTwo_ = fct_.getParametersName(1);
        String nameThree_ = fct_.getParametersName(2);
        String nameFour_ = fct_.getParametersName(3);
        SegmentFindPart seg_ = found.get(occ);
        Parameters p_ = new Parameters();
        p_.getRefParameters().addEntry(nameOne_,new VariableWrapper(LocalVariable.newLocalVariable(new StringStruct(copy.toString()), _res.getContext())));
        p_.getRefParameters().addEntry(nameTwo_,new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(occ), _res.getContext())));
        p_.getRefParameters().addEntry(nameThree_,new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(seg_.getBegin()), _res.getContext())));
        p_.getRefParameters().addEntry(nameFour_,new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(seg_.getEnd()), _res.getContext())));
        return new CustomFoundMethod(instance,targetMethod_.getClassName(), targetMethod_.getPair(), p_);
    }

    @Override
    protected void endCall() {
        if (instance == NullStruct.NULL_VALUE) {
            instance = getStackCallView().getStack().aw().getValue();
            currentIndex = 0;
            super.endCall();
            getStopStack().setEnabled(true);
            return;
        }
        Struct re_= getStackCallView().getStack().aw().getValue();
        if (toIndex == -1) {
            finding(re_);
        }
        if (currentIndex == -1) {
            if (fromIndex >= 0) {
                SegmentFindPart seg_ = found.get(occ);
                StringStruct str_ = NumParsers.getString(re_);
                copy.replace(seg_.getBegin(),seg_.getEnd(),str_.getInstance());
                textOutput.setText(copy.toString());
                occ--;
                if (occ < fromIndex) {
                    getStopStack().setEnabled(false);
                    instance = NullStruct.NULL_VALUE;
                    found.clear();
                    fromIndex = -1;
                    toIndex = -1;
                    copy = new StringBuilder();
                } else {
                    getStopStack().setEnabled(true);
                }
            } else if (findReplaceExpression.getTargetMethodReplace() != null && !found.isEmpty()) {
                getStopStack().setEnabled(true);
                minValue.setMax(found.size()-1);
                maxValue.setMax(found.size()-1);
                copy = new StringBuilder(typed);
                textOutput.setText(typed);
                occ = -1;
            } else {
                getStopStack().setEnabled(false);
                instance = NullStruct.NULL_VALUE;
                found.clear();
                copy = new StringBuilder();
            }
        } else {
            getStopStack().setEnabled(true);
        }
        super.endCall();
    }

    private void finding(Struct _re) {
        if (!(_re instanceof FieldableStruct)) {
            currentIndex = -1;
        } else {
            ResultContextViewReplacer vr_ = findReplaceExpression.getResultContext();
            int begin_ = NumParsers.convertToNumber(((FieldableStruct) _re).getEntryStruct(new ClassField(vr_.getAliasStringSegment(), vr_.getAliasStringSegmentBegin())).getStruct()).intStruct();
            int end_ = NumParsers.convertToNumber(((FieldableStruct) _re).getEntryStruct(new ClassField(vr_.getAliasStringSegment(), vr_.getAliasStringSegmentEnd())).getStruct()).intStruct();
            if (begin_ < currentIndex || begin_ >= end_) {
                currentIndex = -1;
            } else {
                int next_ = NumberUtil.min(end_, typed.length());
                found.add(new SegmentFindPart(begin_, next_));
                currentIndex = next_;
            }
        }
    }

    public CustList<SegmentFindPart> getFound() {
        return found;
    }

    public AbsButton getSelectClass() {
        return selectClass;
    }

    public FormFindReplaceExpression getFindReplaceExpression() {
        return findReplaceExpression;
    }

    public AbsSpinner getMinValue() {
        return minValue;
    }

    public AbsSpinner getMaxValue() {
        return maxValue;
    }

    public AbsTextArea getText() {
        return text;
    }

    public AbsTextArea getTextOutput() {
        return textOutput;
    }
}
