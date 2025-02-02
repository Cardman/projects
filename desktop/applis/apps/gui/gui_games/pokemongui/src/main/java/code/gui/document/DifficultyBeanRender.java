package code.gui.document;

import aiki.beans.*;
import aiki.beans.game.*;
import aiki.facade.*;

public final class DifficultyBeanRender extends AbsBeanRender {
    private final DifficultyBean bean = new DifficultyBean();
    private final DifficultyBeanForm form = new DifficultyBeanForm();
    private IntBeanChgSubmit updateValues;
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade,_form);
        initPage();
        setBackgroundBody();
        setTitledBorder(formatMessageRend(MessagesPkBean.DIFFICULTY,MessagesGameDifficulty.M_P_93_TITLE));
        getBuilder().displayDiff(form,this, bean.getDifficultyCommon(),MessagesPkBean.DIFFICULTY);
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.DIFFICULTY,MessagesGameDifficulty.M_P_93_OK));
        updateValues.addEvt(new DifficultyBeanFormEvent(bean, form));
    }

    @Override
    public String appName() {
        return MessagesPkBean.APP_BEAN;
    }

    public DifficultyBeanForm getForm() {
        return form;
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

}
