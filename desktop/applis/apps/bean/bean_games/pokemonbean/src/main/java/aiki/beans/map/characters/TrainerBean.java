package aiki.beans.map.characters;
import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.map.characters.GymLeader;
import aiki.map.characters.Trainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.pokemon.PokemonTeam;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.StringMap;

public class TrainerBean extends CommonBean {
    static final String PAGE_TEAM = "web/html/map/elements/pokemon_team.html";
    private Trainer trainer;
    private String move = DataBase.EMPTY_STRING;
    private String image;
    private String imageMini;

    @Override
    public void beforeDisplaying() {
        trainer = (Trainer) getForms().getValPers(CST_PERSON);
        DataBase data_ = getDataBase();
        if (trainer instanceof GymLeader) {
            GymLeader gym_ = (GymLeader) trainer;
            move = data_.getTm().getVal(gym_.getTm());
        }
        image = BaseSixtyFourUtil.getStringByImage(data_.getTrainer(trainer.getImageMaxiFileName()));
        imageMini = BaseSixtyFourUtil.getStringByImage(data_.getPerson(trainer.getImageMiniFileName()));
    }
    public String getName() {
        if (trainer instanceof GymLeader) {
            return ((GymLeader)trainer).getName();
        }
        if (trainer instanceof TrainerLeague) {
            return ((TrainerLeague)trainer).getName();
        }
        return DataBase.EMPTY_STRING;
    }
    public String getTrMove() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(move);
    }
    public String clickMove() {
        return tryRedirectMv(move);
    }
    public CustList<PokemonTeam> getTeamsRewards() {
        if (trainer instanceof TrainerMultiFights) {
            return ((TrainerMultiFights)trainer).getTeamsRewards();
        }
        return new CustList<PokemonTeam>();
    }

    public String getImage() {
        return image;
    }

    public String getImageMini() {
        return imageMini;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public String getMove() {
        return move;
    }
}