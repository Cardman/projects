package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import org.junit.Before;
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

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    private Fight invokableMoves() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        return fight_;
    }

    @Test
    public void invokableMoves1Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(FORCE_NATURE, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FORCE_NATURE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(FORCE_NATURE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, SEISME));
    }

    @Test
    public void invokableMoves2Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MIMIQUE, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(MOI_D_ABORD);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, IMPLORE));
    }

    @Test
    public void invokableMoves3Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MIMIQUE, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(IMPLORE);
        MoveData move_ = data.getMove(MIMIQUE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, IMPLORE));
    }

    @Test
    public void invokableMoves4Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MOI_D_ABORD, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MOI_D_ABORD, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(MOI_D_ABORD);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(0, list_.size());
    }

    @Test
    public void invokableMoves5Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(BLABLA_DODO, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(BLABLA_DODO, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(BLABLA_DODO);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(2, list_.size());
        assertTrue(StringUtil.contains(list_, GLAS_DE_SOIN));
        assertTrue(StringUtil.contains(list_, INTERVERSION));
    }

    @Test
    public void invokableMoves6Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(PHOTOCOPIE, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PHOTOCOPIE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).successUsingMove();
        MoveData move_ = data.getMove(PHOTOCOPIE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, IMPLORE));
    }

    @Test
    public void invokableMoves7Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(PHOTOCOPIE, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PHOTOCOPIE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(PHOTOCOPIE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(0, list_.size());
    }

    @Test
    public void invokableMoves8Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MIMIQUE, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(MIMIQUE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(0, list_.size());
    }

    @Test
    public void invokableMoves9Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MALEDICTION, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MALEDICTION, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(NORMAL);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(MALEDICTION);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, BUEE_NOIRE));
    }

    @Test
    public void invokableMoves10Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(MALEDICTION, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MALEDICTION, POKEMON_FOE_TARGET_ZERO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(SPECTRE);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(MALEDICTION);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, MALEDICTION_2));
    }

    @Test
    public void invokableMoves11Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(ASSISTANCE, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(ASSISTANCE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(ASSISTANCE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(3, list_.size());
        assertTrue(StringUtil.contains(list_, BROUHAHA));
        assertTrue(StringUtil.contains(list_, GLAS_DE_SOIN));
        assertTrue(StringUtil.contains(list_, INTERVERSION));
    }

    @Test
    public void invokableMoves12Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(METRONOME, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(METRONOME, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(METRONOME);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(252, list_.size());
        assertTrue(StringUtil.contains(list_, ABIME));
        assertTrue(StringUtil.contains(list_, ACUPRESSION));
        assertTrue(StringUtil.contains(list_, ADAPTATION));
        assertTrue(StringUtil.contains(list_, AIDE));
        assertTrue(StringUtil.contains(list_, AIRE_DE_FEU));
        assertTrue(StringUtil.contains(list_, AIRE_D_EAU));
        assertTrue(StringUtil.contains(list_, AIRE_D_HERBE));
        assertTrue(StringUtil.contains(list_, AIR_VEINARD));
        assertTrue(StringUtil.contains(list_, ANNEAU_HYDRO));
        assertTrue(StringUtil.contains(list_, ANTI_AIR));
        assertTrue(StringUtil.contains(list_, ANTI_BRUME));
        assertTrue(StringUtil.contains(list_, ANTI_CROISEUR));
        assertTrue(StringUtil.contains(list_, ANTI_SOIN));
        assertTrue(StringUtil.contains(list_, ANTI_SOL));
        assertTrue(StringUtil.contains(list_, ARMURE));
        assertTrue(StringUtil.contains(list_, ASSISTANCE_BIS));
        assertTrue(StringUtil.contains(list_, ATTERRISSAGE));
        assertTrue(StringUtil.contains(list_, ATTRACTION));
        assertTrue(StringUtil.contains(list_, ATTRITION));
        assertTrue(StringUtil.contains(list_, BAILLEMENT));
        assertTrue(StringUtil.contains(list_, BAIN_DE_SMOG));
        assertTrue(StringUtil.contains(list_, BALANCE));
        assertTrue(StringUtil.contains(list_, BALLE_GRAINE));
        assertTrue(StringUtil.contains(list_, BALL_GLACE));
        assertTrue(StringUtil.contains(list_, BALL_METEO));
        assertTrue(StringUtil.contains(list_, BALL_OMBRE));
        assertTrue(StringUtil.contains(list_, BALL_ORAGE));
        assertTrue(StringUtil.contains(list_, BASTON));
        assertTrue(StringUtil.contains(list_, BATAILLE));
        assertTrue(StringUtil.contains(list_, BELIER));
        assertTrue(StringUtil.contains(list_, BERCEUSE));
        assertTrue(StringUtil.contains(list_, BOMBAIMANT));
        assertTrue(StringUtil.contains(list_, BOOST));
        assertTrue(StringUtil.contains(list_, BOUCLIER_ROYAL));
        assertTrue(StringUtil.contains(list_, BOUE_BOMBE));
        assertTrue(StringUtil.contains(list_, BOUL_ARMURE));
        assertTrue(StringUtil.contains(list_, BOURDON));
        assertTrue(StringUtil.contains(list_, BRUME));
        assertTrue(StringUtil.contains(list_, BUEE_NOIRE));
        assertTrue(StringUtil.contains(list_, BULLES_D_O));
        assertTrue(StringUtil.contains(list_, CAGE_ECLAIR));
        assertTrue(StringUtil.contains(list_, CALCINATION));
        assertTrue(StringUtil.contains(list_, CAMOUFLAGE));
        assertTrue(StringUtil.contains(list_, CASSE));
        assertTrue(StringUtil.contains(list_, CHAMP_BRUMEUX));
        assertTrue(StringUtil.contains(list_, CHANT));
        assertTrue(StringUtil.contains(list_, CHARGE));
        assertTrue(StringUtil.contains(list_, CHARGEUR));
        assertTrue(StringUtil.contains(list_, CHARME));
        assertTrue(StringUtil.contains(list_, CHOC_MENTAL));
        assertTrue(StringUtil.contains(list_, CHOC_PSY));
        assertTrue(StringUtil.contains(list_, CLAIRVOYANCE));
        assertTrue(StringUtil.contains(list_, CLONAGE));
        assertTrue(StringUtil.contains(list_, COGNOBIDON));
        assertTrue(StringUtil.contains(list_, COMBO_GRIFFE));
        assertTrue(StringUtil.contains(list_, CONVERSION_2));
        assertTrue(StringUtil.contains(list_, COPIE_TYPE));
        assertTrue(StringUtil.contains(list_, COUD_BOUE));
        assertTrue(StringUtil.contains(list_, COUD_KRANE));
        assertTrue(StringUtil.contains(list_, COUPE_VENT));
        assertTrue(StringUtil.contains(list_, COUP_D_BOULE));
        assertTrue(StringUtil.contains(list_, COUP_D_JUS));
        assertTrue(StringUtil.contains(list_, COUP_D_MAIN_2));
        assertTrue(StringUtil.contains(list_, CROC_FATAL));
        assertTrue(StringUtil.contains(list_, CROISSANCE));
        assertTrue(StringUtil.contains(list_, CRU_AILE));
        assertTrue(StringUtil.contains(list_, CYCLE_V));
        assertTrue(StringUtil.contains(list_, CYCLONE));
        assertTrue(StringUtil.contains(list_, DAMOCLES));
        assertTrue(StringUtil.contains(list_, DANSE_LAMES));
        assertTrue(StringUtil.contains(list_, DANSE_LUNE));
        assertTrue(StringUtil.contains(list_, DANSE_PLUIE));
        assertTrue(StringUtil.contains(list_, DANSE_PLUME));
        assertTrue(StringUtil.contains(list_, DARD_MORTEL));
        assertTrue(StringUtil.contains(list_, DEGOMMAGE));
        assertTrue(StringUtil.contains(list_, DELUGE_GLACIAL));
        assertTrue(StringUtil.contains(list_, DELUGE_PLASMIQUE));
        assertTrue(StringUtil.contains(list_, DEMI_TOUR));
        assertTrue(StringUtil.contains(list_, DEPIT));
        assertTrue(StringUtil.contains(list_, DESTRUCTION));
        assertTrue(StringUtil.contains(list_, DETEINTE));
        assertTrue(StringUtil.contains(list_, DETREMPAGE));
        assertTrue(StringUtil.contains(list_, DEVOREVE));
        assertTrue(StringUtil.contains(list_, DISTORSION));
        assertTrue(StringUtil.contains(list_, DODO));
        assertTrue(StringUtil.contains(list_, DODO_PETIT));
        assertTrue(StringUtil.contains(list_, DON_NATUREL));
        assertTrue(StringUtil.contains(list_, DOUBLE_PIED));
        assertTrue(StringUtil.contains(list_, DOUX_PARFUM));
        assertTrue(StringUtil.contains(list_, DRACO_RAGE));
        assertTrue(StringUtil.contains(list_, DYNAMOPOING));
        assertTrue(StringUtil.contains(list_, EBOULEMENT));
        assertTrue(StringUtil.contains(list_, EBULLITION));
        assertTrue(StringUtil.contains(list_, ECHANGE));
        assertTrue(StringUtil.contains(list_, ECHANGE_BIS));
        assertTrue(StringUtil.contains(list_, ECHANGE_PSY));
        assertTrue(StringUtil.contains(list_, ECHANGE_TYPE));
        assertTrue(StringUtil.contains(list_, ECHEC));
        assertTrue(StringUtil.contains(list_, ECLAIR));
        assertTrue(StringUtil.contains(list_, ECUME));
        assertTrue(StringUtil.contains(list_, EFFORT));
        assertTrue(StringUtil.contains(list_, ELECANON));
        assertTrue(StringUtil.contains(list_, ELECTRISATION));
        assertTrue(StringUtil.contains(list_, EMBARGO));
        assertTrue(StringUtil.contains(list_, EMPAL_KORNE));
        assertTrue(StringUtil.contains(list_, ENCORE));
        assertTrue(StringUtil.contains(list_, ENTRAVE));
        assertTrue(StringUtil.contains(list_, EXCUSE));
        assertTrue(StringUtil.contains(list_, FAUX_CHAGE));
        assertTrue(StringUtil.contains(list_, FLEAU));
        assertTrue(StringUtil.contains(list_, FONCE));
        assertTrue(StringUtil.contains(list_, FORCE_TYPE));
        assertTrue(StringUtil.contains(list_, FRAPPE_ATLAS));
        assertTrue(StringUtil.contains(list_, FULMIFER));
        assertTrue(StringUtil.contains(list_, GIGA_SANGSUE));
        assertTrue(StringUtil.contains(list_, GLACIATION));
        assertTrue(StringUtil.contains(list_, GRAVITE));
        assertTrue(StringUtil.contains(list_, GRIFFE));
        assertTrue(StringUtil.contains(list_, GRIFFE_ACIER));
        assertTrue(StringUtil.contains(list_, GRINCEMENT));
        assertTrue(StringUtil.contains(list_, GUILLOTINE));
        assertTrue(StringUtil.contains(list_, HATE));
        assertTrue(StringUtil.contains(list_, HYDROCANON));
        assertTrue(StringUtil.contains(list_, HYPNOSE));
        assertTrue(StringUtil.contains(list_, IMITATION));
        assertTrue(StringUtil.contains(list_, JACKPOT));
        assertTrue(StringUtil.contains(list_, JET_DE_SABLE));
        assertTrue(StringUtil.contains(list_, JET_PIERRES));
        assertTrue(StringUtil.contains(list_, LAME_DE_ROC));
        assertTrue(StringUtil.contains(list_, LAME_D_AIR));
        assertTrue(StringUtil.contains(list_, LANCECROU));
        assertTrue(StringUtil.contains(list_, LEVIKINESIE));
        assertTrue(StringUtil.contains(list_, LIRE_ESPRIT));
        assertTrue(StringUtil.contains(list_, LOTO));
        assertTrue(StringUtil.contains(list_, MALEDICTION));
        assertTrue(StringUtil.contains(list_, MALEDICTION_2));
        assertTrue(StringUtil.contains(list_, MALEFICE_SYLVAIN));
        assertTrue(StringUtil.contains(list_, MEGAPHONE));
        assertTrue(StringUtil.contains(list_, MUR_DE_FER));
        assertTrue(StringUtil.contains(list_, MUR_LUMIERE));
        assertTrue(StringUtil.contains(list_, NUEE_DE_POUDRE));
        assertTrue(StringUtil.contains(list_, OEIL_MIRACLE));
        assertTrue(StringUtil.contains(list_, OMBRE_PORTEE));
        assertTrue(StringUtil.contains(list_, ONDE_FOLIE));
        assertTrue(StringUtil.contains(list_, ONDE_VIDE));
        assertTrue(StringUtil.contains(list_, ORAGE));
        assertTrue(StringUtil.contains(list_, ORAGE_BIS));
        assertTrue(StringUtil.contains(list_, PARADOXE));
        assertTrue(StringUtil.contains(list_, PARTAGE_GARDE));
        assertTrue(StringUtil.contains(list_, PERENITE));
        assertTrue(StringUtil.contains(list_, PERENITE_BIS));
        assertTrue(StringUtil.contains(list_, PERMUCOEUR));
        assertTrue(StringUtil.contains(list_, PICORE));
        assertTrue(StringUtil.contains(list_, PICOTS));
        assertTrue(StringUtil.contains(list_, PICOTS_BIS));
        assertTrue(StringUtil.contains(list_, PICO_DEFENSE));
        assertTrue(StringUtil.contains(list_, PICS_TOXIK));
        assertTrue(StringUtil.contains(list_, PIEGE_DE_ROC));
        assertTrue(StringUtil.contains(list_, PINCE_MASSE));
        assertTrue(StringUtil.contains(list_, PIQURE));
        assertTrue(StringUtil.contains(list_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(list_, PLAIE_CROIX));
        assertTrue(StringUtil.contains(list_, PLANNEUR));
        assertTrue(StringUtil.contains(list_, PLAQUAGE));
        assertTrue(StringUtil.contains(list_, PLONGEE));
        assertTrue(StringUtil.contains(list_, POINGLACE));
        assertTrue(StringUtil.contains(list_, POISSE));
        assertTrue(StringUtil.contains(list_, POSSESSIF));
        assertTrue(StringUtil.contains(list_, POUDRE_TOXIK));
        assertTrue(StringUtil.contains(list_, POURSUITE));
        assertTrue(StringUtil.contains(list_, POUV_ANTIQUE));
        assertTrue(StringUtil.contains(list_, PRESCIENCE));
        assertTrue(StringUtil.contains(list_, PROTECTION));
        assertTrue(StringUtil.contains(list_, PROVOC));
        assertTrue(StringUtil.contains(list_, PSYKO));
        assertTrue(StringUtil.contains(list_, PSYKOUD_BOUL));
        assertTrue(StringUtil.contains(list_, PUIS_CACHEE));
        assertTrue(StringUtil.contains(list_, RACINES));
        assertTrue(StringUtil.contains(list_, RAFALE_PSY));
        assertTrue(StringUtil.contains(list_, RAYON_GEMME));
        assertTrue(StringUtil.contains(list_, RAYON_LUNE));
        assertTrue(StringUtil.contains(list_, RAYON_SIGNAL));
        assertTrue(StringUtil.contains(list_, RAYON_SIMPLE));
        assertTrue(StringUtil.contains(list_, RAYON_UV));
        assertTrue(StringUtil.contains(list_, REBOND));
        assertTrue(StringUtil.contains(list_, REBONDIFEU));
        assertTrue(StringUtil.contains(list_, RECYCLAGE));
        assertTrue(StringUtil.contains(list_, REFLET));
        assertTrue(StringUtil.contains(list_, REGARD_NOIR));
        assertTrue(StringUtil.contains(list_, REGENERATION));
        assertTrue(StringUtil.contains(list_, RELACHE));
        assertTrue(StringUtil.contains(list_, RELAIS));
        assertTrue(StringUtil.contains(list_, REPOS));
        assertTrue(StringUtil.contains(list_, REQUIEM));
        assertTrue(StringUtil.contains(list_, REVEIL_FORCE));
        assertTrue(StringUtil.contains(list_, RISQUE));
        assertTrue(StringUtil.contains(list_, ROC_BOULET));
        assertTrue(StringUtil.contains(list_, ROUE_DE_FEU));
        assertTrue(StringUtil.contains(list_, ROULADE));
        assertTrue(StringUtil.contains(list_, RUNE_PROTECT));
        assertTrue(StringUtil.contains(list_, SACRIFICE));
        assertTrue(StringUtil.contains(list_, SEDUCTION));
        assertTrue(StringUtil.contains(list_, SEISME));
        assertTrue(StringUtil.contains(list_, SIPHON));
        assertTrue(StringUtil.contains(list_, SOIN));
        assertTrue(StringUtil.contains(list_, SONICBOOM));
        assertTrue(StringUtil.contains(list_, STOCKAGE));
        assertTrue(StringUtil.contains(list_, SURF));
        assertTrue(StringUtil.contains(list_, TAILLADE));
        assertTrue(StringUtil.contains(list_, TATAMIGAESHI));
        assertTrue(StringUtil.contains(list_, TELLURIFORCE));
        assertTrue(StringUtil.contains(list_, TEMPETESABLE));
        assertTrue(StringUtil.contains(list_, TEMPETEVERTE));
        assertTrue(StringUtil.contains(list_, TEN_DANSE));
        assertTrue(StringUtil.contains(list_, TIPHON));
        assertTrue(StringUtil.contains(list_, TIR_DE_BOUE));
        assertTrue(StringUtil.contains(list_, TOILE_GLUANTE));
        assertTrue(StringUtil.contains(list_, TONNERRE));
        assertTrue(StringUtil.contains(list_, TORGNOLES));
        assertTrue(StringUtil.contains(list_, TORNADE));
        assertTrue(StringUtil.contains(list_, TOURMENTE));
        assertTrue(StringUtil.contains(list_, TOURNIQUET));
        assertTrue(StringUtil.contains(list_, TOUR_RAPIDE));
        assertTrue(StringUtil.contains(list_, TRANCHE));
        assertTrue(StringUtil.contains(list_, TRANCHE_NUIT));
        assertTrue(StringUtil.contains(list_, TRANCH_HERBE));
        assertTrue(StringUtil.contains(list_, TREMPETTE));
        assertTrue(StringUtil.contains(list_, TROU));
        assertTrue(StringUtil.contains(list_, TROU_BIS));
        assertTrue(StringUtil.contains(list_, TUNNEL));
        assertTrue(StringUtil.contains(list_, ULTIMAPOING));
        assertTrue(StringUtil.contains(list_, ULTIMAWASHI));
        assertTrue(StringUtil.contains(list_, ULTRASON));
        assertTrue(StringUtil.contains(list_, UPPERCUT));
        assertTrue(StringUtil.contains(list_, VAGUE_PSY));
        assertTrue(StringUtil.contains(list_, VAMPIPOING));
        assertTrue(StringUtil.contains(list_, VAMPIRISME));
        assertTrue(StringUtil.contains(list_, VANTARDISE));
        assertTrue(StringUtil.contains(list_, VAS_Y));
        assertTrue(StringUtil.contains(list_, VENT_ARGENTE));
        assertTrue(StringUtil.contains(list_, VENT_ARRIERE));
        assertTrue(StringUtil.contains(list_, VENT_ARRIERE_BIS));
        assertTrue(StringUtil.contains(list_, VENT_GLACE));
        assertTrue(StringUtil.contains(list_, VIBRAQUA));
        assertTrue(StringUtil.contains(list_, VIGILANCE));
        assertTrue(StringUtil.contains(list_, VIVE_ATTAQUE));
        assertTrue(StringUtil.contains(list_, VOEU));
        assertTrue(StringUtil.contains(list_, ENVOL));
        assertTrue(StringUtil.contains(list_, VOL_MAGNETIK));
        assertTrue(StringUtil.contains(list_, ZENITH));
        assertTrue(StringUtil.contains(list_, ZONE_ETRANGE));
        assertTrue(StringUtil.contains(list_, ZONE_MAGIQUE));
    }

    @Test
    public void invokableMoves13Test() {
        Fight fight_ = invokableMoves();
        fight_.setEnvType(EnvironmentType.DESERT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).apprendreAttaqueEcrasant(FORCE_NATURE, COPIE, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(FORCE_NATURE, POKEMON_FOE_TARGET_ZERO);
        fight_.enableGlobalMove(CHAMP_BRUMEUX);
        FightRound.initRound(fight_);
        MoveData move_ = data.getMove(FORCE_NATURE);
        EffectInvoke eff_ = (EffectInvoke) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, TOUR_RAPIDE));
    }

    private Fight copiableMoves() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        return fight_;
    }

    @Test
    public void copiableMoves1Test() {
        Fight fight_ = copiableMoves();
        MoveData move_ = data.getMove(COPIE);
        EffectCopyMove eff_ = (EffectCopyMove) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.copiableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(0, list_.size());
    }

    @Test
    public void copiableMoves2Test() {
        Fight fight_ = copiableMoves();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setLastUsedMove(IMPLORE);
        MoveData move_ = data.getMove(COPIE);
        EffectCopyMove eff_ = (EffectCopyMove) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.copiableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, IMPLORE));
    }

    @Test
    public void copiableMoves3Test() {
        Fight fight_ = copiableMoves();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).apprendreAttaqueEcrasant(IMPLORE, COPIE, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(IMPLORE);
        FightRound.initRound(fight_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setLastUsedMove(IMPLORE);
        MoveData move_ = data.getMove(GRIBOUILLE);
        EffectCopyMove eff_ = (EffectCopyMove) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.copiableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, IMPLORE));
    }

    @Test
    public void copiableMoves4Test() {
        Fight fight_ = copiableMoves();
        MoveData move_ = data.getMove(GRIBOUILLE);
        EffectCopyMove eff_ = (EffectCopyMove) move_.getEffet(move_.indexOfPrimaryEffect());
        StringList list_ = FightInvoke.copiableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, eff_, data);
        assertEq(0, list_.size());
    }

    @Test
    public void effectInvoke1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(JACKPOT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData fAttInvoque_ = data.getMove(MIMIQUE);
        EffectInvoke effet_=(EffectInvoke) fAttInvoque_.getEffet(IndexConstants.FIRST_INDEX);
        EqList<TeamPosition> list_ = FightOrder.targetsEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO,effet_,diff_,data);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(1, FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, effet_, data).size());
        assertTrue(FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO,MIMIQUE, IndexConstants.FIRST_INDEX,true,data).isSuccessful());
        FightInvoke.effectInvoke(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, effet_, data);
        StringList invokedMoves_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(1, invokedMoves_.size());
        assertTrue(StringUtil.contains(invokedMoves_, JACKPOT));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectInvoke1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setSimulation(true);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setLastSufferedMove(JACKPOT);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(METRONOME, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        MoveData fAttInvoque_ = data.getMove(METRONOME);
        EffectInvoke effet_=(EffectInvoke) fAttInvoque_.getEffet(IndexConstants.FIRST_INDEX);
        EqList<TeamPosition> list_ = FightOrder.targetsEffect(fight_,POKEMON_PLAYER_FIGHTER_ZERO,effet_,diff_,data);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(253, FightInvoke.invokableMoves(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, effet_, data).size());
        assertTrue(FightSuccess.successfulMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO,METRONOME, IndexConstants.FIRST_INDEX,true,data).isSuccessful());
        FightInvoke.effectInvoke(fight_,POKEMON_PLAYER_FIGHTER_ZERO,POKEMON_FOE_FIGHTER_ZERO, effet_, data);
        StringList invokedMoves_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAlreadyInvokedMovesRound();
        assertEq(0, invokedMoves_.size());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight processInvokingMove(Difficulty _diff, byte _mult) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
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
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void processInvokingMove1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = processInvokingMove(diff_, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightRound.initRound(fight_);
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
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
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
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
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
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
        FightInvoke.processInvokingMove(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
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
