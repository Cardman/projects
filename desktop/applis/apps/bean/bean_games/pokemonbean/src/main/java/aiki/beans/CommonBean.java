package aiki.beans;

import aiki.beans.effects.EffectWhileSendingBean;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.game.ImgPkPlayer;
import aiki.beans.map.elements.TranslatedPkElements;
import aiki.beans.moves.effects.*;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.fight.ActivityOfMove;
import aiki.map.levels.Level;
import aiki.map.levels.enums.*;
import aiki.map.places.Place;
import aiki.map.pokemon.enums.*;
import aiki.map.util.MiniMapCoordsTileInts;
import aiki.util.Coords;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.scripts.pages.aiki.*;
import code.sml.util.TranslationsFile;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.*;
import code.util.ints.*;

public abstract class CommonBean {
    public static final int FALSE_VALUE = 0;
    public static final int TRUE_VALUE = 1;
//    public static final String GET_IMAGE = "getImage";
//    public static final String CLICK_NAME = "clickName";
//    public static final String GET_NAME = "getName";
//    public static final String GET_GENDER = "getGender";
//    public static final String CLICK_ABILITY = "clickAbility";
//    public static final String GET_ABILITY = "getAbility";
//    public static final String CLICK_ITEM = "clickItem";
//    public static final String GET_ITEM = "getItem";
//    public static final String CLICK_MOVE = "clickMove";
//    public static final String GET_MOVE = "getMove";
    //public static final String ABILITY_ABILITIES="ability/abilities";
    //public static final String ABILITY_ABILITY="ability/ability";
    //public static final String COMBO_COMBO="combo/combo";
    //public static final String DIFFICULTY="difficulty";
    //public static final String ENDROUND_ENDROUND="endround/endround";
    //public static final String ENDROUND_EVENT="endround/event";
    //public static final String ENDROUND_FOE="endround/foe";
    //public static final String ENDROUND_INDIVIDUAL="endround/individual";
    //public static final String ENDROUND_MULTIRELATION="endround/multirelation";
    //public static final String ENDROUND_POSITIONRELATION="endround/positionrelation";
    //public static final String ENDROUND_POSITIONTARGET="endround/positiontarget";
    //public static final String ENDROUND_SINGLERELATION="endround/singlerelation";
    //public static final String ENDROUND_STATUS="endround/status";
    //public static final String ENDROUND_STATUSRELATION="endround/statusrelation";
    //public static final String ENDROUND_TEAM="endround/team";
    //public static final String FIGHT="fight";
    //public static final String FIGHTER="fighter";
    //    public static final String GENERAL_GENERAL="general/general";
    //public static final String INDEX="index";
    //public static final String ITEMS_BALL="items/ball";
    //public static final String ITEMS_BERRY="items/berry";
    //public static final String ITEMS_BOOST="items/boost";
    //public static final String ITEMS_EVOITEM="items/evoitem";
    //public static final String ITEMS_EVOSTONE="items/evostone";
    //public static final String ITEMS_FOSSIL="items/fossil";
    //public static final String ITEMS_HEALINGHP="items/healinghp";
    //public static final String ITEMS_HEALINGHPSTATUS="items/healinghpstatus";
    //public static final String ITEMS_HEALINGITEM="items/healingitem";
    //public static final String ITEMS_HEALINGPP="items/healingpp";
    //public static final String ITEMS_HEALINGSTATUS="items/healingstatus";
    //public static final String ITEMS_ITEM="items/item";
    //public static final String ITEMS_ITEMFORBATTLE="items/itemforbattle";
    //public static final String ITEMS_ITEMS="items/items";
    //public static final String ITEMS_REPEL="items/repel";
    //public static final String LANGS_LANGS="langs/langs";
    //public static final String MAP_LEVEL="map/level";
    //public static final String MAP_POKEMON_KEY="map/pokemon_key";
    //public static final String MOVES_DATA="moves/data";
    //public static final String MOVES_EFFECTS_EFF="moves/effects/eff";
    //public static final String MOVES_EFFECTS_EFFACCURACY="moves/effects/effaccuracy";
    //public static final String MOVES_EFFECTS_EFFALLY="moves/effects/effally";
    //public static final String MOVES_EFFECTS_EFFBATONPASS="moves/effects/effbatonpass";
    //public static final String MOVES_EFFECTS_EFFCLONE="moves/effects/effclone";
    //public static final String MOVES_EFFECTS_EFFCOMMONSTATISTICS="moves/effects/effcommonstatistics";
    //public static final String MOVES_EFFECTS_EFFCOPYFIGHTER="moves/effects/effcopyfighter";
    //public static final String MOVES_EFFECTS_EFFCOPYMOVE="moves/effects/effcopymove";
    //public static final String MOVES_EFFECTS_EFFCOUNTERATTACK="moves/effects/effcounterattack";
    //public static final String MOVES_EFFECTS_EFFDAMAGE="moves/effects/effdamage";
    //public static final String MOVES_EFFECTS_EFFDAMAGERATE="moves/effects/effdamagerate";
    //public static final String MOVES_EFFECTS_EFFENDROUND="moves/effects/effendround";
    //public static final String MOVES_EFFECTS_EFFFULLHPRATE="moves/effects/efffullhprate";
    //public static final String MOVES_EFFECTS_EFFGLOBAL="moves/effects/effglobal";
    //public static final String MOVES_EFFECTS_EFFINVOKE="moves/effects/effinvoke";
    //public static final String MOVES_EFFECTS_EFFMULTSUFFEREDMOVEPOWER="moves/effects/effmultsufferedmovepower";
    //public static final String MOVES_EFFECTS_EFFMULTUSEDMOVEPOWER="moves/effects/effmultusedmovepower";
    //public static final String MOVES_EFFECTS_EFFORDER="moves/effects/efforder";
    //public static final String MOVES_EFFECTS_EFFPROTECTFROMTYPES="moves/effects/effprotectfromtypes";
    //public static final String MOVES_EFFECTS_EFFPROTECTION="moves/effects/effprotection";
    //public static final String MOVES_EFFECTS_EFFREMAINEDHPRATE="moves/effects/effremainedhprate";
    //public static final String MOVES_EFFECTS_EFFRESTRICTION="moves/effects/effrestriction";
    //public static final String MOVES_EFFECTS_EFFSTATIS="moves/effects/effstatis";
    //public static final String MOVES_EFFECTS_EFFSTATUS="moves/effects/effstatus";
    //public static final String MOVES_EFFECTS_EFFSWITCHABILITIES="moves/effects/effswitchabilities";
    //public static final String MOVES_EFFECTS_EFFSWITCHITEMS="moves/effects/effswitchitems";
    //public static final String MOVES_EFFECTS_EFFSWITCHMOVESTYPES="moves/effects/effswitchmovestypes";
    //public static final String MOVES_EFFECTS_EFFSWITCHPOINTVIEW="moves/effects/effswitchpointview";
    //public static final String MOVES_EFFECTS_EFFSWITCHPOSITION="moves/effects/effswitchposition";
    //public static final String MOVES_EFFECTS_EFFSWITCHTYPES="moves/effects/effswitchtypes";
    //public static final String MOVES_EFFECTS_EFFTEAM="moves/effects/effteam";
    //public static final String MOVES_EFFECTS_EFFTEAMWHILESENDINGFOE="moves/effects/effteamwhilesendingfoe";
    //public static final String MOVES_EFFECTS_EFFUNPROTECTFROMTYPES="moves/effects/effunprotectfromtypes";
    //public static final String MOVES_EFFECTS_EFFVARPP="moves/effects/effvarpp";
    //public static final String MOVES_EFFECTS_EFFWINMONEY="moves/effects/effwinmoney";
    //public static final String MOVES_MOVES="moves/moves";
    //public static final String MSG_ABILITIES="msg_abilities";
    //public static final String MSG_ABILITY="msg_ability";
    //public static final String MSG_BALL="msg_ball";
    //public static final String MSG_BERRY="msg_berry";
    //public static final String MSG_BOOST="msg_boost";
    //public static final String MSG_COMBO="msg_combo";
    //public static final String MSG_DIFFICULTY="msg_difficulty";
    //public static final String MSG_EFF="msg_eff";
    //public static final String MSG_EFFACCURACY="msg_effaccuracy";
    //public static final String MSG_EFFALLY="msg_effally";
    //public static final String MSG_EFFBATONPASS="msg_effbatonpass";
    //public static final String MSG_EFFCLONE="msg_effclone";
    //public static final String MSG_EFFCOMMONSTATISTICS="msg_effcommonstatistics";
    //public static final String MSG_EFFCOPYFIGHTER="msg_effcopyfighter";
    //public static final String MSG_EFFCOPYMOVE="msg_effcopymove";
    //public static final String MSG_EFFCOUNTERATTACK="msg_effcounterattack";
    //public static final String MSG_EFFDAMAGE="msg_effdamage";
    //public static final String MSG_EFFDAMAGERATE="msg_effdamagerate";
    //public static final String MSG_EFFENDROUND="msg_effendround";
    //public static final String MSG_EFFFULLHPRATE="msg_efffullhprate";
    //public static final String MSG_EFFGLOBAL="msg_effglobal";
    //public static final String MSG_EFFINVOKE="msg_effinvoke";
    //public static final String MSG_EFFMULTSUFFEREDMOVEPOWER="msg_effmultsufferedmovepower";
    //public static final String MSG_EFFMULTUSEDMOVEPOWER="msg_effmultusedmovepower";
    //public static final String MSG_EFFORDER="msg_efforder";
    //public static final String MSG_EFFPROTECTFROMTYPES="msg_effprotectfromtypes";
    //public static final String MSG_EFFPROTECTION="msg_effprotection";
    //public static final String MSG_EFFREMAINEDHPRATE="msg_effremainedhprate";
    //public static final String MSG_EFFRESTRICTION="msg_effrestriction";
    //public static final String MSG_EFFSTATIS="msg_effstatis";
    //public static final String MSG_EFFSTATUS="msg_effstatus";
    //public static final String MSG_EFFSWITCHABILITIES="msg_effswitchabilities";
    //public static final String MSG_EFFSWITCHITEMS="msg_effswitchitems";
    //public static final String MSG_EFFSWITCHMOVETYPES="msg_effswitchmovetypes";
    //public static final String MSG_EFFSWITCHPOINTVIEW="msg_effswitchpointview";
    //public static final String MSG_EFFSWITCHPOSITION="msg_effswitchposition";
    //public static final String MSG_EFFSWITCHTYPES="msg_effswitchtypes";
    //public static final String MSG_EFFTEAM="msg_effteam";
    //public static final String MSG_EFFTEAMWHILESENDINGFOE="msg_effteamwhilesendingfoe";
    //public static final String MSG_EFFUNPROTECTFROMTYPES="msg_effunprotectfromtypes";
    //public static final String MSG_EFFVARPP="msg_effvarpp";
    //public static final String MSG_EFFWINMONEY="msg_effwinmoney";
    //public static final String MSG_EFF_SENDING="msg_eff_sending";
    //public static final String MSG_ENDROUND="msg_endround";
    //public static final String MSG_EVENT="msg_event";
    //public static final String MSG_EVOHAPPINESS="msg_evohappiness";
    //public static final String MSG_EVOITEM="msg_evoitem";
    //public static final String MSG_EVOLEVEL="msg_evolevel";
    //public static final String MSG_EVOLEVELGENDER="msg_evolevelgender";
    //public static final String MSG_EVOMOVE="msg_evomove";
    //public static final String MSG_EVOSTONE="msg_evostone";
    //public static final String MSG_EVOSTONEGENDER="msg_evostonegender";
    //public static final String MSG_EVOTEAM="msg_evoteam";
    //public static final String MSG_EVOTYPE="msg_evotype";
    //public static final String MSG_EVO_ITEM="msg_evo_item";
    //public static final String MSG_EVO_STONE="msg_evo_stone";
    //    public static final String MSG_FOE="msg_foe";
    //public static final String MSG_FOSSIL="msg_fossil";
    //public static final String MSG_GENERALHELP="msg_generalhelp";
    //public static final String MSG_HEALINGHP="msg_healinghp";
    //public static final String MSG_HEALINGHPSTATUS="msg_healinghpstatus";
    //public static final String MSG_HEALINGITEM="msg_healingitem";
    //public static final String MSG_HEALINGPP="msg_healingpp";
    //public static final String MSG_HEALINGSTATUS="msg_healingstatus";
    //public static final String MSG_HELPROUND="msg_helpround";
    //public static final String MSG_INDEX="msg_index";
    //public static final String MSG_INDIVIDUAL="msg_individual";
    //public static final String MSG_ITEM="msg_item";
    //public static final String MSG_ITEMFORBATTLE="msg_itemforbattle";
    //public static final String MSG_ITEMS="msg_items";
    //public static final String MSG_LANG="msg_lang";
    //public static final String MSG_LEVELMAP="msg_levelmap";
    //public static final String MSG_LEVELSIMU="msg_levelsimu";
    //public static final String MSG_MOVEDATA="msg_movedata";
    //public static final String MSG_MOVES="msg_moves";
    //public static final String MSG_MULTIRELATION="msg_multirelation";
    //public static final String MSG_PKDATA="msg_pkdata";
    //public static final String MSG_POKEDEX="msg_pokedex";
    //public static final String MSG_POKEMON_NPC="msg_pokemon_npc";
    //public static final String MSG_POSITIONRELATION="msg_positionrelation";
    //public static final String MSG_POSITIONTARGET="msg_positiontarget";
    //    public static final String MSG_REPEL="msg_repel";
    //public static final String MSG_SIMULATION="msg_simulation";
    //public static final String MSG_SINGLERELATION="msg_singlerelation";
    //public static final String MSG_SOLUTION="msg_solution";
    //public static final String MSG_STATUS="msg_status";
    //public static final String MSG_STATUSEND="msg_statusend";
    //public static final String MSG_STATUSRELATION="msg_statusrelation";
    //public static final String MSG_STATUSSET="msg_statusset";
    //public static final String MSG_TEAM="msg_team";
    //public static final String POKEMON="pokemon";
    //public static final String POKEMON_DATA="pokemon/data";
    //public static final String POKEMON_EVOLUTIONS_EVOHAPPINESS="pokemon/evolutions/evohappiness";
    //public static final String POKEMON_EVOLUTIONS_EVOITEM="pokemon/evolutions/evoitem";
    //public static final String POKEMON_EVOLUTIONS_EVOLEVEL="pokemon/evolutions/evolevel";
    //public static final String POKEMON_EVOLUTIONS_EVOLEVELGENDER="pokemon/evolutions/evolevelgender";
    //public static final String POKEMON_EVOLUTIONS_EVOMOVE="pokemon/evolutions/evomove";
    //public static final String POKEMON_EVOLUTIONS_EVOSTONE="pokemon/evolutions/evostone";
    //public static final String POKEMON_EVOLUTIONS_EVOSTONEGENDER="pokemon/evolutions/evostonegender";
    //public static final String POKEMON_EVOLUTIONS_EVOTEAM="pokemon/evolutions/evoteam";
    //public static final String POKEMON_EVOLUTIONS_EVOTYPE="pokemon/evolutions/evotype";
    //public static final String POKEMON_POKEDEX="pokemon/pokedex";
    //public static final String ROUND_ROUND="round/round";
    //public static final String SENDING_EFFSENDING="sending/effsending";
    //public static final String SIMULATION_LEVELSIMU="simulation/levelsimu";
    //public static final String SIMULATION_SIMULATION="simulation/simulation";
    //public static final String SOLUTION_SOLUTION="solution/solution";
    //public static final String STATUS_STATUS="status/status";
    //public static final String STATUS_STATUSSET="status/statusset";
    //public static final String TEAM="team";
    //public static final String REN_ADD_WEB_CSS_ABILITIES_CSS="web/css/abilities.css";
    //public static final String REN_ADD_WEB_CSS_ATTAQUES_CSS="web/css/attaques.css";
    //public static final String REN_ADD_WEB_CSS_CAPACITES_CSS="web/css/capacites.css";
    //public static final String REN_ADD_WEB_CSS_ITEMS_CSS="web/css/items.css";
    //public static final String REN_ADD_WEB_CSS_MOVES_CSS="web/css/moves.css";
    //public static final String REN_ADD_WEB_CSS_OBJETS_CSS="web/css/objets.css";
    //public static final String REN_ADD_WEB_CSS_POKEDEX_CSS="web/css/pokedex.css";
    //public static final String REN_ADD_WEB_CSS_SIMULATION_CSS="web/css/simulation.css";
    //    public static final String REN_ADD_WEB_GAME_CSS_DIFFICULTY_CSS="web_game/css/difficulty.css";
    //public static final String REN_ADD_WEB_GAME_MESSAGES="web_game/messages";
    public static final String REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML="0/3";
    public static final String REN_ADD_WEB_HTML_ABILITY_DATA_HTML="0/3_";
    public static final String REN_ADD_WEB_HTML_COMBO_COMBOS_HTML="0/5";
    //public static final String REN_ADD_WEB_HTML_COMBO_COMBO_HTML="web/html/combo/combo.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_EFF_HTML="web/html/endround/eff.html";
    public static final String REN_ADD_WEB_HTML_ENDROUND_ENDROUND_HTML="0/6";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_FOE_HTML="web/html/endround/foe.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_GLOBAL_HTML="web/html/endround/global.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_INDIVIDUAL_HTML="web/html/endround/individual.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_MULTIRELATION_HTML="web/html/endround/multirelation.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_POSITIONRELATION_HTML="web/html/endround/positionrelation.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_POSITIONTARGET_HTML="web/html/endround/positiontarget.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_SINGLERELATION_HTML="web/html/endround/singlerelation.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_STATUSRELATION_HTML="web/html/endround/statusrelation.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_STATUS_HTML="web/html/endround/status.html";
    //public static final String REN_ADD_WEB_HTML_ENDROUND_TEAM_HTML="web/html/endround/team.html";
    public static final String REN_ADD_WEB_HTML_GENERAL_GENERAL_HTML="0/8";
    public static final String REN_ADD_WEB_HTML_ITEMS_BALL_HTML="0/1_0";
    public static final String REN_ADD_WEB_HTML_ITEMS_BERRY_HTML="0/1_1";
    public static final String REN_ADD_WEB_HTML_ITEMS_BOOST_HTML="0/1_2";
    public static final String REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML="0/1_3";
    public static final String REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML="0/1_4";
    public static final String REN_ADD_WEB_HTML_ITEMS_FOSSIL_HTML="0/1_5";
    public static final String REN_ADD_WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML="0/1_6";
    public static final String REN_ADD_WEB_HTML_ITEMS_HEALINGHP_HTML="0/1_7";
    public static final String REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML="0/1_8";
    public static final String REN_ADD_WEB_HTML_ITEMS_HEALINGPP_HTML="0/1_9";
    public static final String REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML="0/1_10";
    public static final String REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML="0/1_11";
    public static final String REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML="0/1";
    //public static final String REN_ADD_WEB_HTML_ITEMS_ITEM_HTML="web/html/items/item.html";
    public static final String REN_ADD_WEB_HTML_ITEMS_REPEL_HTML="0/1_12";
    public static final String REN_ADD_WEB_HTML_ITEMS_SELLINGITEM_HTML="0/1_13";
    public static final String REN_ADD_WEB_HTML_LANGS_LANGS_HTML="0/9";
    //public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_ALLY_HTML="web/html/map/elements/ally.html";
    public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML="0/7/6";
    public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_DEALER_HTML="0/7/0";
    public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML="0/7/5";
    public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML="0/7/2";
    //public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_POKEMON_TEAM_HTML="web/html/map/elements/pokemon_team.html";
    public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_SELLER_HTML="0/7/1";
    public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML="0/7/4";
    public static final String REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML="0/7/3";
    public static final String REN_ADD_WEB_HTML_MAP_LEVEL_HTML="0/7/";
    public static final String REN_ADD_WEB_HTML_MAP_MAP_HTML="0/7";
    public static final String REN_ADD_WEB_HTML_MOVES_DATA_HTML="0/2";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFACCURACY_HTML="web/html/moves/effects/effaccuracy.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFALLY_HTML="web/html/moves/effects/effally.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFBATONPASS_HTML="web/html/moves/effects/effbatonpass.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCLONE_HTML="web/html/moves/effects/effclone.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOMMONSTATISTICS_HTML="web/html/moves/effects/effcommonstatistics.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYFIGHTER_HTML="web/html/moves/effects/effcopyfighter.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYMOVE_HTML="web/html/moves/effects/effcopymove.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOUNTERATTACK_HTML="web/html/moves/effects/effcounterattack.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFDAMAGERATE_HTML="web/html/moves/effects/effdamagerate.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFDAMAGE_HTML="web/html/moves/effects/effdamage.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFENDROUND_HTML="web/html/moves/effects/effendround.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFFULLHPRATE_HTML="web/html/moves/effects/efffullhprate.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFGLOBAL_HTML="web/html/moves/effects/effglobal.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFINVOKE_HTML="web/html/moves/effects/effinvoke.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTSUFFEREDMOVEPOWER_HTML="web/html/moves/effects/effmultsufferedmovepower.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTUSEDMOVEPOWER_HTML="web/html/moves/effects/effmultusedmovepower.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFORDER_HTML="web/html/moves/effects/efforder.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFPROTECTFROMTYPES_HTML="web/html/moves/effects/effprotectfromtypes.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFPROTECTION_HTML="web/html/moves/effects/effprotection.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFREMAINEDHPRATE_HTML="web/html/moves/effects/effremainedhprate.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFRESTRICTION_HTML="web/html/moves/effects/effrestriction.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSTATIS_HTML="web/html/moves/effects/effstatis.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSTATUS_HTML="web/html/moves/effects/effstatus.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHABILITIES_HTML="web/html/moves/effects/effswitchabilities.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHITEMS_HTML="web/html/moves/effects/effswitchitems.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHMOVETYPES_HTML="web/html/moves/effects/effswitchmovetypes.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOINTVIEW_HTML="web/html/moves/effects/effswitchpointview.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOSITION_HTML="web/html/moves/effects/effswitchposition.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHTYPES_HTML="web/html/moves/effects/effswitchtypes.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAMWHILESENDINGFOE_HTML="web/html/moves/effects/effteamwhilesendingfoe.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAM_HTML="web/html/moves/effects/effteam.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFUNPROTECTFROMTYPES_HTML="web/html/moves/effects/effunprotectfromtypes.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFVARPP_HTML="web/html/moves/effects/effvarpp.html";
    //public static final String REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFWINMONEY_HTML="web/html/moves/effects/effwinmoney.html";
    //private static final String REN_ADD_WEB_HTML_MOVES_MOVELINE_HTML="web/html/moves/moveline.html";
    public static final String REN_ADD_WEB_HTML_MOVES_MOVES_HTML="0/2_";
    public static final String REN_ADD_WEB_HTML_POKEMON_DATA_HTML="0/0_";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOHAPPY_HTML="web/html/pokemon/evolutions/evohappy.html";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOITEM_HTML="web/html/pokemon/evolutions/evoitem.html";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVELGENDER_HTML="web/html/pokemon/evolutions/evolevelgender.html";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVEL_HTML="web/html/pokemon/evolutions/evolevel.html";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOMOVE_HTML="web/html/pokemon/evolutions/evomove.html";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONEGENDER_HTML="web/html/pokemon/evolutions/evostonegender.html";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONE_HTML="web/html/pokemon/evolutions/evostone.html";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTEAM_HTML="web/html/pokemon/evolutions/evoteam.html";
    //public static final String REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTYPE_HTML="web/html/pokemon/evolutions/evotype.html";
    public static final String REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML="0/0";
    public static final String REN_ADD_WEB_HTML_ROUND_HELPROUND_HTML="0/10";
    //public static final String REN_ADD_WEB_HTML_SENDING_EFFSENDING_HTML="web/html/sending/effsending.html";
    public static final String REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML="1/2";
    public static final String REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML="1/4";
    public static final String REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML="1/1";
    public static final String REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML="1/3";
    public static final String REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML="1/1/0";
    public static final String REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML="1/1/1";
    public static final String REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML="1/1/2";
    public static final String REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML="1/0";
    public static final String REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML="1";
    public static final String REN_ADD_WEB_HTML_SOLUTION_SOLUTION_HTML="0/11";
    public static final String REN_ADD_WEB_HTML_INDEX_HTML="0";
    public static final String REN_ADD_WEB_HTML_STATUS_DATA_HTML="0/4_";
    public static final String REN_ADD_WEB_HTML_STATUS_STATUS_HTML="0/4";
    //public static final String REN_ADD_WEB_MESSAGES="web/messages";
    //public static final String REN_ADD_WEB_PK_HTML_POKEMON_HTML="web_pk/html/pokemon.html";
    //public static final String REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS="web_prog/css/difficulty.css";
    //public static final String REN_ADD_WEB_PROG_HTML_GAMEPROG_HTML="web_prog/html/gameprog.html";
    //public static final String REN_ADD_WEB_PROG_HTML_GAMEPROGALL_HTML="web_prog/html/gameprogall.html";
    //public static final String REN_ADD_WEB_PROG_HTML_GAMEPROGNOTATALL_HTML="web_prog/html/gameprognotatall.html";
    //public static final String REN_ADD_WEB_PROG_HTML_GAMEPROGPART_HTML="web_prog/html/gameprogpart.html";
    public static final String WEB_FIGHT_HTML_FIGHTER_HTML="2__";
    public static final String WEB_FIGHT_HTML_TEAM_HTML="2_";
    public static final String WEB_FIGHT_HTML_FIGHT_HTML="2";
    public static final String WEB_FIGHT_HTML_FIGHTDETAIL_HTML = "2___";
    //    protected static final String CST_ABILITIES = "abilities";
    public static final String CST_ABILITIES_SET = "0_";
    public static final String CST_ABILITY = "0";
    public static final String CST_ALLY = "9";
    public static final String CST_AREA = "7";
//    protected static final String CST_BALL = "ball";
//    protected static final String CST_BERRY = "berry";
//    protected static final String CST_BOOST = "boost";
    public static final String CST_COMBO = "5";
    public static final String CST_NAMES = "10";
//    protected static final String CST_SIMULATION = "simulation";
    public static final String SIMU_CST_SIMULATION_STATE = "0";
//    protected static final String CST_CURRENT_TILE = "current_tile";
//    protected static final String CST_DEALER = "dealer";
//    protected static final String CST_DUAL = "dual";
//    protected static final String CST_EVO_ITEM = "evo_item";
//    protected static final String CST_EVO_STONE = "evo_stone";
//    protected static final String CST_EVOLVINGITEM = "evolvingitem";
//    protected static final String CST_EVOLVINGSTONE = "evolvingstone";
//    protected static final String CST_FOSSIL = "fossil";
//    protected static final String CST_FROM_LIST = "from_list";
//    protected static final String CST_HEALINGHP = "healinghp";
//    protected static final String CST_HEALINGHPSTATUS = "healinghpstatus";
//    protected static final String CST_HEALINGITEM = "healingitem";
//    protected static final String CST_HEALINGPP = "healingpp";
//    protected static final String CST_HEALINGSTATUS = "healingstatus";
//    protected static final String CST_INSIDE = "inside";
    public static final String CST_ITEM = "1";
//    protected static final String CST_ITEMFORBATTLE = "itemforbattle";
//    protected static final String CST_ITEMS = "items";
    public static final String CST_ITEMS_SET = "1_";
    public static final String CST_LEARNT = "2__";
    public static final String CST_LEARNT_MOVES = "2___";
    public static final String CST_LEG_PK = "6";
//    protected static final String CST_LEVEL = "level";
//    protected static final String CST_LEVEL_MAP = "level_map";
//    protected static final String CST_LEVEL_MAP_INDEX = "level_map_index";
    public static final String CST_MOVE = "2";
//    protected static final String CST_MOVES = "moves";
    public static final String CST_MOVES_SET = "2_";
    public static final String SIMU_CST_MOVES_EDIT_SET = "21";
//    protected static final String CST_OTHER_ITEM = "other_item";
//    protected static final String CST_OTHER_WEATHER = "other_weather";
    public static final String CST_PK = "3";
//    protected static final String CST_PLACE_MAP_INDEX = "place_map_index";
//    protected static final String CST_POKEMON = "pokemon";
    public static final String CST_POKEMON_SET = "3_";
//    protected static final String CST_PROPONE_LINK = "propone_link";
    public static final String CST_COORDS = "11";
    public static final String SIMU_CST_COORDS_TR_VIRTUAL = "1";
    public static final String SIMU_CST_COORDS_TR = "2";
    public static final String SIMU_CST_NO_FIGHT = "3";
    public static final String SIMU_CST_POKEMON_SET_SIMU = "3__";
    public static final String SIMU_CST_POKEMON_ADDED = "13";
//    protected static final String SIMU_CST_POKEMON_EDIT = "pokemon_edit";
    public static final String SIMU_CST_POKEMON_INDEX_EDIT = "22";
    public static final String SIMU_CST_POKEMON_NAME_EDIT = "8";
    public static final String SIMU_CST_POKEMON_LEVEL_EDIT = "11";
    public static final String SIMU_CST_POKEMON_EXPERIENCE = "16";
    public static final String SIMU_CST_POKEMON_HAPPINESS = "17";
    public static final String SIMU_CST_POKEMON_HP = "19";
    public static final String SIMU_CST_POKEMON_EV_VAR = "18";
    public static final String SIMU_CST_HEAL_EDIT_PK = "20";
    public static final String SIMU_CST_CATCHING_BALL = "15";
    public static final String SIMU_CST_ITEM_EDIT = "10";
    public static final String SIMU_CST_POKEMON_MOVES_EDIT = "6";
    public static final String SIMU_CST_POKEMON_ABILITY_EDIT = "7";
    public static final String SIMU_CST_POKEMON_GENDER_EDIT = "9";
    public static final String SIMU_CST_POKEMON_FOE = "5";
    public static final String SIMU_CST_ADDING_TRAINER_PK = "4";
//    protected static final String CST_POKEMON_ITEM_EDIT = "pokemon_item_edit";
    public static final String SIMU_CST_ITEMS_SET_EDIT = "1__";
//    protected static final String CST_VALIDATE_TRAINER_PK = "validate_trainer_pk";
//    protected static final String CST_ADD_POKEMON_PLAYER = "add_pokemon_player";
//    protected static final String SIMU_CST_EDIT_POKEMON_PLAYER = "edit_pokemon_player";
    public static final String SIMU_CST_IS_POKEMON_PLAYER_MOVES = "12";
    public static final String SIMU_CST_PK_NAME = "14";
    /**exception naming*/
//    protected static final String CST_PROPONE_LINK_VAR = "propone_link_";
//    protected static final String CST_PROPONE_TILE = "propone_tile";
//    protected static final String CST_REPEL = "repel";
//    protected static final String CST_SEE_AREA = "see_area";
//    protected static final String CST_SELLER = "seller";
//    protected static final String CST_SELLINGITEM = "sellingitem";
    public static final String CST_STATUS = "4";
    public static final String CST_STATUS_SET = "4_";
//    protected static final String CST_TRAINER = "trainer";
    public static final String CST_PERSON = "8";
//    protected static final String CST_TRAINER_MULTI_FIGHT = "trainer_multi_fight";
//    protected static final String CST_TRAINER_ONE_FIGHT = "trainer_one_fight";
    protected static final String CST_SPACE = " ";
    protected static final String CST_SEP_DASH = " - ";
    protected static final String CST_CENT = "100";
//    protected static final char CST_UNDERSCORE = '_';
    protected static final String CST_LEFT_BRACE = "{";
    protected static final String CST_RIGHT_BRACE = "}";
    protected static final String CST_QUOTED_LEFT_BRACE = "'{";
    protected static final String CST_QUOTED_RIGHT_BRACE = "}'";
    protected static final String CST_QUOTE = "'";
    protected static final String CST_ESCAPED_QUOTE = "''";
    protected static final char CST_LEFT_PAR = '(';
    protected static final char CST_RIGHT_PAR = ')';
    protected static final char CST_PIPE_CHAR = '|';

//    private StringMapObject baseForms;

