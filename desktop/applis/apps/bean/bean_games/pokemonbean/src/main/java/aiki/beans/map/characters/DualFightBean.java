package aiki.beans.map.characters;
import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.map.characters.Ally;
import aiki.map.characters.TempTrainer;
import code.images.BaseSixtyFourUtil;

public class DualFightBean extends CommonBean {
    static final String PAGE_ALLY = "web/html/map/elements/ally.html";
    static final String PAGE_TEAM = "web/html/map/elements/pokemon_team.html";
    private TempTrainer trainer;
    private Ally ally;
    private String image;
    private String imageMini;
    private String imageMiniSecond;

    @Override
    public void beforeDisplaying() {
        trainer = (TempTrainer) getForms().getValPers(CST_PERSON);
        ally = getForms().getValAlly(CST_ALLY);
        DataBase data_ = getDataBase();
        image = BaseSixtyFourUtil.getStringByImage(data_.getTrainer(trainer.getImageMaxiFileName()));
        imageMini = BaseSixtyFourUtil.getStringByImage(data_.getPerson(trainer.getImageMiniFileName()));
        imageMiniSecond = BaseSixtyFourUtil.getStringByImage(data_.getPerson(trainer.getImageMiniSecondTrainerFileName()));
    }

    public String getImage() {
        return image;
    }

    public String getImageMini() {
        return imageMini;
    }

    public String getImageMiniSecond() {
        return imageMiniSecond;
    }

    public Ally getAlly() {
        return ally;
    }

    public TempTrainer getTrainer() {
        return trainer;
    }
}