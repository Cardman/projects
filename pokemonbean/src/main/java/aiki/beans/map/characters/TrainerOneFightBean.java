package aiki.beans.map.characters;
import code.bean.Accessible;
import aiki.beans.CommonBean;
import aiki.map.characters.Trainer;

public class TrainerOneFightBean extends CommonBean {

    @Accessible
    private final String pageTeam = "web/html/map/elements/pokemon_team.html";

    @Accessible
    private Trainer trainer;

    @Override
    public void beforeDisplaying() {
        trainer = (Trainer) getForms().getVal(TRAINER);
    }
}
