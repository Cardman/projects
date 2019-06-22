package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.effects.EffectWhileSendingSimple;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypeDamageBoost;
import aiki.fight.util.TypesDuo;
import aiki.fight.util.WeatherType;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

final class InitializationAbilities {

    static final String ARMUROUILLEE = "ARMUROUILLEE";

    static final String SERENITE = "SERENITE";

    static final String TURBO = "TURBO";

    static final String STATIK = "STATIK";

    static final String PYROMANE = "PYROMANE";

    static final String TOXITOUCHE = "TOXITOUCHE";

    static final String SNIPER = "SNIPER";

    static final String TETE_DE_ROC = "TETE_DE_ROC";

    static final String PEAU_MIRACLE_QUATER = "PEAU_MIRACLE_QUATER";

    static final String PEAU_MIRACLE_TER = "PEAU_MIRACLE_TER";

    static final String PEAU_MIRACLE_BIS = "PEAU_MIRACLE_BIS";

    static final String PEAU_MIRACLE = "PEAU_MIRACLE";

    static final String POISSON = "POISSON";

    static final String MUE = "MUE";

    static final String TECHNICIEN = "TECHNICIEN";

    static final String ALEA_STAT = "ALEA_STAT";

    static final String TELECHARGE = "TELECHARGE";

    static final String DEGUISEMENT = "DEGUISEMENT";

    static final String PROTEEN = "PROTEEN";

    static final String AILES_BOURRASQUE = "AILES_BOURRASQUE";

    static final String AURA_INVERSEE = "AURA_INVERSEE";

    static final String AURA_TENEBREUSE = "AURA_TENEBREUSE";

    static final String TERA_VOLTAGE = "TERA_VOLTAGE";

    static final String TENSION = "TENSION";

    static final String REGARD_VIF = "REGARD_VIF";

    static final String CORPS_SAIN = "CORPS_SAIN";

    static final String ATTENTION = "ATTENTION";

    static final String ARMUMAGMA = "ARMUMAGMA";

    static final String ANTI_BRUIT = "ANTI_BRUIT";

    static final String CHANCEUX = "CHANCEUX";

    static final String SOLIDE_ROC = "SOLIDE_ROC";

    static final String INCONSCIENT = "INCONSCIENT";

    static final String MULTITYPE = "MULTITYPE";

    static final String COLERIQUE = "COLERIQUE";

    static final String INFILTRATION = "INFILTRATION";

    static final String MATINAL = "MATINAL";

    static final String AIR_LOCK = "AIR_LOCK";

    static final String COEUR_NOBLE = "COEUR_NOBLE";

    static final String TIRS = "TIRS";

    static final String MAGNEPIEGE = "MAGNEPIEGE";

    static final String BOOM_FINAL = "BOOM_FINAL";

    static final String LENTITEINTEE = "LENTITEINTEE";

    static final String ECRAN_POUDRE = "ECRAN_POUDRE";

    static final String REGE_FORCE = "REGE_FORCE";

    static final String ACHARNE = "ACHARNE";

    static final String IMPASSIBLE = "IMPASSIBLE";

    static final String HYPER_CUTTER = "HYPER_CUTTER";

    static final String SUINTEMENT = "SUINTEMENT";

    static final String TELEPATHE = "TELEPATHE";

    static final String MULTI_COUPS = "MULTI_COUPS";

    static final String ADAPTABILITE = "ADAPTABILITE";

    static final String IMPUDENCE = "IMPUDENCE";

    static final String GARDE_AMIE = "GARDE_AMIE";

    static final String GARDE_POUR_SOI = "GARDE_POUR_SOI";

    static final String GARDE_MYSTIK = "GARDE_MYSTIK";

    static final String IGNIFUGE = "IGNIFUGE";

    static final String CONTRAIRE = "CONTRAIRE";

    static final String SIMPLE = "SIMPLE";

    static final String FREIN = "FREIN";

    static final String SYNCHRO = "SYNCHRO";

    static final String ANNULE_GARDE = "ANNULE_GARDE";

    static final String METEO = "METEO";

    static final String ABSORB_VOLT = "ABSORB_VOLT";

    static final String ABSORB_EAU = "ABSORB_EAU";

    static final String CRAN = "CRAN";

    static final String PIED_RAPIDE = "PIED_RAPIDE";

    static final String PIED_VELOCE = "PIED_VELOCE";

    static final String GLOUTONNERIE = "GLOUTONNERIE";

    static final String SANS_LIMITE = "SANS_LIMITE";

    static final String PEAU_DURE = "PEAU_DURE";

    static final String FARCEUR = "FARCEUR";

    static final String FEUILLE_PETITE = "FEUILLE_PETITE";

    static final String FEUILLE_GARDE = "FEUILLE_GARDE";

    static final String GARDE_MAGIK = "GARDE_MAGIK";

    static final String TERA_VOLT = "TERA_VOLT";

    static final String MAUVAIS_REVE = "MAUVAIS_REVE";

    static final String MEDIC_NATURE = "MEDIC_NATURE";

    static final String ARMURBASTON = "ARMURBASTON";

    static final String PRESSION = "PRESSION";

    static final String CALQUE = "CALQUE";

    static final String LEVITATION = "LEVITATION";

    static final String PARATONNERRE = "PARATONNERRE";

    static final String NORMALISE = "NORMALISE";

    static final String SECHERESSE = "SECHERESSE";

    static final String TANT_PIS = "TANT_PIS";

    static final String COEUR_SOIN = "COEUR_SOIN";

    static final String MAGICIEN = "MAGICIEN";

    static final String SYMBIOSE = "SYMBIOSE";

    static final String POISSEUX = "POISSEUX";

    static final String BAJOUES = "BAJOUES";

    static final String AROMA_VOILE = "AROMA_VOILE";

    static final String FLORA_VOILE = "FLORA_VOILE";

    static final String PEAU_FEERIQUE = "PEAU_FEERIQUE";

