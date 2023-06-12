package code.expressionlanguage.adv;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCustCheckBox;
import code.gui.AbsTextField;
import code.gui.AbsTxtComponent;
import code.gui.GuiConstants;
import code.gui.events.AfterValidateText;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MethodFromFilter implements AfterValidateText {
    private ResultContext result;
    private AbsCustCheckBox vararg;
    private AbsTextField classesField;

    @Override
    public void act(AbsTxtComponent _compo, String _typed) {
        _compo.setText(_typed);
        _compo.setCaretPosition(_typed.length());
    }

    @Override
    public StringList filter(String _look, CustList<String> _dict) {
        ResultContext r_ = getResult();
        if (r_ == null) {
            return new StringList();
        }
        ExecRootBlock type_ = r_.getContext().getClasses().getClassesBodies().getVal(getClassesField().getText());
        if (type_ == null) {
            getClassesField().setLineBorder(GuiConstants.RED);
            return new StringList();
        }
        getClassesField().setLineBorder(GuiConstants.GREEN);
        String tr_ = _look.trim();
        StringList result_ = new StringList();
        for (ExecBlock m: type_.getChildrenOthers()) {
            if (m instanceof ExecOverridableBlock && matchSgn(r_,(ExecOverridableBlock) m) && ((ExecOverridableBlock) m).getName().startsWith(tr_)) {
                result_.add(((ExecOverridableBlock) m).getName());
            }
        }
        return result_;
    }

    private boolean matchSgn(ResultContext _r,ExecOverridableBlock _p) {
        if (_p.getModifier() != MethodModifier.STATIC) {
            return false;
        }
        if (_p.getImportedParametersTypes().size() != 1) {
            return false;
        }
        if (_p.isVarargs()) {
            return getVararg().isSelected() && StringUtil.quickEq(_r.getPageEl().getAliasString(), _p.getImportedParametersTypes().get(0));
        }
        return !getVararg().isSelected() && StringUtil.quickEq(StringExpUtil.getPrettyArrayType(_r.getPageEl().getAliasString()), _p.getImportedParametersTypes().get(0));
    }

    public ResultContext getResult() {
        return result;
    }

    public void setResult(ResultContext _r) {
        this.result = _r;
    }

    public AbsCustCheckBox getVararg() {
        return vararg;
    }

    public void setVararg(AbsCustCheckBox _v) {
        this.vararg = _v;
    }

    public AbsTextField getClassesField() {
        return classesField;
    }

    public void setClassesField(AbsTextField _c) {
        this.classesField = _c;
    }
}
