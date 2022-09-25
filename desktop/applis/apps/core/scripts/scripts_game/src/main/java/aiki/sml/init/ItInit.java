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
import code.util.core.BoolVal;

public final class ItInit extends CstIgame{
private ItInit(){}
public static StringMap<Item> it(){
 StringMap<Item> m202i_ = new StringMap<Item>(new CollCapacity(287));
m202i_.addEntry(I_ACCRO_GRIFFE,m0());
m202i_.addEntry(I_AIMANT,m1());
m202i_.addEntry(I_AMELIORATOR,m2());
m202i_.addEntry(I_ANTIDOTE,m3());
m202i_.addEntry(I_ANTIGEL,m4());
m202i_.addEntry(I_ANTI_BRULE,m5());
m202i_.addEntry(I_ANTI_PARA,m6());
m202i_.addEntry(I_APPAT_BALL,m7());
m202i_.addEntry(I_BAIE_ABRIKO,m8());
m202i_.addEntry(I_BAIE_ALGA,m9());
m202i_.addEntry(I_BAIE_BABIRI,m10());
m202i_.addEntry(I_BAIE_CERIZ,m11());
m202i_.addEntry(I_BAIE_CHARTI,m12());
m202i_.addEntry(I_BAIE_CHERIM,m13());
m202i_.addEntry(I_BAIE_CHOCCO,m14());
m202i_.addEntry(I_BAIE_COBABA,m15());
m202i_.addEntry(I_BAIE_DURIN,m16());
m202i_.addEntry(I_BAIE_EKA,m17());
m202i_.addEntry(I_BAIE_ENIGMA,m18());
m202i_.addEntry(I_BAIE_FIGUY,m19());
m202i_.addEntry(I_BAIE_FRAIGO,m20());
m202i_.addEntry(I_BAIE_FRAIVE,m21());
m202i_.addEntry(I_BAIE_FRAMBY,m22());
m202i_.addEntry(I_BAIE_FRISTA,m23());
m202i_.addEntry(I_BAIE_GOWAV,m24());
m202i_.addEntry(I_BAIE_GRENA,m25());
m202i_.addEntry(I_BAIE_JABOCA,m26());
m202i_.addEntry(I_BAIE_JOUCA,m27());
m202i_.addEntry(I_BAIE_KEBIA,m28());
m202i_.addEntry(I_BAIE_KIKA,m29());
m202i_.addEntry(I_BAIE_KIWAN,m30());
m202i_.addEntry(I_BAIE_LAMPOU,m31());
m202i_.addEntry(I_BAIE_LANSAT,m32());
m202i_.addEntry(I_BAIE_LICHII,m33());
m202i_.addEntry(I_BAIE_LINGAN,m34());
m202i_.addEntry(I_BAIE_LONME,m35());
m202i_.addEntry(I_BAIE_MAGO,m36());
m202i_.addEntry(I_BAIE_MANGOU,m37());
m202i_.addEntry(I_BAIE_MARON,m38());
m202i_.addEntry(I_BAIE_MEPO,m39());
m202i_.addEntry(I_BAIE_MICLE,m40());
m202i_.addEntry(I_BAIE_MYRTE,m41());
m202i_.addEntry(I_BAIE_NANAB,m42());
m202i_.addEntry(I_BAIE_NANANA,m43());
m202i_.addEntry(I_BAIE_NANONE,m44());
m202i_.addEntry(I_BAIE_ORAN,m45());
m202i_.addEntry(I_BAIE_PALMA,m46());
m202i_.addEntry(I_BAIE_PANGA,m47());
m202i_.addEntry(I_BAIE_PAPAYA,m48());
m202i_.addEntry(I_BAIE_PARMA,m49());
m202i_.addEntry(I_BAIE_PECHA,m50());
m202i_.addEntry(I_BAIE_PITAYE,m51());
m202i_.addEntry(I_BAIE_POCPOC,m52());
m202i_.addEntry(I_BAIE_POMMO,m53());
m202i_.addEntry(I_BAIE_POMROZ,m54());
m202i_.addEntry(I_BAIE_PRINE,m55());
m202i_.addEntry(I_BAIE_QUALOT,m56());
m202i_.addEntry(I_BAIE_RABUTA,m57());
m202i_.addEntry(I_BAIE_RANGMA,m58());
m202i_.addEntry(I_BAIE_RATAM,m59());
m202i_.addEntry(I_BAIE_REMU,m60());
m202i_.addEntry(I_BAIE_REPOI,m61());
m202i_.addEntry(I_BAIE_RESIN,m62());
m202i_.addEntry(I_BAIE_SAILAK,m63());
m202i_.addEntry(I_BAIE_SEDRA,m64());
m202i_.addEntry(I_BAIE_SELRO,m65());
m202i_.addEntry(I_BAIE_SIAM,m66());
m202i_.addEntry(I_BAIE_SITRUS,m67());
m202i_.addEntry(I_BAIE_STEKPA,m68());
m202i_.addEntry(I_BAIE_TAMATO,m69());
m202i_.addEntry(I_BAIE_TRONCI,m70());
m202i_.addEntry(I_BAIE_WIKI,m71());
m202i_.addEntry(I_BAIE_WILLIA,m72());
m202i_.addEntry(I_BAIE_YAPAP,m73());
m202i_.addEntry(I_BAIE_ZALIS,m74());
m202i_.addEntry(I_BALLE_FER,m75());
m202i_.addEntry(I_BALLON,m76());
m202i_.addEntry(I_BANDEAU,m77());
m202i_.addEntry(I_BANDEAU_ETREINTE,m78());
m202i_.addEntry(I_BAND_CHOIX,m79());
m202i_.addEntry(I_BAND_MUSCLE,m80());
m202i_.addEntry(I_BAND_POUV,m81());
m202i_.addEntry(I_BATON,m82());
m202i_.addEntry(I_BEC_POINTU,m83());
m202i_.addEntry(I_BIS_BALL,m84());
m202i_.addEntry(I_BIZAR_ENCENS,m85());
m202i_.addEntry(I_BOUE_NOIRE,m86());
m202i_.addEntry(I_BOULE_DE_NEIGE,m87());
m202i_.addEntry(I_BOULE_FUMEE,m88());
m202i_.addEntry(I_BOUTON_FUITE,m89());
m202i_.addEntry(I_BRAC_MACHO,m90());
m202i_.addEntry(I_BULBE,m91());
m202i_.addEntry(I_CALCIUM,m92());
m202i_.addEntry(I_CARAPACE_MUE,m93());
m202i_.addEntry(I_CARBONE,m94());
m202i_.addEntry(I_CARTE_ROUGE,m95());
m202i_.addEntry(I_CASQUE_BRUT,m96());
m202i_.addEntry(I_CD_DOUTEUX,m97());
m202i_.addEntry(I_CEINTURE_PRO,m98());
m202i_.addEntry(I_CEINT_FORCE,m99());
m202i_.addEntry(I_CEINT_NOIRE,m100());
m202i_.addEntry(I_CEINT_POUV,m101());
m202i_.addEntry(I_CENDRESACREE,m102());
m202i_.addEntry(I_CHAINE_POUV,m103());
m202i_.addEntry(I_CHANTIBONBON,m104());
m202i_.addEntry(I_CHARBON,m105());
m202i_.addEntry(I_CHRONO_BALL,m106());
m202i_.addEntry(I_COMPET_BALL,m107());
m202i_.addEntry(I_COPAIN_BALL,m108());
m202i_.addEntry(I_CROC_DRAGON,m109());
m202i_.addEntry(I_CROC_RASOIR,m110());
m202i_.addEntry(I_CUILLERTORDU,m111());
m202i_.addEntry(I_DENT_OCEAN,m112());
m202i_.addEntry(I_EAU_FRAICHE,m113());
m202i_.addEntry(I_EAU_MYSTIQUE,m114());
m202i_.addEntry(I_ECAILLE_DRACO,m115());
m202i_.addEntry(I_ECAILLE_OCEAN,m116());
m202i_.addEntry(I_ELECTRISEUR,m117());
m202i_.addEntry(I_ELIXIR,m118());
m202i_.addEntry(I_ENCENS_DOUX,m119());
m202i_.addEntry(I_ENCENS_FLEUR,m120());
m202i_.addEntry(I_ENCENS_MER,m121());
m202i_.addEntry(I_ENCENS_PLEIN,m122());
m202i_.addEntry(I_ENCENS_PUR,m123());
m202i_.addEntry(I_ENCENS_ROC,m124());
m202i_.addEntry(I_ENCENS_VAGUE,m125());
m202i_.addEntry(I_ENCENS_VEINE,m126());
m202i_.addEntry(I_EVOLUROC,m127());
m202i_.addEntry(I_FAIBLO_BALL,m128());
m202i_.addEntry(I_FER,m129());
m202i_.addEntry(I_FERRAILLE,m130());
m202i_.addEntry(I_FILET_BALL,m131());
m202i_.addEntry(I_FOSSILE_ARMURE,m132());
m202i_.addEntry(I_FOSSILE_CRANE,m133());
m202i_.addEntry(I_FOSSILE_DOME,m134());
m202i_.addEntry(I_FOSSILE_GRIFFE,m135());
m202i_.addEntry(I_FOSSILE_PLAQUE,m136());
m202i_.addEntry(I_FOSSILE_PLUME,m137());
m202i_.addEntry(I_FOSSILE_RACINE,m138());
m202i_.addEntry(I_GLACETERNEL,m139());
m202i_.addEntry(I_GRAIN_MIRACL,m140());
m202i_.addEntry(I_GRANDE_PERLE,m141());
m202i_.addEntry(I_GRELOT_COQUE,m142());
m202i_.addEntry(I_GRELOT_ZEN,m143());
m202i_.addEntry(I_GRIFFE_RASOIR,m144());
m202i_.addEntry(I_GROSSERACINE,m145());
m202i_.addEntry(I_GROS_CHAMPI,m146());
m202i_.addEntry(I_GUERISON,m147());
m202i_.addEntry(I_HERBEBLANCHE,m148());
m202i_.addEntry(I_HERBE_MENTAL,m149());
m202i_.addEntry(I_HERBE_POUV,m150());
m202i_.addEntry(I_HONOR_BALL,m151());
m202i_.addEntry(I_HUILE,m152());
m202i_.addEntry(I_HUILE_MAX,m153());
m202i_.addEntry(I_HYPER_BALL,m154());
m202i_.addEntry(I_HYPER_POTION,m155());
m202i_.addEntry(I_JUS_DE_BAIE,m156());
m202i_.addEntry(I_LAIT_MEUMEU,m157());
m202i_.addEntry(I_LENTILSCOPE,m158());
m202i_.addEntry(I_LENTIL_ZOOM,m159());
m202i_.addEntry(I_LENT_POUV,m160());
m202i_.addEntry(I_LICHEN_LUMINEUX,m161());
m202i_.addEntry(I_LIMONADE,m162());
m202i_.addEntry(I_LOUPE,m163());
m202i_.addEntry(I_LOVE_BALL,m164());
m202i_.addEntry(I_LUMARGILE,m165());
m202i_.addEntry(I_LUNETTES_FILTRE,m166());
m202i_.addEntry(I_LUNET_CHOIX,m167());
m202i_.addEntry(I_LUNET_NOIRES,m168());
m202i_.addEntry(I_LUNET_SAGES,m169());
m202i_.addEntry(I_LUNE_BALL,m170());
m202i_.addEntry(I_LUXE_BALL,m171());
m202i_.addEntry(I_MAGMARISEUR,m172());
m202i_.addEntry(I_MASSE_BALL,m173());
m202i_.addEntry(I_MASSE_OS,m174());
m202i_.addEntry(I_MASTER_BALL,m175());
m202i_.addEntry(I_MAX_ELIXIR,m176());
m202i_.addEntry(I_MAX_REPOUSSE,m177());
m202i_.addEntry(I_METRO,m178());
m202i_.addEntry(I_MODULE_AQUA,m179());
m202i_.addEntry(I_MODULE_CHOC,m180());
m202i_.addEntry(I_MODULE_CRYO,m181());
m202i_.addEntry(I_MODULE_PYRO,m182());
m202i_.addEntry(I_MORC_ETOILE,m183());
m202i_.addEntry(I_MOUCH_CHOIX,m184());
m202i_.addEntry(I_MOUCH_SOIE,m185());
m202i_.addEntry(I_MULTI_EXP,m186());
m202i_.addEntry(I_NAUTILE,m187());
m202i_.addEntry(I_NIVEAU_BALL,m188());
m202i_.addEntry(I_NOEUD_DESTIN,m189());
m202i_.addEntry(I_OEUF_CHANCE,m190());
m202i_.addEntry(I_ORBE_ADAMANT,m191());
m202i_.addEntry(I_ORBE_FLAMME,m192());
m202i_.addEntry(I_ORBE_PERLE,m193());
m202i_.addEntry(I_ORBE_PLATINE,m194());
m202i_.addEntry(I_ORBE_TOXIQUE,m195());
m202i_.addEntry(I_ORBE_VIE,m196());
m202i_.addEntry(I_OS_RARE,m197());
m202i_.addEntry(I_PEAU_METAL,m198());
m202i_.addEntry(I_PEPITE,m199());
m202i_.addEntry(I_PERLE,m200());
m202i_.addEntry(I_PETIT_CHAMPI,m201());
m202i_.addEntry(I_PIC_VENIN,m202());
m202i_.addEntry(I_PIECE_RUNE,m203());
m202i_.addEntry(I_PIERRALLEGEE,m204());
m202i_.addEntry(I_PIERRE_AUBE,m205());
m202i_.addEntry(I_PIERRE_DURE,m206());
m202i_.addEntry(I_PIERRE_EAU,m207());
m202i_.addEntry(I_PIERRE_ECLAT,m208());
m202i_.addEntry(I_PIERRE_FEU,m209());
m202i_.addEntry(I_PIERRE_FOUDRE,m210());
m202i_.addEntry(I_PIERRE_GLACE,m211());
m202i_.addEntry(I_PIERRE_LUNE,m212());
m202i_.addEntry(I_PIERRE_MOUSSE,m213());
m202i_.addEntry(I_PIERRE_NUIT,m214());
m202i_.addEntry(I_PIERRE_OVALE,m215());
m202i_.addEntry(I_PIERRE_PLANTE,m216());
m202i_.addEntry(I_PIERRE_SOLAIRE,m217());
m202i_.addEntry(I_PIERRE_STASE,m218());
m202i_.addEntry(I_PILE,m219());
m202i_.addEntry(I_PIQUANTS,m220());
m202i_.addEntry(I_PLAQUESPRIT,m221());
m202i_.addEntry(I_PLAQUE_CIEL,m222());
m202i_.addEntry(I_PLAQUE_DRACO,m223());
m202i_.addEntry(I_PLAQUE_FANTO,m224());
m202i_.addEntry(I_PLAQUE_FEE,m225());
m202i_.addEntry(I_PLAQUE_FER,m226());
m202i_.addEntry(I_PLAQUE_FLAM,m227());
m202i_.addEntry(I_PLAQUE_GLACE,m228());
m202i_.addEntry(I_PLAQUE_HERBE,m229());
m202i_.addEntry(I_PLAQUE_HYDRO,m230());
m202i_.addEntry(I_PLAQUE_OMBRE,m231());
m202i_.addEntry(I_PLAQUE_POING,m232());
m202i_.addEntry(I_PLAQUE_ROC,m233());
m202i_.addEntry(I_PLAQUE_TERRE,m234());
m202i_.addEntry(I_PLAQUE_TOXIC,m235());
m202i_.addEntry(I_PLAQUE_VOLT,m236());
m202i_.addEntry(I_PLAQUINSECT,m237());
m202i_.addEntry(I_POIDS_POUV,m238());
m202i_.addEntry(I_POIGN_POUV,m239());
m202i_.addEntry(I_POING_CHANCE,m240());
m202i_.addEntry(I_POKE_BALL,m241());
m202i_.addEntry(I_POTION,m242());
m202i_.addEntry(I_POTION_MAX,m243());
m202i_.addEntry(I_POUDRECLAIRE,m244());
m202i_.addEntry(I_POUDRE_ARG,m245());
m202i_.addEntry(I_POUDRE_METAL,m246());
m202i_.addEntry(I_POUDRE_VITE,m247());
m202i_.addEntry(I_POUSS_ETOILE,m248());
m202i_.addEntry(I_PP_MAX,m249());
m202i_.addEntry(I_PP_PLUS,m250());
m202i_.addEntry(I_PROTECTEUR,m251());
m202i_.addEntry(I_PROTEINE,m252());
m202i_.addEntry(I_PT_DE_MIRE,m253());
m202i_.addEntry(I_PV_PLUS,m254());
m202i_.addEntry(I_RALENTIQUEUE,m255());
m202i_.addEntry(I_RAPIDE_BALL,m256());
m202i_.addEntry(I_RAPPEL,m257());
m202i_.addEntry(I_RAPPEL_MAX,m258());
m202i_.addEntry(I_REPOUSSE,m259());
m202i_.addEntry(I_RESTES,m260());
m202i_.addEntry(I_REVEIL,m261());
m202i_.addEntry(I_ROCHE_CHAUDE,m262());
m202i_.addEntry(I_ROCHE_ELECTRIQUE,m263());
m202i_.addEntry(I_ROCHE_GLACE,m264());
m202i_.addEntry(I_ROCHE_HUMIDE,m265());
m202i_.addEntry(I_ROCHE_LISSE,m266());
m202i_.addEntry(I_ROCHE_ROYALE,m267());
m202i_.addEntry(I_RUNE_PURIF,m268());
m202i_.addEntry(I_RUNE_SORT,m269());
m202i_.addEntry(I_SABLE_DOUX,m270());
m202i_.addEntry(I_SACHET_SENTEUR,m271());
m202i_.addEntry(I_SCUBA_BALL,m272());
m202i_.addEntry(I_SODA_COOL,m273());
m202i_.addEntry(I_SOIN_BALL,m274());
m202i_.addEntry(I_SOMBRE_BALL,m275());
m202i_.addEntry(I_SPEED_BALL,m276());
m202i_.addEntry(I_SUPER_BALL,m277());
m202i_.addEntry(I_SUPER_POTION,m278());
m202i_.addEntry(I_SUPER_REPOUSSE,m279());
m202i_.addEntry(I_TISSU_FAUCHE,m280());
m202i_.addEntry(I_TOTAL_SOIN,m281());
m202i_.addEntry(I_VESTE_DE_COMBAT,m282());
m202i_.addEntry(I_VIEIL_AMBRE,m283());
m202i_.addEntry(I_VIVE_GRIFFE,m284());
m202i_.addEntry(I_VULNE_ASSURANCE,m285());
m202i_.addEntry(I_ZINC,m286());
return m202i_;
}
static Item m0(){
ItemForBattle m203itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> m203stringMapShort_=new StringMap<Short>(new CollCapacity(7));
m203stringMapShort_.addEntry(I_VORTEX_MAGMA,(short)0);
m203stringMapShort_.addEntry(I_CLAQUOIR,(short)0);
m203stringMapShort_.addEntry(I_ETREINTE,(short)0);
m203stringMapShort_.addEntry(I_SIPHON,(short)0);
m203stringMapShort_.addEntry(I_LIGOTAGE,(short)0);
m203stringMapShort_.addEntry(I_TOURBI_SABLE,(short)0);
m203stringMapShort_.addEntry(I_DANSEFLAMME,(short)0);
m203itemForBattle_.setIncreasingMaxNbRoundTrap(m203stringMapShort_);
m203itemForBattle_.setPrice(1000);
return m203itemForBattle_;
}
static Item m1(){
ItemForBattle m204itemForBattle_ =Instances.newItemForBattle();
m204itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_ELECTRIQUE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ELECTRIQUE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m204itemForBattle_.setPrice(1000);
return m204itemForBattle_;
}
static Item m2(){
EvolvingItem m205evolvingItem_=Instances.newEvolvingItem();
m205evolvingItem_.setPrice(1000);
return m205evolvingItem_;
}
static Item m3(){
HealingSimpleStatus m206healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList m206stringList_=new StringList(new CollCapacity(2));
m206stringList_.add(I_SIMPLE_POISON);
m206stringList_.add(I_POISON_GRAVE);
m206healingSimpleStatus_.setStatus(m206stringList_);
m206healingSimpleStatus_.setHealingKo(false);
m206healingSimpleStatus_.setPrice(100);
return m206healingSimpleStatus_;
}
static Item m4(){
HealingSimpleStatus m207healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList m207stringList_=new StringList(new CollCapacity(1));
m207stringList_.add(I_GEL);
m207healingSimpleStatus_.setStatus(m207stringList_);
m207healingSimpleStatus_.setHealingKo(false);
m207healingSimpleStatus_.setPrice(250);
return m207healingSimpleStatus_;
}
static Item m5(){
HealingSimpleStatus m208healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList m208stringList_=new StringList(new CollCapacity(1));
m208stringList_.add(I_BRULURE);
m208healingSimpleStatus_.setStatus(m208stringList_);
m208healingSimpleStatus_.setHealingKo(false);
m208healingSimpleStatus_.setPrice(250);
return m208healingSimpleStatus_;
}
static Item m6(){
HealingSimpleStatus m209healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList m209stringList_=new StringList(new CollCapacity(1));
m209stringList_.add(I_PARALYSIE);
m209healingSimpleStatus_.setStatus(m209stringList_);
m209healingSimpleStatus_.setHealingKo(false);
m209healingSimpleStatus_.setPrice(200);
return m209healingSimpleStatus_;
}
static Item m7(){
Ball m210ball_=Instances.newBall();
m210ball_.setCatchingRate(R_3+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_LIEU_COMBAT+RB+OC+LB+I_WATER+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_WATER+RB+OC+LB+V_LIEU_COMBAT+RB+RP+RP);
m210ball_.setPrice(800);
return m210ball_;
}
static Item m8(){
Berry m211berry_=Instances.newBerry();
IdMap<Statistic,BoostHpRate> m211enumMapStatisticBoostHpRate_=new IdMap<Statistic,BoostHpRate>(new CollCapacity(1));
m211enumMapStatisticBoostHpRate_.addEntry(Statistic.SPECIAL_DEFENSE,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m211berry_.setMultStat(m211enumMapStatisticBoostHpRate_);
m211berry_.setPrice(200);
return m211berry_;
}
static Item m9(){
Berry m212berry_=Instances.newBerry();
m212berry_.setPrice(200);
return m212berry_;
}
static Item m10(){
Berry m213berry_=Instances.newBerry();
StringMap<EfficiencyRate> m213stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m213stringMapEfficiencyRate_.addEntry(I_ACIER,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m213berry_.setMultFoesDamage(m213stringMapEfficiencyRate_);
m213berry_.setPrice(200);
return m213berry_;
}
static Item m11(){
Berry m214berry_=Instances.newBerry();
StringList m214stringList_=new StringList(new CollCapacity(1));
m214stringList_.add(I_PARALYSIE);
m214berry_.setHealStatus(m214stringList_);
m214berry_.setPrice(200);
return m214berry_;
}
static Item m12(){
Berry m215berry_=Instances.newBerry();
StringMap<EfficiencyRate> m215stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m215stringMapEfficiencyRate_.addEntry(I_ROCHE,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m215berry_.setMultFoesDamage(m215stringMapEfficiencyRate_);
m215berry_.setPrice(200);
return m215berry_;
}
static Item m13(){
Berry m216berry_=Instances.newBerry();
m216berry_.setLawForAttackFirst(true);
m216berry_.setPrice(200);
return m216berry_;
}
static Item m14(){
Berry m217berry_=Instances.newBerry();
StringMap<EfficiencyRate> m217stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m217stringMapEfficiencyRate_.addEntry(I_FEU,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m217berry_.setMultFoesDamage(m217stringMapEfficiencyRate_);
m217berry_.setPrice(200);
return m217berry_;
}
static Item m15(){
Berry m218berry_=Instances.newBerry();
StringMap<EfficiencyRate> m218stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m218stringMapEfficiencyRate_.addEntry(I_VOL,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m218berry_.setMultFoesDamage(m218stringMapEfficiencyRate_);
m218berry_.setPrice(200);
return m218berry_;
}
static Item m16(){
Berry m219berry_=Instances.newBerry();
m219berry_.setPrice(200);
return m219berry_;
}
static Item m17(){
Berry m220berry_=Instances.newBerry();
m220berry_.setCategoryBoosting(I_PHYSIQUE);
IdMap<Statistic,Byte> m220enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m220enumMapStatisticByte_.addEntry(Statistic.DEFENSE,(byte)1);
m220berry_.setBoostStatis(m220enumMapStatisticByte_);
m220berry_.setPrice(0);
return m220berry_;
}
static Item m18(){
Berry m221berry_=Instances.newBerry();
m221berry_.setHealHpBySuperEffMove(Rate.newRate(R_1_4));
m221berry_.setPrice(200);
return m221berry_;
}
static Item m19(){
Berry m222berry_=Instances.newBerry();
m222berry_.setHealHpRate(Rate.newRate(R_1_8));
m222berry_.setMaxHpHealingHpRate(Rate.newRate(R_1_4));
m222berry_.setPrice(200);
return m222berry_;
}
static Item m20(){
Berry m223berry_=Instances.newBerry();
StringMap<EfficiencyRate> m223stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m223stringMapEfficiencyRate_.addEntry(I_DRAGON,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m223berry_.setMultFoesDamage(m223stringMapEfficiencyRate_);
m223berry_.setPrice(200);
return m223berry_;
}
static Item m21(){
Berry m224berry_=Instances.newBerry();
StringList m224stringList_=new StringList(new CollCapacity(1));
m224stringList_.add(I_BRULURE);
m224berry_.setHealStatus(m224stringList_);
m224berry_.setPrice(200);
return m224berry_;
}
static Item m22(){
Berry m225berry_=Instances.newBerry();
m225berry_.setPrice(200);
return m225berry_;
}
static Item m23(){
Berry m226berry_=Instances.newBerry();
IdMap<Statistic,BoostHpRate> m226enumMapStatisticBoostHpRate_=new IdMap<Statistic,BoostHpRate>(new CollCapacity(5));
m226enumMapStatisticBoostHpRate_.addEntry(Statistic.ATTACK,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m226enumMapStatisticBoostHpRate_.addEntry(Statistic.SPECIAL_ATTACK,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m226enumMapStatisticBoostHpRate_.addEntry(Statistic.SPECIAL_DEFENSE,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m226enumMapStatisticBoostHpRate_.addEntry(Statistic.DEFENSE,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m226enumMapStatisticBoostHpRate_.addEntry(Statistic.SPEED,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m226berry_.setMultStat(m226enumMapStatisticBoostHpRate_);
m226berry_.setPrice(200);
return m226berry_;
}
static Item m24(){
Berry m227berry_=Instances.newBerry();
m227berry_.setHealHpRate(Rate.newRate(R_1_8));
m227berry_.setMaxHpHealingHpRate(Rate.newRate(R_1_4));
m227berry_.setPrice(200);
return m227berry_;
}
static Item m25(){
Berry m228berry_=Instances.newBerry();
m228berry_.setPrice(200);
return m228berry_;
}
static Item m26(){
Berry m229berry_=Instances.newBerry();
StringMap<Rate> m229stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m229stringMapRate_.addEntry(I_PHYSIQUE,Rate.newRate(R_1_8));
m229berry_.setDamageRateRecoilFoe(m229stringMapRate_);
m229berry_.setPrice(200);
return m229berry_;
}
static Item m27(){
Berry m230berry_=Instances.newBerry();
StringMap<EfficiencyRate> m230stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m230stringMapEfficiencyRate_.addEntry(I_SOL,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m230berry_.setMultFoesDamage(m230stringMapEfficiencyRate_);
m230berry_.setPrice(200);
return m230berry_;
}
static Item m28(){
Berry m231berry_=Instances.newBerry();
StringMap<EfficiencyRate> m231stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m231stringMapEfficiencyRate_.addEntry(I_POISON,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m231berry_.setMultFoesDamage(m231stringMapEfficiencyRate_);
m231berry_.setPrice(200);
return m231berry_;
}
static Item m29(){
Berry m232berry_=Instances.newBerry();
StringList m232stringList_=new StringList(new CollCapacity(1));
m232stringList_.add(I_CONFUSION);
m232berry_.setHealStatus(m232stringList_);
m232berry_.setPrice(200);
return m232berry_;
}
static Item m30(){
Berry m233berry_=Instances.newBerry();
m233berry_.setPrice(200);
return m233berry_;
}
static Item m31(){
Berry m234berry_=Instances.newBerry();
StringMap<EfficiencyRate> m234stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m234stringMapEfficiencyRate_.addEntry(I_TENEBRE,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m234berry_.setMultFoesDamage(m234stringMapEfficiencyRate_);
m234berry_.setPrice(200);
return m234berry_;
}
static Item m32(){
Berry m235berry_=Instances.newBerry();
IdMap<Statistic,BoostHpRate> m235enumMapStatisticBoostHpRate_=new IdMap<Statistic,BoostHpRate>(new CollCapacity(1));
m235enumMapStatisticBoostHpRate_.addEntry(Statistic.CRITICAL_HIT,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m235berry_.setMultStat(m235enumMapStatisticBoostHpRate_);
m235berry_.setPrice(200);
return m235berry_;
}
static Item m33(){
Berry m236berry_=Instances.newBerry();
IdMap<Statistic,BoostHpRate> m236enumMapStatisticBoostHpRate_=new IdMap<Statistic,BoostHpRate>(new CollCapacity(1));
m236enumMapStatisticBoostHpRate_.addEntry(Statistic.ATTACK,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m236berry_.setMultStat(m236enumMapStatisticBoostHpRate_);
m236berry_.setPrice(200);
return m236berry_;
}
static Item m34(){
Berry m237berry_=Instances.newBerry();
IdMap<Statistic,BoostHpRate> m237enumMapStatisticBoostHpRate_=new IdMap<Statistic,BoostHpRate>(new CollCapacity(1));
m237enumMapStatisticBoostHpRate_.addEntry(Statistic.DEFENSE,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m237berry_.setMultStat(m237enumMapStatisticBoostHpRate_);
m237berry_.setPrice(200);
return m237berry_;
}
static Item m35(){
Berry m238berry_=Instances.newBerry();
m238berry_.setPrice(200);
return m238berry_;
}
static Item m36(){
Berry m239berry_=Instances.newBerry();
m239berry_.setHealHpRate(Rate.newRate(R_1_8));
m239berry_.setMaxHpHealingHpRate(Rate.newRate(R_1_4));
m239berry_.setPrice(200);
return m239berry_;
}
static Item m37(){
Berry m240berry_=Instances.newBerry();
m240berry_.setPrice(200);
return m240berry_;
}
static Item m38(){
Berry m241berry_=Instances.newBerry();
StringList m241stringList_=new StringList(new CollCapacity(2));
m241stringList_.add(I_SOMMEIL);
m241stringList_.add(I_SOMMEIL_REPOS);
m241berry_.setHealStatus(m241stringList_);
m241berry_.setPrice(200);
return m241berry_;
}
static Item m39(){
Berry m242berry_=Instances.newBerry();
m242berry_.setHealPp(10);
m242berry_.setPrice(200);
return m242berry_;
}
static Item m40(){
Berry m243berry_=Instances.newBerry();
m243berry_.setWithoutFail(true);
m243berry_.setPrice(200);
return m243berry_;
}
static Item m41(){
Berry m244berry_=Instances.newBerry();
m244berry_.setPrice(200);
return m244berry_;
}
static Item m42(){
Berry m245berry_=Instances.newBerry();
m245berry_.setPrice(200);
return m245berry_;
}
static Item m43(){
Berry m246berry_=Instances.newBerry();
m246berry_.setPrice(200);
return m246berry_;
}
static Item m44(){
Berry m247berry_=Instances.newBerry();
StringMap<EfficiencyRate> m247stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m247stringMapEfficiencyRate_.addEntry(I_GLACE,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m247berry_.setMultFoesDamage(m247stringMapEfficiencyRate_);
m247berry_.setPrice(200);
return m247berry_;
}
static Item m45(){
Berry m248berry_=Instances.newBerry();
m248berry_.setHealHp(Rate.newRate(R_10));
m248berry_.setMaxHpHealingHp(Rate.newRate(R_1_4));
m248berry_.setPrice(200);
return m248berry_;
}
static Item m46(){
Berry m249berry_=Instances.newBerry();
m249berry_.setPrice(200);
return m249berry_;
}
static Item m47(){
Berry m250berry_=Instances.newBerry();
StringMap<EfficiencyRate> m250stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m250stringMapEfficiencyRate_.addEntry(I_INSECTE,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m250berry_.setMultFoesDamage(m250stringMapEfficiencyRate_);
m250berry_.setPrice(200);
return m250berry_;
}
static Item m48(){
Berry m251berry_=Instances.newBerry();
m251berry_.setHealHpRate(Rate.newRate(R_1_8));
m251berry_.setMaxHpHealingHpRate(Rate.newRate(R_1_4));
m251berry_.setPrice(200);
return m251berry_;
}
static Item m49(){
Berry m252berry_=Instances.newBerry();
StringMap<EfficiencyRate> m252stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m252stringMapEfficiencyRate_.addEntry(I_ELECTRIQUE,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m252berry_.setMultFoesDamage(m252stringMapEfficiencyRate_);
m252berry_.setPrice(200);
return m252berry_;
}
static Item m50(){
Berry m253berry_=Instances.newBerry();
StringList m253stringList_=new StringList(new CollCapacity(2));
m253stringList_.add(I_SIMPLE_POISON);
m253stringList_.add(I_POISON_GRAVE);
m253berry_.setHealStatus(m253stringList_);
m253berry_.setPrice(200);
return m253berry_;
}
static Item m51(){
Berry m254berry_=Instances.newBerry();
IdMap<Statistic,BoostHpRate> m254enumMapStatisticBoostHpRate_=new IdMap<Statistic,BoostHpRate>(new CollCapacity(1));
m254enumMapStatisticBoostHpRate_.addEntry(Statistic.SPECIAL_ATTACK,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m254berry_.setMultStat(m254enumMapStatisticBoostHpRate_);
m254berry_.setPrice(200);
return m254berry_;
}
static Item m52(){
Berry m255berry_=Instances.newBerry();
StringMap<EfficiencyRate> m255stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m255stringMapEfficiencyRate_.addEntry(I_EAU,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m255berry_.setMultFoesDamage(m255stringMapEfficiencyRate_);
m255berry_.setPrice(200);
return m255berry_;
}
static Item m53(){
Berry m256berry_=Instances.newBerry();
StringMap<Rate> m256stringMapRate_=new StringMap<Rate>(new CollCapacity(1));
m256stringMapRate_.addEntry(I_SPECIALE,Rate.newRate(R_1_8));
m256berry_.setDamageRateRecoilFoe(m256stringMapRate_);
m256berry_.setPrice(200);
return m256berry_;
}
static Item m54(){
Berry m257berry_=Instances.newBerry();
StringMap<EfficiencyRate> m257stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m257stringMapEfficiencyRate_.addEntry(I_COMBAT,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m257berry_.setMultFoesDamage(m257stringMapEfficiencyRate_);
m257berry_.setPrice(200);
return m257berry_;
}
static Item m55(){
Berry m258berry_=Instances.newBerry();
StringList m258stringList_=new StringList(new CollCapacity(8));
m258stringList_.add(I_SOMMEIL);
m258stringList_.add(I_SOMMEIL_REPOS);
m258stringList_.add(I_PARALYSIE);
m258stringList_.add(I_BRULURE);
m258stringList_.add(I_GEL);
m258stringList_.add(I_SIMPLE_POISON);
m258stringList_.add(I_POISON_GRAVE);
m258stringList_.add(I_CONFUSION);
m258berry_.setHealStatus(m258stringList_);
m258berry_.setPrice(200);
return m258berry_;
}
static Item m56(){
Berry m259berry_=Instances.newBerry();
m259berry_.setPrice(200);
return m259berry_;
}
static Item m57(){
Berry m260berry_=Instances.newBerry();
m260berry_.setPrice(200);
return m260berry_;
}
static Item m58(){
Berry m261berry_=Instances.newBerry();
m261berry_.setCategoryBoosting(I_SPECIALE);
IdMap<Statistic,Byte> m261enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m261enumMapStatisticByte_.addEntry(Statistic.SPECIAL_DEFENSE,(byte)1);
m261berry_.setBoostStatis(m261enumMapStatisticByte_);
m261berry_.setPrice(0);
return m261berry_;
}
static Item m59(){
Berry m262berry_=Instances.newBerry();
StringMap<EfficiencyRate> m262stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m262stringMapEfficiencyRate_.addEntry(I_PLANTE,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m262berry_.setMultFoesDamage(m262stringMapEfficiencyRate_);
m262berry_.setPrice(200);
return m262berry_;
}
static Item m60(){
Berry m263berry_=Instances.newBerry();
m263berry_.setPrice(200);
return m263berry_;
}
static Item m61(){
Berry m264berry_=Instances.newBerry();
m264berry_.setPrice(200);
return m264berry_;
}
static Item m62(){
Berry m265berry_=Instances.newBerry();
m265berry_.setPrice(200);
return m265berry_;
}
static Item m63(){
Berry m266berry_=Instances.newBerry();
IdMap<Statistic,BoostHpRate> m266enumMapStatisticBoostHpRate_=new IdMap<Statistic,BoostHpRate>(new CollCapacity(1));
m266enumMapStatisticBoostHpRate_.addEntry(Statistic.SPEED,new BoostHpRate((byte)1,Rate.newRate(R_1_3)));
m266berry_.setMultStat(m266enumMapStatisticBoostHpRate_);
m266berry_.setPrice(200);
return m266berry_;
}
static Item m64(){
Berry m267berry_=Instances.newBerry();
StringMap<EfficiencyRate> m267stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m267stringMapEfficiencyRate_.addEntry(I_SPECTRE,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m267berry_.setMultFoesDamage(m267stringMapEfficiencyRate_);
m267berry_.setPrice(200);
return m267berry_;
}
static Item m65(){
Berry m268berry_=Instances.newBerry();
StringMap<EfficiencyRate> m268stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m268stringMapEfficiencyRate_.addEntry(I_FEE,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m268berry_.setMultFoesDamage(m268stringMapEfficiencyRate_);
m268berry_.setPrice(0);
return m268berry_;
}
static Item m66(){
Berry m269berry_=Instances.newBerry();
m269berry_.setPrice(200);
return m269berry_;
}
static Item m67(){
Berry m270berry_=Instances.newBerry();
m270berry_.setHealHp(Rate.newRate(R_30));
m270berry_.setMaxHpHealingHp(Rate.newRate(R_1_4));
m270berry_.setPrice(200);
return m270berry_;
}
static Item m68(){
Berry m271berry_=Instances.newBerry();
m271berry_.setPrice(200);
return m271berry_;
}
static Item m69(){
Berry m272berry_=Instances.newBerry();
m272berry_.setPrice(200);
return m272berry_;
}
static Item m70(){
Berry m273berry_=Instances.newBerry();
m273berry_.setPrice(200);
return m273berry_;
}
static Item m71(){
Berry m274berry_=Instances.newBerry();
m274berry_.setHealHpRate(Rate.newRate(R_1_8));
m274berry_.setMaxHpHealingHpRate(Rate.newRate(R_1_4));
m274berry_.setPrice(200);
return m274berry_;
}
static Item m72(){
Berry m275berry_=Instances.newBerry();
StringList m275stringList_=new StringList(new CollCapacity(1));
m275stringList_.add(I_GEL);
m275berry_.setHealStatus(m275stringList_);
m275berry_.setPrice(200);
return m275berry_;
}
static Item m73(){
Berry m276berry_=Instances.newBerry();
StringMap<EfficiencyRate> m276stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m276stringMapEfficiencyRate_.addEntry(I_PSY,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m276berry_.setMultFoesDamage(m276stringMapEfficiencyRate_);
m276berry_.setPrice(200);
return m276berry_;
}
static Item m74(){
Berry m277berry_=Instances.newBerry();
StringMap<EfficiencyRate> m277stringMapEfficiencyRate_=new StringMap<EfficiencyRate>(new CollCapacity(1));
m277stringMapEfficiencyRate_.addEntry(I_NORMAL,new EfficiencyRate(Rate.newRate(R_1),Rate.newRate(R_1_2)));
m277berry_.setMultFoesDamage(m277stringMapEfficiencyRate_);
m277berry_.setPrice(200);
return m277berry_;
}
static Item m75(){
ItemForBattle m278itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m278enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m278enumMapStatisticString_.addEntry(Statistic.SPEED,R_1_2);
m278itemForBattle_.setMultStat(m278enumMapStatisticString_);
CustList<EffectWhileSendingWithStatistic> m278custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m278effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m278effectWhileSendingWithStatistic_.setMultWeight(Rate.newRate(R_2));
m278custListEffectWhileSendingWithStatistic_.add(m278effectWhileSendingWithStatistic_);
m278itemForBattle_.setEffectSending(m278custListEffectWhileSendingWithStatistic_);
m278itemForBattle_.setPrice(1000);
return m278itemForBattle_;
}
static Item m76(){
ItemForBattle m279itemForBattle_ =Instances.newItemForBattle();
StringList m279stringList_=new StringList(new CollCapacity(1));
m279stringList_.add(I_SOL);
m279itemForBattle_.setImmuTypes(m279stringList_);
m279itemForBattle_.setPrice(1000);
return m279itemForBattle_;
}
static Item m77(){
ItemForBattle m280itemForBattle_ =Instances.newItemForBattle();
m280itemForBattle_.setProtectAgainstKo(Rate.newRate(R_1));
m280itemForBattle_.setPrice(1000);
return m280itemForBattle_;
}
static Item m78(){
ItemForBattle m281itemForBattle_ =Instances.newItemForBattle();
m281itemForBattle_.setMultTrappingDamage(Rate.newRate(R_2));
m281itemForBattle_.setPrice(1000);
return m281itemForBattle_;
}
static Item m79(){
ItemForBattle m282itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m282enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m282enumMapStatisticString_.addEntry(Statistic.ATTACK,R_3_2);
m282itemForBattle_.setMultStat(m282enumMapStatisticString_);
m282itemForBattle_.setPrice(1000);
return m282itemForBattle_;
}
static Item m80(){
ItemForBattle m283itemForBattle_ =Instances.newItemForBattle();
m283itemForBattle_.setMultPower(R_11_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_CATEGORIE+RB+OC+LB+I_PHYSIQUE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PHYSIQUE+RB+OC+LB+V_ATTAQUE_CATEGORIE+RB+RP+RP);
m283itemForBattle_.setPrice(1000);
return m283itemForBattle_;
}
static Item m81(){
ItemForBattle m284itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,Short> m284enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m284enumMapStatisticShort_.addEntry(Statistic.SPECIAL_DEFENSE,(short)4);
m284itemForBattle_.setWinEvFight(m284enumMapStatisticShort_);
m284itemForBattle_.setPrice(1000);
return m284itemForBattle_;
}
static Item m82(){
ItemForBattle m285itemForBattle_ =Instances.newItemForBattle();
StatisticPokemons m285objectMapStatisticPokemonByte_=new StatisticPokemons(new CollCapacity(1));
m285objectMapStatisticPokemonByte_.addEntry(new StatisticPokemon(Statistic.CRITICAL_HIT,I_CANARTICHO),(byte)2);
m285itemForBattle_.setMultStatPokemonRank(m285objectMapStatisticPokemonByte_);
m285itemForBattle_.setPrice(1000);
return m285itemForBattle_;
}
static Item m83(){
ItemForBattle m286itemForBattle_ =Instances.newItemForBattle();
m286itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_VOL+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_VOL+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m286itemForBattle_.setPrice(1000);
return m286itemForBattle_;
}
static Item m84(){
Ball m287ball_=Instances.newBall();
m287ball_.setCatchingRate(R_2+OM+V_DEJA_CAPTURE+OP+R_1);
m287ball_.setPrice(800);
return m287ball_;
}
static Item m85(){
ItemForBattle m288itemForBattle_ =Instances.newItemForBattle();
m288itemForBattle_.setMultPower(R_11_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_PSY+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PSY+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
StringList m288stringList_=new StringList(new CollCapacity(1));
m288stringList_.add(I_MIME_JR);
m288itemForBattle_.setHatching(m288stringList_);
m288itemForBattle_.setPrice(1000);
return m288itemForBattle_;
}
static Item m86(){
ItemForBattle m289itemForBattle_ =Instances.newItemForBattle();
CustList<EffectEndRound> m289custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual m289effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
m289effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate(R_0));
m289effectEndRoundIndividual_.setRecoilDamage(Rate.newRate(R_0));
m289effectEndRoundIndividual_.setHealHp(Rate.newRate(R_0));
StringMap<Rate> m289stringMapRate_=new StringMap<Rate>(new CollCapacity(2));
m289stringMapRate_.addEntry(ES,Rate.newRate(OD+R_1_8));
m289stringMapRate_.addEntry(I_POISON,Rate.newRate(R_1_8));
m289effectEndRoundIndividual_.setHealHpByOwnerTypes(m289stringMapRate_);
m289effectEndRoundIndividual_.setUserStatusEndRound(ES);
m289effectEndRoundIndividual_.setFailEndRound(ES);
m289effectEndRoundIndividual_.setEndRoundRank(40);
m289effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
m289effectEndRoundIndividual_.setFail(ES);
m289custListEffectEndRound_.add(m289effectEndRoundIndividual_);
m289itemForBattle_.setEffectEndRound(m289custListEffectEndRound_);
m289itemForBattle_.setPrice(1000);
return m289itemForBattle_;
}
static Item m87(){
ItemForBattle m290itemForBattle_ =Instances.newItemForBattle();
StringMap<IdMap<Statistic,Byte>> m290stringMapEnumMapStatisticByte_=new StringMap<IdMap<Statistic,Byte>>(new CollCapacity(1));
IdMap<Statistic,Byte> m290enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m290enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
m290stringMapEnumMapStatisticByte_.addEntry(I_GLACE,m290enumMapStatisticByte_);
m290itemForBattle_.setBoostStatisTypes(m290stringMapEnumMapStatisticByte_);
m290itemForBattle_.setPrice(1000);
return m290itemForBattle_;
}
static Item m88(){
ItemForBattle m291itemForBattle_ =Instances.newItemForBattle();
m291itemForBattle_.setPrice(1000);
return m291itemForBattle_;
}
static Item m89(){
ItemForBattle m292itemForBattle_ =Instances.newItemForBattle();
m292itemForBattle_.setPrice(1000);
return m292itemForBattle_;
}
static Item m90(){
ItemForBattle m293itemForBattle_ =Instances.newItemForBattle();
m293itemForBattle_.setMultWinningEv(Rate.newRate(R_2));
m293itemForBattle_.setPrice(1000);
return m293itemForBattle_;
}
static Item m91(){
ItemForBattle m294itemForBattle_ =Instances.newItemForBattle();
m294itemForBattle_.setPrice(1000);
return m294itemForBattle_;
}
static Item m92(){
Boost m295boost_=Instances.newBoost();
StringMap<Short> m295stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m295stringMapShort_.addEntry(I_FAIBLO_BALL,(short)2);
m295stringMapShort_.addEntry(I_SCUBA_BALL,(short)2);
m295stringMapShort_.addEntry(I_HONOR_BALL,(short)2);
m295stringMapShort_.addEntry(I_BIS_BALL,(short)2);
m295stringMapShort_.addEntry(I_LUNE_BALL,(short)2);
m295stringMapShort_.addEntry(I_SPEED_BALL,(short)2);
m295stringMapShort_.addEntry(I_LUXE_BALL,(short)6);
m295stringMapShort_.addEntry(I_SOIN_BALL,(short)2);
m295stringMapShort_.addEntry(I_MASTER_BALL,(short)2);
m295stringMapShort_.addEntry(I_CHRONO_BALL,(short)2);
m295stringMapShort_.addEntry(I_POKE_BALL,(short)2);
m295stringMapShort_.addEntry(I_SUPER_BALL,(short)2);
m295stringMapShort_.addEntry(I_RAPIDE_BALL,(short)2);
m295stringMapShort_.addEntry(I_NIVEAU_BALL,(short)2);
m295stringMapShort_.addEntry(I_SOMBRE_BALL,(short)2);
m295stringMapShort_.addEntry(I_HYPER_BALL,(short)2);
m295stringMapShort_.addEntry(I_MASSE_BALL,(short)2);
m295stringMapShort_.addEntry(I_LOVE_BALL,(short)2);
m295stringMapShort_.addEntry(I_COMPET_BALL,(short)2);
m295stringMapShort_.addEntry(I_APPAT_BALL,(short)2);
m295stringMapShort_.addEntry(I_COPAIN_BALL,(short)2);
m295stringMapShort_.addEntry(I_FILET_BALL,(short)2);
m295boost_.setHappiness(m295stringMapShort_);
IdMap<Statistic,Short> m295enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m295enumMapStatisticShort_.addEntry(Statistic.SPECIAL_ATTACK,(short)10);
m295boost_.setEvs(m295enumMapStatisticShort_);
m295boost_.setPrice(9800);
return m295boost_;
}
static Item m93(){
ItemForBattle m296itemForBattle_ =Instances.newItemForBattle();
m296itemForBattle_.setPrice(1000);
return m296itemForBattle_;
}
static Item m94(){
Boost m297boost_=Instances.newBoost();
StringMap<Short> m297stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m297stringMapShort_.addEntry(I_FAIBLO_BALL,(short)2);
m297stringMapShort_.addEntry(I_SCUBA_BALL,(short)2);
m297stringMapShort_.addEntry(I_HONOR_BALL,(short)2);
m297stringMapShort_.addEntry(I_BIS_BALL,(short)2);
m297stringMapShort_.addEntry(I_LUNE_BALL,(short)2);
m297stringMapShort_.addEntry(I_SPEED_BALL,(short)2);
m297stringMapShort_.addEntry(I_LUXE_BALL,(short)6);
m297stringMapShort_.addEntry(I_SOIN_BALL,(short)2);
m297stringMapShort_.addEntry(I_MASTER_BALL,(short)2);
m297stringMapShort_.addEntry(I_CHRONO_BALL,(short)2);
m297stringMapShort_.addEntry(I_POKE_BALL,(short)2);
m297stringMapShort_.addEntry(I_SUPER_BALL,(short)2);
m297stringMapShort_.addEntry(I_RAPIDE_BALL,(short)2);
m297stringMapShort_.addEntry(I_NIVEAU_BALL,(short)2);
m297stringMapShort_.addEntry(I_SOMBRE_BALL,(short)2);
m297stringMapShort_.addEntry(I_HYPER_BALL,(short)2);
m297stringMapShort_.addEntry(I_MASSE_BALL,(short)2);
m297stringMapShort_.addEntry(I_LOVE_BALL,(short)2);
m297stringMapShort_.addEntry(I_COMPET_BALL,(short)2);
m297stringMapShort_.addEntry(I_APPAT_BALL,(short)2);
m297stringMapShort_.addEntry(I_COPAIN_BALL,(short)2);
m297stringMapShort_.addEntry(I_FILET_BALL,(short)2);
m297boost_.setHappiness(m297stringMapShort_);
IdMap<Statistic,Short> m297enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m297enumMapStatisticShort_.addEntry(Statistic.SPEED,(short)10);
m297boost_.setEvs(m297enumMapStatisticShort_);
m297boost_.setPrice(9800);
return m297boost_;
}
static Item m95(){
ItemForBattle m298itemForBattle_ =Instances.newItemForBattle();
m298itemForBattle_.setPrice(1000);
return m298itemForBattle_;
}
static Item m96(){
ItemForBattle m299itemForBattle_ =Instances.newItemForBattle();
m299itemForBattle_.setDamageRecoil(Rate.newRate(R_1_6));
m299itemForBattle_.setPrice(1000);
return m299itemForBattle_;
}
static Item m97(){
EvolvingItem m300evolvingItem_=Instances.newEvolvingItem();
m300evolvingItem_.setPrice(1000);
return m300evolvingItem_;
}
static Item m98(){
ItemForBattle m301itemForBattle_ =Instances.newItemForBattle();
m301itemForBattle_.setMultDamage(R_1_5+OM+A_CARACDROITEOUVERT+LP+V_COEFF_EFF+OC+R_1+RP+OP+R_1);
m301itemForBattle_.setPrice(1000);
return m301itemForBattle_;
}
static Item m99(){
ItemForBattle m302itemForBattle_ =Instances.newItemForBattle();
m302itemForBattle_.setProtectAgainstKoIfFullHp(Rate.newRate(R_1));
m302itemForBattle_.setPrice(1000);
return m302itemForBattle_;
}
static Item m100(){
ItemForBattle m303itemForBattle_ =Instances.newItemForBattle();
m303itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_COMBAT+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_COMBAT+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m303itemForBattle_.setPrice(1000);
return m303itemForBattle_;
}
static Item m101(){
ItemForBattle m304itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,Short> m304enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m304enumMapStatisticShort_.addEntry(Statistic.DEFENSE,(short)4);
m304itemForBattle_.setWinEvFight(m304enumMapStatisticShort_);
m304itemForBattle_.setPrice(1000);
return m304itemForBattle_;
}
static Item m102(){
HealingSimpleItem m305healingSimpleItem_ =Instances.newHealingSimpleItem();
m305healingSimpleItem_.setHealingTeam(true);
m305healingSimpleItem_.setPrice(20000);
return m305healingSimpleItem_;
}
static Item m103(){
ItemForBattle m306itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,Short> m306enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m306enumMapStatisticShort_.addEntry(Statistic.SPEED,(short)4);
m306itemForBattle_.setWinEvFight(m306enumMapStatisticShort_);
m306itemForBattle_.setPrice(1000);
return m306itemForBattle_;
}
static Item m104(){
EvolvingItem m307evolvingItem_=Instances.newEvolvingItem();
m307evolvingItem_.setPrice(1);
return m307evolvingItem_;
}
static Item m105(){
ItemForBattle m308itemForBattle_ =Instances.newItemForBattle();
m308itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_FEU+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_FEU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m308itemForBattle_.setPrice(1000);
return m308itemForBattle_;
}
static Item m106(){
Ball m309ball_=Instances.newBall();
m309ball_.setCatchingRate(A_CARACFERME+LP+V_TEMPS_TOUR+OC+R_0+OC+R_3+RP+OP+R_3+OM+A_CARACFERME+LP+V_TEMPS_TOUR+OC+R_4+OC+R_7+RP+OP+R_6+OM+A_CARACFERME+LP+V_TEMPS_TOUR+OC+R_8+OC+R_11+RP+OP+R_10+OM+A_CARACFERME+LP+V_TEMPS_TOUR+OC+R_12+OC+R_14+RP+OP+R_25+OM+A_CARACDROITEFERME+LP+V_TEMPS_TOUR+OC+R_15+RP);
m309ball_.setPrice(900);
return m309ball_;
}
static Item m107(){
Ball m310ball_=Instances.newBall();
m310ball_.setCatchingRate(R_1);
m310ball_.setPrice(600);
return m310ball_;
}
static Item m108(){
Ball m311ball_=Instances.newBall();
m311ball_.setCatchingRate(R_1);
m311ball_.setPrice(800);
return m311ball_;
}
static Item m109(){
ItemForBattle m312itemForBattle_ =Instances.newItemForBattle();
m312itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_DRAGON+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_DRAGON+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m312itemForBattle_.setPrice(1000);
return m312itemForBattle_;
}
static Item m110(){
EvolvingItem m313evolvingItem_=Instances.newEvolvingItem();
m313evolvingItem_.setPrice(1000);
return m313evolvingItem_;
}
static Item m111(){
ItemForBattle m314itemForBattle_ =Instances.newItemForBattle();
m314itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_PSY+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PSY+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m314itemForBattle_.setPrice(1000);
return m314itemForBattle_;
}
static Item m112(){
EvolvingItem m315evolvingItem_=Instances.newEvolvingItem();
m315evolvingItem_.setPrice(1000);
return m315evolvingItem_;
}
static Item m113(){
HealingHp m316healingHp_ =Instances.newHealingHp();
m316healingHp_.setHp(Rate.newRate(R_50));
StringMap<Short> m316stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m316stringMapShort_.addEntry(I_FAIBLO_BALL,(short)1);
m316stringMapShort_.addEntry(I_SCUBA_BALL,(short)1);
m316stringMapShort_.addEntry(I_HONOR_BALL,(short)1);
m316stringMapShort_.addEntry(I_BIS_BALL,(short)1);
m316stringMapShort_.addEntry(I_LUNE_BALL,(short)1);
m316stringMapShort_.addEntry(I_SPEED_BALL,(short)1);
m316stringMapShort_.addEntry(I_LUXE_BALL,(short)2);
m316stringMapShort_.addEntry(I_SOIN_BALL,(short)1);
m316stringMapShort_.addEntry(I_MASTER_BALL,(short)1);
m316stringMapShort_.addEntry(I_CHRONO_BALL,(short)1);
m316stringMapShort_.addEntry(I_POKE_BALL,(short)1);
m316stringMapShort_.addEntry(I_SUPER_BALL,(short)1);
m316stringMapShort_.addEntry(I_RAPIDE_BALL,(short)1);
m316stringMapShort_.addEntry(I_NIVEAU_BALL,(short)1);
m316stringMapShort_.addEntry(I_SOMBRE_BALL,(short)1);
m316stringMapShort_.addEntry(I_HYPER_BALL,(short)1);
m316stringMapShort_.addEntry(I_MASSE_BALL,(short)1);
m316stringMapShort_.addEntry(I_LOVE_BALL,(short)1);
m316stringMapShort_.addEntry(I_COMPET_BALL,(short)1);
m316stringMapShort_.addEntry(I_APPAT_BALL,(short)1);
m316stringMapShort_.addEntry(I_COPAIN_BALL,(short)1);
m316stringMapShort_.addEntry(I_FILET_BALL,(short)1);
m316healingHp_.setHappiness(m316stringMapShort_);
m316healingHp_.setPrice(200);
return m316healingHp_;
}
static Item m114(){
ItemForBattle m317itemForBattle_ =Instances.newItemForBattle();
m317itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_EAU+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_EAU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m317itemForBattle_.setPrice(1000);
return m317itemForBattle_;
}
static Item m115(){
EvolvingItem m318evolvingItem_=Instances.newEvolvingItem();
m318evolvingItem_.setPrice(1000);
return m318evolvingItem_;
}
static Item m116(){
EvolvingItem m319evolvingItem_=Instances.newEvolvingItem();
m319evolvingItem_.setPrice(1000);
return m319evolvingItem_;
}
static Item m117(){
EvolvingItem m320evolvingItem_=Instances.newEvolvingItem();
m320evolvingItem_.setPrice(1000);
return m320evolvingItem_;
}
static Item m118(){
HealingPp m321healingPp_ =Instances.newHealingPp();
m321healingPp_.setHealingAllMovesFullpp(10);
m321healingPp_.setPrice(1000);
return m321healingPp_;
}
static Item m119(){
ItemForBattle m322itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m322enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m322enumMapStatisticString_.addEntry(Statistic.EVASINESS,R_21_20);
m322itemForBattle_.setMultStat(m322enumMapStatisticString_);
StringList m322stringList_=new StringList(new CollCapacity(1));
m322stringList_.add(I_OKEOKE);
m322itemForBattle_.setHatching(m322stringList_);
m322itemForBattle_.setPrice(1000);
return m322itemForBattle_;
}
static Item m120(){
ItemForBattle m323itemForBattle_ =Instances.newItemForBattle();
m323itemForBattle_.setMultPower(R_11_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_PLANTE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PLANTE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
StringList m323stringList_=new StringList(new CollCapacity(1));
m323stringList_.add(I_ROZBOUTON);
m323itemForBattle_.setHatching(m323stringList_);
m323itemForBattle_.setPrice(1000);
return m323itemForBattle_;
}
static Item m121(){
ItemForBattle m324itemForBattle_ =Instances.newItemForBattle();
m324itemForBattle_.setMultPower(R_11_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_EAU+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_EAU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
StringList m324stringList_=new StringList(new CollCapacity(1));
m324stringList_.add(I_AZURILL);
m324itemForBattle_.setHatching(m324stringList_);
m324itemForBattle_.setPrice(1000);
return m324itemForBattle_;
}
static Item m122(){
ItemForBattle m325itemForBattle_ =Instances.newItemForBattle();
m325itemForBattle_.setAttackLast(true);
StringList m325stringList_=new StringList(new CollCapacity(1));
m325stringList_.add(I_GOINFREX);
m325itemForBattle_.setHatching(m325stringList_);
m325itemForBattle_.setPrice(1000);
return m325itemForBattle_;
}
static Item m123(){
ItemForBattle m326itemForBattle_ =Instances.newItemForBattle();
StringList m326stringList_=new StringList(new CollCapacity(1));
m326stringList_.add(I_KORILLON);
m326itemForBattle_.setHatching(m326stringList_);
m326itemForBattle_.setPrice(1000);
return m326itemForBattle_;
}
static Item m124(){
ItemForBattle m327itemForBattle_ =Instances.newItemForBattle();
m327itemForBattle_.setMultPower(R_11_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_ROCHE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ROCHE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
StringList m327stringList_=new StringList(new CollCapacity(1));
m327stringList_.add(I_MANZAI);
m327itemForBattle_.setHatching(m327stringList_);
m327itemForBattle_.setPrice(1000);
return m327itemForBattle_;
}
static Item m125(){
ItemForBattle m328itemForBattle_ =Instances.newItemForBattle();
m328itemForBattle_.setMultPower(R_11_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_EAU+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_EAU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
StringList m328stringList_=new StringList(new CollCapacity(1));
m328stringList_.add(I_BABIMANTA);
m328itemForBattle_.setHatching(m328stringList_);
m328itemForBattle_.setPrice(1000);
return m328itemForBattle_;
}
static Item m126(){
ItemForBattle m329itemForBattle_ =Instances.newItemForBattle();
StringList m329stringList_=new StringList(new CollCapacity(1));
m329stringList_.add(I_PTIRAVI);
m329itemForBattle_.setHatching(m329stringList_);
m329itemForBattle_.setPrice(1000);
return m329itemForBattle_;
}
static Item m127(){
ItemForBattle m330itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m330enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(2));
m330enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_NOM+RB+OC+LB+I_ABO+OS+I_ABRA+OS+I_ABSOL+OS+I_AFLAMANOIR+OS+I_AIKIVOL+OS+I_AIRMURE+OS+I_AMONITA+OS+I_ANCHWATT+OS+I_ANORITH+OS+I_APITRINI+OS+I_ARAKDO+OS+I_ARCEUS+OS+I_ARCHEOMIRE+OS+I_ARCKO+OS+I_ARKEAPTI+OS+I_ARTIKODIN+OS+I_ASPICOT+OS+I_AXOLOTO+OS+I_AZURILL+OS+I_BABIMANTA+OS+I_BAGGIGUANE+OS+I_BALBUTO+OS+I_BALIGNON+OS+I_BARGANTUA+OS+I_BARLOCHE+OS+I_BARPAU+OS+I_BAUDRIVE+OS+I_BLIZZI+OS+I_BOREAS+OS+I_BULBIZARRE+OS+I_CACNEA+OS+I_CADOIZO+OS+I_CANARTICHO+OS+I_CANINOS+OS+I_CAPUMAIN+OS+I_CARABING+OS+I_CARAPAGOS+OS+I_CARAPUCE+OS+I_CARATROC+OS+I_CARVANHA+OS+I_CELEBI+OS+I_CERFROUSSE+OS+I_CERIBOU+OS+I_CHACRIPAN+OS+I_CHAGLAM+OS+I_CHAMALLOT+OS+I_CHARPENTI+OS+I_CHARTOR+OS+I_CHENIPAN+OS+I_CHENIPOTTE+OS+I_CHENITI+OS+I_CHETIFLOR+OS+I_CHINCHIDOU+OS+I_CHLOROBULE+OS+I_CHOVSOURIR+OS+I_CHUCHMUR+OS+I_COBALTIUM+OS+I_COQUIPERL+OS+I_CORAYON+OS+I_CORNEBRE+OS+I_COUANETON+OS+I_COUPENOTTE+OS+I_COXY+OS+I_CRABICOQUE+OS+I_CRADOPAUD+OS+I_CREFADET+OS+I_CREFOLLET+OS+I_CREHELF+OS+I_CRESSELIA+OS+I_CRIKZIK+OS+I_CRYPTERO+OS+I_DARKRAI+OS+I_DARUMAROND+OS+I_DEBUGANT+OS+I_DEMETEROS+OS+I_DEOXYS+OS+I_DIALGA+OS+I_DINOCLIER+OS+I_DODUO+OS+I_DOUDOUVET+OS+I_DRABY+OS+I_DRAKKARMIN+OS+I_DYNAVOLT+OS+I_ECAYON+OS+I_ECRAPINCE+OS+I_ECREMEUH+OS+I_ELECTHOR+OS+I_ELEKID+OS+I_EMBRYLEX+OS+I_EMOLGA+OS+I_ENTEI+OS+I_ESCARGAUME+OS+I_ETOURMI+OS+I_EVOLI+OS+I_EXCELANGUE+OS+I_FANTOMINUS+OS+I_FARFURET+OS+I_FERMITE+OS+I_FEROSINGE+OS+I_FEUFOREVE+OS+I_FEUILLAJOU+OS+I_FLAMAJOU+OS+I_FLOTAJOU+OS+I_FOUINETTE+OS+I_FRISON+OS+I_FULGURIS+OS+I_FUNECIRE+OS+I_FURAIGLON+OS+I_GALEKID+OS+I_GENESECT+OS+I_GERMIGNON+OS+I_GIRAFARIG+OS+I_GIRATINA+OS+I_GLOUPTI+OS+I_GOBOU+OS+I_GOELISE+OS+I_GOINFREX+OS+I_GOUPIX+OS+I_GRAINIPIOT+OS+I_GRANIVOL+OS+I_GRIKNOT+OS+I_GRINDUR+OS+I_GRINGOLEM+OS+I_GROUDON+OS+I_GRUIKUI+OS+I_HEATRAN+OS+I_HERICENDRE+OS+I_HEXAGEL+OS+I_HIPPOPOTAS+OS+I_HOOTHOOT+OS+I_HO_OH+OS+I_HYPOTREMPE+OS+I_INSECATEUR+OS+I_INSOLOURDO+OS+I_JIRACHI+OS+I_JUDOKRAK+OS+I_KABUTO+OS+I_KAIMINUS+OS+I_KANGOUREX+OS+I_KARACLEE+OS+I_KECLEON+OS+I_KELDEO+OS+I_KEUNOTOR+OS+I_KICKLEE+OS+I_KOKIYAS+OS+I_KORILLON+OS+I_KRABBY+OS+I_KRAKNOIX+OS+I_KRANIDOS+OS+I_KUNGFOUINE+OS+I_KYOGRE+OS+I_KYUREM+OS+I_LAPOREILLE+OS+I_LARVEYETTE+OS+I_LATIAS+OS+I_LATIOS+OS+I_LEWSOR+OS+I_LILIA+OS+I_LIMAGMA+OS+I_LIMONDE+OS+I_LIPPOUTI+OS+I_LIXY+OS+I_LOKHLASS+OS+I_LOUPIO+OS+I_LOVDISC+OS+I_LUGIA+OS+I_LUMIVOLE+OS+I_MACHOC+OS+I_MAGBY+OS+I_MAGICARPE+OS+I_MAGNETI+OS+I_MAKUHITA+OS+I_MALOSSE+OS+I_MAMANBO+OS+I_MANAPHY+OS+I_MANGRIFF+OS+I_MANZAI+OS+I_MARACACHI+OS+I_MARCACRIN+OS+I_MASCAIMAN+OS+I_MEDHYENA+OS+I_MEDITIKKA+OS+I_MELO+OS+I_MELOETTA+OS+I_METAMORPH+OS+I_MEW+OS+I_MEWTWO+OS+I_MIAMIASME+OS+I_MIAOUSS+OS+I_MIME_JR+OS+I_MIMIGAL+OS+I_MIMITOSS+OS+I_MINIDRACO+OS+I_MORPHEO+OS+I_MOTISMA+OS+I_MOUFOUETTE+OS+I_MOUSTILLON+OS+I_MUCIOLE+OS+I_MUNNA+OS+I_MUSTEBOUEE+OS+I_MYSDIBULE+OS+I_MYSTHERBE+OS+I_NANMEOUIE+OS+I_NATU+OS+I_NEGAPI+OS+I_NENUPIOT+OS+I_NIDORAN_F+OS+I_NIDORAN_M+OS+I_NINGALE+OS+I_NIRONDELLE+OS+I_NODULITHE+OS+I_NOEUNOEUF+OS+I_NOSFERAPTI+OS+I_NUCLEOS+OS+I_OBALIE+OS+I_OKEOKE+OS+I_ONIX+OS+I_OSSELAIT+OS+I_OTARIA+OS+I_OUISTICRAM+OS+I_PACHIRISU+OS+I_PALKIA+OS+I_PARAS+OS+I_PARECOOL+OS+I_PHANPY+OS+I_PHIONE+OS+I_PIAFABEC+OS+I_PICHU+OS+I_PIJAKO+OS+I_POICHIGEON+OS+I_POISSIRENE+OS+I_POLARHUME+OS+I_POLICHOMBR+OS+I_POMDEPIK+OS+I_PONCHIOT+OS+I_PONYTA+OS+I_PORYGON+OS+I_POSIPI+OS+I_POUSSIFEU+OS+I_PSYKOKWAK+OS+I_PTERA+OS+I_PTIRAVI+OS+I_PTITARD+OS+I_PYRONILLE+OS+I_QUEULORIOR+OS+I_QWILFISH+OS+I_RACAILLOU+OS+I_RAIKOU+OS+I_RAMOLOSS+OS+I_RAPION+OS+I_RATENTIF+OS+I_RATTATA+OS+I_RAYQUAZA+OS+I_REGICE+OS+I_REGIGIGAS+OS+I_REGIROCK+OS+I_REGISTEEL+OS+I_RELICANTH+OS+I_REMORAID+OS+I_RESHIRAM+OS+I_RHINOCORNE+OS+I_RIOLU+OS+I_ROTOTAUPE+OS+I_ROUCOOL+OS+I_ROZBOUTON+OS+I_SABELETTE+OS+I_SALAMECHE+OS+I_SANCOKI+OS+I_SAQUEDENEU+OS+I_SCALPION+OS+I_SCARABRUTE+OS+I_SCARHINO+OS+I_SCORPLANE+OS+I_SCRUTELLA+OS+I_SELEROC+OS+I_SEVIPER+OS+I_SHAYMIN+OS+I_SKELENOX+OS+I_SKITTY+OS+I_SMOGO+OS+I_SNUBBULL+OS+I_SOLAROC+OS+I_SOLOCHI+OS+I_SOPORIFIK+OS+I_SORBEBE+OS+I_SPINDA+OS+I_SPIRITOMB+OS+I_SPOINK+OS+I_STALGAMIN+OS+I_STARI+OS+I_STATITIK+OS+I_SUICUNE+OS+I_SULFURA+OS+I_TADMORV+OS+I_TARINOR+OS+I_TARSAL+OS+I_TAUPIQUEUR+OS+I_TAUROS+OS+I_TEDDIURSA+OS+I_TENEFIX+OS+I_TENTACOOL+OS+I_TERHAL+OS+I_TERRAKIUM+OS+I_TIC+OS+I_TIPLOUF+OS+I_TOGEPI+OS+I_TORTIPOUSS+OS+I_TOUDOUDOU+OS+I_TOURNEGRIN+OS+I_TRITONDE+OS+I_TROMPIGNON+OS+I_TROPIUS+OS+I_TUTAFEH+OS+I_TYGNON+OS+I_TYLTON+OS+I_VENIPATTE+OS+I_VICTINI+OS+I_VIPELIERRE+OS+I_VIRIDIUM+OS+I_VISKUSE+OS+I_VIVALDAIM+OS+I_VOLTORBE+OS+I_VORTENTE+OS+I_VOSTOURNO+OS+I_WAILMER+OS+I_WATTOUAT+OS+I_YANMA+OS+I_ZARBI+OS+I_ZEBIBRON+OS+I_ZEKROM+OS+I_ZIGZATON+OS+I_ZORUA+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ABO+OS+I_ABRA+OS+I_ABSOL+OS+I_AFLAMANOIR+OS+I_AIKIVOL+OS+I_AIRMURE+OS+I_AMONITA+OS+I_ANCHWATT+OS+I_ANORITH+OS+I_APITRINI+OS+I_ARAKDO+OS+I_ARCEUS+OS+I_ARCHEOMIRE+OS+I_ARCKO+OS+I_ARKEAPTI+OS+I_ARTIKODIN+OS+I_ASPICOT+OS+I_AXOLOTO+OS+I_AZURILL+OS+I_BABIMANTA+OS+I_BAGGIGUANE+OS+I_BALBUTO+OS+I_BALIGNON+OS+I_BARGANTUA+OS+I_BARLOCHE+OS+I_BARPAU+OS+I_BAUDRIVE+OS+I_BLIZZI+OS+I_BOREAS+OS+I_BULBIZARRE+OS+I_CACNEA+OS+I_CADOIZO+OS+I_CANARTICHO+OS+I_CANINOS+OS+I_CAPUMAIN+OS+I_CARABING+OS+I_CARAPAGOS+OS+I_CARAPUCE+OS+I_CARATROC+OS+I_CARVANHA+OS+I_CELEBI+OS+I_CERFROUSSE+OS+I_CERIBOU+OS+I_CHACRIPAN+OS+I_CHAGLAM+OS+I_CHAMALLOT+OS+I_CHARPENTI+OS+I_CHARTOR+OS+I_CHENIPAN+OS+I_CHENIPOTTE+OS+I_CHENITI+OS+I_CHETIFLOR+OS+I_CHINCHIDOU+OS+I_CHLOROBULE+OS+I_CHOVSOURIR+OS+I_CHUCHMUR+OS+I_COBALTIUM+OS+I_COQUIPERL+OS+I_CORAYON+OS+I_CORNEBRE+OS+I_COUANETON+OS+I_COUPENOTTE+OS+I_COXY+OS+I_CRABICOQUE+OS+I_CRADOPAUD+OS+I_CREFADET+OS+I_CREFOLLET+OS+I_CREHELF+OS+I_CRESSELIA+OS+I_CRIKZIK+OS+I_CRYPTERO+OS+I_DARKRAI+OS+I_DARUMAROND+OS+I_DEBUGANT+OS+I_DEMETEROS+OS+I_DEOXYS+OS+I_DIALGA+OS+I_DINOCLIER+OS+I_DODUO+OS+I_DOUDOUVET+OS+I_DRABY+OS+I_DRAKKARMIN+OS+I_DYNAVOLT+OS+I_ECAYON+OS+I_ECRAPINCE+OS+I_ECREMEUH+OS+I_ELECTHOR+OS+I_ELEKID+OS+I_EMBRYLEX+OS+I_EMOLGA+OS+I_ENTEI+OS+I_ESCARGAUME+OS+I_ETOURMI+OS+I_EVOLI+OS+I_EXCELANGUE+OS+I_FANTOMINUS+OS+I_FARFURET+OS+I_FERMITE+OS+I_FEROSINGE+OS+I_FEUFOREVE+OS+I_FEUILLAJOU+OS+I_FLAMAJOU+OS+I_FLOTAJOU+OS+I_FOUINETTE+OS+I_FRISON+OS+I_FULGURIS+OS+I_FUNECIRE+OS+I_FURAIGLON+OS+I_GALEKID+OS+I_GENESECT+OS+I_GERMIGNON+OS+I_GIRAFARIG+OS+I_GIRATINA+OS+I_GLOUPTI+OS+I_GOBOU+OS+I_GOELISE+OS+I_GOINFREX+OS+I_GOUPIX+OS+I_GRAINIPIOT+OS+I_GRANIVOL+OS+I_GRIKNOT+OS+I_GRINDUR+OS+I_GRINGOLEM+OS+I_GROUDON+OS+I_GRUIKUI+OS+I_HEATRAN+OS+I_HERICENDRE+OS+I_HEXAGEL+OS+I_HIPPOPOTAS+OS+I_HOOTHOOT+OS+I_HO_OH+OS+I_HYPOTREMPE+OS+I_INSECATEUR+OS+I_INSOLOURDO+OS+I_JIRACHI+OS+I_JUDOKRAK+OS+I_KABUTO+OS+I_KAIMINUS+OS+I_KANGOUREX+OS+I_KARACLEE+OS+I_KECLEON+OS+I_KELDEO+OS+I_KEUNOTOR+OS+I_KICKLEE+OS+I_KOKIYAS+OS+I_KORILLON+OS+I_KRABBY+OS+I_KRAKNOIX+OS+I_KRANIDOS+OS+I_KUNGFOUINE+OS+I_KYOGRE+OS+I_KYUREM+OS+I_LAPOREILLE+OS+I_LARVEYETTE+OS+I_LATIAS+OS+I_LATIOS+OS+I_LEWSOR+OS+I_LILIA+OS+I_LIMAGMA+OS+I_LIMONDE+OS+I_LIPPOUTI+OS+I_LIXY+OS+I_LOKHLASS+OS+I_LOUPIO+OS+I_LOVDISC+OS+I_LUGIA+OS+I_LUMIVOLE+OS+I_MACHOC+OS+I_MAGBY+OS+I_MAGICARPE+OS+I_MAGNETI+OS+I_MAKUHITA+OS+I_MALOSSE+OS+I_MAMANBO+OS+I_MANAPHY+OS+I_MANGRIFF+OS+I_MANZAI+OS+I_MARACACHI+OS+I_MARCACRIN+OS+I_MASCAIMAN+OS+I_MEDHYENA+OS+I_MEDITIKKA+OS+I_MELO+OS+I_MELOETTA+OS+I_METAMORPH+OS+I_MEW+OS+I_MEWTWO+OS+I_MIAMIASME+OS+I_MIAOUSS+OS+I_MIME_JR+OS+I_MIMIGAL+OS+I_MIMITOSS+OS+I_MINIDRACO+OS+I_MORPHEO+OS+I_MOTISMA+OS+I_MOUFOUETTE+OS+I_MOUSTILLON+OS+I_MUCIOLE+OS+I_MUNNA+OS+I_MUSTEBOUEE+OS+I_MYSDIBULE+OS+I_MYSTHERBE+OS+I_NANMEOUIE+OS+I_NATU+OS+I_NEGAPI+OS+I_NENUPIOT+OS+I_NIDORAN_F+OS+I_NIDORAN_M+OS+I_NINGALE+OS+I_NIRONDELLE+OS+I_NODULITHE+OS+I_NOEUNOEUF+OS+I_NOSFERAPTI+OS+I_NUCLEOS+OS+I_OBALIE+OS+I_OKEOKE+OS+I_ONIX+OS+I_OSSELAIT+OS+I_OTARIA+OS+I_OUISTICRAM+OS+I_PACHIRISU+OS+I_PALKIA+OS+I_PARAS+OS+I_PARECOOL+OS+I_PHANPY+OS+I_PHIONE+OS+I_PIAFABEC+OS+I_PICHU+OS+I_PIJAKO+OS+I_POICHIGEON+OS+I_POISSIRENE+OS+I_POLARHUME+OS+I_POLICHOMBR+OS+I_POMDEPIK+OS+I_PONCHIOT+OS+I_PONYTA+OS+I_PORYGON+OS+I_POSIPI+OS+I_POUSSIFEU+OS+I_PSYKOKWAK+OS+I_PTERA+OS+I_PTIRAVI+OS+I_PTITARD+OS+I_PYRONILLE+OS+I_QUEULORIOR+OS+I_QWILFISH+OS+I_RACAILLOU+OS+I_RAIKOU+OS+I_RAMOLOSS+OS+I_RAPION+OS+I_RATENTIF+OS+I_RATTATA+OS+I_RAYQUAZA+OS+I_REGICE+OS+I_REGIGIGAS+OS+I_REGIROCK+OS+I_REGISTEEL+OS+I_RELICANTH+OS+I_REMORAID+OS+I_RESHIRAM+OS+I_RHINOCORNE+OS+I_RIOLU+OS+I_ROTOTAUPE+OS+I_ROUCOOL+OS+I_ROZBOUTON+OS+I_SABELETTE+OS+I_SALAMECHE+OS+I_SANCOKI+OS+I_SAQUEDENEU+OS+I_SCALPION+OS+I_SCARABRUTE+OS+I_SCARHINO+OS+I_SCORPLANE+OS+I_SCRUTELLA+OS+I_SELEROC+OS+I_SEVIPER+OS+I_SHAYMIN+OS+I_SKELENOX+OS+I_SKITTY+OS+I_SMOGO+OS+I_SNUBBULL+OS+I_SOLAROC+OS+I_SOLOCHI+OS+I_SOPORIFIK+OS+I_SORBEBE+OS+I_SPINDA+OS+I_SPIRITOMB+OS+I_SPOINK+OS+I_STALGAMIN+OS+I_STARI+OS+I_STATITIK+OS+I_SUICUNE+OS+I_SULFURA+OS+I_TADMORV+OS+I_TARINOR+OS+I_TARSAL+OS+I_TAUPIQUEUR+OS+I_TAUROS+OS+I_TEDDIURSA+OS+I_TENEFIX+OS+I_TENTACOOL+OS+I_TERHAL+OS+I_TERRAKIUM+OS+I_TIC+OS+I_TIPLOUF+OS+I_TOGEPI+OS+I_TORTIPOUSS+OS+I_TOUDOUDOU+OS+I_TOURNEGRIN+OS+I_TRITONDE+OS+I_TROMPIGNON+OS+I_TROPIUS+OS+I_TUTAFEH+OS+I_TYGNON+OS+I_TYLTON+OS+I_VENIPATTE+OS+I_VICTINI+OS+I_VIPELIERRE+OS+I_VIRIDIUM+OS+I_VISKUSE+OS+I_VIVALDAIM+OS+I_VOLTORBE+OS+I_VORTENTE+OS+I_VOSTOURNO+OS+I_WAILMER+OS+I_WATTOUAT+OS+I_YANMA+OS+I_ZARBI+OS+I_ZEBIBRON+OS+I_ZEKROM+OS+I_ZIGZATON+OS+I_ZORUA+RB+OC+LB+V_FIGHTER_NOM+RB+RP+RP);
m330enumMapStatisticString_.addEntry(Statistic.DEFENSE,R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_NOM+RB+OC+LB+I_ABO+OS+I_ABRA+OS+I_ABSOL+OS+I_AFLAMANOIR+OS+I_AIKIVOL+OS+I_AIRMURE+OS+I_AMONITA+OS+I_ANCHWATT+OS+I_ANORITH+OS+I_APITRINI+OS+I_ARAKDO+OS+I_ARCEUS+OS+I_ARCHEOMIRE+OS+I_ARCKO+OS+I_ARKEAPTI+OS+I_ARTIKODIN+OS+I_ASPICOT+OS+I_AXOLOTO+OS+I_AZURILL+OS+I_BABIMANTA+OS+I_BAGGIGUANE+OS+I_BALBUTO+OS+I_BALIGNON+OS+I_BARGANTUA+OS+I_BARLOCHE+OS+I_BARPAU+OS+I_BAUDRIVE+OS+I_BLIZZI+OS+I_BOREAS+OS+I_BULBIZARRE+OS+I_CACNEA+OS+I_CADOIZO+OS+I_CANARTICHO+OS+I_CANINOS+OS+I_CAPUMAIN+OS+I_CARABING+OS+I_CARAPAGOS+OS+I_CARAPUCE+OS+I_CARATROC+OS+I_CARVANHA+OS+I_CELEBI+OS+I_CERFROUSSE+OS+I_CERIBOU+OS+I_CHACRIPAN+OS+I_CHAGLAM+OS+I_CHAMALLOT+OS+I_CHARPENTI+OS+I_CHARTOR+OS+I_CHENIPAN+OS+I_CHENIPOTTE+OS+I_CHENITI+OS+I_CHETIFLOR+OS+I_CHINCHIDOU+OS+I_CHLOROBULE+OS+I_CHOVSOURIR+OS+I_CHUCHMUR+OS+I_COBALTIUM+OS+I_COQUIPERL+OS+I_CORAYON+OS+I_CORNEBRE+OS+I_COUANETON+OS+I_COUPENOTTE+OS+I_COXY+OS+I_CRABICOQUE+OS+I_CRADOPAUD+OS+I_CREFADET+OS+I_CREFOLLET+OS+I_CREHELF+OS+I_CRESSELIA+OS+I_CRIKZIK+OS+I_CRYPTERO+OS+I_DARKRAI+OS+I_DARUMAROND+OS+I_DEBUGANT+OS+I_DEMETEROS+OS+I_DEOXYS+OS+I_DIALGA+OS+I_DINOCLIER+OS+I_DODUO+OS+I_DOUDOUVET+OS+I_DRABY+OS+I_DRAKKARMIN+OS+I_DYNAVOLT+OS+I_ECAYON+OS+I_ECRAPINCE+OS+I_ECREMEUH+OS+I_ELECTHOR+OS+I_ELEKID+OS+I_EMBRYLEX+OS+I_EMOLGA+OS+I_ENTEI+OS+I_ESCARGAUME+OS+I_ETOURMI+OS+I_EVOLI+OS+I_EXCELANGUE+OS+I_FANTOMINUS+OS+I_FARFURET+OS+I_FERMITE+OS+I_FEROSINGE+OS+I_FEUFOREVE+OS+I_FEUILLAJOU+OS+I_FLAMAJOU+OS+I_FLOTAJOU+OS+I_FOUINETTE+OS+I_FRISON+OS+I_FULGURIS+OS+I_FUNECIRE+OS+I_FURAIGLON+OS+I_GALEKID+OS+I_GENESECT+OS+I_GERMIGNON+OS+I_GIRAFARIG+OS+I_GIRATINA+OS+I_GLOUPTI+OS+I_GOBOU+OS+I_GOELISE+OS+I_GOINFREX+OS+I_GOUPIX+OS+I_GRAINIPIOT+OS+I_GRANIVOL+OS+I_GRIKNOT+OS+I_GRINDUR+OS+I_GRINGOLEM+OS+I_GROUDON+OS+I_GRUIKUI+OS+I_HEATRAN+OS+I_HERICENDRE+OS+I_HEXAGEL+OS+I_HIPPOPOTAS+OS+I_HOOTHOOT+OS+I_HO_OH+OS+I_HYPOTREMPE+OS+I_INSECATEUR+OS+I_INSOLOURDO+OS+I_JIRACHI+OS+I_JUDOKRAK+OS+I_KABUTO+OS+I_KAIMINUS+OS+I_KANGOUREX+OS+I_KARACLEE+OS+I_KECLEON+OS+I_KELDEO+OS+I_KEUNOTOR+OS+I_KICKLEE+OS+I_KOKIYAS+OS+I_KORILLON+OS+I_KRABBY+OS+I_KRAKNOIX+OS+I_KRANIDOS+OS+I_KUNGFOUINE+OS+I_KYOGRE+OS+I_KYUREM+OS+I_LAPOREILLE+OS+I_LARVEYETTE+OS+I_LATIAS+OS+I_LATIOS+OS+I_LEWSOR+OS+I_LILIA+OS+I_LIMAGMA+OS+I_LIMONDE+OS+I_LIPPOUTI+OS+I_LIXY+OS+I_LOKHLASS+OS+I_LOUPIO+OS+I_LOVDISC+OS+I_LUGIA+OS+I_LUMIVOLE+OS+I_MACHOC+OS+I_MAGBY+OS+I_MAGICARPE+OS+I_MAGNETI+OS+I_MAKUHITA+OS+I_MALOSSE+OS+I_MAMANBO+OS+I_MANAPHY+OS+I_MANGRIFF+OS+I_MANZAI+OS+I_MARACACHI+OS+I_MARCACRIN+OS+I_MASCAIMAN+OS+I_MEDHYENA+OS+I_MEDITIKKA+OS+I_MELO+OS+I_MELOETTA+OS+I_METAMORPH+OS+I_MEW+OS+I_MEWTWO+OS+I_MIAMIASME+OS+I_MIAOUSS+OS+I_MIME_JR+OS+I_MIMIGAL+OS+I_MIMITOSS+OS+I_MINIDRACO+OS+I_MORPHEO+OS+I_MOTISMA+OS+I_MOUFOUETTE+OS+I_MOUSTILLON+OS+I_MUCIOLE+OS+I_MUNNA+OS+I_MUSTEBOUEE+OS+I_MYSDIBULE+OS+I_MYSTHERBE+OS+I_NANMEOUIE+OS+I_NATU+OS+I_NEGAPI+OS+I_NENUPIOT+OS+I_NIDORAN_F+OS+I_NIDORAN_M+OS+I_NINGALE+OS+I_NIRONDELLE+OS+I_NODULITHE+OS+I_NOEUNOEUF+OS+I_NOSFERAPTI+OS+I_NUCLEOS+OS+I_OBALIE+OS+I_OKEOKE+OS+I_ONIX+OS+I_OSSELAIT+OS+I_OTARIA+OS+I_OUISTICRAM+OS+I_PACHIRISU+OS+I_PALKIA+OS+I_PARAS+OS+I_PARECOOL+OS+I_PHANPY+OS+I_PHIONE+OS+I_PIAFABEC+OS+I_PICHU+OS+I_PIJAKO+OS+I_POICHIGEON+OS+I_POISSIRENE+OS+I_POLARHUME+OS+I_POLICHOMBR+OS+I_POMDEPIK+OS+I_PONCHIOT+OS+I_PONYTA+OS+I_PORYGON+OS+I_POSIPI+OS+I_POUSSIFEU+OS+I_PSYKOKWAK+OS+I_PTERA+OS+I_PTIRAVI+OS+I_PTITARD+OS+I_PYRONILLE+OS+I_QUEULORIOR+OS+I_QWILFISH+OS+I_RACAILLOU+OS+I_RAIKOU+OS+I_RAMOLOSS+OS+I_RAPION+OS+I_RATENTIF+OS+I_RATTATA+OS+I_RAYQUAZA+OS+I_REGICE+OS+I_REGIGIGAS+OS+I_REGIROCK+OS+I_REGISTEEL+OS+I_RELICANTH+OS+I_REMORAID+OS+I_RESHIRAM+OS+I_RHINOCORNE+OS+I_RIOLU+OS+I_ROTOTAUPE+OS+I_ROUCOOL+OS+I_ROZBOUTON+OS+I_SABELETTE+OS+I_SALAMECHE+OS+I_SANCOKI+OS+I_SAQUEDENEU+OS+I_SCALPION+OS+I_SCARABRUTE+OS+I_SCARHINO+OS+I_SCORPLANE+OS+I_SCRUTELLA+OS+I_SELEROC+OS+I_SEVIPER+OS+I_SHAYMIN+OS+I_SKELENOX+OS+I_SKITTY+OS+I_SMOGO+OS+I_SNUBBULL+OS+I_SOLAROC+OS+I_SOLOCHI+OS+I_SOPORIFIK+OS+I_SORBEBE+OS+I_SPINDA+OS+I_SPIRITOMB+OS+I_SPOINK+OS+I_STALGAMIN+OS+I_STARI+OS+I_STATITIK+OS+I_SUICUNE+OS+I_SULFURA+OS+I_TADMORV+OS+I_TARINOR+OS+I_TARSAL+OS+I_TAUPIQUEUR+OS+I_TAUROS+OS+I_TEDDIURSA+OS+I_TENEFIX+OS+I_TENTACOOL+OS+I_TERHAL+OS+I_TERRAKIUM+OS+I_TIC+OS+I_TIPLOUF+OS+I_TOGEPI+OS+I_TORTIPOUSS+OS+I_TOUDOUDOU+OS+I_TOURNEGRIN+OS+I_TRITONDE+OS+I_TROMPIGNON+OS+I_TROPIUS+OS+I_TUTAFEH+OS+I_TYGNON+OS+I_TYLTON+OS+I_VENIPATTE+OS+I_VICTINI+OS+I_VIPELIERRE+OS+I_VIRIDIUM+OS+I_VISKUSE+OS+I_VIVALDAIM+OS+I_VOLTORBE+OS+I_VORTENTE+OS+I_VOSTOURNO+OS+I_WAILMER+OS+I_WATTOUAT+OS+I_YANMA+OS+I_ZARBI+OS+I_ZEBIBRON+OS+I_ZEKROM+OS+I_ZIGZATON+OS+I_ZORUA+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ABO+OS+I_ABRA+OS+I_ABSOL+OS+I_AFLAMANOIR+OS+I_AIKIVOL+OS+I_AIRMURE+OS+I_AMONITA+OS+I_ANCHWATT+OS+I_ANORITH+OS+I_APITRINI+OS+I_ARAKDO+OS+I_ARCEUS+OS+I_ARCHEOMIRE+OS+I_ARCKO+OS+I_ARKEAPTI+OS+I_ARTIKODIN+OS+I_ASPICOT+OS+I_AXOLOTO+OS+I_AZURILL+OS+I_BABIMANTA+OS+I_BAGGIGUANE+OS+I_BALBUTO+OS+I_BALIGNON+OS+I_BARGANTUA+OS+I_BARLOCHE+OS+I_BARPAU+OS+I_BAUDRIVE+OS+I_BLIZZI+OS+I_BOREAS+OS+I_BULBIZARRE+OS+I_CACNEA+OS+I_CADOIZO+OS+I_CANARTICHO+OS+I_CANINOS+OS+I_CAPUMAIN+OS+I_CARABING+OS+I_CARAPAGOS+OS+I_CARAPUCE+OS+I_CARATROC+OS+I_CARVANHA+OS+I_CELEBI+OS+I_CERFROUSSE+OS+I_CERIBOU+OS+I_CHACRIPAN+OS+I_CHAGLAM+OS+I_CHAMALLOT+OS+I_CHARPENTI+OS+I_CHARTOR+OS+I_CHENIPAN+OS+I_CHENIPOTTE+OS+I_CHENITI+OS+I_CHETIFLOR+OS+I_CHINCHIDOU+OS+I_CHLOROBULE+OS+I_CHOVSOURIR+OS+I_CHUCHMUR+OS+I_COBALTIUM+OS+I_COQUIPERL+OS+I_CORAYON+OS+I_CORNEBRE+OS+I_COUANETON+OS+I_COUPENOTTE+OS+I_COXY+OS+I_CRABICOQUE+OS+I_CRADOPAUD+OS+I_CREFADET+OS+I_CREFOLLET+OS+I_CREHELF+OS+I_CRESSELIA+OS+I_CRIKZIK+OS+I_CRYPTERO+OS+I_DARKRAI+OS+I_DARUMAROND+OS+I_DEBUGANT+OS+I_DEMETEROS+OS+I_DEOXYS+OS+I_DIALGA+OS+I_DINOCLIER+OS+I_DODUO+OS+I_DOUDOUVET+OS+I_DRABY+OS+I_DRAKKARMIN+OS+I_DYNAVOLT+OS+I_ECAYON+OS+I_ECRAPINCE+OS+I_ECREMEUH+OS+I_ELECTHOR+OS+I_ELEKID+OS+I_EMBRYLEX+OS+I_EMOLGA+OS+I_ENTEI+OS+I_ESCARGAUME+OS+I_ETOURMI+OS+I_EVOLI+OS+I_EXCELANGUE+OS+I_FANTOMINUS+OS+I_FARFURET+OS+I_FERMITE+OS+I_FEROSINGE+OS+I_FEUFOREVE+OS+I_FEUILLAJOU+OS+I_FLAMAJOU+OS+I_FLOTAJOU+OS+I_FOUINETTE+OS+I_FRISON+OS+I_FULGURIS+OS+I_FUNECIRE+OS+I_FURAIGLON+OS+I_GALEKID+OS+I_GENESECT+OS+I_GERMIGNON+OS+I_GIRAFARIG+OS+I_GIRATINA+OS+I_GLOUPTI+OS+I_GOBOU+OS+I_GOELISE+OS+I_GOINFREX+OS+I_GOUPIX+OS+I_GRAINIPIOT+OS+I_GRANIVOL+OS+I_GRIKNOT+OS+I_GRINDUR+OS+I_GRINGOLEM+OS+I_GROUDON+OS+I_GRUIKUI+OS+I_HEATRAN+OS+I_HERICENDRE+OS+I_HEXAGEL+OS+I_HIPPOPOTAS+OS+I_HOOTHOOT+OS+I_HO_OH+OS+I_HYPOTREMPE+OS+I_INSECATEUR+OS+I_INSOLOURDO+OS+I_JIRACHI+OS+I_JUDOKRAK+OS+I_KABUTO+OS+I_KAIMINUS+OS+I_KANGOUREX+OS+I_KARACLEE+OS+I_KECLEON+OS+I_KELDEO+OS+I_KEUNOTOR+OS+I_KICKLEE+OS+I_KOKIYAS+OS+I_KORILLON+OS+I_KRABBY+OS+I_KRAKNOIX+OS+I_KRANIDOS+OS+I_KUNGFOUINE+OS+I_KYOGRE+OS+I_KYUREM+OS+I_LAPOREILLE+OS+I_LARVEYETTE+OS+I_LATIAS+OS+I_LATIOS+OS+I_LEWSOR+OS+I_LILIA+OS+I_LIMAGMA+OS+I_LIMONDE+OS+I_LIPPOUTI+OS+I_LIXY+OS+I_LOKHLASS+OS+I_LOUPIO+OS+I_LOVDISC+OS+I_LUGIA+OS+I_LUMIVOLE+OS+I_MACHOC+OS+I_MAGBY+OS+I_MAGICARPE+OS+I_MAGNETI+OS+I_MAKUHITA+OS+I_MALOSSE+OS+I_MAMANBO+OS+I_MANAPHY+OS+I_MANGRIFF+OS+I_MANZAI+OS+I_MARACACHI+OS+I_MARCACRIN+OS+I_MASCAIMAN+OS+I_MEDHYENA+OS+I_MEDITIKKA+OS+I_MELO+OS+I_MELOETTA+OS+I_METAMORPH+OS+I_MEW+OS+I_MEWTWO+OS+I_MIAMIASME+OS+I_MIAOUSS+OS+I_MIME_JR+OS+I_MIMIGAL+OS+I_MIMITOSS+OS+I_MINIDRACO+OS+I_MORPHEO+OS+I_MOTISMA+OS+I_MOUFOUETTE+OS+I_MOUSTILLON+OS+I_MUCIOLE+OS+I_MUNNA+OS+I_MUSTEBOUEE+OS+I_MYSDIBULE+OS+I_MYSTHERBE+OS+I_NANMEOUIE+OS+I_NATU+OS+I_NEGAPI+OS+I_NENUPIOT+OS+I_NIDORAN_F+OS+I_NIDORAN_M+OS+I_NINGALE+OS+I_NIRONDELLE+OS+I_NODULITHE+OS+I_NOEUNOEUF+OS+I_NOSFERAPTI+OS+I_NUCLEOS+OS+I_OBALIE+OS+I_OKEOKE+OS+I_ONIX+OS+I_OSSELAIT+OS+I_OTARIA+OS+I_OUISTICRAM+OS+I_PACHIRISU+OS+I_PALKIA+OS+I_PARAS+OS+I_PARECOOL+OS+I_PHANPY+OS+I_PHIONE+OS+I_PIAFABEC+OS+I_PICHU+OS+I_PIJAKO+OS+I_POICHIGEON+OS+I_POISSIRENE+OS+I_POLARHUME+OS+I_POLICHOMBR+OS+I_POMDEPIK+OS+I_PONCHIOT+OS+I_PONYTA+OS+I_PORYGON+OS+I_POSIPI+OS+I_POUSSIFEU+OS+I_PSYKOKWAK+OS+I_PTERA+OS+I_PTIRAVI+OS+I_PTITARD+OS+I_PYRONILLE+OS+I_QUEULORIOR+OS+I_QWILFISH+OS+I_RACAILLOU+OS+I_RAIKOU+OS+I_RAMOLOSS+OS+I_RAPION+OS+I_RATENTIF+OS+I_RATTATA+OS+I_RAYQUAZA+OS+I_REGICE+OS+I_REGIGIGAS+OS+I_REGIROCK+OS+I_REGISTEEL+OS+I_RELICANTH+OS+I_REMORAID+OS+I_RESHIRAM+OS+I_RHINOCORNE+OS+I_RIOLU+OS+I_ROTOTAUPE+OS+I_ROUCOOL+OS+I_ROZBOUTON+OS+I_SABELETTE+OS+I_SALAMECHE+OS+I_SANCOKI+OS+I_SAQUEDENEU+OS+I_SCALPION+OS+I_SCARABRUTE+OS+I_SCARHINO+OS+I_SCORPLANE+OS+I_SCRUTELLA+OS+I_SELEROC+OS+I_SEVIPER+OS+I_SHAYMIN+OS+I_SKELENOX+OS+I_SKITTY+OS+I_SMOGO+OS+I_SNUBBULL+OS+I_SOLAROC+OS+I_SOLOCHI+OS+I_SOPORIFIK+OS+I_SORBEBE+OS+I_SPINDA+OS+I_SPIRITOMB+OS+I_SPOINK+OS+I_STALGAMIN+OS+I_STARI+OS+I_STATITIK+OS+I_SUICUNE+OS+I_SULFURA+OS+I_TADMORV+OS+I_TARINOR+OS+I_TARSAL+OS+I_TAUPIQUEUR+OS+I_TAUROS+OS+I_TEDDIURSA+OS+I_TENEFIX+OS+I_TENTACOOL+OS+I_TERHAL+OS+I_TERRAKIUM+OS+I_TIC+OS+I_TIPLOUF+OS+I_TOGEPI+OS+I_TORTIPOUSS+OS+I_TOUDOUDOU+OS+I_TOURNEGRIN+OS+I_TRITONDE+OS+I_TROMPIGNON+OS+I_TROPIUS+OS+I_TUTAFEH+OS+I_TYGNON+OS+I_TYLTON+OS+I_VENIPATTE+OS+I_VICTINI+OS+I_VIPELIERRE+OS+I_VIRIDIUM+OS+I_VISKUSE+OS+I_VIVALDAIM+OS+I_VOLTORBE+OS+I_VORTENTE+OS+I_VOSTOURNO+OS+I_WAILMER+OS+I_WATTOUAT+OS+I_YANMA+OS+I_ZARBI+OS+I_ZEBIBRON+OS+I_ZEKROM+OS+I_ZIGZATON+OS+I_ZORUA+RB+OC+LB+V_FIGHTER_NOM+RB+RP+RP);
m330itemForBattle_.setMultStat(m330enumMapStatisticString_);
m330itemForBattle_.setPrice(1000);
return m330itemForBattle_;
}
static Item m128(){
Ball m331ball_=Instances.newBall();
m331ball_.setCatchingRate(A_PUIS+LP+V_PK_SAUVAGE_NIVEAU+OC+OD+R_3_2+RP+OP+R_2);
m331ball_.setPrice(400);
return m331ball_;
}
static Item m129(){
Boost m332boost_=Instances.newBoost();
StringMap<Short> m332stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m332stringMapShort_.addEntry(I_FAIBLO_BALL,(short)2);
m332stringMapShort_.addEntry(I_SCUBA_BALL,(short)2);
m332stringMapShort_.addEntry(I_HONOR_BALL,(short)2);
m332stringMapShort_.addEntry(I_BIS_BALL,(short)2);
m332stringMapShort_.addEntry(I_LUNE_BALL,(short)2);
m332stringMapShort_.addEntry(I_SPEED_BALL,(short)2);
m332stringMapShort_.addEntry(I_LUXE_BALL,(short)6);
m332stringMapShort_.addEntry(I_SOIN_BALL,(short)2);
m332stringMapShort_.addEntry(I_MASTER_BALL,(short)2);
m332stringMapShort_.addEntry(I_CHRONO_BALL,(short)2);
m332stringMapShort_.addEntry(I_POKE_BALL,(short)2);
m332stringMapShort_.addEntry(I_SUPER_BALL,(short)2);
m332stringMapShort_.addEntry(I_RAPIDE_BALL,(short)2);
m332stringMapShort_.addEntry(I_NIVEAU_BALL,(short)2);
m332stringMapShort_.addEntry(I_SOMBRE_BALL,(short)2);
m332stringMapShort_.addEntry(I_HYPER_BALL,(short)2);
m332stringMapShort_.addEntry(I_MASSE_BALL,(short)2);
m332stringMapShort_.addEntry(I_LOVE_BALL,(short)2);
m332stringMapShort_.addEntry(I_COMPET_BALL,(short)2);
m332stringMapShort_.addEntry(I_APPAT_BALL,(short)2);
m332stringMapShort_.addEntry(I_COPAIN_BALL,(short)2);
m332stringMapShort_.addEntry(I_FILET_BALL,(short)2);
m332boost_.setHappiness(m332stringMapShort_);
IdMap<Statistic,Short> m332enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m332enumMapStatisticShort_.addEntry(Statistic.DEFENSE,(short)10);
m332boost_.setEvs(m332enumMapStatisticShort_);
m332boost_.setPrice(9800);
return m332boost_;
}
static Item m130(){
ItemForBattle m333itemForBattle_ =Instances.newItemForBattle();
m333itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_ACIER+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ACIER+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m333itemForBattle_.setPrice(1000);
return m333itemForBattle_;
}
static Item m131(){
Ball m334ball_=Instances.newBall();
m334ball_.setCatchingRate(R_3+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_PK_SAUVAGE_TYPES_BASE+RB+OC+LB+I_EAU+OS+I_INSECTE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_EAU+OS+I_INSECTE+RB+OC+LB+V_PK_SAUVAGE_TYPES_BASE+RB+RP+RP);
m334ball_.setPrice(900);
return m334ball_;
}
static Item m132(){
Fossil m335fossil_ =Instances.newFossil();
m335fossil_.setPokemon(I_DINOCLIER);
m335fossil_.setLevel((short)5);
m335fossil_.setPrice(2500);
return m335fossil_;
}
static Item m133(){
Fossil m336fossil_ =Instances.newFossil();
m336fossil_.setPokemon(I_KRANIDOS);
m336fossil_.setLevel((short)5);
m336fossil_.setPrice(2500);
return m336fossil_;
}
static Item m134(){
Fossil m337fossil_ =Instances.newFossil();
m337fossil_.setPokemon(I_KABUTO);
m337fossil_.setLevel((short)5);
m337fossil_.setPrice(1000);
return m337fossil_;
}
static Item m135(){
Fossil m338fossil_ =Instances.newFossil();
m338fossil_.setPokemon(I_ANORITH);
m338fossil_.setLevel((short)5);
m338fossil_.setPrice(2000);
return m338fossil_;
}
static Item m136(){
Fossil m339fossil_ =Instances.newFossil();
m339fossil_.setPokemon(I_CARAPAGOS);
m339fossil_.setLevel((short)5);
m339fossil_.setPrice(4000);
return m339fossil_;
}
static Item m137(){
Fossil m340fossil_ =Instances.newFossil();
m340fossil_.setPokemon(I_ARKEAPTI);
m340fossil_.setLevel((short)5);
m340fossil_.setPrice(4000);
return m340fossil_;
}
static Item m138(){
Fossil m341fossil_ =Instances.newFossil();
m341fossil_.setPokemon(I_LILIA);
m341fossil_.setLevel((short)5);
m341fossil_.setPrice(2000);
return m341fossil_;
}
static Item m139(){
ItemForBattle m342itemForBattle_ =Instances.newItemForBattle();
m342itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_GLACE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_GLACE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m342itemForBattle_.setPrice(1000);
return m342itemForBattle_;
}
static Item m140(){
ItemForBattle m343itemForBattle_ =Instances.newItemForBattle();
m343itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_PLANTE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PLANTE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m343itemForBattle_.setPrice(1000);
return m343itemForBattle_;
}
static Item m141(){
SellingItem m344sellingItem_ =Instances.newSellingItem();
m344sellingItem_.setPrice(3750);
return m344sellingItem_;
}
static Item m142(){
ItemForBattle m345itemForBattle_ =Instances.newItemForBattle();
m345itemForBattle_.setDrainedHpByDamageRate(Rate.newRate(R_1_8));
m345itemForBattle_.setPrice(1000);
return m345itemForBattle_;
}
static Item m143(){
ItemForBattle m346itemForBattle_ =Instances.newItemForBattle();
m346itemForBattle_.setMultWinningHappiness(Rate.newRate(R_2));
m346itemForBattle_.setPrice(1000);
return m346itemForBattle_;
}
static Item m144(){
EvolvingItem m347evolvingItem_=Instances.newEvolvingItem();
m347evolvingItem_.setPrice(1000);
return m347evolvingItem_;
}
static Item m145(){
ItemForBattle m348itemForBattle_ =Instances.newItemForBattle();
m348itemForBattle_.setMultDrainedHp(Rate.newRate(R_2));
m348itemForBattle_.setPrice(1000);
return m348itemForBattle_;
}
static Item m146(){
SellingItem m349sellingItem_ =Instances.newSellingItem();
m349sellingItem_.setPrice(2500);
return m349sellingItem_;
}
static Item m147(){
HealingHpStatus m350healingHpStatus_ =Instances.newHealingHpStatus();
m350healingHpStatus_.setHealedHpRate(Rate.newRate(R_1));
StringList m350stringList_=new StringList(new CollCapacity(7));
m350stringList_.add(I_SOMMEIL);
m350stringList_.add(I_SOMMEIL_REPOS);
m350stringList_.add(I_PARALYSIE);
m350stringList_.add(I_SIMPLE_POISON);
m350stringList_.add(I_POISON_GRAVE);
m350stringList_.add(I_BRULURE);
m350stringList_.add(I_GEL);
m350healingHpStatus_.setStatus(m350stringList_);
m350healingHpStatus_.setHealingKo(false);
m350healingHpStatus_.setPrice(3000);
return m350healingHpStatus_;
}
static Item m148(){
ItemForBattle m351itemForBattle_ =Instances.newItemForBattle();
m351itemForBattle_.setImmuLowStatis(true);
m351itemForBattle_.setPrice(1000);
return m351itemForBattle_;
}
static Item m149(){
ItemForBattle m352itemForBattle_ =Instances.newItemForBattle();
StringList m352stringList_=new StringList(new CollCapacity(1));
m352stringList_.add(I_AMOUR);
m352itemForBattle_.setImmuStatus(m352stringList_);
m352itemForBattle_.setPrice(1000);
return m352itemForBattle_;
}
static Item m150(){
ItemForBattle m353itemForBattle_ =Instances.newItemForBattle();
m353itemForBattle_.setAttacksSoon(true);
m353itemForBattle_.setPrice(1000);
return m353itemForBattle_;
}
static Item m151(){
Ball m354ball_=Instances.newBall();
m354ball_.setCatchingRate(R_5_4);
m354ball_.setPrice(300);
return m354ball_;
}
static Item m152(){
HealingPp m355healingPp_ =Instances.newHealingPp();
m355healingPp_.setHealedMovePp(10);
m355healingPp_.setPrice(500);
return m355healingPp_;
}
static Item m153(){
HealingPp m356healingPp_ =Instances.newHealingPp();
m356healingPp_.setHealingMoveFullpp(true);
m356healingPp_.setPrice(1100);
return m356healingPp_;
}
static Item m154(){
Ball m357ball_=Instances.newBall();
m357ball_.setCatchingRate(R_2);
m357ball_.setPrice(1000);
return m357ball_;
}
static Item m155(){
HealingHp m358healingHp_ =Instances.newHealingHp();
m358healingHp_.setHp(Rate.newRate(R_200));
m358healingHp_.setPrice(1200);
return m358healingHp_;
}
static Item m156(){
HealingHp m359healingHp_ =Instances.newHealingHp();
m359healingHp_.setHp(Rate.newRate(R_20));
m359healingHp_.setPrice(250);
return m359healingHp_;
}
static Item m157(){
HealingHp m360healingHp_ =Instances.newHealingHp();
m360healingHp_.setHp(Rate.newRate(R_100));
StringMap<Short> m360stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m360stringMapShort_.addEntry(I_FAIBLO_BALL,(short)1);
m360stringMapShort_.addEntry(I_SCUBA_BALL,(short)1);
m360stringMapShort_.addEntry(I_HONOR_BALL,(short)1);
m360stringMapShort_.addEntry(I_BIS_BALL,(short)1);
m360stringMapShort_.addEntry(I_LUNE_BALL,(short)1);
m360stringMapShort_.addEntry(I_SPEED_BALL,(short)1);
m360stringMapShort_.addEntry(I_LUXE_BALL,(short)2);
m360stringMapShort_.addEntry(I_SOIN_BALL,(short)1);
m360stringMapShort_.addEntry(I_MASTER_BALL,(short)1);
m360stringMapShort_.addEntry(I_CHRONO_BALL,(short)1);
m360stringMapShort_.addEntry(I_POKE_BALL,(short)1);
m360stringMapShort_.addEntry(I_SUPER_BALL,(short)1);
m360stringMapShort_.addEntry(I_RAPIDE_BALL,(short)1);
m360stringMapShort_.addEntry(I_NIVEAU_BALL,(short)1);
m360stringMapShort_.addEntry(I_SOMBRE_BALL,(short)1);
m360stringMapShort_.addEntry(I_HYPER_BALL,(short)1);
m360stringMapShort_.addEntry(I_MASSE_BALL,(short)1);
m360stringMapShort_.addEntry(I_LOVE_BALL,(short)1);
m360stringMapShort_.addEntry(I_COMPET_BALL,(short)1);
m360stringMapShort_.addEntry(I_APPAT_BALL,(short)1);
m360stringMapShort_.addEntry(I_COPAIN_BALL,(short)1);
m360stringMapShort_.addEntry(I_FILET_BALL,(short)1);
m360healingHp_.setHappiness(m360stringMapShort_);
m360healingHp_.setPrice(500);
return m360healingHp_;
}
static Item m158(){
ItemForBattle m361itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,Byte> m361enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m361enumMapStatisticByte_.addEntry(Statistic.CRITICAL_HIT,(byte)1);
m361itemForBattle_.setMultStatRank(m361enumMapStatisticByte_);
m361itemForBattle_.setPrice(1000);
return m361itemForBattle_;
}
static Item m159(){
ItemForBattle m362itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m362enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m362enumMapStatisticString_.addEntry(Statistic.ACCURACY,R_6_5+OM+V_FIGHTER_DER_JOUE+OP+LP+R_1+OD+V_FIGHTER_DER_JOUE+RP);
m362itemForBattle_.setMultStat(m362enumMapStatisticString_);
m362itemForBattle_.setPrice(1000);
return m362itemForBattle_;
}
static Item m160(){
ItemForBattle m363itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,Short> m363enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m363enumMapStatisticShort_.addEntry(Statistic.SPECIAL_ATTACK,(short)4);
m363itemForBattle_.setWinEvFight(m363enumMapStatisticShort_);
m363itemForBattle_.setPrice(1000);
return m363itemForBattle_;
}
static Item m161(){
ItemForBattle m364itemForBattle_ =Instances.newItemForBattle();
StringMap<IdMap<Statistic,Byte>> m364stringMapEnumMapStatisticByte_=new StringMap<IdMap<Statistic,Byte>>(new CollCapacity(1));
IdMap<Statistic,Byte> m364enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m364enumMapStatisticByte_.addEntry(Statistic.SPECIAL_DEFENSE,(byte)1);
m364stringMapEnumMapStatisticByte_.addEntry(I_EAU,m364enumMapStatisticByte_);
m364itemForBattle_.setBoostStatisTypes(m364stringMapEnumMapStatisticByte_);
m364itemForBattle_.setPrice(1000);
return m364itemForBattle_;
}
static Item m162(){
HealingHp m365healingHp_ =Instances.newHealingHp();
m365healingHp_.setHp(Rate.newRate(R_80));
StringMap<Short> m365stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m365stringMapShort_.addEntry(I_FAIBLO_BALL,(short)1);
m365stringMapShort_.addEntry(I_SCUBA_BALL,(short)1);
m365stringMapShort_.addEntry(I_HONOR_BALL,(short)1);
m365stringMapShort_.addEntry(I_BIS_BALL,(short)1);
m365stringMapShort_.addEntry(I_LUNE_BALL,(short)1);
m365stringMapShort_.addEntry(I_SPEED_BALL,(short)1);
m365stringMapShort_.addEntry(I_LUXE_BALL,(short)2);
m365stringMapShort_.addEntry(I_SOIN_BALL,(short)1);
m365stringMapShort_.addEntry(I_MASTER_BALL,(short)1);
m365stringMapShort_.addEntry(I_CHRONO_BALL,(short)1);
m365stringMapShort_.addEntry(I_POKE_BALL,(short)1);
m365stringMapShort_.addEntry(I_SUPER_BALL,(short)1);
m365stringMapShort_.addEntry(I_RAPIDE_BALL,(short)1);
m365stringMapShort_.addEntry(I_NIVEAU_BALL,(short)1);
m365stringMapShort_.addEntry(I_SOMBRE_BALL,(short)1);
m365stringMapShort_.addEntry(I_HYPER_BALL,(short)1);
m365stringMapShort_.addEntry(I_MASSE_BALL,(short)1);
m365stringMapShort_.addEntry(I_LOVE_BALL,(short)1);
m365stringMapShort_.addEntry(I_COMPET_BALL,(short)1);
m365stringMapShort_.addEntry(I_APPAT_BALL,(short)1);
m365stringMapShort_.addEntry(I_COPAIN_BALL,(short)1);
m365stringMapShort_.addEntry(I_FILET_BALL,(short)1);
m365healingHp_.setHappiness(m365stringMapShort_);
m365healingHp_.setPrice(350);
return m365healingHp_;
}
static Item m163(){
ItemForBattle m366itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m366enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m366enumMapStatisticString_.addEntry(Statistic.ACCURACY,R_11_10);
m366itemForBattle_.setMultStat(m366enumMapStatisticString_);
m366itemForBattle_.setPrice(1000);
return m366itemForBattle_;
}
static Item m164(){
Ball m367ball_=Instances.newBall();
m367ball_.setCatchingRate(R_8+OM+A_CARACFERME+LP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_NO_GENDER+RB+OC+A_UNION+LP+LB+V_PK_UT_GENRE+RB+OC+LB+V_PK_SAUVAGE_GENRE+RB+RP+RP+RP+OC+R_2+OC+R_2+RP+OP+A_CARACFERME+LP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_NO_GENDER+RB+OC+A_UNION+LP+LB+V_PK_UT_GENRE+RB+OC+LB+V_PK_SAUVAGE_GENRE+RB+RP+RP+RP+OC+R_0+OC+R_1+RP);
m367ball_.setPrice(700);
return m367ball_;
}
static Item m165(){
ItemForBattle m368itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> m368stringMapShort_=new StringMap<Short>(new CollCapacity(2));
m368stringMapShort_.addEntry(I_PROTECTION,(short)3);
m368stringMapShort_.addEntry(I_MUR_LUMIERE,(short)3);
m368itemForBattle_.setIncreasingMaxNbRoundTeamMove(m368stringMapShort_);
m368itemForBattle_.setPrice(1000);
return m368itemForBattle_;
}
static Item m166(){
ItemForBattle m369itemForBattle_ =Instances.newItemForBattle();
StringList m369stringList_=new StringList(new CollCapacity(1));
m369stringList_.add(I_POUDRE_TOXIK);
m369itemForBattle_.setImmuMoves(m369stringList_);
m369stringList_=new StringList(new CollCapacity(1));
m369stringList_.add(I_TEMPETESABLE);
m369itemForBattle_.setImmuWeather(m369stringList_);
m369itemForBattle_.setPrice(1000);
return m369itemForBattle_;
}
static Item m167(){
ItemForBattle m370itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m370enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m370enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,R_3_2);
m370itemForBattle_.setMultStat(m370enumMapStatisticString_);
m370itemForBattle_.setPrice(1000);
return m370itemForBattle_;
}
static Item m168(){
ItemForBattle m371itemForBattle_ =Instances.newItemForBattle();
m371itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_TENEBRE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_TENEBRE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m371itemForBattle_.setPrice(1000);
return m371itemForBattle_;
}
static Item m169(){
ItemForBattle m372itemForBattle_ =Instances.newItemForBattle();
m372itemForBattle_.setMultPower(R_11_10+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_CATEGORIE+RB+OC+LB+I_SPECIALE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_SPECIALE+RB+OC+LB+V_ATTAQUE_CATEGORIE+RB+RP+RP);
m372itemForBattle_.setPrice(1000);
return m372itemForBattle_;
}
static Item m170(){
Ball m373ball_=Instances.newBall();
m373ball_.setCatchingRate(R_4+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_PK_SAUVAGE_PIERRES_EVOS+RB+OC+LB+I_PIERRE_LUNE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PIERRE_LUNE+RB+OC+LB+V_PK_SAUVAGE_PIERRES_EVOS+RB+RP+RP);
m373ball_.setPrice(700);
return m373ball_;
}
static Item m171(){
Ball m374ball_=Instances.newBall();
m374ball_.setCatchingRate(R_5_4);
m374ball_.setPrice(1000);
return m374ball_;
}
static Item m172(){
EvolvingItem m375evolvingItem_=Instances.newEvolvingItem();
m375evolvingItem_.setPrice(1000);
return m375evolvingItem_;
}
static Item m173(){
Ball m376ball_=Instances.newBall();
m376ball_.setCatchingRate(LP+R_2+OM+A_DIV+LP+V_PK_SAUVAGE_MASSE+OC+V_MASSE_MOYENNE_PK+RP+OP+R_1+RP+OM+R_10);
m376ball_.setPrice(800);
return m376ball_;
}
static Item m174(){
ItemForBattle m377itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m377enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m377enumMapStatisticString_.addEntry(Statistic.ATTACK,R_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_NOM+RB+OC+LB+I_OSSATUEUR+OS+I_OSSELAIT+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_OSSATUEUR+OS+I_OSSELAIT+RB+OC+LB+V_FIGHTER_NOM+RB+RP+RP);
m377itemForBattle_.setMultStat(m377enumMapStatisticString_);
m377itemForBattle_.setPrice(1000);
return m377itemForBattle_;
}
static Item m175(){
Ball m378ball_=Instances.newBall();
m378ball_.setCatchingRate(R_255);
m378ball_.setPrice(0);
return m378ball_;
}
static Item m176(){
HealingPp m379healingPp_ =Instances.newHealingPp();
m379healingPp_.setHealingAllMovesPp(true);
m379healingPp_.setPrice(3000);
return m379healingPp_;
}
static Item m177(){
Repel m380repel_ =Instances.newRepel();
m380repel_.setSteps(500);
m380repel_.setPrice(800);
return m380repel_;
}
static Item m178(){
ItemForBattle m381itemForBattle_ =Instances.newItemForBattle();
m381itemForBattle_.setMultDamage(A_DIV+LP+V_NB_UTILISATION_CONSECUTIF+OC+R_10+RP+OP+R_11_10);
m381itemForBattle_.setPrice(1000);
return m381itemForBattle_;
}
static Item m179(){
ItemForBattle m382itemForBattle_ =Instances.newItemForBattle();
m382itemForBattle_.setPrice(200);
return m382itemForBattle_;
}
static Item m180(){
ItemForBattle m383itemForBattle_ =Instances.newItemForBattle();
m383itemForBattle_.setPrice(200);
return m383itemForBattle_;
}
static Item m181(){
ItemForBattle m384itemForBattle_ =Instances.newItemForBattle();
m384itemForBattle_.setPrice(200);
return m384itemForBattle_;
}
static Item m182(){
ItemForBattle m385itemForBattle_ =Instances.newItemForBattle();
m385itemForBattle_.setPrice(200);
return m385itemForBattle_;
}
static Item m183(){
SellingItem m386sellingItem_ =Instances.newSellingItem();
m386sellingItem_.setPrice(4900);
return m386sellingItem_;
}
static Item m184(){
ItemForBattle m387itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m387enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m387enumMapStatisticString_.addEntry(Statistic.SPEED,R_3_2);
m387itemForBattle_.setMultStat(m387enumMapStatisticString_);
m387itemForBattle_.setPrice(1000);
return m387itemForBattle_;
}
static Item m185(){
ItemForBattle m388itemForBattle_ =Instances.newItemForBattle();
m388itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_NORMAL+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_NORMAL+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m388itemForBattle_.setPrice(1000);
return m388itemForBattle_;
}
static Item m186(){
ItemForBattle m389itemForBattle_ =Instances.newItemForBattle();
m389itemForBattle_.setBoostExp(true);
m389itemForBattle_.setPrice(1000);
return m389itemForBattle_;
}
static Item m187(){
Fossil m390fossil_ =Instances.newFossil();
m390fossil_.setPokemon(I_AMONITA);
m390fossil_.setLevel((short)5);
m390fossil_.setPrice(1000);
return m390fossil_;
}
static Item m188(){
Ball m391ball_=Instances.newBall();
m391ball_.setCatchingRate(A_PUIS+LP+R_2+OC+A_MIN+LP+A_ENT+LP+A_DIV+LP+V_PK_UT_NIVEAU+OC+V_PK_SAUVAGE_NIVEAU+RP+RP+OC+R_8+RP+RP);
m391ball_.setPrice(750);
return m391ball_;
}
static Item m189(){
ItemForBattle m392itemForBattle_ =Instances.newItemForBattle();
StringList m392stringList_=new StringList(new CollCapacity(1));
m392stringList_.add(I_AMOUR);
m392itemForBattle_.setSynchroStatus(m392stringList_);
StringMap<String> m392stringMapString_=new StringMap<String>(new CollCapacity(1));
m392stringMapString_.addEntry(I_AMOUR,V_EXISTE_GENRE_ASSEXUE+OO+V_GENRES_EGAUX+OO+V_CIBLE_POSSEDE_STATUT_RELATION+SE+I_AMOUR);
m392itemForBattle_.setFailStatus(m392stringMapString_);
m392itemForBattle_.setPrice(1000);
return m392itemForBattle_;
}
static Item m190(){
ItemForBattle m393itemForBattle_ =Instances.newItemForBattle();
m393itemForBattle_.setMultWinningExp(Rate.newRate(R_3_2));
m393itemForBattle_.setPrice(1000);
return m393itemForBattle_;
}
static Item m191(){
ItemForBattle m394itemForBattle_ =Instances.newItemForBattle();
m394itemForBattle_.setMultPower(A_CARDINAL+LP+A_INTER+LP+LB+V_LANCEUR_NOM+RB+OC+LB+I_DIALGA+RB+RP+RP+OM+LP+R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_DRAGON+OS+I_ACIER+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_DRAGON+OS+I_ACIER+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_DIALGA+RB+OC+LB+V_LANCEUR_NOM+RB+RP+RP);
m394itemForBattle_.setPrice(1000);
return m394itemForBattle_;
}
static Item m192(){
ItemForBattle m395itemForBattle_ =Instances.newItemForBattle();
CustList<EffectEndRound> m395custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual m395effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
m395effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate(R_0));
m395effectEndRoundIndividual_.setRecoilDamage(Rate.newRate(R_0));
m395effectEndRoundIndividual_.setHealHp(Rate.newRate(R_0));
m395effectEndRoundIndividual_.setUserStatusEndRound(I_BRULURE);
m395effectEndRoundIndividual_.setFailEndRound(ES);
m395effectEndRoundIndividual_.setEndRoundRank(52);
m395effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
m395effectEndRoundIndividual_.setFail(ES);
m395custListEffectEndRound_.add(m395effectEndRoundIndividual_);
m395itemForBattle_.setEffectEndRound(m395custListEffectEndRound_);
m395itemForBattle_.setPrice(1000);
return m395itemForBattle_;
}
static Item m193(){
ItemForBattle m396itemForBattle_ =Instances.newItemForBattle();
m396itemForBattle_.setMultPower(A_CARDINAL+LP+A_INTER+LP+LB+V_LANCEUR_NOM+RB+OC+LB+I_PALKIA+RB+RP+RP+OM+LP+R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_DRAGON+OS+I_EAU+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_DRAGON+OS+I_EAU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PALKIA+RB+OC+LB+V_LANCEUR_NOM+RB+RP+RP);
m396itemForBattle_.setPrice(1000);
return m396itemForBattle_;
}
static Item m194(){
ItemForBattle m397itemForBattle_ =Instances.newItemForBattle();
m397itemForBattle_.setMultPower(A_CARDINAL+LP+A_INTER+LP+LB+V_LANCEUR_NOM+RB+OC+LB+I_GIRATINA+RB+RP+RP+OM+LP+R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_SPECTRE+OS+I_DRAGON+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_SPECTRE+OS+I_DRAGON+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_GIRATINA+RB+OC+LB+V_LANCEUR_NOM+RB+RP+RP);
m397itemForBattle_.setPrice(1000);
return m397itemForBattle_;
}
static Item m195(){
ItemForBattle m398itemForBattle_ =Instances.newItemForBattle();
CustList<EffectEndRound> m398custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual m398effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
m398effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate(R_0));
m398effectEndRoundIndividual_.setRecoilDamage(Rate.newRate(R_0));
m398effectEndRoundIndividual_.setHealHp(Rate.newRate(R_0));
m398effectEndRoundIndividual_.setUserStatusEndRound(I_POISON_GRAVE);
m398effectEndRoundIndividual_.setFailEndRound(ES);
m398effectEndRoundIndividual_.setEndRoundRank(53);
m398effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
m398effectEndRoundIndividual_.setFail(ES);
m398custListEffectEndRound_.add(m398effectEndRoundIndividual_);
m398itemForBattle_.setEffectEndRound(m398custListEffectEndRound_);
m398itemForBattle_.setPrice(1000);
return m398itemForBattle_;
}
static Item m196(){
ItemForBattle m399itemForBattle_ =Instances.newItemForBattle();
m399itemForBattle_.setMultDamage(R_13_10);
CustList<EffectEndRound> m399custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual m399effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
m399effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate(R_0));
m399effectEndRoundIndividual_.setRecoilDamage(Rate.newRate(R_1_10));
m399effectEndRoundIndividual_.setHealHp(Rate.newRate(R_0));
m399effectEndRoundIndividual_.setUserStatusEndRound(ES);
m399effectEndRoundIndividual_.setFailEndRound(ES);
m399effectEndRoundIndividual_.setEndRoundRank(51);
m399effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
m399effectEndRoundIndividual_.setFail(ES);
m399custListEffectEndRound_.add(m399effectEndRoundIndividual_);
m399itemForBattle_.setEffectEndRound(m399custListEffectEndRound_);
m399itemForBattle_.setPrice(1000);
return m399itemForBattle_;
}
static Item m197(){
SellingItem m400sellingItem_ =Instances.newSellingItem();
m400sellingItem_.setPrice(5000);
return m400sellingItem_;
}
static Item m198(){
EvolvingItem m401evolvingItem_=Instances.newEvolvingItem();
m401evolvingItem_.setPrice(1000);
return m401evolvingItem_;
}
static Item m199(){
SellingItem m402sellingItem_ =Instances.newSellingItem();
m402sellingItem_.setPrice(5000);
return m402sellingItem_;
}
static Item m200(){
SellingItem m403sellingItem_ =Instances.newSellingItem();
m403sellingItem_.setPrice(700);
return m403sellingItem_;
}
static Item m201(){
SellingItem m404sellingItem_ =Instances.newSellingItem();
m404sellingItem_.setPrice(250);
return m404sellingItem_;
}
static Item m202(){
ItemForBattle m405itemForBattle_ =Instances.newItemForBattle();
m405itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_POISON+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_POISON+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m405itemForBattle_.setPrice(1000);
return m405itemForBattle_;
}
static Item m203(){
ItemForBattle m406itemForBattle_ =Instances.newItemForBattle();
m406itemForBattle_.setPrice(1000);
return m406itemForBattle_;
}
static Item m204(){
ItemForBattle m407itemForBattle_ =Instances.newItemForBattle();
CustList<EffectWhileSendingWithStatistic> m407custListEffectWhileSendingWithStatistic_ = new CustList<EffectWhileSendingWithStatistic>(new CollCapacity(1));
EffectWhileSendingWithStatistic m407effectWhileSendingWithStatistic_ = Instances.newEffectWhileSendingSimple();
m407effectWhileSendingWithStatistic_.setMultWeight(Rate.newRate(R_1_2));
m407custListEffectWhileSendingWithStatistic_.add(m407effectWhileSendingWithStatistic_);
m407itemForBattle_.setEffectSending(m407custListEffectWhileSendingWithStatistic_);
m407itemForBattle_.setPrice(1000);
return m407itemForBattle_;
}
static Item m205(){
EvolvingStone m408evolvingStone_ =Instances.newEvolvingStone();
m408evolvingStone_.setPrice(1000);
return m408evolvingStone_;
}
static Item m206(){
ItemForBattle m409itemForBattle_ =Instances.newItemForBattle();
m409itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_ROCHE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ROCHE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m409itemForBattle_.setPrice(1000);
return m409itemForBattle_;
}
static Item m207(){
EvolvingStone m410evolvingStone_ =Instances.newEvolvingStone();
m410evolvingStone_.setPrice(1000);
return m410evolvingStone_;
}
static Item m208(){
EvolvingStone m411evolvingStone_ =Instances.newEvolvingStone();
m411evolvingStone_.setPrice(1000);
return m411evolvingStone_;
}
static Item m209(){
EvolvingStone m412evolvingStone_ =Instances.newEvolvingStone();
m412evolvingStone_.setPrice(1000);
return m412evolvingStone_;
}
static Item m210(){
EvolvingStone m413evolvingStone_ =Instances.newEvolvingStone();
m413evolvingStone_.setPrice(1000);
return m413evolvingStone_;
}
static Item m211(){
EvolvingStone m414evolvingStone_ =Instances.newEvolvingStone();
m414evolvingStone_.setPrice(1000);
return m414evolvingStone_;
}
static Item m212(){
EvolvingStone m415evolvingStone_ =Instances.newEvolvingStone();
m415evolvingStone_.setPrice(1000);
return m415evolvingStone_;
}
static Item m213(){
EvolvingStone m416evolvingStone_ =Instances.newEvolvingStone();
m416evolvingStone_.setPrice(1000);
return m416evolvingStone_;
}
static Item m214(){
EvolvingStone m417evolvingStone_ =Instances.newEvolvingStone();
m417evolvingStone_.setPrice(1000);
return m417evolvingStone_;
}
static Item m215(){
EvolvingItem m418evolvingItem_=Instances.newEvolvingItem();
m418evolvingItem_.setPrice(1000);
return m418evolvingItem_;
}
static Item m216(){
EvolvingStone m419evolvingStone_ =Instances.newEvolvingStone();
m419evolvingStone_.setPrice(1000);
return m419evolvingStone_;
}
static Item m217(){
EvolvingStone m420evolvingStone_ =Instances.newEvolvingStone();
m420evolvingStone_.setPrice(1000);
return m420evolvingStone_;
}
static Item m218(){
ItemForBattle m421itemForBattle_ =Instances.newItemForBattle();
m421itemForBattle_.setAgainstEvo(true);
m421itemForBattle_.setPrice(1000);
return m421itemForBattle_;
}
static Item m219(){
ItemForBattle m422itemForBattle_ =Instances.newItemForBattle();
m422itemForBattle_.setPrice(1000);
return m422itemForBattle_;
}
static Item m220(){
ItemForBattle m423itemForBattle_ =Instances.newItemForBattle();
m423itemForBattle_.setDamageRecoil(Rate.newRate(R_1_8));
m423itemForBattle_.setPrice(1000);
return m423itemForBattle_;
}
static Item m221(){
ItemForBattle m424itemForBattle_ =Instances.newItemForBattle();
StringList m424stringList_=new StringList(new CollCapacity(1));
m424stringList_.add(I_PSY);
m424itemForBattle_.setTypesPk(m424stringList_);
m424itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_PSY+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PSY+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m424itemForBattle_.setPrice(1000);
return m424itemForBattle_;
}
static Item m222(){
ItemForBattle m425itemForBattle_ =Instances.newItemForBattle();
StringList m425stringList_=new StringList(new CollCapacity(1));
m425stringList_.add(I_VOL);
m425itemForBattle_.setTypesPk(m425stringList_);
m425itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_VOL+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_VOL+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m425itemForBattle_.setPrice(1000);
return m425itemForBattle_;
}
static Item m223(){
ItemForBattle m426itemForBattle_ =Instances.newItemForBattle();
StringList m426stringList_=new StringList(new CollCapacity(1));
m426stringList_.add(I_DRAGON);
m426itemForBattle_.setTypesPk(m426stringList_);
m426itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_DRAGON+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_DRAGON+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m426itemForBattle_.setPrice(1000);
return m426itemForBattle_;
}
static Item m224(){
ItemForBattle m427itemForBattle_ =Instances.newItemForBattle();
StringList m427stringList_=new StringList(new CollCapacity(1));
m427stringList_.add(I_SPECTRE);
m427itemForBattle_.setTypesPk(m427stringList_);
m427itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_SPECTRE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_SPECTRE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m427itemForBattle_.setPrice(1000);
return m427itemForBattle_;
}
static Item m225(){
ItemForBattle m428itemForBattle_ =Instances.newItemForBattle();
StringList m428stringList_=new StringList(new CollCapacity(1));
m428stringList_.add(I_FEE);
m428itemForBattle_.setTypesPk(m428stringList_);
m428itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_FEE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_FEE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m428itemForBattle_.setPrice(1000);
return m428itemForBattle_;
}
static Item m226(){
ItemForBattle m429itemForBattle_ =Instances.newItemForBattle();
StringList m429stringList_=new StringList(new CollCapacity(1));
m429stringList_.add(I_ACIER);
m429itemForBattle_.setTypesPk(m429stringList_);
m429itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_ACIER+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ACIER+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m429itemForBattle_.setPrice(1000);
return m429itemForBattle_;
}
static Item m227(){
ItemForBattle m430itemForBattle_ =Instances.newItemForBattle();
StringList m430stringList_=new StringList(new CollCapacity(1));
m430stringList_.add(I_FEU);
m430itemForBattle_.setTypesPk(m430stringList_);
m430itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_FEU+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_FEU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m430itemForBattle_.setPrice(1000);
return m430itemForBattle_;
}
static Item m228(){
ItemForBattle m431itemForBattle_ =Instances.newItemForBattle();
StringList m431stringList_=new StringList(new CollCapacity(1));
m431stringList_.add(I_GLACE);
m431itemForBattle_.setTypesPk(m431stringList_);
m431itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_GLACE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_GLACE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m431itemForBattle_.setPrice(1000);
return m431itemForBattle_;
}
static Item m229(){
ItemForBattle m432itemForBattle_ =Instances.newItemForBattle();
StringList m432stringList_=new StringList(new CollCapacity(1));
m432stringList_.add(I_PLANTE);
m432itemForBattle_.setTypesPk(m432stringList_);
m432itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_PLANTE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_PLANTE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m432itemForBattle_.setPrice(1000);
return m432itemForBattle_;
}
static Item m230(){
ItemForBattle m433itemForBattle_ =Instances.newItemForBattle();
StringList m433stringList_=new StringList(new CollCapacity(1));
m433stringList_.add(I_EAU);
m433itemForBattle_.setTypesPk(m433stringList_);
m433itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_EAU+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_EAU+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m433itemForBattle_.setPrice(1000);
return m433itemForBattle_;
}
static Item m231(){
ItemForBattle m434itemForBattle_ =Instances.newItemForBattle();
StringList m434stringList_=new StringList(new CollCapacity(1));
m434stringList_.add(I_TENEBRE);
m434itemForBattle_.setTypesPk(m434stringList_);
m434itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_TENEBRE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_TENEBRE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m434itemForBattle_.setPrice(1000);
return m434itemForBattle_;
}
static Item m232(){
ItemForBattle m435itemForBattle_ =Instances.newItemForBattle();
StringList m435stringList_=new StringList(new CollCapacity(1));
m435stringList_.add(I_COMBAT);
m435itemForBattle_.setTypesPk(m435stringList_);
m435itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_COMBAT+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_COMBAT+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m435itemForBattle_.setPrice(1000);
return m435itemForBattle_;
}
static Item m233(){
ItemForBattle m436itemForBattle_ =Instances.newItemForBattle();
StringList m436stringList_=new StringList(new CollCapacity(1));
m436stringList_.add(I_ROCHE);
m436itemForBattle_.setTypesPk(m436stringList_);
m436itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_ROCHE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ROCHE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m436itemForBattle_.setPrice(1000);
return m436itemForBattle_;
}
static Item m234(){
ItemForBattle m437itemForBattle_ =Instances.newItemForBattle();
StringList m437stringList_=new StringList(new CollCapacity(1));
m437stringList_.add(I_SOL);
m437itemForBattle_.setTypesPk(m437stringList_);
m437itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_SOL+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_SOL+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m437itemForBattle_.setPrice(1000);
return m437itemForBattle_;
}
static Item m235(){
ItemForBattle m438itemForBattle_ =Instances.newItemForBattle();
StringList m438stringList_=new StringList(new CollCapacity(1));
m438stringList_.add(I_POISON);
m438itemForBattle_.setTypesPk(m438stringList_);
m438itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_POISON+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_POISON+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m438itemForBattle_.setPrice(1000);
return m438itemForBattle_;
}
static Item m236(){
ItemForBattle m439itemForBattle_ =Instances.newItemForBattle();
StringList m439stringList_=new StringList(new CollCapacity(1));
m439stringList_.add(I_ELECTRIQUE);
m439itemForBattle_.setTypesPk(m439stringList_);
m439itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_ELECTRIQUE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_ELECTRIQUE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m439itemForBattle_.setPrice(1000);
return m439itemForBattle_;
}
static Item m237(){
ItemForBattle m440itemForBattle_ =Instances.newItemForBattle();
StringList m440stringList_=new StringList(new CollCapacity(1));
m440stringList_.add(I_INSECTE);
m440itemForBattle_.setTypesPk(m440stringList_);
m440itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_INSECTE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_INSECTE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m440itemForBattle_.setPrice(1000);
return m440itemForBattle_;
}
static Item m238(){
ItemForBattle m441itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,Short> m441enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m441enumMapStatisticShort_.addEntry(Statistic.HP,(short)4);
m441itemForBattle_.setWinEvFight(m441enumMapStatisticShort_);
m441itemForBattle_.setPrice(1000);
return m441itemForBattle_;
}
static Item m239(){
ItemForBattle m442itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,Short> m442enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m442enumMapStatisticShort_.addEntry(Statistic.ATTACK,(short)4);
m442itemForBattle_.setWinEvFight(m442enumMapStatisticShort_);
m442itemForBattle_.setPrice(1000);
return m442itemForBattle_;
}
static Item m240(){
ItemForBattle m443itemForBattle_ =Instances.newItemForBattle();
StatisticPokemons m443objectMapStatisticPokemonByte_=new StatisticPokemons(new CollCapacity(1));
m443objectMapStatisticPokemonByte_.addEntry(new StatisticPokemon(Statistic.CRITICAL_HIT,I_LEVEINARD),(byte)1);
m443itemForBattle_.setMultStatPokemonRank(m443objectMapStatisticPokemonByte_);
m443itemForBattle_.setPrice(1000);
return m443itemForBattle_;
}
static Item m241(){
Ball m444ball_=Instances.newBall();
m444ball_.setCatchingRate(R_1);
m444ball_.setPrice(200);
return m444ball_;
}
static Item m242(){
HealingHp m445healingHp_ =Instances.newHealingHp();
m445healingHp_.setHp(Rate.newRate(R_20));
m445healingHp_.setPrice(300);
return m445healingHp_;
}
static Item m243(){
HealingHpStatus m446healingHpStatus_ =Instances.newHealingHpStatus();
m446healingHpStatus_.setHealedHpRate(Rate.newRate(R_1));
m446healingHpStatus_.setHealingKo(false);
m446healingHpStatus_.setPrice(2500);
return m446healingHpStatus_;
}
static Item m244(){
ItemForBattle m447itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m447enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m447enumMapStatisticString_.addEntry(Statistic.EVASINESS,R_10_9);
m447itemForBattle_.setMultStat(m447enumMapStatisticString_);
m447itemForBattle_.setPrice(1000);
return m447itemForBattle_;
}
static Item m245(){
ItemForBattle m448itemForBattle_ =Instances.newItemForBattle();
m448itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_INSECTE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_INSECTE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m448itemForBattle_.setPrice(1000);
return m448itemForBattle_;
}
static Item m246(){
ItemForBattle m449itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m449enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m449enumMapStatisticString_.addEntry(Statistic.DEFENSE,R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_NOM+RB+OC+LB+I_METAMORPH+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_METAMORPH+RB+OC+LB+V_FIGHTER_NOM+RB+RP+RP);
m449itemForBattle_.setMultStat(m449enumMapStatisticString_);
m449itemForBattle_.setPrice(1000);
return m449itemForBattle_;
}
static Item m247(){
ItemForBattle m450itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m450enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m450enumMapStatisticString_.addEntry(Statistic.SPEED,R_3_2+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_NOM+RB+OC+LB+I_METAMORPH+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_METAMORPH+RB+OC+LB+V_FIGHTER_NOM+RB+RP+RP);
m450itemForBattle_.setMultStat(m450enumMapStatisticString_);
m450itemForBattle_.setPrice(1000);
return m450itemForBattle_;
}
static Item m248(){
SellingItem m451sellingItem_ =Instances.newSellingItem();
m451sellingItem_.setPrice(1000);
return m451sellingItem_;
}
static Item m249(){
Boost m452boost_=Instances.newBoost();
m452boost_.setWinPp(Rate.newRate(R_3));
StringMap<Short> m452stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m452stringMapShort_.addEntry(I_FAIBLO_BALL,(short)2);
m452stringMapShort_.addEntry(I_SCUBA_BALL,(short)2);
m452stringMapShort_.addEntry(I_HONOR_BALL,(short)2);
m452stringMapShort_.addEntry(I_BIS_BALL,(short)2);
m452stringMapShort_.addEntry(I_LUNE_BALL,(short)2);
m452stringMapShort_.addEntry(I_SPEED_BALL,(short)2);
m452stringMapShort_.addEntry(I_LUXE_BALL,(short)6);
m452stringMapShort_.addEntry(I_SOIN_BALL,(short)2);
m452stringMapShort_.addEntry(I_MASTER_BALL,(short)2);
m452stringMapShort_.addEntry(I_CHRONO_BALL,(short)2);
m452stringMapShort_.addEntry(I_POKE_BALL,(short)2);
m452stringMapShort_.addEntry(I_SUPER_BALL,(short)2);
m452stringMapShort_.addEntry(I_RAPIDE_BALL,(short)2);
m452stringMapShort_.addEntry(I_NIVEAU_BALL,(short)2);
m452stringMapShort_.addEntry(I_SOMBRE_BALL,(short)2);
m452stringMapShort_.addEntry(I_HYPER_BALL,(short)2);
m452stringMapShort_.addEntry(I_MASSE_BALL,(short)2);
m452stringMapShort_.addEntry(I_LOVE_BALL,(short)2);
m452stringMapShort_.addEntry(I_COMPET_BALL,(short)2);
m452stringMapShort_.addEntry(I_APPAT_BALL,(short)2);
m452stringMapShort_.addEntry(I_COPAIN_BALL,(short)2);
m452stringMapShort_.addEntry(I_FILET_BALL,(short)2);
m452boost_.setHappiness(m452stringMapShort_);
m452boost_.setPrice(9800);
return m452boost_;
}
static Item m250(){
Boost m453boost_=Instances.newBoost();
m453boost_.setWinPp(Rate.newRate(R_1));
StringMap<Short> m453stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m453stringMapShort_.addEntry(I_FAIBLO_BALL,(short)2);
m453stringMapShort_.addEntry(I_SCUBA_BALL,(short)2);
m453stringMapShort_.addEntry(I_HONOR_BALL,(short)2);
m453stringMapShort_.addEntry(I_BIS_BALL,(short)2);
m453stringMapShort_.addEntry(I_LUNE_BALL,(short)2);
m453stringMapShort_.addEntry(I_SPEED_BALL,(short)2);
m453stringMapShort_.addEntry(I_LUXE_BALL,(short)6);
m453stringMapShort_.addEntry(I_SOIN_BALL,(short)2);
m453stringMapShort_.addEntry(I_MASTER_BALL,(short)2);
m453stringMapShort_.addEntry(I_CHRONO_BALL,(short)2);
m453stringMapShort_.addEntry(I_POKE_BALL,(short)2);
m453stringMapShort_.addEntry(I_SUPER_BALL,(short)2);
m453stringMapShort_.addEntry(I_RAPIDE_BALL,(short)2);
m453stringMapShort_.addEntry(I_NIVEAU_BALL,(short)2);
m453stringMapShort_.addEntry(I_SOMBRE_BALL,(short)2);
m453stringMapShort_.addEntry(I_HYPER_BALL,(short)2);
m453stringMapShort_.addEntry(I_MASSE_BALL,(short)2);
m453stringMapShort_.addEntry(I_LOVE_BALL,(short)2);
m453stringMapShort_.addEntry(I_COMPET_BALL,(short)2);
m453stringMapShort_.addEntry(I_APPAT_BALL,(short)2);
m453stringMapShort_.addEntry(I_COPAIN_BALL,(short)2);
m453stringMapShort_.addEntry(I_FILET_BALL,(short)2);
m453boost_.setHappiness(m453stringMapShort_);
m453boost_.setPrice(9800);
return m453boost_;
}
static Item m251(){
EvolvingItem m454evolvingItem_=Instances.newEvolvingItem();
m454evolvingItem_.setPrice(1000);
return m454evolvingItem_;
}
static Item m252(){
Boost m455boost_=Instances.newBoost();
StringMap<Short> m455stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m455stringMapShort_.addEntry(I_FAIBLO_BALL,(short)2);
m455stringMapShort_.addEntry(I_SCUBA_BALL,(short)2);
m455stringMapShort_.addEntry(I_HONOR_BALL,(short)2);
m455stringMapShort_.addEntry(I_BIS_BALL,(short)2);
m455stringMapShort_.addEntry(I_LUNE_BALL,(short)2);
m455stringMapShort_.addEntry(I_SPEED_BALL,(short)2);
m455stringMapShort_.addEntry(I_LUXE_BALL,(short)6);
m455stringMapShort_.addEntry(I_SOIN_BALL,(short)2);
m455stringMapShort_.addEntry(I_MASTER_BALL,(short)2);
m455stringMapShort_.addEntry(I_CHRONO_BALL,(short)2);
m455stringMapShort_.addEntry(I_POKE_BALL,(short)2);
m455stringMapShort_.addEntry(I_SUPER_BALL,(short)2);
m455stringMapShort_.addEntry(I_RAPIDE_BALL,(short)2);
m455stringMapShort_.addEntry(I_NIVEAU_BALL,(short)2);
m455stringMapShort_.addEntry(I_SOMBRE_BALL,(short)2);
m455stringMapShort_.addEntry(I_HYPER_BALL,(short)2);
m455stringMapShort_.addEntry(I_MASSE_BALL,(short)2);
m455stringMapShort_.addEntry(I_LOVE_BALL,(short)2);
m455stringMapShort_.addEntry(I_COMPET_BALL,(short)2);
m455stringMapShort_.addEntry(I_APPAT_BALL,(short)2);
m455stringMapShort_.addEntry(I_COPAIN_BALL,(short)2);
m455stringMapShort_.addEntry(I_FILET_BALL,(short)2);
m455boost_.setHappiness(m455stringMapShort_);
IdMap<Statistic,Short> m455enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m455enumMapStatisticShort_.addEntry(Statistic.ATTACK,(short)10);
m455boost_.setEvs(m455enumMapStatisticShort_);
m455boost_.setPrice(9800);
return m455boost_;
}
static Item m253(){
ItemForBattle m456itemForBattle_ =Instances.newItemForBattle();
m456itemForBattle_.setCancelImmuType(true);
m456itemForBattle_.setPrice(1000);
return m456itemForBattle_;
}
static Item m254(){
Boost m457boost_=Instances.newBoost();
StringMap<Short> m457stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m457stringMapShort_.addEntry(I_FAIBLO_BALL,(short)2);
m457stringMapShort_.addEntry(I_SCUBA_BALL,(short)2);
m457stringMapShort_.addEntry(I_HONOR_BALL,(short)2);
m457stringMapShort_.addEntry(I_BIS_BALL,(short)2);
m457stringMapShort_.addEntry(I_LUNE_BALL,(short)2);
m457stringMapShort_.addEntry(I_SPEED_BALL,(short)2);
m457stringMapShort_.addEntry(I_LUXE_BALL,(short)6);
m457stringMapShort_.addEntry(I_SOIN_BALL,(short)2);
m457stringMapShort_.addEntry(I_MASTER_BALL,(short)2);
m457stringMapShort_.addEntry(I_CHRONO_BALL,(short)2);
m457stringMapShort_.addEntry(I_POKE_BALL,(short)2);
m457stringMapShort_.addEntry(I_SUPER_BALL,(short)2);
m457stringMapShort_.addEntry(I_RAPIDE_BALL,(short)2);
m457stringMapShort_.addEntry(I_NIVEAU_BALL,(short)2);
m457stringMapShort_.addEntry(I_SOMBRE_BALL,(short)2);
m457stringMapShort_.addEntry(I_HYPER_BALL,(short)2);
m457stringMapShort_.addEntry(I_MASSE_BALL,(short)2);
m457stringMapShort_.addEntry(I_LOVE_BALL,(short)2);
m457stringMapShort_.addEntry(I_COMPET_BALL,(short)2);
m457stringMapShort_.addEntry(I_APPAT_BALL,(short)2);
m457stringMapShort_.addEntry(I_COPAIN_BALL,(short)2);
m457stringMapShort_.addEntry(I_FILET_BALL,(short)2);
m457boost_.setHappiness(m457stringMapShort_);
IdMap<Statistic,Short> m457enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m457enumMapStatisticShort_.addEntry(Statistic.HP,(short)10);
m457boost_.setEvs(m457enumMapStatisticShort_);
m457boost_.setPrice(9800);
return m457boost_;
}
static Item m255(){
ItemForBattle m458itemForBattle_ =Instances.newItemForBattle();
m458itemForBattle_.setAttackLast(true);
m458itemForBattle_.setPrice(1000);
return m458itemForBattle_;
}
static Item m256(){
Ball m459ball_=Instances.newBall();
m459ball_.setCatchingRate(R_25+OM+A_CARACFERME+LP+V_TEMPS_TOUR+OC+R_0+OC+R_3+RP+OP+R_10+OM+A_CARACFERME+LP+V_TEMPS_TOUR+OC+R_4+OC+R_7+RP+OP+R_6+OM+A_CARACFERME+LP+V_TEMPS_TOUR+OC+R_8+OC+R_11+RP+OP+R_3+OM+A_CARACFERME+LP+V_TEMPS_TOUR+OC+R_12+OC+R_14+RP+OP+A_CARACDROITEFERME+LP+V_TEMPS_TOUR+OC+R_15+RP);
m459ball_.setPrice(800);
return m459ball_;
}
static Item m257(){
HealingHpStatus m460healingHpStatus_ =Instances.newHealingHpStatus();
m460healingHpStatus_.setHealedHpRate(Rate.newRate(R_1_2));
m460healingHpStatus_.setHealingKo(true);
m460healingHpStatus_.setPrice(1500);
return m460healingHpStatus_;
}
static Item m258(){
HealingHpStatus m461healingHpStatus_ =Instances.newHealingHpStatus();
m461healingHpStatus_.setHealedHpRate(Rate.newRate(R_1));
m461healingHpStatus_.setHealingKo(true);
m461healingHpStatus_.setPrice(2500);
return m461healingHpStatus_;
}
static Item m259(){
Repel m462repel_ =Instances.newRepel();
m462repel_.setSteps(100);
m462repel_.setPrice(300);
return m462repel_;
}
static Item m260(){
ItemForBattle m463itemForBattle_ =Instances.newItemForBattle();
CustList<EffectEndRound> m463custListEffectEndRound_ = new CustList<EffectEndRound>(new CollCapacity(1));
EffectEndRoundIndividual m463effectEndRoundIndividual_=Instances.newEffectEndRoundIndividual();
m463effectEndRoundIndividual_.setDeleteAllStatus(Rate.newRate(R_0));
m463effectEndRoundIndividual_.setRecoilDamage(Rate.newRate(R_0));
m463effectEndRoundIndividual_.setHealHp(Rate.newRate(R_1_16));
m463effectEndRoundIndividual_.setUserStatusEndRound(ES);
m463effectEndRoundIndividual_.setFailEndRound(ES);
m463effectEndRoundIndividual_.setEndRoundRank(39);
m463effectEndRoundIndividual_.setTargetChoice(TargetChoice.LANCEUR);
m463effectEndRoundIndividual_.setFail(ES);
m463custListEffectEndRound_.add(m463effectEndRoundIndividual_);
m463itemForBattle_.setEffectEndRound(m463custListEffectEndRound_);
m463itemForBattle_.setPrice(1000);
return m463itemForBattle_;
}
static Item m261(){
HealingSimpleStatus m464healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList m464stringList_=new StringList(new CollCapacity(2));
m464stringList_.add(I_SOMMEIL);
m464stringList_.add(I_SOMMEIL_REPOS);
m464healingSimpleStatus_.setStatus(m464stringList_);
m464healingSimpleStatus_.setHealingKo(false);
m464healingSimpleStatus_.setPrice(250);
return m464healingSimpleStatus_;
}
static Item m262(){
ItemForBattle m465itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> m465stringMapShort_=new StringMap<Short>(new CollCapacity(1));
m465stringMapShort_.addEntry(I_ZENITH,(short)3);
m465itemForBattle_.setIncreasingMaxNbRoundGlobalMove(m465stringMapShort_);
m465itemForBattle_.setPrice(1000);
return m465itemForBattle_;
}
static Item m263(){
ItemForBattle m466itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> m466stringMapShort_=new StringMap<Short>(new CollCapacity(1));
m466stringMapShort_.addEntry(I_ORAGE,(short)3);
m466itemForBattle_.setIncreasingMaxNbRoundGlobalMove(m466stringMapShort_);
m466itemForBattle_.setPrice(1000);
return m466itemForBattle_;
}
static Item m264(){
ItemForBattle m467itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> m467stringMapShort_=new StringMap<Short>(new CollCapacity(1));
m467stringMapShort_.addEntry(I_GRELE,(short)3);
m467itemForBattle_.setIncreasingMaxNbRoundGlobalMove(m467stringMapShort_);
m467itemForBattle_.setPrice(1000);
return m467itemForBattle_;
}
static Item m265(){
ItemForBattle m468itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> m468stringMapShort_=new StringMap<Short>(new CollCapacity(1));
m468stringMapShort_.addEntry(I_DANSE_PLUIE,(short)3);
m468itemForBattle_.setIncreasingMaxNbRoundGlobalMove(m468stringMapShort_);
m468itemForBattle_.setPrice(1000);
return m468itemForBattle_;
}
static Item m266(){
ItemForBattle m469itemForBattle_ =Instances.newItemForBattle();
StringMap<Short> m469stringMapShort_=new StringMap<Short>(new CollCapacity(1));
m469stringMapShort_.addEntry(I_TEMPETESABLE,(short)3);
m469itemForBattle_.setIncreasingMaxNbRoundGlobalMove(m469stringMapShort_);
m469itemForBattle_.setPrice(1000);
return m469itemForBattle_;
}
static Item m267(){
EvolvingItem m470evolvingItem_=Instances.newEvolvingItem();
m470evolvingItem_.setPrice(1000);
return m470evolvingItem_;
}
static Item m268(){
ItemForBattle m471itemForBattle_ =Instances.newItemForBattle();
m471itemForBattle_.setPrice(1000);
return m471itemForBattle_;
}
static Item m269(){
ItemForBattle m472itemForBattle_ =Instances.newItemForBattle();
m472itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_SPECTRE+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_SPECTRE+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m472itemForBattle_.setPrice(1000);
return m472itemForBattle_;
}
static Item m270(){
ItemForBattle m473itemForBattle_ =Instances.newItemForBattle();
m473itemForBattle_.setMultPower(R_6_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_ATTAQUE_TYPES+RB+OC+LB+I_SOL+RB+RP+RP+OP+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_SOL+RB+OC+LB+V_ATTAQUE_TYPES+RB+RP+RP);
m473itemForBattle_.setPrice(1000);
return m473itemForBattle_;
}
static Item m271(){
EvolvingItem m474evolvingItem_=Instances.newEvolvingItem();
m474evolvingItem_.setPrice(1);
return m474evolvingItem_;
}
static Item m272(){
Ball m475ball_=Instances.newBall();
m475ball_.setCatchingRate(R_7+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_LIEU_COMBAT+RB+OC+LB+I_WATER+RB+RP+RP+OP+R_3_4+OM+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_WATER+RB+OC+LB+V_LIEU_COMBAT+RB+RP+RP);
m475ball_.setPrice(800);
return m475ball_;
}
static Item m273(){
HealingHp m476healingHp_ =Instances.newHealingHp();
m476healingHp_.setHp(Rate.newRate(R_60));
StringMap<Short> m476stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m476stringMapShort_.addEntry(I_FAIBLO_BALL,(short)1);
m476stringMapShort_.addEntry(I_SCUBA_BALL,(short)1);
m476stringMapShort_.addEntry(I_HONOR_BALL,(short)1);
m476stringMapShort_.addEntry(I_BIS_BALL,(short)1);
m476stringMapShort_.addEntry(I_LUNE_BALL,(short)1);
m476stringMapShort_.addEntry(I_SPEED_BALL,(short)1);
m476stringMapShort_.addEntry(I_LUXE_BALL,(short)2);
m476stringMapShort_.addEntry(I_SOIN_BALL,(short)1);
m476stringMapShort_.addEntry(I_MASTER_BALL,(short)1);
m476stringMapShort_.addEntry(I_CHRONO_BALL,(short)1);
m476stringMapShort_.addEntry(I_POKE_BALL,(short)1);
m476stringMapShort_.addEntry(I_SUPER_BALL,(short)1);
m476stringMapShort_.addEntry(I_RAPIDE_BALL,(short)1);
m476stringMapShort_.addEntry(I_NIVEAU_BALL,(short)1);
m476stringMapShort_.addEntry(I_SOMBRE_BALL,(short)1);
m476stringMapShort_.addEntry(I_HYPER_BALL,(short)1);
m476stringMapShort_.addEntry(I_MASSE_BALL,(short)1);
m476stringMapShort_.addEntry(I_LOVE_BALL,(short)1);
m476stringMapShort_.addEntry(I_COMPET_BALL,(short)1);
m476stringMapShort_.addEntry(I_APPAT_BALL,(short)1);
m476stringMapShort_.addEntry(I_COPAIN_BALL,(short)1);
m476stringMapShort_.addEntry(I_FILET_BALL,(short)1);
m476healingHp_.setHappiness(m476stringMapShort_);
m476healingHp_.setPrice(300);
return m476healingHp_;
}
static Item m274(){
Ball m477ball_=Instances.newBall();
m477ball_.setCatchingRate(R_5_4);
m477ball_.setPrice(500);
return m477ball_;
}
static Item m275(){
Ball m478ball_=Instances.newBall();
m478ball_.setCatchingRate(R_7+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_LIEU_COMBAT+RB+OC+LB+I_WATER+RB+RP+RP+OP+R_3_4+OM+A_CARDINAL+LP+A_COMPLEMENTAIRE+LP+LB+I_WATER+RB+OC+LB+V_LIEU_COMBAT+RB+RP+RP);
m478ball_.setPrice(800);
return m478ball_;
}
static Item m276(){
Ball m479ball_=Instances.newBall();
m479ball_.setCatchingRate(R_2+OM+A_CARACFERME+LP+V_PK_SAUVAGE_VITESSE+OC+R_0+OC+R_99+RP+OP+R_6+OM+A_CARACDROITEOUVERT+LP+V_PK_SAUVAGE_VITESSE+OC+R_99+RP);
m479ball_.setPrice(800);
return m479ball_;
}
static Item m277(){
Ball m480ball_=Instances.newBall();
m480ball_.setCatchingRate(R_3_2);
m480ball_.setPrice(600);
return m480ball_;
}
static Item m278(){
HealingHp m481healingHp_ =Instances.newHealingHp();
m481healingHp_.setHp(Rate.newRate(R_50));
m481healingHp_.setPrice(700);
return m481healingHp_;
}
static Item m279(){
Repel m482repel_ =Instances.newRepel();
m482repel_.setSteps(200);
m482repel_.setPrice(500);
return m482repel_;
}
static Item m280(){
EvolvingItem m483evolvingItem_=Instances.newEvolvingItem();
m483evolvingItem_.setPrice(1000);
return m483evolvingItem_;
}
static Item m281(){
HealingSimpleStatus m484healingSimpleStatus_ =Instances.newHealingSimpleStatus();
StringList m484stringList_=new StringList(new CollCapacity(7));
m484stringList_.add(I_SOMMEIL);
m484stringList_.add(I_SOMMEIL_REPOS);
m484stringList_.add(I_PARALYSIE);
m484stringList_.add(I_SIMPLE_POISON);
m484stringList_.add(I_POISON_GRAVE);
m484stringList_.add(I_BRULURE);
m484stringList_.add(I_GEL);
m484healingSimpleStatus_.setStatus(m484stringList_);
m484healingSimpleStatus_.setHealingKo(false);
m484healingSimpleStatus_.setPrice(600);
return m484healingSimpleStatus_;
}
static Item m282(){
ItemForBattle m485itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,String> m485enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m485enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,R_3_2);
m485itemForBattle_.setMultStat(m485enumMapStatisticString_);
m485itemForBattle_.setPrice(0);
return m485itemForBattle_;
}
static Item m283(){
Fossil m486fossil_ =Instances.newFossil();
m486fossil_.setPokemon(I_PTERA);
m486fossil_.setLevel((short)10);
m486fossil_.setPrice(1500);
return m486fossil_;
}
static Item m284(){
ItemForBattle m487itemForBattle_ =Instances.newItemForBattle();
MonteCarloBoolean m487monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
m487monteCarloBoolean_.addQuickEvent(BoolVal.FALSE,LgInt.newLgInt(R_4));
m487monteCarloBoolean_.addQuickEvent(BoolVal.TRUE,LgInt.newLgInt(R_1));
m487itemForBattle_.setLawForAttackFirst(m487monteCarloBoolean_);
m487itemForBattle_.setPrice(1000);
return m487itemForBattle_;
}
static Item m285(){
ItemForBattle m488itemForBattle_ =Instances.newItemForBattle();
IdMap<Statistic,Byte> m488enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m488enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
m488itemForBattle_.setBoostStatisSuperEff(m488enumMapStatisticByte_);
m488itemForBattle_.setPrice(1000);
return m488itemForBattle_;
}
static Item m286(){
Boost m489boost_=Instances.newBoost();
StringMap<Short> m489stringMapShort_=new StringMap<Short>(new CollCapacity(22));
m489stringMapShort_.addEntry(I_FAIBLO_BALL,(short)2);
m489stringMapShort_.addEntry(I_SCUBA_BALL,(short)2);
m489stringMapShort_.addEntry(I_HONOR_BALL,(short)2);
m489stringMapShort_.addEntry(I_BIS_BALL,(short)2);
m489stringMapShort_.addEntry(I_LUNE_BALL,(short)2);
m489stringMapShort_.addEntry(I_SPEED_BALL,(short)2);
m489stringMapShort_.addEntry(I_LUXE_BALL,(short)6);
m489stringMapShort_.addEntry(I_SOIN_BALL,(short)2);
m489stringMapShort_.addEntry(I_MASTER_BALL,(short)2);
m489stringMapShort_.addEntry(I_CHRONO_BALL,(short)2);
m489stringMapShort_.addEntry(I_POKE_BALL,(short)2);
m489stringMapShort_.addEntry(I_SUPER_BALL,(short)2);
m489stringMapShort_.addEntry(I_RAPIDE_BALL,(short)2);
m489stringMapShort_.addEntry(I_NIVEAU_BALL,(short)2);
m489stringMapShort_.addEntry(I_SOMBRE_BALL,(short)2);
m489stringMapShort_.addEntry(I_HYPER_BALL,(short)2);
m489stringMapShort_.addEntry(I_MASSE_BALL,(short)2);
m489stringMapShort_.addEntry(I_LOVE_BALL,(short)2);
m489stringMapShort_.addEntry(I_COMPET_BALL,(short)2);
m489stringMapShort_.addEntry(I_APPAT_BALL,(short)2);
m489stringMapShort_.addEntry(I_COPAIN_BALL,(short)2);
m489stringMapShort_.addEntry(I_FILET_BALL,(short)2);
m489boost_.setHappiness(m489stringMapShort_);
IdMap<Statistic,Short> m489enumMapStatisticShort_=new IdMap<Statistic,Short>(new CollCapacity(1));
m489enumMapStatisticShort_.addEntry(Statistic.SPECIAL_DEFENSE,(short)10);
m489boost_.setEvs(m489enumMapStatisticShort_);
m489boost_.setPrice(9800);
return m489boost_;
}
}
