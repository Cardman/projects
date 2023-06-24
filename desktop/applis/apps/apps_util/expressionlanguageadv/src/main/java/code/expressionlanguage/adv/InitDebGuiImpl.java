package code.expressionlanguage.adv;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;

public final class InitDebGuiImpl extends AbsDebuggerGui {
    private final MethodFromFilter methodFilter;
    private AbsCustCheckBox vararg;
    private AbsCustCheckBox retRef;
    private AbsCustCheckBox paramRef;
    private AbsTextField classesField;
    private AutoCompleteDocument classesFieldAutoComplete;
    private AbsTextField clMethField;
    private FormInputDebugLines formInputDebugLines;
    private AutoCompleteDocument clMethFieldAutoComplete;

    public InitDebGuiImpl(AbsResultContextNext _a, String _lg, AbstractProgramInfos _list, CdmFactory _fact) {
        super(_a,_lg, _list, _fact);
        methodFilter = new MethodFromFilter();
    }

    @Override
    protected AbsPanel buildPart() {
        AbsCommonFrame frame_ = getCommonFrame();
        AbstractProgramInfos pr_ = frame_.getFrames();
        vararg = pr_.getCompoFactory().newCustCheckBox("vararg");
        retRef = pr_.getCompoFactory().newCustCheckBox("return");
        paramRef = pr_.getCompoFactory().newCustCheckBox("params");
        classesField = pr_.getCompoFactory().newTextField();
        clMethField = pr_.getCompoFactory().newTextField();
        classesFieldAutoComplete = new AutoCompleteDocument(classesField, new StringList(), pr_,new FeedExpressionClassValue());
        clMethFieldAutoComplete = new AutoCompleteDocument(clMethField, new StringList(), pr_, methodFilter);
        AbsPanel page_ = pr_.getCompoFactory().newPageBox();
        page_.add(vararg);
        page_.add(retRef);
        page_.add(paramRef);
        page_.add(classesField);
        page_.add(clMethField);
        methodFilter.setClassesField(classesField);
        methodFilter.setVararg(vararg);
        formInputDebugLines = new FormInputDebugLines(frame_);
        page_.add(formInputDebugLines.getScrollPaneGl());
        return page_;
    }

    @Override
    public void update(ResultContext _res, StringMap<String> _src) {
        super.update(_res, _src);
        if (_res.getPageEl().notAllEmptyErrors()) {
            return;
        }
        methodFilter.setResult(_res);
        StringList dict_ = new StringList(_res.getContext().getClasses().getClassesBodies().getKeys());
        classesFieldAutoComplete.setDictionary(dict_);
    }

    @Override
    protected CallingState look() {
        String idCl_ = classesField.getText();
        ExecRootBlock type_ = selectedType(idCl_);
        CustList<ExecOverridableBlock> res_ = methods(type_);
        if (!res_.isEmpty()) {
            Argument argGlLoc_ = new Argument();
            Parameters p_ = new Parameters();
            String name_ = res_.first().getParametersName(0);
            String argType_ = StringExpUtil.getPrettyArrayType(getCurrentResult().getPageEl().getAliasString());
            int s_ = formInputDebugLines.getOutput().size();
            ArrayStruct arr_ = new ArrayStruct(s_, argType_);
            for (int i = 0; i < s_; i++) {
                arr_.set(i, formInputDebugLines.getOutput().get(i));
            }
            p_.getRefParameters().addEntry(name_,new VariableWrapper(LocalVariable.newLocalVariable(arr_, argType_)));
            return new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(type_, idCl_), new ExecTypeFunction(type_, res_.first()), p_);
        }
        return null;
    }

    @Override
    protected void endCall() {
        setStackCall(null);
        super.endCall();
    }

    public FormInputDebugLines getFormInputDebugLines() {
        return formInputDebugLines;
    }

    public AbsTextField getClassesField() {
        return classesField;
    }

    public ExecRootBlock selectedType(String _d) {
        ResultContext r_ = getCurrentResult();
        if (r_ == null) {
            return null;
        }
        return r_.getContext().getClasses().getClassesBodies().getVal(_d);
    }

    public CustList<ExecOverridableBlock> methods(ExecRootBlock _type) {
        ResultContext r_ = getCurrentResult();
        if (r_ == null) {
            return new CustList<ExecOverridableBlock>();
        }
        if (_type == null) {
            return new CustList<ExecOverridableBlock>();
        }
        CustList<BoolVal> refs_ = new CustList<BoolVal>();
        refs_.add(ComparatorBoolean.of(paramRef.isSelected()));
        CustList<String> cls_ = new CustList<String>();
        if (vararg.isSelected()) {
            cls_.add(r_.getPageEl().getAliasString());
        } else {
            cls_.add(StringExpUtil.getPrettyArrayType(r_.getPageEl().getAliasString()));
        }
        return ExecClassesUtil.getMethodBodiesById(_type, new MethodId(retRef.isSelected(), MethodAccessKind.STATIC, clMethField.getText(), cls_, refs_, vararg.isSelected()));
    }

    public AbsCustCheckBox getRetRef() {
        return retRef;
    }

    public AbsCustCheckBox getParamRef() {
        return paramRef;
    }

    public AbsCustCheckBox getVararg() {
        return vararg;
    }

    public AutoCompleteDocument getClMethFieldAutoComplete() {
        return clMethFieldAutoComplete;
    }

    public AutoCompleteDocument getClassesFieldAutoComplete() {
        return classesFieldAutoComplete;
    }
}
