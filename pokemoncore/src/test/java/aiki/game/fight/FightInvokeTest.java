package aiki.game.fight;
import static aiki.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.characters.GymLeader;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class FightInvokeTest extends InitializationDataBase {

    private static Fight invokableMoves() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        return fight_;
    }

    @Test
    public void invokableMoves1Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(FORCE_NATURE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FORCE_NATURE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(FORCE_NATURE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(SEISME));
    }

    @Test
    public void invokableMoves2Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MIMIQUE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(MOI_D_ABORD);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(IMPLORE));
    }

    @Test
    public void invokableMoves3Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MIMIQUE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(IMPLORE);
        MoveData move_ = _data_.getMove(MIMIQUE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(IMPLORE));
    }

    @Test
    public void invokableMoves4Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MOI_D_ABORD, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MOI_D_ABORD, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(MOI_D_ABORD);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(0, list_.size());
    }

    @Test
    public void invokableMoves5Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BLABLA_DODO, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BLABLA_DODO, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(BLABLA_DODO);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(GLAS_DE_SOIN));
        assertTrue(list_.containsObj(INTERVERSION));
    }

    @Test
    public void invokableMoves6Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(PHOTOCOPIE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PHOTOCOPIE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        MoveData move_ = _data_.getMove(PHOTOCOPIE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(IMPLORE));
    }

    @Test
    public void invokableMoves7Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(PHOTOCOPIE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PHOTOCOPIE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(PHOTOCOPIE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(0, list_.size());
    }

    @Test
    public void invokableMoves8Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MIMIQUE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(MIMIQUE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(0, list_.size());
    }

    @Test
    public void invokableMoves9Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MALEDICTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MALEDICTION, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(NORMAL);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(MALEDICTION);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(BUEE_NOIRE));
    }

    @Test
    public void invokableMoves10Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MALEDICTION, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MALEDICTION, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(SPECTRE);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(MALEDICTION);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(MALEDICTION_2));
    }

    @Test
    public void invokableMoves11Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ASSISTANCE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ASSISTANCE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(ASSISTANCE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(BROUHAHA));
        assertTrue(list_.containsObj(GLAS_DE_SOIN));
        assertTrue(list_.containsObj(INTERVERSION));
    }

    @Test
    public void invokableMoves12Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(METRONOME, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(METRONOME, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(METRONOME);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(250, list_.size());
        assertTrue(list_.containsObj(ABIME));
        assertTrue(list_.containsObj(ACUPRESSION));
        assertTrue(list_.containsObj(ADAPTATION));
        assertTrue(list_.containsObj(AIDE));
        assertTrue(list_.containsObj(AIRE_DE_FEU));
        assertTrue(list_.containsObj(AIRE_D_EAU));
        assertTrue(list_.containsObj(AIRE_D_HERBE));
        assertTrue(list_.containsObj(AIR_VEINARD));
        assertTrue(list_.containsObj(ANNEAU_HYDRO));
        assertTrue(list_.containsObj(ANTI_AIR));
        assertTrue(list_.containsObj(ANTI_BRUME));
        assertTrue(list_.containsObj(ANTI_CROISEUR));
        assertTrue(list_.containsObj(ANTI_SOIN));
        assertTrue(list_.containsObj(ANTI_SOL));
        assertTrue(list_.containsObj(ARMURE));
        assertTrue(list_.containsObj(ASSISTANCE_BIS));
        assertTrue(list_.containsObj(ATTERRISSAGE));
        assertTrue(list_.containsObj(ATTRACTION));
        assertTrue(list_.containsObj(ATTRITION));
        assertTrue(list_.containsObj(BAILLEMENT));
        assertTrue(list_.containsObj(BAIN_DE_SMOG));
        assertTrue(list_.containsObj(BALANCE));
        assertTrue(list_.containsObj(BALLE_GRAINE));
        assertTrue(list_.containsObj(BALL_GLACE));
        assertTrue(list_.containsObj(BALL_METEO));
        assertTrue(list_.containsObj(BALL_OMBRE));
        assertTrue(list_.containsObj(BALL_ORAGE));
        assertTrue(list_.containsObj(BASTON));
        assertTrue(list_.containsObj(BATAILLE));
        assertTrue(list_.containsObj(BELIER));
        assertTrue(list_.containsObj(BERCEUSE));
        assertTrue(list_.containsObj(BOMBAIMANT));
        assertTrue(list_.containsObj(BOOST));
        assertTrue(list_.containsObj(BOUCLIER_ROYAL));
        assertTrue(list_.containsObj(BOUE_BOMBE));
        assertTrue(list_.containsObj(BOUL_ARMURE));
        assertTrue(list_.containsObj(BOURDON));
        assertTrue(list_.containsObj(BRUME));
        assertTrue(list_.containsObj(BUEE_NOIRE));
        assertTrue(list_.containsObj(BULLES_D_O));
        assertTrue(list_.containsObj(CAGE_ECLAIR));
        assertTrue(list_.containsObj(CALCINATION));
        assertTrue(list_.containsObj(CAMOUFLAGE));
        assertTrue(list_.containsObj(CASSE));
        assertTrue(list_.containsObj(CHAMP_BRUMEUX));
        assertTrue(list_.containsObj(CHANT));
        assertTrue(list_.containsObj(CHARGE));
        assertTrue(list_.containsObj(CHARGEUR));
        assertTrue(list_.containsObj(CHARME));
        assertTrue(list_.containsObj(CHOC_MENTAL));
        assertTrue(list_.containsObj(CHOC_PSY));
        assertTrue(list_.containsObj(CLAIRVOYANCE));
        assertTrue(list_.containsObj(CLONAGE));
        assertTrue(list_.containsObj(COGNOBIDON));
        assertTrue(list_.containsObj(COMBO_GRIFFE));
        assertTrue(list_.containsObj(CONVERSION_2));
        assertTrue(list_.containsObj(COPIE_TYPE));
        assertTrue(list_.containsObj(COUD_BOUE));
        assertTrue(list_.containsObj(COUD_KRANE));
        assertTrue(list_.containsObj(COUPE_VENT));
        assertTrue(list_.containsObj(COUP_D_BOULE));
        assertTrue(list_.containsObj(COUP_D_JUS));
        assertTrue(list_.containsObj(CROC_FATAL));
        assertTrue(list_.containsObj(CROISSANCE));
        assertTrue(list_.containsObj(CRU_AILE));
        assertTrue(list_.containsObj(CYCLE_V));
        assertTrue(list_.containsObj(CYCLONE));
        assertTrue(list_.containsObj(DAMOCLES));
        assertTrue(list_.containsObj(DANSE_LAMES));
        assertTrue(list_.containsObj(DANSE_LUNE));
        assertTrue(list_.containsObj(DANSE_PLUIE));
        assertTrue(list_.containsObj(DANSE_PLUME));
        assertTrue(list_.containsObj(DARD_MORTEL));
        assertTrue(list_.containsObj(DEGOMMAGE));
        assertTrue(list_.containsObj(DELUGE_GLACIAL));
        assertTrue(list_.containsObj(DELUGE_PLASMIQUE));
        assertTrue(list_.containsObj(DEMI_TOUR));
        assertTrue(list_.containsObj(DEPIT));
        assertTrue(list_.containsObj(DESTRUCTION));
        assertTrue(list_.containsObj(DETEINTE));
        assertTrue(list_.containsObj(DETREMPAGE));
        assertTrue(list_.containsObj(DEVOREVE));
        assertTrue(list_.containsObj(DISTORSION));
        assertTrue(list_.containsObj(DODO));
        assertTrue(list_.containsObj(DODO_PETIT));
        assertTrue(list_.containsObj(DON_NATUREL));
        assertTrue(list_.containsObj(DOUBLE_PIED));
        assertTrue(list_.containsObj(DOUX_PARFUM));
        assertTrue(list_.containsObj(DRACO_RAGE));
        assertTrue(list_.containsObj(DYNAMOPOING));
        assertTrue(list_.containsObj(EBOULEMENT));
        assertTrue(list_.containsObj(EBULLITION));
        assertTrue(list_.containsObj(ECHANGE));
        assertTrue(list_.containsObj(ECHANGE_BIS));
        assertTrue(list_.containsObj(ECHANGE_PSY));
        assertTrue(list_.containsObj(ECHANGE_TYPE));
        assertTrue(list_.containsObj(ECHEC));
        assertTrue(list_.containsObj(ECLAIR));
        assertTrue(list_.containsObj(ECUME));
        assertTrue(list_.containsObj(EFFORT));
        assertTrue(list_.containsObj(ELECANON));
        assertTrue(list_.containsObj(ELECTRISATION));
        assertTrue(list_.containsObj(EMBARGO));
        assertTrue(list_.containsObj(EMPAL_KORNE));
        assertTrue(list_.containsObj(ENCORE));
        assertTrue(list_.containsObj(ENTRAVE));
        assertTrue(list_.containsObj(EXCUSE));
        assertTrue(list_.containsObj(FAUX_CHAGE));
        assertTrue(list_.containsObj(FLEAU));
        assertTrue(list_.containsObj(FONCE));
        assertTrue(list_.containsObj(FORCE_TYPE));
        assertTrue(list_.containsObj(FRAPPE_ATLAS));
        assertTrue(list_.containsObj(FULMIFER));
        assertTrue(list_.containsObj(GIGA_SANGSUE));
        assertTrue(list_.containsObj(GLACIATION));
        assertTrue(list_.containsObj(GRAVITE));
        assertTrue(list_.containsObj(GRIFFE));
        assertTrue(list_.containsObj(GRIFFE_ACIER));
        assertTrue(list_.containsObj(GRINCEMENT));
        assertTrue(list_.containsObj(GUILLOTINE));
        assertTrue(list_.containsObj(HATE));
        assertTrue(list_.containsObj(HYDROCANON));
        assertTrue(list_.containsObj(HYPNOSE));
        assertTrue(list_.containsObj(IMITATION));
        assertTrue(list_.containsObj(JACKPOT));
        assertTrue(list_.containsObj(JET_DE_SABLE));
        assertTrue(list_.containsObj(JET_PIERRES));
        assertTrue(list_.containsObj(LAME_DE_ROC));
        assertTrue(list_.containsObj(LAME_D_AIR));
        assertTrue(list_.containsObj(LANCECROU));
        assertTrue(list_.containsObj(LEVIKINESIE));
        assertTrue(list_.containsObj(LIRE_ESPRIT));
        assertTrue(list_.containsObj(LOTO));
        assertTrue(list_.containsObj(MALEDICTION));
        assertTrue(list_.containsObj(MALEDICTION_2));
        assertTrue(list_.containsObj(MALEFICE_SYLVAIN));
        assertTrue(list_.containsObj(MEGAPHONE));
        assertTrue(list_.containsObj(MUR_DE_FER));
        assertTrue(list_.containsObj(MUR_LUMIERE));
        assertTrue(list_.containsObj(NUEE_DE_POUDRE));
        assertTrue(list_.containsObj(OEIL_MIRACLE));
        assertTrue(list_.containsObj(OMBRE_PORTEE));
        assertTrue(list_.containsObj(ONDE_FOLIE));
        assertTrue(list_.containsObj(ONDE_VIDE));
        assertTrue(list_.containsObj(ORAGE));
        assertTrue(list_.containsObj(ORAGE_BIS));
        assertTrue(list_.containsObj(PARADOXE));
        assertTrue(list_.containsObj(PARTAGE_GARDE));
        assertTrue(list_.containsObj(PERENITE));
        assertTrue(list_.containsObj(PERENITE_BIS));
        assertTrue(list_.containsObj(PERMUCOEUR));
        assertTrue(list_.containsObj(PICORE));
        assertTrue(list_.containsObj(PICOTS));
        assertTrue(list_.containsObj(PICOTS_BIS));
        assertTrue(list_.containsObj(PICO_DEFENSE));
        assertTrue(list_.containsObj(PICS_TOXIK));
        assertTrue(list_.containsObj(PIEGE_DE_ROC));
        assertTrue(list_.containsObj(PINCE_MASSE));
        assertTrue(list_.containsObj(PIQURE));
        assertTrue(list_.containsObj(PISTOLET_A_O));
        assertTrue(list_.containsObj(PLAIE_CROIX));
        assertTrue(list_.containsObj(PLANNEUR));
        assertTrue(list_.containsObj(PLAQUAGE));
        assertTrue(list_.containsObj(PLONGEE));
        assertTrue(list_.containsObj(POINGLACE));
        assertTrue(list_.containsObj(POISSE));
        assertTrue(list_.containsObj(POSSESSIF));
        assertTrue(list_.containsObj(POUDRE_TOXIK));
        assertTrue(list_.containsObj(POURSUITE));
        assertTrue(list_.containsObj(POUV_ANTIQUE));
        assertTrue(list_.containsObj(PRESCIENCE));
        assertTrue(list_.containsObj(PROTECTION));
        assertTrue(list_.containsObj(PROVOC));
        assertTrue(list_.containsObj(PSYKO));
        assertTrue(list_.containsObj(PSYKOUD_BOUL));
        assertTrue(list_.containsObj(PUIS_CACHEE));
        assertTrue(list_.containsObj(RACINES));
        assertTrue(list_.containsObj(RAFALE_PSY));
        assertTrue(list_.containsObj(RAYON_GEMME));
        assertTrue(list_.containsObj(RAYON_LUNE));
        assertTrue(list_.containsObj(RAYON_SIGNAL));
        assertTrue(list_.containsObj(RAYON_SIMPLE));
        assertTrue(list_.containsObj(REBOND));
        assertTrue(list_.containsObj(REBONDIFEU));
        assertTrue(list_.containsObj(RECYCLAGE));
        assertTrue(list_.containsObj(REFLET));
        assertTrue(list_.containsObj(REGARD_NOIR));
        assertTrue(list_.containsObj(REGENERATION));
        assertTrue(list_.containsObj(RELACHE));
        assertTrue(list_.containsObj(RELAIS));
        assertTrue(list_.containsObj(REPOS));
        assertTrue(list_.containsObj(REQUIEM));
        assertTrue(list_.containsObj(REVEIL_FORCE));
        assertTrue(list_.containsObj(RISQUE));
        assertTrue(list_.containsObj(ROC_BOULET));
        assertTrue(list_.containsObj(ROUE_DE_FEU));
        assertTrue(list_.containsObj(ROULADE));
        assertTrue(list_.containsObj(RUNE_PROTECT));
        assertTrue(list_.containsObj(SACRIFICE));
        assertTrue(list_.containsObj(SEDUCTION));
        assertTrue(list_.containsObj(SEISME));
        assertTrue(list_.containsObj(SIPHON));
        assertTrue(list_.containsObj(SOIN));
        assertTrue(list_.containsObj(SONICBOOM));
        assertTrue(list_.containsObj(STOCKAGE));
        assertTrue(list_.containsObj(SURF));
        assertTrue(list_.containsObj(TAILLADE));
        assertTrue(list_.containsObj(TATAMIGAESHI));
        assertTrue(list_.containsObj(TELLURIFORCE));
        assertTrue(list_.containsObj(TEMPETESABLE));
        assertTrue(list_.containsObj(TEMPETEVERTE));
        assertTrue(list_.containsObj(TEN_DANSE));
        assertTrue(list_.containsObj(TIPHON));
        assertTrue(list_.containsObj(TIR_DE_BOUE));
        assertTrue(list_.containsObj(TOILE_GLUANTE));
        assertTrue(list_.containsObj(TONNERRE));
        assertTrue(list_.containsObj(TORGNOLES));
        assertTrue(list_.containsObj(TORNADE));
        assertTrue(list_.containsObj(TOURMENTE));
        assertTrue(list_.containsObj(TOURNIQUET));
        assertTrue(list_.containsObj(TOUR_RAPIDE));
        assertTrue(list_.containsObj(TRANCHE));
        assertTrue(list_.containsObj(TRANCHE_NUIT));
        assertTrue(list_.containsObj(TRANCH_HERBE));
        assertTrue(list_.containsObj(TREMPETTE));
        assertTrue(list_.containsObj(TROU));
        assertTrue(list_.containsObj(TROU_BIS));
        assertTrue(list_.containsObj(TUNNEL));
        assertTrue(list_.containsObj(ULTIMAPOING));
        assertTrue(list_.containsObj(ULTIMAWASHI));
        assertTrue(list_.containsObj(ULTRASON));
        assertTrue(list_.containsObj(UPPERCUT));
        assertTrue(list_.containsObj(VAGUE_PSY));
        assertTrue(list_.containsObj(VAMPIPOING));
        assertTrue(list_.containsObj(VAMPIRISME));
        assertTrue(list_.containsObj(VANTARDISE));
        assertTrue(list_.containsObj(VAS_Y));
        assertTrue(list_.containsObj(VENT_ARGENTE));
        assertTrue(list_.containsObj(VENT_ARRIERE));
        assertTrue(list_.containsObj(VENT_ARRIERE_BIS));
        assertTrue(list_.containsObj(VENT_GLACE));
        assertTrue(list_.containsObj(VIBRAQUA));
        assertTrue(list_.containsObj(VIGILANCE));
        assertTrue(list_.containsObj(VIVE_ATTAQUE));
        assertTrue(list_.containsObj(VOEU));
        assertTrue(list_.containsObj(ENVOL));
        assertTrue(list_.containsObj(VOL_MAGNETIK));
        assertTrue(list_.containsObj(ZENITH));
        assertTrue(list_.containsObj(ZONE_ETRANGE));
        assertTrue(list_.containsObj(ZONE_MAGIQUE));
    }

    @Test
    public void invokableMoves13Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(FORCE_NATURE, COPIE, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FORCE_NATURE, POKEMON_FOE_TARGET_ZERO);
        fight_.enableGlobalMove(CHAMP_BRUMEUX);
        FightRound.initRound(fight_);
        MoveData move_ = _data_.getMove(FORCE_NATURE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(TOUR_RAPIDE));
    }

    private static Fight copiableMoves() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(GLAS_DE_SOIN, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        return fight_;
    }

    @Test
    public void copiableMoves1Test() {
        Fight fight_ = copiableMoves();
        MoveData move_ = _data_.getMove(COPIE);
        EffectCopyMove eff_ = (EffectCopyMove) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.copiableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(0, list_.size());
    }

    @Test
    public void copiableMoves2Test() {
        Fight fight_ = copiableMoves();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setLastUsedMove(IMPLORE);
        MoveData move_ = _data_.getMove(COPIE);
        EffectCopyMove eff_ = (EffectCopyMove) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.copiableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(IMPLORE));
    }

    @Test
    public void copiableMoves3Test() {
        Fight fight_ = copiableMoves();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setLastUsedMove(IMPLORE);
        MoveData move_ = _data_.getMove(GRIBOUILLE);
        EffectCopyMove eff_ = (EffectCopyMove) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.copiableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(IMPLORE));
    }

    @Test
    public void copiableMoves4Test() {
        Fight fight_ = copiableMoves();
        MoveData move_ = _data_.getMove(GRIBOUILLE);
        EffectCopyMove eff_ = (EffectCopyMove) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.copiableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, _data_);
        assertEq(0, list_.size());
    }

    @Test
    public void effectInvoke1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(JACKPOT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData fAttInvoque_ = _data_.getMove(MIMIQUE);
        EffectInvoke effet_=(EffectInvoke) fAttInvoque_.getEffet(CustList.FIRST_INDEX);
        EqList<TeamPosition> list_ = FightOrder.targetsEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO,effet_,diff_,_data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(1, FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, effet_, _data_).size());
        assertTrue(FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO,MIMIQUE,CustList.FIRST_INDEX,true,_data_).isSuccessful());
        FightInvoke.effectInvoke(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, effet_, _data_);
        StringList invokedMoves_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(1, invokedMoves_.size());
        assertTrue(invokedMoves_.containsObj(JACKPOT));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectInvoke1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setSimulation(true);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(JACKPOT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(METRONOME, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData fAttInvoque_ = _data_.getMove(METRONOME);
        EffectInvoke effet_=(EffectInvoke) fAttInvoque_.getEffet(CustList.FIRST_INDEX);
        EqList<TeamPosition> list_ = FightOrder.targetsEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO,effet_,diff_,_data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(251, FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, effet_, _data_).size());
        assertTrue(FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO,METRONOME,CustList.FIRST_INDEX,true,_data_).isSuccessful());
        FightInvoke.effectInvoke(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, effet_, _data_);
        StringList invokedMoves_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(0, invokedMoves_.size());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private static Fight processInvokingMove(Difficulty _diff, byte _mult) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight(_mult);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void processInvokingMove1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = processInvokingMove(diff_, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        StringList list_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(1, list_.size());
        assertEq(SEISME, list_.get(0));
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getLastUsedMove());
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processInvokingMove2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = processInvokingMove(diff_, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(JACKPOT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        StringList list_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(2, list_.size());
        assertEq(MIMIQUE,list_.get(0));
        assertEq(JACKPOT,list_.get(1));
        assertEq(JACKPOT, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getLastUsedMove());
        assertEq(JACKPOT, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertTrue(fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processInvokingMove3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = processInvokingMove(diff_, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(BOOST);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        StringList list_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(1, list_.size());
        assertEq(MIMIQUE,list_.get(0));
        assertEq(MIMIQUE, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getLastUsedMove());
        assertEq(MIMIQUE, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertTrue(!fight_.isInvokedMove());
        assertTrue(!fight_.isSuccessfulInvokation());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processInvokingMove4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = processInvokingMove(diff_, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ASSISTANCE_BIS, POKEMON_PLAYER_TARGET_ONE);
        FightRound.initRound(fight_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        StringList list_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(1, list_.size());
        assertEq(ASSISTANCE_BIS,list_.get(0));
        assertEq(ASSISTANCE_BIS, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getLastUsedMove());
        assertEq(ASSISTANCE_BIS, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertTrue(!fight_.isInvokedMove());
        assertTrue(!fight_.isSuccessfulInvokation());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processInvokingMove1SimulationTest() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = processInvokingMove(diff_, (byte) 1);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(JACKPOT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(METRONOME, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        StringList list_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(1, list_.size());
        assertEq(METRONOME,list_.get(0));
        assertEq(METRONOME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getLastUsedMove());
        assertEq(METRONOME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertTrue(!fight_.isInvokedMove());
        assertTrue(fight_.isSuccessfulInvokation());
        assertTrue(!fight_.getAcceptableChoices());
    }
}
