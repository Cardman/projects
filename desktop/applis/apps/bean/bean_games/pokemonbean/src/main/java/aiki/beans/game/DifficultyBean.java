package aiki.beans.game;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.params.Difficulty;
import code.scripts.pages.aiki.MessagesGameDifficulty;
import code.scripts.pages.aiki.MessagesPkBean;

public class DifficultyBean extends CommonSingleBean implements WithDifficultyCommon, BeanRenderWithAppName {
    private final DifficultyCommon difficultyCommon = new DifficultyCommon();
    private final DifficultyBeanForm form = new DifficultyBeanForm();
    private IntBeanChgSubmit updateValues;

    public DifficultyBean() {
        setAppName(MessagesPkBean.APP_BEAN);
    }

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(this,getFacade(),_form);
        setTitledBorder(formatMessageRend(MessagesPkBean.DIFFICULTY, MessagesGameDifficulty.M_P_93_TITLE));
        form.displayDiff(getBuilder().getGenInput(), this, getDifficultyCommon(), MessagesPkBean.DIFFICULTY);
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.DIFFICULTY,MessagesGameDifficulty.M_P_93_OK));
        updateValues.addEvt(new DifficultyBeanFormEvent(this, form));
    }

    @Override
    public void beforeDisplaying() {
        FacadeGame facadeGame_ = facade();
        DataBase data_ = facadeGame_.getData();
        Difficulty diff_ = facadeGame_.getGame().getDifficulty();
        difficultyCommon.init(data_,getLanguage(),diff_);
    }
    public void change() {
        FacadeGame facadeGame_ = facade();
        Difficulty diff_ = facadeGame_.getGame().getDifficulty();
        difficultyCommon.apply(facadeGame_.getData(),diff_);
    }

    public DifficultyCommon getDifficultyCommon() {
        return difficultyCommon;
    }

    public DifficultyBeanForm getForm() {
        return form;
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }
}