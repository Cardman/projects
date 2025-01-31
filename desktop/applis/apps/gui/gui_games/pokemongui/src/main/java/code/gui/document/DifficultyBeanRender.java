package code.gui.document;

import aiki.beans.*;
import aiki.beans.game.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class DifficultyBeanRender extends AbsBeanRender {
    private final DifficultyBean bean = new DifficultyBean();
    private final DifficultyBeanForm form = new DifficultyBeanForm();
    private AbsButton updateValues;
    @Override
    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade,_form);
        AbsPanel form_ = _api.getCompoFactory().newPageBox();
        form_.setBackground(GuiConstants.WHITE);
        form_.setTitledBorder(formatMessage(_api,MessagesPkBean.DIFFICULTY,MessagesGameDifficulty.M_P_93_TITLE));
        form.displayDiff(this,form_,bean.getDifficultyCommon(),_api,MessagesPkBean.DIFFICULTY);
        updateValues = _api.getCompoFactory().newPlainButton(formatMessage(_api,MessagesPkBean.DIFFICULTY,MessagesGameDifficulty.M_P_93_OK));
        updateValues.addActionListener(new DifficultyBeanFormEvent(getRenders(),this));
        feedParents(form_,updateValues);
        return form_;
    }

    public void apply() {
        form.update(bean.getDifficultyCommon());
        bean.change();
    }

    @Override
    public String appName() {
        return MessagesPkBean.APP_BEAN;
    }

    public DifficultyBeanForm getForm() {
        return form;
    }

    public AbsButton getUpdateValues() {
        return updateValues;
    }

}