    private FacadeGame dataBase;
    private IntBeanBuilderHelper builder;
    private String appName = "";

    private String language = "";
//    private String baseEncode;

    public abstract void beforeDisplaying();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _language) {
        language = _language;
    }
    protected void fwd(CommonBean _b) {
        _b.setAppName(getAppName());
        _b.setDataBase(db());
//        _b.setForms(new StringMapObject());
//        _b.getForms().putAllMap(getForms());
        _b.setLanguage(getLanguage());
        _b.setBuilder(getBuilder());
    }

    public static int toInt(BoolVal _b) {
        if (_b == BoolVal.TRUE) {
            return TRUE_VALUE;
        }
        return FALSE_VALUE;
    }
    public static int toInt(boolean _b) {
        if (_b) {
            return TRUE_VALUE;
        }
        return FALSE_VALUE;
    }
    public static boolean toBool(int _i) {
        return _i == TRUE_VALUE;
    }
    public static Rate rateTrue(MonteCarloBoolean _law) {
        if (_law.isZero()) {
            return Rate.zero();
        }
        return _law.normalizedRate(BoolVal.TRUE);
    }
    public void disTranslatedPkElements(TranslatedPkElements _tr) {
        addImg(_tr.getImage());
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_NAME);
        formatMessageDir(_tr.getName());
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_GENDER);
        formatMessageDir(_tr.getGender());
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_LEVEL);
        formatMessageDir(Long.toString(_tr.getLevel()));
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_ABILITY);
        formatMessageDir(_tr.getAbility());
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_ITEM);
        formatTrKey(_tr.getItem(),MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_ITEM_NO,"");
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _tr.getMoves(),MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MOVES);
    }

    public static void feedForms(int _indexOne, int _indexTwo, StringMapObject _forms) {
        Coords c_ = new Coords();
        c_.setNumberPlace(_indexOne);
        c_.getLevel().setLevelIndex(_indexTwo);
        _forms.put(CST_COORDS, c_);
//        _forms.put(CST_PROPONE_LINK, false);
//        _forms.put(CST_PROPONE_TILE, false);
//        _forms.put(CST_SEE_AREA, false);
//        for (Direction d: Direction.all()) {
//            _forms.putDir(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
//        }
    }
    protected void eff(EffectBean _sub) {
        _sub.buildSubEffPre();
        target(_sub.getEffect().getTargetChoice(),MessagesPkBean.EFF,MessagesDataEff.M_P_36_TARGETS_ADJ_ADV,MessagesDataEff.M_P_36_TARGETS_ADJ_MULT,MessagesDataEff.M_P_36_TARGETS_ADJ_UNIQ,MessagesDataEff.M_P_36_TARGETS_ALLIE,MessagesDataEff.M_P_36_TARGETS_ALLIES,MessagesDataEff.M_P_36_TARGETS_ANY_FOE,MessagesDataEff.M_P_36_TARGETS_AUTRE_UNIQ,MessagesDataEff.M_P_36_TARGETS_GLOBALE,MessagesDataEff.M_P_36_TARGETS_LANCEUR,MessagesDataEff.M_P_36_TARGETS_PSEUDO_GLOBALE,MessagesDataEff.M_P_36_TARGETS_TOUS_ADV,MessagesDataEff.M_P_36_TARGETS_UNIQUE_IMPORTE);
        displayStringList(_sub.getReasons(), MessagesPkBean.EFF, MessagesDataEff.M_P_36_REASONS);
        mapVarsInit(_sub.getMapVarsFail());
        displayBoolTrue(toInt(_sub.getNeedSuccessFirstEffect()), MessagesPkBean.EFF, MessagesDataEff.M_P_36_NEED_SUCESS);
        _sub.buildSubEff();
    }

    protected EffectWhileSendingBean displaySend(EffectWhileSendingWithStatistic _s) {
        EffectWhileSendingBean send_ = effSending(_s);
        formatMessage(MessagesPkBean.SENDING,MessagesDataSending.M_P_84_EFFECT);
        displayBoolTrue(toInt(send_.getDisableWeather()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER);
        displayBoolTrue(toInt(send_.getDisableWeather()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER_2);
        displayBoolTrue(toInt(send_.getDisableWeather()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER_3);
        displayBoolTrue(toInt(send_.getDisableWeather()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER_4);
        formatTrKey(send_.getEnabledWeather(),MessagesPkBean.SENDING,"",MessagesDataSending.M_P_84_WEATHER);
        displayBoolTrue(toInt(send_.getCopyingAbility()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_COPY_AB);
        displayIntDef(send_.getMultWeight(),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_WEIGHT);
        if (send_.getStatistic()) {
            effStatis(send_.getEffectStatisticCommon());
        }
        return send_;
    }

    public EffectWhileSendingBean effSending(EffectWhileSendingWithStatistic _s) {
        EffectWhileSendingBean send_ = new EffectWhileSendingBean();
        send_.setBuilder(getBuilder());
        send_.setAppName(getAppName());
        send_.setFacade(getFacade());
        send_.setLanguage(getLanguage());
        send_.setEffect(_s);
        send_.beforeDisplaying();
        return send_;
    }


    protected void formatTrKey(TranslatedKey _key,String _file,String _empty, String _fill) {
        if (!_key.getKey().isEmpty()) {
            formatMessage(_file,_fill);
            formatMessageDir(_key);
        } else {
            formatMessage(_file,_empty);
        }
//        displayNotEmpty(_file,_key.getTranslation(),_fill);
//        displayEmpty(_file,_key.getTranslation(),_empty);
//        formatMessageDir(_key);
    }

    protected void procMoveChoiceRestrictionType(MoveChoiceRestrictionType _value, MoveChoiceRestrictionType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_RESTRICTION,_key);
        }
    }
    protected void procExchangeType(ExchangeType _value, ExchangeType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHABILITIES,_key);
        }
    }
    protected void procExchangeType(MoveItemType _value, MoveItemType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHITEMS,_key);
        }
    }

    protected void procPointViewChangementType(PointViewChangementType _value, PointViewChangementType _cst, String _key, String... _values) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHPOINTVIEW,_key,_values);
        }
    }
    protected void procExchangeType(ExchangeType _value, ExchangeType _cst, CustList<TranslatedKey> _addedTypes, CustList<TranslatedKey> _constTypes, String _key) {
        if (_value == _cst) {
            displayBoolTrue(1-NumberUtil.signum(_addedTypes.size()), MessagesPkBean.EFF_SWITCHTYPES, _key);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_constTypes,1-NumberUtil.signum(_addedTypes.size()),MessagesPkBean.EFF_SWITCHTYPES,"");
        }
    }
    protected void effStatis(EffectStatisticCommon _sub) {
        if (!_sub.randomStatis()) {
            displayBoolFull(toInt(_sub.isAlwaysEnabled()), MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_ALWAYS_ENABLED,MessagesDataEffstatis.M_P_58_RATE_ENABLED,_sub.getEvtRate().toNumberString(),_sub.getEvtRatePerCent());
        }
        if (_sub.notEmptyVarBoost()) {
            if (_sub.randomStatis()) {
                new BeanDisplayMap<TranslatedKey,StatRankRate>(new BeanDisplayTranslatedKey(),new BeanDisplayStatRankRate(true,true)).displayGrid(this,_sub.getStatisVarRank(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_VAR_STATIS_RANK,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_BOOST,MessagesDataEffstatis.M_P_58_FAIL,MessagesDataEffstatis.M_P_58_RATE_EVENT);
            } else {
                new BeanDisplayMap<TranslatedKey,StatRankRate>(new BeanDisplayTranslatedKey(),new BeanDisplayStatRankRate(true,false)).displayGrid(this,_sub.getStatisVarRank(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_VAR_STATIS_RANK,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_BOOST,MessagesDataEffstatis.M_P_58_FAIL);
            }
            mapVarsInit(_sub.getMapVarsStatistics());
        }
        if (!_sub.getSwapBoostStatis().isEmpty()) {
            new BeanDisplayMap<TranslatedKey,String>(new BeanDisplayTranslatedKey(),new BeanDisplayString()).displayGrid(this,_sub.getSwapBoostStatis(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_SWAP_BOOST,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_FAIL);
            mapVarsInit(_sub.getMapVarsStatistics());
        }
        display(_sub.getCancelLowStat(), MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_CANCEL_LOW_STAT,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCancelLowStat());
        display(_sub.getCancelChgtStat(), MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_CANCEL_CHGT_STAT,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCancelChgtStat());
        display(_sub.getCopyBoost(), MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_COPY_BOOST,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCopyBoost());
    }

    public void element(String _file,String _key, String... _values) {
        element(0,_file,_key,_values);
    }

    public void element(int _i,String _file,String _key, String... _values) {
        getBuilder().setIndent(getBuilder().getIndent()+_i);
        initLine();
        getBuilder().indent();
        paintMetaLabelDisk();
        formatMessage(_file, _key, _values);
        feedParents();
        getBuilder().setIndent(getBuilder().getIndent()-_i);
    }

    public void element(Countable _c,String _file,String _key, String... _values) {
        element(0,_c,_file,_key,_values);
    }

    public void element(int _i,Countable _c,String _file,String _key, String... _values) {
        if (_c.isEmpty()) {
            return;
        }
        getBuilder().setIndent(getBuilder().getIndent()+_i);
        initLine();
        getBuilder().indent();
        paintMetaLabelDisk();
        display(_c,_file, _key, _values);
        feedParents();
        getBuilder().setIndent(getBuilder().getIndent()-_i);
    }

    public void elementOrd(String _file,String _key, String... _values) {
        elementOrd(0,_file,_key,_values);
    }

    public void elementOrd(int _i,String _file,String _key, String... _values) {
        getBuilder().setIndent(getBuilder().getIndent()+_i);
        initLine();
        getBuilder().indent();
        getBuilder().paintNb(getBuilder().getOrderedLists().last()+1);
        formatMessage(_file, _key, _values);
        getBuilder().getOrderedLists().set(getBuilder().getOrderedLists().getLastIndex(),getBuilder().getOrderedLists().last()+1);
        feedParents();
        getBuilder().setIndent(getBuilder().getIndent()-_i);
    }

    protected void mapVarsInit(AbsMap<String,String> _m) {
        new BeanDisplayList<EntryCust<String,String>>(new BeanDisplayVars()).display(this, _m.getList());
//        for (EntryCust<String,String> e: _m.entryList()) {
//            initLine();
//            paintMetaLabelDisk();
//            formatMessageDir(e.getKey()+" : "+e.getValue());
//            feedParents();
//        }
    }

    public static int width(MiniMapCoordsTileInts _miniMap) {
//        int w_ = 0;
//        int y_ = _miniMap.getKey(w_).getYcoords();
//        while (_miniMap.isValidIndex(w_) && _miniMap.getKey(w_).getYcoords() != y_+1) {
//            w_++;
//        }
        return width(new MiniSecondCoordMapper(_miniMap));
    }

    public static int width(IntSecondCoordMapper _miniMap) {
        int len_ = _miniMap.length();
        Ints values_ = new Ints();
        int first_ = 0;
        for (int i = 0; i < len_; i++) {
            if (i == 0) {
                first_ = _miniMap.sec(i);
            }
            values_.add(_miniMap.sec(i));
        }
        int w_ = 0;
        int y_ = first_;
        while (values_.isValidIndex(w_) && values_.get(w_) != y_+1) {
            w_++;
        }
        return w_;
    }
    public boolean isMultiLayer(CustList<PlaceIndex> _pls,int _index) {
        return layers(_pls,_index).size() > IndexConstants.SECOND_INDEX;
    }
    public CustList<Level> layers(CustList<PlaceIndex> _pls,int _index) {
        Place pl_ = _pls.get(_index).getPlace();
        return pl_.getLevelsList();
    }
    protected DictionaryComparator<TranslatedKey, Rate> map(StringMap<Rate> _input) {
        DictionaryComparator<TranslatedKey, Rate> multDamageTypesMoves_;
        multDamageTypesMoves_ = new DictionaryComparator<TranslatedKey, Rate>(new ComparingTranslatedKey());
        for (String m: _input.getKeys()) {
            multDamageTypesMoves_.put(buildTy(getFacade(),m), _input.getVal(m));
        }
        return multDamageTypesMoves_;
    }

    protected static CustList<TranslatedKey> listTrStringsAb(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildAb(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsIt(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildIt(_db, s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsMv(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildMv(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsPk(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildPk(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsSi(CustList<Statistic> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (Statistic s: _input) {
            res_.add(buildSi(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsSt(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildSt(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsTy(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildTy(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }
    public static TranslatedKey buildCa(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedCategories().getVal(_k)));
    }
    public static TranslatedKey buildTy(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedTypes().getVal(_k)));
    }
    public static TranslatedKey buildAb(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedAbilities().getVal(_k)),new RedirectAb(_k,""), REN_ADD_WEB_HTML_ABILITY_DATA_HTML, CST_ABILITY);
    }
    public static TranslatedKey buildIt(FacadeGame _db, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_db.getTranslatedItems().getVal(_k)),new RedirectIt(_k,"",_db.getData().getItem(_k)), "", CST_ITEM);
    }
    public static TranslatedKey buildMv(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedMoves().getVal(_k)),new RedirectMv(_k,""), REN_ADD_WEB_HTML_MOVES_DATA_HTML, CST_MOVE);
    }
    public static TranslatedKey buildPk(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedPokemon().getVal(_k)),new RedirectPk(_k,""), REN_ADD_WEB_HTML_POKEMON_DATA_HTML, CST_PK);
    }
    public static TranslatedKey buildSt(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedStatus().getVal(_k)),new RedirectSt(_k,""), REN_ADD_WEB_HTML_STATUS_DATA_HTML, CST_STATUS);
    }
    public static TranslatedKey buildEnv(FacadeGame _tr, EnvironmentType _k) {
        return new TranslatedKey(_k.getEnvName(),StringUtil.nullToEmpty(_tr.getTranslatedEnvironment().getVal(_k)));
    }
    public static TranslatedKey buildSi(FacadeGame _tr, Statistic _k) {
        return new TranslatedKey(_k.getStatName(),StringUtil.nullToEmpty(_tr.getTranslatedStatistics().getVal(_k)));
    }
    public static TranslatedKey buildGender(FacadeGame _tr, Gender _k) {
        return new TranslatedKey(_k.getGenderName(),StringUtil.nullToEmpty(_tr.getTranslatedGenders().getVal(_k)));
    }
    public DataBase getDataBase() {
        return db().getData();
    }

//    @Override
    public FacadeGame db() {
        return getFacade();
    }

//    @Override
    public void setDataBase(FacadeGame _dataBase) {
        setFacade(_dataBase);
    }

    public FacadeGame getFacade() {
        return dataBase;
    }
    public void setFacade(FacadeGame _f) {
        dataBase = _f;
    }
//
//    public String getBaseEncode() {
//        return baseEncode;
//    }

//    public void setBaseEncode(String _p) {
//        this.baseEncode = _p;
//    }

    public StringMapObject getForms() {
        return builder.getForms();
    }

//    public void setForms(StringMapObject _forms) {
//        setBaseForms(getBaseForms());
//    }

    public static boolean inRange(long _value, long _min, long _max) {
        return _value >= _min && _value <= _max;
    }
    protected static String escapedStringQuote(String _string) {
        StringMap<String> map_ = new StringMap<String>();
        map_.put(CST_QUOTE, CST_ESCAPED_QUOTE);
        map_.put(CST_LEFT_BRACE, StringUtil.concat(CST_QUOTED_LEFT_BRACE, CST_QUOTE));
        map_.put(CST_RIGHT_BRACE, StringUtil.concat(CST_QUOTE, CST_QUOTED_RIGHT_BRACE));
        return StringUtil.formatBasic(_string, map_, false);
    }
    protected static StringList getFormattedReasons(DataBase _data, String _reasons, String _language) {
        return getFormattedReasons(_data,getReasons(_reasons),_language);
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
        while (i_ < _booleanString.length()) {
            if (_booleanString.charAt(i_) == CST_LEFT_PAR) {
                nbLeftPar_++;
            }
            if (_booleanString.charAt(i_) == CST_RIGHT_PAR) {
                nbRightPar_++;
            }
            if (_booleanString.charAt(i_) == CST_PIPE_CHAR && nbLeftPar_ == nbRightPar_) {
                reasons_.add(_booleanString.substring(iPostSep_, i_));
                iPostSep_ = i_ + 1;
            }
            i_++;
        }
        if (iPostSep_ < _booleanString.length()) {
            reasons_.add(_booleanString.substring(iPostSep_));
        }
        return reasons_;
    }

    protected static String validOrEmpty(String _str) {
        if (Rate.isValid(_str)) {
            return _str;
        }
        return DataBase.EMPTY_STRING;
    }
    public String tryRedirect(TranslatedKey _tk) {
        return AbsRedirect.tryRedirect(this,_tk.getRedirect(),_tk.getKeyForm(),_tk.getDest());
    }

//    public StringMapObject getBaseForms() {
//        return baseForms;
//    }
//
//    public void setBaseForms(StringMapObject _base) {
//        this.baseForms = _base;
//    }

    protected void displayStringList(CustList<String> _list, String _file, String _key, String... _values) {
        display(_list, _file, _key,_values);
        displayStringList(_list);
    }

    protected void displayStringList(CustList<String> _list) {
        new BeanDisplayList<String>(new BeanDisplayString()).display(this,_list);
    }

    protected void displayTrainerPlaceNamesList(CustList<TrainerPlaceNames> _list, String _file, String _key) {
        display(_list, _file, _key);
        displayTrainerPlaceNamesList(_list);
    }

    protected void displayTrainerPlaceNamesList(CustList<TrainerPlaceNames> _list) {
        new BeanDisplayList<TrainerPlaceNames>(new BeanDisplayTrainerPlaceNames()).display(this,_list);
//        for (TrainerPlaceNames i: _list) {
//            builder.initLine();
//            paintMetaLabelDisk();
//            builder.formatMessageDir(i.getTrainer()+" - "+i.getPlace());
//            builder.feedParents();
//        }
    }
//    public void build(FacadeGame _facade) {
//        init(_facade);
//    }

    protected void init(FacadeGame _facade) {
        setDataBase(_facade);
        setLanguage(_facade.getLanguage());
        beforeDisplaying();
    }

    public TranslationsFile file(String _file) {
        return builder.file(getAppName(),_file);
    }

    public void addImg(int[][] _img) {
        builder.addImg(_img);
    }

    public void paintMetaLabelDisk() {
        builder.paintMetaLabelDisk();
    }

    public void formatMessageAnc(IntBeanAction _e,String _file, String _key, String... _values) {
        builder.formatMessageAnc(getAppName(),_e,_file,_key,_values);
        getBuilder().breakNext();
    }

    public void buildPkList(CustList<ImgPkPlayer> _list, String _file, String _key) {
        builder.initPage();
        display(_list, _file, _key);
        buildPkList(_list);
        builder.feedParents();
    }

    public void buildPkList(CustList<ImgPkPlayer> _list) {
        new BeanDisplayList<ImgPkPlayer>(new BeanDisplayImgPkPlayer()).display(this,_list);
    }

    public void setTitledBorder(String _title){
        builder.setTitledBorder(_title);
    }
    public void initLine() {
        builder.initLine();
    }

    public void initGrid() {
        builder.initGrid();
    }

    public void initPage() {
        builder.initPage();
    }

    public void displayHead(Countable _info, String _file, String _keyTitle, String... _cols) {
        display(_info, _file, _keyTitle);
        initGrid();
        headerCols(_info, _file, _cols);
    }

    public void displayHeadParam(Countable _info, String[] _values, String _file, String _keyTitle, String... _cols) {
        display(_info, _file, _keyTitle,_values);
        initGrid();
        headerCols(_info, _file, _cols);
    }

    public void display(Countable _ls, String _file, String _key, String... _values) {
        if (!_ls.isEmpty() && !_key.isEmpty()) {
            builder.initLine();
            builder.indent();
            builder.formatMessage(getAppName(),_file,_key,_values);
            builder.feedParents();
            builder.breakNext();
        }
    }
    public void headerCols(Countable _ls, String _file, String... _cols) {
        if (!_ls.isEmpty()) {
            builder.colCount(_cols.length);
            for (String h_ : _cols) {
                headerCol(_file, h_);
            }
        }
    }
    public void displayEmpty(String _value, String _file, String _key) {
        if (_value.isEmpty()) {
            formatMessage(_file,_key);
        }
    }
    public void displayEmpty(Countable _value, String _file, String _key, String... _values) {
        if (_value.isEmpty()) {
            formatMessage(_file,_key,_values);
        }
    }
    public void displayNotEmpty(String _value, String _file, String _key) {
        if (!_value.isEmpty()) {
            formatMessage(_file,_key,_value);
        }
    }
    public void displayBoolFull(int _value, String _file, String _one, String _two) {
        if (_value == CommonBean.TRUE_VALUE) {
            formatMessage(_file,_one);
        } else {
            formatMessage(_file,_two);
        }
    }

    public void displayBoolFull(int _value, String _file, String _one, String _two, String... _values) {
        if (_value == CommonBean.TRUE_VALUE) {
            formatMessage(_file,_one,_values);
        } else {
            formatMessage(_file,_two,_values);
        }
    }

    public void displayIntDef(long _value, String _file, String _one, String _two) {
        if (_value == 0) {
            formatMessage(_file,_one);
        } else {
            formatMessage(_file,_two,Long.toString(_value));
        }
    }

    public void displayIntDef(long _value, String _file, String _one) {
        displayIntDef(Long.toString(_value), _file, _one);
    }

    public void displayIntDef(Rate _value, String _file, String _one) {
        displayIntDef(_value.toNumberString(), _file, _one);
    }

    public void displayIntDef(String _value, String _file, String _one) {
        if (!StringUtil.quickEq(_value,"0")) {
            formatMessage(_file,_one,_value);
        }
    }
    public void target(TargetChoice _target, String _file, String... _poss) {
        if (_target == TargetChoice.ADJ_ADV) {
            formatMessage(_file,_poss[0]);
        }
        if (_target == TargetChoice.ADJ_MULT) {
            formatMessage(_file,_poss[1]);
        }
        if (_target == TargetChoice.ADJ_UNIQ) {
            formatMessage(_file,_poss[2]);
        }
        if (_target == TargetChoice.ALLIE) {
            formatMessage(_file,_poss[3]);
        }
        if (_target == TargetChoice.ALLIES) {
            formatMessage(_file,_poss[4]);
        }
        if (_target == TargetChoice.ANY_FOE) {
            formatMessage(_file,_poss[5]);
        }
        if (_target == TargetChoice.AUTRE_UNIQ) {
            formatMessage(_file,_poss[6]);
        }
        if (_target == TargetChoice.GLOBALE) {
            formatMessage(_file,_poss[7]);
        }
        if (_target == TargetChoice.LANCEUR) {
            formatMessage(_file,_poss[8]);
        }
        if (_target == TargetChoice.PSEUDO_GLOBALE) {
            formatMessage(_file,_poss[9]);
        }
        if (_target == TargetChoice.TOUS_ADV) {
            formatMessage(_file,_poss[10]);
        }
        if (_target == TargetChoice.UNIQUE_IMPORTE) {
            formatMessage(_file,_poss[11]);
        }
    }
    public void displayBoolFalse(int _value, String _file, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.FALSE_VALUE,_key,_values);
    }
    public void displayBoolTrue(int _value, String _file, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.TRUE_VALUE,_key,_values);
    }
    public void displayBool(String _file, int _value, int _car, String _key, String... _values) {
        if (_value == _car) {
            formatMessage(_file,_key,_values);
        }
    }
    public void displayBool(int _value, int _car, int[][] _key) {
        if (_value == _car) {
            addImg(_key);
        }
    }
    public void formatMessage(String _file, String _key, String... _values) {
        String txt_ = builder.formatMessageRend(getAppName(),_file, _key, _values);
        builder.formatMessageDir(txt_);
    }

    public void formatMessageCts(String _file, String _key, String... _values) {
        String txt_ = builder.formatMessageRend(getAppName(), _file, _key, _values);
        builder.formatMessageDirCts(txt_);
        builder.breakNext();
    }

    public void formatMessageIndent(String _file, String _key, String... _values) {
        if (getBuilder().getIndent() == 0) {
            formatMessage(_file, _key, _values);
            return;
        }
        initLine();
        getBuilder().indent();
        String txt_ = formatMessageRend(_file, _key, _values);
        formatMessageDir(txt_);
        feedParents();
    }

    public void formatMessageDirIndent(String _txt) {
        initLine();
        getBuilder().indent();
        paintMetaLabelDisk();
        formatMessageDir(_txt);
        feedParents();
    }

    public String formatMessageRend(String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(builder.file(getAppName(),_file).getMapping().getVal(_key), _values);
    }

    public void formatMessageDirAnc(String _txt, IntBeanAction _b) {
        builder.formatMessageDir(_txt, _b);
    }

    public void formatMessageDir(String _txt) {
        builder.formatMessageDir(_txt);
    }

    public void formatMessageDirCts(String _txt) {
        formatMessageDirCts(new TranslatedKey(_txt,_txt));
    }

    public void formatMessageDirCts(TranslatedKey _txt) {
        if (ent(_txt)) {
            builder.formatMessageDirCts(_txt.getTranslation(),new EntityClickFormEvent(this,_txt));
        } else {
            builder.formatMessageDirCts(_txt.getTranslation());
        }
    }

    public void formatMessageDir(TranslatedKey _txt) {
        if (ent(_txt)) {
            builder.formatMessageDir(_txt.getTranslation(),new EntityClickFormEvent(this,_txt));
        } else {
            builder.formatMessageDir(_txt.getTranslation());
        }
    }
    private boolean ent(TranslatedKey _txt) {
        return !_txt.getKey().isEmpty() && _txt.getRedirect() != null;
    }

    public void feedParentsCts() {
        builder.feedParentsCts();
    }

    public void feedParents() {
        builder.feedParents();
    }

    public void displayActivityOfMoveEnabled(ActivityOfMove _value,String _one, String _two) {
        if (_value.isEnabled()) {
            formatMessageDirCts(_one);
        } else {
            formatMessageDirCts(_two);
        }
    }
    public void displayActivityOfMoveNbRound(ActivityOfMove _value, String _key) {
        if (_value.isIncrementCount()) {
            formatMessageDirCts(Long.toString(_value.getNbTurn()));
        } else {
            formatMessageDirCts(_key);
        }
    }
    public void headerCol(String _file, String _key) {
        String txt_ = builder.formatMessageRend(getAppName(), _file, _key);
        builder.formatMessageDirCtsHeader(txt_);
    }
    public IntBeanBuilderHelper getBuilder() {
        return builder;
    }

    public void setBuilder(IntBeanBuilderHelper _b) {
        this.builder = _b;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String _a) {
        this.appName = _a;
    }
}