package aiki.beans.map.characters;
import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.map.characters.Ally;
import aiki.map.characters.TempTrainer;
import code.scripts.confs.PkScriptPages;

public class DualFightBean extends CommonBean {
    static final String PAGE_ALLY = PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_ALLY_HTML;
    static final String PAGE_TEAM = PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_POKEMON_TEAM_HTML;
    private TempTrainer trainer;
    private Ally ally;
    private int[][] image;
    private int[][] imageMini;
    private int[][] imageMiniSecond;

    @Override
    public void beforeDisplaying() {
        trainer = (TempTrainer) getForms().getValPers(CST_PERSON);
        ally = getForms().getValAlly(CST_ALLY);
        DataBase data_ = getDataBase();
        image = data_.getTrainer(trainer.getImageMaxiFileName());
        imageMini = data_.getPerson(trainer.getImageMiniFileName());
        imageMiniSecond = data_.getPerson(trainer.getImageMiniSecondTrainerFileName());
    }

    public int[][] getImage() {
        return image;
    }

    public int[][] getImageMini() {
        return imageMini;
    }

    public int[][] getImageMiniSecond() {
        return imageMiniSecond;
    }

    public Ally getAlly() {
        return ally;
    }

    public TempTrainer getTrainer() {
        return trainer;
    }
}