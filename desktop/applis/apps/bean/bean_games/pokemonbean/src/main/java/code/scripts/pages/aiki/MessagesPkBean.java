package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesPkBean {
    public static final String APP_BEAN_DATA = "pk_bean_data";
    public static final String APP_BEAN = "pk_bean";
    public static final String APP_BEAN_FIGHT = "pk_bean_fight";
    public static final String FIGHT = "fight";
    public static final String FIGHTER = "fighter";
    public static final String INDEX = "index";
    public static final String MV_DATA="moves/data";
    public static final String COMBO="combo/combo";
    public static final String EFF="moves/effects/eff";
    public static final String EFF_ACCURACY="moves/effects/effaccuracy";
    public static final String EFF_ALLY="moves/effects/effally";
    public static final String EFF_BATONPASS="moves/effects/effbatonpass";
    public static final String EFF_CLONE="moves/effects/effclone";
    public static final String EFF_COMMONSTATISTICS="moves/effects/effcommonstatistics";
    public static final String EFF_COPYFIGHTER="moves/effects/effcopyfighter";
    public static final String EFF_COPYMOVE="moves/effects/effcopymove";
    public static final String EFF_COUNTERATTACK="moves/effects/effcounterattack";
    public static final String EFF_DAMAGE="moves/effects/effdamage";
    public static final String EFF_DAMAGERATE="moves/effects/effdamagerate";
    public static final String EFF_ENDROUND="moves/effects/effendround";
    public static final String EFF_FULLHPRATE="moves/effects/efffullhprate";
    public static final String EFF_GLOBAL="moves/effects/effglobal";
    public static final String EFF_INVOKE="moves/effects/effinvoke";
    public static final String EFF_MULTMOVEPOWER="moves/effects/effmultmovepower";
    public static final String EFF_MULTSUFFEREDMOVEPOWER="moves/effects/effmultsufferedmovepower";
    public static final String EFF_MULTUSEDMOVEPOWER="moves/effects/effmultusedmovepower";
    public static final String EFF_ORDER="moves/effects/efforder";
    public static final String EFF_PROTECTFROMTYPES="moves/effects/effprotectfromtypes";
    public static final String EFF_PROTECTION="moves/effects/effprotection";
    public static final String EFF_REMAINEDHPRATE="moves/effects/effremainedhprate";
    public static final String EFF_RESTRICTION="moves/effects/effrestriction";
    public static final String EFF_STATIS="moves/effects/effstatis";
    public static final String EFF_STATUS="moves/effects/effstatus";
    public static final String EFF_SWITCHABILITIES="moves/effects/effswitchabilities";
    public static final String EFF_SWITCHITEMS="moves/effects/effswitchitems";
    public static final String EFF_SWITCHMOVESTYPES="moves/effects/effswitchmovestypes";
    public static final String EFF_SWITCHPOINTVIEW="moves/effects/effswitchpointview";
    public static final String EFF_SWITCHPOSITION="moves/effects/effswitchposition";
    public static final String EFF_SWITCHTYPES="moves/effects/effswitchtypes";
    public static final String EFF_TEAM="moves/effects/effteam";
    public static final String EFF_TEAMWHILESENDINGFOE="moves/effects/effteamwhilesendingfoe";
    public static final String EFF_UNPROTECTFROMTYPES="moves/effects/effunprotectfromtypes";
    public static final String EFF_VARPP="moves/effects/effvarpp";
    public static final String EFF_WINMONEY="moves/effects/effwinmoney";
    public static final String ENDROUND_ENDROUND="endround/endround";
    public static final String ENDROUND_EVENT="endround/event";
    public static final String ENDROUND_FOE="endround/foe";
    public static final String ENDROUND_INDIVIDUAL="endround/individual";
    public static final String ENDROUND_MULTIRELATION="endround/multirelation";
    public static final String ENDROUND_POSITIONRELATION="endround/positionrelation";
    public static final String ENDROUND_POSITIONTARGET="endround/positiontarget";
    public static final String ENDROUND_SINGLERELATION="endround/singlerelation";
    public static final String ENDROUND_STATUS="endround/status";
    public static final String ENDROUND_STATUSRELATION="endround/statusrelation";
    public static final String ENDROUND_TEAM="endround/team";
    public static final String ABILITIES="ability/abilities";
    public static final String AB_DATA="ability/ability";
    public static final String SENDING = "sending/effsending";
    public static final String MOVES="moves/moves";
    public static final String POKEDEX = "pokemon/pokedex";
    public static final String PK_DATA = "pokemon/data";
    public static final String EVO_HAPPINESS="pokemon/evolutions/evohappiness";
    public static final String EVO_ITEM="pokemon/evolutions/evoitem";
    public static final String EVO_LEVEL="pokemon/evolutions/evolevel";
    public static final String EVO_LEVEL_GENDER="pokemon/evolutions/evolevelgender";
    public static final String EVO_MOVE="pokemon/evolutions/evomove";
    public static final String EVO_STONE="pokemon/evolutions/evostone";
    public static final String EVO_STONE_GENDER="pokemon/evolutions/evostonegender";
    public static final String EVO_TEAM="pokemon/evolutions/evoteam";
    public static final String EVO_TYPE="pokemon/evolutions/evotype";
    public static final String IT_BALL="items/ball";
    public static final String IT_BERRY="items/berry";
    public static final String IT_BOOST="items/boost";
    public static final String IT_EVOITEM="items/evoitem";
    public static final String IT_EVOSTONE="items/evostone";
    public static final String IT_FOSSIL="items/fossil";
    public static final String IT_HEALINGHP="items/healinghp";
    public static final String IT_HEALINGHPSTATUS="items/healinghpstatus";
    public static final String IT_HEALINGITEM="items/healingitem";
    public static final String IT_HEALINGPP="items/healingpp";
    public static final String IT_HEALINGSTATUS="items/healingstatus";
    public static final String IT_ITEM="items/item";
    public static final String IT_ITEMFORBATTLE="items/itemforbattle";
    public static final String ITEMS="items/items";
    public static final String IT_REPEL="items/repel";
    public static final String TEAM = "team";
    public static final String POKEMON = "pokemon";
    public static final String GAMEPROG = "gameprog";
    public static final String DIFFICULTY = "difficulty";
    public static final String SIMULATION = "simulation/simulation";
    public static final String STATUS = "status/status";
    public static final String STATUSSET = "status/statusset";
    public static final String MAP = "map/level";
    public static final String NPC = "map/pokemon_key";
    public static final String GENERAL = "general/general";
    public static final String LANGS = "langs/langs";
    public static final String SOLUTION = "solution/solution";
    public static final String ROUND = "round/round";
    public static final String SIMU = "simulation/simulation";
    public static final String SIMU_LEVEL = "simulation/levelsimu";
    private MessagesPkBean() {

    }
    public static TranslationsAppli en(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(DIFFICULTY,MessagesGameDifficulty.en());
        m.getMapping().addEntry(GAMEPROG,MessagesProgGameprog.en());
        m.getMapping().addEntry(POKEMON,MessagesPkPokemon.en());
        return m;
    }
    public static TranslationsAppli fr(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(DIFFICULTY,MessagesGameDifficulty.fr());
        m.getMapping().addEntry(GAMEPROG,MessagesProgGameprog.fr());
        m.getMapping().addEntry(POKEMON,MessagesPkPokemon.fr());
        return m;
    }
    public static TranslationsAppli enFight(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(FIGHT,MessagesFightFight.en());
        m.getMapping().addEntry(FIGHTER,MessagesFightFighter.en());
        m.getMapping().addEntry(TEAM,MessagesFightTeam.en());
        return m;
    }
    public static TranslationsAppli frFight(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(FIGHT,MessagesFightFight.fr());
        m.getMapping().addEntry(FIGHTER,MessagesFightFighter.fr());
        m.getMapping().addEntry(TEAM,MessagesFightTeam.fr());
        return m;
    }
    public static TranslationsAppli enData(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(INDEX,MessagesDataIndex.en());
        m.getMapping().addEntry(POKEDEX,MessagesDataPokemonPokedex.en());
        m.getMapping().addEntry(PK_DATA,MessagesDataPokemonData.en());
        m.getMapping().addEntry(EVO_HAPPINESS,MessagesDataEvolutionsEvohappiness.en());
        m.getMapping().addEntry(EVO_ITEM,MessagesDataEvolutionsEvoitem.en());
        m.getMapping().addEntry(EVO_LEVEL,MessagesDataEvolutionsEvolevel.en());
        m.getMapping().addEntry(EVO_LEVEL_GENDER,MessagesDataEvolutionsEvolevelgender.en());
        m.getMapping().addEntry(EVO_MOVE,MessagesDataEvolutionsEvomove.en());
        m.getMapping().addEntry(EVO_STONE,MessagesDataEvolutionsEvostone.en());
        m.getMapping().addEntry(EVO_STONE_GENDER,MessagesDataEvolutionsEvostonegender.en());
        m.getMapping().addEntry(EVO_TEAM,MessagesDataEvolutionsEvoteam.en());
        m.getMapping().addEntry(EVO_TYPE,MessagesDataEvolutionsEvotype.en());
        m.getMapping().addEntry(MV_DATA,MessagesDataMovesData.en());
        m.getMapping().addEntry(COMBO,MessagesDataCombo.en());
        m.getMapping().addEntry(EFF,MessagesDataEff.en());
        m.getMapping().addEntry(EFF_ACCURACY,MessagesDataEffaccuracy.en());
        m.getMapping().addEntry(EFF_ALLY,MessagesDataEffally.en());
        m.getMapping().addEntry(EFF_BATONPASS,MessagesDataEffbatonpass.en());
        m.getMapping().addEntry(EFF_CLONE,MessagesDataEffclone.en());
        m.getMapping().addEntry(EFF_COMMONSTATISTICS,MessagesDataEffcommonstatistics.en());
        m.getMapping().addEntry(EFF_COPYFIGHTER,MessagesDataEffcopyfighter.en());
        m.getMapping().addEntry(EFF_COPYMOVE,MessagesDataEffcopymove.en());
        m.getMapping().addEntry(EFF_COUNTERATTACK,MessagesDataEffcounterattack.en());
        m.getMapping().addEntry(EFF_DAMAGE,MessagesDataEffdamage.en());
        m.getMapping().addEntry(EFF_DAMAGERATE,MessagesDataEffdamagerate.en());
        m.getMapping().addEntry(EFF_ENDROUND,MessagesDataEffendround.en());
        m.getMapping().addEntry(EFF_FULLHPRATE,MessagesDataEfffullhprate.en());
        m.getMapping().addEntry(EFF_GLOBAL,MessagesDataEffglobal.en());
        m.getMapping().addEntry(EFF_INVOKE,MessagesDataEffinvoke.en());
        m.getMapping().addEntry(EFF_MULTMOVEPOWER,MessagesDataEffmultmovepower.en());
        m.getMapping().addEntry(EFF_MULTSUFFEREDMOVEPOWER,MessagesDataEffmultsufferedmovepower.en());
        m.getMapping().addEntry(EFF_MULTUSEDMOVEPOWER,MessagesDataEffmultusedmovepower.en());
        m.getMapping().addEntry(EFF_ORDER,MessagesDataEfforder.en());
        m.getMapping().addEntry(EFF_PROTECTFROMTYPES,MessagesDataEffprotectfromtypes.en());
        m.getMapping().addEntry(EFF_PROTECTION,MessagesDataEffprotection.en());
        m.getMapping().addEntry(EFF_REMAINEDHPRATE,MessagesDataEffremainedhprate.en());
        m.getMapping().addEntry(EFF_RESTRICTION,MessagesDataEffrestriction.en());
        m.getMapping().addEntry(EFF_STATIS,MessagesDataEffstatis.en());
        m.getMapping().addEntry(EFF_STATUS,MessagesDataEffstatus.en());
        m.getMapping().addEntry(EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.en());
        m.getMapping().addEntry(EFF_SWITCHITEMS,MessagesDataEffswitchitems.en());
        m.getMapping().addEntry(EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.en());
        m.getMapping().addEntry(EFF_SWITCHPOINTVIEW,MessagesDataEffswitchpointview.en());
        m.getMapping().addEntry(EFF_SWITCHPOSITION,MessagesDataEffswitchposition.en());
        m.getMapping().addEntry(EFF_SWITCHTYPES,MessagesDataEffswitchtypes.en());
        m.getMapping().addEntry(EFF_TEAM,MessagesDataEffteam.en());
        m.getMapping().addEntry(EFF_TEAMWHILESENDINGFOE,MessagesDataEffteamwhilesendingfoe.en());
        m.getMapping().addEntry(EFF_UNPROTECTFROMTYPES,MessagesDataEffunprotectfromtypes.en());
        m.getMapping().addEntry(EFF_VARPP,MessagesDataEffvarpp.en());
        m.getMapping().addEntry(EFF_WINMONEY,MessagesDataEffwinmoney.en());
        m.getMapping().addEntry(ENDROUND_ENDROUND,MessagesDataEndroundEndround.en());
        m.getMapping().addEntry(ENDROUND_EVENT,MessagesDataEndroundEvent.en());
        m.getMapping().addEntry(ENDROUND_FOE,MessagesDataEndroundFoe.en());
        m.getMapping().addEntry(ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.en());
        m.getMapping().addEntry(ENDROUND_MULTIRELATION,MessagesDataEndroundMultirelation.en());
        m.getMapping().addEntry(ENDROUND_POSITIONRELATION,MessagesDataEndroundPositionrelation.en());
        m.getMapping().addEntry(ENDROUND_POSITIONTARGET,MessagesDataEndroundPositiontarget.en());
        m.getMapping().addEntry(ENDROUND_SINGLERELATION,MessagesDataEndroundSinglerelation.en());
        m.getMapping().addEntry(ENDROUND_STATUS,MessagesDataEndroundStatus.en());
        m.getMapping().addEntry(ENDROUND_STATUSRELATION,MessagesDataEndroundStatusrelation.en());
        m.getMapping().addEntry(ENDROUND_TEAM,MessagesDataEndroundTeam.en());
        m.getMapping().addEntry(SENDING,MessagesDataSending.en());
        m.getMapping().addEntry(AB_DATA,MessagesDataAbilityData.en());
        m.getMapping().addEntry(ABILITIES,MessagesDataAbilityAbilities.en());
        m.getMapping().addEntry(MOVES,MessagesDataMovesMoves.en());
        m.getMapping().addEntry(IT_BALL,MessagesDataItemsBall.en());
        m.getMapping().addEntry(IT_BERRY,MessagesDataItemsBerry.en());
        m.getMapping().addEntry(IT_BOOST,MessagesDataItemsBoost.en());
        m.getMapping().addEntry(IT_EVOITEM,MessagesDataItemsEvoitem.en());
        m.getMapping().addEntry(IT_EVOSTONE,MessagesDataItemsEvostone.en());
        m.getMapping().addEntry(IT_FOSSIL,MessagesDataItemsFossil.en());
        m.getMapping().addEntry(IT_HEALINGHP,MessagesDataItemsHealinghp.en());
        m.getMapping().addEntry(IT_HEALINGHPSTATUS,MessagesDataItemsHealinghpstatus.en());
        m.getMapping().addEntry(IT_HEALINGITEM,MessagesDataItemsHealingitem.en());
        m.getMapping().addEntry(IT_HEALINGPP,MessagesDataItemsHealingpp.en());
        m.getMapping().addEntry(IT_HEALINGSTATUS,MessagesDataItemsHealingstatus.en());
        m.getMapping().addEntry(IT_ITEM,MessagesDataItemsItem.en());
        m.getMapping().addEntry(IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.en());
        m.getMapping().addEntry(IT_REPEL,MessagesDataItemsRepel.en());
        m.getMapping().addEntry(ITEMS,MessagesDataItems.en());
        m.getMapping().addEntry(STATUS,MessagesDataStatus.en());
        m.getMapping().addEntry(STATUSSET,MessagesDataStatusset.en());
        m.getMapping().addEntry(MAP,MessagesDataMapLevel.en());
        m.getMapping().addEntry(NPC,MessagesDataMapPokemonKey.en());
        m.getMapping().addEntry(GENERAL,MessagesDataGeneral.en());
        m.getMapping().addEntry(LANGS,MessagesDataLangs.en());
        m.getMapping().addEntry(SOLUTION,MessagesDataSolution.en());
        m.getMapping().addEntry(ROUND,MessagesDataRound.en());
        m.getMapping().addEntry(SIMU,MessagesDataSimulation.en());
        m.getMapping().addEntry(SIMU_LEVEL,MessagesDataSimulationLevelsimu.en());
        m.getMapping().addEntry(DIFFICULTY,MessagesGameDifficulty.en());
        return m;
    }
    public static TranslationsAppli frData(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(INDEX,MessagesDataIndex.fr());
        m.getMapping().addEntry(POKEDEX,MessagesDataPokemonPokedex.fr());
        m.getMapping().addEntry(PK_DATA,MessagesDataPokemonData.fr());
        m.getMapping().addEntry(EVO_HAPPINESS,MessagesDataEvolutionsEvohappiness.fr());
        m.getMapping().addEntry(EVO_ITEM,MessagesDataEvolutionsEvoitem.fr());
        m.getMapping().addEntry(EVO_LEVEL,MessagesDataEvolutionsEvolevel.fr());
        m.getMapping().addEntry(EVO_LEVEL_GENDER,MessagesDataEvolutionsEvolevelgender.fr());
        m.getMapping().addEntry(EVO_MOVE,MessagesDataEvolutionsEvomove.fr());
        m.getMapping().addEntry(EVO_STONE,MessagesDataEvolutionsEvostone.fr());
        m.getMapping().addEntry(EVO_STONE_GENDER,MessagesDataEvolutionsEvostonegender.fr());
        m.getMapping().addEntry(EVO_TEAM,MessagesDataEvolutionsEvoteam.fr());
        m.getMapping().addEntry(EVO_TYPE,MessagesDataEvolutionsEvotype.fr());
        m.getMapping().addEntry(MV_DATA,MessagesDataMovesData.fr());
        m.getMapping().addEntry(COMBO,MessagesDataCombo.fr());
        m.getMapping().addEntry(EFF,MessagesDataEff.fr());
        m.getMapping().addEntry(EFF_ACCURACY,MessagesDataEffaccuracy.fr());
        m.getMapping().addEntry(EFF_ALLY,MessagesDataEffally.fr());
        m.getMapping().addEntry(EFF_BATONPASS,MessagesDataEffbatonpass.fr());
        m.getMapping().addEntry(EFF_CLONE,MessagesDataEffclone.fr());
        m.getMapping().addEntry(EFF_COMMONSTATISTICS,MessagesDataEffcommonstatistics.fr());
        m.getMapping().addEntry(EFF_COPYFIGHTER,MessagesDataEffcopyfighter.fr());
        m.getMapping().addEntry(EFF_COPYMOVE,MessagesDataEffcopymove.fr());
        m.getMapping().addEntry(EFF_COUNTERATTACK,MessagesDataEffcounterattack.fr());
        m.getMapping().addEntry(EFF_DAMAGE,MessagesDataEffdamage.fr());
        m.getMapping().addEntry(EFF_DAMAGERATE,MessagesDataEffdamagerate.fr());
        m.getMapping().addEntry(EFF_ENDROUND,MessagesDataEffendround.fr());
        m.getMapping().addEntry(EFF_FULLHPRATE,MessagesDataEfffullhprate.fr());
        m.getMapping().addEntry(EFF_GLOBAL,MessagesDataEffglobal.fr());
        m.getMapping().addEntry(EFF_INVOKE,MessagesDataEffinvoke.fr());
        m.getMapping().addEntry(EFF_MULTMOVEPOWER,MessagesDataEffmultmovepower.fr());
        m.getMapping().addEntry(EFF_MULTSUFFEREDMOVEPOWER,MessagesDataEffmultsufferedmovepower.fr());
        m.getMapping().addEntry(EFF_MULTUSEDMOVEPOWER,MessagesDataEffmultusedmovepower.fr());
        m.getMapping().addEntry(EFF_ORDER,MessagesDataEfforder.fr());
        m.getMapping().addEntry(EFF_PROTECTFROMTYPES,MessagesDataEffprotectfromtypes.fr());
        m.getMapping().addEntry(EFF_PROTECTION,MessagesDataEffprotection.fr());
        m.getMapping().addEntry(EFF_REMAINEDHPRATE,MessagesDataEffremainedhprate.fr());
        m.getMapping().addEntry(EFF_RESTRICTION,MessagesDataEffrestriction.fr());
        m.getMapping().addEntry(EFF_STATIS,MessagesDataEffstatis.fr());
        m.getMapping().addEntry(EFF_STATUS,MessagesDataEffstatus.fr());
        m.getMapping().addEntry(EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.fr());
        m.getMapping().addEntry(EFF_SWITCHITEMS,MessagesDataEffswitchitems.fr());
        m.getMapping().addEntry(EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.fr());
        m.getMapping().addEntry(EFF_SWITCHPOINTVIEW,MessagesDataEffswitchpointview.fr());
        m.getMapping().addEntry(EFF_SWITCHPOSITION,MessagesDataEffswitchposition.fr());
        m.getMapping().addEntry(EFF_SWITCHTYPES,MessagesDataEffswitchtypes.fr());
        m.getMapping().addEntry(EFF_TEAM,MessagesDataEffteam.fr());
        m.getMapping().addEntry(EFF_TEAMWHILESENDINGFOE,MessagesDataEffteamwhilesendingfoe.fr());
        m.getMapping().addEntry(EFF_UNPROTECTFROMTYPES,MessagesDataEffunprotectfromtypes.fr());
        m.getMapping().addEntry(EFF_VARPP,MessagesDataEffvarpp.fr());
        m.getMapping().addEntry(EFF_WINMONEY,MessagesDataEffwinmoney.fr());
        m.getMapping().addEntry(ENDROUND_ENDROUND,MessagesDataEndroundEndround.fr());
        m.getMapping().addEntry(ENDROUND_EVENT,MessagesDataEndroundEvent.fr());
        m.getMapping().addEntry(ENDROUND_FOE,MessagesDataEndroundFoe.fr());
        m.getMapping().addEntry(ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.fr());
        m.getMapping().addEntry(ENDROUND_MULTIRELATION,MessagesDataEndroundMultirelation.fr());
        m.getMapping().addEntry(ENDROUND_POSITIONRELATION,MessagesDataEndroundPositionrelation.fr());
        m.getMapping().addEntry(ENDROUND_POSITIONTARGET,MessagesDataEndroundPositiontarget.fr());
        m.getMapping().addEntry(ENDROUND_SINGLERELATION,MessagesDataEndroundSinglerelation.fr());
        m.getMapping().addEntry(ENDROUND_STATUS,MessagesDataEndroundStatus.fr());
        m.getMapping().addEntry(ENDROUND_STATUSRELATION,MessagesDataEndroundStatusrelation.fr());
        m.getMapping().addEntry(ENDROUND_TEAM,MessagesDataEndroundTeam.fr());
        m.getMapping().addEntry(SENDING,MessagesDataSending.fr());
        m.getMapping().addEntry(AB_DATA,MessagesDataAbilityData.fr());
        m.getMapping().addEntry(ABILITIES,MessagesDataAbilityAbilities.fr());
        m.getMapping().addEntry(MOVES,MessagesDataMovesMoves.fr());
        m.getMapping().addEntry(IT_BALL,MessagesDataItemsBall.fr());
        m.getMapping().addEntry(IT_BERRY,MessagesDataItemsBerry.fr());
        m.getMapping().addEntry(IT_BOOST,MessagesDataItemsBoost.fr());
        m.getMapping().addEntry(IT_EVOITEM,MessagesDataItemsEvoitem.fr());
        m.getMapping().addEntry(IT_EVOSTONE,MessagesDataItemsEvostone.fr());
        m.getMapping().addEntry(IT_FOSSIL,MessagesDataItemsFossil.fr());
        m.getMapping().addEntry(IT_HEALINGHP,MessagesDataItemsHealinghp.fr());
        m.getMapping().addEntry(IT_HEALINGHPSTATUS,MessagesDataItemsHealinghpstatus.fr());
        m.getMapping().addEntry(IT_HEALINGITEM,MessagesDataItemsHealingitem.fr());
        m.getMapping().addEntry(IT_HEALINGPP,MessagesDataItemsHealingpp.fr());
        m.getMapping().addEntry(IT_HEALINGSTATUS,MessagesDataItemsHealingstatus.fr());
        m.getMapping().addEntry(IT_ITEM,MessagesDataItemsItem.fr());
        m.getMapping().addEntry(IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.fr());
        m.getMapping().addEntry(IT_REPEL,MessagesDataItemsRepel.fr());
        m.getMapping().addEntry(ITEMS,MessagesDataItems.fr());
        m.getMapping().addEntry(STATUS,MessagesDataStatus.fr());
        m.getMapping().addEntry(STATUSSET,MessagesDataStatusset.fr());
        m.getMapping().addEntry(MAP,MessagesDataMapLevel.fr());
        m.getMapping().addEntry(NPC,MessagesDataMapPokemonKey.fr());
        m.getMapping().addEntry(GENERAL,MessagesDataGeneral.fr());
        m.getMapping().addEntry(LANGS,MessagesDataLangs.fr());
        m.getMapping().addEntry(SOLUTION,MessagesDataSolution.fr());
        m.getMapping().addEntry(ROUND,MessagesDataRound.fr());
        m.getMapping().addEntry(SIMU,MessagesDataSimulation.fr());
        m.getMapping().addEntry(SIMU_LEVEL,MessagesDataSimulationLevelsimu.fr());
        m.getMapping().addEntry(DIFFICULTY,MessagesGameDifficulty.fr());
        return m;
    }
}
