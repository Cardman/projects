package aiki.sml.init;
import aiki.instances.*;
import aiki.fight.util.*;
import aiki.fight.effects.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.items.*;
import aiki.fight.enums.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
public final class ItInit{
private ItInit(){}
public static StringMap<Item> i(){
 StringMap<Item> i_ = new StringMap<Item>(new CollCapacity(287));
i_.addEntry("ACCRO_GRIFFE",m0());
i_.addEntry("AIMANT",m1());
i_.addEntry("AMELIORATOR",m2());
i_.addEntry("ANTIDOTE",m3());
i_.addEntry("ANTIGEL",m4());
i_.addEntry("ANTI_BRULE",m5());
i_.addEntry("ANTI_PARA",m6());
i_.addEntry("APPAT_BALL",m7());
i_.addEntry("BAIE_ABRIKO",m8());
i_.addEntry("BAIE_ALGA",m9());
i_.addEntry("BAIE_BABIRI",m10());
i_.addEntry("BAIE_CERIZ",m11());
i_.addEntry("BAIE_CHARTI",m12());
i_.addEntry("BAIE_CHERIM",m13());
i_.addEntry("BAIE_CHOCCO",m14());
i_.addEntry("BAIE_COBABA",m15());
i_.addEntry("BAIE_DURIN",m16());
i_.addEntry("BAIE_EKA",m17());
i_.addEntry("BAIE_ENIGMA",m18());
i_.addEntry("BAIE_FIGUY",m19());
i_.addEntry("BAIE_FRAIGO",m20());
i_.addEntry("BAIE_FRAIVE",m21());
i_.addEntry("BAIE_FRAMBY",m22());
i_.addEntry("BAIE_FRISTA",m23());
i_.addEntry("BAIE_GOWAV",m24());
i_.addEntry("BAIE_GRENA",m25());
i_.addEntry("BAIE_JABOCA",m26());
i_.addEntry("BAIE_JOUCA",m27());
i_.addEntry("BAIE_KEBIA",m28());
i_.addEntry("BAIE_KIKA",m29());
i_.addEntry("BAIE_KIWAN",m30());
i_.addEntry("BAIE_LAMPOU",m31());
i_.addEntry("BAIE_LANSAT",m32());
i_.addEntry("BAIE_LICHII",m33());
i_.addEntry("BAIE_LINGAN",m34());
i_.addEntry("BAIE_LONME",m35());
i_.addEntry("BAIE_MAGO",m36());
i_.addEntry("BAIE_MANGOU",m37());
i_.addEntry("BAIE_MARON",m38());
i_.addEntry("BAIE_MEPO",m39());
i_.addEntry("BAIE_MICLE",m40());
i_.addEntry("BAIE_MYRTE",m41());
i_.addEntry("BAIE_NANAB",m42());
i_.addEntry("BAIE_NANANA",m43());
i_.addEntry("BAIE_NANONE",m44());
i_.addEntry("BAIE_ORAN",m45());
i_.addEntry("BAIE_PALMA",m46());
i_.addEntry("BAIE_PANGA",m47());
i_.addEntry("BAIE_PAPAYA",m48());
i_.addEntry("BAIE_PARMA",m49());
i_.addEntry("BAIE_PECHA",m50());
i_.addEntry("BAIE_PITAYE",m51());
i_.addEntry("BAIE_POCPOC",m52());
i_.addEntry("BAIE_POMMO",m53());
i_.addEntry("BAIE_POMROZ",m54());
i_.addEntry("BAIE_PRINE",m55());
i_.addEntry("BAIE_QUALOT",m56());
i_.addEntry("BAIE_RABUTA",m57());
i_.addEntry("BAIE_RANGMA",m58());
i_.addEntry("BAIE_RATAM",m59());
i_.addEntry("BAIE_REMU",m60());
i_.addEntry("BAIE_REPOI",m61());
i_.addEntry("BAIE_RESIN",m62());
i_.addEntry("BAIE_SAILAK",m63());
i_.addEntry("BAIE_SEDRA",m64());
i_.addEntry("BAIE_SELRO",m65());
i_.addEntry("BAIE_SIAM",m66());
i_.addEntry("BAIE_SITRUS",m67());
i_.addEntry("BAIE_STEKPA",m68());
i_.addEntry("BAIE_TAMATO",m69());
i_.addEntry("BAIE_TRONCI",m70());
i_.addEntry("BAIE_WIKI",m71());
i_.addEntry("BAIE_WILLIA",m72());
i_.addEntry("BAIE_YAPAP",m73());
i_.addEntry("BAIE_ZALIS",m74());
i_.addEntry("BALLE_FER",m75());
i_.addEntry("BALLON",m76());
i_.addEntry("BANDEAU",m77());
i_.addEntry("BANDEAU_ETREINTE",m78());
i_.addEntry("BAND_CHOIX",m79());
i_.addEntry("BAND_MUSCLE",m80());
i_.addEntry("BAND_POUV",m81());
i_.addEntry("BATON",m82());
i_.addEntry("BEC_POINTU",m83());
i_.addEntry("BIS_BALL",m84());
i_.addEntry("BIZAR_ENCENS",m85());
i_.addEntry("BOUE_NOIRE",m86());
i_.addEntry("BOULE_DE_NEIGE",m87());
i_.addEntry("BOULE_FUMEE",m88());
i_.addEntry("BOUTON_FUITE",m89());
i_.addEntry("BRAC_MACHO",m90());
i_.addEntry("BULBE",m91());
i_.addEntry("CALCIUM",m92());
i_.addEntry("CARAPACE_MUE",m93());
i_.addEntry("CARBONE",m94());
i_.addEntry("CARTE_ROUGE",m95());
i_.addEntry("CASQUE_BRUT",m96());
i_.addEntry("CD_DOUTEUX",m97());
i_.addEntry("CEINTURE_PRO",m98());
i_.addEntry("CEINT_FORCE",m99());
i_.addEntry("CEINT_NOIRE",m100());
i_.addEntry("CEINT_POUV",m101());
i_.addEntry("CENDRESACREE",m102());
i_.addEntry("CHAINE_POUV",m103());
i_.addEntry("CHANTIBONBON",m104());
i_.addEntry("CHARBON",m105());
i_.addEntry("CHRONO_BALL",m106());
i_.addEntry("COMPET_BALL",m107());
i_.addEntry("COPAIN_BALL",m108());
i_.addEntry("CROC_DRAGON",m109());
i_.addEntry("CROC_RASOIR",m110());
i_.addEntry("CUILLERTORDU",m111());
i_.addEntry("DENT_OCEAN",m112());
i_.addEntry("EAU_FRAICHE",m113());
i_.addEntry("EAU_MYSTIQUE",m114());
i_.addEntry("ECAILLE_DRACO",m115());
i_.addEntry("ECAILLE_OCEAN",m116());
i_.addEntry("ELECTRISEUR",m117());
i_.addEntry("ELIXIR",m118());
i_.addEntry("ENCENS_DOUX",m119());
i_.addEntry("ENCENS_FLEUR",m120());
i_.addEntry("ENCENS_MER",m121());
i_.addEntry("ENCENS_PLEIN",m122());
i_.addEntry("ENCENS_PUR",m123());
i_.addEntry("ENCENS_ROC",m124());
i_.addEntry("ENCENS_VAGUE",m125());
i_.addEntry("ENCENS_VEINE",m126());
i_.addEntry("EVOLUROC",m127());
i_.addEntry("FAIBLO_BALL",m128());
i_.addEntry("FER",m129());
i_.addEntry("FERRAILLE",m130());
i_.addEntry("FILET_BALL",m131());
i_.addEntry("FOSSILE_ARMURE",m132());
i_.addEntry("FOSSILE_CRANE",m133());
i_.addEntry("FOSSILE_DOME",m134());
i_.addEntry("FOSSILE_GRIFFE",m135());
i_.addEntry("FOSSILE_PLAQUE",m136());
i_.addEntry("FOSSILE_PLUME",m137());
i_.addEntry("FOSSILE_RACINE",m138());
i_.addEntry("GLACETERNEL",m139());
i_.addEntry("GRAIN_MIRACL",m140());
i_.addEntry("GRANDE_PERLE",m141());
i_.addEntry("GRELOT_COQUE",m142());
i_.addEntry("GRELOT_ZEN",m143());
i_.addEntry("GRIFFE_RASOIR",m144());
i_.addEntry("GROSSERACINE",m145());
i_.addEntry("GROS_CHAMPI",m146());
i_.addEntry("GUERISON",m147());
i_.addEntry("HERBEBLANCHE",m148());
i_.addEntry("HERBE_MENTAL",m149());
i_.addEntry("HERBE_POUV",m150());
i_.addEntry("HONOR_BALL",m151());
i_.addEntry("HUILE",m152());
i_.addEntry("HUILE_MAX",m153());
i_.addEntry("HYPER_BALL",m154());
i_.addEntry("HYPER_POTION",m155());
i_.addEntry("JUS_DE_BAIE",m156());
i_.addEntry("LAIT_MEUMEU",m157());
i_.addEntry("LENTILSCOPE",m158());
i_.addEntry("LENTIL_ZOOM",m159());
i_.addEntry("LENT_POUV",m160());
i_.addEntry("LICHEN_LUMINEUX",m161());
i_.addEntry("LIMONADE",m162());
i_.addEntry("LOUPE",m163());
i_.addEntry("LOVE_BALL",m164());
i_.addEntry("LUMARGILE",m165());
i_.addEntry("LUNETTES_FILTRE",m166());
i_.addEntry("LUNET_CHOIX",m167());
i_.addEntry("LUNET_NOIRES",m168());
i_.addEntry("LUNET_SAGES",m169());
i_.addEntry("LUNE_BALL",m170());
i_.addEntry("LUXE_BALL",m171());
i_.addEntry("MAGMARISEUR",m172());
i_.addEntry("MASSE_BALL",m173());
i_.addEntry("MASSE_OS",m174());
i_.addEntry("MASTER_BALL",m175());
i_.addEntry("MAX_ELIXIR",m176());
i_.addEntry("MAX_REPOUSSE",m177());
i_.addEntry("METRO",m178());
i_.addEntry("MODULE_AQUA",m179());
i_.addEntry("MODULE_CHOC",m180());
i_.addEntry("MODULE_CRYO",m181());
i_.addEntry("MODULE_PYRO",m182());
i_.addEntry("MORC_ETOILE",m183());
i_.addEntry("MOUCH_CHOIX",m184());
i_.addEntry("MOUCH_SOIE",m185());
i_.addEntry("MULTI_EXP",m186());
i_.addEntry("NAUTILE",m187());
i_.addEntry("NIVEAU_BALL",m188());
i_.addEntry("NOEUD_DESTIN",m189());
i_.addEntry("OEUF_CHANCE",m190());
i_.addEntry("ORBE_ADAMANT",m191());
i_.addEntry("ORBE_FLAMME",m192());
i_.addEntry("ORBE_PERLE",m193());
i_.addEntry("ORBE_PLATINE",m194());
i_.addEntry("ORBE_TOXIQUE",m195());
i_.addEntry("ORBE_VIE",m196());
i_.addEntry("OS_RARE",m197());
i_.addEntry("PEAU_METAL",m198());
i_.addEntry("PEPITE",m199());
i_.addEntry("PERLE",m200());
i_.addEntry("PETIT_CHAMPI",m201());
i_.addEntry("PIC_VENIN",m202());
i_.addEntry("PIECE_RUNE",m203());
i_.addEntry("PIERRALLEGEE",m204());
i_.addEntry("PIERRE_AUBE",m205());
i_.addEntry("PIERRE_DURE",m206());
i_.addEntry("PIERRE_EAU",m207());
i_.addEntry("PIERRE_ECLAT",m208());
i_.addEntry("PIERRE_FEU",m209());
i_.addEntry("PIERRE_FOUDRE",m210());
i_.addEntry("PIERRE_GLACE",m211());
i_.addEntry("PIERRE_LUNE",m212());
i_.addEntry("PIERRE_MOUSSE",m213());
i_.addEntry("PIERRE_NUIT",m214());
i_.addEntry("PIERRE_OVALE",m215());
i_.addEntry("PIERRE_PLANTE",m216());
i_.addEntry("PIERRE_SOLAIRE",m217());
i_.addEntry("PIERRE_STASE",m218());
i_.addEntry("PILE",m219());
i_.addEntry("PIQUANTS",m220());
i_.addEntry("PLAQUESPRIT",m221());
i_.addEntry("PLAQUE_CIEL",m222());
i_.addEntry("PLAQUE_DRACO",m223());
i_.addEntry("PLAQUE_FANTO",m224());
i_.addEntry("PLAQUE_FEE",m225());
i_.addEntry("PLAQUE_FER",m226());
i_.addEntry("PLAQUE_FLAM",m227());
i_.addEntry("PLAQUE_GLACE",m228());
i_.addEntry("PLAQUE_HERBE",m229());
i_.addEntry("PLAQUE_HYDRO",m230());
i_.addEntry("PLAQUE_OMBRE",m231());
i_.addEntry("PLAQUE_POING",m232());
i_.addEntry("PLAQUE_ROC",m233());
i_.addEntry("PLAQUE_TERRE",m234());
i_.addEntry("PLAQUE_TOXIC",m235());
i_.addEntry("PLAQUE_VOLT",m236());
i_.addEntry("PLAQUINSECT",m237());
i_.addEntry("POIDS_POUV",m238());
i_.addEntry("POIGN_POUV",m239());
i_.addEntry("POING_CHANCE",m240());
i_.addEntry("POKE_BALL",m241());
i_.addEntry("POTION",m242());
i_.addEntry("POTION_MAX",m243());
i_.addEntry("POUDRECLAIRE",m244());
i_.addEntry("POUDRE_ARG",m245());
i_.addEntry("POUDRE_METAL",m246());
i_.addEntry("POUDRE_VITE",m247());
i_.addEntry("POUSS_ETOILE",m248());
i_.addEntry("PP_MAX",m249());
i_.addEntry("PP_PLUS",m250());
i_.addEntry("PROTECTEUR",m251());
i_.addEntry("PROTEINE",m252());
i_.addEntry("PT_DE_MIRE",m253());
i_.addEntry("PV_PLUS",m254());
i_.addEntry("RALENTIQUEUE",m255());
i_.addEntry("RAPIDE_BALL",m256());
i_.addEntry("RAPPEL",m257());
i_.addEntry("RAPPEL_MAX",m258());
i_.addEntry("REPOUSSE",m259());
i_.addEntry("RESTES",m260());
i_.addEntry("REVEIL",m261());
i_.addEntry("ROCHE_CHAUDE",m262());
i_.addEntry("ROCHE_ELECTRIQUE",m263());
i_.addEntry("ROCHE_GLACE",m264());
i_.addEntry("ROCHE_HUMIDE",m265());
i_.addEntry("ROCHE_LISSE",m266());
i_.addEntry("ROCHE_ROYALE",m267());
i_.addEntry("RUNE_PURIF",m268());
i_.addEntry("RUNE_SORT",m269());
i_.addEntry("SABLE_DOUX",m270());
i_.addEntry("SACHET_SENTEUR",m271());
i_.addEntry("SCUBA_BALL",m272());
i_.addEntry("SODA_COOL",m273());
i_.addEntry("SOIN_BALL",m274());
i_.addEntry("SOMBRE_BALL",m275());
i_.addEntry("SPEED_BALL",m276());
i_.addEntry("SUPER_BALL",m277());
i_.addEntry("SUPER_POTION",m278());
i_.addEntry("SUPER_REPOUSSE",m279());
i_.addEntry("TISSU_FAUCHE",m280());
i_.addEntry("TOTAL_SOIN",m281());
i_.addEntry("VESTE_DE_COMBAT",m282());
i_.addEntry("VIEIL_AMBRE",m283());
i_.addEntry("VIVE_GRIFFE",m284());
i_.addEntry("VULNE_ASSURANCE",m285());
i_.addEntry("ZINC",m286());
return i_;
}
static Item m0(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(7));
stringMapShort_.addEntry("VORTEX_MAGMA",(short)0);
stringMapShort_.addEntry("CLAQUOIR",(short)0);
stringMapShort_.addEntry("ETREINTE",(short)0);
stringMapShort_.addEntry("SIPHON",(short)0);
stringMapShort_.addEntry("LIGOTAGE",(short)0);
stringMapShort_.addEntry("TOURBI_SABLE",(short)0);
stringMapShort_.addEntry("DANSEFLAMME",(short)0);
itemForBattle_.setIncreasingMaxNbRoundTrap(stringMapShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m1(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{ELECTRIQUE}))+cardinal(complementaire({ELECTRIQUE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m2(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m3(){
HealingSimpleStatus healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
healingSimpleStatus_.setStatus(stringList_);
healingSimpleStatus_.setHealingKo(false);
healingSimpleStatus_.setPrice(100);
return healingSimpleStatus_;
}
static Item m4(){
HealingSimpleStatus healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GEL");
healingSimpleStatus_.setStatus(stringList_);
healingSimpleStatus_.setHealingKo(false);
healingSimpleStatus_.setPrice(250);
return healingSimpleStatus_;
}
static Item m5(){
HealingSimpleStatus healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("BRULURE");
healingSimpleStatus_.setStatus(stringList_);
healingSimpleStatus_.setHealingKo(false);
healingSimpleStatus_.setPrice(250);
return healingSimpleStatus_;
}
static Item m6(){
HealingSimpleStatus healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
healingSimpleStatus_.setStatus(stringList_);
healingSimpleStatus_.setHealingKo(false);
healingSimpleStatus_.setPrice(200);
return healingSimpleStatus_;
}
static Item m7(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("3*cardinal(inter({VAR__LIEU_COMBAT},{WATER}))+cardinal(complementaire({WATER},{VAR__LIEU_COMBAT}))");
ball_.setPrice(800);
return ball_;
}
static Item m8(){
Berry berry_=Instances.newBerry();
EnumMap<Statistic,BoostHpRate> enumMapStatisticBoostHpRate_=new EnumMap<Statistic,BoostHpRate>(new CollCapacity(1));
enumMapStatisticBoostHpRate_.addEntry(Statistic.SPECIAL_DEFENSE,new BoostHpRate((byte)1,Rate.newRate("1/3")));
berry_.setMultStat(enumMapStatisticBoostHpRate_);
berry_.setPrice(200);
return berry_;
}
static Item m9(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m10(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("ACIER",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m11(){
Berry berry_=Instances.newBerry();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
berry_.setHealStatus(stringList_);
berry_.setPrice(200);
return berry_;
}
static Item m12(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("ROCHE",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m13(){
Berry berry_=Instances.newBerry();
berry_.setLawForAttackFirst(true);
berry_.setPrice(200);
return berry_;
}
static Item m14(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("FEU",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m15(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("VOL",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m16(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m17(){
Berry berry_=Instances.newBerry();
berry_.setCategoryBoosting("PHYSIQUE");
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.DEFENSE,(byte)1);
berry_.setBoostStatis(enumMapStatisticByte_);
berry_.setPrice(0);
return berry_;
}
static Item m18(){
Berry berry_=Instances.newBerry();
berry_.setHealHpBySuperEffMove(Rate.newRate("1/4"));
berry_.setPrice(200);
return berry_;
}
static Item m19(){
Berry berry_=Instances.newBerry();
berry_.setHealHpRate(Rate.newRate("1/8"));
berry_.setMaxHpHealingHpRate(Rate.newRate("1/4"));
berry_.setPrice(200);
return berry_;
}
static Item m20(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("DRAGON",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m21(){
Berry berry_=Instances.newBerry();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("BRULURE");
berry_.setHealStatus(stringList_);
berry_.setPrice(200);
return berry_;
}
static Item m22(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m23(){
Berry berry_=Instances.newBerry();
EnumMap<Statistic,BoostHpRate> enumMapStatisticBoostHpRate_=new EnumMap<Statistic,BoostHpRate>(new CollCapacity(5));
enumMapStatisticBoostHpRate_.addEntry(Statistic.ATTACK,new BoostHpRate((byte)1,Rate.newRate("1/3")));
enumMapStatisticBoostHpRate_.addEntry(Statistic.SPECIAL_ATTACK,new BoostHpRate((byte)1,Rate.newRate("1/3")));
enumMapStatisticBoostHpRate_.addEntry(Statistic.SPECIAL_DEFENSE,new BoostHpRate((byte)1,Rate.newRate("1/3")));
enumMapStatisticBoostHpRate_.addEntry(Statistic.DEFENSE,new BoostHpRate((byte)1,Rate.newRate("1/3")));
enumMapStatisticBoostHpRate_.addEntry(Statistic.SPEED,new BoostHpRate((byte)1,Rate.newRate("1/3")));
berry_.setMultStat(enumMapStatisticBoostHpRate_);
berry_.setPrice(200);
return berry_;
}
static Item m24(){
Berry berry_=Instances.newBerry();
berry_.setHealHpRate(Rate.newRate("1/8"));
berry_.setMaxHpHealingHpRate(Rate.newRate("1/4"));
berry_.setPrice(200);
return berry_;
}
static Item m25(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m26(){
Berry berry_=Instances.newBerry();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("PHYSIQUE",Rate.newRate("1/8"));
berry_.setDamageRateRecoilFoe(stringMapRate_);
berry_.setPrice(200);
return berry_;
}
static Item m27(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("SOL",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m28(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("POISON",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m29(){
Berry berry_=Instances.newBerry();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("CONFUSION");
berry_.setHealStatus(stringList_);
berry_.setPrice(200);
return berry_;
}
static Item m30(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m31(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("TENEBRE",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m32(){
Berry berry_=Instances.newBerry();
EnumMap<Statistic,BoostHpRate> enumMapStatisticBoostHpRate_=new EnumMap<Statistic,BoostHpRate>(new CollCapacity(1));
enumMapStatisticBoostHpRate_.addEntry(Statistic.CRITICAL_HIT,new BoostHpRate((byte)1,Rate.newRate("1/3")));
berry_.setMultStat(enumMapStatisticBoostHpRate_);
berry_.setPrice(200);
return berry_;
}
static Item m33(){
Berry berry_=Instances.newBerry();
EnumMap<Statistic,BoostHpRate> enumMapStatisticBoostHpRate_=new EnumMap<Statistic,BoostHpRate>(new CollCapacity(1));
enumMapStatisticBoostHpRate_.addEntry(Statistic.ATTACK,new BoostHpRate((byte)1,Rate.newRate("1/3")));
berry_.setMultStat(enumMapStatisticBoostHpRate_);
berry_.setPrice(200);
return berry_;
}
static Item m34(){
Berry berry_=Instances.newBerry();
EnumMap<Statistic,BoostHpRate> enumMapStatisticBoostHpRate_=new EnumMap<Statistic,BoostHpRate>(new CollCapacity(1));
enumMapStatisticBoostHpRate_.addEntry(Statistic.DEFENSE,new BoostHpRate((byte)1,Rate.newRate("1/3")));
berry_.setMultStat(enumMapStatisticBoostHpRate_);
berry_.setPrice(200);
return berry_;
}
static Item m35(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m36(){
Berry berry_=Instances.newBerry();
berry_.setHealHpRate(Rate.newRate("1/8"));
berry_.setMaxHpHealingHpRate(Rate.newRate("1/4"));
berry_.setPrice(200);
return berry_;
}
static Item m37(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m38(){
Berry berry_=Instances.newBerry();
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
berry_.setHealStatus(stringList_);
berry_.setPrice(200);
return berry_;
}
static Item m39(){
Berry berry_=Instances.newBerry();
berry_.setHealPp(10);
berry_.setPrice(200);
return berry_;
}
static Item m40(){
Berry berry_=Instances.newBerry();
berry_.setWithoutFail(true);
berry_.setPrice(200);
return berry_;
}
static Item m41(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m42(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m43(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m44(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("GLACE",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m45(){
Berry berry_=Instances.newBerry();
berry_.setHealHp(Rate.newRate("10"));
berry_.setMaxHpHealingHp(Rate.newRate("1/4"));
berry_.setPrice(200);
return berry_;
}
static Item m46(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m47(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("INSECTE",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m48(){
Berry berry_=Instances.newBerry();
berry_.setHealHpRate(Rate.newRate("1/8"));
berry_.setMaxHpHealingHpRate(Rate.newRate("1/4"));
berry_.setPrice(200);
return berry_;
}
static Item m49(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("ELECTRIQUE",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m50(){
Berry berry_=Instances.newBerry();
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
berry_.setHealStatus(stringList_);
berry_.setPrice(200);
return berry_;
}
static Item m51(){
Berry berry_=Instances.newBerry();
EnumMap<Statistic,BoostHpRate> enumMapStatisticBoostHpRate_=new EnumMap<Statistic,BoostHpRate>(new CollCapacity(1));
enumMapStatisticBoostHpRate_.addEntry(Statistic.SPECIAL_ATTACK,new BoostHpRate((byte)1,Rate.newRate("1/3")));
berry_.setMultStat(enumMapStatisticBoostHpRate_);
berry_.setPrice(200);
return berry_;
}
static Item m52(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("EAU",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m53(){
Berry berry_=Instances.newBerry();
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
stringMapRate_.addEntry("SPECIALE",Rate.newRate("1/8"));
berry_.setDamageRateRecoilFoe(stringMapRate_);
berry_.setPrice(200);
return berry_;
}
static Item m54(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("COMBAT",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m55(){
Berry berry_=Instances.newBerry();
StringList stringList_=new StringList(new CollCapacity(8));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringList_.add("PARALYSIE");
stringList_.add("BRULURE");
stringList_.add("GEL");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringList_.add("CONFUSION");
berry_.setHealStatus(stringList_);
berry_.setPrice(200);
return berry_;
}
static Item m56(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m57(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m58(){
Berry berry_=Instances.newBerry();
berry_.setCategoryBoosting("SPECIALE");
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_DEFENSE,(byte)1);
berry_.setBoostStatis(enumMapStatisticByte_);
berry_.setPrice(0);
return berry_;
}
static Item m59(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("PLANTE",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m60(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m61(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m62(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m63(){
Berry berry_=Instances.newBerry();
EnumMap<Statistic,BoostHpRate> enumMapStatisticBoostHpRate_=new EnumMap<Statistic,BoostHpRate>(new CollCapacity(1));
enumMapStatisticBoostHpRate_.addEntry(Statistic.SPEED,new BoostHpRate((byte)1,Rate.newRate("1/3")));
berry_.setMultStat(enumMapStatisticBoostHpRate_);
berry_.setPrice(200);
return berry_;
}
static Item m64(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("SPECTRE",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m65(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("FEE",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(0);
return berry_;
}
static Item m66(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m67(){
Berry berry_=Instances.newBerry();
berry_.setHealHp(Rate.newRate("30"));
berry_.setMaxHpHealingHp(Rate.newRate("1/4"));
berry_.setPrice(200);
return berry_;
}
static Item m68(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m69(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m70(){
Berry berry_=Instances.newBerry();
berry_.setPrice(200);
return berry_;
}
static Item m71(){
Berry berry_=Instances.newBerry();
berry_.setHealHpRate(Rate.newRate("1/8"));
berry_.setMaxHpHealingHpRate(Rate.newRate("1/4"));
berry_.setPrice(200);
return berry_;
}
static Item m72(){
Berry berry_=Instances.newBerry();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GEL");
berry_.setHealStatus(stringList_);
berry_.setPrice(200);
return berry_;
}
static Item m73(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("PSY",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m74(){
Berry berry_=Instances.newBerry();
StringMap<EfficiencyRate> stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
stringMapEfficiencyRate_.addEntry("NORMAL",new EfficiencyRate(Rate.newRate("1"),Rate.newRate("1/2")));
berry_.setMultFoesDamage(stringMapEfficiencyRate_);
berry_.setPrice(200);
return berry_;
}
static Item m75(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"1/2");
itemForBattle_.setMultStat(enumMapStatisticString_);
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setMultWeight(Rate.newRate("2"));
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
itemForBattle_.setEffectSending(custListEffectWhileSendingWithStatistic_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m76(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOL");
itemForBattle_.setImmuTypes(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m77(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setProtectAgainstKo(Rate.newRate("1"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m78(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultTrappingDamage(Rate.newRate("2"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m79(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"3/2");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m80(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("11/10*cardinal(inter({VAR__ATTAQUE_CATEGORIE},{PHYSIQUE}))+cardinal(complementaire({PHYSIQUE},{VAR__ATTAQUE_CATEGORIE}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m81(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.SPECIAL_DEFENSE,(short)4);
itemForBattle_.setWinEvFight(enumMapStatisticShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m82(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StatisticPokemons objectMapStatisticPokemonByte_=new StatisticPokemons(new CollCapacity(1));
objectMapStatisticPokemonByte_.addEntry(new StatisticPokemon(Statistic.CRITICAL_HIT,"CANARTICHO"),(byte)2);
itemForBattle_.setMultStatPokemonRank(objectMapStatisticPokemonByte_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m83(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{VOL}))+cardinal(complementaire({VOL},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m84(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("2*VAR__DEJA_CAPTURE+1");
ball_.setPrice(800);
return ball_;
}
static Item m85(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("11/10*cardinal(inter({VAR__ATTAQUE_TYPES},{PSY}))+cardinal(complementaire({PSY},{VAR__ATTAQUE_TYPES}))");
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("MIME_JR");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m86(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate("0"));
effectEndRoundIndividual_.setRecoilDamage(Rate.newRate("0"));
effectEndRoundIndividual_.setHealHp(Rate.newRate("0"));
StringMap<Rate> stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
stringMapRate_.addEntry("",Rate.newRate("-1/8"));
stringMapRate_.addEntry("POISON",Rate.newRate("1/8"));
effectEndRoundIndividual_.setHealHpByOwnerTypes(stringMapRate_);
effectEndRoundIndividual_.setUserStatusEndRound("");
effectEndRoundIndividual_.setFailEndRound("");
effectEndRoundIndividual_.setEndRoundRank(40);
effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
effectEndRoundIndividual_.setFail("");
custListEffectEndRound_.add(effectEndRoundIndividual_);
itemForBattle_.setEffectEndRound(custListEffectEndRound_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m87(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<EnumMap<Statistic,Byte>> stringMapEnumMapStatisticByte_=new StringMap<EnumMap<Statistic,Byte>>(new CollCapacity(1));
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
stringMapEnumMapStatisticByte_.addEntry("GLACE",enumMapStatisticByte_);
itemForBattle_.setBoostStatisTypes(stringMapEnumMapStatisticByte_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m88(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m89(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m90(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultWinningEv(Rate.newRate("2"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m91(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m92(){
Boost boost_=Instances.newBoost();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)2);
stringMapShort_.addEntry("SCUBA_BALL",(short)2);
stringMapShort_.addEntry("HONOR_BALL",(short)2);
stringMapShort_.addEntry("BIS_BALL",(short)2);
stringMapShort_.addEntry("LUNE_BALL",(short)2);
stringMapShort_.addEntry("SPEED_BALL",(short)2);
stringMapShort_.addEntry("LUXE_BALL",(short)6);
stringMapShort_.addEntry("SOIN_BALL",(short)2);
stringMapShort_.addEntry("MASTER_BALL",(short)2);
stringMapShort_.addEntry("CHRONO_BALL",(short)2);
stringMapShort_.addEntry("POKE_BALL",(short)2);
stringMapShort_.addEntry("SUPER_BALL",(short)2);
stringMapShort_.addEntry("RAPIDE_BALL",(short)2);
stringMapShort_.addEntry("NIVEAU_BALL",(short)2);
stringMapShort_.addEntry("SOMBRE_BALL",(short)2);
stringMapShort_.addEntry("HYPER_BALL",(short)2);
stringMapShort_.addEntry("MASSE_BALL",(short)2);
stringMapShort_.addEntry("LOVE_BALL",(short)2);
stringMapShort_.addEntry("COMPET_BALL",(short)2);
stringMapShort_.addEntry("APPAT_BALL",(short)2);
stringMapShort_.addEntry("COPAIN_BALL",(short)2);
stringMapShort_.addEntry("FILET_BALL",(short)2);
boost_.setHappiness(stringMapShort_);
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.SPECIAL_ATTACK,(short)10);
boost_.setEvs(enumMapStatisticShort_);
boost_.setPrice(9800);
return boost_;
}
static Item m93(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m94(){
Boost boost_=Instances.newBoost();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)2);
stringMapShort_.addEntry("SCUBA_BALL",(short)2);
stringMapShort_.addEntry("HONOR_BALL",(short)2);
stringMapShort_.addEntry("BIS_BALL",(short)2);
stringMapShort_.addEntry("LUNE_BALL",(short)2);
stringMapShort_.addEntry("SPEED_BALL",(short)2);
stringMapShort_.addEntry("LUXE_BALL",(short)6);
stringMapShort_.addEntry("SOIN_BALL",(short)2);
stringMapShort_.addEntry("MASTER_BALL",(short)2);
stringMapShort_.addEntry("CHRONO_BALL",(short)2);
stringMapShort_.addEntry("POKE_BALL",(short)2);
stringMapShort_.addEntry("SUPER_BALL",(short)2);
stringMapShort_.addEntry("RAPIDE_BALL",(short)2);
stringMapShort_.addEntry("NIVEAU_BALL",(short)2);
stringMapShort_.addEntry("SOMBRE_BALL",(short)2);
stringMapShort_.addEntry("HYPER_BALL",(short)2);
stringMapShort_.addEntry("MASSE_BALL",(short)2);
stringMapShort_.addEntry("LOVE_BALL",(short)2);
stringMapShort_.addEntry("COMPET_BALL",(short)2);
stringMapShort_.addEntry("APPAT_BALL",(short)2);
stringMapShort_.addEntry("COPAIN_BALL",(short)2);
stringMapShort_.addEntry("FILET_BALL",(short)2);
boost_.setHappiness(stringMapShort_);
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.SPEED,(short)10);
boost_.setEvs(enumMapStatisticShort_);
boost_.setPrice(9800);
return boost_;
}
static Item m95(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m96(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setDamageRecoil(Rate.newRate("1/6"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m97(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m98(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultDamage("1/5*caracdroiteouvert(VAR__COEFF_EFF,1)+1");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m99(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setProtectAgainstKoIfFullHp(Rate.newRate("1"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m100(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{COMBAT}))+cardinal(complementaire({COMBAT},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m101(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.DEFENSE,(short)4);
itemForBattle_.setWinEvFight(enumMapStatisticShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m102(){
HealingSimpleItem healingSimpleItem_ =Instances.newHealingSimpleItem();
healingSimpleItem_.setHealingTeam(true);
healingSimpleItem_.setPrice(20000);
return healingSimpleItem_;
}
static Item m103(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.SPEED,(short)4);
itemForBattle_.setWinEvFight(enumMapStatisticShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m104(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1);
return evolvingItem_;
}
static Item m105(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{FEU}))+cardinal(complementaire({FEU},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m106(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("caracferme(VAR__TEMPS_TOUR,0,3)+3*caracferme(VAR__TEMPS_TOUR,4,7)+6*caracferme(VAR__TEMPS_TOUR,8,11)+10*caracferme(VAR__TEMPS_TOUR,12,14)+25*caracdroiteferme(VAR__TEMPS_TOUR,15)");
ball_.setPrice(900);
return ball_;
}
static Item m107(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("1");
ball_.setPrice(600);
return ball_;
}
static Item m108(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("1");
ball_.setPrice(800);
return ball_;
}
static Item m109(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{DRAGON}))+cardinal(complementaire({DRAGON},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m110(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m111(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{PSY}))+cardinal(complementaire({PSY},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m112(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m113(){
HealingHp healingHp_ =Instances.newHealingHp();
healingHp_.setHp(Rate.newRate("50"));
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)1);
stringMapShort_.addEntry("SCUBA_BALL",(short)1);
stringMapShort_.addEntry("HONOR_BALL",(short)1);
stringMapShort_.addEntry("BIS_BALL",(short)1);
stringMapShort_.addEntry("LUNE_BALL",(short)1);
stringMapShort_.addEntry("SPEED_BALL",(short)1);
stringMapShort_.addEntry("LUXE_BALL",(short)2);
stringMapShort_.addEntry("SOIN_BALL",(short)1);
stringMapShort_.addEntry("MASTER_BALL",(short)1);
stringMapShort_.addEntry("CHRONO_BALL",(short)1);
stringMapShort_.addEntry("POKE_BALL",(short)1);
stringMapShort_.addEntry("SUPER_BALL",(short)1);
stringMapShort_.addEntry("RAPIDE_BALL",(short)1);
stringMapShort_.addEntry("NIVEAU_BALL",(short)1);
stringMapShort_.addEntry("SOMBRE_BALL",(short)1);
stringMapShort_.addEntry("HYPER_BALL",(short)1);
stringMapShort_.addEntry("MASSE_BALL",(short)1);
stringMapShort_.addEntry("LOVE_BALL",(short)1);
stringMapShort_.addEntry("COMPET_BALL",(short)1);
stringMapShort_.addEntry("APPAT_BALL",(short)1);
stringMapShort_.addEntry("COPAIN_BALL",(short)1);
stringMapShort_.addEntry("FILET_BALL",(short)1);
healingHp_.setHappiness(stringMapShort_);
healingHp_.setPrice(200);
return healingHp_;
}
static Item m114(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{EAU}))+cardinal(complementaire({EAU},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m115(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m116(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m117(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m118(){
HealingPp healingPp_ =Instances.newHealingPp();
healingPp_.setHealingAllMovesFullpp(10);
healingPp_.setPrice(1000);
return healingPp_;
}
static Item m119(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.EVASINESS,"21/20");
itemForBattle_.setMultStat(enumMapStatisticString_);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("OKEOKE");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m120(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("11/10*cardinal(inter({VAR__ATTAQUE_TYPES},{PLANTE}))+cardinal(complementaire({PLANTE},{VAR__ATTAQUE_TYPES}))");
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ROZBOUTON");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m121(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("11/10*cardinal(inter({VAR__ATTAQUE_TYPES},{EAU}))+cardinal(complementaire({EAU},{VAR__ATTAQUE_TYPES}))");
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("AZURILL");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m122(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setAttackLast(true);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GOINFREX");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m123(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("KORILLON");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m124(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("11/10*cardinal(inter({VAR__ATTAQUE_TYPES},{ROCHE}))+cardinal(complementaire({ROCHE},{VAR__ATTAQUE_TYPES}))");
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("MANZAI");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m125(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("11/10*cardinal(inter({VAR__ATTAQUE_TYPES},{EAU}))+cardinal(complementaire({EAU},{VAR__ATTAQUE_TYPES}))");
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("BABIMANTA");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m126(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PTIRAVI");
itemForBattle_.setHatching(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m127(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(2));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,"3/2*cardinal(inter({VAR__FIGHTER_NOM},{ABO;ABRA;ABSOL;AFLAMANOIR;AIKIVOL;AIRMURE;AMONITA;ANCHWATT;ANORITH;APITRINI;ARAKDO;ARCEUS;ARCHEOMIRE;ARCKO;ARKEAPTI;ARTIKODIN;ASPICOT;AXOLOTO;AZURILL;BABIMANTA;BAGGIGUANE;BALBUTO;BALIGNON;BARGANTUA;BARLOCHE;BARPAU;BAUDRIVE;BLIZZI;BOREAS;BULBIZARRE;CACNEA;CADOIZO;CANARTICHO;CANINOS;CAPUMAIN;CARABING;CARAPAGOS;CARAPUCE;CARATROC;CARVANHA;CELEBI;CERFROUSSE;CERIBOU;CHACRIPAN;CHAGLAM;CHAMALLOT;CHARPENTI;CHARTOR;CHENIPAN;CHENIPOTTE;CHENITI;CHETIFLOR;CHINCHIDOU;CHLOROBULE;CHOVSOURIR;CHUCHMUR;COBALTIUM;COQUIPERL;CORAYON;CORNEBRE;COUANETON;COUPENOTTE;COXY;CRABICOQUE;CRADOPAUD;CREFADET;CREFOLLET;CREHELF;CRESSELIA;CRIKZIK;CRYPTERO;DARKRAI;DARUMAROND;DEBUGANT;DEMETEROS;DEOXYS;DIALGA;DINOCLIER;DODUO;DOUDOUVET;DRABY;DRAKKARMIN;DYNAVOLT;ECAYON;ECRAPINCE;ECREMEUH;ELECTHOR;ELEKID;EMBRYLEX;EMOLGA;ENTEI;ESCARGAUME;ETOURMI;EVOLI;EXCELANGUE;FANTOMINUS;FARFURET;FERMITE;FEROSINGE;FEUFOREVE;FEUILLAJOU;FLAMAJOU;FLOTAJOU;FOUINETTE;FRISON;FULGURIS;FUNECIRE;FURAIGLON;GALEKID;GENESECT;GERMIGNON;GIRAFARIG;GIRATINA;GLOUPTI;GOBOU;GOELISE;GOINFREX;GOUPIX;GRAINIPIOT;GRANIVOL;GRIKNOT;GRINDUR;GRINGOLEM;GROUDON;GRUIKUI;HEATRAN;HERICENDRE;HEXAGEL;HIPPOPOTAS;HOOTHOOT;HO_OH;HYPOTREMPE;INSECATEUR;INSOLOURDO;JIRACHI;JUDOKRAK;KABUTO;KAIMINUS;KANGOUREX;KARACLEE;KECLEON;KELDEO;KEUNOTOR;KICKLEE;KOKIYAS;KORILLON;KRABBY;KRAKNOIX;KRANIDOS;KUNGFOUINE;KYOGRE;KYUREM;LAPOREILLE;LARVEYETTE;LATIAS;LATIOS;LEWSOR;LILIA;LIMAGMA;LIMONDE;LIPPOUTI;LIXY;LOKHLASS;LOUPIO;LOVDISC;LUGIA;LUMIVOLE;MACHOC;MAGBY;MAGICARPE;MAGNETI;MAKUHITA;MALOSSE;MAMANBO;MANAPHY;MANGRIFF;MANZAI;MARACACHI;MARCACRIN;MASCAIMAN;MEDHYENA;MEDITIKKA;MELO;MELOETTA;METAMORPH;MEW;MEWTWO;MIAMIASME;MIAOUSS;MIME_JR;MIMIGAL;MIMITOSS;MINIDRACO;MORPHEO;MOTISMA;MOUFOUETTE;MOUSTILLON;MUCIOLE;MUNNA;MUSTEBOUEE;MYSDIBULE;MYSTHERBE;NANMEOUIE;NATU;NEGAPI;NENUPIOT;NIDORAN_F;NIDORAN_M;NINGALE;NIRONDELLE;NODULITHE;NOEUNOEUF;NOSFERAPTI;NUCLEOS;OBALIE;OKEOKE;ONIX;OSSELAIT;OTARIA;OUISTICRAM;PACHIRISU;PALKIA;PARAS;PARECOOL;PHANPY;PHIONE;PIAFABEC;PICHU;PIJAKO;POICHIGEON;POISSIRENE;POLARHUME;POLICHOMBR;POMDEPIK;PONCHIOT;PONYTA;PORYGON;POSIPI;POUSSIFEU;PSYKOKWAK;PTERA;PTIRAVI;PTITARD;PYRONILLE;QUEULORIOR;QWILFISH;RACAILLOU;RAIKOU;RAMOLOSS;RAPION;RATENTIF;RATTATA;RAYQUAZA;REGICE;REGIGIGAS;REGIROCK;REGISTEEL;RELICANTH;REMORAID;RESHIRAM;RHINOCORNE;RIOLU;ROTOTAUPE;ROUCOOL;ROZBOUTON;SABELETTE;SALAMECHE;SANCOKI;SAQUEDENEU;SCALPION;SCARABRUTE;SCARHINO;SCORPLANE;SCRUTELLA;SELEROC;SEVIPER;SHAYMIN;SKELENOX;SKITTY;SMOGO;SNUBBULL;SOLAROC;SOLOCHI;SOPORIFIK;SORBEBE;SPINDA;SPIRITOMB;SPOINK;STALGAMIN;STARI;STATITIK;SUICUNE;SULFURA;TADMORV;TARINOR;TARSAL;TAUPIQUEUR;TAUROS;TEDDIURSA;TENEFIX;TENTACOOL;TERHAL;TERRAKIUM;TIC;TIPLOUF;TOGEPI;TORTIPOUSS;TOUDOUDOU;TOURNEGRIN;TRITONDE;TROMPIGNON;TROPIUS;TUTAFEH;TYGNON;TYLTON;VENIPATTE;VICTINI;VIPELIERRE;VIRIDIUM;VISKUSE;VIVALDAIM;VOLTORBE;VORTENTE;VOSTOURNO;WAILMER;WATTOUAT;YANMA;ZARBI;ZEBIBRON;ZEKROM;ZIGZATON;ZORUA}))+cardinal(complementaire({ABO;ABRA;ABSOL;AFLAMANOIR;AIKIVOL;AIRMURE;AMONITA;ANCHWATT;ANORITH;APITRINI;ARAKDO;ARCEUS;ARCHEOMIRE;ARCKO;ARKEAPTI;ARTIKODIN;ASPICOT;AXOLOTO;AZURILL;BABIMANTA;BAGGIGUANE;BALBUTO;BALIGNON;BARGANTUA;BARLOCHE;BARPAU;BAUDRIVE;BLIZZI;BOREAS;BULBIZARRE;CACNEA;CADOIZO;CANARTICHO;CANINOS;CAPUMAIN;CARABING;CARAPAGOS;CARAPUCE;CARATROC;CARVANHA;CELEBI;CERFROUSSE;CERIBOU;CHACRIPAN;CHAGLAM;CHAMALLOT;CHARPENTI;CHARTOR;CHENIPAN;CHENIPOTTE;CHENITI;CHETIFLOR;CHINCHIDOU;CHLOROBULE;CHOVSOURIR;CHUCHMUR;COBALTIUM;COQUIPERL;CORAYON;CORNEBRE;COUANETON;COUPENOTTE;COXY;CRABICOQUE;CRADOPAUD;CREFADET;CREFOLLET;CREHELF;CRESSELIA;CRIKZIK;CRYPTERO;DARKRAI;DARUMAROND;DEBUGANT;DEMETEROS;DEOXYS;DIALGA;DINOCLIER;DODUO;DOUDOUVET;DRABY;DRAKKARMIN;DYNAVOLT;ECAYON;ECRAPINCE;ECREMEUH;ELECTHOR;ELEKID;EMBRYLEX;EMOLGA;ENTEI;ESCARGAUME;ETOURMI;EVOLI;EXCELANGUE;FANTOMINUS;FARFURET;FERMITE;FEROSINGE;FEUFOREVE;FEUILLAJOU;FLAMAJOU;FLOTAJOU;FOUINETTE;FRISON;FULGURIS;FUNECIRE;FURAIGLON;GALEKID;GENESECT;GERMIGNON;GIRAFARIG;GIRATINA;GLOUPTI;GOBOU;GOELISE;GOINFREX;GOUPIX;GRAINIPIOT;GRANIVOL;GRIKNOT;GRINDUR;GRINGOLEM;GROUDON;GRUIKUI;HEATRAN;HERICENDRE;HEXAGEL;HIPPOPOTAS;HOOTHOOT;HO_OH;HYPOTREMPE;INSECATEUR;INSOLOURDO;JIRACHI;JUDOKRAK;KABUTO;KAIMINUS;KANGOUREX;KARACLEE;KECLEON;KELDEO;KEUNOTOR;KICKLEE;KOKIYAS;KORILLON;KRABBY;KRAKNOIX;KRANIDOS;KUNGFOUINE;KYOGRE;KYUREM;LAPOREILLE;LARVEYETTE;LATIAS;LATIOS;LEWSOR;LILIA;LIMAGMA;LIMONDE;LIPPOUTI;LIXY;LOKHLASS;LOUPIO;LOVDISC;LUGIA;LUMIVOLE;MACHOC;MAGBY;MAGICARPE;MAGNETI;MAKUHITA;MALOSSE;MAMANBO;MANAPHY;MANGRIFF;MANZAI;MARACACHI;MARCACRIN;MASCAIMAN;MEDHYENA;MEDITIKKA;MELO;MELOETTA;METAMORPH;MEW;MEWTWO;MIAMIASME;MIAOUSS;MIME_JR;MIMIGAL;MIMITOSS;MINIDRACO;MORPHEO;MOTISMA;MOUFOUETTE;MOUSTILLON;MUCIOLE;MUNNA;MUSTEBOUEE;MYSDIBULE;MYSTHERBE;NANMEOUIE;NATU;NEGAPI;NENUPIOT;NIDORAN_F;NIDORAN_M;NINGALE;NIRONDELLE;NODULITHE;NOEUNOEUF;NOSFERAPTI;NUCLEOS;OBALIE;OKEOKE;ONIX;OSSELAIT;OTARIA;OUISTICRAM;PACHIRISU;PALKIA;PARAS;PARECOOL;PHANPY;PHIONE;PIAFABEC;PICHU;PIJAKO;POICHIGEON;POISSIRENE;POLARHUME;POLICHOMBR;POMDEPIK;PONCHIOT;PONYTA;PORYGON;POSIPI;POUSSIFEU;PSYKOKWAK;PTERA;PTIRAVI;PTITARD;PYRONILLE;QUEULORIOR;QWILFISH;RACAILLOU;RAIKOU;RAMOLOSS;RAPION;RATENTIF;RATTATA;RAYQUAZA;REGICE;REGIGIGAS;REGIROCK;REGISTEEL;RELICANTH;REMORAID;RESHIRAM;RHINOCORNE;RIOLU;ROTOTAUPE;ROUCOOL;ROZBOUTON;SABELETTE;SALAMECHE;SANCOKI;SAQUEDENEU;SCALPION;SCARABRUTE;SCARHINO;SCORPLANE;SCRUTELLA;SELEROC;SEVIPER;SHAYMIN;SKELENOX;SKITTY;SMOGO;SNUBBULL;SOLAROC;SOLOCHI;SOPORIFIK;SORBEBE;SPINDA;SPIRITOMB;SPOINK;STALGAMIN;STARI;STATITIK;SUICUNE;SULFURA;TADMORV;TARINOR;TARSAL;TAUPIQUEUR;TAUROS;TEDDIURSA;TENEFIX;TENTACOOL;TERHAL;TERRAKIUM;TIC;TIPLOUF;TOGEPI;TORTIPOUSS;TOUDOUDOU;TOURNEGRIN;TRITONDE;TROMPIGNON;TROPIUS;TUTAFEH;TYGNON;TYLTON;VENIPATTE;VICTINI;VIPELIERRE;VIRIDIUM;VISKUSE;VIVALDAIM;VOLTORBE;VORTENTE;VOSTOURNO;WAILMER;WATTOUAT;YANMA;ZARBI;ZEBIBRON;ZEKROM;ZIGZATON;ZORUA},{VAR__FIGHTER_NOM}))");
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"3/2*cardinal(inter({VAR__FIGHTER_NOM},{ABO;ABRA;ABSOL;AFLAMANOIR;AIKIVOL;AIRMURE;AMONITA;ANCHWATT;ANORITH;APITRINI;ARAKDO;ARCEUS;ARCHEOMIRE;ARCKO;ARKEAPTI;ARTIKODIN;ASPICOT;AXOLOTO;AZURILL;BABIMANTA;BAGGIGUANE;BALBUTO;BALIGNON;BARGANTUA;BARLOCHE;BARPAU;BAUDRIVE;BLIZZI;BOREAS;BULBIZARRE;CACNEA;CADOIZO;CANARTICHO;CANINOS;CAPUMAIN;CARABING;CARAPAGOS;CARAPUCE;CARATROC;CARVANHA;CELEBI;CERFROUSSE;CERIBOU;CHACRIPAN;CHAGLAM;CHAMALLOT;CHARPENTI;CHARTOR;CHENIPAN;CHENIPOTTE;CHENITI;CHETIFLOR;CHINCHIDOU;CHLOROBULE;CHOVSOURIR;CHUCHMUR;COBALTIUM;COQUIPERL;CORAYON;CORNEBRE;COUANETON;COUPENOTTE;COXY;CRABICOQUE;CRADOPAUD;CREFADET;CREFOLLET;CREHELF;CRESSELIA;CRIKZIK;CRYPTERO;DARKRAI;DARUMAROND;DEBUGANT;DEMETEROS;DEOXYS;DIALGA;DINOCLIER;DODUO;DOUDOUVET;DRABY;DRAKKARMIN;DYNAVOLT;ECAYON;ECRAPINCE;ECREMEUH;ELECTHOR;ELEKID;EMBRYLEX;EMOLGA;ENTEI;ESCARGAUME;ETOURMI;EVOLI;EXCELANGUE;FANTOMINUS;FARFURET;FERMITE;FEROSINGE;FEUFOREVE;FEUILLAJOU;FLAMAJOU;FLOTAJOU;FOUINETTE;FRISON;FULGURIS;FUNECIRE;FURAIGLON;GALEKID;GENESECT;GERMIGNON;GIRAFARIG;GIRATINA;GLOUPTI;GOBOU;GOELISE;GOINFREX;GOUPIX;GRAINIPIOT;GRANIVOL;GRIKNOT;GRINDUR;GRINGOLEM;GROUDON;GRUIKUI;HEATRAN;HERICENDRE;HEXAGEL;HIPPOPOTAS;HOOTHOOT;HO_OH;HYPOTREMPE;INSECATEUR;INSOLOURDO;JIRACHI;JUDOKRAK;KABUTO;KAIMINUS;KANGOUREX;KARACLEE;KECLEON;KELDEO;KEUNOTOR;KICKLEE;KOKIYAS;KORILLON;KRABBY;KRAKNOIX;KRANIDOS;KUNGFOUINE;KYOGRE;KYUREM;LAPOREILLE;LARVEYETTE;LATIAS;LATIOS;LEWSOR;LILIA;LIMAGMA;LIMONDE;LIPPOUTI;LIXY;LOKHLASS;LOUPIO;LOVDISC;LUGIA;LUMIVOLE;MACHOC;MAGBY;MAGICARPE;MAGNETI;MAKUHITA;MALOSSE;MAMANBO;MANAPHY;MANGRIFF;MANZAI;MARACACHI;MARCACRIN;MASCAIMAN;MEDHYENA;MEDITIKKA;MELO;MELOETTA;METAMORPH;MEW;MEWTWO;MIAMIASME;MIAOUSS;MIME_JR;MIMIGAL;MIMITOSS;MINIDRACO;MORPHEO;MOTISMA;MOUFOUETTE;MOUSTILLON;MUCIOLE;MUNNA;MUSTEBOUEE;MYSDIBULE;MYSTHERBE;NANMEOUIE;NATU;NEGAPI;NENUPIOT;NIDORAN_F;NIDORAN_M;NINGALE;NIRONDELLE;NODULITHE;NOEUNOEUF;NOSFERAPTI;NUCLEOS;OBALIE;OKEOKE;ONIX;OSSELAIT;OTARIA;OUISTICRAM;PACHIRISU;PALKIA;PARAS;PARECOOL;PHANPY;PHIONE;PIAFABEC;PICHU;PIJAKO;POICHIGEON;POISSIRENE;POLARHUME;POLICHOMBR;POMDEPIK;PONCHIOT;PONYTA;PORYGON;POSIPI;POUSSIFEU;PSYKOKWAK;PTERA;PTIRAVI;PTITARD;PYRONILLE;QUEULORIOR;QWILFISH;RACAILLOU;RAIKOU;RAMOLOSS;RAPION;RATENTIF;RATTATA;RAYQUAZA;REGICE;REGIGIGAS;REGIROCK;REGISTEEL;RELICANTH;REMORAID;RESHIRAM;RHINOCORNE;RIOLU;ROTOTAUPE;ROUCOOL;ROZBOUTON;SABELETTE;SALAMECHE;SANCOKI;SAQUEDENEU;SCALPION;SCARABRUTE;SCARHINO;SCORPLANE;SCRUTELLA;SELEROC;SEVIPER;SHAYMIN;SKELENOX;SKITTY;SMOGO;SNUBBULL;SOLAROC;SOLOCHI;SOPORIFIK;SORBEBE;SPINDA;SPIRITOMB;SPOINK;STALGAMIN;STARI;STATITIK;SUICUNE;SULFURA;TADMORV;TARINOR;TARSAL;TAUPIQUEUR;TAUROS;TEDDIURSA;TENEFIX;TENTACOOL;TERHAL;TERRAKIUM;TIC;TIPLOUF;TOGEPI;TORTIPOUSS;TOUDOUDOU;TOURNEGRIN;TRITONDE;TROMPIGNON;TROPIUS;TUTAFEH;TYGNON;TYLTON;VENIPATTE;VICTINI;VIPELIERRE;VIRIDIUM;VISKUSE;VIVALDAIM;VOLTORBE;VORTENTE;VOSTOURNO;WAILMER;WATTOUAT;YANMA;ZARBI;ZEBIBRON;ZEKROM;ZIGZATON;ZORUA}))+cardinal(complementaire({ABO;ABRA;ABSOL;AFLAMANOIR;AIKIVOL;AIRMURE;AMONITA;ANCHWATT;ANORITH;APITRINI;ARAKDO;ARCEUS;ARCHEOMIRE;ARCKO;ARKEAPTI;ARTIKODIN;ASPICOT;AXOLOTO;AZURILL;BABIMANTA;BAGGIGUANE;BALBUTO;BALIGNON;BARGANTUA;BARLOCHE;BARPAU;BAUDRIVE;BLIZZI;BOREAS;BULBIZARRE;CACNEA;CADOIZO;CANARTICHO;CANINOS;CAPUMAIN;CARABING;CARAPAGOS;CARAPUCE;CARATROC;CARVANHA;CELEBI;CERFROUSSE;CERIBOU;CHACRIPAN;CHAGLAM;CHAMALLOT;CHARPENTI;CHARTOR;CHENIPAN;CHENIPOTTE;CHENITI;CHETIFLOR;CHINCHIDOU;CHLOROBULE;CHOVSOURIR;CHUCHMUR;COBALTIUM;COQUIPERL;CORAYON;CORNEBRE;COUANETON;COUPENOTTE;COXY;CRABICOQUE;CRADOPAUD;CREFADET;CREFOLLET;CREHELF;CRESSELIA;CRIKZIK;CRYPTERO;DARKRAI;DARUMAROND;DEBUGANT;DEMETEROS;DEOXYS;DIALGA;DINOCLIER;DODUO;DOUDOUVET;DRABY;DRAKKARMIN;DYNAVOLT;ECAYON;ECRAPINCE;ECREMEUH;ELECTHOR;ELEKID;EMBRYLEX;EMOLGA;ENTEI;ESCARGAUME;ETOURMI;EVOLI;EXCELANGUE;FANTOMINUS;FARFURET;FERMITE;FEROSINGE;FEUFOREVE;FEUILLAJOU;FLAMAJOU;FLOTAJOU;FOUINETTE;FRISON;FULGURIS;FUNECIRE;FURAIGLON;GALEKID;GENESECT;GERMIGNON;GIRAFARIG;GIRATINA;GLOUPTI;GOBOU;GOELISE;GOINFREX;GOUPIX;GRAINIPIOT;GRANIVOL;GRIKNOT;GRINDUR;GRINGOLEM;GROUDON;GRUIKUI;HEATRAN;HERICENDRE;HEXAGEL;HIPPOPOTAS;HOOTHOOT;HO_OH;HYPOTREMPE;INSECATEUR;INSOLOURDO;JIRACHI;JUDOKRAK;KABUTO;KAIMINUS;KANGOUREX;KARACLEE;KECLEON;KELDEO;KEUNOTOR;KICKLEE;KOKIYAS;KORILLON;KRABBY;KRAKNOIX;KRANIDOS;KUNGFOUINE;KYOGRE;KYUREM;LAPOREILLE;LARVEYETTE;LATIAS;LATIOS;LEWSOR;LILIA;LIMAGMA;LIMONDE;LIPPOUTI;LIXY;LOKHLASS;LOUPIO;LOVDISC;LUGIA;LUMIVOLE;MACHOC;MAGBY;MAGICARPE;MAGNETI;MAKUHITA;MALOSSE;MAMANBO;MANAPHY;MANGRIFF;MANZAI;MARACACHI;MARCACRIN;MASCAIMAN;MEDHYENA;MEDITIKKA;MELO;MELOETTA;METAMORPH;MEW;MEWTWO;MIAMIASME;MIAOUSS;MIME_JR;MIMIGAL;MIMITOSS;MINIDRACO;MORPHEO;MOTISMA;MOUFOUETTE;MOUSTILLON;MUCIOLE;MUNNA;MUSTEBOUEE;MYSDIBULE;MYSTHERBE;NANMEOUIE;NATU;NEGAPI;NENUPIOT;NIDORAN_F;NIDORAN_M;NINGALE;NIRONDELLE;NODULITHE;NOEUNOEUF;NOSFERAPTI;NUCLEOS;OBALIE;OKEOKE;ONIX;OSSELAIT;OTARIA;OUISTICRAM;PACHIRISU;PALKIA;PARAS;PARECOOL;PHANPY;PHIONE;PIAFABEC;PICHU;PIJAKO;POICHIGEON;POISSIRENE;POLARHUME;POLICHOMBR;POMDEPIK;PONCHIOT;PONYTA;PORYGON;POSIPI;POUSSIFEU;PSYKOKWAK;PTERA;PTIRAVI;PTITARD;PYRONILLE;QUEULORIOR;QWILFISH;RACAILLOU;RAIKOU;RAMOLOSS;RAPION;RATENTIF;RATTATA;RAYQUAZA;REGICE;REGIGIGAS;REGIROCK;REGISTEEL;RELICANTH;REMORAID;RESHIRAM;RHINOCORNE;RIOLU;ROTOTAUPE;ROUCOOL;ROZBOUTON;SABELETTE;SALAMECHE;SANCOKI;SAQUEDENEU;SCALPION;SCARABRUTE;SCARHINO;SCORPLANE;SCRUTELLA;SELEROC;SEVIPER;SHAYMIN;SKELENOX;SKITTY;SMOGO;SNUBBULL;SOLAROC;SOLOCHI;SOPORIFIK;SORBEBE;SPINDA;SPIRITOMB;SPOINK;STALGAMIN;STARI;STATITIK;SUICUNE;SULFURA;TADMORV;TARINOR;TARSAL;TAUPIQUEUR;TAUROS;TEDDIURSA;TENEFIX;TENTACOOL;TERHAL;TERRAKIUM;TIC;TIPLOUF;TOGEPI;TORTIPOUSS;TOUDOUDOU;TOURNEGRIN;TRITONDE;TROMPIGNON;TROPIUS;TUTAFEH;TYGNON;TYLTON;VENIPATTE;VICTINI;VIPELIERRE;VIRIDIUM;VISKUSE;VIVALDAIM;VOLTORBE;VORTENTE;VOSTOURNO;WAILMER;WATTOUAT;YANMA;ZARBI;ZEBIBRON;ZEKROM;ZIGZATON;ZORUA},{VAR__FIGHTER_NOM}))");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m128(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("puis(VAR__PK_SAUVAGE_NIVEAU,-3/2)+2");
ball_.setPrice(400);
return ball_;
}
static Item m129(){
Boost boost_=Instances.newBoost();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)2);
stringMapShort_.addEntry("SCUBA_BALL",(short)2);
stringMapShort_.addEntry("HONOR_BALL",(short)2);
stringMapShort_.addEntry("BIS_BALL",(short)2);
stringMapShort_.addEntry("LUNE_BALL",(short)2);
stringMapShort_.addEntry("SPEED_BALL",(short)2);
stringMapShort_.addEntry("LUXE_BALL",(short)6);
stringMapShort_.addEntry("SOIN_BALL",(short)2);
stringMapShort_.addEntry("MASTER_BALL",(short)2);
stringMapShort_.addEntry("CHRONO_BALL",(short)2);
stringMapShort_.addEntry("POKE_BALL",(short)2);
stringMapShort_.addEntry("SUPER_BALL",(short)2);
stringMapShort_.addEntry("RAPIDE_BALL",(short)2);
stringMapShort_.addEntry("NIVEAU_BALL",(short)2);
stringMapShort_.addEntry("SOMBRE_BALL",(short)2);
stringMapShort_.addEntry("HYPER_BALL",(short)2);
stringMapShort_.addEntry("MASSE_BALL",(short)2);
stringMapShort_.addEntry("LOVE_BALL",(short)2);
stringMapShort_.addEntry("COMPET_BALL",(short)2);
stringMapShort_.addEntry("APPAT_BALL",(short)2);
stringMapShort_.addEntry("COPAIN_BALL",(short)2);
stringMapShort_.addEntry("FILET_BALL",(short)2);
boost_.setHappiness(stringMapShort_);
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.DEFENSE,(short)10);
boost_.setEvs(enumMapStatisticShort_);
boost_.setPrice(9800);
return boost_;
}
static Item m130(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{ACIER}))+cardinal(complementaire({ACIER},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m131(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("3*cardinal(inter({VAR__PK_SAUVAGE_TYPES_BASE},{EAU;INSECTE}))+cardinal(complementaire({EAU;INSECTE},{VAR__PK_SAUVAGE_TYPES_BASE}))");
ball_.setPrice(900);
return ball_;
}
static Item m132(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("DINOCLIER");
fossil_.setLevel((short)5);
fossil_.setPrice(2500);
return fossil_;
}
static Item m133(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("KRANIDOS");
fossil_.setLevel((short)5);
fossil_.setPrice(2500);
return fossil_;
}
static Item m134(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("KABUTO");
fossil_.setLevel((short)5);
fossil_.setPrice(1000);
return fossil_;
}
static Item m135(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("ANORITH");
fossil_.setLevel((short)5);
fossil_.setPrice(2000);
return fossil_;
}
static Item m136(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("CARAPAGOS");
fossil_.setLevel((short)5);
fossil_.setPrice(4000);
return fossil_;
}
static Item m137(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("ARKEAPTI");
fossil_.setLevel((short)5);
fossil_.setPrice(4000);
return fossil_;
}
static Item m138(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("LILIA");
fossil_.setLevel((short)5);
fossil_.setPrice(2000);
return fossil_;
}
static Item m139(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{GLACE}))+cardinal(complementaire({GLACE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m140(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{PLANTE}))+cardinal(complementaire({PLANTE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m141(){
SellingItem sellingItem_ =Instances.newSellingItem();
sellingItem_.setPrice(3750);
return sellingItem_;
}
static Item m142(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setDrainedHpByDamageRate(Rate.newRate("1/8"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m143(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultWinningHappiness(Rate.newRate("2"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m144(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m145(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultDrainedHp(Rate.newRate("2"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m146(){
SellingItem sellingItem_ =Instances.newSellingItem();
sellingItem_.setPrice(2500);
return sellingItem_;
}
static Item m147(){
HealingHpStatus healingHpStatus_ =Instances.newHealingHpStatus();
healingHpStatus_.setHealedHpRate(Rate.newRate("1"));
StringList stringList_=new StringList(new CollCapacity(7));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringList_.add("PARALYSIE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringList_.add("BRULURE");
stringList_.add("GEL");
healingHpStatus_.setStatus(stringList_);
healingHpStatus_.setHealingKo(false);
healingHpStatus_.setPrice(3000);
return healingHpStatus_;
}
static Item m148(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setImmuLowStatis(true);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m149(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("AMOUR");
itemForBattle_.setImmuStatus(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m150(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setAttacksSoon(true);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m151(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("5/4");
ball_.setPrice(300);
return ball_;
}
static Item m152(){
HealingPp healingPp_ =Instances.newHealingPp();
healingPp_.setHealedMovePp(10);
healingPp_.setPrice(500);
return healingPp_;
}
static Item m153(){
HealingPp healingPp_ =Instances.newHealingPp();
healingPp_.setHealingMoveFullpp(true);
healingPp_.setPrice(1100);
return healingPp_;
}
static Item m154(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("2");
ball_.setPrice(1000);
return ball_;
}
static Item m155(){
HealingHp healingHp_ =Instances.newHealingHp();
healingHp_.setHp(Rate.newRate("200"));
healingHp_.setPrice(1200);
return healingHp_;
}
static Item m156(){
HealingHp healingHp_ =Instances.newHealingHp();
healingHp_.setHp(Rate.newRate("20"));
healingHp_.setPrice(250);
return healingHp_;
}
static Item m157(){
HealingHp healingHp_ =Instances.newHealingHp();
healingHp_.setHp(Rate.newRate("100"));
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)1);
stringMapShort_.addEntry("SCUBA_BALL",(short)1);
stringMapShort_.addEntry("HONOR_BALL",(short)1);
stringMapShort_.addEntry("BIS_BALL",(short)1);
stringMapShort_.addEntry("LUNE_BALL",(short)1);
stringMapShort_.addEntry("SPEED_BALL",(short)1);
stringMapShort_.addEntry("LUXE_BALL",(short)2);
stringMapShort_.addEntry("SOIN_BALL",(short)1);
stringMapShort_.addEntry("MASTER_BALL",(short)1);
stringMapShort_.addEntry("CHRONO_BALL",(short)1);
stringMapShort_.addEntry("POKE_BALL",(short)1);
stringMapShort_.addEntry("SUPER_BALL",(short)1);
stringMapShort_.addEntry("RAPIDE_BALL",(short)1);
stringMapShort_.addEntry("NIVEAU_BALL",(short)1);
stringMapShort_.addEntry("SOMBRE_BALL",(short)1);
stringMapShort_.addEntry("HYPER_BALL",(short)1);
stringMapShort_.addEntry("MASSE_BALL",(short)1);
stringMapShort_.addEntry("LOVE_BALL",(short)1);
stringMapShort_.addEntry("COMPET_BALL",(short)1);
stringMapShort_.addEntry("APPAT_BALL",(short)1);
stringMapShort_.addEntry("COPAIN_BALL",(short)1);
stringMapShort_.addEntry("FILET_BALL",(short)1);
healingHp_.setHappiness(stringMapShort_);
healingHp_.setPrice(500);
return healingHp_;
}
static Item m158(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.CRITICAL_HIT,(byte)1);
itemForBattle_.setMultStatRank(enumMapStatisticByte_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m159(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ACCURACY,"6/5*VAR__FIGHTER_DER_JOUE+(1-VAR__FIGHTER_DER_JOUE)");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m160(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.SPECIAL_ATTACK,(short)4);
itemForBattle_.setWinEvFight(enumMapStatisticShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m161(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<EnumMap<Statistic,Byte>> stringMapEnumMapStatisticByte_=new StringMap<EnumMap<Statistic,Byte>>(new CollCapacity(1));
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_DEFENSE,(byte)1);
stringMapEnumMapStatisticByte_.addEntry("EAU",enumMapStatisticByte_);
itemForBattle_.setBoostStatisTypes(stringMapEnumMapStatisticByte_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m162(){
HealingHp healingHp_ =Instances.newHealingHp();
healingHp_.setHp(Rate.newRate("80"));
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)1);
stringMapShort_.addEntry("SCUBA_BALL",(short)1);
stringMapShort_.addEntry("HONOR_BALL",(short)1);
stringMapShort_.addEntry("BIS_BALL",(short)1);
stringMapShort_.addEntry("LUNE_BALL",(short)1);
stringMapShort_.addEntry("SPEED_BALL",(short)1);
stringMapShort_.addEntry("LUXE_BALL",(short)2);
stringMapShort_.addEntry("SOIN_BALL",(short)1);
stringMapShort_.addEntry("MASTER_BALL",(short)1);
stringMapShort_.addEntry("CHRONO_BALL",(short)1);
stringMapShort_.addEntry("POKE_BALL",(short)1);
stringMapShort_.addEntry("SUPER_BALL",(short)1);
stringMapShort_.addEntry("RAPIDE_BALL",(short)1);
stringMapShort_.addEntry("NIVEAU_BALL",(short)1);
stringMapShort_.addEntry("SOMBRE_BALL",(short)1);
stringMapShort_.addEntry("HYPER_BALL",(short)1);
stringMapShort_.addEntry("MASSE_BALL",(short)1);
stringMapShort_.addEntry("LOVE_BALL",(short)1);
stringMapShort_.addEntry("COMPET_BALL",(short)1);
stringMapShort_.addEntry("APPAT_BALL",(short)1);
stringMapShort_.addEntry("COPAIN_BALL",(short)1);
stringMapShort_.addEntry("FILET_BALL",(short)1);
healingHp_.setHappiness(stringMapShort_);
healingHp_.setPrice(350);
return healingHp_;
}
static Item m163(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ACCURACY,"11/10");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m164(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("8*caracferme(cardinal(complementaire({NO_GENDER},union({VAR__PK_UT_GENRE},{VAR__PK_SAUVAGE_GENRE}))),2,2)+caracferme(cardinal(complementaire({NO_GENDER},union({VAR__PK_UT_GENRE},{VAR__PK_SAUVAGE_GENRE}))),0,1)");
ball_.setPrice(700);
return ball_;
}
static Item m165(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(2));
stringMapShort_.addEntry("PROTECTION",(short)3);
stringMapShort_.addEntry("MUR_LUMIERE",(short)3);
itemForBattle_.setIncreasingMaxNbRoundTeamMove(stringMapShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m166(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("POUDRE_TOXIK");
itemForBattle_.setImmuMoves(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("TEMPETESABLE");
itemForBattle_.setImmuWeather(stringList_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m167(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,"3/2");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m168(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{TENEBRE}))+cardinal(complementaire({TENEBRE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m169(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("11/10*cardinal(inter({VAR__ATTAQUE_CATEGORIE},{SPECIALE}))+cardinal(complementaire({SPECIALE},{VAR__ATTAQUE_CATEGORIE}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m170(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("4*cardinal(inter({VAR__PK_SAUVAGE_PIERRES_EVOS},{PIERRE_LUNE}))+cardinal(complementaire({PIERRE_LUNE},{VAR__PK_SAUVAGE_PIERRES_EVOS}))");
ball_.setPrice(700);
return ball_;
}
static Item m171(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("5/4");
ball_.setPrice(1000);
return ball_;
}
static Item m172(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m173(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("(2*div(VAR__PK_SAUVAGE_MASSE,VAR__MASSE_MOYENNE_PK)+1)*10");
ball_.setPrice(800);
return ball_;
}
static Item m174(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"2*cardinal(inter({VAR__FIGHTER_NOM},{OSSATUEUR;OSSELAIT}))+cardinal(complementaire({OSSATUEUR;OSSELAIT},{VAR__FIGHTER_NOM}))");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m175(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("255");
ball_.setPrice(0);
return ball_;
}
static Item m176(){
HealingPp healingPp_ =Instances.newHealingPp();
healingPp_.setHealingAllMovesPp(true);
healingPp_.setPrice(3000);
return healingPp_;
}
static Item m177(){
Repel repel_ =Instances.newRepel();
repel_.setSteps(500);repel_.setPrice(800);
return repel_;
}
static Item m178(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultDamage("div(VAR__NB_UTILISATION_CONSECUTIF,10)+11/10");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m179(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(200);
return itemForBattle_;
}
static Item m180(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(200);
return itemForBattle_;
}
static Item m181(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(200);
return itemForBattle_;
}
static Item m182(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(200);
return itemForBattle_;
}
static Item m183(){
SellingItem sellingItem_ =Instances.newSellingItem();
sellingItem_.setPrice(4900);
return sellingItem_;
}
static Item m184(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"3/2");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m185(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{NORMAL}))+cardinal(complementaire({NORMAL},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m186(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setBoostExp(true);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m187(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("AMONITA");
fossil_.setLevel((short)5);
fossil_.setPrice(1000);
return fossil_;
}
static Item m188(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("puis(2,min(ent(div(VAR__PK_UT_NIVEAU,VAR__PK_SAUVAGE_NIVEAU)),8))");
ball_.setPrice(750);
return ball_;
}
static Item m189(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("AMOUR");
itemForBattle_.setSynchroStatus(stringList_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(1));
stringMapString_.addEntry("AMOUR","VAR__EXISTE_GENRE_ASSEXUE|VAR__GENRES_EGAUX|VAR__CIBLE_POSSEDE_STATUT_RELATION__AMOUR");
itemForBattle_.setFailStatus(stringMapString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m190(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultWinningExp(Rate.newRate("3/2"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m191(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("cardinal(inter({VAR__LANCEUR_NOM},{DIALGA}))*(6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{DRAGON;ACIER}))+cardinal(complementaire({DRAGON;ACIER},{VAR__ATTAQUE_TYPES})))+cardinal(complementaire({DIALGA},{VAR__LANCEUR_NOM}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m192(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate("0"));
effectEndRoundIndividual_.setRecoilDamage(Rate.newRate("0"));
effectEndRoundIndividual_.setHealHp(Rate.newRate("0"));
effectEndRoundIndividual_.setUserStatusEndRound("BRULURE");
effectEndRoundIndividual_.setFailEndRound("");
effectEndRoundIndividual_.setEndRoundRank(52);
effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
effectEndRoundIndividual_.setFail("");
custListEffectEndRound_.add(effectEndRoundIndividual_);
itemForBattle_.setEffectEndRound(custListEffectEndRound_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m193(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("cardinal(inter({VAR__LANCEUR_NOM},{PALKIA}))*(6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{DRAGON;EAU}))+cardinal(complementaire({DRAGON;EAU},{VAR__ATTAQUE_TYPES})))+cardinal(complementaire({PALKIA},{VAR__LANCEUR_NOM}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m194(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("cardinal(inter({VAR__LANCEUR_NOM},{GIRATINA}))*(6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{SPECTRE;DRAGON}))+cardinal(complementaire({SPECTRE;DRAGON},{VAR__ATTAQUE_TYPES})))+cardinal(complementaire({GIRATINA},{VAR__LANCEUR_NOM}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m195(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate("0"));
effectEndRoundIndividual_.setRecoilDamage(Rate.newRate("0"));
effectEndRoundIndividual_.setHealHp(Rate.newRate("0"));
effectEndRoundIndividual_.setUserStatusEndRound("POISON_GRAVE");
effectEndRoundIndividual_.setFailEndRound("");
effectEndRoundIndividual_.setEndRoundRank(53);
effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
effectEndRoundIndividual_.setFail("");
custListEffectEndRound_.add(effectEndRoundIndividual_);
itemForBattle_.setEffectEndRound(custListEffectEndRound_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m196(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultDamage("13/10");
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate("0"));
effectEndRoundIndividual_.setRecoilDamage(Rate.newRate("1/10"));
effectEndRoundIndividual_.setHealHp(Rate.newRate("0"));
effectEndRoundIndividual_.setUserStatusEndRound("");
effectEndRoundIndividual_.setFailEndRound("");
effectEndRoundIndividual_.setEndRoundRank(51);
effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
effectEndRoundIndividual_.setFail("");
custListEffectEndRound_.add(effectEndRoundIndividual_);
itemForBattle_.setEffectEndRound(custListEffectEndRound_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m197(){
SellingItem sellingItem_ =Instances.newSellingItem();
sellingItem_.setPrice(5000);
return sellingItem_;
}
static Item m198(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m199(){
SellingItem sellingItem_ =Instances.newSellingItem();
sellingItem_.setPrice(5000);
return sellingItem_;
}
static Item m200(){
SellingItem sellingItem_ =Instances.newSellingItem();
sellingItem_.setPrice(700);
return sellingItem_;
}
static Item m201(){
SellingItem sellingItem_ =Instances.newSellingItem();
sellingItem_.setPrice(250);
return sellingItem_;
}
static Item m202(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{POISON}))+cardinal(complementaire({POISON},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m203(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m204(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
CustList<EffectWhileSendingWithStatistic> custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
effectWhileSendingWithStatistic_.setMultWeight(Rate.newRate("1/2"));
custListEffectWhileSendingWithStatistic_.add(effectWhileSendingWithStatistic_);
itemForBattle_.setEffectSending(custListEffectWhileSendingWithStatistic_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m205(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m206(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{ROCHE}))+cardinal(complementaire({ROCHE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m207(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m208(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m209(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m210(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m211(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m212(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m213(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m214(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m215(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m216(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m217(){
EvolvingStone evolvingStone_ =Instances.newEvolvingStone();
evolvingStone_.setPrice(1000);
return evolvingStone_;
}
static Item m218(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setAgainstEvo(true);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m219(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m220(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setDamageRecoil(Rate.newRate("1/8"));
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m221(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PSY");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{PSY}))+cardinal(complementaire({PSY},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m222(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("VOL");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{VOL}))+cardinal(complementaire({VOL},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m223(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("DRAGON");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{DRAGON}))+cardinal(complementaire({DRAGON},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m224(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("SPECTRE");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{SPECTRE}))+cardinal(complementaire({SPECTRE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m225(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEE");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{FEE}))+cardinal(complementaire({FEE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m226(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ACIER");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{ACIER}))+cardinal(complementaire({ACIER},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m227(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{FEU}))+cardinal(complementaire({FEU},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m228(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GLACE");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{GLACE}))+cardinal(complementaire({GLACE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m229(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{PLANTE}))+cardinal(complementaire({PLANTE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m230(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{EAU}))+cardinal(complementaire({EAU},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m231(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("TENEBRE");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{TENEBRE}))+cardinal(complementaire({TENEBRE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m232(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("COMBAT");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{COMBAT}))+cardinal(complementaire({COMBAT},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m233(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ROCHE");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{ROCHE}))+cardinal(complementaire({ROCHE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m234(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("SOL");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{SOL}))+cardinal(complementaire({SOL},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m235(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("POISON");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{POISON}))+cardinal(complementaire({POISON},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m236(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ELECTRIQUE");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{ELECTRIQUE}))+cardinal(complementaire({ELECTRIQUE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m237(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("INSECTE");
itemForBattle_.setTypesPk(stringList_);
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{INSECTE}))+cardinal(complementaire({INSECTE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m238(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.HP,(short)4);
itemForBattle_.setWinEvFight(enumMapStatisticShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m239(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.ATTACK,(short)4);
itemForBattle_.setWinEvFight(enumMapStatisticShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m240(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StatisticPokemons objectMapStatisticPokemonByte_=new StatisticPokemons(new CollCapacity(1));
objectMapStatisticPokemonByte_.addEntry(new StatisticPokemon(Statistic.CRITICAL_HIT,"LEVEINARD"),(byte)1);
itemForBattle_.setMultStatPokemonRank(objectMapStatisticPokemonByte_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m241(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("1");
ball_.setPrice(200);
return ball_;
}
static Item m242(){
HealingHp healingHp_ =Instances.newHealingHp();
healingHp_.setHp(Rate.newRate("20"));
healingHp_.setPrice(300);
return healingHp_;
}
static Item m243(){
HealingHpStatus healingHpStatus_ =Instances.newHealingHpStatus();
healingHpStatus_.setHealedHpRate(Rate.newRate("1"));
healingHpStatus_.setHealingKo(false);
healingHpStatus_.setPrice(2500);
return healingHpStatus_;
}
static Item m244(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.EVASINESS,"10/9");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m245(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{INSECTE}))+cardinal(complementaire({INSECTE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m246(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"3/2*cardinal(inter({VAR__FIGHTER_NOM},{METAMORPH}))+cardinal(complementaire({METAMORPH},{VAR__FIGHTER_NOM}))");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m247(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"3/2*cardinal(inter({VAR__FIGHTER_NOM},{METAMORPH}))+cardinal(complementaire({METAMORPH},{VAR__FIGHTER_NOM}))");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m248(){
SellingItem sellingItem_ =Instances.newSellingItem();
sellingItem_.setPrice(1000);
return sellingItem_;
}
static Item m249(){
Boost boost_=Instances.newBoost();
boost_.setWinPp(Rate.newRate("3"));
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)2);
stringMapShort_.addEntry("SCUBA_BALL",(short)2);
stringMapShort_.addEntry("HONOR_BALL",(short)2);
stringMapShort_.addEntry("BIS_BALL",(short)2);
stringMapShort_.addEntry("LUNE_BALL",(short)2);
stringMapShort_.addEntry("SPEED_BALL",(short)2);
stringMapShort_.addEntry("LUXE_BALL",(short)6);
stringMapShort_.addEntry("SOIN_BALL",(short)2);
stringMapShort_.addEntry("MASTER_BALL",(short)2);
stringMapShort_.addEntry("CHRONO_BALL",(short)2);
stringMapShort_.addEntry("POKE_BALL",(short)2);
stringMapShort_.addEntry("SUPER_BALL",(short)2);
stringMapShort_.addEntry("RAPIDE_BALL",(short)2);
stringMapShort_.addEntry("NIVEAU_BALL",(short)2);
stringMapShort_.addEntry("SOMBRE_BALL",(short)2);
stringMapShort_.addEntry("HYPER_BALL",(short)2);
stringMapShort_.addEntry("MASSE_BALL",(short)2);
stringMapShort_.addEntry("LOVE_BALL",(short)2);
stringMapShort_.addEntry("COMPET_BALL",(short)2);
stringMapShort_.addEntry("APPAT_BALL",(short)2);
stringMapShort_.addEntry("COPAIN_BALL",(short)2);
stringMapShort_.addEntry("FILET_BALL",(short)2);
boost_.setHappiness(stringMapShort_);
boost_.setPrice(9800);
return boost_;
}
static Item m250(){
Boost boost_=Instances.newBoost();
boost_.setWinPp(Rate.newRate("1"));
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)2);
stringMapShort_.addEntry("SCUBA_BALL",(short)2);
stringMapShort_.addEntry("HONOR_BALL",(short)2);
stringMapShort_.addEntry("BIS_BALL",(short)2);
stringMapShort_.addEntry("LUNE_BALL",(short)2);
stringMapShort_.addEntry("SPEED_BALL",(short)2);
stringMapShort_.addEntry("LUXE_BALL",(short)6);
stringMapShort_.addEntry("SOIN_BALL",(short)2);
stringMapShort_.addEntry("MASTER_BALL",(short)2);
stringMapShort_.addEntry("CHRONO_BALL",(short)2);
stringMapShort_.addEntry("POKE_BALL",(short)2);
stringMapShort_.addEntry("SUPER_BALL",(short)2);
stringMapShort_.addEntry("RAPIDE_BALL",(short)2);
stringMapShort_.addEntry("NIVEAU_BALL",(short)2);
stringMapShort_.addEntry("SOMBRE_BALL",(short)2);
stringMapShort_.addEntry("HYPER_BALL",(short)2);
stringMapShort_.addEntry("MASSE_BALL",(short)2);
stringMapShort_.addEntry("LOVE_BALL",(short)2);
stringMapShort_.addEntry("COMPET_BALL",(short)2);
stringMapShort_.addEntry("APPAT_BALL",(short)2);
stringMapShort_.addEntry("COPAIN_BALL",(short)2);
stringMapShort_.addEntry("FILET_BALL",(short)2);
boost_.setHappiness(stringMapShort_);
boost_.setPrice(9800);
return boost_;
}
static Item m251(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m252(){
Boost boost_=Instances.newBoost();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)2);
stringMapShort_.addEntry("SCUBA_BALL",(short)2);
stringMapShort_.addEntry("HONOR_BALL",(short)2);
stringMapShort_.addEntry("BIS_BALL",(short)2);
stringMapShort_.addEntry("LUNE_BALL",(short)2);
stringMapShort_.addEntry("SPEED_BALL",(short)2);
stringMapShort_.addEntry("LUXE_BALL",(short)6);
stringMapShort_.addEntry("SOIN_BALL",(short)2);
stringMapShort_.addEntry("MASTER_BALL",(short)2);
stringMapShort_.addEntry("CHRONO_BALL",(short)2);
stringMapShort_.addEntry("POKE_BALL",(short)2);
stringMapShort_.addEntry("SUPER_BALL",(short)2);
stringMapShort_.addEntry("RAPIDE_BALL",(short)2);
stringMapShort_.addEntry("NIVEAU_BALL",(short)2);
stringMapShort_.addEntry("SOMBRE_BALL",(short)2);
stringMapShort_.addEntry("HYPER_BALL",(short)2);
stringMapShort_.addEntry("MASSE_BALL",(short)2);
stringMapShort_.addEntry("LOVE_BALL",(short)2);
stringMapShort_.addEntry("COMPET_BALL",(short)2);
stringMapShort_.addEntry("APPAT_BALL",(short)2);
stringMapShort_.addEntry("COPAIN_BALL",(short)2);
stringMapShort_.addEntry("FILET_BALL",(short)2);
boost_.setHappiness(stringMapShort_);
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.ATTACK,(short)10);
boost_.setEvs(enumMapStatisticShort_);
boost_.setPrice(9800);
return boost_;
}
static Item m253(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setCancelImmuType(true);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m254(){
Boost boost_=Instances.newBoost();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)2);
stringMapShort_.addEntry("SCUBA_BALL",(short)2);
stringMapShort_.addEntry("HONOR_BALL",(short)2);
stringMapShort_.addEntry("BIS_BALL",(short)2);
stringMapShort_.addEntry("LUNE_BALL",(short)2);
stringMapShort_.addEntry("SPEED_BALL",(short)2);
stringMapShort_.addEntry("LUXE_BALL",(short)6);
stringMapShort_.addEntry("SOIN_BALL",(short)2);
stringMapShort_.addEntry("MASTER_BALL",(short)2);
stringMapShort_.addEntry("CHRONO_BALL",(short)2);
stringMapShort_.addEntry("POKE_BALL",(short)2);
stringMapShort_.addEntry("SUPER_BALL",(short)2);
stringMapShort_.addEntry("RAPIDE_BALL",(short)2);
stringMapShort_.addEntry("NIVEAU_BALL",(short)2);
stringMapShort_.addEntry("SOMBRE_BALL",(short)2);
stringMapShort_.addEntry("HYPER_BALL",(short)2);
stringMapShort_.addEntry("MASSE_BALL",(short)2);
stringMapShort_.addEntry("LOVE_BALL",(short)2);
stringMapShort_.addEntry("COMPET_BALL",(short)2);
stringMapShort_.addEntry("APPAT_BALL",(short)2);
stringMapShort_.addEntry("COPAIN_BALL",(short)2);
stringMapShort_.addEntry("FILET_BALL",(short)2);
boost_.setHappiness(stringMapShort_);
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.HP,(short)10);
boost_.setEvs(enumMapStatisticShort_);
boost_.setPrice(9800);
return boost_;
}
static Item m255(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setAttackLast(true);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m256(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("25*caracferme(VAR__TEMPS_TOUR,0,3)+10*caracferme(VAR__TEMPS_TOUR,4,7)+6*caracferme(VAR__TEMPS_TOUR,8,11)+3*caracferme(VAR__TEMPS_TOUR,12,14)+caracdroiteferme(VAR__TEMPS_TOUR,15)");
ball_.setPrice(800);
return ball_;
}
static Item m257(){
HealingHpStatus healingHpStatus_ =Instances.newHealingHpStatus();
healingHpStatus_.setHealedHpRate(Rate.newRate("1/2"));
healingHpStatus_.setHealingKo(true);
healingHpStatus_.setPrice(1500);
return healingHpStatus_;
}
static Item m258(){
HealingHpStatus healingHpStatus_ =Instances.newHealingHpStatus();
healingHpStatus_.setHealedHpRate(Rate.newRate("1"));
healingHpStatus_.setHealingKo(true);
healingHpStatus_.setPrice(2500);
return healingHpStatus_;
}
static Item m259(){
Repel repel_ =Instances.newRepel();
repel_.setSteps(100);repel_.setPrice(300);
return repel_;
}
static Item m260(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
CustList<EffectEndRound> custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate("0"));
effectEndRoundIndividual_.setRecoilDamage(Rate.newRate("0"));
effectEndRoundIndividual_.setHealHp(Rate.newRate("1/16"));
effectEndRoundIndividual_.setUserStatusEndRound("");
effectEndRoundIndividual_.setFailEndRound("");
effectEndRoundIndividual_.setEndRoundRank(39);
effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
effectEndRoundIndividual_.setFail("");
custListEffectEndRound_.add(effectEndRoundIndividual_);
itemForBattle_.setEffectEndRound(custListEffectEndRound_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m261(){
HealingSimpleStatus healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList stringList_=new StringList(new CollCapacity(2));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
healingSimpleStatus_.setStatus(stringList_);
healingSimpleStatus_.setHealingKo(false);
healingSimpleStatus_.setPrice(250);
return healingSimpleStatus_;
}
static Item m262(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(1));
stringMapShort_.addEntry("ZENITH",(short)3);
itemForBattle_.setIncreasingMaxNbRoundGlobalMove(stringMapShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m263(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(1));
stringMapShort_.addEntry("ORAGE",(short)3);
itemForBattle_.setIncreasingMaxNbRoundGlobalMove(stringMapShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m264(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(1));
stringMapShort_.addEntry("GRELE",(short)3);
itemForBattle_.setIncreasingMaxNbRoundGlobalMove(stringMapShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m265(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(1));
stringMapShort_.addEntry("DANSE_PLUIE",(short)3);
itemForBattle_.setIncreasingMaxNbRoundGlobalMove(stringMapShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m266(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(1));
stringMapShort_.addEntry("TEMPETESABLE",(short)3);
itemForBattle_.setIncreasingMaxNbRoundGlobalMove(stringMapShort_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m267(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m268(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m269(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{SPECTRE}))+cardinal(complementaire({SPECTRE},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m270(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
itemForBattle_.setMultPower("6/5*cardinal(inter({VAR__ATTAQUE_TYPES},{SOL}))+cardinal(complementaire({SOL},{VAR__ATTAQUE_TYPES}))");
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m271(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1);
return evolvingItem_;
}
static Item m272(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("7*cardinal(inter({VAR__LIEU_COMBAT},{WATER}))+3/4*cardinal(complementaire({WATER},{VAR__LIEU_COMBAT}))");
ball_.setPrice(800);
return ball_;
}
static Item m273(){
HealingHp healingHp_ =Instances.newHealingHp();
healingHp_.setHp(Rate.newRate("60"));
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)1);
stringMapShort_.addEntry("SCUBA_BALL",(short)1);
stringMapShort_.addEntry("HONOR_BALL",(short)1);
stringMapShort_.addEntry("BIS_BALL",(short)1);
stringMapShort_.addEntry("LUNE_BALL",(short)1);
stringMapShort_.addEntry("SPEED_BALL",(short)1);
stringMapShort_.addEntry("LUXE_BALL",(short)2);
stringMapShort_.addEntry("SOIN_BALL",(short)1);
stringMapShort_.addEntry("MASTER_BALL",(short)1);
stringMapShort_.addEntry("CHRONO_BALL",(short)1);
stringMapShort_.addEntry("POKE_BALL",(short)1);
stringMapShort_.addEntry("SUPER_BALL",(short)1);
stringMapShort_.addEntry("RAPIDE_BALL",(short)1);
stringMapShort_.addEntry("NIVEAU_BALL",(short)1);
stringMapShort_.addEntry("SOMBRE_BALL",(short)1);
stringMapShort_.addEntry("HYPER_BALL",(short)1);
stringMapShort_.addEntry("MASSE_BALL",(short)1);
stringMapShort_.addEntry("LOVE_BALL",(short)1);
stringMapShort_.addEntry("COMPET_BALL",(short)1);
stringMapShort_.addEntry("APPAT_BALL",(short)1);
stringMapShort_.addEntry("COPAIN_BALL",(short)1);
stringMapShort_.addEntry("FILET_BALL",(short)1);
healingHp_.setHappiness(stringMapShort_);
healingHp_.setPrice(300);
return healingHp_;
}
static Item m274(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("5/4");
ball_.setPrice(500);
return ball_;
}
static Item m275(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("7*cardinal(inter({VAR__LIEU_COMBAT},{WATER}))+3/4*cardinal(complementaire({WATER},{VAR__LIEU_COMBAT}))");
ball_.setPrice(800);
return ball_;
}
static Item m276(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("2*caracferme(VAR__PK_SAUVAGE_VITESSE,0,99)+6*caracdroiteouvert(VAR__PK_SAUVAGE_VITESSE,99)");
ball_.setPrice(800);
return ball_;
}
static Item m277(){
Ball ball_=Instances.newBall();
ball_.setCatchingRate("3/2");
ball_.setPrice(600);
return ball_;
}
static Item m278(){
HealingHp healingHp_ =Instances.newHealingHp();
healingHp_.setHp(Rate.newRate("50"));
healingHp_.setPrice(700);
return healingHp_;
}
static Item m279(){
Repel repel_ =Instances.newRepel();
repel_.setSteps(200);repel_.setPrice(500);
return repel_;
}
static Item m280(){
EvolvingItem evolvingItem_=Instances.newEvolvingItem();
evolvingItem_.setPrice(1000);
return evolvingItem_;
}
static Item m281(){
HealingSimpleStatus healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList stringList_=new StringList(new CollCapacity(7));
stringList_.add("SOMMEIL");
stringList_.add("SOMMEIL_REPOS");
stringList_.add("PARALYSIE");
stringList_.add("SIMPLE_POISON");
stringList_.add("POISON_GRAVE");
stringList_.add("BRULURE");
stringList_.add("GEL");
healingSimpleStatus_.setStatus(stringList_);
healingSimpleStatus_.setHealingKo(false);
healingSimpleStatus_.setPrice(600);
return healingSimpleStatus_;
}
static Item m282(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,"3/2");
itemForBattle_.setMultStat(enumMapStatisticString_);
itemForBattle_.setPrice(0);
return itemForBattle_;
}
static Item m283(){
Fossil fossil_ =Instances.newFossil();
fossil_.setPokemon("PTERA");
fossil_.setLevel((short)10);
fossil_.setPrice(1500);
return fossil_;
}
static Item m284(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
MonteCarloBoolean monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
monteCarloBoolean_.addQuickEvent(false,LgInt.newLgInt("4"));
monteCarloBoolean_.addQuickEvent(true,LgInt.newLgInt("1"));
itemForBattle_.setLawForAttackFirst(monteCarloBoolean_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m285(){
ItemForBattle itemForBattle_ =Instances.newItemForBattle();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
itemForBattle_.setBoostStatisSuperEff(enumMapStatisticByte_);
itemForBattle_.setPrice(1000);
return itemForBattle_;
}
static Item m286(){
Boost boost_=Instances.newBoost();
StringMap<Short> stringMapShort_=new StringMap<Short>(new CollCapacity(22));
stringMapShort_.addEntry("FAIBLO_BALL",(short)2);
stringMapShort_.addEntry("SCUBA_BALL",(short)2);
stringMapShort_.addEntry("HONOR_BALL",(short)2);
stringMapShort_.addEntry("BIS_BALL",(short)2);
stringMapShort_.addEntry("LUNE_BALL",(short)2);
stringMapShort_.addEntry("SPEED_BALL",(short)2);
stringMapShort_.addEntry("LUXE_BALL",(short)6);
stringMapShort_.addEntry("SOIN_BALL",(short)2);
stringMapShort_.addEntry("MASTER_BALL",(short)2);
stringMapShort_.addEntry("CHRONO_BALL",(short)2);
stringMapShort_.addEntry("POKE_BALL",(short)2);
stringMapShort_.addEntry("SUPER_BALL",(short)2);
stringMapShort_.addEntry("RAPIDE_BALL",(short)2);
stringMapShort_.addEntry("NIVEAU_BALL",(short)2);
stringMapShort_.addEntry("SOMBRE_BALL",(short)2);
stringMapShort_.addEntry("HYPER_BALL",(short)2);
stringMapShort_.addEntry("MASSE_BALL",(short)2);
stringMapShort_.addEntry("LOVE_BALL",(short)2);
stringMapShort_.addEntry("COMPET_BALL",(short)2);
stringMapShort_.addEntry("APPAT_BALL",(short)2);
stringMapShort_.addEntry("COPAIN_BALL",(short)2);
stringMapShort_.addEntry("FILET_BALL",(short)2);
boost_.setHappiness(stringMapShort_);
EnumMap<Statistic,Short> enumMapStatisticShort_=new EnumMap<Statistic,Short>(new CollCapacity(1));
enumMapStatisticShort_.addEntry(Statistic.SPECIAL_DEFENSE,(short)10);
boost_.setEvs(enumMapStatisticShort_);
boost_.setPrice(9800);
return boost_;
}
}
