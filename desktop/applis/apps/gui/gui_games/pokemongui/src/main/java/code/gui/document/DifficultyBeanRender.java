package code.gui.document;

import aiki.beans.*;
import aiki.beans.game.*;
import aiki.facade.*;
import code.gui.*;

public final class DifficultyBeanRender extends AbsBeanRender {
    private final DifficultyBean bean = new DifficultyBean();
    private final DifficultyBeanForm form = new DifficultyBeanForm();
    private AbsButton updateValues;
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade,_form);
        initPage();
        setBackground(GuiConstants.WHITE);
        setTitledBorder(formatMessageRend(MessagesPkBean.DIFFICULTY,MessagesGameDifficulty.M_P_93_TITLE));
        getBuilder().displayDiff(form,this, bean.getDifficultyCommon(),MessagesPkBean.DIFFICULTY);
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.DIFFICULTY,MessagesGameDifficulty.M_P_93_OK));
        updateValues.addActionListener(new BeanAnchorEvent(getBuilder(),new DifficultyBeanFormEvent(bean, form)));
        feedParent(updateValues);
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
