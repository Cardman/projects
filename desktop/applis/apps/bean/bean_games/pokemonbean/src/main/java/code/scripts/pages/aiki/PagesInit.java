package code.scripts.pages.aiki;
import code.scripts.confs.PkScriptPages;
import code.sml.*;
import code.util.*;

public final class PagesInit{
private PagesInit(){}
public static StringMap<Document> build(){
StringMap<Document> m_ = new StringMap<Document>();
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML,PageDataAbilityAbilities.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITY_HTML,PageDataAbilityAbility.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,PageDataAbilityData.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBO_HTML,PageDataComboCombo.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML,PageDataComboCombos.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_EFF_HTML,PageDataEndroundEff.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_ENDROUND_HTML,PageDataEndroundEndround.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_FOE_HTML,PageDataEndroundFoe.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_GLOBAL_HTML,PageDataEndroundGlobal.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_INDIVIDUAL_HTML,PageDataEndroundIndividual.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_MULTIRELATION_HTML,PageDataEndroundMultirelation.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_POSITIONRELATION_HTML,PageDataEndroundPositionrelation.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_POSITIONTARGET_HTML,PageDataEndroundPositiontarget.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_SINGLERELATION_HTML,PageDataEndroundSinglerelation.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_STATUS_HTML,PageDataEndroundStatus.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_STATUSRELATION_HTML,PageDataEndroundStatusrelation.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_TEAM_HTML,PageDataEndroundTeam.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_GENERAL_GENERAL_HTML,PageDataGeneralGeneral.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,PageDataIndex.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,PageDataItemsBall.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML,PageDataItemsBerry.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BOOST_HTML,PageDataItemsBoost.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML,PageDataItemsEvoItem.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML,PageDataItemsEvoStone.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_FOSSIL_HTML,PageDataItemsFossil.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGHP_HTML,PageDataItemsHealinghp.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML,PageDataItemsHealinghpstatus.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML,PageDataItemsHealingitem.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGPP_HTML,PageDataItemsHealingpp.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML,PageDataItemsHealingstatus.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEM_HTML,PageDataItemsItem.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,PageDataItemsItemforbattle.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML,PageDataItemsItems.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_REPEL_HTML,PageDataItemsRepel.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_SELLINGITEM_HTML,PageDataItemsSellingitem.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_LANGS_LANGS_HTML,PageDataLangsLangs.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_ALLY_HTML,PageDataMapElementsAlly.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML,PageDataMapElementsArea.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DEALER_HTML,PageDataMapElementsDealer.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML,PageDataMapElementsDualFight.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML,PageDataMapElementsLegPk.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_POKEMON_TEAM_HTML,PageDataMapElementsPokemonTeam.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_SELLER_HTML,PageDataMapElementsSeller.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML,PageDataMapElementsTrainerMultiFight.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,PageDataMapElementsTrainerOneFight.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,PageDataMapLevel.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_MAP_HTML,PageDataMapMap.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,PageDataMovesData.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFF_HTML,PageDataMovesEffectsEff.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFACCURACY_HTML,PageDataMovesEffectsEffaccuracy.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFALLY_HTML,PageDataMovesEffectsEffally.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFBATONPASS_HTML,PageDataMovesEffectsEffbatonpass.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCLONE_HTML,PageDataMovesEffectsEffclone.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOMMONSTATISTICS_HTML,PageDataMovesEffectsEffcommonstatistics.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYFIGHTER_HTML,PageDataMovesEffectsEffcopyfighter.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYMOVE_HTML,PageDataMovesEffectsEffcopymove.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOUNTERATTACK_HTML,PageDataMovesEffectsEffcounterattack.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFDAMAGE_HTML,PageDataMovesEffectsEffdamage.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFDAMAGERATE_HTML,PageDataMovesEffectsEffdamagerate.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFENDROUND_HTML,PageDataMovesEffectsEffendround.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFFULLHPRATE_HTML,PageDataMovesEffectsEfffullhprate.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFGLOBAL_HTML,PageDataMovesEffectsEffglobal.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFINVOKE_HTML,PageDataMovesEffectsEffinvoke.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTSUFFEREDMOVEPOWER_HTML,PageDataMovesEffectsEffmultsufferedmovepower.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFMULTUSEDMOVEPOWER_HTML,PageDataMovesEffectsEffmultusedmovepower.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFORDER_HTML,PageDataMovesEffectsEfforder.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFPROTECTFROMTYPES_HTML,PageDataMovesEffectsEffprotectfromtypes.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFPROTECTION_HTML,PageDataMovesEffectsEffprotection.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFREMAINEDHPRATE_HTML,PageDataMovesEffectsEffremainedhprate.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFRESTRICTION_HTML,PageDataMovesEffectsEffrestriction.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSTATIS_HTML,PageDataMovesEffectsEffstatis.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSTATUS_HTML,PageDataMovesEffectsEffstatus.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHABILITIES_HTML,PageDataMovesEffectsEffswitchabilities.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHITEMS_HTML,PageDataMovesEffectsEffswitchitems.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHMOVETYPES_HTML,PageDataMovesEffectsEffswitchmovetypes.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOINTVIEW_HTML,PageDataMovesEffectsEffswitchpointview.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOSITION_HTML,PageDataMovesEffectsEffswitchposition.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHTYPES_HTML,PageDataMovesEffectsEffswitchtypes.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAM_HTML,PageDataMovesEffectsEffteam.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAMWHILESENDINGFOE_HTML,PageDataMovesEffectsEffteamwhilesendingfoe.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFUNPROTECTFROMTYPES_HTML,PageDataMovesEffectsEffunprotectfromtypes.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFVARPP_HTML,PageDataMovesEffectsEffvarpp.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFWINMONEY_HTML,PageDataMovesEffectsEffwinmoney.build());
//m_.addEntry("web/html/moves/moveline.html",PageDataMovesMoveline.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,PageDataMovesMoves.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,PageDataPokemonData.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOHAPPY_HTML,PageDataPokemonEvolutionsEvohappy.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOITEM_HTML,PageDataPokemonEvolutionsEvoitem.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVEL_HTML,PageDataPokemonEvolutionsEvolevel.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVELGENDER_HTML,PageDataPokemonEvolutionsEvolevelgender.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOMOVE_HTML,PageDataPokemonEvolutionsEvomove.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONE_HTML,PageDataPokemonEvolutionsEvostone.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONEGENDER_HTML,PageDataPokemonEvolutionsEvostonegender.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTEAM_HTML,PageDataPokemonEvolutionsEvoteam.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTYPE_HTML,PageDataPokemonEvolutionsEvotype.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML,PageDataPokemonPokedex.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_ROUND_HELPROUND_HTML,PageDataRoundHelpround.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SENDING_EFFSENDING_HTML,PageDataSendingEffsending.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML,PageDataSimulationAddpokemon.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML,PageDataSimulationEditpokemon.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML,PageDataSimulationEditpokemonmoves.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML,PageDataSimulationEditpokemontrainer.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML,PageDataSimulationSelectability.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML,PageDataSimulationSelectitem.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML,PageDataSimulationSelectpokemon.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,PageDataSimulationSimulation.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML,PageDataSimulationSimulationlevel.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SOLUTION_SOLUTION_HTML,PageDataSolutionSolution.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,PageDataStatusData.build());
m_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_STATUS_STATUS_HTML,PageDataStatusStatus.build());
//m_.addEntry(PkScriptPages.DIFF_COMMON_HTML,PageDifficultyCommon.build());
return m_;
}
}