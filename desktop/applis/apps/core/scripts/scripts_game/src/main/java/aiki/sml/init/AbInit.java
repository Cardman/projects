package aiki.sml.init;
import aiki.instances.*;
import aiki.fight.util.*;
import aiki.fight.effects.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.abilities.*;
import aiki.fight.enums.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
public final class AbInit extends CstIgame{
private AbInit(){}
public static StringMap<AbilityData> ab(){
 StringMap<AbilityData> m3a_ = new StringMap<AbilityData>(new CollCapacity(164));
m3a_.addEntry(I_ABRI_PROT,m0());
m3a_.addEntry(I_ABSORB_EAU,m1());
m3a_.addEntry(I_ABSORB_VOLT,m2());
m3a_.addEntry(I_ACHARNE,m3());
m3a_.addEntry(I_ADAPTABILITE,m4());
m3a_.addEntry(I_AGITATION,m5());
m3a_.addEntry(I_AILES_BOURRASQUE,m6());
m3a_.addEntry(I_AIR_LOCK,m7());
m3a_.addEntry(I_ALERTE_NEIGE,m8());
m3a_.addEntry(I_ANALYSTE,m9());
m3a_.addEntry(I_ANNULE_GARDE,m10());
m3a_.addEntry(I_ANTI_BRUIT,m11());
m3a_.addEntry(I_ARMUMAGMA,m12());
m3a_.addEntry(I_ARMURBASTON,m13());
m3a_.addEntry(I_ARMUROUILLEE,m14());
m3a_.addEntry(I_AROMA_VOILE,m15());
m3a_.addEntry(I_ATTENTION,m16());
m3a_.addEntry(I_AURA_FEERIQUE,m17());
m3a_.addEntry(I_AURA_INVERSEE,m18());
m3a_.addEntry(I_AURA_TENEBREUSE,m19());
m3a_.addEntry(I_BAIGNE_SABLE,m20());
m3a_.addEntry(I_BAJOUES,m21());
m3a_.addEntry(I_BENET,m22());
m3a_.addEntry(I_BOOM_FINAL,m23());
m3a_.addEntry(I_BRASIER,m24());
m3a_.addEntry(I_BRISE_MOULE,m25());
m3a_.addEntry(I_CALQUE,m26());
m3a_.addEntry(I_CHANCEUX,m27());
m3a_.addEntry(I_CHLOROPHYLLE,m28());
m3a_.addEntry(I_CIEL_GRIS,m29());
m3a_.addEntry(I_COEUR_DE_COQ,m30());
m3a_.addEntry(I_COEUR_NOBLE,m31());
m3a_.addEntry(I_COEUR_SOIN,m32());
m3a_.addEntry(I_COLERIQUE,m33());
m3a_.addEntry(I_COLOFORCE,m34());
m3a_.addEntry(I_CONTESTATION,m35());
m3a_.addEntry(I_CONTRASTE,m36());
m3a_.addEntry(I_COQUE_ARMURE,m37());
m3a_.addEntry(I_CORPS_ARDENT,m38());
m3a_.addEntry(I_CORPS_GEL,m39());
m3a_.addEntry(I_CORPS_SAIN,m40());
m3a_.addEntry(I_CRACHIN,m41());
m3a_.addEntry(I_CRAN,m42());
m3a_.addEntry(I_CUVETTE,m43());
m3a_.addEntry(I_DECLIC_TACTIQUE,m44());
m3a_.addEntry(I_DEGUISEMENT,m45());
m3a_.addEntry(I_DELESTAGE,m46());
m3a_.addEntry(I_DON_FLORAL,m47());
m3a_.addEntry(I_ECAILLE_SPECIALE,m48());
m3a_.addEntry(I_ECHAUFFEMENT,m49());
m3a_.addEntry(I_ECRAN_FUMEE,m50());
m3a_.addEntry(I_ECRAN_POUDRE,m51());
m3a_.addEntry(I_ENGRAIS,m52());
m3a_.addEntry(I_ENVELOCAPE,m53());
m3a_.addEntry(I_EPINE_DE_FER,m54());
m3a_.addEntry(I_ESPRIT_VITAL,m55());
m3a_.addEntry(I_ESSAIM,m56());
m3a_.addEntry(I_FARCEUR,m57());
m3a_.addEntry(I_FERMETE,m58());
m3a_.addEntry(I_FEUILLE_GARDE,m59());
m3a_.addEntry(I_FILTRE,m60());
m3a_.addEntry(I_FLORA_VOILE,m61());
m3a_.addEntry(I_FORCE_PURE,m62());
m3a_.addEntry(I_FORCE_SABLE,m63());
m3a_.addEntry(I_FORCE_SOLEIL,m64());
m3a_.addEntry(I_FREIN,m65());
m3a_.addEntry(I_GARDE_AMIE,m66());
m3a_.addEntry(I_GARDE_MAGIK,m67());
m3a_.addEntry(I_GARDE_MYSTIK,m68());
m3a_.addEntry(I_GLISSADE,m69());
m3a_.addEntry(I_GLOUTONNERIE,m70());
m3a_.addEntry(I_GLUCO_VOILE,m71());
m3a_.addEntry(I_GLUE,m72());
m3a_.addEntry(I_GRIFFE_DURE,m73());
m3a_.addEntry(I_HERBIVORE,m74());
m3a_.addEntry(I_HYDRATATION,m75());
m3a_.addEntry(I_HYPER_CUTTER,m76());
m3a_.addEntry(I_IGNIFUGE,m77());
m3a_.addEntry(I_IGNIFU_VOILE,m78());
m3a_.addEntry(I_IMPASSIBLE,m79());
m3a_.addEntry(I_IMPUDENCE,m80());
m3a_.addEntry(I_INCONSCIENT,m81());
m3a_.addEntry(I_INFILTRATION,m82());
m3a_.addEntry(I_INSOMNIA,m83());
m3a_.addEntry(I_INTIMIDANT,m84());
m3a_.addEntry(I_ISOGRAISSE,m85());
m3a_.addEntry(I_JOLI_SOURIRE,m86());
m3a_.addEntry(I_LAVABO,m87());
m3a_.addEntry(I_LENTITEINTEE,m88());
m3a_.addEntry(I_LEVITATION,m89());
m3a_.addEntry(I_MAGICIEN,m90());
m3a_.addEntry(I_MAGNEPIEGE,m91());
m3a_.addEntry(I_MARQUE_OMBRE,m92());
m3a_.addEntry(I_MATINAL,m93());
m3a_.addEntry(I_MAUVAIS_REVE,m94());
m3a_.addEntry(I_MEDIC_NATURE,m95());
m3a_.addEntry(I_MEGA_BLASTER,m96());
m3a_.addEntry(I_METEO,m97());
m3a_.addEntry(I_MINUS,m98());
m3a_.addEntry(I_MOITEUR,m99());
m3a_.addEntry(I_MOMIE,m100());
m3a_.addEntry(I_MOTORISE,m101());
m3a_.addEntry(I_MUE,m102());
m3a_.addEntry(I_MULTITYPE,m103());
m3a_.addEntry(I_MULTI_COUPS,m104());
m3a_.addEntry(I_NORMALISE,m105());
m3a_.addEntry(I_OEIL_COMPOSE,m106());
m3a_.addEntry(I_PARATONNERRE,m107());
m3a_.addEntry(I_PARE_BALLES,m108());
m3a_.addEntry(I_PEAU_DURE,m109());
m3a_.addEntry(I_PEAU_FEERIQUE,m110());
m3a_.addEntry(I_PEAU_GELEE,m111());
m3a_.addEntry(I_PEAU_MIRACLE,m112());
m3a_.addEntry(I_PEAU_SECHE,m113());
m3a_.addEntry(I_PHOBIQUE,m114());
m3a_.addEntry(I_PIEDS_CONFUS,m115());
m3a_.addEntry(I_PIED_VELOCE,m116());
m3a_.addEntry(I_PIEGE,m117());
m3a_.addEntry(I_PLUS,m118());
m3a_.addEntry(I_POING_DE_FER,m119());
m3a_.addEntry(I_POINT_POISON,m120());
m3a_.addEntry(I_POISSEUX,m121());
m3a_.addEntry(I_POSE_SPORE,m122());
m3a_.addEntry(I_PRESSION,m123());
m3a_.addEntry(I_PROGNATHE,m124());
m3a_.addEntry(I_PROTEEN,m125());
m3a_.addEntry(I_PUANTEUR,m126());
m3a_.addEntry(I_QUERELLEUR,m127());
m3a_.addEntry(I_RECOLTE,m128());
m3a_.addEntry(I_REGARD_VIF,m129());
m3a_.addEntry(I_REGE_FORCE,m130());
m3a_.addEntry(I_RIDEAU_NEIGE,m131());
m3a_.addEntry(I_RIVALITE,m132());
m3a_.addEntry(I_SABLE_VOLANT,m133());
m3a_.addEntry(I_SANS_LIMITE,m134());
m3a_.addEntry(I_SECHERESSE,m135());
m3a_.addEntry(I_SERENITE,m136());
m3a_.addEntry(I_SIMPLE,m137());
m3a_.addEntry(I_SNIPER,m138());
m3a_.addEntry(I_SOIN_POISON,m139());
m3a_.addEntry(I_SOLIDE_ROC,m140());
m3a_.addEntry(I_STATIK,m141());
m3a_.addEntry(I_SUINTEMENT,m142());
m3a_.addEntry(I_SYMBIOSE,m143());
m3a_.addEntry(I_SYNCHRO,m144());
m3a_.addEntry(I_TECHNICIEN,m145());
m3a_.addEntry(I_TELECHARGE,m146());
m3a_.addEntry(I_TELEPATHE,m147());
m3a_.addEntry(I_TEMERAIRE,m148());
m3a_.addEntry(I_TEMPO_PERSO,m149());
m3a_.addEntry(I_TENSION,m150());
m3a_.addEntry(I_TERA_VOLTAGE,m151());
m3a_.addEntry(I_TETE_DE_ROC,m152());
m3a_.addEntry(I_TOISON_EPAISSE,m153());
m3a_.addEntry(I_TOISON_HERBUE,m154());
m3a_.addEntry(I_TORCHE,m155());
m3a_.addEntry(I_TORRENT,m156());
m3a_.addEntry(I_TOXITOUCHE,m157());
m3a_.addEntry(I_TURBO,m158());
m3a_.addEntry(I_TURBOBRASIER,m159());
m3a_.addEntry(I_VACCIN,m160());
m3a_.addEntry(I_VENTOUSE,m161());
m3a_.addEntry(I_VICTORIEUX,m162());
m3a_.addEntry(I_VOILE_SABLE,m163());
return m3a_;
}
static AbilityData m0(){
AbilityData m4abilityData_ = Instances.newAbilityData();
StringMap<Rate> m4stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m4stringMapRate_.addEntry(I_ORAGE,Rate.newRate(R_1_16));
m4abilityData_.setHealHpByWeather(m4stringMapRate_);
StringList m4stringList_=new StringList(new CollCapacity(1));
m4stringList_.add(I_ORAGE);
m4abilityData_.setImmuWeather(m4stringList_);
WeatherTypes m4objectMapWeatherTypeRate_=new WeatherTypes(new CollCapacity(1));
m4objectMapWeatherTypeRate_.addEntry(new WeatherType(I_ORAGE,I_ELECTRIQUE),Rate.newRate(R_1_4));
m4abilityData_.setHealHpByTypeIfWeather(m4objectMapWeatherTypeRate_);
StringMap<StringList> m4stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
m4stringList_=new StringList(new CollCapacity(1));
m4stringList_.add(I_ELECTRIQUE);
m4stringMapStringList_.addEntry(I_ORAGE,m4stringList_);
m4abilityData_.setImmuMoveTypesByWeather(m4stringMapStringList_);
return m4abilityData_;
}
static AbilityData m1(){
AbilityData m5abilityData_ = Instances.newAbilityData();
WeatherTypes m5objectMapWeatherTypeRate_=new WeatherTypes(new CollCapacity(6));
m5objectMapWeatherTypeRate_.addEntry(new WeatherType(ES,I_EAU),Rate.newRate(R_1_4));
m5objectMapWeatherTypeRate_.addEntry(new WeatherType(I_ORAGE,I_EAU),Rate.newRate(R_1_4));
m5objectMapWeatherTypeRate_.addEntry(new WeatherType(I_TEMPETESABLE,I_EAU),Rate.newRate(R_1_4));
m5objectMapWeatherTypeRate_.addEntry(new WeatherType(I_ZENITH,I_EAU),Rate.newRate(R_1_4));
m5objectMapWeatherTypeRate_.addEntry(new WeatherType(I_GRELE,I_EAU),Rate.newRate(R_1_4));
m5objectMapWeatherTypeRate_.addEntry(new WeatherType(I_DANSE_PLUIE,I_EAU),Rate.newRate(R_1_4));
m5abilityData_.setHealHpByTypeIfWeather(m5objectMapWeatherTypeRate_);
StringMap<StringList> m5stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m5stringList_=new StringList(new CollCapacity(1));
m5stringList_.add(I_EAU);
m5stringMapStringList_.addEntry(ES,m5stringList_);
m5stringList_=new StringList(new CollCapacity(1));
m5stringList_.add(I_EAU);
m5stringMapStringList_.addEntry(I_GRELE,m5stringList_);
m5stringList_=new StringList(new CollCapacity(1));
m5stringList_.add(I_EAU);
m5stringMapStringList_.addEntry(I_ZENITH,m5stringList_);
m5stringList_=new StringList(new CollCapacity(1));
m5stringList_.add(I_EAU);
m5stringMapStringList_.addEntry(I_DANSE_PLUIE,m5stringList_);
m5stringList_=new StringList(new CollCapacity(1));
m5stringList_.add(I_EAU);
m5stringMapStringList_.addEntry(I_ORAGE,m5stringList_);
m5stringList_=new StringList(new CollCapacity(1));
m5stringList_.add(I_EAU);
m5stringMapStringList_.addEntry(I_TEMPETESABLE,m5stringList_);
m5abilityData_.setImmuMoveTypesByWeather(m5stringMapStringList_);
return m5abilityData_;
}
static AbilityData m2(){
AbilityData m6abilityData_ = Instances.newAbilityData();
WeatherTypes m6objectMapWeatherTypeRate_=new WeatherTypes(new CollCapacity(6));
m6objectMapWeatherTypeRate_.addEntry(new WeatherType(I_DANSE_PLUIE,I_ELECTRIQUE),Rate.newRate(R_1_4));
m6objectMapWeatherTypeRate_.addEntry(new WeatherType(I_ORAGE,I_ELECTRIQUE),Rate.newRate(R_1_4));
m6objectMapWeatherTypeRate_.addEntry(new WeatherType(I_TEMPETESABLE,I_ELECTRIQUE),Rate.newRate(R_1_4));
m6objectMapWeatherTypeRate_.addEntry(new WeatherType(I_ZENITH,I_ELECTRIQUE),Rate.newRate(R_1_4));
m6objectMapWeatherTypeRate_.addEntry(new WeatherType(ES,I_ELECTRIQUE),Rate.newRate(R_1_4));
m6objectMapWeatherTypeRate_.addEntry(new WeatherType(I_GRELE,I_ELECTRIQUE),Rate.newRate(R_1_4));
m6abilityData_.setHealHpByTypeIfWeather(m6objectMapWeatherTypeRate_);
StringMap<StringList> m6stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m6stringList_=new StringList(new CollCapacity(1));
m6stringList_.add(I_ELECTRIQUE);
m6stringMapStringList_.addEntry(ES,m6stringList_);
m6stringList_=new StringList(new CollCapacity(1));
m6stringList_.add(I_ELECTRIQUE);
m6stringMapStringList_.addEntry(I_GRELE,m6stringList_);
m6stringList_=new StringList(new CollCapacity(1));
m6stringList_.add(I_ELECTRIQUE);
m6stringMapStringList_.addEntry(I_ZENITH,m6stringList_);
m6stringList_=new StringList(new CollCapacity(1));
m6stringList_.add(I_ELECTRIQUE);
m6stringMapStringList_.addEntry(I_DANSE_PLUIE,m6stringList_);
m6stringList_=new StringList(new CollCapacity(1));
m6stringList_.add(I_ELECTRIQUE);
m6stringMapStringList_.addEntry(I_ORAGE,m6stringList_);
m6stringList_=new StringList(new CollCapacity(1));
m6stringList_.add(I_ELECTRIQUE);
m6stringMapStringList_.addEntry(I_TEMPETESABLE,m6stringList_);
m6abilityData_.setImmuMoveTypesByWeather(m6stringMapStringList_);
return m6abilityData_;
}
static AbilityData m3(){
AbilityData m7abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> m7enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m7enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)2);
m7abilityData_.setMultStatIfLowStat(m7enumMapStatisticByte_);
return m7abilityData_;
}
static AbilityData m4(){
AbilityData m8abilityData_ = Instances.newAbilityData();
m8abilityData_.setMultStab(Rate.newRate(R_4_3));
return m8abilityData_;
}
static AbilityData m5(){
AbilityData m9abilityData_ = Instances.newAbilityData();
m9abilityData_.setMultPower(R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_CATEGORIE+RB+OC+LB+I_PHYSIQUE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PHYSIQUE+RB+OC+LB+V_ATTAQUE_CATEGORIE+RB+RP+RP);
return m9abilityData_;
}
static AbilityData m6(){
AbilityData m10abilityData_ = Instances.newAbilityData();
StringMap<Short> m10stringMapShort_=new StringMap<Short>(new CollCapacity(1));
m10stringMapShort_.addEntry(I_VOL,(short)1);
m10abilityData_.setIncreasedPrioTypes(m10stringMapShort_);
return m10abilityData_;
}
static AbilityData m7(){
AbilityData m11abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m11custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m11effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m11effectWhileSendingWithStatistic_.setDisableWeather(true);
m11custListEffectWhileSendingWithStatistic_.add(m11effectWhileSendingWithStatistic_);
m11abilityData_.setEffectSending(m11custListEffectWhileSendingWithStatistic_);
return m11abilityData_;
}
static AbilityData m8(){
AbilityData m12abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m12custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m12effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m12effectWhileSendingWithStatistic_.setEnabledWeather(I_GRELE);
m12custListEffectWhileSendingWithStatistic_.add(m12effectWhileSendingWithStatistic_);
m12abilityData_.setEffectSending(m12custListEffectWhileSendingWithStatistic_);
return m12abilityData_;
}
static AbilityData m9(){
AbilityData m13abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m13enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m13enumMapStatisticString_.addEntry(Statistic.ATTACK,R_13_10+OM+V_FIGHTER_DER_JOUE+OP+LP+R_1+OD+V_FIGHTER_DER_JOUE+RP);
m13abilityData_.setMultStat(m13enumMapStatisticString_);
return m13abilityData_;
}
static AbilityData m10(){
AbilityData m14abilityData_ = Instances.newAbilityData();
EnumList<Statistic> m14enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
m14enumListStatistic_.add(Statistic.ACCURACY);
m14abilityData_.setImmuLowStat(m14enumListStatistic_);
m14abilityData_.setBreakProtection(true);
m14abilityData_.setAchievedDisappearedPk(true);
return m14abilityData_;
}
static AbilityData m11(){
AbilityData m15abilityData_ = Instances.newAbilityData();
StringList m15stringList_=new StringList(new CollCapacity(20));
m15stringList_.add(I_ABOIEMENT);
m15stringList_.add(I_BABIL);
m15stringList_.add(I_BERCEUSE);
m15stringList_.add(I_BOURDON);
m15stringList_.add(I_BROUHAHA);
m15stringList_.add(I_CHANT_ANTIQUE);
m15stringList_.add(I_CHANT_CANON);
m15stringList_.add(I_ECHO);
m15stringList_.add(I_GLAS_DE_SOIN);
m15stringList_.add(I_GRINCEMENT);
m15stringList_.add(I_HURLE_TEMPS);
m15stringList_.add(I_HURLEMENT);
m15stringList_.add(I_MEGAPHONE);
m15stringList_.add(I_REQUIEM);
m15stringList_.add(I_RONFLEMENT);
m15stringList_.add(I_RUGISSEMENT);
m15stringList_.add(I_SIFFL_HERBE);
m15stringList_.add(I_SONICBOOM);
m15stringList_.add(I_STRIDO_SON);
m15stringList_.add(I_ULTRASON);
m15abilityData_.setImmuMove(m15stringList_);
return m15abilityData_;
}
static AbilityData m12(){
AbilityData m16abilityData_ = Instances.newAbilityData();
m16abilityData_.setDecreaseNecStepsHatch(1);
StringMap<StringList> m16stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m16stringList_=new StringList(new CollCapacity(1));
m16stringList_.add(I_GEL);
m16stringMapStringList_.addEntry(ES,m16stringList_);
m16stringList_=new StringList(new CollCapacity(1));
m16stringList_.add(I_GEL);
m16stringMapStringList_.addEntry(I_GRELE,m16stringList_);
m16stringList_=new StringList(new CollCapacity(1));
m16stringList_.add(I_GEL);
m16stringMapStringList_.addEntry(I_ZENITH,m16stringList_);
m16stringList_=new StringList(new CollCapacity(1));
m16stringList_.add(I_GEL);
m16stringMapStringList_.addEntry(I_DANSE_PLUIE,m16stringList_);
m16stringList_=new StringList(new CollCapacity(1));
m16stringList_.add(I_GEL);
m16stringMapStringList_.addEntry(I_ORAGE,m16stringList_);
m16stringList_=new StringList(new CollCapacity(1));
m16stringList_.add(I_GEL);
m16stringMapStringList_.addEntry(I_TEMPETESABLE,m16stringList_);
m16abilityData_.setImmuStatus(m16stringMapStringList_);
return m16abilityData_;
}
static AbilityData m13(){
AbilityData m17abilityData_ = Instances.newAbilityData();
m17abilityData_.setImmuCh(true);
return m17abilityData_;
}
static AbilityData m14(){
AbilityData m18abilityData_ = Instances.newAbilityData();
StatisticCategoryByte m18objectMapStatisticCategoryByte_=new StatisticCategoryByte(new CollCapacity(1));
m18objectMapStatisticCategoryByte_.addEntry(new StatisticCategory(Statistic.SPEED,I_PHYSIQUE),(byte)1);
m18abilityData_.setMultStatIfDamageCat(m18objectMapStatisticCategoryByte_);
return m18abilityData_;
}
static AbilityData m15(){
AbilityData m19abilityData_ = Instances.newAbilityData();
StringList m19stringList_=new StringList(new CollCapacity(6));
m19stringList_.add(I_PROVOC);
m19stringList_.add(I_ENCORE);
m19stringList_.add(I_ENTRAVE);
m19stringList_.add(I_TOURMENTE);
m19stringList_.add(I_ANTI_SOIN);
m19stringList_.add(I_ATTRACTION);
m19abilityData_.setImmuAllyFromMoves(m19stringList_);
return m19abilityData_;
}
static AbilityData m16(){
AbilityData m20abilityData_ = Instances.newAbilityData();
StringMap<StringList> m20stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m20stringList_=new StringList(new CollCapacity(1));
m20stringList_.add(I_PEUR);
m20stringMapStringList_.addEntry(ES,m20stringList_);
m20stringList_=new StringList(new CollCapacity(1));
m20stringList_.add(I_PEUR);
m20stringMapStringList_.addEntry(I_GRELE,m20stringList_);
m20stringList_=new StringList(new CollCapacity(1));
m20stringList_.add(I_PEUR);
m20stringMapStringList_.addEntry(I_ZENITH,m20stringList_);
m20stringList_=new StringList(new CollCapacity(1));
m20stringList_.add(I_PEUR);
m20stringMapStringList_.addEntry(I_DANSE_PLUIE,m20stringList_);
m20stringList_=new StringList(new CollCapacity(1));
m20stringList_.add(I_PEUR);
m20stringMapStringList_.addEntry(I_ORAGE,m20stringList_);
m20stringList_=new StringList(new CollCapacity(1));
m20stringList_.add(I_PEUR);
m20stringMapStringList_.addEntry(I_TEMPETESABLE,m20stringList_);
m20abilityData_.setImmuStatus(m20stringMapStringList_);
return m20abilityData_;
}
static AbilityData m17(){
AbilityData m21abilityData_ = Instances.newAbilityData();
StringMap<Rate> m21stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m21stringMapRate_.addEntry(I_FEE,Rate.newRate(R_3_2));
m21abilityData_.setMultPowerMovesTypesGlobal(m21stringMapRate_);
return m21abilityData_;
}
static AbilityData m18(){
AbilityData m22abilityData_ = Instances.newAbilityData();
m22abilityData_.setReverseEffectsPowerMovesTypesGlobal(true);
return m22abilityData_;
}
static AbilityData m19(){
AbilityData m23abilityData_ = Instances.newAbilityData();
StringMap<Rate> m23stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m23stringMapRate_.addEntry(I_TENEBRE,Rate.newRate(R_3_2));
m23abilityData_.setMultPowerMovesTypesGlobal(m23stringMapRate_);
return m23abilityData_;
}
static AbilityData m20(){
AbilityData m24abilityData_ = Instances.newAbilityData();
StringList m24stringList_=new StringList(new CollCapacity(1));
m24stringList_.add(I_TEMPETESABLE);
m24abilityData_.setImmuWeather(m24stringList_);
EnumMap<Statistic,String> m24enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m24enumMapStatisticString_.addEntry(Statistic.SPEED,A_MAX+LP+R_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_TEMPETESABLE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_TEMPETESABLE+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m24abilityData_.setMultStat(m24enumMapStatisticString_);
return m24abilityData_;
}
static AbilityData m21(){
AbilityData m25abilityData_ = Instances.newAbilityData();
m25abilityData_.setHealHpWhileUsingBerry(Rate.newRate(R_1_3));
return m25abilityData_;
}
static AbilityData m22(){
AbilityData m26abilityData_ = Instances.newAbilityData();
StringList m26stringList_=new StringList(new CollCapacity(2));
m26stringList_.add(I_ATTRACTION);
m26stringList_.add(I_SEDUCTION);
m26abilityData_.setImmuMove(m26stringList_);
StringMap<StringList> m26stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
m26stringList_=new StringList(new CollCapacity(1));
m26stringList_.add(I_AMOUR);
m26stringMapStringList_.addEntry(ES,m26stringList_);
m26stringList_=new StringList(new CollCapacity(1));
m26stringList_.add(I_AMOUR);
m26stringMapStringList_.addEntry(I_GRELE,m26stringList_);
m26stringList_=new StringList(new CollCapacity(1));
m26stringList_.add(I_AMOUR);
m26stringMapStringList_.addEntry(I_ZENITH,m26stringList_);
m26stringList_=new StringList(new CollCapacity(1));
m26stringList_.add(I_AMOUR);
m26stringMapStringList_.addEntry(I_DANSE_PLUIE,m26stringList_);
m26stringList_=new StringList(new CollCapacity(1));
m26stringList_.add(I_AMOUR);
m26stringMapStringList_.addEntry(I_ORAGE,m26stringList_);
m26stringList_=new StringList(new CollCapacity(1));
m26stringList_.add(I_AMOUR);
m26stringMapStringList_.addEntry(I_TEMPETESABLE,m26stringList_);
m26abilityData_.setImmuStatus(m26stringMapStringList_);
return m26abilityData_;
}
static AbilityData m23(){
AbilityData m27abilityData_ = Instances.newAbilityData();
return m27abilityData_;
}
static AbilityData m24(){
AbilityData m28abilityData_ = Instances.newAbilityData();
m28abilityData_.setMultPower(A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_FEU+RB+RP+RP+OM+LP+R_3_2+OM+A_CARACGAUCHEFERME+LP+V_LANCEUR_PV_RESTANTS+OC+R_1_3+OM+V_LANCEUR_PV_MAX+RP+OP+A_CARACDROITEOUVERT+LP+V_LANCEUR_PV_RESTANTS+OC+R_1_3+OM+V_LANCEUR_PV_MAX+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_FEU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
return m28abilityData_;
}
static AbilityData m25(){
AbilityData m29abilityData_ = Instances.newAbilityData();
StringList m29stringList_=new StringList(new CollCapacity(46));
m29stringList_.add(I_ABSORB_EAU);
m29stringList_.add(I_ABSORB_VOLT);
m29stringList_.add(I_ANTI_BRUIT);
m29stringList_.add(I_ARMUMAGMA);
m29stringList_.add(I_ARMURBASTON);
m29stringList_.add(I_ATTENTION);
m29stringList_.add(I_BENET);
m29stringList_.add(I_COEUR_DE_COQ);
m29stringList_.add(I_COQUE_ARMURE);
m29stringList_.add(I_CORPS_SAIN);
m29stringList_.add(I_DON_FLORAL);
m29stringList_.add(I_ECAILLE_SPECIALE);
m29stringList_.add(I_ECHAUFFEMENT);
m29stringList_.add(I_ECRAN_FUMEE);
m29stringList_.add(I_ECRAN_POUDRE);
m29stringList_.add(I_ESPRIT_VITAL);
m29stringList_.add(I_FERMETE);
m29stringList_.add(I_FEUILLE_GARDE);
m29stringList_.add(I_FILTRE);
m29stringList_.add(I_GARDE_MYSTIK);
m29stringList_.add(I_GLUE);
m29stringList_.add(I_HERBIVORE);
m29stringList_.add(I_HYPER_CUTTER);
m29stringList_.add(I_IGNIFU_VOILE);
m29stringList_.add(I_IGNIFUGE);
m29stringList_.add(I_INCONSCIENT);
m29stringList_.add(I_INSOMNIA);
m29stringList_.add(I_ISOGRAISSE);
m29stringList_.add(I_LAVABO);
m29stringList_.add(I_LEVITATION);
m29stringList_.add(I_MOITEUR);
m29stringList_.add(I_MOMIE);
m29stringList_.add(I_MOTORISE);
m29stringList_.add(I_PARATONNERRE);
m29stringList_.add(I_PEAU_SECHE);
m29stringList_.add(I_PIEDS_CONFUS);
m29stringList_.add(I_REGARD_VIF);
m29stringList_.add(I_RIDEAU_NEIGE);
m29stringList_.add(I_SIMPLE);
m29stringList_.add(I_SOLIDE_ROC);
m29stringList_.add(I_TEMPO_PERSO);
m29stringList_.add(I_TENSION);
m29stringList_.add(I_TORCHE);
m29stringList_.add(I_VACCIN);
m29stringList_.add(I_VENTOUSE);
m29stringList_.add(I_VOILE_SABLE);
m29abilityData_.setIgnAbility(m29stringList_);
m29abilityData_.setMumy(true);
return m29abilityData_;
}
static AbilityData m26(){
AbilityData m30abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m30custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m30effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m30effectWhileSendingWithStatistic_.setCopyingAbility(true);
m30custListEffectWhileSendingWithStatistic_.add(m30effectWhileSendingWithStatistic_);
m30abilityData_.setEffectSending(m30custListEffectWhileSendingWithStatistic_);
return m30abilityData_;
}
static AbilityData m27(){
AbilityData m31abilityData_ = Instances.newAbilityData();
m31abilityData_.setMultEvtRateCh(Rate.newRate(R_2));
return m31abilityData_;
}
static AbilityData m28(){
AbilityData m32abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m32enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m32enumMapStatisticString_.addEntry(Statistic.SPEED,A_MAX+LP+R_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_ZENITH+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ZENITH+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m32abilityData_.setMultStat(m32enumMapStatisticString_);
return m32abilityData_;
}
static AbilityData m29(){
AbilityData m33abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m33custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m33effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m33effectWhileSendingWithStatistic_.setDisableWeather(true);
m33custListEffectWhileSendingWithStatistic_.add(m33effectWhileSendingWithStatistic_);
m33abilityData_.setEffectSending(m33custListEffectWhileSendingWithStatistic_);
return m33abilityData_;
}
static AbilityData m30(){
AbilityData m34abilityData_ = Instances.newAbilityData();
EnumList<Statistic> m34enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
m34enumListStatistic_.add(Statistic.DEFENSE);
m34abilityData_.setImmuLowStat(m34enumListStatistic_);
return m34abilityData_;
}
static AbilityData m31(){
AbilityData m35abilityData_ = Instances.newAbilityData();
StatisticTypeByte m35objectMapStatisticTypeByte_=new StatisticTypeByte(new CollCapacity(1));
m35objectMapStatisticTypeByte_.addEntry(new StatisticType(Statistic.ATTACK,I_TENEBRE),(byte)1);
m35abilityData_.setMultStatIfDamgeType(m35objectMapStatisticTypeByte_);
return m35abilityData_;
}
static AbilityData m32(){
AbilityData m36abilityData_ = Instances.newAbilityData();
CustList<EffectEndRound> m36custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundTeam m36effectEndRoundTeam_=Instances.newEffectEndRoundTeam();
m36effectEndRoundTeam_.setDeleteAllStatus(Rate.newRate(R_3_10));
m36effectEndRoundTeam_.setDeleteAllStatusAlly(Rate.newRate(R_3_10));
m36effectEndRoundTeam_.setFailEndRound(ES);
m36effectEndRoundTeam_.setEndRoundRank(25);
m36effectEndRoundTeam_.setTargetChoice(TargetChoice.NOTHING);
m36effectEndRoundTeam_.setFail(ES);
m36custListEffectEndRound_.add(m36effectEndRoundTeam_);
m36abilityData_.setEffectEndRound(m36custListEffectEndRound_);
return m36abilityData_;
}
static AbilityData m33(){
AbilityData m37abilityData_ = Instances.newAbilityData();
EnumList<Statistic> m37enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
m37enumListStatistic_.add(Statistic.ATTACK);
m37abilityData_.setMaxStatisticsIfCh(m37enumListStatistic_);
return m37abilityData_;
}
static AbilityData m34(){
AbilityData m38abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m38enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m38enumMapStatisticString_.addEntry(Statistic.ATTACK,R_2);
m38abilityData_.setMultStat(m38enumMapStatisticString_);
return m38abilityData_;
}
static AbilityData m35(){
AbilityData m39abilityData_ = Instances.newAbilityData();
m39abilityData_.setMultVarBoost(Rate.newRate(R_1));
return m39abilityData_;
}
static AbilityData m36(){
AbilityData m40abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m40custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m40effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m40effectWhileSendingWithStatistic_.setEnabledWeather(I_ORAGE);
m40custListEffectWhileSendingWithStatistic_.add(m40effectWhileSendingWithStatistic_);
m40abilityData_.setEffectSending(m40custListEffectWhileSendingWithStatistic_);
return m40abilityData_;
}
static AbilityData m37(){
AbilityData m41abilityData_ = Instances.newAbilityData();
m41abilityData_.setImmuCh(true);
return m41abilityData_;
}
static AbilityData m38(){
AbilityData m42abilityData_ = Instances.newAbilityData();
m42abilityData_.setDecreaseNecStepsHatch(1);
MonteCarloString m42monteCarloString_=new MonteCarloString(new CollCapacity(2));
m42monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_7));
m42monteCarloString_.addQuickEvent(I_BRULURE,LgInt.newLgInt(R_3));
m42abilityData_.setSingleStatus(m42monteCarloString_);
StringMap<String> m42stringMapString_=new StringMap<String>(new CollCapacity(1));
m42stringMapString_.addEntry(I_BRULURE,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_BRULURE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_TYPES+RB+OC+LB+I_FEU+RB+RP+RP+GT+R_0);
m42abilityData_.setFailStatus(m42stringMapString_);
return m42abilityData_;
}
static AbilityData m39(){
AbilityData m43abilityData_ = Instances.newAbilityData();
StringMap<Rate> m43stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m43stringMapRate_.addEntry(I_GRELE,Rate.newRate(R_1_16));
m43abilityData_.setHealHpByWeather(m43stringMapRate_);
StringList m43stringList_=new StringList(new CollCapacity(1));
m43stringList_.add(I_GRELE);
m43abilityData_.setImmuWeather(m43stringList_);
return m43abilityData_;
}
static AbilityData m40(){
AbilityData m44abilityData_ = Instances.newAbilityData();
EnumList<Statistic> m44enumListStatistic_=new EnumList<Statistic>(new CollCapacity(5));
m44enumListStatistic_.add(Statistic.ATTACK);
m44enumListStatistic_.add(Statistic.DEFENSE);
m44enumListStatistic_.add(Statistic.SPECIAL_ATTACK);
m44enumListStatistic_.add(Statistic.SPECIAL_DEFENSE);
m44enumListStatistic_.add(Statistic.SPEED);
m44abilityData_.setImmuLowStat(m44enumListStatistic_);
return m44abilityData_;
}
static AbilityData m41(){
AbilityData m45abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m45custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m45effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m45effectWhileSendingWithStatistic_.setEnabledWeather(I_DANSE_PLUIE);
m45custListEffectWhileSendingWithStatistic_.add(m45effectWhileSendingWithStatistic_);
m45abilityData_.setEffectSending(m45custListEffectWhileSendingWithStatistic_);
return m45abilityData_;
}
static AbilityData m42(){
AbilityData m46abilityData_ = Instances.newAbilityData();
m46abilityData_.setImmuLowStatIfStatus(new CustList<StatisticStatus>(new StatisticStatus(Statistic.ATTACK,I_BRULURE)));
EnumMap<Statistic,String> m46enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m46enumMapStatisticString_.addEntry(Statistic.ATTACK,R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_STATUTS+RB+OC+LB+I_PARALYSIE+OS+I_SIMPLE_POISON+OS+I_SOMMEIL+OS+I_GEL+OS+I_SOMMEIL_REPOS+OS+I_POISON_GRAVE+OS+I_BRULURE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PARALYSIE+OS+I_SIMPLE_POISON+OS+I_SOMMEIL+OS+I_GEL+OS+I_SOMMEIL_REPOS+OS+I_POISON_GRAVE+OS+I_BRULURE+RB+OC+LB+V_FIGHTER_STATUTS+RB+RP+RP);
m46abilityData_.setMultStat(m46enumMapStatisticString_);
return m46abilityData_;
}
static AbilityData m43(){
AbilityData m47abilityData_ = Instances.newAbilityData();
StringMap<Rate> m47stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m47stringMapRate_.addEntry(I_DANSE_PLUIE,Rate.newRate(R_1_16));
m47abilityData_.setHealHpByWeather(m47stringMapRate_);
return m47abilityData_;
}
static AbilityData m44(){
AbilityData m48abilityData_ = Instances.newAbilityData();
m48abilityData_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_NOM+RB+OC+LB+I_POING_ECLAIR+OS+I_MARTO_POING+OS+I_POING_OMBRE+OS+I_PISTO_POING+OS+I_DYNAMOPOING+OS+I_ULTIMAPOING+OS+I_POING_DE_FEU+OS+I_POING_METEOR+OS+I_POINGLACE+OS+I_POING_COMETE+OS+I_VAMPIPOING+OS+I_UPPERCUT+OS+I_MITRA_POING+OS+I_STRATOPERCUT+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_POING_ECLAIR+OS+I_MARTO_POING+OS+I_POING_OMBRE+OS+I_PISTO_POING+OS+I_DYNAMOPOING+OS+I_ULTIMAPOING+OS+I_POING_DE_FEU+OS+I_POING_METEOR+OS+I_POINGLACE+OS+I_POING_COMETE+OS+I_VAMPIPOING+OS+I_UPPERCUT+OS+I_MITRA_POING+OS+I_STRATOPERCUT+RB+OC+LB+V_ATTAQUE_NOM+RB+RP+RP);
return m48abilityData_;
}
static AbilityData m45(){
AbilityData m49abilityData_ = Instances.newAbilityData();
m49abilityData_.setChgtTypeByDamage(true);
return m49abilityData_;
}
static AbilityData m46(){
AbilityData m50abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m50enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m50enumMapStatisticString_.addEntry(Statistic.SPEED,R_2+OM+A_CARDINAL+LP+LB+V_FIGHTER_OBJET+RB+RP+OP+LP+R_1+OD+A_CARDINAL+LP+LB+V_FIGHTER_OBJET+RB+RP+RP);
m50abilityData_.setMultStat(m50enumMapStatisticString_);
return m50abilityData_;
}
static AbilityData m47(){
AbilityData m51abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m51enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(4));
m51enumMapStatisticString_.addEntry(Statistic.ATTACK,A_MAX+LP+R_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_ZENITH+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ZENITH+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m51enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,A_MAX+LP+R_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_ZENITH+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ZENITH+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m51enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,A_MAX+LP+R_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_ZENITH+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ZENITH+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m51enumMapStatisticString_.addEntry(Statistic.DEFENSE,A_MAX+LP+R_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_ZENITH+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ZENITH+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m51abilityData_.setMultStat(m51enumMapStatisticString_);
return m51abilityData_;
}
static AbilityData m48(){
AbilityData m52abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m52enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m52enumMapStatisticString_.addEntry(Statistic.DEFENSE,R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_STATUTS+RB+OC+LB+I_SOMMEIL_REPOS+OS+I_GEL+OS+I_POISON_GRAVE+OS+I_PARALYSIE+OS+I_BRULURE+OS+I_SOMMEIL+OS+I_SIMPLE_POISON+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_SOMMEIL_REPOS+OS+I_GEL+OS+I_POISON_GRAVE+OS+I_PARALYSIE+OS+I_BRULURE+OS+I_SOMMEIL+OS+I_SIMPLE_POISON+RB+OC+LB+V_FIGHTER_STATUTS+RB+RP+RP);
m52abilityData_.setMultStat(m52enumMapStatisticString_);
return m52abilityData_;
}
static AbilityData m49(){
AbilityData m53abilityData_ = Instances.newAbilityData();
StringMap<StringList> m53stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m53stringList_=new StringList(new CollCapacity(1));
m53stringList_.add(I_PARALYSIE);
m53stringMapStringList_.addEntry(ES,m53stringList_);
m53stringList_=new StringList(new CollCapacity(1));
m53stringList_.add(I_PARALYSIE);
m53stringMapStringList_.addEntry(I_GRELE,m53stringList_);
m53stringList_=new StringList(new CollCapacity(1));
m53stringList_.add(I_PARALYSIE);
m53stringMapStringList_.addEntry(I_ZENITH,m53stringList_);
m53stringList_=new StringList(new CollCapacity(1));
m53stringList_.add(I_PARALYSIE);
m53stringMapStringList_.addEntry(I_DANSE_PLUIE,m53stringList_);
m53stringList_=new StringList(new CollCapacity(1));
m53stringList_.add(I_PARALYSIE);
m53stringMapStringList_.addEntry(I_ORAGE,m53stringList_);
m53stringList_=new StringList(new CollCapacity(1));
m53stringList_.add(I_PARALYSIE);
m53stringMapStringList_.addEntry(I_TEMPETESABLE,m53stringList_);
m53abilityData_.setImmuStatus(m53stringMapStringList_);
return m53abilityData_;
}
static AbilityData m50(){
AbilityData m54abilityData_ = Instances.newAbilityData();
EnumList<Statistic> m54enumListStatistic_=new EnumList<Statistic>(new CollCapacity(5));
m54enumListStatistic_.add(Statistic.ATTACK);
m54enumListStatistic_.add(Statistic.DEFENSE);
m54enumListStatistic_.add(Statistic.SPECIAL_ATTACK);
m54enumListStatistic_.add(Statistic.SPECIAL_DEFENSE);
m54enumListStatistic_.add(Statistic.SPEED);
m54abilityData_.setImmuLowStat(m54enumListStatistic_);
return m54abilityData_;
}
static AbilityData m51(){
AbilityData m55abilityData_ = Instances.newAbilityData();
m55abilityData_.setCancelSecEffectOther(true);
return m55abilityData_;
}
static AbilityData m52(){
AbilityData m56abilityData_ = Instances.newAbilityData();
m56abilityData_.setMultPower(A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_PLANTE+RB+RP+RP+OM+LP+R_3_2+OM+A_CARACGAUCHEFERME+LP+V_LANCEUR_PV_RESTANTS+OC+R_1_3+OM+V_LANCEUR_PV_MAX+RP+OP+A_CARACDROITEOUVERT+LP+V_LANCEUR_PV_RESTANTS+OC+R_1_3+OM+V_LANCEUR_PV_MAX+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PLANTE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
return m56abilityData_;
}
static AbilityData m53(){
AbilityData m57abilityData_ = Instances.newAbilityData();
StringList m57stringList_=new StringList(new CollCapacity(2));
m57stringList_.add(I_GRELE);
m57stringList_.add(I_TEMPETESABLE);
m57abilityData_.setImmuWeather(m57stringList_);
return m57abilityData_;
}
static AbilityData m54(){
AbilityData m58abilityData_ = Instances.newAbilityData();
m58abilityData_.setRecoilDamageFoe(Rate.newRate(R_1_8));
return m58abilityData_;
}
static AbilityData m55(){
AbilityData m59abilityData_ = Instances.newAbilityData();
StringMap<StringList> m59stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m59stringList_=new StringList(new CollCapacity(2));
m59stringList_.add(I_SOMMEIL);
m59stringList_.add(I_SOMMEIL_REPOS);
m59stringMapStringList_.addEntry(ES,m59stringList_);
m59stringList_=new StringList(new CollCapacity(2));
m59stringList_.add(I_SOMMEIL);
m59stringList_.add(I_SOMMEIL_REPOS);
m59stringMapStringList_.addEntry(I_GRELE,m59stringList_);
m59stringList_=new StringList(new CollCapacity(2));
m59stringList_.add(I_SOMMEIL);
m59stringList_.add(I_SOMMEIL_REPOS);
m59stringMapStringList_.addEntry(I_ZENITH,m59stringList_);
m59stringList_=new StringList(new CollCapacity(2));
m59stringList_.add(I_SOMMEIL);
m59stringList_.add(I_SOMMEIL_REPOS);
m59stringMapStringList_.addEntry(I_DANSE_PLUIE,m59stringList_);
m59stringList_=new StringList(new CollCapacity(2));
m59stringList_.add(I_SOMMEIL);
m59stringList_.add(I_SOMMEIL_REPOS);
m59stringMapStringList_.addEntry(I_ORAGE,m59stringList_);
m59stringList_=new StringList(new CollCapacity(2));
m59stringList_.add(I_SOMMEIL);
m59stringList_.add(I_SOMMEIL_REPOS);
m59stringMapStringList_.addEntry(I_TEMPETESABLE,m59stringList_);
m59abilityData_.setImmuStatus(m59stringMapStringList_);
return m59abilityData_;
}
static AbilityData m56(){
AbilityData m60abilityData_ = Instances.newAbilityData();
m60abilityData_.setMultPower(A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_INSECTE+RB+RP+RP+OM+LP+R_3_2+OM+A_CARACGAUCHEFERME+LP+V_LANCEUR_PV_RESTANTS+OC+R_1_3+OM+V_LANCEUR_PV_MAX+RP+OP+A_CARACDROITEOUVERT+LP+V_LANCEUR_PV_RESTANTS+OC+R_1_3+OM+V_LANCEUR_PV_MAX+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_INSECTE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
return m60abilityData_;
}
static AbilityData m57(){
AbilityData m61abilityData_ = Instances.newAbilityData();
StringMap<Short> m61stringMapShort_=new StringMap<Short>(new CollCapacity(1));
m61stringMapShort_.addEntry(I_AUTRE,(short)1);
m61abilityData_.setIncreasedPrio(m61stringMapShort_);
return m61abilityData_;
}
static AbilityData m58(){
AbilityData m62abilityData_ = Instances.newAbilityData();
StringList m62stringList_=new StringList(new CollCapacity(4));
m62stringList_.add(I_ABIME);
m62stringList_.add(I_EMPAL_KORNE);
m62stringList_.add(I_GUILLOTINE);
m62stringList_.add(I_GLACIATION);
m62abilityData_.setImmuMove(m62stringList_);
EnumMap<Statistic,String> m62enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(2));
m62enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,R_3_2);
m62enumMapStatisticString_.addEntry(Statistic.DEFENSE,R_3_2);
m62abilityData_.setMultStat(m62enumMapStatisticString_);
return m62abilityData_;
}
static AbilityData m59(){
AbilityData m63abilityData_ = Instances.newAbilityData();
StringMap<StringList> m63stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
StringList m63stringList_=new StringList(new CollCapacity(7));
m63stringList_.add(I_SOMMEIL);
m63stringList_.add(I_SOMMEIL_REPOS);
m63stringList_.add(I_PARALYSIE);
m63stringList_.add(I_GEL);
m63stringList_.add(I_BRULURE);
m63stringList_.add(I_SIMPLE_POISON);
m63stringList_.add(I_POISON_GRAVE);
m63stringMapStringList_.addEntry(I_ZENITH,m63stringList_);
m63abilityData_.setImmuStatus(m63stringMapStringList_);
return m63abilityData_;
}
static AbilityData m60(){
AbilityData m64abilityData_ = Instances.newAbilityData();
m64abilityData_.setMultSufferedDamageSuperEff(Rate.newRate(R_3_4));
return m64abilityData_;
}
static AbilityData m61(){
AbilityData m65abilityData_ = Instances.newAbilityData();
StringMap<StringList> m65stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
StringList m65stringList_=new StringList(new CollCapacity(11));
m65stringList_.add(I_POISON_GRAVE);
m65stringList_.add(I_BRULURE);
m65stringList_.add(I_VAMPIGRAINE_ST);
m65stringList_.add(I_SIMPLE_POISON);
m65stringList_.add(I_GEL);
m65stringList_.add(I_AMOUR);
m65stringList_.add(I_SOMMEIL);
m65stringList_.add(I_SOMMEIL_REPOS);
m65stringList_.add(I_MAUDIT);
m65stringList_.add(I_CAUCHEMAR_ST);
m65stringList_.add(I_PARALYSIE);
m65stringMapStringList_.addEntry(I_PLANTE,m65stringList_);
m65abilityData_.setImmuStatusTypes(m65stringMapStringList_);
StringMap<EnumList<Statistic>> m65stringMapEnumListStatistic_=new StringMap<EnumList<Statistic>>(new CollCapacity(1));
EnumList<Statistic> m65enumListStatistic_=new EnumList<Statistic>(new CollCapacity(8));
m65enumListStatistic_.add(Statistic.ATTACK);
m65enumListStatistic_.add(Statistic.DEFENSE);
m65enumListStatistic_.add(Statistic.SPECIAL_ATTACK);
m65enumListStatistic_.add(Statistic.SPECIAL_DEFENSE);
m65enumListStatistic_.add(Statistic.SPEED);
m65enumListStatistic_.add(Statistic.ACCURACY);
m65enumListStatistic_.add(Statistic.EVASINESS);
m65enumListStatistic_.add(Statistic.CRITICAL_HIT);
m65stringMapEnumListStatistic_.addEntry(I_PLANTE,m65enumListStatistic_);
m65abilityData_.setImmuLowStatisTypes(m65stringMapEnumListStatistic_);
return m65abilityData_;
}
static AbilityData m62(){
AbilityData m66abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m66enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m66enumMapStatisticString_.addEntry(Statistic.ATTACK,R_2);
m66abilityData_.setMultStat(m66enumMapStatisticString_);
return m66abilityData_;
}
static AbilityData m63(){
AbilityData m67abilityData_ = Instances.newAbilityData();
StringList m67stringList_=new StringList(new CollCapacity(1));
m67stringList_.add(I_TEMPETESABLE);
m67abilityData_.setImmuWeather(m67stringList_);
m67abilityData_.setMultPower(A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_TEMPETESABLE+RB+RP+RP+OM+LP+R_13_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_ROCHE+OS+I_ACIER+OS+I_SOL+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ROCHE+OS+I_ACIER+OS+I_SOL+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_TEMPETESABLE+RB+OC+LB+V_CLIMATS+RB+RP+RP);
return m67abilityData_;
}
static AbilityData m64(){
AbilityData m68abilityData_ = Instances.newAbilityData();
StringMap<Rate> m68stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m68stringMapRate_.addEntry(I_ZENITH,Rate.newRate(R_1_8));
m68abilityData_.setHealHpByWeather(m68stringMapRate_);
EnumMap<Statistic,String> m68enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m68enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,A_MAX+LP+R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_ZENITH+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ZENITH+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m68abilityData_.setMultStat(m68enumMapStatisticString_);
return m68abilityData_;
}
static AbilityData m65(){
AbilityData m69abilityData_ = Instances.newAbilityData();
m69abilityData_.setSlowing(true);
return m69abilityData_;
}
static AbilityData m66(){
AbilityData m70abilityData_ = Instances.newAbilityData();
m70abilityData_.setMultAllyDamage(Rate.newRate(R_3_4));
return m70abilityData_;
}
static AbilityData m67(){
AbilityData m71abilityData_ = Instances.newAbilityData();
StringList m71stringList_=new StringList(new CollCapacity(5));
m71stringList_.add(I_PICOTS);
m71stringList_.add(I_PIEGE_DE_ROC);
m71stringList_.add(I_CAUCHEMAR);
m71stringList_.add(I_MALEDICTION_2);
m71stringList_.add(I_VAMPIGRAINE);
m71abilityData_.setImmuMove(m71stringList_);
m71stringList_=new StringList(new CollCapacity(2));
m71stringList_.add(I_GRELE);
m71stringList_.add(I_TEMPETESABLE);
m71abilityData_.setImmuWeather(m71stringList_);
m71abilityData_.setImmuDamageTrappingMoves(true);
m71abilityData_.setImmuDamageRecoil(true);
m71stringList_=new StringList(new CollCapacity(1));
m71stringList_.add(I_MAUVAIS_REVE);
m71abilityData_.setImmuAbility(m71stringList_);
m71stringList_=new StringList(new CollCapacity(1));
m71stringList_.add(I_PARALYSIE);
m71abilityData_.setImmuStatusBeginRound(m71stringList_);
StringMap<StringList> m71stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
m71stringList_=new StringList(new CollCapacity(3));
m71stringList_.add(I_BRULURE);
m71stringList_.add(I_SIMPLE_POISON);
m71stringList_.add(I_POISON_GRAVE);
m71stringMapStringList_.addEntry(ES,m71stringList_);
m71stringList_=new StringList(new CollCapacity(3));
m71stringList_.add(I_BRULURE);
m71stringList_.add(I_SIMPLE_POISON);
m71stringList_.add(I_POISON_GRAVE);
m71stringMapStringList_.addEntry(I_GRELE,m71stringList_);
m71stringList_=new StringList(new CollCapacity(3));
m71stringList_.add(I_BRULURE);
m71stringList_.add(I_SIMPLE_POISON);
m71stringList_.add(I_POISON_GRAVE);
m71stringMapStringList_.addEntry(I_ZENITH,m71stringList_);
m71stringList_=new StringList(new CollCapacity(3));
m71stringList_.add(I_BRULURE);
m71stringList_.add(I_SIMPLE_POISON);
m71stringList_.add(I_POISON_GRAVE);
m71stringMapStringList_.addEntry(I_DANSE_PLUIE,m71stringList_);
m71stringList_=new StringList(new CollCapacity(3));
m71stringList_.add(I_BRULURE);
m71stringList_.add(I_SIMPLE_POISON);
m71stringList_.add(I_POISON_GRAVE);
m71stringMapStringList_.addEntry(I_ORAGE,m71stringList_);
m71stringList_=new StringList(new CollCapacity(3));
m71stringList_.add(I_BRULURE);
m71stringList_.add(I_SIMPLE_POISON);
m71stringList_.add(I_POISON_GRAVE);
m71stringMapStringList_.addEntry(I_TEMPETESABLE,m71stringList_);
m71abilityData_.setImmuStatus(m71stringMapStringList_);
return m71abilityData_;
}
static AbilityData m68(){
AbilityData m72abilityData_ = Instances.newAbilityData();
StringList m72stringList_=new StringList(new CollCapacity(5));
m72stringList_.add(I_IMPLORE);
m72stringList_.add(I_LARCIN);
m72stringList_.add(I_TOURMAGIK);
m72stringList_.add(I_PASSE_PASSE);
m72stringList_.add(I_SABOTAGE);
m72abilityData_.setImmuMove(m72stringList_);
m72abilityData_.setImmuSufferedDamageLowEff(true);
return m72abilityData_;
}
static AbilityData m69(){
AbilityData m73abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m73enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m73enumMapStatisticString_.addEntry(Statistic.SPEED,A_MAX+LP+R_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_DANSE_PLUIE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_DANSE_PLUIE+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m73abilityData_.setMultStat(m73enumMapStatisticString_);
return m73abilityData_;
}
static AbilityData m70(){
AbilityData m74abilityData_ = Instances.newAbilityData();
m74abilityData_.setMaxHpForUsingBerry(Rate.newRate(R_1_2));
return m74abilityData_;
}
static AbilityData m71(){
AbilityData m75abilityData_ = Instances.newAbilityData();
StringMap<StringList> m75stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
StringList m75stringList_=new StringList(new CollCapacity(1));
m75stringList_.add(I_SOMMEIL);
m75stringMapStringList_.addEntry(I_FEE,m75stringList_);
m75abilityData_.setImmuStatusTypes(m75stringMapStringList_);
return m75abilityData_;
}
static AbilityData m72(){
AbilityData m76abilityData_ = Instances.newAbilityData();
StringList m76stringList_=new StringList(new CollCapacity(5));
m76stringList_.add(I_IMPLORE);
m76stringList_.add(I_LARCIN);
m76stringList_.add(I_TOURMAGIK);
m76stringList_.add(I_PASSE_PASSE);
m76stringList_.add(I_SABOTAGE);
m76abilityData_.setImmuMove(m76stringList_);
return m76abilityData_;
}
static AbilityData m73(){
AbilityData m77abilityData_ = Instances.newAbilityData();
m77abilityData_.setMultPower(R_3_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_NOM+RB+OC+LB+I_PINCE_MASSE+OS+I_PRESSE+OS+I_PSYKOUD_BOUL+OS+I_CROC_FATAL+OS+I_POURSUITE+OS+I_LANCECROU+OS+I_MEGAFOUET+OS+I_COUD_KRANE+OS+I_MACH_PUNCH+OS+I_TOUR_RAPIDE+OS+I_PISTO_POING+OS+I_ECLATEGRIFFE+OS+I_BASTON+OS+I_ECLAIR_FOU+OS+I_MEGACORNE+OS+I_AVALANCHE+OS+I_POING_ECLAIR+OS+I_FORCE+OS+I_TRIPLE_PIED+OS+I_MORSURE+OS+I_STRATOPERCUT+OS+I_ETRANGLEMENT+OS+I_PLANNEUR+OS+I_FLEAU+OS+I_FRAPPE_ATLAS+OS+I_TRANCHE+OS+I_TACLE_LOURD+OS+I_BALAYAGE+OS+I_POING_COMETE+OS+I_COUP_VICTOIRE+OS+I_TUNNELIER+OS+I_PICPIC+OS+I_PEIGNEE+OS+I_BOUTEFEU+OS+I_DANSE_FLEUR+OS+I_RETOUR+OS+I_FRACASS_TETE+OS+I_BALAYETTE+OS+I_CLOSE_COMBAT+OS+I_STIMULANT+OS+I_PLAIE_CROIX+OS+I_COUPE+OS+I_AQUA_JET+OS+I_DOUBLE_BAFFE+OS+I_MAWASHI_GERI+OS+I_COUP_DOUBLE+OS+I_GYROBALLE+OS+I_GRIFFE+OS+I_FRENESIE+OS+I_ROUE_DE_FEU+OS+I_FURIE+OS+I_CRU_AILE+OS+I_VENGEANCE+OS+I_ASSURANCE+OS+I_TRICHERIE+OS+I_TACLE_FEU+OS+I_VIT_EXTREME+OS+I_ELECTACLE+OS+I_MARTO_POING+OS+I_CONTRE+OS+I_REVENANT+OS+I_CROCS_GIVRE+OS+I_ATOUT+OS+I_NITROCHARGE+OS+I_CONSTRICTION+OS+I_BLUFF+OS+I_LIGOTAGE+OS+I_ECRASEMENT+OS+I_ETINCELLE+OS+I_BULLDOBOULE+OS+I_LUTTE+OS+I_PLONGEE+OS+I_FOUET_LIANES+OS+I_LARCIN+OS+I_ULTIMAPOING+OS+I_BALL_GLACE+OS+I_LAME_FEUILLE+OS+I_DYNAMOPOING+OS+I_ESCALADE+OS+I_DAMOCLES+OS+I_MANIA+OS+I_QUEUE_DE_FER+OS+I_ATTRITION+OS+I_CORPS_PERDU+OS+I_DOUBLE_PIED+OS+I_POING_DARD+OS+I_TRANCHE_NUIT+OS+I_REPRESAILLES+OS+I_FORTE_PAUME+OS+I_DEMI_TOUR+OS+I_ECRAS_FACE+OS+I_VAMPIPOING+OS+I_AIKIDO+OS+I_HYDROQUEUE+OS+I_PICORE+OS+I_ROULADE+OS+I_POING_DE_FEU+OS+I_QUEUE_POISON+OS+I_GRIFFE_ACIER+OS+I_LAME_SAINTE+OS+I_PIQURE+OS+I_CROCS_FEU+OS+I_PIED_VOLTIGE+OS+I_BEC_VRILLE+OS+I_POING_KARATE+OS+I_COLERE+OS+I_FACADE+OS+I_VIVE_ATTAQUE+OS+I_CASSE_BRIQUE+OS+I_OMBRE_PORTEE+OS+I_ECLATE_ROC+OS+I_DRACO_QUEUE+OS+I_NOEUD_HERBE+OS+I_TELEPORT+OS+I_ETREINTE+OS+I_DERNIERECOUR+OS+I_CREVECOEUR+OS+I_BELIER+OS+I_REVEIL_FORCE+OS+I_COMBO_GRIFFE+OS+I_TETE_DE_FER+OS+I_SABOTAGE+OS+I_PIED_BRULEUR+OS+I_CLAQUOIR+OS+I_COGNE+OS+I_IMPLORE+OS+I_CROCHETVENIN+OS+I_VAMPIRISME+OS+I_LECHOUILLE+OS+I_CHUTE_LIBRE+OS+I_RAPACE+OS+I_PIED_SAUTE+OS+I_COUP_CROIX+OS+I_CROCS_ECLAIR+OS+I_KOUD_KORNE+OS+I_ESSORAGE+OS+I_TUNNEL+OS+I_DRACOCHARGE+OS+I_FAUX_CHAGE+OS+I_CHARGE+OS+I_ETONNEMENT+OS+I_ENVOL+OS+I_MARTOBOIS+OS+I_PROJECTION+OS+I_COQUILAME+OS+I_CHARGE_FOUDRE+OS+I_GIGA_IMPACT+OS+I_GRIFFE_OMBRE+OS+I_SURPUISSANCE+OS+I_EXCUSE+OS+I_CASCADE+OS+I_PUNITION+OS+I_DIRECT_TOXIK+OS+I_SACRIFICE+OS+I_VENDETTA+OS+I_POING_METEOR+OS+I_ACROBATIE+OS+I_YAMA_ARASHI+OS+I_FORCE_POIGNE+OS+I_PATIENCE+OS+I_COUP_BAS+OS+I_POINGLACE+OS+I_TORGNOLES+OS+I_PLUMO_QUEUE+OS+I_FEINTE+OS+I_RIPOSTE+OS+I_PLAQUAGE+OS+I_AILE_D_ACIER+OS+I_DRACOGRIFFE+OS+I_POISON_CROIX+OS+I_ENCORNEBOIS+OS+I_COUP_D_BOULE+OS+I_MACHOUILLE+OS+I_CROC_DE_MORT+OS+I_TAILLADE+OS+I_ULTIMAWASHI+OS+I_POING_OMBRE+OS+I_AEROPIQUE+OS+I_REBOND+OS+I_MITRA_POING+OS+I_UPPERCUT+OS+I_SOUPLESSE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PINCE_MASSE+OS+I_PRESSE+OS+I_PSYKOUD_BOUL+OS+I_CROC_FATAL+OS+I_POURSUITE+OS+I_LANCECROU+OS+I_MEGAFOUET+OS+I_COUD_KRANE+OS+I_MACH_PUNCH+OS+I_TOUR_RAPIDE+OS+I_PISTO_POING+OS+I_ECLATEGRIFFE+OS+I_BASTON+OS+I_ECLAIR_FOU+OS+I_MEGACORNE+OS+I_AVALANCHE+OS+I_POING_ECLAIR+OS+I_FORCE+OS+I_TRIPLE_PIED+OS+I_MORSURE+OS+I_STRATOPERCUT+OS+I_ETRANGLEMENT+OS+I_PLANNEUR+OS+I_FLEAU+OS+I_FRAPPE_ATLAS+OS+I_TRANCHE+OS+I_TACLE_LOURD+OS+I_BALAYAGE+OS+I_POING_COMETE+OS+I_COUP_VICTOIRE+OS+I_TUNNELIER+OS+I_PICPIC+OS+I_PEIGNEE+OS+I_BOUTEFEU+OS+I_DANSE_FLEUR+OS+I_RETOUR+OS+I_FRACASS_TETE+OS+I_BALAYETTE+OS+I_CLOSE_COMBAT+OS+I_STIMULANT+OS+I_PLAIE_CROIX+OS+I_COUPE+OS+I_AQUA_JET+OS+I_DOUBLE_BAFFE+OS+I_MAWASHI_GERI+OS+I_COUP_DOUBLE+OS+I_GYROBALLE+OS+I_GRIFFE+OS+I_FRENESIE+OS+I_ROUE_DE_FEU+OS+I_FURIE+OS+I_CRU_AILE+OS+I_VENGEANCE+OS+I_ASSURANCE+OS+I_TRICHERIE+OS+I_TACLE_FEU+OS+I_VIT_EXTREME+OS+I_ELECTACLE+OS+I_MARTO_POING+OS+I_CONTRE+OS+I_REVENANT+OS+I_CROCS_GIVRE+OS+I_ATOUT+OS+I_NITROCHARGE+OS+I_CONSTRICTION+OS+I_BLUFF+OS+I_LIGOTAGE+OS+I_ECRASEMENT+OS+I_ETINCELLE+OS+I_BULLDOBOULE+OS+I_LUTTE+OS+I_PLONGEE+OS+I_FOUET_LIANES+OS+I_LARCIN+OS+I_ULTIMAPOING+OS+I_BALL_GLACE+OS+I_LAME_FEUILLE+OS+I_DYNAMOPOING+OS+I_ESCALADE+OS+I_DAMOCLES+OS+I_MANIA+OS+I_QUEUE_DE_FER+OS+I_ATTRITION+OS+I_CORPS_PERDU+OS+I_DOUBLE_PIED+OS+I_POING_DARD+OS+I_TRANCHE_NUIT+OS+I_REPRESAILLES+OS+I_FORTE_PAUME+OS+I_DEMI_TOUR+OS+I_ECRAS_FACE+OS+I_VAMPIPOING+OS+I_AIKIDO+OS+I_HYDROQUEUE+OS+I_PICORE+OS+I_ROULADE+OS+I_POING_DE_FEU+OS+I_QUEUE_POISON+OS+I_GRIFFE_ACIER+OS+I_LAME_SAINTE+OS+I_PIQURE+OS+I_CROCS_FEU+OS+I_PIED_VOLTIGE+OS+I_BEC_VRILLE+OS+I_POING_KARATE+OS+I_COLERE+OS+I_FACADE+OS+I_VIVE_ATTAQUE+OS+I_CASSE_BRIQUE+OS+I_OMBRE_PORTEE+OS+I_ECLATE_ROC+OS+I_DRACO_QUEUE+OS+I_NOEUD_HERBE+OS+I_TELEPORT+OS+I_ETREINTE+OS+I_DERNIERECOUR+OS+I_CREVECOEUR+OS+I_BELIER+OS+I_REVEIL_FORCE+OS+I_COMBO_GRIFFE+OS+I_TETE_DE_FER+OS+I_SABOTAGE+OS+I_PIED_BRULEUR+OS+I_CLAQUOIR+OS+I_COGNE+OS+I_IMPLORE+OS+I_CROCHETVENIN+OS+I_VAMPIRISME+OS+I_LECHOUILLE+OS+I_CHUTE_LIBRE+OS+I_RAPACE+OS+I_PIED_SAUTE+OS+I_COUP_CROIX+OS+I_CROCS_ECLAIR+OS+I_KOUD_KORNE+OS+I_ESSORAGE+OS+I_TUNNEL+OS+I_DRACOCHARGE+OS+I_FAUX_CHAGE+OS+I_CHARGE+OS+I_ETONNEMENT+OS+I_ENVOL+OS+I_MARTOBOIS+OS+I_PROJECTION+OS+I_COQUILAME+OS+I_CHARGE_FOUDRE+OS+I_GIGA_IMPACT+OS+I_GRIFFE_OMBRE+OS+I_SURPUISSANCE+OS+I_EXCUSE+OS+I_CASCADE+OS+I_PUNITION+OS+I_DIRECT_TOXIK+OS+I_SACRIFICE+OS+I_VENDETTA+OS+I_POING_METEOR+OS+I_ACROBATIE+OS+I_YAMA_ARASHI+OS+I_FORCE_POIGNE+OS+I_PATIENCE+OS+I_COUP_BAS+OS+I_POINGLACE+OS+I_TORGNOLES+OS+I_PLUMO_QUEUE+OS+I_FEINTE+OS+I_RIPOSTE+OS+I_PLAQUAGE+OS+I_AILE_D_ACIER+OS+I_DRACOGRIFFE+OS+I_POISON_CROIX+OS+I_ENCORNEBOIS+OS+I_COUP_D_BOULE+OS+I_MACHOUILLE+OS+I_CROC_DE_MORT+OS+I_TAILLADE+OS+I_ULTIMAWASHI+OS+I_POING_OMBRE+OS+I_AEROPIQUE+OS+I_REBOND+OS+I_MITRA_POING+OS+I_UPPERCUT+OS+I_SOUPLESSE+RB+OC+LB+V_ATTAQUE_NOM+RB+RP+RP);
return m77abilityData_;
}
static AbilityData m74(){
AbilityData m78abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> m78enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m78enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
m78abilityData_.setBoostStatRankProtected(m78enumMapStatisticByte_);
StringMap<StringList> m78stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m78stringList_=new StringList(new CollCapacity(1));
m78stringList_.add(I_PLANTE);
m78stringMapStringList_.addEntry(ES,m78stringList_);
m78stringList_=new StringList(new CollCapacity(1));
m78stringList_.add(I_PLANTE);
m78stringMapStringList_.addEntry(I_GRELE,m78stringList_);
m78stringList_=new StringList(new CollCapacity(1));
m78stringList_.add(I_PLANTE);
m78stringMapStringList_.addEntry(I_ZENITH,m78stringList_);
m78stringList_=new StringList(new CollCapacity(1));
m78stringList_.add(I_PLANTE);
m78stringMapStringList_.addEntry(I_DANSE_PLUIE,m78stringList_);
m78stringList_=new StringList(new CollCapacity(1));
m78stringList_.add(I_PLANTE);
m78stringMapStringList_.addEntry(I_ORAGE,m78stringList_);
m78stringList_=new StringList(new CollCapacity(1));
m78stringList_.add(I_PLANTE);
m78stringMapStringList_.addEntry(I_TEMPETESABLE,m78stringList_);
m78abilityData_.setImmuMoveTypesByWeather(m78stringMapStringList_);
return m78abilityData_;
}
static AbilityData m75(){
AbilityData m79abilityData_ = Instances.newAbilityData();
StringMap<StringList> m79stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
StringList m79stringList_=new StringList(new CollCapacity(7));
m79stringList_.add(I_SOMMEIL);
m79stringList_.add(I_SOMMEIL_REPOS);
m79stringList_.add(I_PARALYSIE);
m79stringList_.add(I_GEL);
m79stringList_.add(I_BRULURE);
m79stringList_.add(I_SIMPLE_POISON);
m79stringList_.add(I_POISON_GRAVE);
m79stringMapStringList_.addEntry(I_DANSE_PLUIE,m79stringList_);
m79abilityData_.setImmuStatus(m79stringMapStringList_);
return m79abilityData_;
}
static AbilityData m76(){
AbilityData m80abilityData_ = Instances.newAbilityData();
EnumList<Statistic> m80enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
m80enumListStatistic_.add(Statistic.ATTACK);
m80abilityData_.setImmuLowStat(m80enumListStatistic_);
return m80abilityData_;
}
static AbilityData m77(){
AbilityData m81abilityData_ = Instances.newAbilityData();
StringMap<Rate> m81stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m81stringMapRate_.addEntry(I_FEU,Rate.newRate(R_1_2));
m81abilityData_.setMultDamageFoe(m81stringMapRate_);
CustList<EffectEndRound> m81custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual m81effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
m81effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate(R_0));
m81effectEndRoundIndividual_.setRecoilDamage(Rate.newRate(R_0));
m81effectEndRoundIndividual_.setHealHp(Rate.newRate(R_0));
m81stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m81stringMapRate_.addEntry(I_BRULURE,Rate.newRate(OD+R_2));
m81effectEndRoundIndividual_.setMultDamageStatus(m81stringMapRate_);
m81effectEndRoundIndividual_.setUserStatusEndRound(ES);
m81effectEndRoundIndividual_.setFailEndRound(ES);
m81effectEndRoundIndividual_.setEndRoundRank(42);
m81effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
m81effectEndRoundIndividual_.setFail(ES);
m81custListEffectEndRound_.add(m81effectEndRoundIndividual_);
m81abilityData_.setEffectEndRound(m81custListEffectEndRound_);
return m81abilityData_;
}
static AbilityData m78(){
AbilityData m82abilityData_ = Instances.newAbilityData();
StringMap<StringList> m82stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m82stringList_=new StringList(new CollCapacity(1));
m82stringList_.add(I_BRULURE);
m82stringMapStringList_.addEntry(ES,m82stringList_);
m82stringList_=new StringList(new CollCapacity(1));
m82stringList_.add(I_BRULURE);
m82stringMapStringList_.addEntry(I_GRELE,m82stringList_);
m82stringList_=new StringList(new CollCapacity(1));
m82stringList_.add(I_BRULURE);
m82stringMapStringList_.addEntry(I_ZENITH,m82stringList_);
m82stringList_=new StringList(new CollCapacity(1));
m82stringList_.add(I_BRULURE);
m82stringMapStringList_.addEntry(I_DANSE_PLUIE,m82stringList_);
m82stringList_=new StringList(new CollCapacity(1));
m82stringList_.add(I_BRULURE);
m82stringMapStringList_.addEntry(I_ORAGE,m82stringList_);
m82stringList_=new StringList(new CollCapacity(1));
m82stringList_.add(I_BRULURE);
m82stringMapStringList_.addEntry(I_TEMPETESABLE,m82stringList_);
m82abilityData_.setImmuStatus(m82stringMapStringList_);
return m82abilityData_;
}
static AbilityData m79(){
AbilityData m83abilityData_ = Instances.newAbilityData();
StatisticStatusList m83objectMapStatisticStatusByte_=new StatisticStatusList(new CollCapacity(1));
m83objectMapStatisticStatusByte_.addEntry(new StatisticStatus(Statistic.SPEED,I_PEUR),(byte)1);
m83abilityData_.setMultStatIfStatutRank(m83objectMapStatisticStatusByte_);
return m83abilityData_;
}
static AbilityData m80(){
AbilityData m84abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> m84enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m84enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
m84abilityData_.setMultStatIfKoFoe(m84enumMapStatisticByte_);
return m84abilityData_;
}
static AbilityData m81(){
AbilityData m85abilityData_ = Instances.newAbilityData();
m85abilityData_.setIgnFoeStatisBoost(true);
return m85abilityData_;
}
static AbilityData m82(){
AbilityData m86abilityData_ = Instances.newAbilityData();
StringList m86stringList_=new StringList(new CollCapacity(4));
m86stringList_.add(I_PROTECTION);
m86stringList_.add(I_MUR_LUMIERE);
m86stringList_.add(I_RUNE_PROTECT);
m86stringList_.add(I_BRUME);
m86abilityData_.setIgnFoeTeamMove(m86stringList_);
return m86abilityData_;
}
static AbilityData m83(){
AbilityData m87abilityData_ = Instances.newAbilityData();
StringMap<StringList> m87stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m87stringList_=new StringList(new CollCapacity(2));
m87stringList_.add(I_SOMMEIL);
m87stringList_.add(I_SOMMEIL_REPOS);
m87stringMapStringList_.addEntry(ES,m87stringList_);
m87stringList_=new StringList(new CollCapacity(2));
m87stringList_.add(I_SOMMEIL);
m87stringList_.add(I_SOMMEIL_REPOS);
m87stringMapStringList_.addEntry(I_GRELE,m87stringList_);
m87stringList_=new StringList(new CollCapacity(2));
m87stringList_.add(I_SOMMEIL);
m87stringList_.add(I_SOMMEIL_REPOS);
m87stringMapStringList_.addEntry(I_ZENITH,m87stringList_);
m87stringList_=new StringList(new CollCapacity(2));
m87stringList_.add(I_SOMMEIL);
m87stringList_.add(I_SOMMEIL_REPOS);
m87stringMapStringList_.addEntry(I_DANSE_PLUIE,m87stringList_);
m87stringList_=new StringList(new CollCapacity(2));
m87stringList_.add(I_SOMMEIL);
m87stringList_.add(I_SOMMEIL_REPOS);
m87stringMapStringList_.addEntry(I_ORAGE,m87stringList_);
m87stringList_=new StringList(new CollCapacity(2));
m87stringList_.add(I_SOMMEIL);
m87stringList_.add(I_SOMMEIL_REPOS);
m87stringMapStringList_.addEntry(I_TEMPETESABLE,m87stringList_);
m87abilityData_.setImmuStatus(m87stringMapStringList_);
return m87abilityData_;
}
static AbilityData m84(){
AbilityData m88abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m88custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m88effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingWithStatistic();
EffectStatistic m88effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> m88enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m88enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)-1);
m88effectStatistic_.setStatisVarRank(m88enumMapStatisticByte_);
EnumMap<Statistic,String> m88enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m88enumMapStatisticString_.addEntry(Statistic.ATTACK,V_CIBLE_CLONE+GT+R_0);
m88effectStatistic_.setLocalFailStatis(m88enumMapStatisticString_);
m88effectStatistic_.setEvtRate(Rate.newRate(R_1));
m88effectStatistic_.setTargetChoice(TargetChoice.TOUS_ADV);
m88effectStatistic_.setFail(CF);
m88effectWhileSendingWithStatistic_.setEffect(m88effectStatistic_);
m88custListEffectWhileSendingWithStatistic_.add(m88effectWhileSendingWithStatistic_);
m88abilityData_.setEffectSending(m88custListEffectWhileSendingWithStatistic_);
return m88abilityData_;
}
static AbilityData m85(){
AbilityData m89abilityData_ = Instances.newAbilityData();
StringMap<Rate> m89stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
m89stringMapRate_.addEntry(I_GLACE,Rate.newRate(R_1_2));
m89stringMapRate_.addEntry(I_FEU,Rate.newRate(R_1_2));
m89abilityData_.setMultDamageFoe(m89stringMapRate_);
return m89abilityData_;
}
static AbilityData m86(){
AbilityData m90abilityData_ = Instances.newAbilityData();
MonteCarloString m90monteCarloString_=new MonteCarloString(new CollCapacity(2));
m90monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_7));
m90monteCarloString_.addQuickEvent(I_AMOUR,LgInt.newLgInt(R_3));
m90abilityData_.setSingleStatus(m90monteCarloString_);
StringMap<String> m90stringMapString_=new StringMap<String>(new CollCapacity(1));
m90stringMapString_.addEntry(I_AMOUR,V_EXISTE_GENRE_ASSEXUE+OO+V_GENRES_EGAUX+OO+V_CIBLE_POSSEDE_STATUT_RELATION+SE+I_AMOUR);
m90abilityData_.setFailStatus(m90stringMapString_);
return m90abilityData_;
}
static AbilityData m87(){
AbilityData m91abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> m91enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m91enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)1);
m91abilityData_.setBoostStatRankProtected(m91enumMapStatisticByte_);
StringMap<StringList> m91stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m91stringList_=new StringList(new CollCapacity(1));
m91stringList_.add(I_EAU);
m91stringMapStringList_.addEntry(ES,m91stringList_);
m91stringList_=new StringList(new CollCapacity(1));
m91stringList_.add(I_EAU);
m91stringMapStringList_.addEntry(I_GRELE,m91stringList_);
m91stringList_=new StringList(new CollCapacity(1));
m91stringList_.add(I_EAU);
m91stringMapStringList_.addEntry(I_ZENITH,m91stringList_);
m91stringList_=new StringList(new CollCapacity(1));
m91stringList_.add(I_EAU);
m91stringMapStringList_.addEntry(I_DANSE_PLUIE,m91stringList_);
m91stringList_=new StringList(new CollCapacity(1));
m91stringList_.add(I_EAU);
m91stringMapStringList_.addEntry(I_ORAGE,m91stringList_);
m91stringList_=new StringList(new CollCapacity(1));
m91stringList_.add(I_EAU);
m91stringMapStringList_.addEntry(I_TEMPETESABLE,m91stringList_);
m91abilityData_.setImmuMoveTypesByWeather(m91stringMapStringList_);
return m91abilityData_;
}
static AbilityData m88(){
AbilityData m92abilityData_ = Instances.newAbilityData();
m92abilityData_.setMultDamage(A_CARACGAUCHEOUVERT+LP+V_COEFF_EFF+OC+R_1+RP+OP+R_1);
return m92abilityData_;
}
static AbilityData m89(){
AbilityData m93abilityData_ = Instances.newAbilityData();
StringList m93stringList_=new StringList(new CollCapacity(4));
m93stringList_.add(I_VOL_MAGNETIK);
m93stringList_.add(I_LEVIKINESIE);
m93stringList_.add(I_PICOTS);
m93stringList_.add(I_PICS_TOXIK);
m93abilityData_.setImmuMove(m93stringList_);
StringMap<StringList> m93stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
m93stringList_=new StringList(new CollCapacity(1));
m93stringList_.add(I_SOL);
m93stringMapStringList_.addEntry(ES,m93stringList_);
m93stringList_=new StringList(new CollCapacity(1));
m93stringList_.add(I_SOL);
m93stringMapStringList_.addEntry(I_GRELE,m93stringList_);
m93stringList_=new StringList(new CollCapacity(1));
m93stringList_.add(I_SOL);
m93stringMapStringList_.addEntry(I_ZENITH,m93stringList_);
m93stringList_=new StringList(new CollCapacity(1));
m93stringList_.add(I_SOL);
m93stringMapStringList_.addEntry(I_DANSE_PLUIE,m93stringList_);
m93stringList_=new StringList(new CollCapacity(1));
m93stringList_.add(I_SOL);
m93stringMapStringList_.addEntry(I_ORAGE,m93stringList_);
m93stringList_=new StringList(new CollCapacity(1));
m93stringList_.add(I_SOL);
m93stringMapStringList_.addEntry(I_TEMPETESABLE,m93stringList_);
m93abilityData_.setImmuMoveTypesByWeather(m93stringMapStringList_);
return m93abilityData_;
}
static AbilityData m90(){
AbilityData m94abilityData_ = Instances.newAbilityData();
m94abilityData_.setTakeItemByDamagingMove(true);
return m94abilityData_;
}
static AbilityData m91(){
AbilityData m95abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Rate> m95enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
m95enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate(R_2));
m95abilityData_.setMultStatAlly(m95enumMapStatisticRate_);
EnumMap<Statistic,String> m95enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m95enumMapStatisticString_.addEntry(Statistic.ACCURACY,R_2);
m95abilityData_.setMultStat(m95enumMapStatisticString_);
return m95abilityData_;
}
static AbilityData m92(){
AbilityData m96abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Rate> m96enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
m96enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate(R_2));
m96abilityData_.setMultStatAlly(m96enumMapStatisticRate_);
EnumMap<Statistic,String> m96enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m96enumMapStatisticString_.addEntry(Statistic.ACCURACY,R_2);
m96abilityData_.setMultStat(m96enumMapStatisticString_);
return m96abilityData_;
}
static AbilityData m93(){
AbilityData m97abilityData_ = Instances.newAbilityData();
StringMap<Rate> m97stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
m97stringMapRate_.addEntry(I_SOMMEIL,Rate.newRate(R_2));
m97stringMapRate_.addEntry(I_SOMMEIL_REPOS,Rate.newRate(R_2));
m97abilityData_.setDivideStatusRound(m97stringMapRate_);
return m97abilityData_;
}
static AbilityData m94(){
AbilityData m98abilityData_ = Instances.newAbilityData();
CustList<EffectEndRound> m98custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundMultiRelation m98effectEndRoundMultiRelation_=Instances.newEffectEndRoundMultiRelation();
StringMap<Rate> m98stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
m98stringMapRate_.addEntry(I_SOMMEIL,Rate.newRate(R_1_8));
m98stringMapRate_.addEntry(I_SOMMEIL_REPOS,Rate.newRate(R_1_8));
m98effectEndRoundMultiRelation_.setDamageByStatus(m98stringMapRate_);
m98effectEndRoundMultiRelation_.setFailEndRound(ES);
m98effectEndRoundMultiRelation_.setEndRoundRank(59);
m98effectEndRoundMultiRelation_.setTargetChoice(TargetChoice.TOUS_ADV);
m98effectEndRoundMultiRelation_.setFail(ES);
m98custListEffectEndRound_.add(m98effectEndRoundMultiRelation_);
m98abilityData_.setEffectEndRound(m98custListEffectEndRound_);
return m98abilityData_;
}
static AbilityData m95(){
AbilityData m99abilityData_ = Instances.newAbilityData();
m99abilityData_.setHealedStatusBySwitch(true);
return m99abilityData_;
}
static AbilityData m96(){
AbilityData m100abilityData_ = Instances.newAbilityData();
m100abilityData_.setMultPower(R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_NOM+RB+OC+LB+I_AURASPHERE+OS+I_VIBROBSCUR+OS+I_DRACOCHOC+OS+I_VIBRA_SOIN+OS+I_VIBRAQUA+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_AURASPHERE+OS+I_VIBROBSCUR+OS+I_DRACOCHOC+OS+I_VIBRA_SOIN+OS+I_VIBRAQUA+RB+OC+LB+V_ATTAQUE_NOM+RB+RP+RP);
return m100abilityData_;
}
static AbilityData m97(){
AbilityData m101abilityData_ = Instances.newAbilityData();
StringMap<String> m101stringMapString_=new StringMap<String>(new CollCapacity(4));
m101stringMapString_.addEntry(I_GRELE,I_GLACE);
m101stringMapString_.addEntry(I_ZENITH,I_FEU);
m101stringMapString_.addEntry(I_DANSE_PLUIE,I_EAU);
m101stringMapString_.addEntry(I_TEMPETESABLE,I_ROCHE);
m101abilityData_.setChgtTypeByWeather(m101stringMapString_);
return m101abilityData_;
}
static AbilityData m98(){
AbilityData m102abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m102enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m102enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,R_3_2);
m102abilityData_.setMultStat(m102enumMapStatisticString_);
return m102abilityData_;
}
static AbilityData m99(){
AbilityData m103abilityData_ = Instances.newAbilityData();
StringList m103stringList_=new StringList(new CollCapacity(3));
m103stringList_.add(I_DESTRUCTION);
m103stringList_.add(I_EXPLOSION);
m103stringList_.add(I_TOUT_OU_RIEN);
m103abilityData_.setImmuMove(m103stringList_);
return m103abilityData_;
}
static AbilityData m100(){
AbilityData m104abilityData_ = Instances.newAbilityData();
m104abilityData_.setMumy(true);
return m104abilityData_;
}
static AbilityData m101(){
AbilityData m105abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> m105enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m105enumMapStatisticByte_.addEntry(Statistic.SPEED,(byte)1);
m105abilityData_.setBoostStatRankProtected(m105enumMapStatisticByte_);
StringMap<StringList> m105stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m105stringList_=new StringList(new CollCapacity(1));
m105stringList_.add(I_ELECTRIQUE);
m105stringMapStringList_.addEntry(ES,m105stringList_);
m105stringList_=new StringList(new CollCapacity(1));
m105stringList_.add(I_ELECTRIQUE);
m105stringMapStringList_.addEntry(I_GRELE,m105stringList_);
m105stringList_=new StringList(new CollCapacity(1));
m105stringList_.add(I_ELECTRIQUE);
m105stringMapStringList_.addEntry(I_ZENITH,m105stringList_);
m105stringList_=new StringList(new CollCapacity(1));
m105stringList_.add(I_ELECTRIQUE);
m105stringMapStringList_.addEntry(I_DANSE_PLUIE,m105stringList_);
m105stringList_=new StringList(new CollCapacity(1));
m105stringList_.add(I_ELECTRIQUE);
m105stringMapStringList_.addEntry(I_ORAGE,m105stringList_);
m105stringList_=new StringList(new CollCapacity(1));
m105stringList_.add(I_ELECTRIQUE);
m105stringMapStringList_.addEntry(I_TEMPETESABLE,m105stringList_);
m105abilityData_.setImmuMoveTypesByWeather(m105stringMapStringList_);
return m105abilityData_;
}
static AbilityData m102(){
AbilityData m106abilityData_ = Instances.newAbilityData();
CustList<EffectEndRound> m106custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual m106effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
m106effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate(R_3_10));
m106effectEndRoundIndividual_.setRecoilDamage(Rate.newRate(R_0));
m106effectEndRoundIndividual_.setHealHp(Rate.newRate(R_0));
m106effectEndRoundIndividual_.setUserStatusEndRound(ES);
m106effectEndRoundIndividual_.setFailEndRound(ES);
m106effectEndRoundIndividual_.setEndRoundRank(24);
m106effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
m106effectEndRoundIndividual_.setFail(ES);
m106custListEffectEndRound_.add(m106effectEndRoundIndividual_);
m106abilityData_.setEffectEndRound(m106custListEffectEndRound_);
return m106abilityData_;
}
static AbilityData m103(){
AbilityData m107abilityData_ = Instances.newAbilityData();
StringList m107stringList_=new StringList(new CollCapacity(4));
m107stringList_.add(I_IMITATION);
m107stringList_.add(I_ECHANGE);
m107stringList_.add(I_PASSE_PASSE);
m107stringList_.add(I_TOURMAGIK);
m107abilityData_.setImmuMove(m107stringList_);
m107abilityData_.setPlate(true);
return m107abilityData_;
}
static AbilityData m104(){
AbilityData m108abilityData_ = Instances.newAbilityData();
m108abilityData_.setNbHits(true);
return m108abilityData_;
}
static AbilityData m105(){
AbilityData m109abilityData_ = Instances.newAbilityData();
m109abilityData_.setTypeForMoves(I_NORMAL);
return m109abilityData_;
}
static AbilityData m106(){
AbilityData m110abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m110enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m110enumMapStatisticString_.addEntry(Statistic.ACCURACY,R_13_10);
m110abilityData_.setMultStat(m110enumMapStatisticString_);
return m110abilityData_;
}
static AbilityData m107(){
AbilityData m111abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> m111enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m111enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)1);
m111abilityData_.setBoostStatRankProtected(m111enumMapStatisticByte_);
StringMap<StringList> m111stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m111stringList_=new StringList(new CollCapacity(1));
m111stringList_.add(I_ELECTRIQUE);
m111stringMapStringList_.addEntry(ES,m111stringList_);
m111stringList_=new StringList(new CollCapacity(1));
m111stringList_.add(I_ELECTRIQUE);
m111stringMapStringList_.addEntry(I_GRELE,m111stringList_);
m111stringList_=new StringList(new CollCapacity(1));
m111stringList_.add(I_ELECTRIQUE);
m111stringMapStringList_.addEntry(I_ZENITH,m111stringList_);
m111stringList_=new StringList(new CollCapacity(1));
m111stringList_.add(I_ELECTRIQUE);
m111stringMapStringList_.addEntry(I_DANSE_PLUIE,m111stringList_);
m111stringList_=new StringList(new CollCapacity(1));
m111stringList_.add(I_ELECTRIQUE);
m111stringMapStringList_.addEntry(I_ORAGE,m111stringList_);
m111stringList_=new StringList(new CollCapacity(1));
m111stringList_.add(I_ELECTRIQUE);
m111stringMapStringList_.addEntry(I_TEMPETESABLE,m111stringList_);
m111abilityData_.setImmuMoveTypesByWeather(m111stringMapStringList_);
return m111abilityData_;
}
static AbilityData m108(){
AbilityData m112abilityData_ = Instances.newAbilityData();
StringList m112stringList_=new StringList(new CollCapacity(21));
m112stringList_.add(I_BOMBE_ACIDE);
m112stringList_.add(I_AURASPHERE);
m112stringList_.add(I_PILONNAGE);
m112stringList_.add(I_BALLE_GRAINE);
m112stringList_.add(I_BOMB_OEUF);
m112stringList_.add(I_BOULE_ELEK);
m112stringList_.add(I_ECO_SPHERE);
m112stringList_.add(I_EXPLOFORCE);
m112stringList_.add(I_GYROBALLE);
m112stringList_.add(I_BALL_GLACE);
m112stringList_.add(I_BOMBAIMANT);
m112stringList_.add(I_BALL_BRUME);
m112stringList_.add(I_BOUE_BOMBE);
m112stringList_.add(I_OCTAZOOKA);
m112stringList_.add(I_ROC_BOULET);
m112stringList_.add(I_INCENDIE);
m112stringList_.add(I_CANON_GRAINE);
m112stringList_.add(I_BALL_OMBRE);
m112stringList_.add(I_BOMB_BEURK);
m112stringList_.add(I_BALL_METEO);
m112stringList_.add(I_ELECANON);
m112abilityData_.setImmuMove(m112stringList_);
return m112abilityData_;
}
static AbilityData m109(){
AbilityData m113abilityData_ = Instances.newAbilityData();
m113abilityData_.setRecoilDamageFoe(Rate.newRate(R_1_8));
return m113abilityData_;
}
static AbilityData m110(){
AbilityData m114abilityData_ = Instances.newAbilityData();
StringMap<TypeDamageBoost> m114stringMapTypeDamageBoost_=new StringMap<TypeDamageBoost>(new CollCapacity(1));
m114stringMapTypeDamageBoost_.addEntry(I_NORMAL,new TypeDamageBoost(I_FEE,Rate.newRate(R_13_10)));
m114abilityData_.setChangingBoostTypes(m114stringMapTypeDamageBoost_);
return m114abilityData_;
}
static AbilityData m111(){
AbilityData m115abilityData_ = Instances.newAbilityData();
StringMap<TypeDamageBoost> m115stringMapTypeDamageBoost_=new StringMap<TypeDamageBoost>(new CollCapacity(1));
m115stringMapTypeDamageBoost_.addEntry(I_NORMAL,new TypeDamageBoost(I_GLACE,Rate.newRate(R_13_10)));
m115abilityData_.setChangingBoostTypes(m115stringMapTypeDamageBoost_);
return m115abilityData_;
}
static AbilityData m112(){
AbilityData m116abilityData_ = Instances.newAbilityData();
StatisticCategoryRate m116objectMapStatisticCategoryRate_=new StatisticCategoryRate(new CollCapacity(1));
m116objectMapStatisticCategoryRate_.addEntry(new StatisticCategory(Statistic.EVASINESS,I_AUTRE),Rate.newRate(R_2));
m116abilityData_.setMultStatIfCat(m116objectMapStatisticCategoryRate_);
return m116abilityData_;
}
static AbilityData m113(){
AbilityData m117abilityData_ = Instances.newAbilityData();
StringMap<Rate> m117stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
m117stringMapRate_.addEntry(I_ZENITH,Rate.newRate(OD+R_1_8));
m117stringMapRate_.addEntry(I_DANSE_PLUIE,Rate.newRate(R_1_8));
m117abilityData_.setHealHpByWeather(m117stringMapRate_);
m117stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m117stringMapRate_.addEntry(I_FEU,Rate.newRate(R_5_4));
m117abilityData_.setMultDamageFoe(m117stringMapRate_);
WeatherTypes m117objectMapWeatherTypeRate_=new WeatherTypes(new CollCapacity(6));
m117objectMapWeatherTypeRate_.addEntry(new WeatherType(I_TEMPETESABLE,I_EAU),Rate.newRate(R_1_4));
m117objectMapWeatherTypeRate_.addEntry(new WeatherType(ES,I_EAU),Rate.newRate(R_1_4));
m117objectMapWeatherTypeRate_.addEntry(new WeatherType(I_GRELE,I_EAU),Rate.newRate(R_1_4));
m117objectMapWeatherTypeRate_.addEntry(new WeatherType(I_ZENITH,I_EAU),Rate.newRate(R_1_4));
m117objectMapWeatherTypeRate_.addEntry(new WeatherType(I_DANSE_PLUIE,I_EAU),Rate.newRate(R_1_4));
m117objectMapWeatherTypeRate_.addEntry(new WeatherType(I_ORAGE,I_EAU),Rate.newRate(R_1_4));
m117abilityData_.setHealHpByTypeIfWeather(m117objectMapWeatherTypeRate_);
StringMap<StringList> m117stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m117stringList_=new StringList(new CollCapacity(1));
m117stringList_.add(I_EAU);
m117stringMapStringList_.addEntry(ES,m117stringList_);
m117stringList_=new StringList(new CollCapacity(1));
m117stringList_.add(I_EAU);
m117stringMapStringList_.addEntry(I_GRELE,m117stringList_);
m117stringList_=new StringList(new CollCapacity(1));
m117stringList_.add(I_EAU);
m117stringMapStringList_.addEntry(I_ZENITH,m117stringList_);
m117stringList_=new StringList(new CollCapacity(1));
m117stringList_.add(I_EAU);
m117stringMapStringList_.addEntry(I_DANSE_PLUIE,m117stringList_);
m117stringList_=new StringList(new CollCapacity(1));
m117stringList_.add(I_EAU);
m117stringMapStringList_.addEntry(I_ORAGE,m117stringList_);
m117stringList_=new StringList(new CollCapacity(1));
m117stringList_.add(I_EAU);
m117stringMapStringList_.addEntry(I_TEMPETESABLE,m117stringList_);
m117abilityData_.setImmuMoveTypesByWeather(m117stringMapStringList_);
return m117abilityData_;
}
static AbilityData m114(){
AbilityData m118abilityData_ = Instances.newAbilityData();
StatisticTypeByte m118objectMapStatisticTypeByte_=new StatisticTypeByte(new CollCapacity(3));
m118objectMapStatisticTypeByte_.addEntry(new StatisticType(Statistic.SPEED,I_SPECTRE),(byte)1);
m118objectMapStatisticTypeByte_.addEntry(new StatisticType(Statistic.SPEED,I_INSECTE),(byte)1);
m118objectMapStatisticTypeByte_.addEntry(new StatisticType(Statistic.SPEED,I_TENEBRE),(byte)1);
m118abilityData_.setMultStatIfDamgeType(m118objectMapStatisticTypeByte_);
return m118abilityData_;
}
static AbilityData m115(){
AbilityData m119abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m119enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m119enumMapStatisticString_.addEntry(Statistic.EVASINESS,R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_STATUTS+RB+OC+LB+I_CONFUSION+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_CONFUSION+RB+OC+LB+V_FIGHTER_STATUTS+RB+RP+RP);
m119abilityData_.setMultStat(m119enumMapStatisticString_);
return m119abilityData_;
}
static AbilityData m116(){
AbilityData m120abilityData_ = Instances.newAbilityData();
m120abilityData_.setImmuLowStatIfStatus(new CustList<StatisticStatus>(new StatisticStatus(Statistic.SPEED,I_PARALYSIE)));
EnumMap<Statistic,String> m120enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m120enumMapStatisticString_.addEntry(Statistic.SPEED,R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_STATUTS+RB+OC+LB+I_BRULURE+OS+I_POISON_GRAVE+OS+I_SOMMEIL_REPOS+OS+I_SIMPLE_POISON+OS+I_PARALYSIE+OS+I_SOMMEIL+OS+I_GEL+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_BRULURE+OS+I_POISON_GRAVE+OS+I_SOMMEIL_REPOS+OS+I_SIMPLE_POISON+OS+I_PARALYSIE+OS+I_SOMMEIL+OS+I_GEL+RB+OC+LB+V_FIGHTER_STATUTS+RB+RP+RP);
m120abilityData_.setMultStat(m120enumMapStatisticString_);
return m120abilityData_;
}
static AbilityData m117(){
AbilityData m121abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Rate> m121enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
m121enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate(R_2));
m121abilityData_.setMultStatAlly(m121enumMapStatisticRate_);
EnumMap<Statistic,String> m121enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m121enumMapStatisticString_.addEntry(Statistic.ACCURACY,R_2);
m121abilityData_.setMultStat(m121enumMapStatisticString_);
return m121abilityData_;
}
static AbilityData m118(){
AbilityData m122abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m122enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m122enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,R_3_2);
m122abilityData_.setMultStat(m122enumMapStatisticString_);
return m122abilityData_;
}
static AbilityData m119(){
AbilityData m123abilityData_ = Instances.newAbilityData();
m123abilityData_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_NOM+RB+OC+LB+I_POING_ECLAIR+OS+I_MARTO_POING+OS+I_POING_OMBRE+OS+I_PISTO_POING+OS+I_DYNAMOPOING+OS+I_ULTIMAPOING+OS+I_POING_DE_FEU+OS+I_POING_METEOR+OS+I_POINGLACE+OS+I_POING_COMETE+OS+I_VAMPIPOING+OS+I_UPPERCUT+OS+I_MITRA_POING+OS+I_STRATOPERCUT+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_POING_ECLAIR+OS+I_MARTO_POING+OS+I_POING_OMBRE+OS+I_PISTO_POING+OS+I_DYNAMOPOING+OS+I_ULTIMAPOING+OS+I_POING_DE_FEU+OS+I_POING_METEOR+OS+I_POINGLACE+OS+I_POING_COMETE+OS+I_VAMPIPOING+OS+I_UPPERCUT+OS+I_MITRA_POING+OS+I_STRATOPERCUT+RB+OC+LB+V_ATTAQUE_NOM+RB+RP+RP);
return m123abilityData_;
}
static AbilityData m120(){
AbilityData m124abilityData_ = Instances.newAbilityData();
MonteCarloString m124monteCarloString_=new MonteCarloString(new CollCapacity(2));
m124monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_7));
m124monteCarloString_.addQuickEvent(I_SIMPLE_POISON,LgInt.newLgInt(R_3));
m124abilityData_.setSingleStatus(m124monteCarloString_);
StringMap<String> m124stringMapString_=new StringMap<String>(new CollCapacity(1));
m124stringMapString_.addEntry(I_SIMPLE_POISON,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_SIMPLE_POISON+OS+I_POISON_GRAVE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_TYPES+RB+OC+LB+I_POISON+OS+I_ACIER+RB+RP+RP+GT+R_0);
m124abilityData_.setFailStatus(m124stringMapString_);
return m124abilityData_;
}
static AbilityData m121(){
AbilityData m125abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> m125enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m125enumMapStatisticByte_.addEntry(Statistic.SPEED,(byte)-1);
m125abilityData_.setLowStatFoeHit(m125enumMapStatisticByte_);
return m125abilityData_;
}
static AbilityData m122(){
AbilityData m126abilityData_ = Instances.newAbilityData();
MonteCarloString m126monteCarloString_=new MonteCarloString(new CollCapacity(4));
m126monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_7));
m126monteCarloString_.addQuickEvent(I_SIMPLE_POISON,LgInt.newLgInt(R_1));
m126monteCarloString_.addQuickEvent(I_SOMMEIL,LgInt.newLgInt(R_1));
m126monteCarloString_.addQuickEvent(I_PARALYSIE,LgInt.newLgInt(R_1));
m126abilityData_.setSingleStatus(m126monteCarloString_);
StringMap<String> m126stringMapString_=new StringMap<String>(new CollCapacity(3));
m126stringMapString_.addEntry(I_SIMPLE_POISON,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_SIMPLE_POISON+OS+I_POISON_GRAVE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_TYPES+RB+OC+LB+I_POISON+OS+I_ACIER+RB+RP+RP+GT+R_0);
m126stringMapString_.addEntry(I_SOMMEIL,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_SOMMEIL+OS+I_SOMMEIL_REPOS+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0);
m126stringMapString_.addEntry(I_PARALYSIE,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_PARALYSIE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0);
m126abilityData_.setFailStatus(m126stringMapString_);
return m126abilityData_;
}
static AbilityData m123(){
AbilityData m127abilityData_ = Instances.newAbilityData();
m127abilityData_.setNbUsedPp(1);
return m127abilityData_;
}
static AbilityData m124(){
AbilityData m128abilityData_ = Instances.newAbilityData();
m128abilityData_.setMultPower(R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_NOM+RB+OC+LB+I_MORSURE+OS+I_MACHOUILLE+OS+I_CROCS_ECLAIR+OS+I_CROCS_FEU+OS+I_CROCS_GIVRE+OS+I_CROCHETVENIN+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_MORSURE+OS+I_MACHOUILLE+OS+I_CROCS_ECLAIR+OS+I_CROCS_FEU+OS+I_CROCS_GIVRE+OS+I_CROCHETVENIN+RB+OC+LB+V_ATTAQUE_NOM+RB+RP+RP);
return m128abilityData_;
}
static AbilityData m125(){
AbilityData m129abilityData_ = Instances.newAbilityData();
m129abilityData_.setCopyMovesTypes(true);
return m129abilityData_;
}
static AbilityData m126(){
AbilityData m130abilityData_ = Instances.newAbilityData();
MonteCarloString m130monteCarloString_=new MonteCarloString(new CollCapacity(2));
m130monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_4));
m130monteCarloString_.addQuickEvent(I_PEUR,LgInt.newLgInt(R_1));
m130abilityData_.setSingleStatus(m130monteCarloString_);
StringMap<String> m130stringMapString_=new StringMap<String>(new CollCapacity(1));
m130stringMapString_.addEntry(I_PEUR,V_CIBLE_CLONE+GT+R_0);
m130abilityData_.setFailStatus(m130stringMapString_);
return m130abilityData_;
}
static AbilityData m127(){
AbilityData m131abilityData_ = Instances.newAbilityData();
m131abilityData_.setBreakFoeImmune(new CustList<TypesDuo>(new TypesDuo(I_NORMAL,I_SPECTRE),new TypesDuo(I_COMBAT,I_SPECTRE)));
return m131abilityData_;
}
static AbilityData m128(){
AbilityData m132abilityData_ = Instances.newAbilityData();
m132abilityData_.setGiveItemToAllyHavingUsed(true);
return m132abilityData_;
}
static AbilityData m129(){
AbilityData m133abilityData_ = Instances.newAbilityData();
StringList m133stringList_=new StringList(new CollCapacity(2));
m133stringList_.add(I_JET_DE_SABLE);
m133stringList_.add(I_TELEKINESIE);
m133abilityData_.setImmuMove(m133stringList_);
EnumList<Statistic> m133enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
m133enumListStatistic_.add(Statistic.ACCURACY);
m133abilityData_.setImmuLowStat(m133enumListStatistic_);
return m133abilityData_;
}
static AbilityData m130(){
AbilityData m134abilityData_ = Instances.newAbilityData();
m134abilityData_.setHealedHpRateBySwitch(Rate.newRate(R_1_3));
return m134abilityData_;
}
static AbilityData m131(){
AbilityData m135abilityData_ = Instances.newAbilityData();
StringList m135stringList_=new StringList(new CollCapacity(1));
m135stringList_.add(I_GRELE);
m135abilityData_.setImmuWeather(m135stringList_);
EnumMap<Statistic,String> m135enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m135enumMapStatisticString_.addEntry(Statistic.DEFENSE,A_MAX+LP+R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_GRELE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_GRELE+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m135abilityData_.setMultStat(m135enumMapStatisticString_);
return m135abilityData_;
}
static AbilityData m132(){
AbilityData m136abilityData_ = Instances.newAbilityData();
m136abilityData_.setMultPower(LP+R_5_4+OM+A_DIFFERENTNUM+LP+LB+V_CIBLE_GENRE+RB+OC+LB+V_LANCEUR_GENRE+RB+RP+OP+R_3_4+OM+A_EGALNUM+LP+LB+V_CIBLE_GENRE+RB+OC+LB+V_LANCEUR_GENRE+RB+RP+RP+OM+A_INCLUSNUM+LP+LB+V_CIBLE_GENRE+RB+OC+LB+I_FEMALE+OS+I_MALE+RB+RP+OM+A_INCLUSNUM+LP+LB+V_LANCEUR_GENRE+RB+OC+LB+I_FEMALE+OS+I_MALE+RB+RP);
return m136abilityData_;
}
static AbilityData m133(){
AbilityData m137abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m137custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m137effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m137effectWhileSendingWithStatistic_.setEnabledWeather(I_TEMPETESABLE);
m137custListEffectWhileSendingWithStatistic_.add(m137effectWhileSendingWithStatistic_);
m137abilityData_.setEffectSending(m137custListEffectWhileSendingWithStatistic_);
return m137abilityData_;
}
static AbilityData m134(){
AbilityData m138abilityData_ = Instances.newAbilityData();
m138abilityData_.setImmuDamageRecoil(true);
m138abilityData_.setImmuRechargeRound(true);
m138abilityData_.setCancelSecEffectOwner(true);
m138abilityData_.setMultPower(R_13_10);
return m138abilityData_;
}
static AbilityData m135(){
AbilityData m139abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m139custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m139effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m139effectWhileSendingWithStatistic_.setEnabledWeather(I_ZENITH);
m139custListEffectWhileSendingWithStatistic_.add(m139effectWhileSendingWithStatistic_);
m139abilityData_.setEffectSending(m139custListEffectWhileSendingWithStatistic_);
return m139abilityData_;
}
static AbilityData m136(){
AbilityData m140abilityData_ = Instances.newAbilityData();
m140abilityData_.setMultEvtRateSecEffectOwner(Rate.newRate(R_2));
return m140abilityData_;
}
static AbilityData m137(){
AbilityData m141abilityData_ = Instances.newAbilityData();
m141abilityData_.setMultVarBoost(Rate.newRate(R_2));
return m141abilityData_;
}
static AbilityData m138(){
AbilityData m142abilityData_ = Instances.newAbilityData();
m142abilityData_.setMultDamageCh(Rate.newRate(R_3_2));
return m142abilityData_;
}
static AbilityData m139(){
AbilityData m143abilityData_ = Instances.newAbilityData();
CustList<EffectEndRound> m143custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual m143effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
m143effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate(R_0));
m143effectEndRoundIndividual_.setRecoilDamage(Rate.newRate(R_0));
m143effectEndRoundIndividual_.setHealHp(Rate.newRate(R_0));
StringMap<Rate> m143stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
m143stringMapRate_.addEntry(I_POISON_GRAVE,Rate.newRate(OD+R_2));
m143stringMapRate_.addEntry(I_SIMPLE_POISON,Rate.newRate(OD+R_2));
m143effectEndRoundIndividual_.setMultDamageStatus(m143stringMapRate_);
m143effectEndRoundIndividual_.setUserStatusEndRound(ES);
m143effectEndRoundIndividual_.setFailEndRound(ES);
m143effectEndRoundIndividual_.setEndRoundRank(41);
m143effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
m143effectEndRoundIndividual_.setFail(ES);
m143custListEffectEndRound_.add(m143effectEndRoundIndividual_);
m143abilityData_.setEffectEndRound(m143custListEffectEndRound_);
return m143abilityData_;
}
static AbilityData m140(){
AbilityData m144abilityData_ = Instances.newAbilityData();
m144abilityData_.setMultSufferedDamageSuperEff(Rate.newRate(R_3_4));
return m144abilityData_;
}
static AbilityData m141(){
AbilityData m145abilityData_ = Instances.newAbilityData();
MonteCarloString m145monteCarloString_=new MonteCarloString(new CollCapacity(2));
m145monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_7));
m145monteCarloString_.addQuickEvent(I_PARALYSIE,LgInt.newLgInt(R_3));
m145abilityData_.setSingleStatus(m145monteCarloString_);
StringMap<String> m145stringMapString_=new StringMap<String>(new CollCapacity(1));
m145stringMapString_.addEntry(I_PARALYSIE,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_PARALYSIE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0);
m145abilityData_.setFailStatus(m145stringMapString_);
return m145abilityData_;
}
static AbilityData m142(){
AbilityData m146abilityData_ = Instances.newAbilityData();
m146abilityData_.setInflictingDamageInsteadOfSuffering(true);
return m146abilityData_;
}
static AbilityData m143(){
AbilityData m147abilityData_ = Instances.newAbilityData();
m147abilityData_.setGiveItemToAllyHavingUsed(true);
return m147abilityData_;
}
static AbilityData m144(){
AbilityData m148abilityData_ = Instances.newAbilityData();
StringMap<String> m148stringMapString_=new StringMap<String>(new CollCapacity(6));
m148stringMapString_.addEntry(I_POISON_GRAVE,I_SIMPLE_POISON);
m148stringMapString_.addEntry(I_BRULURE,I_BRULURE);
m148stringMapString_.addEntry(I_SIMPLE_POISON,I_SIMPLE_POISON);
m148stringMapString_.addEntry(I_SOMMEIL,I_SOMMEIL);
m148stringMapString_.addEntry(I_GEL,I_GEL);
m148stringMapString_.addEntry(I_PARALYSIE,I_PARALYSIE);
m148abilityData_.setForwardStatus(m148stringMapString_);
m148stringMapString_=new StringMap<String>(new CollCapacity(5));
m148stringMapString_.addEntry(I_BRULURE,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_BRULURE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_TYPES+RB+OC+LB+I_FEU+RB+RP+RP+GT+R_0);
m148stringMapString_.addEntry(I_SIMPLE_POISON,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_SIMPLE_POISON+OS+I_POISON_GRAVE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_TYPES+RB+OC+LB+I_POISON+OS+I_ACIER+RB+RP+RP+GT+R_0);
m148stringMapString_.addEntry(I_SOMMEIL,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_SOMMEIL+OS+I_SOMMEIL_REPOS+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0);
m148stringMapString_.addEntry(I_GEL,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_GEL+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_TYPES+RB+OC+LB+I_GLACE+RB+RP+RP+GT+R_0);
m148stringMapString_.addEntry(I_PARALYSIE,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_PARALYSIE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0);
m148abilityData_.setFailStatus(m148stringMapString_);
return m148abilityData_;
}
static AbilityData m145(){
AbilityData m149abilityData_ = Instances.newAbilityData();
m149abilityData_.setMultPower(R_3_2+OM+A_CARACDROITEFERME+LP+V_PUISSANCE_BASE+OC+R_60+RP+OP+A_CARACGAUCHEOUVERT+LP+V_PUISSANCE_BASE+OC+R_60+RP);
return m149abilityData_;
}
static AbilityData m146(){
AbilityData m150abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> m150custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m150effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingWithStatistic();
EffectStatistic m150effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> m150enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(2));
m150enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
m150enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)1);
m150effectStatistic_.setStatisVarRank(m150enumMapStatisticByte_);
m150effectStatistic_.setEvtRate(Rate.newRate(R_1));
m150effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
m150effectStatistic_.setFail(CF);
m150effectWhileSendingWithStatistic_.setEffect(m150effectStatistic_);
m150custListEffectWhileSendingWithStatistic_.add(m150effectWhileSendingWithStatistic_);
m150abilityData_.setEffectSending(m150custListEffectWhileSendingWithStatistic_);
return m150abilityData_;
}
static AbilityData m147(){
AbilityData m151abilityData_ = Instances.newAbilityData();
m151abilityData_.setImmuDamageAllyMoves(true);
return m151abilityData_;
}
static AbilityData m148(){
AbilityData m152abilityData_ = Instances.newAbilityData();
m152abilityData_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_NOM+RB+OC+LB+I_LUTTE+OS+I_FRACASS_TETE+OS+I_DAMOCLES+OS+I_MARTOBOIS+OS+I_ELECTACLE+OS+I_RAPACE+OS+I_PIED_SAUTE+OS+I_SACRIFICE+OS+I_BOUTEFEU+OS+I_BELIER+OS+I_PIED_VOLTIGE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_LUTTE+OS+I_FRACASS_TETE+OS+I_DAMOCLES+OS+I_MARTOBOIS+OS+I_ELECTACLE+OS+I_RAPACE+OS+I_PIED_SAUTE+OS+I_SACRIFICE+OS+I_BOUTEFEU+OS+I_BELIER+OS+I_PIED_VOLTIGE+RB+OC+LB+V_ATTAQUE_NOM+RB+RP+RP);
return m152abilityData_;
}
static AbilityData m149(){
AbilityData m153abilityData_ = Instances.newAbilityData();
StringMap<StringList> m153stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m153stringList_=new StringList(new CollCapacity(1));
m153stringList_.add(I_CONFUSION);
m153stringMapStringList_.addEntry(ES,m153stringList_);
m153stringList_=new StringList(new CollCapacity(1));
m153stringList_.add(I_CONFUSION);
m153stringMapStringList_.addEntry(I_GRELE,m153stringList_);
m153stringList_=new StringList(new CollCapacity(1));
m153stringList_.add(I_CONFUSION);
m153stringMapStringList_.addEntry(I_ZENITH,m153stringList_);
m153stringList_=new StringList(new CollCapacity(1));
m153stringList_.add(I_CONFUSION);
m153stringMapStringList_.addEntry(I_DANSE_PLUIE,m153stringList_);
m153stringList_=new StringList(new CollCapacity(1));
m153stringList_.add(I_CONFUSION);
m153stringMapStringList_.addEntry(I_ORAGE,m153stringList_);
m153stringList_=new StringList(new CollCapacity(1));
m153stringList_.add(I_CONFUSION);
m153stringMapStringList_.addEntry(I_TEMPETESABLE,m153stringList_);
m153abilityData_.setImmuStatus(m153stringMapStringList_);
return m153abilityData_;
}
static AbilityData m150(){
AbilityData m154abilityData_ = Instances.newAbilityData();
m154abilityData_.setForbidUseBerryAgainstFoes(true);
return m154abilityData_;
}
static AbilityData m151(){
AbilityData m155abilityData_ = Instances.newAbilityData();
StringList m155stringList_=new StringList(new CollCapacity(46));
m155stringList_.add(I_ABSORB_EAU);
m155stringList_.add(I_ABSORB_VOLT);
m155stringList_.add(I_ANTI_BRUIT);
m155stringList_.add(I_ARMUMAGMA);
m155stringList_.add(I_ARMURBASTON);
m155stringList_.add(I_ATTENTION);
m155stringList_.add(I_BENET);
m155stringList_.add(I_COEUR_DE_COQ);
m155stringList_.add(I_COQUE_ARMURE);
m155stringList_.add(I_CORPS_SAIN);
m155stringList_.add(I_DON_FLORAL);
m155stringList_.add(I_ECAILLE_SPECIALE);
m155stringList_.add(I_ECHAUFFEMENT);
m155stringList_.add(I_ECRAN_FUMEE);
m155stringList_.add(I_ECRAN_POUDRE);
m155stringList_.add(I_ESPRIT_VITAL);
m155stringList_.add(I_FERMETE);
m155stringList_.add(I_FEUILLE_GARDE);
m155stringList_.add(I_FILTRE);
m155stringList_.add(I_GARDE_MYSTIK);
m155stringList_.add(I_GLUE);
m155stringList_.add(I_HERBIVORE);
m155stringList_.add(I_HYPER_CUTTER);
m155stringList_.add(I_IGNIFU_VOILE);
m155stringList_.add(I_IGNIFUGE);
m155stringList_.add(I_INCONSCIENT);
m155stringList_.add(I_INSOMNIA);
m155stringList_.add(I_ISOGRAISSE);
m155stringList_.add(I_LAVABO);
m155stringList_.add(I_LEVITATION);
m155stringList_.add(I_MOITEUR);
m155stringList_.add(I_MOMIE);
m155stringList_.add(I_MOTORISE);
m155stringList_.add(I_PARATONNERRE);
m155stringList_.add(I_PEAU_SECHE);
m155stringList_.add(I_PIEDS_CONFUS);
m155stringList_.add(I_REGARD_VIF);
m155stringList_.add(I_RIDEAU_NEIGE);
m155stringList_.add(I_SIMPLE);
m155stringList_.add(I_SOLIDE_ROC);
m155stringList_.add(I_TEMPO_PERSO);
m155stringList_.add(I_TENSION);
m155stringList_.add(I_TORCHE);
m155stringList_.add(I_VACCIN);
m155stringList_.add(I_VENTOUSE);
m155stringList_.add(I_VOILE_SABLE);
m155abilityData_.setIgnAbility(m155stringList_);
m155abilityData_.setMumy(true);
return m155abilityData_;
}
static AbilityData m152(){
AbilityData m156abilityData_ = Instances.newAbilityData();
m156abilityData_.setImmuDamageRecoil(true);
return m156abilityData_;
}
static AbilityData m153(){
AbilityData m157abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m157enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m157enumMapStatisticString_.addEntry(Statistic.DEFENSE,R_2);
m157abilityData_.setMultStat(m157enumMapStatisticString_);
return m157abilityData_;
}
static AbilityData m154(){
AbilityData m158abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> m158enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m158enumMapStatisticString_.addEntry(Statistic.SPEED,R_2);
m158abilityData_.setMultStat(m158enumMapStatisticString_);
return m158abilityData_;
}
static AbilityData m155(){
AbilityData m159abilityData_ = Instances.newAbilityData();
m159abilityData_.setMultPower(R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_FEU+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_FEU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
StringMap<StringList> m159stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m159stringList_=new StringList(new CollCapacity(1));
m159stringList_.add(I_FEU);
m159stringMapStringList_.addEntry(ES,m159stringList_);
m159stringList_=new StringList(new CollCapacity(1));
m159stringList_.add(I_FEU);
m159stringMapStringList_.addEntry(I_GRELE,m159stringList_);
m159stringList_=new StringList(new CollCapacity(1));
m159stringList_.add(I_FEU);
m159stringMapStringList_.addEntry(I_ZENITH,m159stringList_);
m159stringList_=new StringList(new CollCapacity(1));
m159stringList_.add(I_FEU);
m159stringMapStringList_.addEntry(I_DANSE_PLUIE,m159stringList_);
m159stringList_=new StringList(new CollCapacity(1));
m159stringList_.add(I_FEU);
m159stringMapStringList_.addEntry(I_ORAGE,m159stringList_);
m159stringList_=new StringList(new CollCapacity(1));
m159stringList_.add(I_FEU);
m159stringMapStringList_.addEntry(I_TEMPETESABLE,m159stringList_);
m159abilityData_.setImmuMoveTypesByWeather(m159stringMapStringList_);
return m159abilityData_;
}
static AbilityData m156(){
AbilityData m160abilityData_ = Instances.newAbilityData();
m160abilityData_.setMultPower(A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_EAU+RB+RP+RP+OM+LP+R_3_2+OM+A_CARACGAUCHEFERME+LP+V_LANCEUR_PV_RESTANTS+OC+R_1_3+OM+V_LANCEUR_PV_MAX+RP+OP+A_CARACDROITEOUVERT+LP+V_LANCEUR_PV_RESTANTS+OC+R_1_3+OM+V_LANCEUR_PV_MAX+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_EAU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
return m160abilityData_;
}
static AbilityData m157(){
AbilityData m161abilityData_ = Instances.newAbilityData();
MonteCarloString m161monteCarloString_=new MonteCarloString(new CollCapacity(2));
m161monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_7));
m161monteCarloString_.addQuickEvent(I_SIMPLE_POISON,LgInt.newLgInt(R_3));
m161abilityData_.setSingleStatus(m161monteCarloString_);
StringMap<String> m161stringMapString_=new StringMap<String>(new CollCapacity(1));
m161stringMapString_.addEntry(I_SIMPLE_POISON,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_SIMPLE_POISON+OS+I_POISON_GRAVE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_TYPES+RB+OC+LB+I_POISON+OS+I_ACIER+RB+RP+RP+GT+R_0);
m161abilityData_.setFailStatus(m161stringMapString_);
return m161abilityData_;
}
static AbilityData m158(){
AbilityData m162abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> m162enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
m162enumMapStatisticByte_.addEntry(Statistic.SPEED,(byte)1);
m162abilityData_.setBoostStatRankEndRound(m162enumMapStatisticByte_);
return m162abilityData_;
}
static AbilityData m159(){
AbilityData m163abilityData_ = Instances.newAbilityData();
StringList m163stringList_=new StringList(new CollCapacity(46));
m163stringList_.add(I_ABSORB_EAU);
m163stringList_.add(I_ABSORB_VOLT);
m163stringList_.add(I_ANTI_BRUIT);
m163stringList_.add(I_ARMUMAGMA);
m163stringList_.add(I_ARMURBASTON);
m163stringList_.add(I_ATTENTION);
m163stringList_.add(I_BENET);
m163stringList_.add(I_COEUR_DE_COQ);
m163stringList_.add(I_COQUE_ARMURE);
m163stringList_.add(I_CORPS_SAIN);
m163stringList_.add(I_DON_FLORAL);
m163stringList_.add(I_ECAILLE_SPECIALE);
m163stringList_.add(I_ECHAUFFEMENT);
m163stringList_.add(I_ECRAN_FUMEE);
m163stringList_.add(I_ECRAN_POUDRE);
m163stringList_.add(I_ESPRIT_VITAL);
m163stringList_.add(I_FERMETE);
m163stringList_.add(I_FEUILLE_GARDE);
m163stringList_.add(I_FILTRE);
m163stringList_.add(I_GARDE_MYSTIK);
m163stringList_.add(I_GLUE);
m163stringList_.add(I_HERBIVORE);
m163stringList_.add(I_HYPER_CUTTER);
m163stringList_.add(I_IGNIFU_VOILE);
m163stringList_.add(I_IGNIFUGE);
m163stringList_.add(I_INCONSCIENT);
m163stringList_.add(I_INSOMNIA);
m163stringList_.add(I_ISOGRAISSE);
m163stringList_.add(I_LAVABO);
m163stringList_.add(I_LEVITATION);
m163stringList_.add(I_MOITEUR);
m163stringList_.add(I_MOMIE);
m163stringList_.add(I_MOTORISE);
m163stringList_.add(I_PARATONNERRE);
m163stringList_.add(I_PEAU_SECHE);
m163stringList_.add(I_PIEDS_CONFUS);
m163stringList_.add(I_REGARD_VIF);
m163stringList_.add(I_RIDEAU_NEIGE);
m163stringList_.add(I_SIMPLE);
m163stringList_.add(I_SOLIDE_ROC);
m163stringList_.add(I_TEMPO_PERSO);
m163stringList_.add(I_TENSION);
m163stringList_.add(I_TORCHE);
m163stringList_.add(I_VACCIN);
m163stringList_.add(I_VENTOUSE);
m163stringList_.add(I_VOILE_SABLE);
m163abilityData_.setIgnAbility(m163stringList_);
m163abilityData_.setMumy(true);
return m163abilityData_;
}
static AbilityData m160(){
AbilityData m164abilityData_ = Instances.newAbilityData();
StringMap<StringList> m164stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList m164stringList_=new StringList(new CollCapacity(2));
m164stringList_.add(I_SIMPLE_POISON);
m164stringList_.add(I_POISON_GRAVE);
m164stringMapStringList_.addEntry(ES,m164stringList_);
m164stringList_=new StringList(new CollCapacity(2));
m164stringList_.add(I_SIMPLE_POISON);
m164stringList_.add(I_POISON_GRAVE);
m164stringMapStringList_.addEntry(I_GRELE,m164stringList_);
m164stringList_=new StringList(new CollCapacity(2));
m164stringList_.add(I_SIMPLE_POISON);
m164stringList_.add(I_POISON_GRAVE);
m164stringMapStringList_.addEntry(I_ZENITH,m164stringList_);
m164stringList_=new StringList(new CollCapacity(2));
m164stringList_.add(I_SIMPLE_POISON);
m164stringList_.add(I_POISON_GRAVE);
m164stringMapStringList_.addEntry(I_DANSE_PLUIE,m164stringList_);
m164stringList_=new StringList(new CollCapacity(2));
m164stringList_.add(I_SIMPLE_POISON);
m164stringList_.add(I_POISON_GRAVE);
m164stringMapStringList_.addEntry(I_ORAGE,m164stringList_);
m164stringList_=new StringList(new CollCapacity(2));
m164stringList_.add(I_SIMPLE_POISON);
m164stringList_.add(I_POISON_GRAVE);
m164stringMapStringList_.addEntry(I_TEMPETESABLE,m164stringList_);
m164abilityData_.setImmuStatus(m164stringMapStringList_);
return m164abilityData_;
}
static AbilityData m161(){
AbilityData m165abilityData_ = Instances.newAbilityData();
StringList m165stringList_=new StringList(new CollCapacity(2));
m165stringList_.add(I_CYCLONE);
m165stringList_.add(I_HURLEMENT);
m165abilityData_.setImmuMove(m165stringList_);
return m165abilityData_;
}
static AbilityData m162(){
AbilityData m166abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Rate> m166enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
m166enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate(R_11_10));
m166abilityData_.setMultStatAlly(m166enumMapStatisticRate_);
EnumMap<Statistic,String> m166enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m166enumMapStatisticString_.addEntry(Statistic.ACCURACY,R_11_10);
m166abilityData_.setMultStat(m166enumMapStatisticString_);
return m166abilityData_;
}
static AbilityData m163(){
AbilityData m167abilityData_ = Instances.newAbilityData();
StringList m167stringList_=new StringList(new CollCapacity(1));
m167stringList_.add(I_TEMPETESABLE);
m167abilityData_.setImmuWeather(m167stringList_);
EnumMap<Statistic,String> m167enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
m167enumMapStatisticString_.addEntry(Statistic.DEFENSE,A_MAX+LP+R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_TEMPETESABLE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_TEMPETESABLE+RB+OC+LB+V_CLIMATS+RB+RP+RP+OC+R_1+RP);
m167abilityData_.setMultStat(m167enumMapStatisticString_);
return m167abilityData_;
}
}
