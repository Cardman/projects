package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.UsesOfMove;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.ActionHealMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSimpleHeal;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.LevelExpPoints;
import aiki.game.fight.util.MovesAbilities;
import aiki.game.params.Difficulty;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;


public class FighterTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    @Test
    public void initCreature1Test() {
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        fighter_.initCreature(pokemon_);
        assertEq(PIKACHU, fighter_.getName());
        assertEq(PIKACHU, fighter_.getCurrentName());
        assertEq(PARATONNERRE, fighter_.getAbility());
        assertEq(PARATONNERRE, fighter_.getCurrentAbility());
        assertEq(MAGNET, fighter_.getItem());
        assertEq(Gender.NO_GENDER, fighter_.getGender());
        assertEq(Gender.NO_GENDER, fighter_.getCurrentGender());
        assertEq(3, fighter_.getLevel());
        assertEq(0, fighter_.getStatus().size());
        assertEq(0, fighter_.getStatusRelat().size());
        assertEq(0, fighter_.getIncrUserAccuracy().size());
        assertEq(0, fighter_.getTrackingMoves().size());
        assertEq(0, fighter_.getPrivateMoves().size());
        assertEq(0, fighter_.getTrappingMoves().size());
    }

    @Test
    public void initCreatureUser1Test() {
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.getStatus().add(GEL);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        fighter_.initCreatureUser(pokemonUser_, _data_);
        assertEq(SUPER_BALL, fighter_.getUsedBallCatching());
        assertEq(40, fighter_.getHappiness());
        assertEq(PIKA, fighter_.getNickname());
        assertEq(PIKACHU, fighter_.getName());
        assertEq(PIKACHU, fighter_.getCurrentName());
        assertEq(PARATONNERRE, fighter_.getAbility());
        assertEq(PARATONNERRE, fighter_.getCurrentAbility());
        assertEq(MAGNET, fighter_.getItem());
        assertEq(NULL_REF,fighter_.getExpItem());
        assertEq(Gender.NO_GENDER, fighter_.getGender());
        assertEq(Gender.NO_GENDER, fighter_.getCurrentGender());
        assertEq(3, fighter_.getLevel());
        assertEq(17, fighter_.getStatus().size());
//        assertEq(1, fighter_.getStatus().getKeys((short) 1).size());
        assertEq(1, fighter_.getNbStatusByRounds((short) 1));
        assertEq(1, fighter_.getStatusNbRound(GEL));
//        assertTrue(fighter_.getStatus().getKeys((short) 1).containsObj(GEL));
//        assertEq(16, fighter_.getStatus().getKeys((short) 0).size());
        assertEq(16, fighter_.getNbStatusByRounds((short) 0));
        assertEq(0, fighter_.getStatusNbRound(POISON_ST));
        assertEq(0, fighter_.getStatusNbRound(POISON_GRAVE));
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL_REPOS));
        assertEq(0, fighter_.getStatusNbRound(PEUR));
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(0, fighter_.getStatusNbRound(CONFUSION));
        assertEq(0, fighter_.getStatusNbRound(CRAME));
        assertEq(0, fighter_.getStatusNbRound(CRAME_BIS));
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE_FORTE));
        assertEq(0, fighter_.getStatusNbRound(ERE_GEL));
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertEq(0, fighter_.getStatusNbRound(TROUILLE));
        assertEq(0, fighter_.getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertEq(0, fighter_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(0, fighter_.getStatusRelat().size());
        assertEq(0, fighter_.getIncrUserAccuracy().size());
        assertEq(0, fighter_.getTrackingMoves().size());
        assertEq(0, fighter_.getPrivateMoves().size());
        assertEq(0, fighter_.getTrappingMoves().size());
        assertEq(new Rate("3/2"), fighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void initCreatureUser2Test() {
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(BRAC_MACHO);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.getStatus().add(GEL);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        fighter_.initCreatureUser(pokemonUser_, _data_);
        assertEq(SUPER_BALL, fighter_.getUsedBallCatching());
        assertEq(40, fighter_.getHappiness());
        assertEq(PIKA, fighter_.getNickname());
        assertEq(PIKACHU, fighter_.getName());
        assertEq(PIKACHU, fighter_.getCurrentName());
        assertEq(PARATONNERRE, fighter_.getAbility());
        assertEq(PARATONNERRE, fighter_.getCurrentAbility());
        assertEq(BRAC_MACHO, fighter_.getItem());
        assertEq(BRAC_MACHO,fighter_.getExpItem());
        assertEq(Gender.NO_GENDER, fighter_.getGender());
        assertEq(Gender.NO_GENDER, fighter_.getCurrentGender());
        assertEq(3, fighter_.getLevel());
        assertEq(17, fighter_.getStatus().size());
//        assertEq(1, fighter_.getStatus().getKeys((short) 1).size());
        assertEq(1, fighter_.getNbStatusByRounds((short) 1));
        assertEq(1, fighter_.getStatusNbRound(GEL));
//        assertTrue(fighter_.getStatus().getKeys((short) 1).containsObj(GEL));
//        assertEq(16, fighter_.getStatus().getKeys((short) 0).size());
        assertEq(16, fighter_.getNbStatusByRounds((short) 0));
        assertEq(0, fighter_.getStatusNbRound(POISON_ST));
        assertEq(0, fighter_.getStatusNbRound(POISON_GRAVE));
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL_REPOS));
        assertEq(0, fighter_.getStatusNbRound(PEUR));
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(0, fighter_.getStatusNbRound(CONFUSION));
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE_FORTE));
        assertEq(0, fighter_.getStatusNbRound(ERE_GEL));
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertEq(0, fighter_.getStatusNbRound(TROUILLE));
        assertEq(0, fighter_.getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertEq(0, fighter_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(0, fighter_.getStatusNbRound(CRAME));
        assertEq(0, fighter_.getStatusNbRound(CRAME_BIS));
        assertEq(0, fighter_.getStatusRelat().size());
        assertEq(0, fighter_.getIncrUserAccuracy().size());
        assertEq(0, fighter_.getTrackingMoves().size());
        assertEq(0, fighter_.getPrivateMoves().size());
        assertEq(0, fighter_.getTrappingMoves().size());
        assertEq(new Rate("3/2"), fighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void initCreatureNonUser1Test() {
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        fighter_.initCreatureNonUser(pokemon_, _data_);
        assertEq(70, fighter_.getHappiness());
        assertEq(PIKACHU, fighter_.getNickname());
        assertEq(PIKACHU, fighter_.getName());
        assertEq(PIKACHU, fighter_.getCurrentName());
        assertEq(PARATONNERRE, fighter_.getAbility());
        assertEq(PARATONNERRE, fighter_.getCurrentAbility());
        assertEq(MAGNET, fighter_.getItem());
        assertEq(NULL_REF,fighter_.getExpItem());
        assertEq(Gender.NO_GENDER, fighter_.getGender());
        assertEq(Gender.NO_GENDER, fighter_.getCurrentGender());
        assertEq(3, fighter_.getLevel());
        assertEq(17, fighter_.getStatus().size());
//        assertEq(0, fighter_.getStatus().getKeys((short) 1).size());
//        assertEq(17, fighter_.getStatus().getKeys((short) 0).size());
        assertEq(0, fighter_.getNbStatusByRounds((short) 1));
        assertEq(17, fighter_.getNbStatusByRounds((short) 0));
        assertEq(0, fighter_.getStatusNbRound(GEL));
        assertEq(0, fighter_.getStatusNbRound(CRAME));
        assertEq(0, fighter_.getStatusNbRound(CRAME_BIS));
        assertEq(0, fighter_.getStatusNbRound(POISON_ST));
        assertEq(0, fighter_.getStatusNbRound(POISON_GRAVE));
        assertEq(0, fighter_.getStatusNbRound(PEUR));
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertEq(0, fighter_.getStatusNbRound(CONFUSION));
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL_REPOS));
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE_FORTE));
        assertEq(0, fighter_.getStatusNbRound(ERE_GEL));
        assertEq(0, fighter_.getStatusNbRound(FEU_GEL));
        assertEq(0, fighter_.getStatusNbRound(TROUILLE));
        assertEq(0, fighter_.getStatusNbRound(LONGUE_CONFUSION_SANS_DOMMAGE));
        assertEq(0, fighter_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(0, fighter_.getStatusRelat().size());
        assertEq(0, fighter_.getIncrUserAccuracy().size());
        assertEq(0, fighter_.getTrackingMoves().size());
        assertEq(0, fighter_.getPrivateMoves().size());
        assertEq(0, fighter_.getTrappingMoves().size());
        assertEq(0, fighter_.getMoves().size());
        assertEq(0, fighter_.getCurrentMoves().size());
        assertEq(Rate.zero(), fighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void initUserMoves1Test() {
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        fighter_.initUserMoves(pokemonUser_);
        assertEq(2, fighter_.getMoves().size());
        assertEq(2, fighter_.getCurrentMoves().size());
        assertTrue(fighter_.getMoves().contains(JACKPOT));
        assertTrue(fighter_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(fighter_.getCurrentMoves().contains(JACKPOT));
        assertTrue(fighter_.getCurrentMoves().contains(OEIL_MIRACLE));
        UsesOfMove uses_ = fighter_.getMoves().getVal(JACKPOT);
        assertEq(20, uses_.getCurrent());
        assertEq(20, uses_.getMax());
        uses_ = fighter_.getCurrentMoves().getVal(JACKPOT);
        assertEq(20, uses_.getCurrent());
        assertEq(20, uses_.getMax());
        uses_ = fighter_.getMoves().getVal(OEIL_MIRACLE);
        assertEq(0, uses_.getCurrent());
        assertEq(40, uses_.getMax());
        uses_ = fighter_.getCurrentMoves().getVal(OEIL_MIRACLE);
        assertEq(0, uses_.getCurrent());
        assertEq(40, uses_.getMax());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
        assertEq(0, fighter_.getMovesAbilitiesEvos().size());
    }

    @Test
    public void initPokemonTrainerMoves1Test() {
        Fighter fighter_ = new Fighter();
        PkTrainer pokemon_ = new PkTrainer();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        pokemon_.setMoves(new StringList(JACKPOT));
        fighter_.initCreatureNonUser(pokemon_, _data_);
        fighter_.initPokemonTrainerMoves(pokemon_, _data_);
        assertEq(1, fighter_.getMoves().size());
        assertEq(1, fighter_.getCurrentMoves().size());
        assertTrue(fighter_.getMoves().contains(JACKPOT));
        assertTrue(fighter_.getCurrentMoves().contains(JACKPOT));
        UsesOfMove uses_ = fighter_.getMoves().getVal(JACKPOT);
        assertEq(20, uses_.getCurrent());
        assertEq(20, uses_.getMax());
        uses_ = fighter_.getCurrentMoves().getVal(JACKPOT);
        assertEq(20, uses_.getCurrent());
        assertEq(20, uses_.getMax());
    }

    @Test
    public void initWildPokemonMoves1Test() {
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        fighter_.initCreatureNonUser(pokemon_, _data_);
        fighter_.initWildPokemonMoves(_data_);
        assertEq(2, fighter_.getMoves().size());
        assertEq(2, fighter_.getCurrentMoves().size());
        assertTrue(fighter_.getMoves().contains(JACKPOT));
        assertTrue(fighter_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(fighter_.getCurrentMoves().contains(JACKPOT));
        assertTrue(fighter_.getCurrentMoves().contains(OEIL_MIRACLE));
        UsesOfMove uses_ = fighter_.getMoves().getVal(JACKPOT);
        assertEq(20, uses_.getCurrent());
        assertEq(20, uses_.getMax());
        uses_ = fighter_.getCurrentMoves().getVal(JACKPOT);
        assertEq(20, uses_.getCurrent());
        assertEq(20, uses_.getMax());
        uses_ = fighter_.getMoves().getVal(OEIL_MIRACLE);
        assertEq(40, uses_.getCurrent());
        assertEq(40, uses_.getMax());
        uses_ = fighter_.getCurrentMoves().getVal(OEIL_MIRACLE);
        assertEq(40, uses_.getCurrent());
        assertEq(40, uses_.getMax());
    }

    @Test
    public void initEvIvUser1Test() {
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        fighter_.initCreatureUser(pokemonUser_, _data_);
        fighter_.initUserMoves(pokemonUser_);
        fighter_.initEvIvUser(pokemonUser_);
        assertEq(6, fighter_.getEv().size());
        assertEq(1, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(31, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(31, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(31, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(31, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(31, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(31, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(new Rate("1873/100"), pokemonUser_.getRemainingHp());
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
    }

    @Test
    public void initEvIvOther1Test() {
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        fighter_.initCreatureNonUser(pokemon_, _data_);
        fighter_.initWildPokemonMoves(_data_);
        fighter_.initEvIvOther(_data_);
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(0, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(new Rate("89/5"), fighter_.getRemainingHp());
    }

    @Test
    public void initCreatureGeneral1Test() {
        StringList types_;
        Fighter fighter_ = new Fighter();
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        fighter_.initCreatureUser(pokemonUser_, _data_);
        fighter_.initUserMoves(pokemonUser_);
        fighter_.initEvIvUser(pokemonUser_);
        fighter_.initCreatureGeneral(_data_);
        assertEq(new Rate("50"), fighter_.getStatisBase().getVal(Statistic.ATTACK));
        assertEq(new Rate("143/16"), fighter_.statistiqueGlobaleEvIv(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.DEFENSE).intValue());
        assertEq(1, fighter_.getTypes().size());
        assertTrue(fighter_.getTypes().containsObj(ELECTRIQUE));
        assertEq(new Rate("3"), fighter_.getWeight());
        assertEq(new Rate("1/10"), fighter_.getHeight());
        assertEq(Rate.zero(), fighter_.getClone());
        assertEq(LgInt.zero(), fighter_.getNbRounds());
        assertEq(Rate.zero(), fighter_.getWonExp());
        types_ = new StringList(fighter_.getDamageRateInflictedByType().getKeys());
        assertEq(18, types_.size());
        assertEq(_data_.getTypes().size(), types_.size());
        types_.sort();
        assertEq(ACIER,types_.get(0));
        assertEq(COMBAT,types_.get(1));
        assertEq(DRAGON,types_.get(2));
        assertEq(EAU,types_.get(3));
        assertEq(ELECTRIQUE,types_.get(4));
        assertEq(FEE,types_.get(5));
        assertEq(FEU,types_.get(6));
        assertEq(GLACE,types_.get(7));
        assertEq(INSECTE,types_.get(8));
        assertEq(NORMAL,types_.get(9));
        assertEq(PLANTE,types_.get(10));
        assertEq(POISON,types_.get(11));
        assertEq(PSY,types_.get(12));
        assertEq(ROCHE,types_.get(13));
        assertEq(SOL,types_.get(14));
        assertEq(SPECTRE,types_.get(15));
        assertEq(TENEBRE,types_.get(16));
        assertEq(VOL,types_.get(17));
        assertEq(18, nbValues(fighter_.getDamageRateInflictedByType(), Rate.one()));
        types_ = new StringList(fighter_.getDamageRateSufferedByType().getKeys());
        assertEq(18, types_.size());
        assertEq(_data_.getTypes().size(), types_.size());
        types_.sort();
        assertEq(ACIER,types_.get(0));
        assertEq(COMBAT,types_.get(1));
        assertEq(DRAGON,types_.get(2));
        assertEq(EAU,types_.get(3));
        assertEq(ELECTRIQUE,types_.get(4));
        assertEq(FEE,types_.get(5));
        assertEq(FEU,types_.get(6));
        assertEq(GLACE,types_.get(7));
        assertEq(INSECTE,types_.get(8));
        assertEq(NORMAL,types_.get(9));
        assertEq(PLANTE,types_.get(10));
        assertEq(POISON,types_.get(11));
        assertEq(PSY,types_.get(12));
        assertEq(ROCHE,types_.get(13));
        assertEq(SOL,types_.get(14));
        assertEq(SPECTRE,types_.get(15));
        assertEq(TENEBRE,types_.get(16));
        assertEq(VOL,types_.get(17));
        assertEq(18, nbValues(fighter_.getDamageRateSufferedByType(), Rate.one()));
//        assertTrue(fighter_.getDamageRateInflictedByType().values().containsObj(DataBase.defRateProduct()));
//        assertTrue(fighter_.getDamageRateSufferedByType().values().containsObj(DataBase.defRateProduct()));
        assertEq(2, fighter_.getDamageSufferedCateg().size());
        assertEq(Rate.zero(), fighter_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), fighter_.getDamageSufferedCateg().getVal(SPECIALE));
        assertEq(2, fighter_.getDamageSufferedCategRound().size());
        assertEq(Rate.zero(), fighter_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(Rate.zero(), fighter_.getDamageSufferedCategRound().getVal(SPECIALE));
//        assertTrue(fighter_.getDamageSufferedCateg().contains(PHYSIQUE));
//        assertTrue(fighter_.getDamageSufferedCateg().contains(SPECIALE));
//        assertTrue(fighter_.getDamageSufferedCategRound().contains(PHYSIQUE));
//        assertTrue(fighter_.getDamageSufferedCategRound().contains(SPECIALE));
//        assertEq(2,fighter_.getDamageSufferedCateg().getKeys(Rate.zero()).size());
//        assertEq(2,fighter_.getDamageSufferedCategRound().getKeys(Rate.zero()).size());
        assertEq(7, fighter_.getNbUsesMoves().size());
        assertTrue(fighter_.getNbUsesMoves().contains(BOUL_ARMURE));
        assertTrue(fighter_.getNbUsesMoves().contains(STOCKAGE));
        assertTrue(fighter_.getNbUsesMoves().contains(TENACITE));
        assertTrue(fighter_.getNbUsesMoves().contains(PREVENTION));
        assertTrue(fighter_.getNbUsesMoves().contains(GARDE_LARGE));
        assertTrue(fighter_.getNbUsesMoves().contains(ABRI));
        assertTrue(fighter_.getNbUsesMoves().contains(DETECTION));
        assertEq(1, fighter_.getCopiedMoves().size());
        assertTrue(fighter_.getCopiedMoves().contains(COPIE));
        assertEq(NULL_REF, fighter_.getCopiedMoves().getVal(COPIE).getMove());
        assertEq(0, fighter_.getCopiedMoves().getVal(COPIE).getPp());
        assertEq(2, fighter_.getEnabledMovesForAlly().size());
        assertTrue(fighter_.getEnabledMovesForAlly().contains(COUP_D_MAIN));
        assertTrue(!fighter_.getEnabledMovesForAlly().getVal(COUP_D_MAIN));
        assertTrue(fighter_.getEnabledMovesForAlly().contains(AIDE));
        assertTrue(!fighter_.getEnabledMovesForAlly().getVal(AIDE));
        assertEq(4, fighter_.getEnabledMovesEndRound().size());
        assertTrue(fighter_.getEnabledMovesEndRound().contains(ANNEAU_HYDRO));
        assertTrue(fighter_.getEnabledMovesEndRound().contains(RACINES));
        assertTrue(fighter_.getEnabledMovesEndRound().contains(POISSE));
        assertTrue(fighter_.getEnabledMovesEndRound().contains(ANTI_CROISEUR));
        ActivityOfMove activity_ = fighter_.getEnabledMovesEndRound().getVal(ANNEAU_HYDRO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesEndRound().getVal(RACINES);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesEndRound().getVal(POISSE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesEndRound().getVal(ANTI_CROISEUR);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(6, fighter_.getEnabledMovesUnprot().size());
        assertTrue(fighter_.getEnabledMovesUnprot().contains(ANTI_AIR));
        assertTrue(fighter_.getEnabledMovesUnprot().contains(ANTI_CROISEUR));
        assertTrue(fighter_.getEnabledMovesUnprot().contains(RACINES));
        assertTrue(fighter_.getEnabledMovesUnprot().contains(ANTI_SOL));
        assertTrue(fighter_.getEnabledMovesUnprot().contains(CLAIRVOYANCE));
        assertTrue(fighter_.getEnabledMovesUnprot().contains(OEIL_MIRACLE));
        activity_ = fighter_.getEnabledMovesUnprot().getVal(ANTI_AIR);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesUnprot().getVal(ANTI_CROISEUR);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesUnprot().getVal(RACINES);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesUnprot().getVal(ANTI_SOL);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesUnprot().getVal(CLAIRVOYANCE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesUnprot().getVal(OEIL_MIRACLE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
        assertEq(4, fighter_.getEnabledMovesProt().size());
        assertTrue(fighter_.getEnabledMovesProt().contains(VOL_MAGNETIK));
        assertTrue(fighter_.getEnabledMovesProt().contains(LEVIKINESIE));
        assertTrue(fighter_.getEnabledMovesProt().contains(TROU));
        assertTrue(fighter_.getEnabledMovesProt().contains(TROU_BIS));
        activity_ = fighter_.getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesProt().getVal(LEVIKINESIE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesProt().getVal(TROU);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMovesProt().getVal(TROU_BIS);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(4, fighter_.getEnabledMoves().size());
        assertTrue(fighter_.getEnabledMoves().contains(PROVOC));
        assertTrue(fighter_.getEnabledMoves().contains(EMBARGO));
        assertTrue(fighter_.getEnabledMoves().contains(TOURMENTE));
        assertTrue(fighter_.getEnabledMoves().contains(CYCLE_V));
        activity_ = fighter_.getEnabledMoves().getVal(PROVOC);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMoves().getVal(EMBARGO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMoves().getVal(TOURMENTE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
        activity_ = fighter_.getEnabledMoves().getVal(CYCLE_V);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
        assertEq(1, fighter_.getEnabledMovesConstChoices().size());
        assertTrue(fighter_.getEnabledMovesConstChoices().contains(ROULADE));
        activity_ = fighter_.getEnabledMovesConstChoices().getVal(ROULADE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(3, fighter_.getEnabledChangingTypesMoves().size());
        assertTrue(fighter_.getEnabledChangingTypesMoves().contains(ELECTRISATION));
        assertTrue(fighter_.getEnabledChangingTypesMoves().contains(DELUGE_PLASMIQUE));
        assertTrue(fighter_.getEnabledChangingTypesMoves().contains(DELUGE_GLACIAL));
        activity_ = fighter_.getEnabledChangingTypesMoves().getVal(ELECTRISATION);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledChangingTypesMoves().getVal(DELUGE_PLASMIQUE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledChangingTypesMoves().getVal(DELUGE_GLACIAL);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(3, fighter_.getEnabledCounteringMoves().size());
        assertTrue(fighter_.getEnabledCounteringMoves().contains(NUEE_DE_POUDRE));
        assertTrue(fighter_.getEnabledCounteringMoves().contains(BOUCLIER_ROYAL));
        assertTrue(fighter_.getEnabledCounteringMoves().contains(PICO_DEFENSE));
        activity_ = fighter_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledCounteringMoves().getVal(BOUCLIER_ROYAL);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, fighter_.getAlreadyInvokedMovesRound().size());
        assertEq(NULL_REF, fighter_.getLastUsedItem());
        assertEq(NULL_REF, fighter_.getLastUsedMove());
        assertEq(NULL_REF, fighter_.getUsedMoveLastRound());
        assertEq(NULL_REF, fighter_.getLastSuccessfulMove());
        assertEq(0, fighter_.getLastSufferedMoveTypes().size());
    }

    private int nbValues(StringMap<Rate> _map, Rate _value) {
        int nb_ = 0;
        for (EntryCust<String,Rate> e: _map.entryList()) {
            if (e.getValue().eq(_value)) {
                nb_++;
            }
        }
        return nb_;
    }

    @Test
    public void new_Fighter_PokemonPlayer_DataBase_byte_1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
    }

    @Test
    public void new_Fighter_PokemonTrainer_DataBase_byte_1Test() {
        PkTrainer pokemon_ = new PkTrainer();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        pokemon_.setMoves(new StringList(JACKPOT));
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
    }

    @Test
    public void new_Fighter_Pokemon_DataBase_byte_1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
    }

    @Test
    public void ajouterRelationAutre1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        TeamPosition fighterCoords_ = new TeamPosition(Fight.PLAYER, (byte)0);
        fighter_.ajouterRelationAutre(fighterCoords_, _data_);
        assertEq(11, fighter_.getStatusRelat().size());
//        assertEq(11, fighter_.getStatusRelat().getKeys((short) 0).size());
        assertEq(11, fighter_.getNbStatusRelatByRounds((short) 0));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR_MOU,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(COUP_DE_BEC,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_BLANCHE,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_BLANCHE_BIS,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_NOIRE,fighterCoords_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE,fighterCoords_)));
        assertEq(3, fighter_.getTrackingMoves().size());
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(ENCORE,fighterCoords_)));
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(ENTRAVE,fighterCoords_)));
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(CHANT,fighterCoords_)));
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,fighterCoords_)).getMove());
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,fighterCoords_)).getMove());
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(CHANT,fighterCoords_)).getMove());
        ActivityOfMove activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,fighterCoords_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,fighterCoords_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(CHANT,fighterCoords_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, fighter_.getPrivateMoves().size());
        assertTrue(fighter_.getPrivateMoves().contains(new MoveTeamPosition(POSSESSIF,fighterCoords_)));
        assertEq(0, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,fighterCoords_)).size());
        assertEq(2, fighter_.getTrappingMoves().size());
        assertTrue(fighter_.getTrappingMoves().contains(new MoveTeamPosition(SIPHON,fighterCoords_)));
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,fighterCoords_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertTrue(fighter_.getTrappingMoves().contains(new MoveTeamPosition(TIPHON,fighterCoords_)));
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(TIPHON,fighterCoords_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(2, fighter_.getIncrUserAccuracy().size());
        assertTrue(fighter_.getIncrUserAccuracy().contains(new MoveTeamPosition(LIRE_ESPRIT,fighterCoords_)));
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,fighterCoords_)));
        assertTrue(fighter_.getIncrUserAccuracy().contains(new MoveTeamPosition(LEVIKINESIE,fighterCoords_)));
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LEVIKINESIE,fighterCoords_)));
    }

    @Test
    public void initCreatureRelationsAutre1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        TeamPosition fighterCoordsOne_ = new TeamPosition(Fight.PLAYER, (byte)0);
        TeamPosition fighterCoordsTwo_ = new TeamPosition(Fight.PLAYER, (byte)1);
        EqList<TeamPosition> fightersCoords_ = new EqList<TeamPosition>();
        fightersCoords_.add(fighterCoordsOne_);
        fightersCoords_.add(fighterCoordsTwo_);
        fighter_.initCreatureRelationsAutre(fightersCoords_, _data_);
        assertEq(22, fighter_.getStatusRelat().size());
