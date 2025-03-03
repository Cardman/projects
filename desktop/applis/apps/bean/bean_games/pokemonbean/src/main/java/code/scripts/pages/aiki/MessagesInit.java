package code.scripts.pages.aiki;
import code.bean.nat.analyze.blocks.*;
import code.sml.util.*;
public final class MessagesInit{
public static final String APP_BEAN_DATA = "pk_bean_data_";
public static final String APP_BEAN_FIGHT = "pk_bean_fight";
public static final String APP_BEAN = "pk_bean";
public static final String DIFFICULTY = "difficulty";
private MessagesInit(){}
public static TranslationsAppli enData(){
TranslationsAppli m = new TranslationsAppli();
m.getMapping().addEntry("ability/abilities",AnaRendBlockHelp.file(MesDataAbilityAbilities.en()));
m.getMapping().addEntry("ability/ability",AnaRendBlockHelp.file(MesDataAbilityAbility.en()));
m.getMapping().addEntry("combo/combo",AnaRendBlockHelp.file(MesDataComboCombo.en()));
m.getMapping().addEntry("endround/endround",AnaRendBlockHelp.file(MesDataEndroundEndround.en()));
m.getMapping().addEntry("endround/event",AnaRendBlockHelp.file(MesDataEndroundEvent.en()));
m.getMapping().addEntry("endround/foe",AnaRendBlockHelp.file(MesDataEndroundFoe.en()));
m.getMapping().addEntry("endround/individual",AnaRendBlockHelp.file(MesDataEndroundIndividual.en()));
m.getMapping().addEntry("endround/multirelation",AnaRendBlockHelp.file(MesDataEndroundMultirelation.en()));
m.getMapping().addEntry("endround/positionrelation",AnaRendBlockHelp.file(MesDataEndroundPositionrelation.en()));
m.getMapping().addEntry("endround/positiontarget",AnaRendBlockHelp.file(MesDataEndroundPositiontarget.en()));
m.getMapping().addEntry("endround/singlerelation",AnaRendBlockHelp.file(MesDataEndroundSinglerelation.en()));
m.getMapping().addEntry("endround/status",AnaRendBlockHelp.file(MesDataEndroundStatus.en()));
m.getMapping().addEntry("endround/statusrelation",AnaRendBlockHelp.file(MesDataEndroundStatusrelation.en()));
m.getMapping().addEntry("endround/team",AnaRendBlockHelp.file(MesDataEndroundTeam.en()));
m.getMapping().addEntry("general/general",AnaRendBlockHelp.file(MesDataGeneralGeneral.en()));
m.getMapping().addEntry("index",AnaRendBlockHelp.file(MesDataIndex.en()));
m.getMapping().addEntry("items/ball",AnaRendBlockHelp.file(MesDataItemsBall.en()));
m.getMapping().addEntry("items/berry",AnaRendBlockHelp.file(MesDataItemsBerry.en()));
m.getMapping().addEntry("items/boost",AnaRendBlockHelp.file(MesDataItemsBoost.en()));
m.getMapping().addEntry("items/evoitem",AnaRendBlockHelp.file(MesDataItemsEvoitem.en()));
m.getMapping().addEntry("items/evostone",AnaRendBlockHelp.file(MesDataItemsEvostone.en()));
m.getMapping().addEntry("items/fossil",AnaRendBlockHelp.file(MesDataItemsFossil.en()));
m.getMapping().addEntry("items/healinghp",AnaRendBlockHelp.file(MesDataItemsHealinghp.en()));
m.getMapping().addEntry("items/healinghpstatus",AnaRendBlockHelp.file(MesDataItemsHealinghpstatus.en()));
m.getMapping().addEntry("items/healingitem",AnaRendBlockHelp.file(MesDataItemsHealingitem.en()));
m.getMapping().addEntry("items/healingpp",AnaRendBlockHelp.file(MesDataItemsHealingpp.en()));
m.getMapping().addEntry("items/healingstatus",AnaRendBlockHelp.file(MesDataItemsHealingstatus.en()));
m.getMapping().addEntry("items/item",AnaRendBlockHelp.file(MesDataItemsItem.en()));
m.getMapping().addEntry("items/itemforbattle",AnaRendBlockHelp.file(MesDataItemsItemforbattle.en()));
m.getMapping().addEntry("items/items",AnaRendBlockHelp.file(MesDataItemsItems.en()));
m.getMapping().addEntry("items/repel",AnaRendBlockHelp.file(MesDataItemsRepel.en()));
m.getMapping().addEntry("langs/langs",AnaRendBlockHelp.file(MesDataLangsLangs.en()));
m.getMapping().addEntry("map/level",AnaRendBlockHelp.file(MesDataMapLevel.en()));
m.getMapping().addEntry("map/map",AnaRendBlockHelp.file(MesDataMapMap.en()));
m.getMapping().addEntry("map/pokemon_key",AnaRendBlockHelp.file(MesDataMapPokemonKey.en()));
m.getMapping().addEntry("moves/data",AnaRendBlockHelp.file(MesDataMovesData.en()));
m.getMapping().addEntry("moves/effects/eff",AnaRendBlockHelp.file(MesDataMovesEffectsEff.en()));
m.getMapping().addEntry("moves/effects/effaccuracy",AnaRendBlockHelp.file(MesDataMovesEffectsEffaccuracy.en()));
m.getMapping().addEntry("moves/effects/effally",AnaRendBlockHelp.file(MesDataMovesEffectsEffally.en()));
m.getMapping().addEntry("moves/effects/effbatonpass",AnaRendBlockHelp.file(MesDataMovesEffectsEffbatonpass.en()));
m.getMapping().addEntry("moves/effects/effclone",AnaRendBlockHelp.file(MesDataMovesEffectsEffclone.en()));
m.getMapping().addEntry("moves/effects/effcommonstatistics",AnaRendBlockHelp.file(MesDataMovesEffectsEffcommonstatistics.en()));
m.getMapping().addEntry("moves/effects/effcopyfighter",AnaRendBlockHelp.file(MesDataMovesEffectsEffcopyfighter.en()));
m.getMapping().addEntry("moves/effects/effcopymove",AnaRendBlockHelp.file(MesDataMovesEffectsEffcopymove.en()));
m.getMapping().addEntry("moves/effects/effcounterattack",AnaRendBlockHelp.file(MesDataMovesEffectsEffcounterattack.en()));
m.getMapping().addEntry("moves/effects/effdamage",AnaRendBlockHelp.file(MesDataMovesEffectsEffdamage.en()));
m.getMapping().addEntry("moves/effects/effdamagerate",AnaRendBlockHelp.file(MesDataMovesEffectsEffdamagerate.en()));
m.getMapping().addEntry("moves/effects/effendround",AnaRendBlockHelp.file(MesDataMovesEffectsEffendround.en()));
m.getMapping().addEntry("moves/effects/efffullhprate",AnaRendBlockHelp.file(MesDataMovesEffectsEfffullhprate.en()));
m.getMapping().addEntry("moves/effects/effglobal",AnaRendBlockHelp.file(MesDataMovesEffectsEffglobal.en()));
m.getMapping().addEntry("moves/effects/effinvoke",AnaRendBlockHelp.file(MesDataMovesEffectsEffinvoke.en()));
m.getMapping().addEntry("moves/effects/effmultsufferedmovepower",AnaRendBlockHelp.file(MesDataMovesEffectsEffmultsufferedmovepower.en()));
m.getMapping().addEntry("moves/effects/effmultusedmovepower",AnaRendBlockHelp.file(MesDataMovesEffectsEffmultusedmovepower.en()));
m.getMapping().addEntry("moves/effects/efforder",AnaRendBlockHelp.file(MesDataMovesEffectsEfforder.en()));
m.getMapping().addEntry("moves/effects/effprotectfromtypes",AnaRendBlockHelp.file(MesDataMovesEffectsEffprotectfromtypes.en()));
m.getMapping().addEntry("moves/effects/effprotection",AnaRendBlockHelp.file(MesDataMovesEffectsEffprotection.en()));
m.getMapping().addEntry("moves/effects/effremainedhprate",AnaRendBlockHelp.file(MesDataMovesEffectsEffremainedhprate.en()));
m.getMapping().addEntry("moves/effects/effrestriction",AnaRendBlockHelp.file(MesDataMovesEffectsEffrestriction.en()));
m.getMapping().addEntry("moves/effects/effstatis",AnaRendBlockHelp.file(MesDataMovesEffectsEffstatis.en()));
m.getMapping().addEntry("moves/effects/effstatus",AnaRendBlockHelp.file(MesDataMovesEffectsEffstatus.en()));
m.getMapping().addEntry("moves/effects/effswitchabilities",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchabilities.en()));
m.getMapping().addEntry("moves/effects/effswitchitems",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchitems.en()));
m.getMapping().addEntry("moves/effects/effswitchmovestypes",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchmovestypes.en()));
m.getMapping().addEntry("moves/effects/effswitchpointview",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchpointview.en()));
m.getMapping().addEntry("moves/effects/effswitchposition",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchposition.en()));
m.getMapping().addEntry("moves/effects/effswitchtypes",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchtypes.en()));
m.getMapping().addEntry("moves/effects/effteam",AnaRendBlockHelp.file(MesDataMovesEffectsEffteam.en()));
m.getMapping().addEntry("moves/effects/effteamwhilesendingfoe",AnaRendBlockHelp.file(MesDataMovesEffectsEffteamwhilesendingfoe.en()));
m.getMapping().addEntry("moves/effects/effunprotectfromtypes",AnaRendBlockHelp.file(MesDataMovesEffectsEffunprotectfromtypes.en()));
m.getMapping().addEntry("moves/effects/effvarpp",AnaRendBlockHelp.file(MesDataMovesEffectsEffvarpp.en()));
m.getMapping().addEntry("moves/effects/effwinmoney",AnaRendBlockHelp.file(MesDataMovesEffectsEffwinmoney.en()));
m.getMapping().addEntry("moves/moves",AnaRendBlockHelp.file(MesDataMovesMoves.en()));
m.getMapping().addEntry("pokemon/data",AnaRendBlockHelp.file(MesDataPokemonData.en()));
m.getMapping().addEntry("pokemon/evolutions/evohappiness",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvohappiness.en()));
m.getMapping().addEntry("pokemon/evolutions/evoitem",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvoitem.en()));
m.getMapping().addEntry("pokemon/evolutions/evolevel",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvolevel.en()));
m.getMapping().addEntry("pokemon/evolutions/evolevelgender",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvolevelgender.en()));
m.getMapping().addEntry("pokemon/evolutions/evomove",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvomove.en()));
m.getMapping().addEntry("pokemon/evolutions/evostone",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvostone.en()));
m.getMapping().addEntry("pokemon/evolutions/evostonegender",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvostonegender.en()));
m.getMapping().addEntry("pokemon/evolutions/evoteam",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvoteam.en()));
m.getMapping().addEntry("pokemon/evolutions/evotype",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvotype.en()));
m.getMapping().addEntry("pokemon/pokedex",AnaRendBlockHelp.file(MesDataPokemonPokedex.en()));
m.getMapping().addEntry("round/round",AnaRendBlockHelp.file(MesDataRoundRound.en()));
m.getMapping().addEntry("sending/effsending",AnaRendBlockHelp.file(MesDataSendingEffsending.en()));
m.getMapping().addEntry("simulation/levelsimu",AnaRendBlockHelp.file(MesDataSimulationLevelsimu.en()));
m.getMapping().addEntry("simulation/simulation",AnaRendBlockHelp.file(MesDataSimulationSimulation.en()));
m.getMapping().addEntry("solution/solution",AnaRendBlockHelp.file(MesDataSolutionSolution.en()));
m.getMapping().addEntry("status/status",AnaRendBlockHelp.file(MesDataStatusStatus.en()));
m.getMapping().addEntry("status/statusset",AnaRendBlockHelp.file(MesDataStatusStatusset.en()));
m.getMapping().addEntry(DIFFICULTY,AnaRendBlockHelp.file(MesGameDifficulty.en()));
return m;
}
public static TranslationsAppli frData(){
TranslationsAppli m = new TranslationsAppli();
m.getMapping().addEntry("ability/abilities",AnaRendBlockHelp.file(MesDataAbilityAbilities.fr()));
m.getMapping().addEntry("ability/ability",AnaRendBlockHelp.file(MesDataAbilityAbility.fr()));
m.getMapping().addEntry("combo/combo",AnaRendBlockHelp.file(MesDataComboCombo.fr()));
m.getMapping().addEntry("endround/endround",AnaRendBlockHelp.file(MesDataEndroundEndround.fr()));
m.getMapping().addEntry("endround/event",AnaRendBlockHelp.file(MesDataEndroundEvent.fr()));
m.getMapping().addEntry("endround/foe",AnaRendBlockHelp.file(MesDataEndroundFoe.fr()));
m.getMapping().addEntry("endround/individual",AnaRendBlockHelp.file(MesDataEndroundIndividual.fr()));
m.getMapping().addEntry("endround/multirelation",AnaRendBlockHelp.file(MesDataEndroundMultirelation.fr()));
m.getMapping().addEntry("endround/positionrelation",AnaRendBlockHelp.file(MesDataEndroundPositionrelation.fr()));
m.getMapping().addEntry("endround/positiontarget",AnaRendBlockHelp.file(MesDataEndroundPositiontarget.fr()));
m.getMapping().addEntry("endround/singlerelation",AnaRendBlockHelp.file(MesDataEndroundSinglerelation.fr()));
m.getMapping().addEntry("endround/status",AnaRendBlockHelp.file(MesDataEndroundStatus.fr()));
m.getMapping().addEntry("endround/statusrelation",AnaRendBlockHelp.file(MesDataEndroundStatusrelation.fr()));
m.getMapping().addEntry("endround/team",AnaRendBlockHelp.file(MesDataEndroundTeam.fr()));
m.getMapping().addEntry("general/general",AnaRendBlockHelp.file(MesDataGeneralGeneral.fr()));
m.getMapping().addEntry("index",AnaRendBlockHelp.file(MesDataIndex.fr()));
m.getMapping().addEntry("items/ball",AnaRendBlockHelp.file(MesDataItemsBall.fr()));
m.getMapping().addEntry("items/berry",AnaRendBlockHelp.file(MesDataItemsBerry.fr()));
m.getMapping().addEntry("items/boost",AnaRendBlockHelp.file(MesDataItemsBoost.fr()));
m.getMapping().addEntry("items/evoitem",AnaRendBlockHelp.file(MesDataItemsEvoitem.fr()));
m.getMapping().addEntry("items/evostone",AnaRendBlockHelp.file(MesDataItemsEvostone.fr()));
m.getMapping().addEntry("items/fossil",AnaRendBlockHelp.file(MesDataItemsFossil.fr()));
m.getMapping().addEntry("items/healinghp",AnaRendBlockHelp.file(MesDataItemsHealinghp.fr()));
m.getMapping().addEntry("items/healinghpstatus",AnaRendBlockHelp.file(MesDataItemsHealinghpstatus.fr()));
m.getMapping().addEntry("items/healingitem",AnaRendBlockHelp.file(MesDataItemsHealingitem.fr()));
m.getMapping().addEntry("items/healingpp",AnaRendBlockHelp.file(MesDataItemsHealingpp.fr()));
m.getMapping().addEntry("items/healingstatus",AnaRendBlockHelp.file(MesDataItemsHealingstatus.fr()));
m.getMapping().addEntry("items/item",AnaRendBlockHelp.file(MesDataItemsItem.fr()));
m.getMapping().addEntry("items/itemforbattle",AnaRendBlockHelp.file(MesDataItemsItemforbattle.fr()));
m.getMapping().addEntry("items/items",AnaRendBlockHelp.file(MesDataItemsItems.fr()));
m.getMapping().addEntry("items/repel",AnaRendBlockHelp.file(MesDataItemsRepel.fr()));
m.getMapping().addEntry("langs/langs",AnaRendBlockHelp.file(MesDataLangsLangs.fr()));
m.getMapping().addEntry("map/level",AnaRendBlockHelp.file(MesDataMapLevel.fr()));
m.getMapping().addEntry("map/map",AnaRendBlockHelp.file(MesDataMapMap.fr()));
m.getMapping().addEntry("map/pokemon_key",AnaRendBlockHelp.file(MesDataMapPokemonKey.fr()));
m.getMapping().addEntry("moves/data",AnaRendBlockHelp.file(MesDataMovesData.fr()));
m.getMapping().addEntry("moves/effects/eff",AnaRendBlockHelp.file(MesDataMovesEffectsEff.fr()));
m.getMapping().addEntry("moves/effects/effaccuracy",AnaRendBlockHelp.file(MesDataMovesEffectsEffaccuracy.fr()));
m.getMapping().addEntry("moves/effects/effally",AnaRendBlockHelp.file(MesDataMovesEffectsEffally.fr()));
m.getMapping().addEntry("moves/effects/effbatonpass",AnaRendBlockHelp.file(MesDataMovesEffectsEffbatonpass.fr()));
m.getMapping().addEntry("moves/effects/effclone",AnaRendBlockHelp.file(MesDataMovesEffectsEffclone.fr()));
m.getMapping().addEntry("moves/effects/effcommonstatistics",AnaRendBlockHelp.file(MesDataMovesEffectsEffcommonstatistics.fr()));
m.getMapping().addEntry("moves/effects/effcopyfighter",AnaRendBlockHelp.file(MesDataMovesEffectsEffcopyfighter.fr()));
m.getMapping().addEntry("moves/effects/effcopymove",AnaRendBlockHelp.file(MesDataMovesEffectsEffcopymove.fr()));
m.getMapping().addEntry("moves/effects/effcounterattack",AnaRendBlockHelp.file(MesDataMovesEffectsEffcounterattack.fr()));
m.getMapping().addEntry("moves/effects/effdamage",AnaRendBlockHelp.file(MesDataMovesEffectsEffdamage.fr()));
m.getMapping().addEntry("moves/effects/effdamagerate",AnaRendBlockHelp.file(MesDataMovesEffectsEffdamagerate.fr()));
m.getMapping().addEntry("moves/effects/effendround",AnaRendBlockHelp.file(MesDataMovesEffectsEffendround.fr()));
m.getMapping().addEntry("moves/effects/efffullhprate",AnaRendBlockHelp.file(MesDataMovesEffectsEfffullhprate.fr()));
m.getMapping().addEntry("moves/effects/effglobal",AnaRendBlockHelp.file(MesDataMovesEffectsEffglobal.fr()));
m.getMapping().addEntry("moves/effects/effinvoke",AnaRendBlockHelp.file(MesDataMovesEffectsEffinvoke.fr()));
m.getMapping().addEntry("moves/effects/effmultsufferedmovepower",AnaRendBlockHelp.file(MesDataMovesEffectsEffmultsufferedmovepower.fr()));
m.getMapping().addEntry("moves/effects/effmultusedmovepower",AnaRendBlockHelp.file(MesDataMovesEffectsEffmultusedmovepower.fr()));
m.getMapping().addEntry("moves/effects/efforder",AnaRendBlockHelp.file(MesDataMovesEffectsEfforder.fr()));
m.getMapping().addEntry("moves/effects/effprotectfromtypes",AnaRendBlockHelp.file(MesDataMovesEffectsEffprotectfromtypes.fr()));
m.getMapping().addEntry("moves/effects/effprotection",AnaRendBlockHelp.file(MesDataMovesEffectsEffprotection.fr()));
m.getMapping().addEntry("moves/effects/effremainedhprate",AnaRendBlockHelp.file(MesDataMovesEffectsEffremainedhprate.fr()));
m.getMapping().addEntry("moves/effects/effrestriction",AnaRendBlockHelp.file(MesDataMovesEffectsEffrestriction.fr()));
m.getMapping().addEntry("moves/effects/effstatis",AnaRendBlockHelp.file(MesDataMovesEffectsEffstatis.fr()));
m.getMapping().addEntry("moves/effects/effstatus",AnaRendBlockHelp.file(MesDataMovesEffectsEffstatus.fr()));
m.getMapping().addEntry("moves/effects/effswitchabilities",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchabilities.fr()));
m.getMapping().addEntry("moves/effects/effswitchitems",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchitems.fr()));
m.getMapping().addEntry("moves/effects/effswitchmovestypes",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchmovestypes.fr()));
m.getMapping().addEntry("moves/effects/effswitchpointview",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchpointview.fr()));
m.getMapping().addEntry("moves/effects/effswitchposition",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchposition.fr()));
m.getMapping().addEntry("moves/effects/effswitchtypes",AnaRendBlockHelp.file(MesDataMovesEffectsEffswitchtypes.fr()));
m.getMapping().addEntry("moves/effects/effteam",AnaRendBlockHelp.file(MesDataMovesEffectsEffteam.fr()));
m.getMapping().addEntry("moves/effects/effteamwhilesendingfoe",AnaRendBlockHelp.file(MesDataMovesEffectsEffteamwhilesendingfoe.fr()));
m.getMapping().addEntry("moves/effects/effunprotectfromtypes",AnaRendBlockHelp.file(MesDataMovesEffectsEffunprotectfromtypes.fr()));
m.getMapping().addEntry("moves/effects/effvarpp",AnaRendBlockHelp.file(MesDataMovesEffectsEffvarpp.fr()));
m.getMapping().addEntry("moves/effects/effwinmoney",AnaRendBlockHelp.file(MesDataMovesEffectsEffwinmoney.fr()));
m.getMapping().addEntry("moves/moves",AnaRendBlockHelp.file(MesDataMovesMoves.fr()));
m.getMapping().addEntry("pokemon/data",AnaRendBlockHelp.file(MesDataPokemonData.fr()));
m.getMapping().addEntry("pokemon/evolutions/evohappiness",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvohappiness.fr()));
m.getMapping().addEntry("pokemon/evolutions/evoitem",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvoitem.fr()));
m.getMapping().addEntry("pokemon/evolutions/evolevel",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvolevel.fr()));
m.getMapping().addEntry("pokemon/evolutions/evolevelgender",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvolevelgender.fr()));
m.getMapping().addEntry("pokemon/evolutions/evomove",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvomove.fr()));
m.getMapping().addEntry("pokemon/evolutions/evostone",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvostone.fr()));
m.getMapping().addEntry("pokemon/evolutions/evostonegender",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvostonegender.fr()));
m.getMapping().addEntry("pokemon/evolutions/evoteam",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvoteam.fr()));
m.getMapping().addEntry("pokemon/evolutions/evotype",AnaRendBlockHelp.file(MesDataPokemonEvolutionsEvotype.fr()));
m.getMapping().addEntry("pokemon/pokedex",AnaRendBlockHelp.file(MesDataPokemonPokedex.fr()));
m.getMapping().addEntry("round/round",AnaRendBlockHelp.file(MesDataRoundRound.fr()));
m.getMapping().addEntry("sending/effsending",AnaRendBlockHelp.file(MesDataSendingEffsending.fr()));
m.getMapping().addEntry("simulation/levelsimu",AnaRendBlockHelp.file(MesDataSimulationLevelsimu.fr()));
m.getMapping().addEntry("simulation/simulation",AnaRendBlockHelp.file(MesDataSimulationSimulation.fr()));
m.getMapping().addEntry("solution/solution",AnaRendBlockHelp.file(MesDataSolutionSolution.fr()));
m.getMapping().addEntry("status/status",AnaRendBlockHelp.file(MesDataStatusStatus.fr()));
m.getMapping().addEntry("status/statusset",AnaRendBlockHelp.file(MesDataStatusStatusset.fr()));
m.getMapping().addEntry(DIFFICULTY,AnaRendBlockHelp.file(MesGameDifficulty.fr()));
return m;
}
}