    static final String JOLI_SOURIRE = "JOLI_SOURIRE";

    static final String QUERELLEUR = "QUERELLEUR";

    static final String ESSAIM = "ESSAIM";

    static final String OEIL_COMPOSE = "OEIL_COMPOSE";

    static final String ENVELOCAPE = "ENVELOCAPE";

    static final String GLISSADE = "GLISSADE";

    static final String FERMETE = "FERMETE";

    static final String MOITEUR = "MOITEUR";

    static final String CONTRE = "CONTRE";

    static final String CRACHIN = "CRACHIN";

    static final String GARDE = "GARDE";

    static final String FOUR = "FOUR";

    static final String ABRI_CAPE = "ABRI_CAPE";

    private static final String TENEBRE = InitializationDataBase.TENEBRE;

    private static final String SPECTRE = InitializationDataBase.SPECTRE;

    private static final String SOL = InitializationDataBase.SOL;

    private static final String ROCHE = InitializationDataBase.ROCHE;

    private static final String PSY = InitializationDataBase.PSY;

    private static final String PLANTE = InitializationDataBase.PLANTE;

    private static final String NORMAL = InitializationDataBase.NORMAL;

    private static final String FEU = InitializationDataBase.FEU;

    private static final String ELECTRIQUE = InitializationDataBase.ELECTRIQUE;

    private static final String EAU = InitializationDataBase.EAU;

    private static final String COMBAT = InitializationDataBase.COMBAT;

    private static final String FEE = InitializationDataBase.FEE;

    private static final String POISON = InitializationDataBase.POISON;

    private static final String VOL = InitializationDataBase.VOL;

    private static final String ZENITH = InitializationMoves.ZENITH;
    private static final String TEMPETESABLE = InitializationMoves.TEMPETESABLE;
    private static final String RUNE_PROTECT = InitializationMoves.RUNE_PROTECT;
    private static final String PROTECTION = InitializationMoves.PROTECTION;
    private static final String ORAGE = InitializationMoves.ORAGE;
    private static final String MUR_LUMIERE = InitializationMoves.MUR_LUMIERE;
    private static final String MALEDICTION_2 = InitializationMoves.MALEDICTION_2;
    private static final String JET_DE_SABLE = InitializationMoves.JET_DE_SABLE;
    private static final String ENTRAVE = InitializationMoves.ENTRAVE;
    private static final String ENCORE = InitializationMoves.ENCORE;
    private static final String EMBARGO = InitializationMoves.EMBARGO;
    private static final String ECHANGE = InitializationMoves.ECHANGE;
    private static final String DANSE_PLUIE = InitializationMoves.DANSE_PLUIE;
    private static final String BRUME = InitializationMoves.BRUME;
    private static final String PIEGE_DE_ROC = InitializationMoves.PIEGE_DE_ROC;
    private static final String PICOTS = InitializationMoves.PICOTS;
    private static final String REQUIEM = InitializationMoves.REQUIEM;
    private static final String PASSE_PASSE = InitializationMoves.PASSE_PASSE;
    private static final String LARCIN = InitializationMoves.LARCIN;
    private static final String IMITATION = InitializationMoves.IMITATION;
    private static final String IMPLORE = InitializationMoves.IMPLORE;
    private static final String SOMMEIL_REPOS = InitializationStatus.SOMMEIL_REPOS;
    private static final String SOMMEIL = InitializationStatus.SOMMEIL;
    private static final String PARALYSIE_FORTE = InitializationStatus.PARALYSIE_FORTE;
    private static final String PARALYSIE = InitializationStatus.PARALYSIE;
    private static final String BRULURE = InitializationStatus.BRULURE;
    private static final String GEL = InitializationStatus.GEL;
    private static final String AMOUR = InitializationStatus.AMOUR;
    private static final String VAMPIGRAINE = InitializationStatus.VAMPIGRAINE;
    private static final String POISON_GRAVE = InitializationStatus.POISON_GRAVE;
    private static final String PEUR = InitializationStatus.PEUR;
    private static final String POISON_ST = InitializationStatus.POISON_ST;

    private static final String AUTRE = DataBase.AUTRE;

    private static final String F = "F";

    private static final String NULL_REF = InitializationDataBase.NULL_REF;

