package aiki.beans.map.characters;
import aiki.beans.*;
import aiki.beans.map.pokemon.PokemonTeamBean;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.map.characters.Ally;
import aiki.map.characters.TempTrainer;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class DualFightBean extends CommonBean implements BeanRenderWithAppName {
    static final String PAGE_ALLY = PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_ALLY_HTML;
    static final String PAGE_TEAM = PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_POKEMON_TEAM_HTML;
    private TempTrainer trainer;
    private Ally ally;
    private int[][] image;
    private int[][] imageMini;
    private int[][] imageMiniSecond;
    private AllyBean allyTeam;
    private PokemonTeamBean foeTeam;
    private StringList names;

    public DualFightBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataMapLevel.M_P_32_TITLE_DUAL));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this), MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_INDEX);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_MAP_HTML,this),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_MAP);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,this),MessagesPkBean.MAP, MessagesDataMapLevel.M_P_32_LEVEL);
        addImg(image);
        addImg(imageMini);
        addImg(imageMiniSecond);
        displayStringList(names);
        allyTeam.beforeDisplaying();
        allyTeam.buildSub();
        foeTeam.beforeDisplaying();
        foeTeam.buildSub();
    }

    public AllyBean getAllyTeam() {
        return allyTeam;
    }

    public PokemonTeamBean getFoeTeam() {
        return foeTeam;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.MAP).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        trainer = (TempTrainer) getForms().getValPers(CST_PERSON);
        ally = getForms().getValAlly(CST_ALLY);
        names = getForms().getValList(CST_NAMES);
        DataBase data_ = getDataBase();
        image = data_.getTrainer(trainer.getImageMaxiFileName());
        imageMini = data_.getPerson(trainer.getImageMiniFileName());
        imageMiniSecond = data_.getPerson(trainer.getImageMiniSecondTrainerFileName());
        allyTeam = new AllyBean();
        fwd(allyTeam);
        allyTeam.setAlly(ally);
        foeTeam = new PokemonTeamBean();
        fwd(foeTeam);
        foeTeam.setTrainer(trainer);

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