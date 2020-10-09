package aiki.beans;
import aiki.db.DataBase;
import code.bean.Bean;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class CommonBean extends Bean {
    protected static final String ABILITIES = "abilities";
    protected static final String ABILITIES_SET = "abilities_set";
    protected static final String ABILITY = "ability";
    protected static final String ALLY = "ally";
    protected static final String AREA = "area";
    protected static final String BALL = "ball";
    protected static final String BERRY = "berry";
    protected static final String BOOST = "boost";
    protected static final String COMBO = "combo";
    protected static final String SIMULATION = "simulation";
    protected static final String SIMULATION_STATE = "simulation_state";
    protected static final String CURRENT_TILE = "current_tile";
    protected static final String DEALER = "dealer";
    protected static final String DUAL = "dual";
    protected static final String EVO_ITEM = "evo_item";
    protected static final String EVO_STONE = "evo_stone";
    protected static final String EVOLVINGITEM = "evolvingitem";
    protected static final String EVOLVINGSTONE = "evolvingstone";
    protected static final String FOSSIL = "fossil";
    protected static final String FROM_LIST = "from_list";
    protected static final String HEALINGHP = "healinghp";
    protected static final String HEALINGHPSTATUS = "healinghpstatus";
    protected static final String HEALINGITEM = "healingitem";
    protected static final String HEALINGPP = "healingpp";
    protected static final String HEALINGSTATUS = "healingstatus";
    protected static final String INSIDE = "inside";
    protected static final String ITEM = "item";
    protected static final String ITEMFORBATTLE = "itemforbattle";
    protected static final String ITEMS = "items";
    protected static final String ITEMS_SET = "items_set";
    protected static final String LEARNT = "learnt";
    protected static final String LEARNT_MOVES = "learnt_moves";
    protected static final String LEG_PK = "leg_pk";
    protected static final String LEVEL = "level";
    protected static final String LEVEL_MAP = "level_map";
    protected static final String LEVEL_MAP_INDEX = "level_map_index";
    protected static final String MOVE = "move";
    protected static final String MOVES = "moves";
    protected static final String MOVES_SET = "moves_set";
    protected static final String OTHER_ITEM = "other_item";
    protected static final String OTHER_WEATHER = "other_weather";
    protected static final String PK = "pk";
    protected static final String PLACE_MAP_INDEX = "place_map_index";
    protected static final String POKEMON = "pokemon";
    protected static final String POKEMON_SET = "pokemon_set";
    protected static final String PROPONE_LINK = "propone_link";
    protected static final String COORDS = "coords";
    protected static final String NO_FIGHT = "no_fight";
    protected static final String POKEMON_SET_SIMU = "pokemon_set_simu";
    protected static final String POKEMON_ADDED = "pokemon_added";
    protected static final String POKEMON_EDIT = "pokemon_edit";
    protected static final String POKEMON_INDEX_EDIT = "pokemon_index_edit";
    protected static final String POKEMON_NAME_EDIT = "pokemon_name_edit";
    protected static final String POKEMON_LEVEL_EDIT = "pokemon_level_edit";
    protected static final String POKEMON_EXPERIENCE = "pokemon_experience";
    protected static final String POKEMON_HAPPINESS = "pokemon_happiness";
    protected static final String POKEMON_HP = "pokemon_hp";
    protected static final String POKEMON_EV_VAR = "pokemon_ev_";
    protected static final String HEAL_EDIT_PK = "heal_edit_pk";
    protected static final String CATCHING_BALL = "catching_ball";
    protected static final String ITEM_EDIT = "item_edit";
    protected static final String POKEMON_MOVES_EDIT = "pokemon_moves_edit";
    protected static final String POKEMON_ABILITY_EDIT = "pokemon_ability_edit";
    protected static final String POKEMON_GENDER_EDIT = "pokemon_gender_edit";
    protected static final String POKEMON_FOE = "pokemon_foe";
    protected static final String ADDING_TRAINER_PK = "adding_pk";
    protected static final String POKEMON_ITEM_EDIT = "pokemon_item_edit";
    protected static final String ITEMS_SET_EDIT = "items_set_edit";
    protected static final String VALIDATE_TRAINER_PK = "validate_trainer_pk";
    protected static final String ADD_POKEMON_PLAYER = "add_pokemon_player";
    protected static final String EDIT_POKEMON_PLAYER = "edit_pokemon_player";
    protected static final String IS_POKEMON_PLAYER_MOVES = "is_pokemon_player_moves";
    protected static final String PK_NAME = "pk_name";
    /**exception naming*/
    protected static final String PROPONE_LINK_VAR = "propone_link_";
    protected static final String PROPONE_TILE = "propone_tile";
    protected static final String REPEL = "repel";
    protected static final String SEE_AREA = "see_area";
    protected static final String SELLER = "seller";
    protected static final String SELLINGITEM = "sellingitem";
    protected static final String STATUS = "status";
    protected static final String STATUS_SET = "status_set";
    protected static final String TRAINER = "trainer";
    protected static final String TRAINER_MULTI_FIGHT = "trainer_multi_fight";
    protected static final String TRAINER_ONE_FIGHT = "trainer_one_fight";
    protected static final String SPACE = " ";
    protected static final String SEP_DASH = " - ";
    protected static final String CENT = "100";
    protected static final char UNDERSCORE = '_';
    protected static final String LEFT_BRACE = "{";
    protected static final String RIGHT_BRACE = "}";
    protected static final String QUOTED_LEFT_BRACE = "'{";
    protected static final String QUOTED_RIGHT_BRACE = "}'";
    protected static final String QUOTE = "'";
    protected static final String ESCAPED_QUOTE = "''";
    protected static final char LEFT_PAR = '(';
    protected static final char RIGHT_PAR = ')';
    protected static final char PIPE_CHAR = '|';

    protected static String escapedStringQuote(String _string) {
        StringMap<String> map_ = new StringMap<String>();
        map_.put(QUOTE, ESCAPED_QUOTE);
        map_.put(LEFT_BRACE, StringUtil.concat(QUOTED_LEFT_BRACE,QUOTE));
        map_.put(RIGHT_BRACE, StringUtil.concat(QUOTE,QUOTED_RIGHT_BRACE));
        return StringUtil.formatBasic(_string, map_, false);
    }
    protected static StringList getFormattedReasons(DataBase _data, StringList _reasons, String _language) {
//      Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
//        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList reasons_;
        reasons_ = new StringList();
        for (String f: _reasons) {
            String formula_ = _data.getFormula(f, _language);
//            formula_ = StringList.replace(formula_, locHtml_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = formula_.replace(EAMP, E_AMP);
//            formula_ = formula_.replace(EGT, E_GT);
//            formula_ = formula_.replace(ELT, E_LT);
            reasons_.add(formula_);
        }
        return reasons_;
    }

    protected static NatStringTreeMap<String> getMapVarsFail(DataBase _data, String _fail, String _language) {
        NatStringTreeMap<String> mapVars_ = _data.getDescriptions(_fail, _language);
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        return mapVarsFail_;
    }

    protected static StringList getReasons(String _booleanString) {
        StringList reasons_;
        reasons_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        int iPostSep_ = IndexConstants.FIRST_INDEX;
        int nbLeftPar_ = 0;
        int nbRightPar_ = 0;
        String fail_ = _booleanString;
        while (i_ < fail_.length()) {
            if (fail_.charAt(i_) == LEFT_PAR) {
                nbLeftPar_++;
            }
            if (fail_.charAt(i_) == RIGHT_PAR) {
                nbRightPar_++;
            }
            if (fail_.charAt(i_) == PIPE_CHAR) {
                if (nbLeftPar_ == nbRightPar_) {
                    reasons_.add(fail_.substring(iPostSep_, i_));
                    iPostSep_ = i_ + 1;
                }
            }
            i_++;
        }
        if (iPostSep_ < fail_.length()) {
            reasons_.add(fail_.substring(iPostSep_));
        }
        return reasons_;
    }

}