//        assertEq(22, fighter_.getStatusRelat().getKeys((short) 0).size());
        assertEq(22, fighter_.getNbStatusRelatByRounds((short) 0));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR_MOU,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR_TRES_MOU,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(COUP_DE_BEC,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_BLANCHE,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_BLANCHE_BIS,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_NOIRE,fighterCoordsOne_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR_MOU,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR_TRES_MOU,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(AMOUR_FOU,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(CAUCHEMAR,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(PRISE_DE_TETE,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(COUP_DE_BEC,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_BLANCHE,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_BLANCHE_BIS,fighterCoordsTwo_)));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(NUIT_NOIRE,fighterCoordsTwo_)));
        assertEq(6, fighter_.getTrackingMoves().size());
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(ENCORE,fighterCoordsOne_)));
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(ENTRAVE,fighterCoordsOne_)));
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(CHANT,fighterCoordsOne_)));
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(ENCORE,fighterCoordsTwo_)));
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(ENTRAVE,fighterCoordsTwo_)));
        assertTrue(fighter_.getTrackingMoves().contains(new MoveTeamPosition(CHANT,fighterCoordsTwo_)));
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,fighterCoordsOne_)).getMove());
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,fighterCoordsOne_)).getMove());
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(CHANT,fighterCoordsOne_)).getMove());
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,fighterCoordsTwo_)).getMove());
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,fighterCoordsTwo_)).getMove());
        assertEq(NULL_REF,fighter_.getTrackingMoves().getVal(new MoveTeamPosition(CHANT,fighterCoordsTwo_)).getMove());
        ActivityOfMove activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,fighterCoordsOne_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,fighterCoordsOne_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(CHANT,fighterCoordsOne_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,fighterCoordsTwo_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,fighterCoordsTwo_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(CHANT,fighterCoordsTwo_)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(2, fighter_.getPrivateMoves().size());
        assertTrue(fighter_.getPrivateMoves().contains(new MoveTeamPosition(POSSESSIF,fighterCoordsOne_)));
        assertEq(0, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,fighterCoordsOne_)).size());
        assertTrue(fighter_.getPrivateMoves().contains(new MoveTeamPosition(POSSESSIF,fighterCoordsTwo_)));
        assertEq(0, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,fighterCoordsTwo_)).size());
        assertEq(4, fighter_.getTrappingMoves().size());
        assertTrue(fighter_.getTrappingMoves().contains(new MoveTeamPosition(SIPHON,fighterCoordsOne_)));
        assertTrue(fighter_.getTrappingMoves().contains(new MoveTeamPosition(SIPHON,fighterCoordsTwo_)));
        assertTrue(fighter_.getTrappingMoves().contains(new MoveTeamPosition(TIPHON,fighterCoordsOne_)));
        assertTrue(fighter_.getTrappingMoves().contains(new MoveTeamPosition(TIPHON,fighterCoordsTwo_)));
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,fighterCoordsOne_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,fighterCoordsTwo_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(TIPHON,fighterCoordsOne_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(TIPHON,fighterCoordsTwo_));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(4, fighter_.getIncrUserAccuracy().size());
        assertTrue(fighter_.getIncrUserAccuracy().contains(new MoveTeamPosition(LIRE_ESPRIT,fighterCoordsOne_)));
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,fighterCoordsTwo_)));
        assertTrue(fighter_.getIncrUserAccuracy().contains(new MoveTeamPosition(LIRE_ESPRIT,fighterCoordsOne_)));
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT,fighterCoordsTwo_)));
        assertTrue(fighter_.getIncrUserAccuracy().contains(new MoveTeamPosition(LEVIKINESIE,fighterCoordsOne_)));
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LEVIKINESIE,fighterCoordsTwo_)));
        assertTrue(fighter_.getIncrUserAccuracy().contains(new MoveTeamPosition(LEVIKINESIE,fighterCoordsOne_)));
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LEVIKINESIE,fighterCoordsTwo_)));
    }

    @Test
    public void initIvUt1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((short) 3);
        fighter_.initIvUt(diff_);
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(3, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.HP).intValue());
    }

    @Test
    public void initIvAdv1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        Difficulty diff_ = new Difficulty();
        diff_.setIvFoe((short) 3);
        fighter_.initIvAdv(diff_,HYPER_BALL);
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(3, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(HYPER_BALL, fighter_.getUsedBallCatching());
    }

    @Test
    public void initHp1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        Difficulty diff_ = new Difficulty();
        diff_.setIvFoe((short) 3);
        fighter_.initIvAdv(diff_,HYPER_BALL);
        fighter_.initHp();
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(3, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(HYPER_BALL, fighter_.getUsedBallCatching());
        assertEq(new Rate("89/5"), fighter_.getRemainingHp());
        assertEq(new Rate("1789/100"), fighter_.pvMax());
    }

    @Test
    public void initHp2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.getRemainingHp().affect(new Rate("100"));
        Difficulty diff_ = new Difficulty();
        diff_.setIvFoe((short) 3);
        fighter_.initIvAdv(diff_,HYPER_BALL);
        fighter_.initHp();
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(3, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(HYPER_BALL, fighter_.getUsedBallCatching());
        assertEq(new Rate("1789/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1789/100"), fighter_.pvMax());
    }

    @Test
    public void initHp3Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.getRemainingHp().affect(new Rate("1"));
        Difficulty diff_ = new Difficulty();
        diff_.setIvFoe((short) 3);
        fighter_.initIvAdv(diff_,HYPER_BALL);
        fighter_.initHp();
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(3, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(HYPER_BALL, fighter_.getUsedBallCatching());
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(new Rate("1789/100"), fighter_.pvMax());
    }

    @Test
    public void initHp4Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((short) 3);
        fighter_.initIvUt(diff_);
        fighter_.initHp();
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(3, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(new Rate("89/5"), fighter_.getRemainingHp());
        assertEq(new Rate("1789/100"), fighter_.pvMax());
    }

    @Test
    public void initHp5Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.getRemainingHp().affect(new Rate("100"));
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((short) 3);
        fighter_.initIvUt(diff_);
        fighter_.initHp();
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(3, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(new Rate("1789/100"), fighter_.getRemainingHp());
        assertEq(new Rate("1789/100"), fighter_.pvMax());
    }

    @Test
    public void initHp6Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.getRemainingHp().affect(new Rate("1"));
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((short) 3);
        fighter_.initIvUt(diff_);
        fighter_.initHp();
        assertEq(6, fighter_.getEv().size());
        assertEq(0, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.SPEED).intValue());
        assertEq(0, fighter_.getEv().getVal(Statistic.HP).intValue());
        assertEq(6, fighter_.getIv().size());
        assertEq(3, fighter_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(3, fighter_.getIv().getVal(Statistic.HP).intValue());
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(new Rate("1789/100"), fighter_.pvMax());
    }

    @Test
    public void apprendreAttaqueEcrasant1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(DETECTION, newMoveInfo_.getMove());
        assertEq(5, newMoveInfo_.getPp());
    }

    @Test
    public void apprendreAttaqueEcrasant2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, GRIBOUILLE, _data_);
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(NULL_REF, newMoveInfo_.getMove());
        assertEq(0, newMoveInfo_.getPp());
    }

    @Test
    public void apprendreAttaqueEcrasant3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, POUV_ANTIQUE, _data_);
        CopiedMove newMoveInfo_ = fighter_.getCopiedMoves().getVal(COPIE);
        assertEq(NULL_REF, newMoveInfo_.getMove());
        assertEq(0, newMoveInfo_.getPp());
    }

    @Test
    public void apprendreAttaqueEcrasantDef1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(GRIBOUILLE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChanged(false);
        fighter_.apprendreAttaqueEcrasantDef(DETECTION, GRIBOUILLE, _data_);
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(DETECTION));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(5, map_.getVal(DETECTION).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(DETECTION));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(5, map_.getVal(DETECTION).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
    }

    @Test
    public void apprendreAttaqueEcrasantDef2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(GRIBOUILLE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChanged(true);
        fighter_.apprendreAttaqueEcrasantDef(DETECTION, GRIBOUILLE, _data_);
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(GRIBOUILLE));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(GRIBOUILLE).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(DETECTION));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(5, map_.getVal(DETECTION).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
    }

    @Test
    public void apprendreAttaqueEcrasantDef3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(GRIBOUILLE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasantDef(DETECTION, COPIE, _data_);
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(GRIBOUILLE));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(GRIBOUILLE).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(GRIBOUILLE));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(GRIBOUILLE).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
    }

    @Test
    public void attaquesUtilisables1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        StringList list_ = fighter_.attaquesUtilisables();
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(DETECTION));
        assertTrue(list_.containsObj(BROUHAHA));
        assertTrue(list_.containsObj(POUV_ANTIQUE));
    }

    @Test
    public void attaquesUtilisables2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        fighter_.getCopiedMoves().getVal(COPIE).setPp((short) 0);
        StringList list_ = fighter_.attaquesUtilisables();
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(COPIE));
        assertTrue(list_.containsObj(BROUHAHA));
        assertTrue(list_.containsObj(POUV_ANTIQUE));
    }

    @Test
    public void powerPointsMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        assertEq(10, fighter_.powerPointsMove(BROUHAHA));
        assertEq(10, fighter_.powerPointsMove(POUV_ANTIQUE));
        assertEq(10, fighter_.powerPointsMove(COPIE));
        assertEq(5, fighter_.powerPointsMove(DETECTION));
        assertEq(0, fighter_.powerPointsMove(SEISME));
    }

    @Test
    public void powerPointsMove2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.getMoves().removeKey(COPIE);
        UsesOfMove uses_ = new UsesOfMove((short) 7,(short) 8);
        fighter_.getMoves().put(SEISME, uses_);
        assertEq(10, fighter_.powerPointsMove(BROUHAHA));
        assertEq(10, fighter_.powerPointsMove(POUV_ANTIQUE));
        assertEq(10, fighter_.powerPointsMove(COPIE));
        assertEq(0, fighter_.powerPointsMove(DETECTION));
        assertEq(7, fighter_.powerPointsMove(SEISME));
    }

    @Test
    public void maxPowerPointsMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        assertEq(10, fighter_.maxPowerPointsMove(BROUHAHA, _data_));
        assertEq(10, fighter_.maxPowerPointsMove(POUV_ANTIQUE, _data_));
        assertEq(10, fighter_.maxPowerPointsMove(COPIE, _data_));
        assertEq(5, fighter_.maxPowerPointsMove(DETECTION, _data_));
        assertEq(0, fighter_.maxPowerPointsMove(SEISME, _data_));
    }

    @Test
    public void maxPowerPointsMove2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.getMoves().removeKey(COPIE);
        UsesOfMove uses_ = new UsesOfMove((short) 7,(short) 8);
        fighter_.getMoves().put(SEISME, uses_);
        assertEq(10, fighter_.maxPowerPointsMove(BROUHAHA,_data_));
        assertEq(10, fighter_.maxPowerPointsMove(POUV_ANTIQUE,_data_));
        assertEq(10, fighter_.maxPowerPointsMove(COPIE,_data_));
        assertEq(0, fighter_.maxPowerPointsMove(DETECTION,_data_));
        assertEq(8, fighter_.maxPowerPointsMove(SEISME,_data_));
    }

    @Test
    public void usePowerPointsByMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        Difficulty diff_ = new Difficulty();
        diff_.setRestoredMovesEndFight(false);
        fighter_.usePowerPointsByMove(diff_, BROUHAHA, (short) 1);
        assertEq(9, fighter_.powerPointsMove(BROUHAHA));
        assertEq(9, fighter_.getMoves().getVal(BROUHAHA).getCurrent());
        assertEq(9, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
    }

    @Test
    public void usePowerPointsByMove2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        Difficulty diff_ = new Difficulty();
        diff_.setRestoredMovesEndFight(false);
        fighter_.usePowerPointsByMove(diff_, DETECTION, (short) 1);
        assertEq(4, fighter_.powerPointsMove(DETECTION));
        assertEq(10, fighter_.getMoves().getVal(COPIE).getCurrent());
        assertEq(10, fighter_.getCurrentMoves().getVal(COPIE).getCurrent());
    }

    @Test
    public void usePowerPointsByMove3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        Difficulty diff_ = new Difficulty();
        diff_.setRestoredMovesEndFight(false);
        fighter_.usePowerPointsByMove(diff_, DETECTION, (short) 6);
        assertTrue(!fighter_.attaquesUtilisables().containsObj(DETECTION));
    }

    @Test
    public void usePowerPointsByMove4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        Difficulty diff_ = new Difficulty();
        diff_.setRestoredMovesEndFight(false);
        fighter_.usePowerPointsByMove(diff_, BROUHAHA, (short) 11);
        assertEq(0, fighter_.powerPointsMove(BROUHAHA));
        assertEq(0, fighter_.getMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
    }

    @Test
    public void usePowerPointsByMove5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        Difficulty diff_ = new Difficulty();
        diff_.setRestoredMovesEndFight(true);
        fighter_.usePowerPointsByMove(diff_, BROUHAHA, (short) 1);
        assertEq(9, fighter_.powerPointsMove(BROUHAHA));
        assertEq(10, fighter_.getMoves().getVal(BROUHAHA).getCurrent());
        assertEq(9, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
    }

    @Test
    public void usePowerPointsByMove6Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        Difficulty diff_ = new Difficulty();
        diff_.setRestoredMovesEndFight(true);
        fighter_.usePowerPointsByMove(diff_, BROUHAHA, (short) 11);
        assertEq(0, fighter_.powerPointsMove(BROUHAHA));
        assertEq(10, fighter_.getMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
    }

    @Test
    public void usePowerPointsByMove7Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChanged(true);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        Difficulty diff_ = new Difficulty();
        diff_.setRestoredMovesEndFight(false);
        fighter_.usePowerPointsByMove(diff_, BROUHAHA, (short) 1);
        assertEq(9, fighter_.powerPointsMove(BROUHAHA));
        assertEq(10, fighter_.getMoves().getVal(BROUHAHA).getCurrent());
        assertEq(9, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
    }

    @Test
    public void usePowerPointsByMove8Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChanged(true);
        fighter_.apprendreAttaqueEcrasant(DETECTION, COPIE, _data_);
        Difficulty diff_ = new Difficulty();
        diff_.setRestoredMovesEndFight(false);
        fighter_.usePowerPointsByMove(diff_, BROUHAHA, (short) 11);
        assertEq(0, fighter_.powerPointsMove(BROUHAHA));
        assertEq(10, fighter_.getMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
    }

    @Test
    public void activerAttaque1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaque(PROVOC);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMoves().getVal(PROVOC);
        assertTrue(currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
    }

    @Test
    public void desactiverAttaque1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaque(PROVOC);
        fighter_.desactiverAttaque(PROVOC);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMoves().getVal(PROVOC);
        assertTrue(!currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
    }

    @Test
    public void activerAttaqueImmu1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueImmu(VOL_MAGNETIK, _data_);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertTrue(currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
        assertEq(1, fighter_.getProtectedAgainstMoveTypes().size());
        assertTrue(fighter_.getProtectedAgainstMoveTypes().containsObj(SOL));
    }

    @Test
    public void activerAttaqueImmu2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueImmu(TROU_BIS, _data_);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesProt().getVal(TROU_BIS);
        assertTrue(currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
        assertEq(1, fighter_.getProtectedAgainstMoveTypes().size());
        assertTrue(fighter_.getProtectedAgainstMoveTypes().containsObj(VOL));
    }

    @Test
    public void desactiverAttaqueImmu1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fighter_.desactiverAttaqueImmu(VOL_MAGNETIK, _data_);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertTrue(!currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
        assertEq(0, fighter_.getProtectedAgainstMoveTypes().size());
    }

    @Test
    public void desactiverAttaqueImmu2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.desactiverAttaqueImmu(VOL_MAGNETIK, _data_);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertTrue(!currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
        currentActivity_ = fighter_.getEnabledMovesProt().getVal(TROU);
        assertTrue(currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
        assertEq(1, fighter_.getProtectedAgainstMoveTypes().size());
        assertTrue(fighter_.getProtectedAgainstMoveTypes().containsObj(VOL));
    }

    @Test
    public void desactiverAttaqueImmu3Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueImmu(TROU_BIS, _data_);
        fighter_.activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fighter_.desactiverAttaqueImmu(VOL_MAGNETIK, _data_);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertTrue(!currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
        currentActivity_ = fighter_.getEnabledMovesProt().getVal(TROU_BIS);
        assertTrue(currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
        assertEq(1, fighter_.getProtectedAgainstMoveTypes().size());
        assertTrue(fighter_.getProtectedAgainstMoveTypes().containsObj(VOL));
    }

    @Test
    public void activerAttaqueAntiImmu1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueAntiImmu(OEIL_MIRACLE);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesUnprot().getVal(OEIL_MIRACLE);
        assertTrue(currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
    }

    @Test
    public void desactiverAttaqueAntiImmu1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueAntiImmu(OEIL_MIRACLE);
        fighter_.desactiverAttaqueAntiImmu(OEIL_MIRACLE);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesUnprot().getVal(OEIL_MIRACLE);
        assertTrue(!currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
    }

    @Test
    public void activerAttaqueBlocantLanceur1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesConstChoices().getVal(ROULADE);
        assertTrue(currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
    }

    @Test
    public void desactiverAttaqueBlocantLanceur1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.desactiverAttaqueBlocantLanceur(ROULADE);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesConstChoices().getVal(ROULADE);
        assertTrue(!currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
    }

    @Test
    public void activerAttaqueFinTourIndividuel1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesEndRound().getVal(RACINES);
        assertTrue(currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
    }

    @Test
    public void desactiverAttaqueFinTourIndividuel1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.desactiverAttaqueFinTourIndividuel(RACINES);
        ActivityOfMove currentActivity_ = fighter_.getEnabledMovesEndRound().getVal(RACINES);
        assertTrue(!currentActivity_.isEnabled());
        assertEq(0, currentActivity_.getNbTurn());
    }

    @Test
    public void formeNormale1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.setWeight(new Rate("4"));
        fighter_.setHeight(new Rate("1"));
        fighter_.setTypes(new StringList(EAU,FEU));
        fighter_.setClone(new Rate("2"));
        fighter_.activerAttaque(PROVOC);
        fighter_.activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fighter_.activerAttaqueAntiImmu(OEIL_MIRACLE);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.formeNormale(_data_);
        ActivityOfMove activity_ = fighter_.getEnabledMoves().getVal(PROVOC);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        activity_ = fighter_.getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        activity_ = fighter_.getEnabledMovesUnprot().getVal(OEIL_MIRACLE);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        activity_ = fighter_.getEnabledMovesConstChoices().getVal(ROULADE);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        activity_ = fighter_.getEnabledMovesEndRound().getVal(RACINES);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        assertEq(new Rate("3"), fighter_.getWeight());
        assertEq(new Rate("1/10"), fighter_.getHeight());
        assertEq(1, fighter_.getTypes().size());
        assertTrue(fighter_.getTypes().containsObj(ELECTRIQUE));
        assertEq(Rate.zero(), fighter_.getClone());
        assertTrue(!fighter_.isChanged());
    }

    @Test
    public void formeNormale2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.setWeight(new Rate("4"));
        fighter_.setHeight(new Rate("1"));
        fighter_.setTypes(new StringList(EAU,FEU));
        fighter_.setClone(new Rate("2"));
        fighter_.activerAttaque(PROVOC);
        fighter_.activerAttaqueImmu(VOL_MAGNETIK, _data_);
        fighter_.activerAttaqueAntiImmu(OEIL_MIRACLE);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.setChanged(true);
        fighter_.setCurrentName(ARTIKODIN);
        fighter_.setCurrentGender(Gender.NO_GENDER);
        fighter_.getCurrentMoves().clear();
        fighter_.getCurrentMoves().put(SEISME, new UsesOfMove((short) 5));
        fighter_.formeNormale(_data_);
        ActivityOfMove activity_ = fighter_.getEnabledMoves().getVal(PROVOC);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        activity_ = fighter_.getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        activity_ = fighter_.getEnabledMovesUnprot().getVal(OEIL_MIRACLE);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        activity_ = fighter_.getEnabledMovesConstChoices().getVal(ROULADE);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        activity_ = fighter_.getEnabledMovesEndRound().getVal(RACINES);
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        assertEq(new Rate("3"), fighter_.getWeight());
        assertEq(new Rate("1/10"), fighter_.getHeight());
        assertEq(1, fighter_.getTypes().size());
        assertTrue(fighter_.getTypes().containsObj(ELECTRIQUE));
        assertEq(Rate.zero(), fighter_.getClone());
        assertTrue(!fighter_.isChanged());
        assertEq(PIKACHU, fighter_.getCurrentName());
        assertEq(Gender.NO_GENDER, fighter_.getCurrentGender());
        assertEq(2, fighter_.getCurrentMoves().size());
        assertTrue(fighter_.getMoves().contains(JACKPOT));
        assertTrue(fighter_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(fighter_.getCurrentMoves().contains(JACKPOT));
        assertTrue(fighter_.getCurrentMoves().contains(OEIL_MIRACLE));
        UsesOfMove uses_ = fighter_.getMoves().getVal(JACKPOT);
        assertEq(20, uses_.getCurrent());
        assertEq(20, uses_.getMax());
        uses_ = fighter_.getCurrentMoves().getVal(JACKPOT);
        assertEq(20, uses_.getCurrent());
        assertEq(20, uses_.getMax());
        uses_ = fighter_.getMoves().getVal(OEIL_MIRACLE);
        assertEq(40, uses_.getCurrent());
        assertEq(40, uses_.getMax());
        uses_ = fighter_.getCurrentMoves().getVal(OEIL_MIRACLE);
        assertEq(40, uses_.getCurrent());
        assertEq(40, uses_.getMax());
    }

    @Test
    public void canDisableWeather1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        assertTrue(!fighter_.canDisableWeather(_data_));
    }

    @Test
    public void canDisableWeather2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte) 0);
        fighter_.setCurrentAbility(NULL_REF);
        assertTrue(!fighter_.canDisableWeather(_data_));
    }

    @Test
    public void canDisableWeather3Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(AIR_LOCK);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, Fighter.BACK);
        assertTrue(!fighter_.canDisableWeather(_data_));
    }

    @Test
    public void canDisableWeather4Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(TELECHARGE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        assertTrue(!fighter_.canDisableWeather(_data_));
    }

    @Test
    public void canDisableWeather5Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(AIR_LOCK);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        assertTrue(fighter_.canDisableWeather(_data_));
    }

    @Test
    public void disableAllStatusByEnabledWeather1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(ATTENTION);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(PEUR);
        fighter_.disableAllStatusByEnabledWeather(NULL_REF, _data_);
        assertEq(1, fighter_.getStatusNbRound(PEUR).intValue());
    }

    @Test
    public void disableAllStatusByEnabledWeather2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(GARDE_MAGIK);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        TeamPosition fighterCoordsOne_ = new TeamPosition(Fight.PLAYER, (byte)0);
        TeamPosition fighterCoordsTwo_ = new TeamPosition(Fight.PLAYER, (byte)1);
        EqList<TeamPosition> fightersCoords_ = new EqList<TeamPosition>();
        fightersCoords_.add(fighterCoordsOne_);
        fightersCoords_.add(fighterCoordsTwo_);
        fighter_.initCreatureRelationsAutre(fightersCoords_, _data_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterPseudoStatut(fighterCoordsTwo_, VAMPIGRAINE);
        fighter_.disableAllStatusByEnabledWeather(NULL_REF, _data_);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, fighterCoordsTwo_)).intValue());
    }

    @Test
    public void disableAllStatusByEnabledWeather3Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(FEUILLE_GARDE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.affecterStatut(PEUR);
        fighter_.disableAllStatusByEnabledWeather(ORAGE, _data_);
        assertEq(1, fighter_.getStatusNbRound(PEUR).intValue());
    }

    @Test
    public void disableAllStatusByEnabledWeather4Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(ATTENTION);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.affecterStatut(PEUR);
        fighter_.disableAllStatusByEnabledWeather(NULL_REF, _data_);
        assertEq(0, fighter_.getStatusNbRound(PEUR).intValue());
    }

    @Test
    public void disableAllStatusByEnabledWeather5Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(FEUILLE_PETITE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        TeamPosition fighterCoordsOne_ = new TeamPosition(Fight.FOE, (byte)0);
        EqList<TeamPosition> fightersCoords_ = new EqList<TeamPosition>();
        fightersCoords_.add(fighterCoordsOne_);
        fighter_.initCreatureRelationsAutre(fightersCoords_, _data_);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        fighter_.disableAllStatusByEnabledWeather(ZENITH, _data_);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)).intValue());
    }

    @Test
    public void getAddedTypesByEnabledWeather1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setCurrentAbility(NULL_REF);
        assertEq(0, fighter_.getAddedTypesByEnabledWeather(ZENITH, _data_).size());
    }

    @Test
    public void getAddedTypesByEnabledWeather2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(ATTENTION);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        assertEq(0, fighter_.getAddedTypesByEnabledWeather(ZENITH, _data_).size());
    }

    @Test
    public void getAddedTypesByEnabledWeather3Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        StringList list_ = fighter_.getAddedTypesByEnabledWeather(ZENITH, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(FEU));
    }

    @Test
    public void hasObjectEnabledBeingSent1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        assertTrue(!fighter_.hasObjectEnabledBeingSent(_data_));
    }

    @Test
    public void hasObjectEnabledBeingSent2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(HUILE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        assertTrue(!fighter_.hasObjectEnabledBeingSent(_data_));
    }

    @Test
    public void hasObjectEnabledBeingSent3Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(VIVE_GRIFFE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        assertTrue(!fighter_.hasObjectEnabledBeingSent(_data_));
    }

    @Test
    public void hasObjectEnabledBeingSent4Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        assertTrue(fighter_.hasObjectEnabledBeingSent(_data_));
    }

    @Test
    public void variationBoostStatistique1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 2);
        assertEq(2, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 1);
        assertEq(3, fighter_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void setFirstChosenMoveTarget1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(NULL_REF, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
    }

    @Test
    public void setFirstChosenMoveTargetSubstitute1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO, (byte) 1);
        AbstractAction action_ = fighter_.getAction();
        assertEq(DEMI_TOUR,((ActionMove)action_).getFirstChosenMove());
        assertEq(NULL_REF, ((ActionMove)action_).getFinalChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertTrue(((ActionMove)action_).getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(1, ((ActionMove)action_).getSubstitute());
    }

    @Test
    public void setFirstChosenMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setFirstChosenMove(BROUHAHA);
        AbstractAction action_ = fighter_.getAction();
        assertEq(BROUHAHA,((ActionMove)action_).getFirstChosenMove());
        assertEq(NULL_REF, ((ActionMove)action_).getFinalChosenMove());
        assertEq(0, ((ActionMove)action_).getChosenTargets().size());
    }

    @Test
    public void setChosenHealingObject1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObject(BAIE_ENIGMA, _data_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(BAIE_ENIGMA,((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
    }

    @Test
    public void setChosenHealingObject2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObject(CENDRESACREE, _data_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(CENDRESACREE,((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(((ActionSimpleHeal)action_).isTeam());
    }

    @Test
    public void setChosenHealingObject3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObject(EAU_FRAICHE, _data_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE,((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
    }

    @Test
    public void setChosenHealingObjectMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObjectMove(HUILE, DEMI_TOUR);
        AbstractAction action_ = fighter_.getAction();
        assertEq(DEMI_TOUR,((ActionHealMove)action_).getFirstChosenMove());
        assertEq(HUILE,((ActionHealMove)action_).getChosenHealingItem());
        assertTrue(!((ActionHealMove)action_).isTeam());
    }

    @Test
    public void setSubstitute1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstitute((byte) 1);
        AbstractAction action_ = fighter_.getAction();
        assertEq(1,((ActionSwitch)action_).getSubstitute());
    }

    @Test
    public void setSubstituteForMove1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstituteForMove((byte) 1);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_.isEmpty());
    }

    @Test
    public void setSubstituteForMove2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setAction(new ActionSwitch());
        fighter_.setSubstituteForMove((byte) 1);
        AbstractAction action_ = fighter_.getAction();
        assertEq(1,((ActionSwitch)action_).getSubstitute());
    }

    @Test
    public void setSubstituteForMove3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setAction(new ActionMove());
        fighter_.setSubstituteForMove((byte) 1);
        AbstractAction action_ = fighter_.getAction();
        assertEq(1,((ActionMove)action_).getSubstitute());
    }

    @Test
    public void cancelActions1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.cancelActions();
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_.isEmpty());
    }

    @Test
    public void cancelSubstituing1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.cancelSubstituing();
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_.isActionMove());
    }

    @Test
    public void cancelSubstituing2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setChosenHealingObject(EAU_FRAICHE, _data_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
    }

    @Test
    public void cancelSubstituing3Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObjectMove(HUILE, DEMI_TOUR);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_.isActionHealMove());
    }

    @Test
    public void cancelSubstituing4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstitute((byte) 1);
        fighter_.cancelSubstituing();
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_.isEmpty());
    }

    @Test
    public void getFirstChosenMove1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        assertEq(SEISME, fighter_.getFirstChosenMove());
    }

    @Test
    public void getFirstChosenMove2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setChosenHealingObject(EAU_FRAICHE, _data_);
        assertEq(NULL_REF, fighter_.getFirstChosenMove());
    }

    @Test
    public void getFirstChosenMove3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObjectMove(HUILE, DEMI_TOUR);
        assertEq(DEMI_TOUR, fighter_.getFirstChosenMove());
    }

    @Test
    public void getFirstChosenMove4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstitute((byte) 1);
        assertEq(NULL_REF, fighter_.getFirstChosenMove());
    }

    @Test
    public void getFirstChosenMove5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.cancelActions();
        assertEq(NULL_REF, fighter_.getFirstChosenMove());
    }

    @Test
    public void getObjetSoinChoisi1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        assertEq(NULL_REF, fighter_.getChosenHealingItem());
    }

    @Test
    public void getObjetSoinChoisi2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setChosenHealingObject(EAU_FRAICHE, _data_);
        assertEq(EAU_FRAICHE, fighter_.getChosenHealingItem());
    }

    @Test
    public void getObjetSoinChoisi3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObjectMove(HUILE, DEMI_TOUR);
        assertEq(HUILE, fighter_.getChosenHealingItem());
    }

    @Test
    public void getObjetSoinChoisi4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstitute((byte) 1);
        assertEq(NULL_REF, fighter_.getChosenHealingItem());
    }

    @Test
    public void getObjetSoinChoisi5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.cancelActions();
        assertEq(NULL_REF, fighter_.getChosenHealingItem());
    }

    @Test
    public void getChosenTargets1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        EqList<TargetCoords> list_ = fighter_.getChosenTargets();
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(POKEMON_FOE_TARGET_ZERO));
    }

    @Test
    public void getChosenTargets2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setChosenHealingObject(EAU_FRAICHE, _data_);
        EqList<TargetCoords> list_ = fighter_.getChosenTargets();
        assertEq(0, list_.size());
    }

    @Test
    public void getChosenTargets3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObjectMove(HUILE, DEMI_TOUR);
        EqList<TargetCoords> list_ = fighter_.getChosenTargets();
        assertEq(0, list_.size());
    }

    @Test
    public void getChosenTargets4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstitute((byte) 1);
        EqList<TargetCoords> list_ = fighter_.getChosenTargets();
        assertEq(0, list_.size());
    }

    @Test
    public void getChosenTargets5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.cancelActions();
        EqList<TargetCoords> list_ = fighter_.getChosenTargets();
        assertEq(0, list_.size());
    }

    @Test
    public void getSubstistute1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void getSubstistute2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setFirstChosenMove(DEMI_TOUR);
        fighter_.setSubstituteForMove((byte) 1);
        //fighter_.setFirstChosenMoveSubstitute(DEMI_TOUR, (byte) 1);
        assertEq(1, fighter_.getSubstistute());
    }

    @Test
    public void getSubstistute3Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setChosenHealingObject(EAU_FRAICHE, _data_);
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void getSubstistute4Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObjectMove(HUILE, DEMI_TOUR);
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void getSubstistute5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstitute((byte) 1);
        assertEq(1, fighter_.getSubstistute());
    }

    @Test
    public void getSubstistute6Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.cancelActions();
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void choisirAttaqueFin1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.choisirAttaqueFin();
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SEISME, action_.getFirstChosenMove());
        assertEq(SEISME, action_.getFinalChosenMove());
        assertEq(0, action_.getChosenTargets().size());
    }

    @Test
    public void choisirAttaqueFin2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.choisirAttaqueFin();
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(PISTOLET_A_O, action_.getFinalChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
    }

    @Test
    public void getFinalChosenMove1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.choisirAttaqueFin();
        assertEq(SEISME, fighter_.getFinalChosenMove());
    }

    @Test
    public void getFinalChosenMove2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setChosenHealingObject(EAU_FRAICHE, _data_);
        fighter_.choisirAttaqueFin();
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
    }

    @Test
    public void getFinalChosenMove3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObjectMove(HUILE, DEMI_TOUR);
        fighter_.choisirAttaqueFin();
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
    }

    @Test
    public void getFinalChosenMove4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstitute((byte) 1);
        fighter_.choisirAttaqueFin();
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
    }

    @Test
    public void getFinalChosenMove5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.cancelActions();
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
    }

    @Test
    public void healedPpMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChanged(true);
        assertEq(0, fighter_.healedPpMove(POURSUITE, HUILE, _data_));
    }

    @Test
    public void healedPpMove2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.apprendreAttaqueEcrasant(ULTRASON, COPIE, _data_);
        fighter_.usePowerPointsByMove(new Difficulty(), ULTRASON, (short) 5);
        assertEq(0, fighter_.healedPpMove(ULTRASON, HUILE, _data_));
    }

    @Test
    public void healedPpMove3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 5);
        assertEq(5, fighter_.healedPpMove(POURSUITE, HUILE, _data_));
    }

    @Test
    public void healedPpMove4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 15);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 12);
        assertEq(10, fighter_.healedPpMove(POURSUITE, HUILE, _data_));
    }

    @Test
    public void healedPpMove5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 5);
        assertEq(5, fighter_.healedPpMove(POURSUITE, HUILE_MAX, _data_));
    }

    @Test
    public void healedPpMove6Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 15);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 12);
        assertEq(12, fighter_.healedPpMove(POURSUITE, HUILE_MAX, _data_));
    }

    @Test
    public void healedPpMove7Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 5);
        assertEq(5, fighter_.healedPpMove(POURSUITE, ELIXIR, _data_));
    }

    @Test
    public void healedPpMove8Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 15);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 12);
        assertEq(10, fighter_.healedPpMove(POURSUITE, ELIXIR, _data_));
    }

    @Test
    public void healedPpMove9Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 5);
        assertEq(5, fighter_.healedPpMove(POURSUITE, MAX_ELIXIR, _data_));
    }

    @Test
    public void healedPpMove10Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 15);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 12);
        assertEq(12, fighter_.healedPpMove(POURSUITE, MAX_ELIXIR, _data_));
    }

    @Test
    public void healedPpMove11Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 5);
        assertEq(5, fighter_.healedPpMove(POURSUITE, BAIE_MEPO, _data_));
    }

    @Test
    public void healedPpMove12Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 15);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 12);
        assertEq(10, fighter_.healedPpMove(POURSUITE, BAIE_MEPO, _data_));
    }

    @Test
    public void healPowerPoints1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 15);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.usePowerPointsByMove(new Difficulty(), POURSUITE, (short) 12);
        fighter_.healPowerPoints(POURSUITE, (short) 10);
        assertEq(13, fighter_.getCurrentMoves().getVal(POURSUITE).getCurrent());
    }

    @Test
    public void ajouterAttaquesDejaInvoqueesTour1Test(){
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.choisirAttaqueFin();
        fighter_.ajouterAttaquesDejaInvoqueesTour(SEISME);
        assertEq(1, fighter_.getAlreadyInvokedMovesRound().size());
        assertTrue(fighter_.getAlreadyInvokedMovesRound().containsObj(SEISME));
    }

    @Test
    public void toutSupprimerAttaquesDejaInvoqueesTour1Test(){
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.choisirAttaqueFin();
        fighter_.ajouterAttaquesDejaInvoqueesTour(SEISME);
        fighter_.toutSupprimerAttaquesDejaInvoqueesTour();
        assertEq(0, fighter_.getAlreadyInvokedMovesRound().size());
    }

    @Test
    public void reinitEffetTour1Test(){
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.choisirAttaqueFin();
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.reinitEffetTour();
        assertTrue(!fighter_.getEnabledMovesForAlly().getVal(COUP_D_MAIN));
    }

    @Test
    public void reinitEffetTour2Test(){
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.choisirAttaqueFin();
        fighter_.reinitEffetTour();
        assertTrue(!fighter_.getEnabledMovesForAlly().getVal(COUP_D_MAIN));
    }

    @Test
    public void initRoundFrontFighter1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.getDamageSufferedCateg().getVal(PHYSIQUE).affect(new Rate("2"));
        fighter_.getDamageSufferedCateg().getVal(SPECIALE).affect(new Rate("2"));
        fighter_.getDamageSufferedCategRound().getVal(PHYSIQUE).affect(new Rate("3"));
        fighter_.getDamageSufferedCategRound().getVal(SPECIALE).affect(new Rate("3"));
        fighter_.initRoundFrontFighter();
        assertTrue(!fighter_.getEnabledMovesForAlly().getVal(COUP_D_MAIN));
        assertEq(0, fighter_.getAlreadyInvokedMovesRound().size());
        assertTrue(!fighter_.isActed());
        assertEq(Rate.zero(), fighter_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), fighter_.getDamageSufferedCateg().getVal(SPECIALE));
        assertEq(Rate.zero(), fighter_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(Rate.zero(), fighter_.getDamageSufferedCategRound().getVal(SPECIALE));
    }

    @Test
    public void initRoundFrontFighter2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setNbPrepaRound((short) 1);
        fighter_.getDamageSufferedCateg().getVal(PHYSIQUE).affect(new Rate("2"));
        fighter_.getDamageSufferedCateg().getVal(SPECIALE).affect(new Rate("2"));
        fighter_.getDamageSufferedCategRound().getVal(PHYSIQUE).affect(new Rate("3"));
        fighter_.getDamageSufferedCategRound().getVal(SPECIALE).affect(new Rate("3"));
        fighter_.initRoundFrontFighter();
        assertTrue(!fighter_.getEnabledMovesForAlly().getVal(COUP_D_MAIN));
        assertEq(0, fighter_.getAlreadyInvokedMovesRound().size());
        assertTrue(!fighter_.isActed());
        assertEq(new Rate("2"), fighter_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(new Rate("2"), fighter_.getDamageSufferedCateg().getVal(SPECIALE));
        assertEq(Rate.zero(), fighter_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(Rate.zero(), fighter_.getDamageSufferedCategRound().getVal(SPECIALE));
    }

    @Test
    public void invokeMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setFirstChosenMoveTarget(MIMIQUE, POKEMON_FOE_TARGET_ZERO);
        fighter_.choisirAttaqueFin();
        fighter_.ajouterAttaquesDejaInvoqueesTour(SEISME);
        fighter_.invokeMove();
        assertEq(SEISME, fighter_.getFinalChosenMove());
    }

    @Test
    public void successUsingMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(SEISME, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.choisirAttaqueFin();
        fighter_.successUsingMove();
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(SEISME, fighter_.getLastSuccessfulMove());
    }

    @Test
    public void setLastUsedMove1Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setFirstChosenMove(SEISME);
        fighter_.choisirAttaqueFin();
        fighter_.setLastUsedMove();
        assertEq(SEISME, fighter_.getUsedMoveLastRound());
    }

    @Test
    public void setLastUsedMove2Test() {
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        Fighter fighter_ = new Fighter(pokemon_, _data_, (byte)0);
        fighter_.setChosenHealingObject(EAU_FRAICHE, _data_);
        fighter_.choisirAttaqueFin();
        fighter_.setLastUsedMove();
        assertEq(NULL_REF, fighter_.getUsedMoveLastRound());
    }

    @Test
    public void setLastUsedMove3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setChosenHealingObjectMove(HUILE, DEMI_TOUR);
        fighter_.choisirAttaqueFin();
        fighter_.setLastUsedMove();
        assertEq(NULL_REF, fighter_.getUsedMoveLastRound());
    }

    @Test
    public void setLastUsedMove4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setSubstitute((byte) 1);
        fighter_.choisirAttaqueFin();
        fighter_.setLastUsedMove();
        assertEq(NULL_REF, fighter_.getUsedMoveLastRound());
    }

    @Test
    public void setLastUsedMove5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.cancelActions();
        fighter_.choisirAttaqueFin();
        fighter_.setLastUsedMove();
        assertEq(NULL_REF, fighter_.getUsedMoveLastRound());
    }

    @Test
    public void backUpObject1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.backUpObject(NULL_REF);
        assertEq(PIERRALLEGEE, fighter_.getLastUsedItem());
        assertEq(NULL_REF, fighter_.getItem());
    }

    @Test
    public void backUpObject2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.backUpObject(BAIE_MEPO);
        assertEq(NULL_REF, fighter_.getLastUsedItem());
        assertEq(BAIE_MEPO, fighter_.getItem());
    }

    @Test
    public void backUpObject3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.backUpObject(BAIE_MEPO);
        assertEq(NULL_REF, fighter_.getLastUsedItem());
        assertEq(BAIE_MEPO, fighter_.getItem());
    }

    @Test
    public void restoreLastObject1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.restoreLastObject();
        assertEq(NULL_REF, fighter_.getLastUsedItem());
        assertEq(PIERRALLEGEE, fighter_.getItem());
    }

    @Test
    public void restoreLastObject2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setLastUsedItem(PIERRALLEGEE);
        fighter_.setItem(NULL_REF);
        fighter_.restoreLastObject();
        assertEq(PIERRALLEGEE, fighter_.getLastUsedItem());
        assertEq(PIERRALLEGEE, fighter_.getItem());
    }

    @Test
    public void restoreLastObject3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.restoreLastObject();
        assertEq(NULL_REF, fighter_.getLastUsedItem());
        assertEq(NULL_REF, fighter_.getItem());
    }

    @Test
    public void useObject1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.useObject();
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void tossLastUsedObject1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.tossLastUsedObject();
        assertEq(NULL_REF, fighter_.getLastUsedItem());
        assertEq(PIERRALLEGEE, fighter_.getItem());
    }

    @Test
    public void tossLastUsedObject2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.useObject();
        fighter_.tossLastUsedObject();
        assertEq(NULL_REF, fighter_.getItem());
        assertEq(PIERRALLEGEE, fighter_.getLastUsedItem());
    }

    @Test
    public void tossLastUsedObject3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.useObject();
        fighter_.tossLastUsedObject();
        fighter_.useObject();
        fighter_.tossLastUsedObject();
        assertEq(NULL_REF, fighter_.getItem());
        assertEq(NULL_REF, fighter_.getLastUsedItem());
    }

    @Test
    public void affectNoUsesMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setNbRepeatingSuccessfulMoves(new LgInt("2"));
        fighter_.affectNoUsesMove();
        assertEq(LgInt.zero(), fighter_.getNbRepeatingSuccessfulMoves());
    }

    @Test
    public void incrementConsecutiveUsesMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setNbRepeatingSuccessfulMoves(new LgInt("2"));
        fighter_.incrementConsecutiveUsesMove();
        assertEq(new LgInt("3"), fighter_.getNbRepeatingSuccessfulMoves());
    }

    @Test
    public void affectNoRoundBeforeUsingMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setNbPrepaRound((short) 2);
        fighter_.affectNoRoundBeforeUsingMove();
        assertEq(0, fighter_.getNbPrepaRound());
    }

    @Test
    public void incrementRoundBeforeUsingMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setNbPrepaRound((short) 2);
        fighter_.incrementRoundBeforeUsingMove();
        assertEq(3, fighter_.getNbPrepaRound());
    }

    @Test
    public void variationGainExperience1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.variationGainExperience(new Rate("2"), _data_);
        assertEq(new Rate("2"), fighter_.getWonExp());
    }

    @Test
    public void numberNecessaryPointsForGrowingLevel1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3,2));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        Rate nbPoints_ = fighter_.numberNecessaryPointsForGrowingLevel((short) 4, _data_);
        assertEq(new Rate("7"),nbPoints_);
        nbPoints_ = fighter_.numberNecessaryPointsForGrowingLevel((short) 5, _data_);
        assertEq(new Rate("9"),nbPoints_);
    }

    @Test
    public void nomEvolutions1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PIERRE_STASE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        assertEq(0, fighter_.nomEvolutions(_data_, new StringList()).size());
    }

    @Test
    public void nomEvolutions2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NUCLEOS);
        pokemon_.setItem(PIERRE_STASE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 34);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        assertEq(0, fighter_.nomEvolutions(_data_, new StringList()).size());
    }

    @Test
    public void nomEvolutions3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NUCLEOS);
        pokemon_.setItem(PIERRE_STASE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 31);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        assertEq(0, fighter_.nomEvolutions(_data_, new StringList()).size());
    }

    @Test
    public void nomEvolutions4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NUCLEOS);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 34);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(MEIOS));
    }

    @Test
    public void nomEvolutions5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NUCLEOS);
        pokemon_.setItem(BRAC_MACHO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 31);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(0, list_.size());
    }

    @Test
    public void nomEvolutions6Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 34);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(0, list_.size());
    }

    @Test
    public void nomEvolutions7Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 34);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(YANMEGA));
    }

    @Test
    public void nomEvolutions8Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(CHENITI);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.MALE);
        pokemon_.setLevel((short) 15);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(0, list_.size());
    }

    @Test
    public void nomEvolutions9Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(CHENITI);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.FEMALE);
        pokemon_.setLevel((short) 15);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(0, list_.size());
    }

    @Test
    public void nomEvolutions10Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(CHENITI);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.MALE);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(PAPILORD));
    }

    @Test
    public void nomEvolutions11Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(CHENITI);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.FEMALE);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(CHENISELLE));
    }

    @Test
    public void nomEvolutions12Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 40);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(0, list_.size());
    }

    @Test
    public void nomEvolutions13Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(TARINORME));
    }

    @Test
    public void nomEvolutions14Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(0, list_.size());
    }

    @Test
    public void nomEvolutions15Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(ROCHE_ROYALE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(TARPAUD));
    }

    @Test
    public void nomEvolutions16Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(0, list_.size());
    }

    @Test
    public void nomEvolutions17Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList(REMORAID));
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(DEMANTA));
    }

    @Test
    public void nomEvolutions18Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(CARAPUCE);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 2);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(CARAPUCE);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(0, list_.size());
    }

    @Test
    public void nomEvolutions19Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(CARAPUCE);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 2);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(CARAPUCE);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        StringList list_ = fighter_.nomEvolutions(_data_, new StringList());
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(CARABAFFE));
    }

    @Test
    public void transformer1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(CARTE_ROUGE);
        pokemon_.setAbility(CRACHIN);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 22);
        PkTrainer pokemonTrainer_ = new PkTrainer(pokemon_, new StringList(GRIBOUILLE,COPIE,POUV_ANTIQUE));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        Fighter foeFighter_ = new Fighter(pokemonTrainer_, _data_, (byte) 0);
        foeFighter_.getTypes().add(SOL);
        fighter_.transformer(foeFighter_, (short) 5);
        assertTrue(fighter_.isChanged());
        assertEq(BABIMANTA,fighter_.getName());
        assertEq(PTITARD,fighter_.getCurrentName());
        assertEq(METEO,fighter_.getAbility());
        assertEq(CRACHIN,fighter_.getCurrentAbility());
        assertEq(NULL_REF,fighter_.getItem());
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(MORPHING));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(MORPHING).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(GRIBOUILLE));
        assertTrue(map_.contains(COPIE));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(5, map_.getVal(GRIBOUILLE).getCurrent());
        assertEq(5, map_.getVal(COPIE).getCurrent());
        assertEq(5, map_.getVal(POUV_ANTIQUE).getCurrent());
        assertEq(20, fighter_.getLevel());
        assertEq(2, fighter_.getTypes().size());
        assertTrue(fighter_.getTypes().containsObj(EAU));
        assertTrue(fighter_.getTypes().containsObj(SOL));
        assertEq(new Rate("62/5"), fighter_.getWeight());
        assertEq(new Rate("3/5"), fighter_.getHeight());
    }

    @Test
    public void creerClone1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.creerClone(new Rate("1/4"));
        assertEq(new Rate("271/20"),fighter_.getClone());
        assertEq(new Rate("813/20"),fighter_.getRemainingHp());
    }

    @Test
    public void creerClone2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setRemainingHp(new Rate("1"));
        fighter_.creerClone(new Rate("1/4"));
        assertEq(new Rate("0"),fighter_.getClone());
        assertEq(new Rate("1"),fighter_.getRemainingHp());
    }

    @Test
    public void infligerDegatsClone1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.creerClone(new Rate("1/4"));
        fighter_.infligerDegatsClone(new Rate("271/80"));
        assertEq(new Rate("813/80"),fighter_.getClone());
        assertEq(new Rate("813/20"),fighter_.getRemainingHp());
    }

    @Test
    public void infligerDegatsClone2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.creerClone(new Rate("1/4"));
        fighter_.infligerDegatsClone(new Rate("271/10"));
        assertEq(Rate.zero(),fighter_.getClone());
        assertEq(new Rate("813/20"),fighter_.getRemainingHp());
    }

    @Test
    public void effectBatonPass1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter partner_ = new Fighter(pokemonUser_, _data_, Fighter.BACK);
        fighter_.initCreatureRelationsAutre(new EqList<TeamPosition>(POKEMON_FOE_FIGHTER_ZERO),_data_);
        partner_.initCreatureRelationsAutre(new EqList<TeamPosition>(POKEMON_FOE_FIGHTER_ZERO),_data_);
        fighter_.getStatisBoost().put(Statistic.ATTACK, (byte) 1);
        fighter_.creerClone(new Rate("1/2"));
        fighter_.getNbUsesMoves().put(BOUL_ARMURE, 1);
        fighter_.getDamageSufferedCateg().put(PHYSIQUE, Rate.one());
        fighter_.getDamageSufferedCategRound().put(PHYSIQUE, Rate.one());
        fighter_.getDamageRateSufferedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.getDamageRateInflictedByType().getVal(ELECTRIQUE).affect(new Rate("2"));
        fighter_.setLastSufferedMove(JACKPOT);
        fighter_.getNbRepeatingSuccessfulMoves().affect(new LgInt("2"));
        fighter_.activerAttaque(EMBARGO);
        fighter_.activerAttaqueAntiImmu(RACINES);
        fighter_.activerAttaqueImmu(TROU, _data_);
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        fighter_.activerAttaqueFinTourIndividuel(RACINES);
        fighter_.getEnabledMovesForAlly().put(COUP_D_MAIN, true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        partner_.effectBatonPass(fighter_);
        assertEq(1, partner_.getNbUsesMoves().getVal(BOUL_ARMURE).intValue());
        assertEq(Rate.one(), partner_.getDamageSufferedCateg().getVal(PHYSIQUE));
        assertEq(Rate.zero(), partner_.getDamageSufferedCategRound().getVal(PHYSIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateSufferedByType().getVal(ELECTRIQUE));
        assertEq(new Rate("2"), partner_.getDamageRateInflictedByType().getVal(ELECTRIQUE));
        assertEq(new LgInt("2"), partner_.getNbRepeatingSuccessfulMoves());
        assertEq(JACKPOT, partner_.getLastSufferedMove());
        assertEq(new Rate("271/5"), partner_.getRemainingHp());
        assertEq(new Rate("271/10"), partner_.getClone());
        assertEq(1, partner_.getStatisBoost().getVal(Statistic.ATTACK).intValue());
        ActivityOfMove activity_;
        activity_ = partner_.getEnabledMoves().getVal(EMBARGO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        activity_ = partner_.getEnabledMovesUnprot().getVal(RACINES);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        activity_ = partner_.getEnabledMovesProt().getVal(TROU);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        StringList types_ = partner_.getProtectedAgainstMoveTypes();
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(VOL));
        activity_ = partner_.getEnabledMovesConstChoices().getVal(ROULADE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        activity_ = partner_.getEnabledMovesEndRound().getVal(RACINES);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        activity_ = partner_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO));
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(partner_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(!partner_.getEnabledMovesForAlly().getVal(COUP_D_MAIN));
    }

    @Test
    public void newLevelWonPoints1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        assertEq(3, result_.getLevel());
        assertEq(new Rate("7"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate(4));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        assertEq(4, result_.getLevel());
        assertEq(new Rate("16"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 99);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1000"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        assertEq(100, result_.getLevel());
        assertEq(new Rate("199"), result_.getExpPoints());
    }

    @Test
    public void newLevelWonPoints4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        assertEq(100, result_.getLevel());
    }

    @Test
    public void newLevelWonPoints5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.getWonExp().affect(new Rate("201"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        assertEq(100, result_.getLevel());
    }

    @Test
    public void changeWonPoints1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        assertEq(Rate.zero(), fighter_.getWonExp());
        assertEq(new Rate("3"), fighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate(4));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        assertEq(Rate.zero(), fighter_.getWonExp());
        assertEq(new Rate("2"), fighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(5));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate(14));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        assertEq(Rate.zero(), fighter_.getWonExp());
        assertEq(new Rate("3"), fighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void changeWonPoints4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 99);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("0"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("1000"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        assertEq(new Rate("801"), fighter_.getWonExp());
        assertEq(Rate.zero(), fighter_.getWonExpSinceLastLevel());
    }

    @Test
    public void newMoves1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
        assertEq(4, newMoves_.size());
        assertTrue(!newMoves_.getVal(ULTRASON));
        assertTrue(!newMoves_.getVal(BROUHAHA));
        assertTrue(!newMoves_.getVal(POURSUITE));
        assertTrue(newMoves_.getVal(POUV_ANTIQUE));
    }

    @Test
    public void newMoves2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(true);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
        assertEq(4, newMoves_.size());
        assertTrue(!newMoves_.getVal(ULTRASON));
        assertTrue(!newMoves_.getVal(BROUHAHA));
        assertTrue(!newMoves_.getVal(POURSUITE));
        assertTrue(newMoves_.getVal(POUV_ANTIQUE));
    }

    @Test
    public void newMoves3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 33);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
        assertEq(4, newMoves_.size());
        assertTrue(!newMoves_.getVal(ULTRASON));
        assertTrue(!newMoves_.getVal(BROUHAHA));
        assertTrue(!newMoves_.getVal(POURSUITE));
        assertTrue(newMoves_.getVal(POUV_ANTIQUE));
    }

    @Test
    public void newMoves4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(true);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 33);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
        assertEq(3, newMoves_.size());
        assertTrue(!newMoves_.getVal(ULTRASON));
        assertTrue(!newMoves_.getVal(BROUHAHA));
        assertTrue(!newMoves_.getVal(POURSUITE));
    }

    @Test
    public void initLearntMoves1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=getMoves(newMoves_,false);
        StringList attaquesApprendre_=getMoves(newMoves_,true);
        fighter_.initLearntMoves(attaquesApprendre_, attaquesConnues_, _data_);
        assertEq(0, fighter_.getMovesToBeLearnt().size());
    }

    @Test
    public void initLearntMoves2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=getMoves(newMoves_,false);
        StringList attaquesApprendre_=getMoves(newMoves_,true);
        fighter_.initLearntMoves(attaquesApprendre_, attaquesConnues_, _data_);
        StringList movesToBeLearnt_ = fighter_.getMovesToBeLearnt();
        assertEq(1, movesToBeLearnt_.size());
        assertTrue(movesToBeLearnt_.containsObj(POUV_ANTIQUE));
    }

    @Test
    public void learnMoves1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=getMoves(newMoves_,false);
        StringList attaquesApprendre_=getMoves(newMoves_,true);
        fighter_.initLearntMoves(attaquesApprendre_, attaquesConnues_, _data_);
        fighter_.setLevel(result_.getLevel());
        fighter_.learnMoves(attaquesApprendre_, _data_);
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(4, map_.size());
        assertTrue(map_.contains(ULTRASON));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POURSUITE));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(ULTRASON).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POURSUITE).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(4, map_.size());
        assertTrue(map_.contains(ULTRASON));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POURSUITE));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(ULTRASON).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POURSUITE).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
    }

    @Test
    public void learnMoves2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.getCurrentMoves().put(POUV_ANTIQUE, new UsesOfMove((short) 5));
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=getMoves(newMoves_,false);
        StringList attaquesApprendre_=getMoves(newMoves_,true);
        fighter_.initLearntMoves(attaquesApprendre_, attaquesConnues_, _data_);
        fighter_.setLevel(result_.getLevel());
        fighter_.learnMoves(attaquesApprendre_, _data_);
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(4, map_.size());
        assertTrue(map_.contains(ULTRASON));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POURSUITE));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(ULTRASON).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POURSUITE).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(4, map_.size());
        assertTrue(map_.contains(ULTRASON));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POURSUITE));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(ULTRASON).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POURSUITE).getCurrent());
        assertEq(5, map_.getVal(POUV_ANTIQUE).getCurrent());
    }

    @Test
    public void learnMoves3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=getMoves(newMoves_,false);
        StringList attaquesApprendre_=getMoves(newMoves_,true);
        fighter_.initLearntMoves(attaquesApprendre_, attaquesConnues_, _data_);
        fighter_.setLevel(result_.getLevel());
        fighter_.learnMoves(attaquesApprendre_, _data_);
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(4, map_.size());
        assertTrue(map_.contains(ULTRASON));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POURSUITE));
        assertTrue(map_.contains(DETECTION));
        assertEq(10, map_.getVal(ULTRASON).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POURSUITE).getCurrent());
        assertEq(10, map_.getVal(DETECTION).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(4, map_.size());
        assertTrue(map_.contains(ULTRASON));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POURSUITE));
        assertTrue(map_.contains(DETECTION));
        assertEq(10, map_.getVal(ULTRASON).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POURSUITE).getCurrent());
        assertEq(10, map_.getVal(DETECTION).getCurrent());
    }

    @Test
    public void proponeMovesAbilitiesForEvolutions1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=getMoves(newMoves_,false);
        StringList attaquesApprendre_=getMoves(newMoves_,true);
        fighter_.initLearntMoves(attaquesApprendre_, attaquesConnues_, _data_);
        fighter_.setLevel(result_.getLevel());
        fighter_.learnMoves(attaquesApprendre_, _data_);
        attaquesConnues_.clear();
        attaquesConnues_.addAllElts(fighter_.getMoves().getKeys());
        fighter_.proponeMovesAbilitiesForEvolutions(attaquesApprendre_, attaquesConnues_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(1, movesAbilities_.size());
        assertTrue(movesAbilities_.contains(YANMEGA));
        StringList movesEvo_ = movesAbilities_.getVal(YANMEGA).getMoves();
        StringList abilitiesEvo_ = movesAbilities_.getVal(YANMEGA).getAbilities();
        assertEq(8, movesEvo_.size());
        assertTrue(movesEvo_.containsStr(CHARGE));
        assertTrue(movesEvo_.containsStr(CLAIRVOYANCE));
        assertTrue(movesEvo_.containsStr(PIQURE));
        assertTrue(movesEvo_.containsStr(TRANCHE_NUIT));
        assertTrue(movesEvo_.containsStr(VIVE_ATTAQUE));
        assertTrue(movesEvo_.containsStr(REFLET));
        assertTrue(movesEvo_.containsStr(SONICBOOM));
        assertTrue(movesEvo_.containsStr(DETECTION));
        assertEq(2, abilitiesEvo_.size());
        assertTrue(abilitiesEvo_.containsStr(TURBO));
        assertTrue(abilitiesEvo_.containsStr(LENTITEINTEE));
    }

    @Test
    public void proponeMovesAbilitiesForEvolutions2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 37);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(POUV_ANTIQUE, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("4215"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("5"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=getMoves(newMoves_,false);
        StringList attaquesApprendre_=getMoves(newMoves_,true);
        fighter_.initLearntMoves(attaquesApprendre_, attaquesConnues_, _data_);
        fighter_.setLevel(result_.getLevel());
        fighter_.learnMoves(attaquesApprendre_, _data_);
        attaquesConnues_.clear();
        attaquesConnues_.addAllElts(fighter_.getMoves().getKeys());
        fighter_.proponeMovesAbilitiesForEvolutions(attaquesApprendre_, attaquesConnues_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(1, movesAbilities_.size());
        assertTrue(movesAbilities_.contains(YANMEGA));
        StringList movesEvo_ = movesAbilities_.getVal(YANMEGA).getMoves();
        StringList abilitiesEvo_ = movesAbilities_.getVal(YANMEGA).getAbilities();
        assertEq(10, movesEvo_.size());
        assertTrue(movesEvo_.containsStr(CHARGE));
        assertTrue(movesEvo_.containsStr(CLAIRVOYANCE));
        assertTrue(movesEvo_.containsStr(PIQURE));
        assertTrue(movesEvo_.containsStr(TRANCHE_NUIT));
        assertTrue(movesEvo_.containsStr(VIVE_ATTAQUE));
        assertTrue(movesEvo_.containsStr(REFLET));
        assertTrue(movesEvo_.containsStr(SONICBOOM));
        assertTrue(movesEvo_.containsStr(RUSE));
        assertTrue(movesEvo_.containsStr(HYPNOSE));
        assertTrue(movesEvo_.containsStr(DETECTION));
        assertEq(2, abilitiesEvo_.size());
        assertTrue(abilitiesEvo_.containsStr(TURBO));
        assertTrue(abilitiesEvo_.containsStr(LENTITEINTEE));
    }

    @Test
    public void proponeMovesAbilitiesForEvolutions3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        LevelExpPoints result_ = fighter_.newLevelWonPoints(_data_);
        fighter_.changeWonPoints(result_.getLevel(), result_.getExpPoints(), _data_);
        StringMap<Boolean> newMoves_ = fighter_.newMoves(result_.getLevel(), diff_, _data_);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=getMoves(newMoves_,false);
        StringList attaquesApprendre_=getMoves(newMoves_,true);
        fighter_.initLearntMoves(attaquesApprendre_, attaquesConnues_, _data_);
        fighter_.setLevel(result_.getLevel());
        fighter_.learnMoves(attaquesApprendre_, _data_);
        attaquesConnues_.clear();
        attaquesConnues_.addAllElts(fighter_.getMoves().getKeys());
        fighter_.proponeMovesAbilitiesForEvolutions(attaquesApprendre_, attaquesConnues_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, movesAbilities_.size());
    }

    private StringList getMoves(StringMap<Boolean> _map, boolean _learn) {
        StringList moves_;
        moves_ = new StringList();
        if (_learn) {
            for (EntryCust<String,Boolean> e: _map.entryList()) {
                if (e.getValue()) {
                    moves_.add(e.getKey());
                }
            }
        } else {
            for (EntryCust<String,Boolean> e: _map.entryList()) {
                if (!e.getValue()) {
                    moves_.add(e.getKey());
                }
            }
        }
        return moves_;
    }

    @Test
    public void winHappinessByGrowingLevel1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.winHappinessByGrowingLevel((short) 5, _data_);
        assertEq(150, fighter_.getHappiness());
    }

    @Test
    public void winHappinessByGrowingLevel2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(HUILE);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.winHappinessByGrowingLevel((short) 5, _data_);
        assertEq(150, fighter_.getHappiness());
    }

    @Test
    public void winHappinessByGrowingLevel3Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(BRAC_MACHO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.winHappinessByGrowingLevel((short) 5, _data_);
        assertEq(150, fighter_.getHappiness());
    }

    @Test
    public void winHappinessByGrowingLevel4Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.winHappinessByGrowingLevel((short) 5, _data_);
        assertEq(160, fighter_.getHappiness());
    }

    @Test
    public void winHappinessByGrowingLevel5Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 155);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.winHappinessByGrowingLevel((short) 5, _data_);
        assertEq(170, fighter_.getHappiness());
    }

    @Test
    public void exitFrontBattle1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 155);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.exitFrontBattle();
        assertEq(Fighter.BACK,fighter_.getGroundPlace());
    }

    @Test
    public void exitFrontBattleForBeingSubstitued1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 155);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.exitFrontBattleForBeingSubstitued();
        assertEq(Fighter.BACK,fighter_.getGroundPlaceSubst());
    }

    @Test
    public void incrementRoundsStatus1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.getStatus().add(GEL);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 155);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.incrementRoundsStatus(GEL);
        assertEq(2, fighter_.getStatusNbRound(GEL).intValue());
    }

    @Test
    public void fullHeal1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.ajouterRelationAutre(POKEMON_FOE_FIGHTER_ZERO, _data_);
        fighter_.setRemainingHp(new Rate("813/20"));
        fighter_.getMoves().getVal(MORPHING).setCurrent((short) 0);
        fighter_.getMoves().getVal(BROUHAHA).setCurrent((short) 5);
        fighter_.getMoves().getVal(POUV_ANTIQUE).setCurrent((short) 0);
        fighter_.getCurrentMoves().getVal(MORPHING).setCurrent((short) 0);
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 5);
        fighter_.getCurrentMoves().getVal(POUV_ANTIQUE).setCurrent((short) 0);
        fighter_.fullHeal(_data_);
        assertEq(new Rate("271/5"),fighter_.getRemainingHp());
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(MORPHING));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(MORPHING).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(3, map_.size());
        assertTrue(map_.contains(MORPHING));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(MORPHING).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
    }

    @Test
    public void calculateNewLevel1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
        assertEq(32,fighter_.getLevel());
        assertEq(70, fighter_.getHappiness());
        assertEq(new Rate("4"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
    }

    @Test
    public void calculateNewLevel2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(1, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
        assertEq(32,fighter_.getLevel());
        assertTrue(movesAbilities_.contains(TARINORME));
        StringList allMoves_ = movesAbilities_.getVal(TARINORME).getMoves();
        assertEq(8, allMoves_.size());
        assertTrue(allMoves_.containsObj(CHARGE));
        assertTrue(allMoves_.containsObj(GRAVITE));
        assertTrue(allMoves_.containsObj(VOL_MAGNETIK));
        assertTrue(allMoves_.containsObj(MUR_DE_FER));
        assertTrue(allMoves_.containsObj(BOMBAIMANT));
        assertTrue(allMoves_.containsObj(REGARD_NOIR));
        assertTrue(allMoves_.containsObj(CAGE_ECLAIR));
        assertTrue(allMoves_.containsObj(EBOULEMENT));
        StringList abilities_ = movesAbilities_.getVal(TARINORME).getAbilities();
        assertEq(2, abilities_.size());
        assertTrue(abilities_.containsObj(MAGNEPIEGE));
        assertTrue(abilities_.containsObj(FERMETE));
        assertEq(140, fighter_.getHappiness());
        assertEq(new Rate("4"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
    }

    @Test
    public void calculateNewLevel3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3168"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(1, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
        assertEq(33,fighter_.getLevel());
        assertTrue(movesAbilities_.contains(TARINORME));
        StringList allMoves_ = movesAbilities_.getVal(TARINORME).getMoves();
        assertEq(8, allMoves_.size());
        assertTrue(allMoves_.containsObj(CHARGE));
        assertTrue(allMoves_.containsObj(GRAVITE));
        assertTrue(allMoves_.containsObj(VOL_MAGNETIK));
        assertTrue(allMoves_.containsObj(MUR_DE_FER));
        assertTrue(allMoves_.containsObj(BOMBAIMANT));
        assertTrue(allMoves_.containsObj(REGARD_NOIR));
        assertTrue(allMoves_.containsObj(CAGE_ECLAIR));
        assertTrue(allMoves_.containsObj(EBOULEMENT));
        StringList abilities_ = movesAbilities_.getVal(TARINORME).getAbilities();
        assertEq(2, abilities_.size());
        assertTrue(abilities_.containsObj(MAGNEPIEGE));
        assertTrue(abilities_.containsObj(FERMETE));
        assertEq(142, fighter_.getHappiness());
        assertEq(new Rate("2"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
    }

    @Test
    public void calculateNewLevel4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 30);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("2790"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, movesAbilities_.size());
        assertEq(1, fighter_.getMovesToBeLearnt().size());
        assertTrue(fighter_.getMovesToBeLearnt().containsObj(EBOULEMENT));
        assertEq(31,fighter_.getLevel());
        assertEq(72, fighter_.getHappiness());
        assertEq(new Rate("2"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
    }

    @Test
    public void calculateNewLevel5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 19);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("0"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(2, movesAbilities_.size());
        assertEq(1, fighter_.getMovesToBeLearnt().size());
        assertTrue(fighter_.getMovesToBeLearnt().containsObj(LIRE_ESPRIT));
        assertTrue(movesAbilities_.contains(NINJASK));
        StringList allMoves_ = movesAbilities_.getVal(NINJASK).getMoves();
        assertEq(10, allMoves_.size());
        assertTrue(allMoves_.containsObj(ARMURE));
        assertTrue(allMoves_.containsObj(GRIFFE));
        assertTrue(allMoves_.containsObj(PIQURE));
        assertTrue(allMoves_.containsObj(VAMPIRISME));
        assertTrue(allMoves_.containsObj(JET_DE_SABLE));
        assertTrue(allMoves_.containsObj(COMBO_GRIFFE));
        assertTrue(allMoves_.containsObj(LIRE_ESPRIT));
        assertTrue(allMoves_.containsObj(GRINCEMENT));
        assertTrue(allMoves_.containsObj(REFLET));
        assertTrue(allMoves_.containsObj(TAILLADE));
        StringList abilities_ = movesAbilities_.getVal(NINJASK).getAbilities();
        assertEq(1, abilities_.size());
        assertTrue(abilities_.containsObj(TURBO));
        allMoves_ = movesAbilities_.getVal(MUNJA).getMoves();
        assertEq(6, allMoves_.size());
        assertTrue(allMoves_.containsObj(ARMURE));
        assertTrue(allMoves_.containsObj(GRIFFE));
        assertTrue(allMoves_.containsObj(VAMPIRISME));
        assertTrue(allMoves_.containsObj(JET_DE_SABLE));
        assertTrue(allMoves_.containsObj(COMBO_GRIFFE));
        assertTrue(allMoves_.containsObj(LIRE_ESPRIT));
        abilities_ = movesAbilities_.getVal(MUNJA).getAbilities();
        assertEq(1, abilities_.size());
        assertTrue(abilities_.containsObj(GARDE_MYSTIK));
        assertEq(20,fighter_.getLevel());
        assertEq(72, fighter_.getHappiness());
        assertEq(new Rate("1"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
    }

    @Test
    public void calculateNewLevel6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        assertEq(100,fighter_.getLevel());
        assertEq(new Rate("0"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(2, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
        assertTrue(movesAbilities_.contains(NINJASK));
        StringList allMoves_ = movesAbilities_.getVal(NINJASK).getMoves();
        assertEq(16, allMoves_.size());
        assertTrue(allMoves_.containsObj(ARMURE));
        assertTrue(allMoves_.containsObj(GRIFFE));
        assertTrue(allMoves_.containsObj(PIQURE));
        assertTrue(allMoves_.containsObj(VAMPIRISME));
        assertTrue(allMoves_.containsObj(JET_DE_SABLE));
        assertTrue(allMoves_.containsObj(COMBO_GRIFFE));
        assertTrue(allMoves_.containsObj(LIRE_ESPRIT));
        assertTrue(allMoves_.containsObj(GRINCEMENT));
        assertTrue(allMoves_.containsObj(REFLET));
        assertTrue(allMoves_.containsObj(TAILLADE));
        assertTrue(allMoves_.containsObj(DANSE_LAMES));
        assertTrue(allMoves_.containsObj(TRANCHE));
        assertTrue(allMoves_.containsObj(HATE));
        assertTrue(allMoves_.containsObj(RELAIS));
        assertTrue(allMoves_.containsObj(PLAIE_CROIX));
        assertTrue(allMoves_.containsObj(GIGA_SANGSUE));
        StringList abilities_ = movesAbilities_.getVal(NINJASK).getAbilities();
        assertEq(1, abilities_.size());
        assertTrue(abilities_.containsObj(TURBO));
        allMoves_ = movesAbilities_.getVal(MUNJA).getMoves();
        assertEq(11, allMoves_.size());
        assertTrue(allMoves_.containsObj(ARMURE));
        assertTrue(allMoves_.containsObj(GRIFFE));
        assertTrue(allMoves_.containsObj(VAMPIRISME));
        assertTrue(allMoves_.containsObj(JET_DE_SABLE));
        assertTrue(allMoves_.containsObj(COMBO_GRIFFE));
        assertTrue(allMoves_.containsObj(LIRE_ESPRIT));
        assertTrue(allMoves_.containsObj(DEPIT));
        assertTrue(allMoves_.containsObj(ONDE_FOLIE));
        assertTrue(allMoves_.containsObj(OMBRE_PORTEE));
        assertTrue(allMoves_.containsObj(ANTI_SOIN));
        assertTrue(allMoves_.containsObj(BALL_OMBRE));
        abilities_ = movesAbilities_.getVal(MUNJA).getAbilities();
        assertEq(1, abilities_.size());
        assertTrue(abilities_.containsObj(GARDE_MYSTIK));
    }

    @Test
    public void calculateNewLevel7Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(MUNJA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        assertEq(100,fighter_.getLevel());
        assertEq(new Rate("0"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
    }

    @Test
    public void calculateNewLevel8Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(MUNJA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 99);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("1"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        assertEq(100,fighter_.getLevel());
        assertEq(new Rate("0"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
    }

    @Test
    public void calculateNewLevel9Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
        assertEq(32,fighter_.getLevel());
        assertEq(70, fighter_.getHappiness());
        assertEq(new Rate("0"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
    }

    @Test
    public void calculateNewLevel10Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
        assertEq(32,fighter_.getLevel());
        assertEq(70, fighter_.getHappiness());
        assertEq(new Rate("1"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
    }

    @Test
    public void calculateNewLevel11Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("1"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringMap<MovesAbilities> movesAbilities_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, movesAbilities_.size());
        assertEq(0, fighter_.getMovesToBeLearnt().size());
        assertEq(32,fighter_.getLevel());
        assertEq(70, fighter_.getHappiness());
        assertEq(new Rate("1"), fighter_.getWonExpSinceLastLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
    }

    @Test
    public void variationLeftHp1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.variationLeftHp(new Rate("1"));
        assertEq(new Rate("271/5"),fighter_.getRemainingHp());
    }

    @Test
    public void variationLeftHp2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.variationLeftHp(new Rate("-1"));
        assertEq(new Rate("266/5"),fighter_.getRemainingHp());
    }

    @Test
    public void wonEvStatistic1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.wonEvStatistic(Statistic.ATTACK, (short) 1, (short) 100, _data_);
        assertEq(2, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void wonEvStatistic2Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(BABIMANTA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 20);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(MORPHING, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POUV_ANTIQUE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 90);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate(3));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.wonEvStatistic(Statistic.ATTACK, (short) 20, (short) 100, _data_);
        assertEq(100, fighter_.getEv().getVal(Statistic.ATTACK).intValue());
    }

    @Test
    public void learnMovesWithoutEvolving1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.learnMovesWithoutEvolving(new StringList(POURSUITE,ULTRASON,POUV_ANTIQUE,BROUHAHA), _data_);
        StringMap<UsesOfMove> map_ = fighter_.getMoves();
        assertEq(4, map_.size());
        assertTrue(map_.contains(ULTRASON));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POURSUITE));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(ULTRASON).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POURSUITE).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
        map_ = fighter_.getCurrentMoves();
        assertEq(4, map_.size());
        assertTrue(map_.contains(ULTRASON));
        assertTrue(map_.contains(BROUHAHA));
        assertTrue(map_.contains(POURSUITE));
        assertTrue(map_.contains(POUV_ANTIQUE));
        assertEq(10, map_.getVal(ULTRASON).getCurrent());
        assertEq(10, map_.getVal(BROUHAHA).getCurrent());
        assertEq(10, map_.getVal(POURSUITE).getCurrent());
        assertEq(10, map_.getVal(POUV_ANTIQUE).getCurrent());
    }

    @Test
    public void noPowerPointForLastUsedMove1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 0);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        assertTrue(fighter_.noPowerPointForLastUsedMove());
        fighter_.setUsedMoveLastRound(IMPLORE);
        assertTrue(fighter_.noPowerPointForLastUsedMove());
        fighter_.setUsedMoveLastRound(ULTRASON);
        assertTrue(fighter_.noPowerPointForLastUsedMove());
        fighter_.setUsedMoveLastRound(DETECTION);
        assertTrue(!fighter_.noPowerPointForLastUsedMove());
    }

    @Test
    public void resistingTypes1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 0);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        assertEq(0, fighter_.resistingTypes(_data_).size());
        fighter_.getLastSufferedMoveTypes().add(ROCHE);
        StringList list_ = fighter_.resistingTypes(_data_);
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(COMBAT));
        assertTrue(list_.containsObj(SOL));
        assertTrue(list_.containsObj(ACIER));
    }

    @Test
    public void spendPowerPoint1Test() {
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 0);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        assertTrue(fighter_.spendPowerPoint(DETECTION, _data_));
        assertTrue(fighter_.spendPowerPoint(ROC_BOULET, _data_));
        assertTrue(fighter_.spendPowerPoint(ROULADE, _data_));
        fighter_.setUsedMoveLastRound(ROULADE);
        assertTrue(!fighter_.spendPowerPoint(ROULADE, _data_));
    }

    @Test
    public void getMoves1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        NatStringTreeMap<Boolean> map_ = fighter_.getMoves(NULL_REF);
        assertEq(4, map_.size());
        assertTrue(map_.getVal(DETECTION));
        assertTrue(map_.getVal(ULTRASON));
        assertTrue(map_.getVal(BROUHAHA));
        assertTrue(map_.getVal(POURSUITE));
    }

    @Test
    public void getMoves2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        NatStringTreeMap<Boolean> map_ = fighter_.getMoves(TARINORME);
        assertEq(12, map_.size());
        assertTrue(map_.getVal(DETECTION));
        assertTrue(map_.getVal(ULTRASON));
        assertTrue(map_.getVal(BROUHAHA));
        assertTrue(map_.getVal(POURSUITE));
        assertTrue(!map_.getVal(CHARGE));
        assertTrue(!map_.getVal(GRAVITE));
        assertTrue(!map_.getVal(VOL_MAGNETIK));
        assertTrue(!map_.getVal(MUR_DE_FER));
        assertTrue(!map_.getVal(BOMBAIMANT));
        assertTrue(!map_.getVal(REGARD_NOIR));
        assertTrue(!map_.getVal(CAGE_ECLAIR));
        assertTrue(!map_.getVal(EBOULEMENT));
    }

    @Test
    public void getMoves3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 30);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 70);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("2790"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        NatStringTreeMap<Boolean> map_ = fighter_.getMoves(NULL_REF);
        assertEq(5, map_.size());
        assertTrue(map_.getVal(DETECTION));
        assertTrue(map_.getVal(ULTRASON));
        assertTrue(map_.getVal(BROUHAHA));
        assertTrue(map_.getVal(POURSUITE));
        assertTrue(!map_.getVal(EBOULEMENT));
    }

    @Test
    public void getAbilities1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringList list_ = fighter_.getAbilities(NULL_REF);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(METEO));
    }

    @Test
    public void getAbilities2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.setWonExp(new Rate("3"));
        fighter_.calculateNewLevel(diff_, _data_, new StringList());
        StringList list_ = fighter_.getAbilities(TARINORME);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(MAGNEPIEGE));
        assertTrue(list_.containsObj(FERMETE));
    }

    @Test
    public void isKoAt1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        assertTrue(!fighter_.isKoAt((byte) 0));
    }

    @Test
    public void isKoAt2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.getRemainingHp().affectZero();
        fighter_.setGroundPlaceSubst((byte) 1);
        assertTrue(!fighter_.isKoAt((byte) 0));
    }

    @Test
    public void isKoAt3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.getEv().put(Statistic.ATTACK, (short) 1);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("1"));
        Fighter fighter_ = new Fighter(pokemonUser_, _data_, (byte) 0);
        fighter_.getRemainingHp().affectZero();
        assertTrue(fighter_.isKoAt((byte) 0));
    }
}
