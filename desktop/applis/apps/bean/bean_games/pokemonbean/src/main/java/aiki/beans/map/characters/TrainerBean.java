package aiki.beans.map.characters;
import aiki.beans.*;
import aiki.beans.map.pokemon.PokemonTeamBean;
import aiki.db.*;
import aiki.facade.FacadeGame;
import aiki.map.characters.*;
import aiki.map.pokemon.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.MessagesDataMapLevel;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;
import code.util.core.StringUtil;

public final class TrainerBean extends CommonBean implements BeanRenderWithAppName {
    static final String PAGE_TEAM = PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_POKEMON_TEAM_HTML;
    private Trainer trainer;
    private String name = DataBase.EMPTY_STRING;
    private TranslatedKey move;
    private int[][] image;
    private int[][] imageMini;
    private CustList<PokemonTeamBean> beans;
    public TrainerBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        if (!getName().isEmpty()) {
            setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataMapLevel.M_P_32_TITLE_FIGHT),getName()));
        } else {
            setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataMapLevel.M_P_32_TITLE_FIGHT_STAND),getName()));
        }
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_INDEX);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_MAP_HTML,this),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_MAP);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,this),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_LEVEL);
        addImg(image);
        addImg(imageMini);
        formatMessageDir(name);
        int len_ = beans.size();
        for (int i = 0; i < len_; i++) {
            beans.get(i).beforeDisplaying();
            formatMessageDir(Long.toString(i));
            beans.get(i).buildSub();
        }
        formatMessageDir(move);
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.MAP).getMapping();
    }
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
        CustList<PokemonTeamBean> bs_ = new CustList<PokemonTeamBean>();
        if (trainer instanceof TrainerMultiFights) {
            CustList<PokemonTeam> teams_ = ((TrainerMultiFights) trainer).getTeamsRewards();
            int len_ = teams_.size();
            for (int i = 0; i < len_; i++) {
                PokemonTeamBean b_ = new PokemonTeamBean();
                fwd(b_);
                b_.setTrainer(trainer);
                b_.setNoFight(i);
                bs_.add(b_);
            }
        } else {
            PokemonTeamBean b_ = new PokemonTeamBean();
            fwd(b_);
            b_.setTrainer(trainer);
            bs_.add(b_);
        }
        beans = bs_;
    }

    public CustList<PokemonTeamBean> getBeans() {
        return beans;
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