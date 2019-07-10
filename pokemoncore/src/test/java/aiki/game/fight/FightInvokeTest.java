package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
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
        assertTrue(StringList.contains(list_, SEISME));
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
        assertTrue(StringList.contains(list_, IMPLORE));
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
        assertTrue(StringList.contains(list_, IMPLORE));
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
        assertTrue(StringList.contains(list_, GLAS_DE_SOIN));
        assertTrue(StringList.contains(list_, INTERVERSION));
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
        assertTrue(StringList.contains(list_, IMPLORE));
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
        assertTrue(StringList.contains(list_, BUEE_NOIRE));
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
        assertTrue(StringList.contains(list_, MALEDICTION_2));
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
        assertTrue(StringList.contains(list_, BROUHAHA));
        assertTrue(StringList.contains(list_, GLAS_DE_SOIN));
        assertTrue(StringList.contains(list_, INTERVERSION));
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
        assertEq(252, list_.size());
        assertTrue(StringList.contains(list_, ABIME));
        assertTrue(StringList.contains(list_, ACUPRESSION));
        assertTrue(StringList.contains(list_, ADAPTATION));
        assertTrue(StringList.contains(list_, AIDE));
        assertTrue(StringList.contains(list_, AIRE_DE_FEU));
        assertTrue(StringList.contains(list_, AIRE_D_EAU));
        assertTrue(StringList.contains(list_, AIRE_D_HERBE));
        assertTrue(StringList.contains(list_, AIR_VEINARD));
        assertTrue(StringList.contains(list_, ANNEAU_HYDRO));
        assertTrue(StringList.contains(list_, ANTI_AIR));
        assertTrue(StringList.contains(list_, ANTI_BRUME));
        assertTrue(StringList.contains(list_, ANTI_CROISEUR));
        assertTrue(StringList.contains(list_, ANTI_SOIN));
        assertTrue(StringList.contains(list_, ANTI_SOL));
        assertTrue(StringList.contains(list_, ARMURE));
        assertTrue(StringList.contains(list_, ASSISTANCE_BIS));
        assertTrue(StringList.contains(list_, ATTERRISSAGE));
        assertTrue(StringList.contains(list_, ATTRACTION));
        assertTrue(StringList.contains(list_, ATTRITION));
        assertTrue(StringList.contains(list_, BAILLEMENT));
        assertTrue(StringList.contains(list_, BAIN_DE_SMOG));
        assertTrue(StringList.contains(list_, BALANCE));
        assertTrue(StringList.contains(list_, BALLE_GRAINE));
        assertTrue(StringList.contains(list_, BALL_GLACE));
        assertTrue(StringList.contains(list_, BALL_METEO));
        assertTrue(StringList.contains(list_, BALL_OMBRE));
        assertTrue(StringList.contains(list_, BALL_ORAGE));
        assertTrue(StringList.contains(list_, BASTON));
        assertTrue(StringList.contains(list_, BATAILLE));
        assertTrue(StringList.contains(list_, BELIER));
        assertTrue(StringList.contains(list_, BERCEUSE));
        assertTrue(StringList.contains(list_, BOMBAIMANT));
        assertTrue(StringList.contains(list_, BOOST));
        assertTrue(StringList.contains(list_, BOUCLIER_ROYAL));
        assertTrue(StringList.contains(list_, BOUE_BOMBE));
        assertTrue(StringList.contains(list_, BOUL_ARMURE));
        assertTrue(StringList.contains(list_, BOURDON));
        assertTrue(StringList.contains(list_, BRUME));
        assertTrue(StringList.contains(list_, BUEE_NOIRE));
        assertTrue(StringList.contains(list_, BULLES_D_O));
        assertTrue(StringList.contains(list_, CAGE_ECLAIR));
        assertTrue(StringList.contains(list_, CALCINATION));
        assertTrue(StringList.contains(list_, CAMOUFLAGE));
        assertTrue(StringList.contains(list_, CASSE));
        assertTrue(StringList.contains(list_, CHAMP_BRUMEUX));
        assertTrue(StringList.contains(list_, CHANT));
        assertTrue(StringList.contains(list_, CHARGE));
        assertTrue(StringList.contains(list_, CHARGEUR));
        assertTrue(StringList.contains(list_, CHARME));
        assertTrue(StringList.contains(list_, CHOC_MENTAL));
        assertTrue(StringList.contains(list_, CHOC_PSY));
        assertTrue(StringList.contains(list_, CLAIRVOYANCE));
        assertTrue(StringList.contains(list_, CLONAGE));
        assertTrue(StringList.contains(list_, COGNOBIDON));
        assertTrue(StringList.contains(list_, COMBO_GRIFFE));
        assertTrue(StringList.contains(list_, CONVERSION_2));
        assertTrue(StringList.contains(list_, COPIE_TYPE));
        assertTrue(StringList.contains(list_, COUD_BOUE));
        assertTrue(StringList.contains(list_, COUD_KRANE));
        assertTrue(StringList.contains(list_, COUPE_VENT));
        assertTrue(StringList.contains(list_, COUP_D_BOULE));
        assertTrue(StringList.contains(list_, COUP_D_JUS));
        assertTrue(StringList.contains(list_, COUP_D_MAIN_2));
        assertTrue(StringList.contains(list_, CROC_FATAL));
        assertTrue(StringList.contains(list_, CROISSANCE));
        assertTrue(StringList.contains(list_, CRU_AILE));
        assertTrue(StringList.contains(list_, CYCLE_V));
        assertTrue(StringList.contains(list_, CYCLONE));
        assertTrue(StringList.contains(list_, DAMOCLES));
        assertTrue(StringList.contains(list_, DANSE_LAMES));
        assertTrue(StringList.contains(list_, DANSE_LUNE));
        assertTrue(StringList.contains(list_, DANSE_PLUIE));
        assertTrue(StringList.contains(list_, DANSE_PLUME));
        assertTrue(StringList.contains(list_, DARD_MORTEL));
        assertTrue(StringList.contains(list_, DEGOMMAGE));
        assertTrue(StringList.contains(list_, DELUGE_GLACIAL));
        assertTrue(StringList.contains(list_, DELUGE_PLASMIQUE));
        assertTrue(StringList.contains(list_, DEMI_TOUR));
        assertTrue(StringList.contains(list_, DEPIT));
        assertTrue(StringList.contains(list_, DESTRUCTION));
        assertTrue(StringList.contains(list_, DETEINTE));
        assertTrue(StringList.contains(list_, DETREMPAGE));
        assertTrue(StringList.contains(list_, DEVOREVE));
        assertTrue(StringList.contains(list_, DISTORSION));
        assertTrue(StringList.contains(list_, DODO));
        assertTrue(StringList.contains(list_, DODO_PETIT));
        assertTrue(StringList.contains(list_, DON_NATUREL));
        assertTrue(StringList.contains(list_, DOUBLE_PIED));
        assertTrue(StringList.contains(list_, DOUX_PARFUM));
        assertTrue(StringList.contains(list_, DRACO_RAGE));
        assertTrue(StringList.contains(list_, DYNAMOPOING));
        assertTrue(StringList.contains(list_, EBOULEMENT));
        assertTrue(StringList.contains(list_, EBULLITION));
        assertTrue(StringList.contains(list_, ECHANGE));
        assertTrue(StringList.contains(list_, ECHANGE_BIS));
        assertTrue(StringList.contains(list_, ECHANGE_PSY));
        assertTrue(StringList.contains(list_, ECHANGE_TYPE));
        assertTrue(StringList.contains(list_, ECHEC));
        assertTrue(StringList.contains(list_, ECLAIR));
        assertTrue(StringList.contains(list_, ECUME));
        assertTrue(StringList.contains(list_, EFFORT));
        assertTrue(StringList.contains(list_, ELECANON));
        assertTrue(StringList.contains(list_, ELECTRISATION));
        assertTrue(StringList.contains(list_, EMBARGO));
        assertTrue(StringList.contains(list_, EMPAL_KORNE));
        assertTrue(StringList.contains(list_, ENCORE));
        assertTrue(StringList.contains(list_, ENTRAVE));
        assertTrue(StringList.contains(list_, EXCUSE));
        assertTrue(StringList.contains(list_, FAUX_CHAGE));
        assertTrue(StringList.contains(list_, FLEAU));
        assertTrue(StringList.contains(list_, FONCE));
        assertTrue(StringList.contains(list_, FORCE_TYPE));
        assertTrue(StringList.contains(list_, FRAPPE_ATLAS));
        assertTrue(StringList.contains(list_, FULMIFER));
        assertTrue(StringList.contains(list_, GIGA_SANGSUE));
        assertTrue(StringList.contains(list_, GLACIATION));
        assertTrue(StringList.contains(list_, GRAVITE));
        assertTrue(StringList.contains(list_, GRIFFE));
        assertTrue(StringList.contains(list_, GRIFFE_ACIER));
        assertTrue(StringList.contains(list_, GRINCEMENT));
        assertTrue(StringList.contains(list_, GUILLOTINE));
        assertTrue(StringList.contains(list_, HATE));
        assertTrue(StringList.contains(list_, HYDROCANON));
        assertTrue(StringList.contains(list_, HYPNOSE));
        assertTrue(StringList.contains(list_, IMITATION));
        assertTrue(StringList.contains(list_, JACKPOT));
        assertTrue(StringList.contains(list_, JET_DE_SABLE));
        assertTrue(StringList.contains(list_, JET_PIERRES));
        assertTrue(StringList.contains(list_, LAME_DE_ROC));
        assertTrue(StringList.contains(list_, LAME_D_AIR));
        assertTrue(StringList.contains(list_, LANCECROU));
        assertTrue(StringList.contains(list_, LEVIKINESIE));
        assertTrue(StringList.contains(list_, LIRE_ESPRIT));
        assertTrue(StringList.contains(list_, LOTO));
        assertTrue(StringList.contains(list_, MALEDICTION));
        assertTrue(StringList.contains(list_, MALEDICTION_2));
        assertTrue(StringList.contains(list_, MALEFICE_SYLVAIN));
        assertTrue(StringList.contains(list_, MEGAPHONE));
        assertTrue(StringList.contains(list_, MUR_DE_FER));
        assertTrue(StringList.contains(list_, MUR_LUMIERE));
        assertTrue(StringList.contains(list_, NUEE_DE_POUDRE));
        assertTrue(StringList.contains(list_, OEIL_MIRACLE));
        assertTrue(StringList.contains(list_, OMBRE_PORTEE));
        assertTrue(StringList.contains(list_, ONDE_FOLIE));
        assertTrue(StringList.contains(list_, ONDE_VIDE));
        assertTrue(StringList.contains(list_, ORAGE));
        assertTrue(StringList.contains(list_, ORAGE_BIS));
        assertTrue(StringList.contains(list_, PARADOXE));
        assertTrue(StringList.contains(list_, PARTAGE_GARDE));
        assertTrue(StringList.contains(list_, PERENITE));
        assertTrue(StringList.contains(list_, PERENITE_BIS));
        assertTrue(StringList.contains(list_, PERMUCOEUR));
        assertTrue(StringList.contains(list_, PICORE));
        assertTrue(StringList.contains(list_, PICOTS));
        assertTrue(StringList.contains(list_, PICOTS_BIS));
        assertTrue(StringList.contains(list_, PICO_DEFENSE));
        assertTrue(StringList.contains(list_, PICS_TOXIK));
        assertTrue(StringList.contains(list_, PIEGE_DE_ROC));
        assertTrue(StringList.contains(list_, PINCE_MASSE));
        assertTrue(StringList.contains(list_, PIQURE));
        assertTrue(StringList.contains(list_, PISTOLET_A_O));
        assertTrue(StringList.contains(list_, PLAIE_CROIX));
        assertTrue(StringList.contains(list_, PLANNEUR));
        assertTrue(StringList.contains(list_, PLAQUAGE));
        assertTrue(StringList.contains(list_, PLONGEE));
        assertTrue(StringList.contains(list_, POINGLACE));
        assertTrue(StringList.contains(list_, POISSE));
        assertTrue(StringList.contains(list_, POSSESSIF));
        assertTrue(StringList.contains(list_, POUDRE_TOXIK));
        assertTrue(StringList.contains(list_, POURSUITE));
        assertTrue(StringList.contains(list_, POUV_ANTIQUE));
        assertTrue(StringList.contains(list_, PRESCIENCE));
        assertTrue(StringList.contains(list_, PROTECTION));
        assertTrue(StringList.contains(list_, PROVOC));
        assertTrue(StringList.contains(list_, PSYKO));
        assertTrue(StringList.contains(list_, PSYKOUD_BOUL));
        assertTrue(StringList.contains(list_, PUIS_CACHEE));
        assertTrue(StringList.contains(list_, RACINES));
        assertTrue(StringList.contains(list_, RAFALE_PSY));
        assertTrue(StringList.contains(list_, RAYON_GEMME));
        assertTrue(StringList.contains(list_, RAYON_LUNE));
        assertTrue(StringList.contains(list_, RAYON_SIGNAL));
        assertTrue(StringList.contains(list_, RAYON_SIMPLE));
        assertTrue(StringList.contains(list_, RAYON_UV));
        assertTrue(StringList.contains(list_, REBOND));
        assertTrue(StringList.contains(list_, REBONDIFEU));
        assertTrue(StringList.contains(list_, RECYCLAGE));
        assertTrue(StringList.contains(list_, REFLET));
        assertTrue(StringList.contains(list_, REGARD_NOIR));
        assertTrue(StringList.contains(list_, REGENERATION));
        assertTrue(StringList.contains(list_, RELACHE));
        assertTrue(StringList.contains(list_, RELAIS));
        assertTrue(StringList.contains(list_, REPOS));
        assertTrue(StringList.contains(list_, REQUIEM));
        assertTrue(StringList.contains(list_, REVEIL_FORCE));
        assertTrue(StringList.contains(list_, RISQUE));
        assertTrue(StringList.contains(list_, ROC_BOULET));
        assertTrue(StringList.contains(list_, ROUE_DE_FEU));
        assertTrue(StringList.contains(list_, ROULADE));
        assertTrue(StringList.contains(list_, RUNE_PROTECT));
        assertTrue(StringList.contains(list_, SACRIFICE));
        assertTrue(StringList.contains(list_, SEDUCTION));
        assertTrue(StringList.contains(list_, SEISME));
        assertTrue(StringList.contains(list_, SIPHON));
        assertTrue(StringList.contains(list_, SOIN));
        assertTrue(StringList.contains(list_, SONICBOOM));
        assertTrue(StringList.contains(list_, STOCKAGE));
        assertTrue(StringList.contains(list_, SURF));
        assertTrue(StringList.contains(list_, TAILLADE));
        assertTrue(StringList.contains(list_, TATAMIGAESHI));
        assertTrue(StringList.contains(list_, TELLURIFORCE));
        assertTrue(StringList.contains(list_, TEMPETESABLE));
        assertTrue(StringList.contains(list_, TEMPETEVERTE));
        assertTrue(StringList.contains(list_, TEN_DANSE));
        assertTrue(StringList.contains(list_, TIPHON));
        assertTrue(StringList.contains(list_, TIR_DE_BOUE));
        assertTrue(StringList.contains(list_, TOILE_GLUANTE));
        assertTrue(StringList.contains(list_, TONNERRE));
        assertTrue(StringList.contains(list_, TORGNOLES));
        assertTrue(StringList.contains(list_, TORNADE));
        assertTrue(StringList.contains(list_, TOURMENTE));
        assertTrue(StringList.contains(list_, TOURNIQUET));
        assertTrue(StringList.contains(list_, TOUR_RAPIDE));
        assertTrue(StringList.contains(list_, TRANCHE));
        assertTrue(StringList.contains(list_, TRANCHE_NUIT));
        assertTrue(StringList.contains(list_, TRANCH_HERBE));
        assertTrue(StringList.contains(list_, TREMPETTE));
        assertTrue(StringList.contains(list_, TROU));
        assertTrue(StringList.contains(list_, TROU_BIS));
        assertTrue(StringList.contains(list_, TUNNEL));
        assertTrue(StringList.contains(list_, ULTIMAPOING));
        assertTrue(StringList.contains(list_, ULTIMAWASHI));
        assertTrue(StringList.contains(list_, ULTRASON));
        assertTrue(StringList.contains(list_, UPPERCUT));
        assertTrue(StringList.contains(list_, VAGUE_PSY));
        assertTrue(StringList.contains(list_, VAMPIPOING));
        assertTrue(StringList.contains(list_, VAMPIRISME));
        assertTrue(StringList.contains(list_, VANTARDISE));
        assertTrue(StringList.contains(list_, VAS_Y));
        assertTrue(StringList.contains(list_, VENT_ARGENTE));
        assertTrue(StringList.contains(list_, VENT_ARRIERE));
        assertTrue(StringList.contains(list_, VENT_ARRIERE_BIS));
        assertTrue(StringList.contains(list_, VENT_GLACE));
        assertTrue(StringList.contains(list_, VIBRAQUA));
        assertTrue(StringList.contains(list_, VIGILANCE));
        assertTrue(StringList.contains(list_, VIVE_ATTAQUE));
        assertTrue(StringList.contains(list_, VOEU));
        assertTrue(StringList.contains(list_, ENVOL));
        assertTrue(StringList.contains(list_, VOL_MAGNETIK));
        assertTrue(StringList.contains(list_, ZENITH));
        assertTrue(StringList.contains(list_, ZONE_ETRANGE));
        assertTrue(StringList.contains(list_, ZONE_MAGIQUE));
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
        assertTrue(StringList.contains(list_, TOUR_RAPIDE));
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
        assertTrue(StringList.contains(list_, IMPLORE));
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
        assertTrue(StringList.contains(list_, IMPLORE));
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
        assertTrue(StringList.contains(invokedMoves_, JACKPOT));
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
        assertEq(253, FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, effet_, _data_).size());
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