    private InitializationAbilities() {
    }
    static void initAllAbilities(DataBase _data) {
        EffectEndRoundIndividual effectEndRoundIndividual_;
        EffectWhileSending effectWhileSending_;
        AbilityData ficheCapacite_;
        EffectEndRoundTeam effectEndRoundTeam_;
        EffectStatistic effetStatistique_;
        EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_;
        EffectEndRoundMultiRelation effectEndRoundMultiRelation_;
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setDecreaseNecStepsHatch(5);
        _data.completeMembers(FOUR,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultPower("VAR__FIGHTER_PP__ANTI_AIR*VAR__FIGHTER_PP__SEISME");
        _data.completeMembers(GARDE,ficheCapacite_);
        _data.completeMembers(CRACHIN,defaultFicheCapacite());
        _data.completeMembers(CONTRE,defaultFicheCapacite());
        _data.completeMembers(MOITEUR,defaultFicheCapacite());
        _data.completeMembers(FERMETE,defaultFicheCapacite());
        _data.completeMembers(GLISSADE,defaultFicheCapacite());
        _data.completeMembers(ENVELOCAPE,defaultFicheCapacite());
        _data.completeMembers(OEIL_COMPOSE,defaultFicheCapacite());
        _data.completeMembers(ESSAIM,defaultFicheCapacite());
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getBreakFoeImmune().add(new TypesDuo(NORMAL,SPECTRE));
        ficheCapacite_.getBreakFoeImmune().add(new TypesDuo(COMBAT,SPECTRE));
        _data.completeMembers(QUERELLEUR,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getSingleStatus().addEvent(AMOUR, LgInt.one());
        _data.completeMembers(JOLI_SOURIRE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getChangingBoostTypes().put(NORMAL, new TypeDamageBoost(FEE, new Rate("3/2")));
        _data.completeMembers(PEAU_FEERIQUE, ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuStatusTypes().put(PLANTE, new StringList(SOMMEIL,PARALYSIE));
        ficheCapacite_.getImmuLowStatisTypes().put(PLANTE, new EnumList<Statistic>(Statistic.ATTACK,Statistic.SPECIAL_ATTACK));
        _data.completeMembers(FLORA_VOILE, ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuAllyFromMoves().add(EMBARGO);
        ficheCapacite_.getImmuAllyFromMoves().add(ENCORE);
        ficheCapacite_.getImmuAllyFromMoves().add(ENTRAVE);
        _data.completeMembers(AROMA_VOILE, ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setHealHpWhileUsingBerry(new Rate("1/4"));
        _data.completeMembers(BAJOUES, ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getLowStatFoeHit().put(Statistic.SPEED, (byte) -1);
        _data.completeMembers(POISSEUX, ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setGiveItemToAllyHavingUsed(true);
        _data.completeMembers(SYMBIOSE, ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setTakeItemByDamagingMove(true);
        _data.completeMembers(MAGICIEN, ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectEndRoundTeam_ = defaultEffectEndRoundTeam();
        effectEndRoundTeam_.setDeleteAllStatusAlly(new Rate("1"));
        effectEndRoundTeam_.setEndRoundRank(24);
        effectEndRoundTeam_.setTargetChoice(TargetChoice.NOTHING);
        ficheCapacite_.getEffectEndRound().add(effectEndRoundTeam_);
        _data.completeMembers(COEUR_SOIN,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectEndRoundTeam_ = defaultEffectEndRoundTeam();
        effectEndRoundTeam_.setDeleteAllStatus(new Rate("1"));
//        effectEndRoundTeam_.setEndRoundRank(24);
        effectEndRoundTeam_.setEndRoundRank(65);
        effectEndRoundTeam_.setTargetChoice(TargetChoice.NOTHING);
        ficheCapacite_.getEffectEndRound().add(effectEndRoundTeam_);
        _data.completeMembers(TANT_PIS,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectWhileSending_ = defaultEffectWhileSending();
        effectWhileSending_.setEnabledWeather(ZENITH);
        ficheCapacite_.getEffectSending().add(effectWhileSending_);
        _data.completeMembers(SECHERESSE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setTypeForMoves(NORMAL);
        _data.completeMembers(NORMALISE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getBoostStatRankProtected().put(Statistic.SPECIAL_ATTACK,(byte)1);
        ficheCapacite_.getImmuMoveTypesByWeather().put(NULL_REF,new StringList(ELECTRIQUE));
        //validateficheCapacite_.getImmuMoveTypesByWeather().put("GRELE",new StringList("ELECTRIQUE"));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ZENITH,new StringList(ELECTRIQUE));
        ficheCapacite_.getImmuMoveTypesByWeather().put(DANSE_PLUIE,new StringList(ELECTRIQUE));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ORAGE,new StringList(ELECTRIQUE));
        ficheCapacite_.getImmuMoveTypesByWeather().put(TEMPETESABLE,new StringList(ELECTRIQUE));
        _data.completeMembers(PARATONNERRE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuMoveTypesByWeather().put(NULL_REF,new StringList(SOL));
        //validateficheCapacite_.getImmuMoveTypesByWeather().put("GRELE",new StringList("SOL"));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ZENITH,new StringList(SOL));
        ficheCapacite_.getImmuMoveTypesByWeather().put(DANSE_PLUIE,new StringList(SOL));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ORAGE,new StringList(SOL));
        ficheCapacite_.getImmuMoveTypesByWeather().put(TEMPETESABLE,new StringList(SOL));
        _data.completeMembers(LEVITATION,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectWhileSending_ = defaultEffectWhileSending();
        effectWhileSending_.setCopyingAbility(true);
        ficheCapacite_.getEffectSending().add(effectWhileSending_);
        _data.completeMembers(CALQUE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setNbUsedPp(1);
        _data.completeMembers(PRESSION,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setImmuCh(true);
        _data.completeMembers(ARMURBASTON,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setHealedStatusBySwitch(true);
        _data.completeMembers(MEDIC_NATURE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuMove().add(PICOTS);
        ficheCapacite_.getImmuMove().add(PIEGE_DE_ROC);
        //validate ficheCapacite_.getImmuMove().add("CAUCHEMAR");
        ficheCapacite_.getImmuMove().add(MALEDICTION_2);
        //validate ficheCapacite_.getImmuMove().add("VAMPIGRAINE");
        //validate ficheCapacite_.getImmuWeather().add("GRELE");
        ficheCapacite_.getImmuWeather().add(TEMPETESABLE);
        ficheCapacite_.setImmuDamageTrappingMoves(true);
        ficheCapacite_.setImmuDamageRecoil(true);
        ficheCapacite_.getImmuAbility().add(MAUVAIS_REVE);
        ficheCapacite_.getImmuAbility().add(TERA_VOLT);
        ficheCapacite_.getImmuStatusBeginRound().add(PARALYSIE);
        ficheCapacite_.getImmuStatusBeginRound().add(PARALYSIE_FORTE);
        ficheCapacite_.getImmuStatus().put(NULL_REF,new StringList(BRULURE,POISON_ST,POISON_GRAVE));
        //validateficheCapacite_.getImmuStatus().put("GRELE",new StringList("BRULURE","POISON","POISON_GRAVE"));
        ficheCapacite_.getImmuStatus().put(ZENITH,new StringList(BRULURE,POISON_ST,POISON_GRAVE));
        ficheCapacite_.getImmuStatus().put(DANSE_PLUIE,new StringList(BRULURE,POISON_ST,POISON_GRAVE));
        ficheCapacite_.getImmuStatus().put(ORAGE,new StringList(BRULURE,POISON_ST,POISON_GRAVE));
        ficheCapacite_.getImmuStatus().put(TEMPETESABLE,new StringList(BRULURE,POISON_ST,POISON_GRAVE));
        _data.completeMembers(GARDE_MAGIK,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuStatus().put(ZENITH,new StringList(PEUR,BRULURE,POISON_ST,POISON_GRAVE));
        _data.completeMembers(FEUILLE_GARDE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuStatus().put(ZENITH,new StringList(VAMPIGRAINE,BRULURE,POISON_ST,POISON_GRAVE));
        _data.completeMembers(FEUILLE_PETITE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getIncreasedPrio().put(AUTRE,(short)1);
        _data.completeMembers(FARCEUR,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setRecoilDamageFoe(new Rate("1/8"));
        _data.completeMembers(PEAU_DURE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setImmuDamageRecoil(true);
        ficheCapacite_.setImmuRechargeRound(true);
        ficheCapacite_.setCancelSecEffectOwner(true);
        ficheCapacite_.setMultPower("13/10");
        _data.completeMembers(SANS_LIMITE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMaxHpForUsingBerry(new Rate("1/2"));
        _data.completeMembers(GLOUTONNERIE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectEndRoundMultiRelation_ = defaultEffectEndRoundMultiRelation();
        effectEndRoundMultiRelation_.getDamageByStatus().put(SOMMEIL,new Rate("1/8"));
        effectEndRoundMultiRelation_.getDamageByStatus().put(SOMMEIL_REPOS,new Rate("1/8"));
        effectEndRoundMultiRelation_.setEndRoundRank(38);
        effectEndRoundMultiRelation_.setTargetChoice(TargetChoice.TOUS_ADV);
        ficheCapacite_.getEffectEndRound().add(effectEndRoundMultiRelation_);
        _data.completeMembers(MAUVAIS_REVE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,PARALYSIE));
        ficheCapacite_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,BRULURE));
        ficheCapacite_.getMultStat().put(Statistic.SPEED,"3/2*cardinal(inter({VAR__FIGHTER_STATUTS},{BRULURE;POISON_GRAVE;SOMMEIL_REPOS;POISON_ST;PARALYSIE;SOMMEIL;GEL}))+caracferme(cardinal(complementaire({BRULURE;POISON_GRAVE;SOMMEIL_REPOS;POISON_ST;PARALYSIE;SOMMEIL;GEL},{VAR__FIGHTER_STATUTS})),0,0)");
        _data.completeMembers(PIED_VELOCE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,PARALYSIE));
        ficheCapacite_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,BRULURE));
        ficheCapacite_.getMultStat().put(Statistic.SPEED,"VAR__COEFF_EFF_BASE_TYPES_FIGHTER__ROCHE");
        _data.completeMembers(PIED_RAPIDE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.ATTACK,PARALYSIE));
        ficheCapacite_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.ATTACK,BRULURE));
        ficheCapacite_.getMultStat().put(Statistic.ATTACK,"2");
        _data.completeMembers(CRAN,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getHealHpByWeather().put(ORAGE,new Rate("1/16"));
        ficheCapacite_.getImmuWeather().add(ORAGE);
        ficheCapacite_.getHealHpByTypeIfWeather().put(new WeatherType(ORAGE,ELECTRIQUE),new Rate("1/4"));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ORAGE,new StringList(ELECTRIQUE));
        _data.completeMembers(ABRI_CAPE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getHealHpByWeather().put(DANSE_PLUIE,new Rate("1/16"));
        ficheCapacite_.getHealHpByTypeIfWeather().put(new WeatherType(ZENITH,EAU),new Rate("1/4"));
        ficheCapacite_.getHealHpByTypeIfWeather().put(new WeatherType(DANSE_PLUIE,EAU),new Rate("1/4"));
        ficheCapacite_.getHealHpByTypeIfWeather().put(new WeatherType(ORAGE,EAU),new Rate("1/4"));
        ficheCapacite_.getHealHpByTypeIfWeather().put(new WeatherType(NULL_REF,EAU),new Rate("1/4"));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ZENITH,new StringList(EAU));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ORAGE,new StringList(EAU));
        ficheCapacite_.getImmuMoveTypesByWeather().put(DANSE_PLUIE,new StringList(EAU));
        ficheCapacite_.getImmuMoveTypesByWeather().put(NULL_REF,new StringList(EAU));
        _data.completeMembers(ABSORB_EAU,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getHealHpByWeather().put(ORAGE,new Rate("1/16"));
        ficheCapacite_.getImmuWeather().add(ORAGE);
        ficheCapacite_.getHealHpByTypeIfWeather().put(new WeatherType(ZENITH,ELECTRIQUE),new Rate("1/4"));
        ficheCapacite_.getHealHpByTypeIfWeather().put(new WeatherType(ORAGE,ELECTRIQUE),new Rate("1/4"));
        ficheCapacite_.getHealHpByTypeIfWeather().put(new WeatherType(NULL_REF,ELECTRIQUE),new Rate("1/4"));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ZENITH,new StringList(ELECTRIQUE));
        ficheCapacite_.getImmuMoveTypesByWeather().put(ORAGE,new StringList(ELECTRIQUE));
        ficheCapacite_.getImmuMoveTypesByWeather().put(NULL_REF,new StringList(ELECTRIQUE));
        _data.completeMembers(ABSORB_VOLT,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        //validateficheCapacite_.getChgtTypeByWeather().put("GRELE","GLACE");
        ficheCapacite_.getChgtTypeByWeather().put(ZENITH,FEU);
        ficheCapacite_.getChgtTypeByWeather().put(DANSE_PLUIE,EAU);
        ficheCapacite_.getChgtTypeByWeather().put(TEMPETESABLE,ROCHE);
        _data.completeMembers(METEO,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuLowStat().add(Statistic.ACCURACY);
        ficheCapacite_.setBreakProtection(true);
        ficheCapacite_.setAchievedDisappearedPk(true);
        _data.completeMembers(ANNULE_GARDE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getForwardStatus().put(POISON_GRAVE,POISON_ST);
        ficheCapacite_.getForwardStatus().put(BRULURE,BRULURE);
        ficheCapacite_.getForwardStatus().put(GEL,GEL);
        ficheCapacite_.getForwardStatus().put(POISON_ST,POISON_ST);
        ficheCapacite_.getForwardStatus().put(SOMMEIL,SOMMEIL);
        ficheCapacite_.getForwardStatus().put(PARALYSIE,PARALYSIE);
        _data.completeMembers(SYNCHRO,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setSlowing(true);
        _data.completeMembers(FREIN,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultVarBoost(new Rate("2"));
        _data.completeMembers(SIMPLE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultVarBoost(new Rate("-2"));
        _data.completeMembers(CONTRAIRE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultDamageFoe().put(FEU,new Rate("1/2"));
        effectEndRoundIndividual_ = defaultEffectEndRoundIndividual();
        effectEndRoundIndividual_.getMultDamageStatus().put(BRULURE,new Rate("-2"));
        effectEndRoundIndividual_.setEndRoundRank(32);
        effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
        ficheCapacite_.getEffectEndRound().add(effectEndRoundIndividual_);
        _data.completeMembers(IGNIFUGE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuMove().add(IMPLORE);
        ficheCapacite_.getImmuMove().add(LARCIN);
        //validate ficheCapacite_.getImmuMove().add("TOURMAGIK");
        ficheCapacite_.getImmuMove().add(PASSE_PASSE);
        //validate ficheCapacite_.getImmuMove().add("SABOTAGE");
        ficheCapacite_.setImmuSufferedDamageLowEff(true);
        _data.completeMembers(GARDE_MYSTIK,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuMoveTypesByWeather().put(NULL_REF,new StringList(PSY));
        _data.completeMembers(GARDE_POUR_SOI,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultAllyDamage(new Rate("3/4"));
        _data.completeMembers(GARDE_AMIE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatIfKoFoe().put(Statistic.ATTACK,(byte)1);
        _data.completeMembers(IMPUDENCE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultStab(new Rate("4/3"));
        _data.completeMembers(ADAPTABILITE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setNbHits(true);
        _data.completeMembers(MULTI_COUPS,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setImmuDamageAllyMoves(true);
        _data.completeMembers(TELEPATHE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setInflictingDamageInsteadOfSuffering(true);
        _data.completeMembers(SUINTEMENT,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuLowStat().add(Statistic.ATTACK);
        _data.completeMembers(HYPER_CUTTER,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatIfStatutRank().put(new StatisticStatus(Statistic.SPEED,PEUR),(byte)1);
        _data.completeMembers(IMPASSIBLE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatIfLowStat().put(Statistic.ATTACK,(byte)2);
        _data.completeMembers(ACHARNE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setHealedHpRateBySwitch(new Rate("1/3"));
        _data.completeMembers(REGE_FORCE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setCancelSecEffectOther(true);
        _data.completeMembers(ECRAN_POUDRE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultDamage("caracgaucheouvert(VAR__COEFF_EFF,1)+1");
        _data.completeMembers(LENTITEINTEE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setRecoilDamageFoeByKoOwner(new Rate("1/4"));
        _data.completeMembers(BOOM_FINAL,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatAlly().put(Statistic.ACCURACY,new Rate("2"));
        ficheCapacite_.getMultStat().put(Statistic.ACCURACY,"2");
        _data.completeMembers(MAGNEPIEGE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatAlly().put(Statistic.CRITICAL_HIT,new Rate("2"));
        ficheCapacite_.getMultStat().put(Statistic.CRITICAL_HIT,"2");
        _data.completeMembers(TIRS,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatIfDamgeType().put(new StatisticType(Statistic.ATTACK,TENEBRE),(byte)1);
        _data.completeMembers(COEUR_NOBLE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectWhileSending_ = defaultEffectWhileSending();
        effectWhileSending_.setDisableWeather(true);
        ficheCapacite_.getEffectSending().add(effectWhileSending_);
        _data.completeMembers(AIR_LOCK,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getDivideStatusRound().put(POISON_GRAVE,new Rate("2"));
        ficheCapacite_.getDivideStatusRound().put(PARALYSIE,new Rate("2"));
        ficheCapacite_.getDivideStatusRound().put(SOMMEIL,new Rate("2"));
        ficheCapacite_.getDivideStatusRound().put(SOMMEIL_REPOS,new Rate("2"));
        _data.completeMembers(MATINAL,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getIgnFoeTeamMove().add(PROTECTION);
        ficheCapacite_.getIgnFoeTeamMove().add(MUR_LUMIERE);
        ficheCapacite_.getIgnFoeTeamMove().add(RUNE_PROTECT);
        ficheCapacite_.getIgnFoeTeamMove().add(BRUME);
        _data.completeMembers(INFILTRATION,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMaxStatisticsIfCh().add(Statistic.ATTACK);
        _data.completeMembers(COLERIQUE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuMove().add(IMITATION);
        ficheCapacite_.getImmuMove().add(ECHANGE);
        ficheCapacite_.getImmuMove().add(PASSE_PASSE);
        //validate ficheCapacite_.getImmuMove().add("TOURMAGIK");
        ficheCapacite_.setPlate(true);
        _data.completeMembers(MULTITYPE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setIgnFoeStatisBoost(true);
        _data.completeMembers(INCONSCIENT,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultSufferedDamageSuperEff(new Rate("3/4"));
        _data.completeMembers(SOLIDE_ROC,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultEvtRateCh(new Rate("2"));
        _data.completeMembers(CHANCEUX,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getIgnAbility().add(ABSORB_EAU);
        ficheCapacite_.getIgnAbility().add(ABSORB_VOLT);
        ficheCapacite_.getIgnAbility().add(ANTI_BRUIT);
        ficheCapacite_.getIgnAbility().add(ARMUMAGMA);
        ficheCapacite_.getIgnAbility().add(ARMURBASTON);
        ficheCapacite_.getIgnAbility().add(ATTENTION);
        //validate ficheCapacite_.getIgnAbility().add("BENET");
        //validate ficheCapacite_.getIgnAbility().add("COEUR_DE_COQ");
        //validate ficheCapacite_.getIgnAbility().add("COQUE_ARMURE");
        ficheCapacite_.getIgnAbility().add(CORPS_SAIN);
        //validate ficheCapacite_.getIgnAbility().add("DON_FLORAL");
        //validate ficheCapacite_.getIgnAbility().add("ECAILLE_SPECIALE");
        //validate ficheCapacite_.getIgnAbility().add("ECHAUFFEMENT");
        //validate ficheCapacite_.getIgnAbility().add("ECRAN_FUMEE");
        ficheCapacite_.getIgnAbility().add(ECRAN_POUDRE);
        //validate ficheCapacite_.getIgnAbility().add("ESPRIT_VITAL");
        ficheCapacite_.getIgnAbility().add(FERMETE);
        ficheCapacite_.getIgnAbility().add(FEUILLE_GARDE);
        //validate ficheCapacite_.getIgnAbility().add("FILTRE");
        ficheCapacite_.getIgnAbility().add(GARDE_MYSTIK);
        //validate ficheCapacite_.getIgnAbility().add("GLUE");
        //validate ficheCapacite_.getIgnAbility().add("HERBIVORE");
        ficheCapacite_.getIgnAbility().add(HYPER_CUTTER);
        //validate ficheCapacite_.getIgnAbility().add("IGNIFU_VOILE");
        ficheCapacite_.getIgnAbility().add(IGNIFUGE);
        ficheCapacite_.getIgnAbility().add(INCONSCIENT);
        //validate ficheCapacite_.getIgnAbility().add("INSOMNIA");
        //validate ficheCapacite_.getIgnAbility().add("ISOGRAISSE");
        //validate ficheCapacite_.getIgnAbility().add("LAVABO");
        ficheCapacite_.getIgnAbility().add(LEVITATION);
        ficheCapacite_.getIgnAbility().add(MOITEUR);
        //validate ficheCapacite_.getIgnAbility().add("MOMIE");
        //validate ficheCapacite_.getIgnAbility().add("MOTORISE");
        ficheCapacite_.getIgnAbility().add(PARATONNERRE);
        //validate ficheCapacite_.getIgnAbility().add("PEAU_SECHE");
        //validate ficheCapacite_.getIgnAbility().add("PIEDS_CONFUS");
        ficheCapacite_.getIgnAbility().add(REGARD_VIF);
        //validate ficheCapacite_.getIgnAbility().add("RIDEAU_NEIGE");
        ficheCapacite_.getIgnAbility().add(SIMPLE);
        ficheCapacite_.getIgnAbility().add(SOLIDE_ROC);
        ficheCapacite_.getIgnAbility().add(SYNCHRO);
        //validate ficheCapacite_.getIgnAbility().add("TEMPO_PERSO");
        ficheCapacite_.getIgnAbility().add(TENSION);
        //validate ficheCapacite_.getIgnAbility().add("TORCHE");
        //validate ficheCapacite_.getIgnAbility().add("VACCIN");
        //validate ficheCapacite_.getIgnAbility().add("VENTOUSE");
        //validate ficheCapacite_.getIgnAbility().add("VOILE_SABLE");
        ficheCapacite_.setMumy(true);
        _data.completeMembers(TERA_VOLTAGE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getIgnAbility().add(GARDE_MAGIK);
        _data.completeMembers(TERA_VOLT,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuMove().add(REQUIEM);
        _data.completeMembers(ANTI_BRUIT,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultPowerMovesTypesGlobal().put(TENEBRE, new Rate("3/2"));
        ficheCapacite_.getMultPowerMovesTypesGlobal().put(VOL, new Rate("0"));
        _data.completeMembers(AURA_TENEBREUSE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setReverseEffectsPowerMovesTypesGlobal(true);
        _data.completeMembers(AURA_INVERSEE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getIncreasedPrioTypes().put(VOL, (short)1);
        ficheCapacite_.getIncreasedPrioTypes().put(NORMAL, (short)1);
        _data.completeMembers(AILES_BOURRASQUE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setCopyMovesTypes(true);
        _data.completeMembers(PROTEEN,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuLowStat().add(Statistic.SPEED);
        _data.completeMembers(CORPS_SAIN,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setChgtTypeByDamage(true);
        _data.completeMembers(DEGUISEMENT,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectWhileSendingWithStatistic_ = defaultEffectWhileSendingWithStatistic();
        effetStatistique_ = defaultEffetStatistique();
        effetStatistique_.getStatisVarRank().put(Statistic.ATTACK,(byte)1);
        effetStatistique_.getStatisVarRank().put(Statistic.SPECIAL_ATTACK,(byte)1);
        effetStatistique_.setEvtRate(new Rate("1"));
        effetStatistique_.setTargetChoice(TargetChoice.LANCEUR);
        effetStatistique_.setFail(F);
        effectWhileSendingWithStatistic_.setEffect(effetStatistique_);
        ficheCapacite_.getEffectSending().add(effectWhileSendingWithStatistic_);
        _data.completeMembers(TELECHARGE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectWhileSendingWithStatistic_ = defaultEffectWhileSendingWithStatistic();
        effetStatistique_ = defaultEffetStatistique();
        effetStatistique_.getStatisVarRank().put(Statistic.ATTACK,(byte)1);
        effetStatistique_.getStatisVarRank().put(Statistic.SPECIAL_ATTACK,(byte)1);
        effetStatistique_.setEvtRate(new Rate("1/2"));
        effetStatistique_.setTargetChoice(TargetChoice.LANCEUR);
        effetStatistique_.setFail(F);
        effectWhileSendingWithStatistic_.setEffect(effetStatistique_);
        ficheCapacite_.getEffectSending().add(effectWhileSendingWithStatistic_);
        _data.completeMembers(ALEA_STAT,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultPower("3/2*caracdroiteferme(VAR__PUISSANCE_BASE,60)+caracgaucheouvert(VAR__PUISSANCE_BASE,60)");
        _data.completeMembers(TECHNICIEN,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectEndRoundIndividual_ = defaultEffectEndRoundIndividual();
        effectEndRoundIndividual_.setDeleteAllStatus(new Rate("1"));
        effectEndRoundIndividual_.setEndRoundRank(23);
        effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
        ficheCapacite_.getEffectEndRound().add(effectEndRoundIndividual_);
        _data.completeMembers(MUE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        effectEndRoundIndividual_ = defaultEffectEndRoundIndividual();
        effectEndRoundIndividual_.getHealHpByOwnerTypes().put(NULL_REF,new Rate("-1/8"));
        effectEndRoundIndividual_.getHealHpByOwnerTypes().put(POISON,new Rate("1/8"));
//        effectEndRoundIndividual_.setEndRoundRank(31);
        effectEndRoundIndividual_.setEndRoundRank(59);
        effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
        ficheCapacite_.getEffectEndRound().add(effectEndRoundIndividual_);
        _data.completeMembers(POISSON,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatIfCat().put(new StatisticCategory(Statistic.EVASINESS,AUTRE),new Rate("2"));
        _data.completeMembers(PEAU_MIRACLE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatIfCat().put(new StatisticCategory(Statistic.EVASINESS,AUTRE),new Rate("0"));
        _data.completeMembers(PEAU_MIRACLE_BIS,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatIfCat().put(new StatisticCategory(Statistic.ACCURACY,AUTRE),new Rate("0"));
        _data.completeMembers(PEAU_MIRACLE_TER,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getBonusStatRank().put(Statistic.ACCURACY,(byte) 1);
        _data.completeMembers(PEAU_MIRACLE_QUATER,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setImmuDamageRecoil(true);
        _data.completeMembers(TETE_DE_ROC,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuStatus().put(NULL_REF,new StringList(PEUR));
        //validate ficheCapacite_.getImmuStatus().put("GRELE",new StringList("PEUR"));
        ficheCapacite_.getImmuStatus().put(ZENITH,new StringList(PEUR));
        ficheCapacite_.getImmuStatus().put(DANSE_PLUIE,new StringList(PEUR));
        ficheCapacite_.getImmuStatus().put(ORAGE,new StringList(PEUR));
        ficheCapacite_.getImmuStatus().put(TEMPETESABLE,new StringList(PEUR));
        _data.completeMembers(ATTENTION,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setDecreaseNecStepsHatch(1);
        ficheCapacite_.getImmuStatus().put(NULL_REF,new StringList(GEL));
        //validate ficheCapacite_.getImmuStatus().put("GRELE",new StringList("GEL"));
        ficheCapacite_.getImmuStatus().put(ZENITH,new StringList(GEL));
        ficheCapacite_.getImmuStatus().put(DANSE_PLUIE,new StringList(GEL));
        ficheCapacite_.getImmuStatus().put(ORAGE,new StringList(GEL));
        ficheCapacite_.getImmuStatus().put(TEMPETESABLE,new StringList(GEL));
        _data.completeMembers(ARMUMAGMA,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultDamageCh(new Rate("3/2"));
        _data.completeMembers(SNIPER,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getSingleStatus().addEvent(NULL_REF,new LgInt("7"));
        ficheCapacite_.getSingleStatus().addEvent(POISON_ST,new LgInt("3"));
        _data.completeMembers(TOXITOUCHE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getSingleStatus().addEvent(BRULURE,new LgInt("1"));
        _data.completeMembers(PYROMANE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getSingleStatus().addEvent(PARALYSIE,new LgInt("1"));
        _data.completeMembers(STATIK,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getImmuMove().add(JET_DE_SABLE);
        //validate ficheCapacite_.getImmuMove().add("TELEKINESIE");
        ficheCapacite_.getImmuLowStat().add(Statistic.ACCURACY);
        _data.completeMembers(REGARD_VIF,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getBoostStatRankEndRound().put(Statistic.SPEED,(byte)1);
        _data.completeMembers(TURBO,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setMultEvtRateSecEffectOwner(new Rate("2"));
        _data.completeMembers(SERENITE,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.setForbidUseBerryAgainstFoes(true);
        _data.completeMembers(TENSION,ficheCapacite_);
        ficheCapacite_ = defaultFicheCapacite();
        ficheCapacite_.getMultStatIfDamageCat().put(new StatisticCategory(Statistic.SPEED,"PHYSIQUE"),(byte)1);
        _data.completeMembers(ARMUROUILLEE,ficheCapacite_);
    }

    private static EffectEndRoundIndividual defaultEffectEndRoundIndividual() {
        EffectEndRoundIndividual object_ = new EffectEndRoundIndividual();
        object_.setDeleteAllStatus(Rate.zero());
        object_.setRecoilDamage(Rate.zero());
        object_.setHealHp(Rate.zero());
        object_.setHealHpByOwnerTypes(new StringMap<Rate>());
        object_.setMultDamageStatus(new StringMap<Rate>());
        object_.setUserStatusEndRound(NULL_REF);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(NULL_REF);
        object_.setRequiredSuccessfulEffects(new Ints());
        object_.setFailEndRound(NULL_REF);
        return object_;
    }

    private static EffectWhileSending defaultEffectWhileSending() {
        EffectWhileSending object_ = new EffectWhileSendingSimple();
        object_.setEnabledWeather(NULL_REF);
        object_.setMultWeight(Rate.zero());
        return object_;
    }

    private static AbilityData defaultFicheCapacite() {
        AbilityData object_ = new AbilityData();
        object_.setBreakFoeImmune(new EqList<TypesDuo>());
        object_.setChgtTypeByWeather(new StringMap<String>());
        object_.setRecoilDamageFoe(Rate.zero());
        object_.setRecoilDamageFoeByKoOwner(Rate.zero());
        object_.setDivideStatusRound(new StringMap<Rate>());
        object_.setHealHpByWeather(new StringMap<Rate>());
        object_.setIgnAbility(new StringList());
        object_.setIgnFoeTeamMove(new StringList());
        object_.setImmuMove(new StringList());
        object_.setImmuLowStat(new EnumList<Statistic>());
        object_.setImmuLowStatIfStatus(new EqList<StatisticStatus>());
        object_.setImmuWeather(new StringList());
        object_.setImmuAbility(new StringList());
        object_.setImmuStatusBeginRound(new StringList());
        object_.setImmuStatus(new StringMap<StringList>());
        object_.setMultDamageFoe(new StringMap<Rate>());
        object_.setMultDamageCh(Rate.zero());
        object_.setMultAllyDamage(Rate.zero());
        object_.setMultSufferedDamageSuperEff(Rate.zero());
        object_.setMultEvtRateCh(Rate.zero());
        object_.setMultEvtRateSecEffectOwner(Rate.zero());
        object_.setMultPower(NULL_REF);
        object_.setMultDamage(NULL_REF);
        object_.setMultStab(Rate.zero());
        object_.setLowStatFoeHit(new EnumMap<Statistic,Byte>());
        object_.setBonusStatRank(new EnumMap<Statistic,Byte>());
        object_.setBoostStatRankProtected(new EnumMap<Statistic,Byte>());
        object_.setBoostStatRankEndRound(new EnumMap<Statistic,Byte>());
        object_.setMultStatAlly(new EnumMap<Statistic,Rate>());
        object_.setMultStatIfKoFoe(new EnumMap<Statistic,Byte>());
        object_.setMultStatIfLowStat(new EnumMap<Statistic,Byte>());
        object_.setMultStatIfCat(new ObjectMap<StatisticCategory,Rate>());
        object_.setMultStatIfStatutRank(new ObjectMap<StatisticStatus,Byte>());
        object_.setMultStatIfDamageCat(new ObjectMap<StatisticCategory,Byte>());
        object_.setMultStatIfDamgeType(new ObjectMap<StatisticType,Byte>());
        object_.setMultStat(new EnumMap<Statistic,String>());
        object_.setMultVarBoost(Rate.zero());
        object_.setHealedHpRateBySwitch(Rate.zero());
        object_.setIncreasedPrio(new StringMap<Short>());
        object_.setMaxStatisticsIfCh(new EnumList<Statistic>());
        object_.setSingleStatus(new MonteCarloString());
        object_.setForwardStatus(new StringMap<String>());
        object_.setTypeForMoves(NULL_REF);
        object_.setMaxHpForUsingBerry(Rate.zero());
        object_.setHealHpByTypeIfWeather(new ObjectMap<WeatherType,Rate>());
        object_.setChangingBoostTypes(new StringMap<TypeDamageBoost>());
        object_.setImmuLowStatisTypes(new StringMap<EnumList<Statistic>>());
        object_.setImmuStatusTypes(new StringMap<StringList>());
        object_.setImmuAllyFromMoves(new StringList());
        object_.setImmuMoveTypesByWeather(new StringMap<StringList>());
        object_.setEffectEndRound(new CustList<EffectEndRound>());
        object_.setEffectSending(new CustList<EffectWhileSending>());
        object_.setFailStatus(new StringMap<String>());
        object_.setHealHpWhileUsingBerry(Rate.zero());
        object_.setMultPowerMovesTypesGlobal(new StringMap<Rate>());
        object_.setIncreasedPrioTypes(new StringMap<Short>());
        return object_;
    }

    private static EffectEndRoundTeam defaultEffectEndRoundTeam() {
        EffectEndRoundTeam object_ = new EffectEndRoundTeam();
        object_.setDeleteAllStatus(Rate.zero());
        object_.setDeleteAllStatusAlly(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(NULL_REF);
        object_.setRequiredSuccessfulEffects(new Ints());
        object_.setFailEndRound(NULL_REF);
        return object_;
    }

    private static EffectStatistic defaultEffetStatistique() {
        EffectStatistic object_ = new EffectStatistic();
        object_.setStatisVarRank(new EnumMap<Statistic,Byte>());
        object_.setLocalFailStatis(new EnumMap<Statistic,String>());
        object_.setEvtRate(Rate.zero());
        object_.setCopyBoost(new EnumList<Statistic>());
        object_.setSwapBoostStatis(new EnumList<Statistic>());
        object_.setLocalFailSwapBoostStatis(new EnumMap<Statistic,String>());
        object_.setLawBoost(new MonteCarloEnum<Statistic>());
        object_.setCancelLowStat(new EnumList<Statistic>());
        object_.setCancelChgtStat(new EnumList<Statistic>());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(NULL_REF);
        object_.setRequiredSuccessfulEffects(new Ints());
        return object_;
    }

    private static EffectWhileSendingWithStatistic defaultEffectWhileSendingWithStatistic() {
        EffectWhileSendingWithStatistic object_ = new EffectWhileSendingWithStatistic();
        object_.setEffect(defaultEffetStatistique());
        object_.setEnabledWeather(NULL_REF);
        object_.setMultWeight(Rate.zero());
        return object_;
    }

    private static EffectEndRoundMultiRelation defaultEffectEndRoundMultiRelation() {
        EffectEndRoundMultiRelation object_ = new EffectEndRoundMultiRelation();
        object_.setDamageByStatus(new StringMap<Rate>());
        object_.setMultDamageStatus(new StringMap<Rate>());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(NULL_REF);
        object_.setRequiredSuccessfulEffects(new Ints());
        object_.setFailEndRound(NULL_REF);
        return object_;
    }
}
