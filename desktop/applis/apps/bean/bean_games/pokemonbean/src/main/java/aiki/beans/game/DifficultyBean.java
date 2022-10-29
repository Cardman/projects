package aiki.beans.game;

import aiki.beans.CommonSingleBean;
import aiki.beans.DifficultyCommon;
import aiki.beans.WithDifficultyCommon;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.params.Difficulty;

public class DifficultyBean extends CommonSingleBean implements WithDifficultyCommon {
    private final DifficultyCommon difficultyCommon = new DifficultyCommon();
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

}