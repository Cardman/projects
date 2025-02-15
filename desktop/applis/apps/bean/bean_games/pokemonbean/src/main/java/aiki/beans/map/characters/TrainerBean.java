package aiki.beans.map.characters;
import aiki.beans.*;
import aiki.db.*;
import aiki.map.characters.*;
import aiki.map.pokemon.*;
import code.scripts.confs.*;
import code.util.*;

public class TrainerBean extends CommonBean {
    static final String PAGE_TEAM = PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_POKEMON_TEAM_HTML;
    private Trainer trainer;
    private String name = DataBase.EMPTY_STRING;
    private TranslatedKey move;
    private int[][] image;
    private int[][] imageMini;

    @Override
    public void beforeDisplaying() {
        trainer = (Trainer) getForms().getValPers(CST_PERSON);
        DataBase data_ = getDataBase();
        name = data_.getMap().getTrainerName(getForms().getValCoords(CST_COORDS));
        if (trainer instanceof GymLeader) {
            GymLeader gym_ = (GymLeader) trainer;
            move = buildMv(getFacade(),data_.getTm().getVal(gym_.getTm()));
        } else {
            move = buildMv(getFacade(),DataBase.EMPTY_STRING);
        }
        image = data_.getTrainer(trainer.getImageMaxiFileName());
        imageMini = data_.getPerson(trainer.getImageMiniFileName());
    }
    public String getName() {
        return name;
    }
    public String getTrMove() {
        return move.getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(move);
    }
    public String clickMove() {
        return tryRedirect(move);
    }
    public CustList<PokemonTeam> getTeamsRewards() {
        if (trainer instanceof TrainerMultiFights) {
            return ((TrainerMultiFights)trainer).getTeamsRewards();
        }
        return new CustList<PokemonTeam>();
    }

    public int[][] getImage() {
        return image;
    }

    public int[][] getImageMini() {
        return imageMini;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public String getMove() {
        return move.getKey();
    }
}