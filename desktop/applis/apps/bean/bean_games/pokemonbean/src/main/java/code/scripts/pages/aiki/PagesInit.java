package code.scripts.pages.aiki;
import aiki.beans.game.AikiBeansGameStd;
import code.sml.*;
import code.util.*;
import aiki.beans.fight.*;
public final class PagesInit{
public static final String PREFIX="resources_pk/rom/";
private PagesInit(){}
public static StringMap<Document> buildInd(){
StringMap<Document> m_ = new StringMap<Document>();
m_.addEntry("resources_pk/rom/web_pk/html/pokemon.html",PagePkPokemon.build());
return m_;
}
public static StringMap<Document> buildDiff(){
StringMap<Document> m_ = new StringMap<Document>();
m_.addEntry(PREFIX+AikiBeansGameStd.WEB_GAME_HTML_DIFFICULTY_HTML,PageGameDifficulty.build());
m_.addEntry(PREFIX+AikiBeansGameStd.DIFF_COMMON_HTML,PageDifficultyCommon.build());
return m_;
}
public static StringMap<Document> buildFight(){
StringMap<Document> m_ = new StringMap<Document>();
m_.addEntry(PREFIX+AikiBeansFightStd.WEB_FIGHT_HTML_FIGHT_HTML,PageFightFight.build());
m_.addEntry(PREFIX+AikiBeansFightStd.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,PageFightFightdetail.build());
m_.addEntry(PREFIX+AikiBeansFightStd.WEB_FIGHT_HTML_FIGHTER_HTML,PageFightFighter.build());
m_.addEntry(PREFIX+AikiBeansFightStd.WEB_FIGHT_HTML_TEAM_HTML,PageFightTeam.build());
return m_;
}
public static StringMap<Document> buildProg(){
StringMap<Document> m_ = new StringMap<Document>();
m_.addEntry("resources_pk/rom/web_prog/html/gameprog.html",PageProgGameprog.build());
m_.addEntry("resources_pk/rom/web_prog/html/gameprogall.html",PageProgGameprogall.build());
m_.addEntry("resources_pk/rom/web_prog/html/gameprognotatall.html",PageProgGameprognotatall.build());
m_.addEntry("resources_pk/rom/web_prog/html/gameprogpart.html",PageProgGameprogpart.build());
return m_;
}
public static StringMap<Document> build(){
StringMap<Document> m_ = new StringMap<Document>();
m_.addEntry("resources_pk/rom/web/html/ability/abilities.html",PageDataAbilityAbilities.build());
m_.addEntry("resources_pk/rom/web/html/ability/ability.html",PageDataAbilityAbility.build());
m_.addEntry("resources_pk/rom/web/html/ability/data.html",PageDataAbilityData.build());
m_.addEntry("resources_pk/rom/web/html/combo/combo.html",PageDataComboCombo.build());
m_.addEntry("resources_pk/rom/web/html/combo/combos.html",PageDataComboCombos.build());
m_.addEntry("resources_pk/rom/web/html/endround/eff.html",PageDataEndroundEff.build());
m_.addEntry("resources_pk/rom/web/html/endround/endround.html",PageDataEndroundEndround.build());
m_.addEntry("resources_pk/rom/web/html/endround/foe.html",PageDataEndroundFoe.build());
m_.addEntry("resources_pk/rom/web/html/endround/global.html",PageDataEndroundGlobal.build());
m_.addEntry("resources_pk/rom/web/html/endround/individual.html",PageDataEndroundIndividual.build());
m_.addEntry("resources_pk/rom/web/html/endround/multirelation.html",PageDataEndroundMultirelation.build());
m_.addEntry("resources_pk/rom/web/html/endround/positionrelation.html",PageDataEndroundPositionrelation.build());
m_.addEntry("resources_pk/rom/web/html/endround/positiontarget.html",PageDataEndroundPositiontarget.build());
m_.addEntry("resources_pk/rom/web/html/endround/singlerelation.html",PageDataEndroundSinglerelation.build());
m_.addEntry("resources_pk/rom/web/html/endround/status.html",PageDataEndroundStatus.build());
m_.addEntry("resources_pk/rom/web/html/endround/statusrelation.html",PageDataEndroundStatusrelation.build());
m_.addEntry("resources_pk/rom/web/html/endround/team.html",PageDataEndroundTeam.build());
m_.addEntry("resources_pk/rom/web/html/general/general.html",PageDataGeneralGeneral.build());
m_.addEntry("resources_pk/rom/web/html/index.html",PageDataIndex.build());
m_.addEntry("resources_pk/rom/web/html/items/ball.html",PageDataItemsBall.build());
m_.addEntry("resources_pk/rom/web/html/items/berry.html",PageDataItemsBerry.build());
m_.addEntry("resources_pk/rom/web/html/items/boost.html",PageDataItemsBoost.build());
m_.addEntry("resources_pk/rom/web/html/items/evo_item.html",PageDataItemsEvoItem.build());
m_.addEntry("resources_pk/rom/web/html/items/evo_stone.html",PageDataItemsEvoStone.build());
m_.addEntry("resources_pk/rom/web/html/items/fossil.html",PageDataItemsFossil.build());
m_.addEntry("resources_pk/rom/web/html/items/healinghp.html",PageDataItemsHealinghp.build());
m_.addEntry("resources_pk/rom/web/html/items/healinghpstatus.html",PageDataItemsHealinghpstatus.build());
m_.addEntry("resources_pk/rom/web/html/items/healingitem.html",PageDataItemsHealingitem.build());
m_.addEntry("resources_pk/rom/web/html/items/healingpp.html",PageDataItemsHealingpp.build());
m_.addEntry("resources_pk/rom/web/html/items/healingstatus.html",PageDataItemsHealingstatus.build());
m_.addEntry("resources_pk/rom/web/html/items/item.html",PageDataItemsItem.build());
m_.addEntry("resources_pk/rom/web/html/items/itemforbattle.html",PageDataItemsItemforbattle.build());
m_.addEntry("resources_pk/rom/web/html/items/items.html",PageDataItemsItems.build());
m_.addEntry("resources_pk/rom/web/html/items/repel.html",PageDataItemsRepel.build());
m_.addEntry("resources_pk/rom/web/html/items/sellingitem.html",PageDataItemsSellingitem.build());
m_.addEntry("resources_pk/rom/web/html/langs/langs.html",PageDataLangsLangs.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/ally.html",PageDataMapElementsAlly.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/area.html",PageDataMapElementsArea.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/dealer.html",PageDataMapElementsDealer.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/dual_fight.html",PageDataMapElementsDualFight.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/leg_pk.html",PageDataMapElementsLegPk.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/pokemon_team.html",PageDataMapElementsPokemonTeam.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/seller.html",PageDataMapElementsSeller.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/trainer_multi_fight.html",PageDataMapElementsTrainerMultiFight.build());
m_.addEntry("resources_pk/rom/web/html/map/elements/trainer_one_fight.html",PageDataMapElementsTrainerOneFight.build());
m_.addEntry("resources_pk/rom/web/html/map/level.html",PageDataMapLevel.build());
m_.addEntry("resources_pk/rom/web/html/map/map.html",PageDataMapMap.build());
m_.addEntry("resources_pk/rom/web/html/moves/data.html",PageDataMovesData.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/eff.html",PageDataMovesEffectsEff.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effaccuracy.html",PageDataMovesEffectsEffaccuracy.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effally.html",PageDataMovesEffectsEffally.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effbatonpass.html",PageDataMovesEffectsEffbatonpass.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effclone.html",PageDataMovesEffectsEffclone.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effcommonstatistics.html",PageDataMovesEffectsEffcommonstatistics.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effcopyfighter.html",PageDataMovesEffectsEffcopyfighter.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effcopymove.html",PageDataMovesEffectsEffcopymove.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effcounterattack.html",PageDataMovesEffectsEffcounterattack.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effdamage.html",PageDataMovesEffectsEffdamage.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effdamagerate.html",PageDataMovesEffectsEffdamagerate.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effendround.html",PageDataMovesEffectsEffendround.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/efffullhprate.html",PageDataMovesEffectsEfffullhprate.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effglobal.html",PageDataMovesEffectsEffglobal.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effinvoke.html",PageDataMovesEffectsEffinvoke.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effmultsufferedmovepower.html",PageDataMovesEffectsEffmultsufferedmovepower.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effmultusedmovepower.html",PageDataMovesEffectsEffmultusedmovepower.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/efforder.html",PageDataMovesEffectsEfforder.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effprotectfromtypes.html",PageDataMovesEffectsEffprotectfromtypes.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effprotection.html",PageDataMovesEffectsEffprotection.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effremainedhprate.html",PageDataMovesEffectsEffremainedhprate.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effrestriction.html",PageDataMovesEffectsEffrestriction.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effstatis.html",PageDataMovesEffectsEffstatis.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effstatus.html",PageDataMovesEffectsEffstatus.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effswitchabilities.html",PageDataMovesEffectsEffswitchabilities.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effswitchitems.html",PageDataMovesEffectsEffswitchitems.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effswitchmovetypes.html",PageDataMovesEffectsEffswitchmovetypes.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effswitchpointview.html",PageDataMovesEffectsEffswitchpointview.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effswitchposition.html",PageDataMovesEffectsEffswitchposition.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effswitchtypes.html",PageDataMovesEffectsEffswitchtypes.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effteam.html",PageDataMovesEffectsEffteam.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effteamwhilesendingfoe.html",PageDataMovesEffectsEffteamwhilesendingfoe.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effunprotectfromtypes.html",PageDataMovesEffectsEffunprotectfromtypes.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effvarpp.html",PageDataMovesEffectsEffvarpp.build());
m_.addEntry("resources_pk/rom/web/html/moves/effects/effwinmoney.html",PageDataMovesEffectsEffwinmoney.build());
//m_.addEntry("resources_pk/rom/web/html/moves/moveline.html",PageDataMovesMoveline.build());
m_.addEntry("resources_pk/rom/web/html/moves/moves.html",PageDataMovesMoves.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/data.html",PageDataPokemonData.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evohappy.html",PageDataPokemonEvolutionsEvohappy.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evoitem.html",PageDataPokemonEvolutionsEvoitem.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evolevel.html",PageDataPokemonEvolutionsEvolevel.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evolevelgender.html",PageDataPokemonEvolutionsEvolevelgender.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evomove.html",PageDataPokemonEvolutionsEvomove.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evostone.html",PageDataPokemonEvolutionsEvostone.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evostonegender.html",PageDataPokemonEvolutionsEvostonegender.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evoteam.html",PageDataPokemonEvolutionsEvoteam.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/evolutions/evotype.html",PageDataPokemonEvolutionsEvotype.build());
m_.addEntry("resources_pk/rom/web/html/pokemon/pokedex.html",PageDataPokemonPokedex.build());
m_.addEntry("resources_pk/rom/web/html/round/helpround.html",PageDataRoundHelpround.build());
m_.addEntry("resources_pk/rom/web/html/sending/effsending.html",PageDataSendingEffsending.build());
m_.addEntry("resources_pk/rom/web/html/simulation/addpokemon.html",PageDataSimulationAddpokemon.build());
m_.addEntry("resources_pk/rom/web/html/simulation/editpokemon.html",PageDataSimulationEditpokemon.build());
m_.addEntry("resources_pk/rom/web/html/simulation/editpokemonmoves.html",PageDataSimulationEditpokemonmoves.build());
m_.addEntry("resources_pk/rom/web/html/simulation/editpokemontrainer.html",PageDataSimulationEditpokemontrainer.build());
m_.addEntry("resources_pk/rom/web/html/simulation/selectability.html",PageDataSimulationSelectability.build());
m_.addEntry("resources_pk/rom/web/html/simulation/selectitem.html",PageDataSimulationSelectitem.build());
m_.addEntry("resources_pk/rom/web/html/simulation/selectpokemon.html",PageDataSimulationSelectpokemon.build());
m_.addEntry("resources_pk/rom/web/html/simulation/simulation.html",PageDataSimulationSimulation.build());
m_.addEntry("resources_pk/rom/web/html/simulation/simulationlevel.html",PageDataSimulationSimulationlevel.build());
m_.addEntry("resources_pk/rom/web/html/solution/solution.html",PageDataSolutionSolution.build());
m_.addEntry("resources_pk/rom/web/html/status/data.html",PageDataStatusData.build());
m_.addEntry("resources_pk/rom/web/html/status/status.html",PageDataStatusStatus.build());
m_.addEntry(PREFIX+AikiBeansGameStd.DIFF_COMMON_HTML,PageDifficultyCommon.build());
return m_;
}
}