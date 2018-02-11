package aiki.beans.map.characters;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.map.characters.Ally;
import aiki.map.characters.TempTrainer;

public class DualFightBean extends CommonBean {

    @Accessible
    private final String pageAlly = "web/html/map/elements/ally.html";

    @Accessible
    private final String pageTeam = "web/html/map/elements/pokemon_team.html";

    @Accessible
    private TempTrainer trainer;

    @Accessible
    private Ally ally;

    @Accessible
    private String image;

    @Accessible
    private String imageMini;

    @Accessible
    private String imageMiniSecond;

    @Override
    public void beforeDisplaying() {
        trainer = (TempTrainer) getForms().getVal(TRAINER);
        ally = (Ally) getForms().getVal(ALLY);
        DataBase data_ = (DataBase) getDataBase();
        image = ConverterBufferedImage.surroundImage(data_.getTrainer(trainer.getImageMaxiFileName()));
        imageMini = ConverterBufferedImage.surroundImage(data_.getPerson(trainer.getImageMiniFileName()));
        imageMiniSecond = ConverterBufferedImage.surroundImage(data_.getPerson(trainer.getImageMiniSecondTrainerFileName()));
    }
}
