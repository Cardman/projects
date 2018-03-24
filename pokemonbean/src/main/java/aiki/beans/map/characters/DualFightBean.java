package aiki.beans.map.characters;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.map.characters.Ally;
import aiki.map.characters.TempTrainer;
import code.util.opers.BaseSixtyFourUtil;

public class DualFightBean extends CommonBean {
    private final String pageAlly = "web/html/map/elements/ally.html";
    private final String pageTeam = "web/html/map/elements/pokemon_team.html";
    private TempTrainer trainer;
    private Ally ally;
    private String image;
    private String imageMini;
    private String imageMiniSecond;

    @Override
    public void beforeDisplaying() {
        trainer = (TempTrainer) getForms().getVal(TRAINER);
        ally = (Ally) getForms().getVal(ALLY);
        DataBase data_ = (DataBase) getDataBase();
        image = BaseSixtyFourUtil.getSringByImage(data_.getTrainer(trainer.getImageMaxiFileName()));
        imageMini = BaseSixtyFourUtil.getSringByImage(data_.getPerson(trainer.getImageMiniFileName()));
        imageMiniSecond = BaseSixtyFourUtil.getSringByImage(data_.getPerson(trainer.getImageMiniSecondTrainerFileName()));
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

    public String getPageAlly() {
        return pageAlly;
    }

    public Ally getAlly() {
        return ally;
    }

    public String getPageTeam() {
        return pageTeam;
    }

    public TempTrainer getTrainer() {
        return trainer;
    }
}