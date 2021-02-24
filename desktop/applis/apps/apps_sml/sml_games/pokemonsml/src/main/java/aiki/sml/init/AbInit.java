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
public final class AbInit{
private AbInit(){}
public static StringMap<AbilityData> ab(){
 StringMap<AbilityData> a_ = new StringMap<AbilityData>(new CollCapacity(164));
a_.addEntry("ABRI_PROT",m0());
a_.addEntry("ABSORB_EAU",m1());
a_.addEntry("ABSORB_VOLT",m2());
a_.addEntry("ACHARNE",m3());
a_.addEntry("ADAPTABILITE",m4());
a_.addEntry("AGITATION",m5());
a_.addEntry("AILES_BOURRASQUE",m6());
a_.addEntry("AIR_LOCK",m7());
a_.addEntry("ALERTE_NEIGE",m8());
a_.addEntry("ANALYSTE",m9());
a_.addEntry("ANNULE_GARDE",m10());
a_.addEntry("ANTI_BRUIT",m11());
a_.addEntry("ARMUMAGMA",m12());
a_.addEntry("ARMURBASTON",m13());
a_.addEntry("ARMUROUILLEE",m14());
a_.addEntry("AROMA_VOILE",m15());
a_.addEntry("ATTENTION",m16());
a_.addEntry("AURA_FEERIQUE",m17());
a_.addEntry("AURA_INVERSEE",m18());
a_.addEntry("AURA_TENEBREUSE",m19());
a_.addEntry("BAIGNE_SABLE",m20());
a_.addEntry("BAJOUES",m21());
a_.addEntry("BENET",m22());
a_.addEntry("BOOM_FINAL",m23());
a_.addEntry("BRASIER",m24());
a_.addEntry("BRISE_MOULE",m25());
a_.addEntry("CALQUE",m26());
a_.addEntry("CHANCEUX",m27());
a_.addEntry("CHLOROPHYLLE",m28());
a_.addEntry("CIEL_GRIS",m29());
a_.addEntry("COEUR_DE_COQ",m30());
a_.addEntry("COEUR_NOBLE",m31());
a_.addEntry("COEUR_SOIN",m32());
a_.addEntry("COLERIQUE",m33());
a_.addEntry("COLOFORCE",m34());
a_.addEntry("CONTESTATION",m35());
a_.addEntry("CONTRASTE",m36());
a_.addEntry("COQUE_ARMURE",m37());
a_.addEntry("CORPS_ARDENT",m38());
a_.addEntry("CORPS_GEL",m39());
a_.addEntry("CORPS_SAIN",m40());
a_.addEntry("CRACHIN",m41());
a_.addEntry("CRAN",m42());
a_.addEntry("CUVETTE",m43());
a_.addEntry("DECLIC_TACTIQUE",m44());
a_.addEntry("DEGUISEMENT",m45());
a_.addEntry("DELESTAGE",m46());
a_.addEntry("DON_FLORAL",m47());
a_.addEntry("ECAILLE_SPECIALE",m48());
a_.addEntry("ECHAUFFEMENT",m49());
a_.addEntry("ECRAN_FUMEE",m50());
a_.addEntry("ECRAN_POUDRE",m51());
a_.addEntry("ENGRAIS",m52());
a_.addEntry("ENVELOCAPE",m53());
a_.addEntry("EPINE_DE_FER",m54());
a_.addEntry("ESPRIT_VITAL",m55());
a_.addEntry("ESSAIM",m56());
a_.addEntry("FARCEUR",m57());
a_.addEntry("FERMETE",m58());
a_.addEntry("FEUILLE_GARDE",m59());
a_.addEntry("FILTRE",m60());
a_.addEntry("FLORA_VOILE",m61());
a_.addEntry("FORCE_PURE",m62());
a_.addEntry("FORCE_SABLE",m63());
a_.addEntry("FORCE_SOLEIL",m64());
a_.addEntry("FREIN",m65());
a_.addEntry("GARDE_AMIE",m66());
a_.addEntry("GARDE_MAGIK",m67());
a_.addEntry("GARDE_MYSTIK",m68());
a_.addEntry("GLISSADE",m69());
a_.addEntry("GLOUTONNERIE",m70());
a_.addEntry("GLUCO_VOILE",m71());
a_.addEntry("GLUE",m72());
a_.addEntry("GRIFFE_DURE",m73());
a_.addEntry("HERBIVORE",m74());
a_.addEntry("HYDRATATION",m75());
a_.addEntry("HYPER_CUTTER",m76());
a_.addEntry("IGNIFUGE",m77());
a_.addEntry("IGNIFU_VOILE",m78());
a_.addEntry("IMPASSIBLE",m79());
a_.addEntry("IMPUDENCE",m80());
a_.addEntry("INCONSCIENT",m81());
a_.addEntry("INFILTRATION",m82());
a_.addEntry("INSOMNIA",m83());
a_.addEntry("INTIMIDANT",m84());
a_.addEntry("ISOGRAISSE",m85());
a_.addEntry("JOLI_SOURIRE",m86());
a_.addEntry("LAVABO",m87());
a_.addEntry("LENTITEINTEE",m88());
a_.addEntry("LEVITATION",m89());
a_.addEntry("MAGICIEN",m90());
a_.addEntry("MAGNEPIEGE",m91());
a_.addEntry("MARQUE_OMBRE",m92());
a_.addEntry("MATINAL",m93());
a_.addEntry("MAUVAIS_REVE",m94());
a_.addEntry("MEDIC_NATURE",m95());
a_.addEntry("MEGA_BLASTER",m96());
a_.addEntry("METEO",m97());
a_.addEntry("MINUS",m98());
a_.addEntry("MOITEUR",m99());
a_.addEntry("MOMIE",m100());
a_.addEntry("MOTORISE",m101());
a_.addEntry("MUE",m102());
a_.addEntry("MULTITYPE",m103());
a_.addEntry("MULTI_COUPS",m104());
a_.addEntry("NORMALISE",m105());
a_.addEntry("OEIL_COMPOSE",m106());
a_.addEntry("PARATONNERRE",m107());
a_.addEntry("PARE_BALLES",m108());
a_.addEntry("PEAU_DURE",m109());
a_.addEntry("PEAU_FEERIQUE",m110());
a_.addEntry("PEAU_GELEE",m111());
a_.addEntry("PEAU_MIRACLE",m112());
a_.addEntry("PEAU_SECHE",m113());
a_.addEntry("PHOBIQUE",m114());
a_.addEntry("PIEDS_CONFUS",m115());
a_.addEntry("PIED_VELOCE",m116());
a_.addEntry("PIEGE",m117());
a_.addEntry("PLUS",m118());
a_.addEntry("POING_DE_FER",m119());
a_.addEntry("POINT_POISON",m120());
a_.addEntry("POISSEUX",m121());
a_.addEntry("POSE_SPORE",m122());
a_.addEntry("PRESSION",m123());
a_.addEntry("PROGNATHE",m124());
a_.addEntry("PROTEEN",m125());
a_.addEntry("PUANTEUR",m126());
a_.addEntry("QUERELLEUR",m127());
a_.addEntry("RECOLTE",m128());
a_.addEntry("REGARD_VIF",m129());
a_.addEntry("REGE_FORCE",m130());
a_.addEntry("RIDEAU_NEIGE",m131());
a_.addEntry("RIVALITE",m132());
a_.addEntry("SABLE_VOLANT",m133());
a_.addEntry("SANS_LIMITE",m134());
a_.addEntry("SECHERESSE",m135());
a_.addEntry("SERENITE",m136());
a_.addEntry("SIMPLE",m137());
a_.addEntry("SNIPER",m138());
a_.addEntry("SOIN_POISON",m139());
a_.addEntry("SOLIDE_ROC",m140());
a_.addEntry("STATIK",m141());
a_.addEntry("SUINTEMENT",m142());
a_.addEntry("SYMBIOSE",m143());
a_.addEntry("SYNCHRO",m144());
a_.addEntry("TECHNICIEN",m145());
a_.addEntry("TELECHARGE",m146());
a_.addEntry("TELEPATHE",m147());
a_.addEntry("TEMERAIRE",m148());
a_.addEntry("TEMPO_PERSO",m149());
a_.addEntry("TENSION",m150());
a_.addEntry("TERA_VOLTAGE",m151());
a_.addEntry("TETE_DE_ROC",m152());
a_.addEntry("TOISON_EPAISSE",m153());
a_.addEntry("TOISON_HERBUE",m154());
a_.addEntry("TORCHE",m155());
a_.addEntry("TORRENT",m156());
a_.addEntry("TOXITOUCHE",m157());
a_.addEntry("TURBO",m158());
a_.addEntry("TURBOBRASIER",m159());
a_.addEntry("VACCIN",m160());
a_.addEntry("VENTOUSE",m161());
a_.addEntry("VICTORIEUX",m162());
a_.addEntry("VOILE_SABLE",m163());
return a_;
}
static AbilityData m0(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("ORAGE",Rate.newRate("1/16"));
abilityData_.setHealHpByWeather(stringMapRate_);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ORAGE");
abilityData_.setImmuWeather(stringList_);
WeatherTypes objectMapWeatherTypeRate_=new WeatherTypes(new CollCapacity(1));
objectMapWeatherTypeRate_.addEntry(new WeatherType("ORAGE","ELECTRIQUE"),Rate.newRate("1/4"));
abilityData_.setHealHpByTypeIfWeather(objectMapWeatherTypeRate_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("ORAGE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m1(){
AbilityData abilityData_ = Instances.newAbilityData();
WeatherTypes objectMapWeatherTypeRate_=new WeatherTypes(new CollCapacity(6));
objectMapWeatherTypeRate_.addEntry(new WeatherType("","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("ORAGE","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("TEMPETESABLE","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("ZENITH","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("GRELE","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("DANSE_PLUIE","EAU"),Rate.newRate("1/4"));
abilityData_.setHealHpByTypeIfWeather(objectMapWeatherTypeRate_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m2(){
AbilityData abilityData_ = Instances.newAbilityData();
WeatherTypes objectMapWeatherTypeRate_=new WeatherTypes(new CollCapacity(6));
objectMapWeatherTypeRate_.addEntry(new WeatherType("DANSE_PLUIE","ELECTRIQUE"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("ORAGE","ELECTRIQUE"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("TEMPETESABLE","ELECTRIQUE"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("ZENITH","ELECTRIQUE"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("","ELECTRIQUE"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("GRELE","ELECTRIQUE"),Rate.newRate("1/4"));
abilityData_.setHealHpByTypeIfWeather(objectMapWeatherTypeRate_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m3(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)2);
abilityData_.setMultStatIfLowStat(enumMapStatisticByte_);
return abilityData_;
}
static AbilityData m4(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultStab(Rate.newRate("4/3"));
return abilityData_;
}
static AbilityData m5(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("3/2*cardinal(inter({VAR__ATTAQUE_CATEGORIE},{PHYSIQUE}))+cardinal(complementaire({PHYSIQUE},{VAR__ATTAQUE_CATEGORIE}))");
return abilityData_;
}
static AbilityData m6(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(1));
stringMapShort_.addEntry("VOL",(short)1);
abilityData_.setIncreasedPrioTypes(stringMapShort_);
return abilityData_;
}
static AbilityData m7(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setDisableWeather(true);
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m8(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setEnabledWeather("GRELE");
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m9(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"13/10*VAR__FIGHTER_DER_JOUE+(1-VAR__FIGHTER_DER_JOUE)");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m10(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumList<Statistic> enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
enumListStatistic_.add(Statistic.ACCURACY);
abilityData_.setImmuLowStat(enumListStatistic_);
abilityData_.setBreakProtection(true);
abilityData_.setAchievedDisappearedPk(true);
return abilityData_;
}
static AbilityData m11(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(20));
stringList_.add("ABOIEMENT");
stringList_.add("BABIL");
stringList_.add("BERCEUSE");
stringList_.add("BOURDON");
stringList_.add("BROUHAHA");
stringList_.add("CHANT_ANTIQUE");
stringList_.add("CHANT_CANON");
stringList_.add("ECHO");
stringList_.add("GLAS_DE_SOIN");
stringList_.add("GRINCEMENT");
stringList_.add("HURLE_TEMPS");
stringList_.add("HURLEMENT");
stringList_.add("MEGAPHONE");
stringList_.add("REQUIEM");
stringList_.add("RONFLEMENT");
stringList_.add("RUGISSEMENT");
stringList_.add("SIFFL_HERBE");
stringList_.add("SONICBOOM");
stringList_.add("STRIDO_SON");
stringList_.add("ULTRASON");
abilityData_.setImmuMove(stringList_);
return abilityData_;
}
static AbilityData m12(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setDecreaseNecStepsHatch(1);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GEL");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("GEL");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("GEL");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("GEL");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("GEL");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("GEL");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m13(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setImmuCh(true);
return abilityData_;
}
static AbilityData m14(){
AbilityData abilityData_ = Instances.newAbilityData();
StatisticCategoryByte objectMapStatisticCategoryByte_=new StatisticCategoryByte(new CollCapacity(1));
objectMapStatisticCategoryByte_.addEntry(new StatisticCategory(Statistic.SPEED,"PHYSIQUE"),(byte)1);
abilityData_.setMultStatIfDamageCat(objectMapStatisticCategoryByte_);
return abilityData_;
}
static AbilityData m15(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(6));
stringList_.add("PROVOC");
stringList_.add("ENCORE");
stringList_.add("ENTRAVE");
stringList_.add("TOURMENTE");
stringList_.add("ANTI_SOIN");
stringList_.add("ATTRACTION");
abilityData_.setImmuAllyFromMoves(stringList_);
return abilityData_;
}
static AbilityData m16(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PEUR");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PEUR");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PEUR");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PEUR");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PEUR");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PEUR");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m17(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("FEE",Rate.newRate("3/2"));
abilityData_.setMultPowerMovesTypesGlobal(stringMapRate_);
return abilityData_;
}
static AbilityData m18(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setReverseEffectsPowerMovesTypesGlobal(true);
return abilityData_;
}
static AbilityData m19(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("TENEBRE",Rate.newRate("3/2"));
abilityData_.setMultPowerMovesTypesGlobal(stringMapRate_);
return abilityData_;
}
static AbilityData m20(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("TEMPETESABLE");
abilityData_.setImmuWeather(stringList_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"max(2*cardinal(inter({VAR__CLIMATS},{TEMPETESABLE}))+cardinal(complementaire({TEMPETESABLE},{VAR__CLIMATS})),1)");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m21(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setHealHpWhileUsingBerry(Rate.newRate("1/3"));
return abilityData_;
}
static AbilityData m22(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("ATTRACTION");
stringList_.add("SEDUCTION");
abilityData_.setImmuMove(stringList_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
stringList_=new StringList(new CollCapacity(1));
stringList_.add("AMOUR");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("AMOUR");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("AMOUR");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("AMOUR");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("AMOUR");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("AMOUR");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m23(){
AbilityData abilityData_ = Instances.newAbilityData();
return abilityData_;
}
static AbilityData m24(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("cardinal(inter({VAR__ATTAQUE_TYPES},{FEU}))*(3/2*caracgaucheferme(VAR__LANCEUR_PV_RESTANTS,1/3*VAR__LANCEUR_PV_MAX)+caracdroiteouvert(VAR__LANCEUR_PV_RESTANTS,1/3*VAR__LANCEUR_PV_MAX))+cardinal(complementaire({FEU},{VAR__ATTAQUE_TYPES}))");
return abilityData_;
}
static AbilityData m25(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(46));
stringList_.add("ABSORB_EAU");
stringList_.add("ABSORB_VOLT");
stringList_.add("ANTI_BRUIT");
stringList_.add("ARMUMAGMA");
stringList_.add("ARMURBASTON");
stringList_.add("ATTENTION");
stringList_.add("BENET");
stringList_.add("COEUR_DE_COQ");
stringList_.add("COQUE_ARMURE");
stringList_.add("CORPS_SAIN");
stringList_.add("DON_FLORAL");
stringList_.add("ECAILLE_SPECIALE");
stringList_.add("ECHAUFFEMENT");
stringList_.add("ECRAN_FUMEE");
stringList_.add("ECRAN_POUDRE");
stringList_.add("ESPRIT_VITAL");
stringList_.add("FERMETE");
stringList_.add("FEUILLE_GARDE");
stringList_.add("FILTRE");
stringList_.add("GARDE_MYSTIK");
stringList_.add("GLUE");
stringList_.add("HERBIVORE");
stringList_.add("HYPER_CUTTER");
stringList_.add("IGNIFU_VOILE");
stringList_.add("IGNIFUGE");
stringList_.add("INCONSCIENT");
stringList_.add("INSOMNIA");
stringList_.add("ISOGRAISSE");
stringList_.add("LAVABO");
stringList_.add("LEVITATION");
stringList_.add("MOITEUR");
stringList_.add("MOMIE");
stringList_.add("MOTORISE");
stringList_.add("PARATONNERRE");
stringList_.add("PEAU_SECHE");
stringList_.add("PIEDS_CONFUS");
stringList_.add("REGARD_VIF");
stringList_.add("RIDEAU_NEIGE");
stringList_.add("SIMPLE");
stringList_.add("SOLIDE_ROC");
stringList_.add("TEMPO_PERSO");
stringList_.add("TENSION");
stringList_.add("TORCHE");
stringList_.add("VACCIN");
stringList_.add("VENTOUSE");
stringList_.add("VOILE_SABLE");
abilityData_.setIgnAbility(stringList_);
abilityData_.setMumy(true);
return abilityData_;
}
static AbilityData m26(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setCopyingAbility(true);
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m27(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultEvtRateCh(Rate.newRate("2"));
return abilityData_;
}
static AbilityData m28(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"max(2*cardinal(inter({VAR__CLIMATS},{ZENITH}))+cardinal(complementaire({ZENITH},{VAR__CLIMATS})),1)");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m29(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setDisableWeather(true);
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m30(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumList<Statistic> enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
enumListStatistic_.add(Statistic.DEFENSE);
abilityData_.setImmuLowStat(enumListStatistic_);
return abilityData_;
}
static AbilityData m31(){
AbilityData abilityData_ = Instances.newAbilityData();
StatisticTypeByte objectMapStatisticTypeByte_=new StatisticTypeByte(new CollCapacity(1));
objectMapStatisticTypeByte_.addEntry(new StatisticType(Statistic.ATTACK,"TENEBRE"),(byte)1);
abilityData_.setMultStatIfDamgeType(objectMapStatisticTypeByte_);
return abilityData_;
}
static AbilityData m32(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundTeam effectEndRoundTeam_=Instances.newEffectEndRoundTeam();
effectEndRoundTeam_.setDeleteAllStatus(Rate.newRate("3/10"));
effectEndRoundTeam_.setDeleteAllStatusAlly(Rate.newRate("3/10"));
effectEndRoundTeam_.setFailEndRound("");
effectEndRoundTeam_.setEndRoundRank(25);
effectEndRoundTeam_.setTargetChoice(TargetChoice.NOTHING);
effectEndRoundTeam_.setFail("");
custListEffectEndRound_.add(effectEndRoundTeam_);
abilityData_.setEffectEndRound(custListEffectEndRound_);
return abilityData_;
}
static AbilityData m33(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumList<Statistic> enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
enumListStatistic_.add(Statistic.ATTACK);
abilityData_.setMaxStatisticsIfCh(enumListStatistic_);
return abilityData_;
}
static AbilityData m34(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m35(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultVarBoost(Rate.newRate("1"));
return abilityData_;
}
static AbilityData m36(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setEnabledWeather("ORAGE");
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m37(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setImmuCh(true);
return abilityData_;
}
static AbilityData m38(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setDecreaseNecStepsHatch(1);
MonteCarloString monteCarloString_=new MonteCarloString(new CollCapacity(2));
monteCarloString_.addQuickEvent("",LgInt.newLgInt("7"));
monteCarloString_.addQuickEvent("BRULURE",LgInt.newLgInt("3"));
abilityData_.setSingleStatus(monteCarloString_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(1));
stringMapString_.addEntry("BRULURE","cardinal(inter({VAR__CIBLE_STATUTS},{BRULURE}))>0|VAR__CIBLE_CLONE>0|cardinal(inter({VAR__CIBLE_TYPES},{FEU}))>0");
abilityData_.setFailStatus(stringMapString_);
return abilityData_;
}
static AbilityData m39(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("GRELE",Rate.newRate("1/16"));
abilityData_.setHealHpByWeather(stringMapRate_);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GRELE");
abilityData_.setImmuWeather(stringList_);
return abilityData_;
}
static AbilityData m40(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumList<Statistic> enumListStatistic_=new EnumList<Statistic>(new CollCapacity(5));
enumListStatistic_.add(Statistic.ATTACK);
enumListStatistic_.add(Statistic.DEFENSE);
enumListStatistic_.add(Statistic.SPECIAL_ATTACK);
enumListStatistic_.add(Statistic.SPECIAL_DEFENSE);
enumListStatistic_.add(Statistic.SPEED);
abilityData_.setImmuLowStat(enumListStatistic_);
return abilityData_;
}
static AbilityData m41(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setEnabledWeather("DANSE_PLUIE");
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m42(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setImmuLowStatIfStatus(new CustList<StatisticStatus>(new StatisticStatus(Statistic.ATTACK,"BRULURE")));
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"3/2*cardinal(inter({VAR__FIGHTER_STATUTS},{PARALYSIE;SIMPLE_POISON;SOMMEIL;GEL;SOMMEIL_REPOS;POISON_GRAVE;BRULURE}))+cardinal(complementaire({PARALYSIE;SIMPLE_POISON;SOMMEIL;GEL;SOMMEIL_REPOS;POISON_GRAVE;BRULURE},{VAR__FIGHTER_STATUTS}))");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m43(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("DANSE_PLUIE",Rate.newRate("1/16"));
abilityData_.setHealHpByWeather(stringMapRate_);
return abilityData_;
}
static AbilityData m44(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_NOM},{POING_ECLAIR;MARTO_POING;POING_OMBRE;PISTO_POING;DYNAMOPOING;ULTIMAPOING;POING_DE_FEU;POING_METEOR;POINGLACE;POING_COMETE;VAMPIPOING;UPPERCUT;MITRA_POING;STRATOPERCUT}))+cardinal(complementaire({POING_ECLAIR;MARTO_POING;POING_OMBRE;PISTO_POING;DYNAMOPOING;ULTIMAPOING;POING_DE_FEU;POING_METEOR;POINGLACE;POING_COMETE;VAMPIPOING;UPPERCUT;MITRA_POING;STRATOPERCUT},{VAR__ATTAQUE_NOM}))");
return abilityData_;
}
static AbilityData m45(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setChgtTypeByDamage(true);
return abilityData_;
}
static AbilityData m46(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"2*cardinal({VAR__FIGHTER_OBJET})+(1-cardinal({VAR__FIGHTER_OBJET}))");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m47(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(4));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"max(2*cardinal(inter({VAR__CLIMATS},{ZENITH}))+cardinal(complementaire({ZENITH},{VAR__CLIMATS})),1)");
enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,"max(2*cardinal(inter({VAR__CLIMATS},{ZENITH}))+cardinal(complementaire({ZENITH},{VAR__CLIMATS})),1)");
enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,"max(2*cardinal(inter({VAR__CLIMATS},{ZENITH}))+cardinal(complementaire({ZENITH},{VAR__CLIMATS})),1)");
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"max(2*cardinal(inter({VAR__CLIMATS},{ZENITH}))+cardinal(complementaire({ZENITH},{VAR__CLIMATS})),1)");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m48(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"3/2*cardinal(inter({VAR__FIGHTER_STATUTS},{SOMMEIL_REPOS;GEL;POISON_GRAVE;PARALYSIE;BRULURE;SOMMEIL;SIMPLE_POISON}))+cardinal(complementaire({SOMMEIL_REPOS;GEL;POISON_GRAVE;PARALYSIE;BRULURE;SOMMEIL;SIMPLE_POISON},{VAR__FIGHTER_STATUTS}))");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m49(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m50(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumList<Statistic> enumListStatistic_=new EnumList<Statistic>(new CollCapacity(5));
enumListStatistic_.add(Statistic.ATTACK);
enumListStatistic_.add(Statistic.DEFENSE);
enumListStatistic_.add(Statistic.SPECIAL_ATTACK);
enumListStatistic_.add(Statistic.SPECIAL_DEFENSE);
enumListStatistic_.add(Statistic.SPEED);
abilityData_.setImmuLowStat(enumListStatistic_);
return abilityData_;
}
static AbilityData m51(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setCancelSecEffectOther(true);
return abilityData_;
}
static AbilityData m52(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("cardinal(inter({VAR__ATTAQUE_TYPES},{PLANTE}))*(3/2*caracgaucheferme(VAR__LANCEUR_PV_RESTANTS,1/3*VAR__LANCEUR_PV_MAX)+caracdroiteouvert(VAR__LANCEUR_PV_RESTANTS,1/3*VAR__LANCEUR_PV_MAX))+cardinal(complementaire({PLANTE},{VAR__ATTAQUE_TYPES}))");
return abilityData_;
}
static AbilityData m53(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("GRELE");
stringList_.add("TEMPETESABLE");
abilityData_.setImmuWeather(stringList_);
return abilityData_;
}
static AbilityData m54(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setRecoilDamageFoe(Rate.newRate("1/8"));
return abilityData_;
}
static AbilityData m55(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m56(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("cardinal(inter({VAR__ATTAQUE_TYPES},{INSECTE}))*(3/2*caracgaucheferme(VAR__LANCEUR_PV_RESTANTS,1/3*VAR__LANCEUR_PV_MAX)+caracdroiteouvert(VAR__LANCEUR_PV_RESTANTS,1/3*VAR__LANCEUR_PV_MAX))+cardinal(complementaire({INSECTE},{VAR__ATTAQUE_TYPES}))");
return abilityData_;
}
static AbilityData m57(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(1));
stringMapShort_.addEntry("AUTRE",(short)1);
abilityData_.setIncreasedPrio(stringMapShort_);
return abilityData_;
}
static AbilityData m58(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(4));
stringList_.add("ABIME");
stringList_.add("EMPAL_KORNE");
stringList_.add("GUILLOTINE");
stringList_.add("GLACIATION");
abilityData_.setImmuMove(stringList_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(2));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,"3/2");
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"3/2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m59(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
StringList stringList_=new StringList(new CollCapacity(7));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringList_.add("PARALYSIE");
stringList_.add("GEL");
stringList_.add("BRULURE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("ZENITH",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m60(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultSufferedDamageSuperEff(Rate.newRate("3/4"));
return abilityData_;
}
static AbilityData m61(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
StringList stringList_=new StringList(new CollCapacity(11));
stringList_.add("POISON_GRAVE");
stringList_.add("BRULURE");
stringList_.add("VAMPIGRAINE_ST");
stringList_.add("SIMPLE_POISON");
stringList_.add("GEL");
stringList_.add("AMOUR");
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringList_.add("MAUDIT");
stringList_.add("CAUCHEMAR_ST");
stringList_.add("PARALYSIE");
stringMapStringList_.addEntry("PLANTE",stringList_);
abilityData_.setImmuStatusTypes(stringMapStringList_);
StringMap<EnumList<Statistic>> stringMapEnumListStatistic_=new StringMap<EnumList<Statistic>>(new CollCapacity(1));
EnumList<Statistic> enumListStatistic_=new EnumList<Statistic>(new CollCapacity(8));
enumListStatistic_.add(Statistic.ATTACK);
enumListStatistic_.add(Statistic.DEFENSE);
enumListStatistic_.add(Statistic.SPECIAL_ATTACK);
enumListStatistic_.add(Statistic.SPECIAL_DEFENSE);
enumListStatistic_.add(Statistic.SPEED);
enumListStatistic_.add(Statistic.ACCURACY);
enumListStatistic_.add(Statistic.EVASINESS);
enumListStatistic_.add(Statistic.CRITICAL_HIT);
stringMapEnumListStatistic_.addEntry("PLANTE",enumListStatistic_);
abilityData_.setImmuLowStatisTypes(stringMapEnumListStatistic_);
return abilityData_;
}
static AbilityData m62(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m63(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("TEMPETESABLE");
abilityData_.setImmuWeather(stringList_);
abilityData_.setMultPower("cardinal(inter({VAR__CLIMATS},{TEMPETESABLE}))*(13/10*cardinal(inter({VAR__ATTAQUE_TYPES},{ROCHE;ACIER;SOL}))+cardinal(complementaire({ROCHE;ACIER;SOL},{VAR__ATTAQUE_TYPES})))+cardinal(complementaire({TEMPETESABLE},{VAR__CLIMATS}))");
return abilityData_;
}
static AbilityData m64(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("ZENITH",Rate.newRate("1/8"));
abilityData_.setHealHpByWeather(stringMapRate_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,"max(3/2*cardinal(inter({VAR__CLIMATS},{ZENITH}))+cardinal(complementaire({ZENITH},{VAR__CLIMATS})),1)");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m65(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setSlowing(true);
return abilityData_;
}
static AbilityData m66(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultAllyDamage(Rate.newRate("3/4"));
return abilityData_;
}
static AbilityData m67(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(5));
stringList_.add("PICOTS");
stringList_.add("PIEGE_DE_ROC");
stringList_.add("CAUCHEMAR");
stringList_.add("MALEDICTION_2");
stringList_.add("VAMPIGRAINE");
abilityData_.setImmuMove(stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("GRELE");
stringList_.add("TEMPETESABLE");
abilityData_.setImmuWeather(stringList_);
abilityData_.setImmuDamageTrappingMoves(true);
abilityData_.setImmuDamageRecoil(true);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("MAUVAIS_REVE");
abilityData_.setImmuAbility(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
abilityData_.setImmuStatusBeginRound(stringList_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
stringList_=new StringList(new CollCapacity(3));
stringList_.add("BRULURE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(3));
stringList_.add("BRULURE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(3));
stringList_.add("BRULURE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(3));
stringList_.add("BRULURE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(3));
stringList_.add("BRULURE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(3));
stringList_.add("BRULURE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m68(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(5));
stringList_.add("IMPLORE");
stringList_.add("LARCIN");
stringList_.add("TOURMAGIK");
stringList_.add("PASSE_PASSE");
stringList_.add("SABOTAGE");
abilityData_.setImmuMove(stringList_);
abilityData_.setImmuSufferedDamageLowEff(true);
return abilityData_;
}
static AbilityData m69(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"max(2*cardinal(inter({VAR__CLIMATS},{DANSE_PLUIE}))+cardinal(complementaire({DANSE_PLUIE},{VAR__CLIMATS})),1)");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m70(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMaxHpForUsingBerry(Rate.newRate("1/2"));
return abilityData_;
}
static AbilityData m71(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOMMEIL");
stringMapStringList_.addEntry("FEE",stringList_);
abilityData_.setImmuStatusTypes(stringMapStringList_);
return abilityData_;
}
static AbilityData m72(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(5));
stringList_.add("IMPLORE");
stringList_.add("LARCIN");
stringList_.add("TOURMAGIK");
stringList_.add("PASSE_PASSE");
stringList_.add("SABOTAGE");
abilityData_.setImmuMove(stringList_);
return abilityData_;
}
static AbilityData m73(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("3/10*cardinal(inter({VAR__ATTAQUE_NOM},{PINCE_MASSE;PRESSE;PSYKOUD_BOUL;CROC_FATAL;POURSUITE;LANCECROU;MEGAFOUET;COUD_KRANE;MACH_PUNCH;TOUR_RAPIDE;PISTO_POING;ECLATEGRIFFE;BASTON;ECLAIR_FOU;MEGACORNE;AVALANCHE;POING_ECLAIR;FORCE;TRIPLE_PIED;MORSURE;STRATOPERCUT;ETRANGLEMENT;PLANNEUR;FLEAU;FRAPPE_ATLAS;TRANCHE;TACLE_LOURD;BALAYAGE;POING_COMETE;COUP_VICTOIRE;TUNNELIER;PICPIC;PEIGNEE;BOUTEFEU;DANSE_FLEUR;RETOUR;FRACASS_TETE;BALAYETTE;CLOSE_COMBAT;STIMULANT;PLAIE_CROIX;COUPE;AQUA_JET;DOUBLE_BAFFE;MAWASHI_GERI;COUP_DOUBLE;GYROBALLE;GRIFFE;FRENESIE;ROUE_DE_FEU;FURIE;CRU_AILE;VENGEANCE;ASSURANCE;TRICHERIE;TACLE_FEU;VIT_EXTREME;ELECTACLE;MARTO_POING;CONTRE;REVENANT;CROCS_GIVRE;ATOUT;NITROCHARGE;CONSTRICTION;BLUFF;LIGOTAGE;ECRASEMENT;ETINCELLE;BULLDOBOULE;LUTTE;PLONGEE;FOUET_LIANES;LARCIN;ULTIMAPOING;BALL_GLACE;LAME_FEUILLE;DYNAMOPOING;ESCALADE;DAMOCLES;MANIA;QUEUE_DE_FER;ATTRITION;CORPS_PERDU;DOUBLE_PIED;POING_DARD;TRANCHE_NUIT;REPRESAILLES;FORTE_PAUME;DEMI_TOUR;ECRAS_FACE;VAMPIPOING;AIKIDO;HYDROQUEUE;PICORE;ROULADE;POING_DE_FEU;QUEUE_POISON;GRIFFE_ACIER;LAME_SAINTE;PIQURE;CROCS_FEU;PIED_VOLTIGE;BEC_VRILLE;POING_KARATE;COLERE;FACADE;VIVE_ATTAQUE;CASSE_BRIQUE;OMBRE_PORTEE;ECLATE_ROC;DRACO_QUEUE;NOEUD_HERBE;TELEPORT;ETREINTE;DERNIERECOUR;CREVECOEUR;BELIER;REVEIL_FORCE;COMBO_GRIFFE;TETE_DE_FER;SABOTAGE;PIED_BRULEUR;CLAQUOIR;COGNE;IMPLORE;CROCHETVENIN;VAMPIRISME;LECHOUILLE;CHUTE_LIBRE;RAPACE;PIED_SAUTE;COUP_CROIX;CROCS_ECLAIR;KOUD_KORNE;ESSORAGE;TUNNEL;DRACOCHARGE;FAUX_CHAGE;CHARGE;ETONNEMENT;ENVOL;MARTOBOIS;PROJECTION;COQUILAME;CHARGE_FOUDRE;GIGA_IMPACT;GRIFFE_OMBRE;SURPUISSANCE;EXCUSE;CASCADE;PUNITION;DIRECT_TOXIK;SACRIFICE;VENDETTA;POING_METEOR;ACROBATIE;YAMA_ARASHI;FORCE_POIGNE;PATIENCE;COUP_BAS;POINGLACE;TORGNOLES;PLUMO_QUEUE;FEINTE;RIPOSTE;PLAQUAGE;AILE_D_ACIER;DRACOGRIFFE;POISON_CROIX;ENCORNEBOIS;COUP_D_BOULE;MACHOUILLE;CROC_DE_MORT;TAILLADE;ULTIMAWASHI;POING_OMBRE;AEROPIQUE;REBOND;MITRA_POING;UPPERCUT;SOUPLESSE}))+cardinal(complementaire({PINCE_MASSE;PRESSE;PSYKOUD_BOUL;CROC_FATAL;POURSUITE;LANCECROU;MEGAFOUET;COUD_KRANE;MACH_PUNCH;TOUR_RAPIDE;PISTO_POING;ECLATEGRIFFE;BASTON;ECLAIR_FOU;MEGACORNE;AVALANCHE;POING_ECLAIR;FORCE;TRIPLE_PIED;MORSURE;STRATOPERCUT;ETRANGLEMENT;PLANNEUR;FLEAU;FRAPPE_ATLAS;TRANCHE;TACLE_LOURD;BALAYAGE;POING_COMETE;COUP_VICTOIRE;TUNNELIER;PICPIC;PEIGNEE;BOUTEFEU;DANSE_FLEUR;RETOUR;FRACASS_TETE;BALAYETTE;CLOSE_COMBAT;STIMULANT;PLAIE_CROIX;COUPE;AQUA_JET;DOUBLE_BAFFE;MAWASHI_GERI;COUP_DOUBLE;GYROBALLE;GRIFFE;FRENESIE;ROUE_DE_FEU;FURIE;CRU_AILE;VENGEANCE;ASSURANCE;TRICHERIE;TACLE_FEU;VIT_EXTREME;ELECTACLE;MARTO_POING;CONTRE;REVENANT;CROCS_GIVRE;ATOUT;NITROCHARGE;CONSTRICTION;BLUFF;LIGOTAGE;ECRASEMENT;ETINCELLE;BULLDOBOULE;LUTTE;PLONGEE;FOUET_LIANES;LARCIN;ULTIMAPOING;BALL_GLACE;LAME_FEUILLE;DYNAMOPOING;ESCALADE;DAMOCLES;MANIA;QUEUE_DE_FER;ATTRITION;CORPS_PERDU;DOUBLE_PIED;POING_DARD;TRANCHE_NUIT;REPRESAILLES;FORTE_PAUME;DEMI_TOUR;ECRAS_FACE;VAMPIPOING;AIKIDO;HYDROQUEUE;PICORE;ROULADE;POING_DE_FEU;QUEUE_POISON;GRIFFE_ACIER;LAME_SAINTE;PIQURE;CROCS_FEU;PIED_VOLTIGE;BEC_VRILLE;POING_KARATE;COLERE;FACADE;VIVE_ATTAQUE;CASSE_BRIQUE;OMBRE_PORTEE;ECLATE_ROC;DRACO_QUEUE;NOEUD_HERBE;TELEPORT;ETREINTE;DERNIERECOUR;CREVECOEUR;BELIER;REVEIL_FORCE;COMBO_GRIFFE;TETE_DE_FER;SABOTAGE;PIED_BRULEUR;CLAQUOIR;COGNE;IMPLORE;CROCHETVENIN;VAMPIRISME;LECHOUILLE;CHUTE_LIBRE;RAPACE;PIED_SAUTE;COUP_CROIX;CROCS_ECLAIR;KOUD_KORNE;ESSORAGE;TUNNEL;DRACOCHARGE;FAUX_CHAGE;CHARGE;ETONNEMENT;ENVOL;MARTOBOIS;PROJECTION;COQUILAME;CHARGE_FOUDRE;GIGA_IMPACT;GRIFFE_OMBRE;SURPUISSANCE;EXCUSE;CASCADE;PUNITION;DIRECT_TOXIK;SACRIFICE;VENDETTA;POING_METEOR;ACROBATIE;YAMA_ARASHI;FORCE_POIGNE;PATIENCE;COUP_BAS;POINGLACE;TORGNOLES;PLUMO_QUEUE;FEINTE;RIPOSTE;PLAQUAGE;AILE_D_ACIER;DRACOGRIFFE;POISON_CROIX;ENCORNEBOIS;COUP_D_BOULE;MACHOUILLE;CROC_DE_MORT;TAILLADE;ULTIMAWASHI;POING_OMBRE;AEROPIQUE;REBOND;MITRA_POING;UPPERCUT;SOUPLESSE},{VAR__ATTAQUE_NOM}))");
return abilityData_;
}
static AbilityData m74(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
abilityData_.setBoostStatRankProtected(enumMapStatisticByte_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m75(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(1));
StringList stringList_=new StringList(new CollCapacity(7));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringList_.add("PARALYSIE");
stringList_.add("GEL");
stringList_.add("BRULURE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m76(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumList<Statistic> enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
enumListStatistic_.add(Statistic.ATTACK);
abilityData_.setImmuLowStat(enumListStatistic_);
return abilityData_;
}
static AbilityData m77(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("FEU",Rate.newRate("1/2"));
abilityData_.setMultDamageFoe(stringMapRate_);
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate("0"));
effectEndRoundIndividual_.setRecoilDamage(Rate.newRate("0"));
effectEndRoundIndividual_.setHealHp(Rate.newRate("0"));
stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("BRULURE",Rate.newRate("-2"));
effectEndRoundIndividual_.setMultDamageStatus(stringMapRate_);
effectEndRoundIndividual_.setUserStatusEndRound("");
effectEndRoundIndividual_.setFailEndRound("");
effectEndRoundIndividual_.setEndRoundRank(42);
effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
effectEndRoundIndividual_.setFail("");
custListEffectEndRound_.add(effectEndRoundIndividual_);
abilityData_.setEffectEndRound(custListEffectEndRound_);
return abilityData_;
}
static AbilityData m78(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("BRULURE");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("BRULURE");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("BRULURE");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("BRULURE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("BRULURE");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("BRULURE");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m79(){
AbilityData abilityData_ = Instances.newAbilityData();
StatisticStatusList objectMapStatisticStatusByte_=new StatisticStatusList(new CollCapacity(1));
objectMapStatisticStatusByte_.addEntry(new StatisticStatus(Statistic.SPEED,"PEUR"),(byte)1);
abilityData_.setMultStatIfStatutRank(objectMapStatisticStatusByte_);
return abilityData_;
}
static AbilityData m80(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
abilityData_.setMultStatIfKoFoe(enumMapStatisticByte_);
return abilityData_;
}
static AbilityData m81(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setIgnFoeStatisBoost(true);
return abilityData_;
}
static AbilityData m82(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(4));
stringList_.add("PROTECTION");
stringList_.add("MUR_LUMIERE");
stringList_.add("RUNE_PROTECT");
stringList_.add("BRUME");
abilityData_.setIgnFoeTeamMove(stringList_);
return abilityData_;
}
static AbilityData m83(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m84(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingWithStatistic();
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)-1);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"VAR__CIBLE_CLONE>0");
effectStatistic_.setLocalFailStatis(enumMapStatisticString_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.TOUS_ADV);
effectStatistic_.setFail("F");
effectWhileSendingWithStatistic_.setEffect(effectStatistic_);
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m85(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
stringMapRate_.addEntry("GLACE",Rate.newRate("1/2"));
stringMapRate_.addEntry("FEU",Rate.newRate("1/2"));
abilityData_.setMultDamageFoe(stringMapRate_);
return abilityData_;
}
static AbilityData m86(){
AbilityData abilityData_ = Instances.newAbilityData();
MonteCarloString monteCarloString_=new MonteCarloString(new CollCapacity(2));
monteCarloString_.addQuickEvent("",LgInt.newLgInt("7"));
monteCarloString_.addQuickEvent("AMOUR",LgInt.newLgInt("3"));
abilityData_.setSingleStatus(monteCarloString_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(1));
stringMapString_.addEntry("AMOUR","VAR__EXISTE_GENRE_ASSEXUE|VAR__GENRES_EGAUX|VAR__CIBLE_POSSEDE_STATUT_RELATION__AMOUR");
abilityData_.setFailStatus(stringMapString_);
return abilityData_;
}
static AbilityData m87(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)1);
abilityData_.setBoostStatRankProtected(enumMapStatisticByte_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m88(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultDamage("caracgaucheouvert(VAR__COEFF_EFF,1)+1");
return abilityData_;
}
static AbilityData m89(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(4));
stringList_.add("VOL_MAGNETIK");
stringList_.add("LEVIKINESIE");
stringList_.add("PICOTS");
stringList_.add("PICS_TOXIK");
abilityData_.setImmuMove(stringList_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOL");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOL");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOL");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOL");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOL");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOL");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m90(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setTakeItemByDamagingMove(true);
return abilityData_;
}
static AbilityData m91(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Rate> enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate("2"));
abilityData_.setMultStatAlly(enumMapStatisticRate_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ACCURACY,"2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m92(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Rate> enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate("2"));
abilityData_.setMultStatAlly(enumMapStatisticRate_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ACCURACY,"2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m93(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
stringMapRate_.addEntry("SOMMEIL",Rate.newRate("2"));
stringMapRate_.addEntry("SOMMEIL_REPOS",Rate.newRate("2"));
abilityData_.setDivideStatusRound(stringMapRate_);
return abilityData_;
}
static AbilityData m94(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundMultiRelation effectEndRoundMultiRelation_=Instances.newEffectEndRoundMultiRelation();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
stringMapRate_.addEntry("SOMMEIL",Rate.newRate("1/8"));
stringMapRate_.addEntry("SOMMEIL_REPOS",Rate.newRate("1/8"));
effectEndRoundMultiRelation_.setDamageByStatus(stringMapRate_);
effectEndRoundMultiRelation_.setFailEndRound("");
effectEndRoundMultiRelation_.setEndRoundRank(59);
effectEndRoundMultiRelation_.setTargetChoice(TargetChoice.TOUS_ADV);
effectEndRoundMultiRelation_.setFail("");
custListEffectEndRound_.add(effectEndRoundMultiRelation_);
abilityData_.setEffectEndRound(custListEffectEndRound_);
return abilityData_;
}
static AbilityData m95(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setHealedStatusBySwitch(true);
return abilityData_;
}
static AbilityData m96(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("3/2*cardinal(inter({VAR__ATTAQUE_NOM},{AURASPHERE;VIBROBSCUR;DRACOCHOC;VIBRA_SOIN;VIBRAQUA}))+cardinal(complementaire({AURASPHERE;VIBROBSCUR;DRACOCHOC;VIBRA_SOIN;VIBRAQUA},{VAR__ATTAQUE_NOM}))");
return abilityData_;
}
static AbilityData m97(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(4));
stringMapString_.addEntry("GRELE","GLACE");
stringMapString_.addEntry("ZENITH","FEU");
stringMapString_.addEntry("DANSE_PLUIE","EAU");
stringMapString_.addEntry("TEMPETESABLE","ROCHE");
abilityData_.setChgtTypeByWeather(stringMapString_);
return abilityData_;
}
static AbilityData m98(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,"3/2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m99(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(3));
stringList_.add("DESTRUCTION");
stringList_.add("EXPLOSION");
stringList_.add("TOUT_OU_RIEN");
abilityData_.setImmuMove(stringList_);
return abilityData_;
}
static AbilityData m100(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMumy(true);
return abilityData_;
}
static AbilityData m101(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPEED,(byte)1);
abilityData_.setBoostStatRankProtected(enumMapStatisticByte_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m102(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate("3/10"));
effectEndRoundIndividual_.setRecoilDamage(Rate.newRate("0"));
effectEndRoundIndividual_.setHealHp(Rate.newRate("0"));
effectEndRoundIndividual_.setUserStatusEndRound("");
effectEndRoundIndividual_.setFailEndRound("");
effectEndRoundIndividual_.setEndRoundRank(24);
effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
effectEndRoundIndividual_.setFail("");
custListEffectEndRound_.add(effectEndRoundIndividual_);
abilityData_.setEffectEndRound(custListEffectEndRound_);
return abilityData_;
}
static AbilityData m103(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(4));
stringList_.add("IMITATION");
stringList_.add("ECHANGE");
stringList_.add("PASSE_PASSE");
stringList_.add("TOURMAGIK");
abilityData_.setImmuMove(stringList_);
abilityData_.setPlate(true);
return abilityData_;
}
static AbilityData m104(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setNbHits(true);
return abilityData_;
}
static AbilityData m105(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setTypeForMoves("NORMAL");
return abilityData_;
}
static AbilityData m106(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ACCURACY,"13/10");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m107(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)1);
abilityData_.setBoostStatRankProtected(enumMapStatisticByte_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m108(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(21));
stringList_.add("BOMBE_ACIDE");
stringList_.add("AURASPHERE");
stringList_.add("PILONNAGE");
stringList_.add("BALLE_GRAINE");
stringList_.add("BOMB_OEUF");
stringList_.add("BOULE_ELEK");
stringList_.add("ECO_SPHERE");
stringList_.add("EXPLOFORCE");
stringList_.add("GYROBALLE");
stringList_.add("BALL_GLACE");
stringList_.add("BOMBAIMANT");
stringList_.add("BALL_BRUME");
stringList_.add("BOUE_BOMBE");
stringList_.add("OCTAZOOKA");
stringList_.add("ROC_BOULET");
stringList_.add("INCENDIE");
stringList_.add("CANON_GRAINE");
stringList_.add("BALL_OMBRE");
stringList_.add("BOMB_BEURK");
stringList_.add("BALL_METEO");
stringList_.add("ELECANON");
abilityData_.setImmuMove(stringList_);
return abilityData_;
}
static AbilityData m109(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setRecoilDamageFoe(Rate.newRate("1/8"));
return abilityData_;
}
static AbilityData m110(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<TypeDamageBoost> stringMapTypeDamageBoost_=new StringMap<TypeDamageBoost>(new CollCapacity(1));
stringMapTypeDamageBoost_.addEntry("NORMAL",new TypeDamageBoost("FEE",Rate.newRate("13/10")));
abilityData_.setChangingBoostTypes(stringMapTypeDamageBoost_);
return abilityData_;
}
static AbilityData m111(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<TypeDamageBoost> stringMapTypeDamageBoost_=new StringMap<TypeDamageBoost>(new CollCapacity(1));
stringMapTypeDamageBoost_.addEntry("NORMAL",new TypeDamageBoost("GLACE",Rate.newRate("13/10")));
abilityData_.setChangingBoostTypes(stringMapTypeDamageBoost_);
return abilityData_;
}
static AbilityData m112(){
AbilityData abilityData_ = Instances.newAbilityData();
StatisticCategoryRate objectMapStatisticCategoryRate_=new StatisticCategoryRate(new CollCapacity(1));
objectMapStatisticCategoryRate_.addEntry(new StatisticCategory(Statistic.EVASINESS,"AUTRE"),Rate.newRate("2"));
abilityData_.setMultStatIfCat(objectMapStatisticCategoryRate_);
return abilityData_;
}
static AbilityData m113(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
stringMapRate_.addEntry("ZENITH",Rate.newRate("-1/8"));
stringMapRate_.addEntry("DANSE_PLUIE",Rate.newRate("1/8"));
abilityData_.setHealHpByWeather(stringMapRate_);
stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("FEU",Rate.newRate("5/4"));
abilityData_.setMultDamageFoe(stringMapRate_);
WeatherTypes objectMapWeatherTypeRate_=new WeatherTypes(new CollCapacity(6));
objectMapWeatherTypeRate_.addEntry(new WeatherType("TEMPETESABLE","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("GRELE","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("ZENITH","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("DANSE_PLUIE","EAU"),Rate.newRate("1/4"));
objectMapWeatherTypeRate_.addEntry(new WeatherType("ORAGE","EAU"),Rate.newRate("1/4"));
abilityData_.setHealHpByTypeIfWeather(objectMapWeatherTypeRate_);
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m114(){
AbilityData abilityData_ = Instances.newAbilityData();
StatisticTypeByte objectMapStatisticTypeByte_=new StatisticTypeByte(new CollCapacity(3));
objectMapStatisticTypeByte_.addEntry(new StatisticType(Statistic.SPEED,"SPECTRE"),(byte)1);
objectMapStatisticTypeByte_.addEntry(new StatisticType(Statistic.SPEED,"INSECTE"),(byte)1);
objectMapStatisticTypeByte_.addEntry(new StatisticType(Statistic.SPEED,"TENEBRE"),(byte)1);
abilityData_.setMultStatIfDamgeType(objectMapStatisticTypeByte_);
return abilityData_;
}
static AbilityData m115(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.EVASINESS,"6/5*cardinal(inter({VAR__FIGHTER_STATUTS},{CONFUSION}))+cardinal(complementaire({CONFUSION},{VAR__FIGHTER_STATUTS}))");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m116(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setImmuLowStatIfStatus(new CustList<StatisticStatus>(new StatisticStatus(Statistic.SPEED,"PARALYSIE")));
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"3/2*cardinal(inter({VAR__FIGHTER_STATUTS},{BRULURE;POISON_GRAVE;SOMMEIL_REPOS;SIMPLE_POISON;PARALYSIE;SOMMEIL;GEL}))+cardinal(complementaire({BRULURE;POISON_GRAVE;SOMMEIL_REPOS;SIMPLE_POISON;PARALYSIE;SOMMEIL;GEL},{VAR__FIGHTER_STATUTS}))");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m117(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Rate> enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate("2"));
abilityData_.setMultStatAlly(enumMapStatisticRate_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ACCURACY,"2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m118(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,"3/2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m119(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_NOM},{POING_ECLAIR;MARTO_POING;POING_OMBRE;PISTO_POING;DYNAMOPOING;ULTIMAPOING;POING_DE_FEU;POING_METEOR;POINGLACE;POING_COMETE;VAMPIPOING;UPPERCUT;MITRA_POING;STRATOPERCUT}))+cardinal(complementaire({POING_ECLAIR;MARTO_POING;POING_OMBRE;PISTO_POING;DYNAMOPOING;ULTIMAPOING;POING_DE_FEU;POING_METEOR;POINGLACE;POING_COMETE;VAMPIPOING;UPPERCUT;MITRA_POING;STRATOPERCUT},{VAR__ATTAQUE_NOM}))");
return abilityData_;
}
static AbilityData m120(){
AbilityData abilityData_ = Instances.newAbilityData();
MonteCarloString monteCarloString_=new MonteCarloString(new CollCapacity(2));
monteCarloString_.addQuickEvent("",LgInt.newLgInt("7"));
monteCarloString_.addQuickEvent("SIMPLE_POISON",LgInt.newLgInt("3"));
abilityData_.setSingleStatus(monteCarloString_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(1));
stringMapString_.addEntry("SIMPLE_POISON","cardinal(inter({VAR__CIBLE_STATUTS},{SIMPLE_POISON;POISON_GRAVE}))>0|VAR__CIBLE_CLONE>0|cardinal(inter({VAR__CIBLE_TYPES},{POISON;ACIER}))>0");
abilityData_.setFailStatus(stringMapString_);
return abilityData_;
}
static AbilityData m121(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPEED,(byte)-1);
abilityData_.setLowStatFoeHit(enumMapStatisticByte_);
return abilityData_;
}
static AbilityData m122(){
AbilityData abilityData_ = Instances.newAbilityData();
MonteCarloString monteCarloString_=new MonteCarloString(new CollCapacity(4));
monteCarloString_.addQuickEvent("",LgInt.newLgInt("7"));
monteCarloString_.addQuickEvent("SIMPLE_POISON",LgInt.newLgInt("1"));
monteCarloString_.addQuickEvent("SOMMEIL",LgInt.newLgInt("1"));
monteCarloString_.addQuickEvent("PARALYSIE",LgInt.newLgInt("1"));
abilityData_.setSingleStatus(monteCarloString_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(3));
stringMapString_.addEntry("SIMPLE_POISON","cardinal(inter({VAR__CIBLE_STATUTS},{SIMPLE_POISON;POISON_GRAVE}))>0|VAR__CIBLE_CLONE>0|cardinal(inter({VAR__CIBLE_TYPES},{POISON;ACIER}))>0");
stringMapString_.addEntry("SOMMEIL","cardinal(inter({VAR__CIBLE_STATUTS},{SOMMEIL;SOMMEIL_REPOS}))>0|VAR__CIBLE_CLONE>0");
stringMapString_.addEntry("PARALYSIE","cardinal(inter({VAR__CIBLE_STATUTS},{PARALYSIE}))>0|VAR__CIBLE_CLONE>0");
abilityData_.setFailStatus(stringMapString_);
return abilityData_;
}
static AbilityData m123(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setNbUsedPp(1);
return abilityData_;
}
static AbilityData m124(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("3/2*cardinal(inter({VAR__ATTAQUE_NOM},{MORSURE;MACHOUILLE;CROCS_ECLAIR;CROCS_FEU;CROCS_GIVRE;CROCHETVENIN}))+cardinal(complementaire({MORSURE;MACHOUILLE;CROCS_ECLAIR;CROCS_FEU;CROCS_GIVRE;CROCHETVENIN},{VAR__ATTAQUE_NOM}))");
return abilityData_;
}
static AbilityData m125(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setCopyMovesTypes(true);
return abilityData_;
}
static AbilityData m126(){
AbilityData abilityData_ = Instances.newAbilityData();
MonteCarloString monteCarloString_=new MonteCarloString(new CollCapacity(2));
monteCarloString_.addQuickEvent("",LgInt.newLgInt("4"));
monteCarloString_.addQuickEvent("PEUR",LgInt.newLgInt("1"));
abilityData_.setSingleStatus(monteCarloString_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(1));
stringMapString_.addEntry("PEUR","VAR__CIBLE_CLONE>0");
abilityData_.setFailStatus(stringMapString_);
return abilityData_;
}
static AbilityData m127(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setBreakFoeImmune(new CustList<TypesDuo>(new TypesDuo("NORMAL","SPECTRE"),new TypesDuo("COMBAT","SPECTRE")));
return abilityData_;
}
static AbilityData m128(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setGiveItemToAllyHavingUsed(true);
return abilityData_;
}
static AbilityData m129(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("JET_DE_SABLE");
stringList_.add("TELEKINESIE");
abilityData_.setImmuMove(stringList_);
EnumList<Statistic> enumListStatistic_=new EnumList<Statistic>(new CollCapacity(1));
enumListStatistic_.add(Statistic.ACCURACY);
abilityData_.setImmuLowStat(enumListStatistic_);
return abilityData_;
}
static AbilityData m130(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setHealedHpRateBySwitch(Rate.newRate("1/3"));
return abilityData_;
}
static AbilityData m131(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GRELE");
abilityData_.setImmuWeather(stringList_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"max(6/5*cardinal(inter({VAR__CLIMATS},{GRELE}))+cardinal(complementaire({GRELE},{VAR__CLIMATS})),1)");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m132(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("(5/4*differentnum({VAR__CIBLE_GENRE},{VAR__LANCEUR_GENRE})+3/4*egalnum({VAR__CIBLE_GENRE},{VAR__LANCEUR_GENRE}))*inclusnum({VAR__CIBLE_GENRE},{FEMALE;MALE})*inclusnum({VAR__LANCEUR_GENRE},{FEMALE;MALE})");
return abilityData_;
}
static AbilityData m133(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setEnabledWeather("TEMPETESABLE");
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m134(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setImmuDamageRecoil(true);
abilityData_.setImmuRechargeRound(true);
abilityData_.setCancelSecEffectOwner(true);
abilityData_.setMultPower("13/10");
return abilityData_;
}
static AbilityData m135(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setEnabledWeather("ZENITH");
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m136(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultEvtRateSecEffectOwner(Rate.newRate("2"));
return abilityData_;
}
static AbilityData m137(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultVarBoost(Rate.newRate("2"));
return abilityData_;
}
static AbilityData m138(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultDamageCh(Rate.newRate("3/2"));
return abilityData_;
}
static AbilityData m139(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate("0"));
effectEndRoundIndividual_.setRecoilDamage(Rate.newRate("0"));
effectEndRoundIndividual_.setHealHp(Rate.newRate("0"));
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
stringMapRate_.addEntry("POISON_GRAVE",Rate.newRate("-2"));
stringMapRate_.addEntry("SIMPLE_POISON",Rate.newRate("-2"));
effectEndRoundIndividual_.setMultDamageStatus(stringMapRate_);
effectEndRoundIndividual_.setUserStatusEndRound("");
effectEndRoundIndividual_.setFailEndRound("");
effectEndRoundIndividual_.setEndRoundRank(41);
effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
effectEndRoundIndividual_.setFail("");
custListEffectEndRound_.add(effectEndRoundIndividual_);
abilityData_.setEffectEndRound(custListEffectEndRound_);
return abilityData_;
}
static AbilityData m140(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultSufferedDamageSuperEff(Rate.newRate("3/4"));
return abilityData_;
}
static AbilityData m141(){
AbilityData abilityData_ = Instances.newAbilityData();
MonteCarloString monteCarloString_=new MonteCarloString(new CollCapacity(2));
monteCarloString_.addQuickEvent("",LgInt.newLgInt("7"));
monteCarloString_.addQuickEvent("PARALYSIE",LgInt.newLgInt("3"));
abilityData_.setSingleStatus(monteCarloString_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(1));
stringMapString_.addEntry("PARALYSIE","cardinal(inter({VAR__CIBLE_STATUTS},{PARALYSIE}))>0|VAR__CIBLE_CLONE>0");
abilityData_.setFailStatus(stringMapString_);
return abilityData_;
}
static AbilityData m142(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setInflictingDamageInsteadOfSuffering(true);
return abilityData_;
}
static AbilityData m143(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setGiveItemToAllyHavingUsed(true);
return abilityData_;
}
static AbilityData m144(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(6));
stringMapString_.addEntry("POISON_GRAVE","SIMPLE_POISON");
stringMapString_.addEntry("BRULURE","BRULURE");
stringMapString_.addEntry("SIMPLE_POISON","SIMPLE_POISON");
stringMapString_.addEntry("SOMMEIL","SOMMEIL");
stringMapString_.addEntry("GEL","GEL");
stringMapString_.addEntry("PARALYSIE","PARALYSIE");
abilityData_.setForwardStatus(stringMapString_);
stringMapString_=new StringMap<String>(new CollCapacity(5));
stringMapString_.addEntry("BRULURE","cardinal(inter({VAR__CIBLE_STATUTS},{BRULURE}))>0|VAR__CIBLE_CLONE>0|cardinal(inter({VAR__CIBLE_TYPES},{FEU}))>0");
stringMapString_.addEntry("SIMPLE_POISON","cardinal(inter({VAR__CIBLE_STATUTS},{SIMPLE_POISON;POISON_GRAVE}))>0|VAR__CIBLE_CLONE>0|cardinal(inter({VAR__CIBLE_TYPES},{POISON;ACIER}))>0");
stringMapString_.addEntry("SOMMEIL","cardinal(inter({VAR__CIBLE_STATUTS},{SOMMEIL;SOMMEIL_REPOS}))>0|VAR__CIBLE_CLONE>0");
stringMapString_.addEntry("GEL","cardinal(inter({VAR__CIBLE_STATUTS},{GEL}))>0|VAR__CIBLE_CLONE>0|cardinal(inter({VAR__CIBLE_TYPES},{GLACE}))>0");
stringMapString_.addEntry("PARALYSIE","cardinal(inter({VAR__CIBLE_STATUTS},{PARALYSIE}))>0|VAR__CIBLE_CLONE>0");
abilityData_.setFailStatus(stringMapString_);
return abilityData_;
}
static AbilityData m145(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("3/2*caracdroiteferme(VAR__PUISSANCE_BASE,60)+caracgaucheouvert(VAR__PUISSANCE_BASE,60)");
return abilityData_;
}
static AbilityData m146(){
AbilityData abilityData_ = Instances.newAbilityData();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingWithStatistic();
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(2));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)1);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
effectStatistic_.setFail("F");
effectWhileSendingWithStatistic_.setEffect(effectStatistic_);
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
abilityData_.setEffectSending(custListEffectWhileSendingWithStatistic_);
return abilityData_;
}
static AbilityData m147(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setImmuDamageAllyMoves(true);
return abilityData_;
}
static AbilityData m148(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_NOM},{LUTTE;FRACASS_TETE;DAMOCLES;MARTOBOIS;ELECTACLE;RAPACE;PIED_SAUTE;SACRIFICE;BOUTEFEU;BELIER;PIED_VOLTIGE}))+cardinal(complementaire({LUTTE;FRACASS_TETE;DAMOCLES;MARTOBOIS;ELECTACLE;RAPACE;PIED_SAUTE;SACRIFICE;BOUTEFEU;BELIER;PIED_VOLTIGE},{VAR__ATTAQUE_NOM}))");
return abilityData_;
}
static AbilityData m149(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("CONFUSION");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("CONFUSION");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("CONFUSION");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("CONFUSION");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("CONFUSION");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("CONFUSION");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m150(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setForbidUseBerryAgainstFoes(true);
return abilityData_;
}
static AbilityData m151(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(46));
stringList_.add("ABSORB_EAU");
stringList_.add("ABSORB_VOLT");
stringList_.add("ANTI_BRUIT");
stringList_.add("ARMUMAGMA");
stringList_.add("ARMURBASTON");
stringList_.add("ATTENTION");
stringList_.add("BENET");
stringList_.add("COEUR_DE_COQ");
stringList_.add("COQUE_ARMURE");
stringList_.add("CORPS_SAIN");
stringList_.add("DON_FLORAL");
stringList_.add("ECAILLE_SPECIALE");
stringList_.add("ECHAUFFEMENT");
stringList_.add("ECRAN_FUMEE");
stringList_.add("ECRAN_POUDRE");
stringList_.add("ESPRIT_VITAL");
stringList_.add("FERMETE");
stringList_.add("FEUILLE_GARDE");
stringList_.add("FILTRE");
stringList_.add("GARDE_MYSTIK");
stringList_.add("GLUE");
stringList_.add("HERBIVORE");
stringList_.add("HYPER_CUTTER");
stringList_.add("IGNIFU_VOILE");
stringList_.add("IGNIFUGE");
stringList_.add("INCONSCIENT");
stringList_.add("INSOMNIA");
stringList_.add("ISOGRAISSE");
stringList_.add("LAVABO");
stringList_.add("LEVITATION");
stringList_.add("MOITEUR");
stringList_.add("MOMIE");
stringList_.add("MOTORISE");
stringList_.add("PARATONNERRE");
stringList_.add("PEAU_SECHE");
stringList_.add("PIEDS_CONFUS");
stringList_.add("REGARD_VIF");
stringList_.add("RIDEAU_NEIGE");
stringList_.add("SIMPLE");
stringList_.add("SOLIDE_ROC");
stringList_.add("TEMPO_PERSO");
stringList_.add("TENSION");
stringList_.add("TORCHE");
stringList_.add("VACCIN");
stringList_.add("VENTOUSE");
stringList_.add("VOILE_SABLE");
abilityData_.setIgnAbility(stringList_);
abilityData_.setMumy(true);
return abilityData_;
}
static AbilityData m152(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setImmuDamageRecoil(true);
return abilityData_;
}
static AbilityData m153(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m154(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"2");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m155(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("3/2*cardinal(inter({VAR__ATTAQUE_TYPES},{FEU}))+cardinal(complementaire({FEU},{VAR__ATTAQUE_TYPES}))");
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuMoveTypesByWeather(stringMapStringList_);
return abilityData_;
}
static AbilityData m156(){
AbilityData abilityData_ = Instances.newAbilityData();
abilityData_.setMultPower("cardinal(inter({VAR__ATTAQUE_TYPES},{EAU}))*(3/2*caracgaucheferme(VAR__LANCEUR_PV_RESTANTS,1/3*VAR__LANCEUR_PV_MAX)+caracdroiteouvert(VAR__LANCEUR_PV_RESTANTS,1/3*VAR__LANCEUR_PV_MAX))+cardinal(complementaire({EAU},{VAR__ATTAQUE_TYPES}))");
return abilityData_;
}
static AbilityData m157(){
AbilityData abilityData_ = Instances.newAbilityData();
MonteCarloString monteCarloString_=new MonteCarloString(new CollCapacity(2));
monteCarloString_.addQuickEvent("",LgInt.newLgInt("7"));
monteCarloString_.addQuickEvent("SIMPLE_POISON",LgInt.newLgInt("3"));
abilityData_.setSingleStatus(monteCarloString_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(1));
stringMapString_.addEntry("SIMPLE_POISON","cardinal(inter({VAR__CIBLE_STATUTS},{SIMPLE_POISON;POISON_GRAVE}))>0|VAR__CIBLE_CLONE>0|cardinal(inter({VAR__CIBLE_TYPES},{POISON;ACIER}))>0");
abilityData_.setFailStatus(stringMapString_);
return abilityData_;
}
static AbilityData m158(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPEED,(byte)1);
abilityData_.setBoostStatRankEndRound(enumMapStatisticByte_);
return abilityData_;
}
static AbilityData m159(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(46));
stringList_.add("ABSORB_EAU");
stringList_.add("ABSORB_VOLT");
stringList_.add("ANTI_BRUIT");
stringList_.add("ARMUMAGMA");
stringList_.add("ARMURBASTON");
stringList_.add("ATTENTION");
stringList_.add("BENET");
stringList_.add("COEUR_DE_COQ");
stringList_.add("COQUE_ARMURE");
stringList_.add("CORPS_SAIN");
stringList_.add("DON_FLORAL");
stringList_.add("ECAILLE_SPECIALE");
stringList_.add("ECHAUFFEMENT");
stringList_.add("ECRAN_FUMEE");
stringList_.add("ECRAN_POUDRE");
stringList_.add("ESPRIT_VITAL");
stringList_.add("FERMETE");
stringList_.add("FEUILLE_GARDE");
stringList_.add("FILTRE");
stringList_.add("GARDE_MYSTIK");
stringList_.add("GLUE");
stringList_.add("HERBIVORE");
stringList_.add("HYPER_CUTTER");
stringList_.add("IGNIFU_VOILE");
stringList_.add("IGNIFUGE");
stringList_.add("INCONSCIENT");
stringList_.add("INSOMNIA");
stringList_.add("ISOGRAISSE");
stringList_.add("LAVABO");
stringList_.add("LEVITATION");
stringList_.add("MOITEUR");
stringList_.add("MOMIE");
stringList_.add("MOTORISE");
stringList_.add("PARATONNERRE");
stringList_.add("PEAU_SECHE");
stringList_.add("PIEDS_CONFUS");
stringList_.add("REGARD_VIF");
stringList_.add("RIDEAU_NEIGE");
stringList_.add("SIMPLE");
stringList_.add("SOLIDE_ROC");
stringList_.add("TEMPO_PERSO");
stringList_.add("TENSION");
stringList_.add("TORCHE");
stringList_.add("VACCIN");
stringList_.add("VENTOUSE");
stringList_.add("VOILE_SABLE");
abilityData_.setIgnAbility(stringList_);
abilityData_.setMumy(true);
return abilityData_;
}
static AbilityData m160(){
AbilityData abilityData_ = Instances.newAbilityData();
StringMap<StringList> stringMapStringList_=new StringMap<StringList>(new CollCapacity(6));
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("GRELE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("ZENITH",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("DANSE_PLUIE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("ORAGE",stringList_);
stringList_=new StringList(new CollCapacity(2));
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringMapStringList_.addEntry("TEMPETESABLE",stringList_);
abilityData_.setImmuStatus(stringMapStringList_);
return abilityData_;
}
static AbilityData m161(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("CYCLONE");
stringList_.add("HURLEMENT");
abilityData_.setImmuMove(stringList_);
return abilityData_;
}
static AbilityData m162(){
AbilityData abilityData_ = Instances.newAbilityData();
EnumMap<Statistic,Rate> enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate("11/10"));
abilityData_.setMultStatAlly(enumMapStatisticRate_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ACCURACY,"11/10");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
static AbilityData m163(){
AbilityData abilityData_ = Instances.newAbilityData();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("TEMPETESABLE");
abilityData_.setImmuWeather(stringList_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"max(6/5*cardinal(inter({VAR__CLIMATS},{TEMPETESABLE}))+cardinal(complementaire({TEMPETESABLE},{VAR__CLIMATS})),1)");
abilityData_.setMultStat(enumMapStatisticString_);
return abilityData_;
}
}
