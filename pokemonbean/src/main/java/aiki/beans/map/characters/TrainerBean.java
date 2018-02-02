package aiki.beans.map.characters;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.util.CustList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.map.characters.GymLeader;
import aiki.map.characters.Trainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.pokemon.PokemonTeam;

public class TrainerBean extends CommonBean {

    @Accessible
    private final String pageTeam = "web/html/map/elements/pokemon_team.html";

    @Accessible
    private Trainer trainer;

    @Accessible
    private String move = DataBase.EMPTY_STRING;

    @Accessible
    private String image;

    @Accessible
    private String imageMini;

    @Override
    public void beforeDisplaying() {
        trainer = (Trainer) getForms().getVal(TRAINER);
        DataBase data_ = (DataBase) getDataBase();
        if (trainer instanceof GymLeader) {
            GymLeader gym_ = (GymLeader) trainer;
            move = data_.getTm().getVal(gym_.getTm());
        }
        image = ConverterBufferedImage.surroundImage(data_.getTrainer(trainer.getImageMaxiFileName()));
        imageMini = ConverterBufferedImage.surroundImage(data_.getPerson(trainer.getImageMiniFileName()));
    }

    @Accessible
    private String getName() {
        if (trainer instanceof GymLeader) {
            return ((GymLeader)trainer).getName();
        }
        if (trainer instanceof TrainerLeague) {
            return ((TrainerLeague)trainer).getName();
        }
        return DataBase.EMPTY_STRING;
    }

    @Accessible
    private String getTrMove() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(move);
    }

    @Accessible
    private String clickMove() {
        getForms().put(MOVE, move);
        return MOVE;
    }
    public CustList<PokemonTeam> getTeamsRewards() {
        if (trainer instanceof TrainerMultiFights) {
            return ((TrainerMultiFights)trainer).getTeamsRewards();
        }
        return new CustList<PokemonTeam>();
    }
}
