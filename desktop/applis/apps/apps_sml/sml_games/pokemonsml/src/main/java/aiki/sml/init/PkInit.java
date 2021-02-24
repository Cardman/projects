package aiki.sml.init;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.pokemon.enums.*;
import code.maths.*;
import code.util.*;
public final class PkInit{
private PkInit(){}
public static StringMap<PokemonData> p(){
 StringMap<PokemonData> p_ = new StringMap<PokemonData>(new CollCapacity(722));
p_.addEntry("ABO",PkInit0.p0());
p_.addEntry("ABRA",PkInit0.p1());
p_.addEntry("ABSOL",PkInit0.p2());
p_.addEntry("AEROMITE",PkInit0.p3());
p_.addEntry("AEROPTERYX",PkInit0.p4());
p_.addEntry("AFLAMANOIR",PkInit0.p5());
p_.addEntry("AIKIVOL",PkInit0.p6());
p_.addEntry("AIRMURE",PkInit0.p7());
p_.addEntry("AKWAKWAK",PkInit0.p8());
p_.addEntry("ALAKAZAM",PkInit0.p9());
p_.addEntry("ALIGATUEUR",PkInit0.p10());
p_.addEntry("ALTARIA",PkInit0.p11());
p_.addEntry("AMAGARA",PkInit0.p12());
p_.addEntry("AMONISTAR",PkInit0.p13());
p_.addEntry("AMONITA",PkInit0.p14());
p_.addEntry("AMPHINOBI",PkInit0.p15());
p_.addEntry("ANCHWATT",PkInit0.p16());
p_.addEntry("ANORITH",PkInit0.p17());
p_.addEntry("APIREINE",PkInit0.p18());
p_.addEntry("APITRINI",PkInit0.p19());
p_.addEntry("AQUALI",PkInit0.p20());
p_.addEntry("ARAKDO",PkInit0.p21());
p_.addEntry("ARBOK",PkInit0.p22());
p_.addEntry("ARCANIN",PkInit0.p23());
p_.addEntry("ARCEUS",PkInit0.p24());
p_.addEntry("ARCHEODONG",PkInit0.p25());
p_.addEntry("ARCHEOMIRE",PkInit0.p26());
p_.addEntry("ARCKO",PkInit1.p27());
p_.addEntry("ARKEAPTI",PkInit1.p28());
p_.addEntry("ARMALDO",PkInit1.p29());
p_.addEntry("ARMULYS",PkInit1.p30());
p_.addEntry("ARTIKODIN",PkInit1.p31());
p_.addEntry("ASPICOT",PkInit1.p32());
p_.addEntry("AVALTOUT",PkInit1.p33());
p_.addEntry("AXOLOTO",PkInit1.p34());
p_.addEntry("AZUMARILL",PkInit1.p35());
p_.addEntry("AZURILL",PkInit1.p36());
p_.addEntry("BABIMANTA",PkInit1.p37());
p_.addEntry("BAGGAID",PkInit1.p38());
p_.addEntry("BAGGIGUANE",PkInit1.p39());
p_.addEntry("BALBUTO",PkInit1.p40());
p_.addEntry("BALIGNON",PkInit1.p41());
p_.addEntry("BANSHITROUYE",PkInit1.p42());
p_.addEntry("BARBICHA",PkInit1.p43());
p_.addEntry("BARGANTUA",PkInit1.p44());
p_.addEntry("BARLOCHE",PkInit1.p45());
p_.addEntry("BARPAU",PkInit1.p46());
p_.addEntry("BASTIODON",PkInit1.p47());
p_.addEntry("BATRACNE",PkInit1.p48());
p_.addEntry("BAUDRIVE",PkInit1.p49());
p_.addEntry("BEKIPAN",PkInit1.p50());
p_.addEntry("BETOCHEF",PkInit1.p51());
p_.addEntry("BLINDALYS",PkInit1.p52());
p_.addEntry("BLINDEPIQUE",PkInit1.p53());
p_.addEntry("BLIZZAROI",PkInit2.p54());
p_.addEntry("BLIZZI",PkInit2.p55());
p_.addEntry("BOGUERISSE",PkInit2.p56());
p_.addEntry("BOREAS",PkInit2.p57());
p_.addEntry("BOSKARA",PkInit2.p58());
p_.addEntry("BOULDENEU",PkInit2.p59());
p_.addEntry("BOUSTIFLOR",PkInit2.p60());
p_.addEntry("BRAISILLON",PkInit2.p61());
p_.addEntry("BRANETTE",PkInit2.p62());
p_.addEntry("BRASEGALI",PkInit2.p63());
p_.addEntry("BROCELOME",PkInit2.p64());
p_.addEntry("BROUHABAM",PkInit2.p65());
p_.addEntry("BRUTALIBRE",PkInit2.p66());
p_.addEntry("BRUTAPODE",PkInit2.p67());
p_.addEntry("BRUYVERNE",PkInit2.p68());
p_.addEntry("BULBIZARRE",PkInit2.p69());
p_.addEntry("CABRIOLAINE",PkInit2.p70());
p_.addEntry("CACNEA",PkInit2.p71());
p_.addEntry("CACTURNE",PkInit2.p72());
p_.addEntry("CADOIZO",PkInit2.p73());
p_.addEntry("CAMERUPT",PkInit2.p74());
p_.addEntry("CANARTICHO",PkInit2.p75());
p_.addEntry("CANINOS",PkInit2.p76());
p_.addEntry("CAPIDEXTRE",PkInit2.p77());
p_.addEntry("CAPUMAIN",PkInit2.p78());
p_.addEntry("CARABAFFE",PkInit2.p79());
p_.addEntry("CARABING",PkInit2.p80());
p_.addEntry("CARAPAGOS",PkInit3.p81());
p_.addEntry("CARAPUCE",PkInit3.p82());
p_.addEntry("CARATROC",PkInit3.p83());
p_.addEntry("CARCHACROK",PkInit3.p84());
p_.addEntry("CARMACHE",PkInit3.p85());
p_.addEntry("CARVANHA",PkInit3.p86());
p_.addEntry("CASTORNO",PkInit3.p87());
p_.addEntry("CELEBI",PkInit3.p88());
p_.addEntry("CERFROUSSE",PkInit3.p89());
p_.addEntry("CERIBOU",PkInit3.p90());
p_.addEntry("CERIFLOR",PkInit3.p91());
p_.addEntry("CHACRIPAN",PkInit3.p92());
p_.addEntry("CHAFFREUX",PkInit3.p93());
p_.addEntry("CHAGLAM",PkInit3.p94());
p_.addEntry("CHAMALLOT",PkInit3.p95());
p_.addEntry("CHAPIGNON",PkInit3.p96());
p_.addEntry("CHARKOS",PkInit3.p97());
p_.addEntry("CHARMILLON",PkInit3.p98());
p_.addEntry("CHARMINA",PkInit3.p99());
p_.addEntry("CHARPENTI",PkInit3.p100());
p_.addEntry("CHARTOR",PkInit3.p101());
p_.addEntry("CHENIPAN",PkInit3.p102());
p_.addEntry("CHENIPOTTE",PkInit3.p103());
p_.addEntry("CHENISELLE",PkInit3.p104());
p_.addEntry("CHENITI",PkInit3.p105());
p_.addEntry("CHETIFLOR",PkInit3.p106());
p_.addEntry("CHEVROUM",PkInit3.p107());
p_.addEntry("CHIMPENFEU",PkInit4.p108());
p_.addEntry("CHINCHIDOU",PkInit4.p109());
p_.addEntry("CHLOROBULE",PkInit4.p110());
p_.addEntry("CHOVSOURIR",PkInit4.p111());
p_.addEntry("CHRYSACIER",PkInit4.p112());
p_.addEntry("CHUCHMUR",PkInit4.p113());
p_.addEntry("CIZAYOX",PkInit4.p114());
p_.addEntry("CLAMIRAL",PkInit4.p115());
p_.addEntry("CLIC",PkInit4.p116());
p_.addEntry("CLITICLIC",PkInit4.p117());
p_.addEntry("COATOX",PkInit4.p118());
p_.addEntry("COBALTIUM",PkInit4.p119());
p_.addEntry("COCHIGNON",PkInit4.p120());
p_.addEntry("COCONFORT",PkInit4.p121());
p_.addEntry("COCOTINE",PkInit4.p122());
p_.addEntry("COLHOMARD",PkInit4.p123());
p_.addEntry("COLIMUCUS",PkInit4.p124());
p_.addEntry("COLOMBEAU",PkInit4.p125());
p_.addEntry("COLOSSINGE",PkInit4.p126());
p_.addEntry("COQUIPERL",PkInit4.p127());
p_.addEntry("CORAYON",PkInit4.p128());
p_.addEntry("CORBOSS",PkInit4.p129());
p_.addEntry("CORNEBRE",PkInit4.p130());
p_.addEntry("COTOVOL",PkInit4.p131());
p_.addEntry("COUAFAREL",PkInit4.p132());
p_.addEntry("COUANETON",PkInit4.p133());
p_.addEntry("COUDLANGUE",PkInit4.p134());
p_.addEntry("COUPENOTTE",PkInit5.p135());
p_.addEntry("COUVERDURE",PkInit5.p136());
p_.addEntry("COXY",PkInit5.p137());
p_.addEntry("COXYCLAQUE",PkInit5.p138());
p_.addEntry("CRABARAQUE",PkInit5.p139());
p_.addEntry("CRABICOQUE",PkInit5.p140());
p_.addEntry("CRADOPAUD",PkInit5.p141());
p_.addEntry("CRAPUSTULE",PkInit5.p142());
p_.addEntry("CREFADET",PkInit5.p143());
p_.addEntry("CREFOLLET",PkInit5.p144());
p_.addEntry("CREHELF",PkInit5.p145());
p_.addEntry("CRESSELIA",PkInit5.p146());
p_.addEntry("CRIKZIK",PkInit5.p147());
p_.addEntry("CROAPORAL",PkInit5.p148());
p_.addEntry("CROCORIBLE",PkInit5.p149());
p_.addEntry("CROCRODIL",PkInit5.p150());
p_.addEntry("CRUSTABRI",PkInit5.p151());
p_.addEntry("CRYPTERO",PkInit5.p152());
p_.addEntry("CUPCANAILLE",PkInit5.p153());
p_.addEntry("DARDARGNAN",PkInit5.p154());
p_.addEntry("DARKRAI",PkInit5.p155());
p_.addEntry("DARUMACHO",PkInit5.p156());
p_.addEntry("DARUMAROND",PkInit5.p157());
p_.addEntry("DEBUGANT",PkInit5.p158());
p_.addEntry("DEDENNE",PkInit5.p159());
p_.addEntry("DEFLAISAN",PkInit5.p160());
p_.addEntry("DELCATTY",PkInit5.p161());
p_.addEntry("DEMANTA",PkInit6.p162());
p_.addEntry("DEMETEROS",PkInit6.p163());
p_.addEntry("DEMOLOSSE",PkInit6.p164());
p_.addEntry("DEOXYS",PkInit6.p165());
p_.addEntry("DESSELIANDE",PkInit6.p166());
p_.addEntry("DIALGA",PkInit6.p167());
p_.addEntry("DIAMAT",PkInit6.p168());
p_.addEntry("DIANCIE",PkInit6.p169());
p_.addEntry("DIMOCLES",PkInit6.p170());
p_.addEntry("DIMORET",PkInit6.p171());
p_.addEntry("DINOCLIER",PkInit6.p172());
p_.addEntry("DODRIO",PkInit6.p173());
p_.addEntry("DODUO",PkInit6.p174());
p_.addEntry("DONPHAN",PkInit6.p175());
p_.addEntry("DOUDOUVET",PkInit6.p176());
p_.addEntry("DRABY",PkInit6.p177());
p_.addEntry("DRACAUFEU",PkInit6.p178());
p_.addEntry("DRACKHAUS",PkInit6.p179());
p_.addEntry("DRACO",PkInit6.p180());
p_.addEntry("DRACOLOSSE",PkInit6.p181());
p_.addEntry("DRAGMARA",PkInit6.p182());
p_.addEntry("DRAKKARMIN",PkInit6.p183());
p_.addEntry("DRASCORE",PkInit6.p184());
p_.addEntry("DRATTAK",PkInit6.p185());
p_.addEntry("DYNAVOLT",PkInit6.p186());
p_.addEntry("ECAYON",PkInit6.p187());
p_.addEntry("ECRAPINCE",PkInit6.p188());
p_.addEntry("ECREMEUH",PkInit7.p189());
p_.addEntry("ECTOPLASMA",PkInit7.p190());
p_.addEntry("ELECSPRINT",PkInit7.p191());
p_.addEntry("ELECTHOR",PkInit7.p192());
p_.addEntry("ELECTRODE",PkInit7.p193());
p_.addEntry("ELEKABLE",PkInit7.p194());
p_.addEntry("ELEKID",PkInit7.p195());
p_.addEntry("ELEKTEK",PkInit7.p196());
p_.addEntry("EMBRYLEX",PkInit7.p197());
p_.addEntry("EMOLGA",PkInit7.p198());
p_.addEntry("EMPIFLOR",PkInit7.p199());
p_.addEntry("ENTEI",PkInit7.p200());
p_.addEntry("EOKO",PkInit7.p201());
p_.addEntry("ESCARGAUME",PkInit7.p202());
p_.addEntry("ESCROCO",PkInit7.p203());
p_.addEntry("ETOURAPTOR",PkInit7.p204());
p_.addEntry("ETOURMI",PkInit7.p205());
p_.addEntry("ETOURVOL",PkInit7.p206());
p_.addEntry("EVOLI",PkInit7.p207());
p_.addEntry("EXAGIDE",PkInit7.p208());
p_.addEntry("EXCAVARENNE",PkInit7.p209());
p_.addEntry("EXCELANGUE",PkInit7.p210());
p_.addEntry("FANTOMINUS",PkInit7.p211());
p_.addEntry("FARFADUVET",PkInit7.p212());
p_.addEntry("FARFURET",PkInit7.p213());
p_.addEntry("FERMITE",PkInit7.p214());
p_.addEntry("FEROSINGE",PkInit7.p215());
p_.addEntry("FEUFOREVE",PkInit8.p216());
p_.addEntry("FEUILLAJOU",PkInit8.p217());
p_.addEntry("FEUILOUTAN",PkInit8.p218());
p_.addEntry("FEUNARD",PkInit8.p219());
p_.addEntry("FEUNNEC",PkInit8.p220());
p_.addEntry("FEURISSON",PkInit8.p221());
p_.addEntry("FLABEBE",PkInit8.p222());
p_.addEntry("FLAGADOSS",PkInit8.p223());
p_.addEntry("FLAMAJOU",PkInit8.p224());
p_.addEntry("FLAMBUSARD",PkInit8.p225());
p_.addEntry("FLAMOUTAN",PkInit8.p226());
p_.addEntry("FLINGOUSTE",PkInit8.p227());
p_.addEntry("FLOBIO",PkInit8.p228());
p_.addEntry("FLOETTE",PkInit8.p229());
p_.addEntry("FLORAVOL",PkInit8.p230());
p_.addEntry("FLORGES",PkInit8.p231());
p_.addEntry("FLORIZARRE",PkInit8.p232());
p_.addEntry("FLOTAJOU",PkInit8.p233());
p_.addEntry("FLOTOUTAN",PkInit8.p234());
p_.addEntry("FLUVETIN",PkInit8.p235());
p_.addEntry("FORETRESS",PkInit8.p236());
p_.addEntry("FOUINAR",PkInit8.p237());
p_.addEntry("FOUINETTE",PkInit8.p238());
p_.addEntry("FRAGILADY",PkInit8.p239());
p_.addEntry("FRISON",PkInit8.p240());
p_.addEntry("FULGURIS",PkInit8.p241());
p_.addEntry("FUNECIRE",PkInit8.p242());
p_.addEntry("FURAIGLON",PkInit9.p243());
p_.addEntry("GALEGON",PkInit9.p244());
p_.addEntry("GALEKID",PkInit9.p245());
p_.addEntry("GALEKING",PkInit9.p246());
p_.addEntry("GALIFEU",PkInit9.p247());
p_.addEntry("GALLAME",PkInit9.p248());
p_.addEntry("GALOPA",PkInit9.p249());
p_.addEntry("GALVARAN",PkInit9.p250());
p_.addEntry("GAMBLAST",PkInit9.p251());
p_.addEntry("GARDEVOIR",PkInit9.p252());
p_.addEntry("GAULET",PkInit9.p253());
p_.addEntry("GENESECT",PkInit9.p254());
p_.addEntry("GEOLITHE",PkInit9.p255());
p_.addEntry("GERMIGNON",PkInit9.p256());
p_.addEntry("GIGALITHE",PkInit9.p257());
p_.addEntry("GIRAFARIG",PkInit9.p258());
p_.addEntry("GIRATINA",PkInit9.p259());
p_.addEntry("GIVRALI",PkInit9.p260());
p_.addEntry("GLOUPTI",PkInit9.p261());
p_.addEntry("GOBOU",PkInit9.p262());
p_.addEntry("GOELISE",PkInit9.p263());
p_.addEntry("GOINFREX",PkInit9.p264());
p_.addEntry("GOLEMASTOC",PkInit9.p265());
p_.addEntry("GOLGOPATHE",PkInit9.p266());
p_.addEntry("GOUPELIN",PkInit9.p267());
p_.addEntry("GOUPIX",PkInit9.p268());
p_.addEntry("GRAHYENA",PkInit9.p269());
p_.addEntry("GRAINIPIOT",PkInit10.p270());
p_.addEntry("GRANBULL",PkInit10.p271());
p_.addEntry("GRANIVOL",PkInit10.p272());
p_.addEntry("GRAVALANCH",PkInit10.p273());
p_.addEntry("GRELACON",PkInit10.p274());
p_.addEntry("GRENOUSSE",PkInit10.p275());
p_.addEntry("GRIKNOT",PkInit10.p276());
p_.addEntry("GRINDUR",PkInit10.p277());
p_.addEntry("GRINGOLEM",PkInit10.p278());
p_.addEntry("GRODOUDOU",PkInit10.p279());
p_.addEntry("GRODRIVE",PkInit10.p280());
p_.addEntry("GROLEM",PkInit10.p281());
p_.addEntry("GRORET",PkInit10.p282());
p_.addEntry("GROTADMORV",PkInit10.p283());
p_.addEntry("GROTICHON",PkInit10.p284());
p_.addEntry("GROUDON",PkInit10.p285());
p_.addEntry("GRUIKUI",PkInit10.p286());
p_.addEntry("GUERIAIGLE",PkInit10.p287());
p_.addEntry("HARIYAMA",PkInit10.p288());
p_.addEntry("HAYDAIM",PkInit10.p289());
p_.addEntry("HEATRAN",PkInit10.p290());
p_.addEntry("HELEDELLE",PkInit10.p291());
p_.addEntry("HELIATRONC",PkInit10.p292());
p_.addEntry("HELIONCEAU",PkInit10.p293());
p_.addEntry("HERBIZARRE",PkInit10.p294());
p_.addEntry("HERICENDRE",PkInit10.p295());
p_.addEntry("HEXAGEL",PkInit10.p296());
p_.addEntry("HIPPODOCUS",PkInit11.p297());
p_.addEntry("HIPPOPOTAS",PkInit11.p298());
p_.addEntry("HOOPA",PkInit11.p299());
p_.addEntry("HOOTHOOT",PkInit11.p300());
p_.addEntry("HO_OH",PkInit11.p301());
p_.addEntry("HYPNOMADE",PkInit11.p302());
p_.addEntry("HYPOCEAN",PkInit11.p303());
p_.addEntry("HYPOROI",PkInit11.p304());
p_.addEntry("HYPOTREMPE",PkInit11.p305());
p_.addEntry("IGUOLTA",PkInit11.p306());
p_.addEntry("INCISACHE",PkInit11.p307());
p_.addEntry("INSECATEUR",PkInit11.p308());
p_.addEntry("INSOLOURDO",PkInit11.p309());
p_.addEntry("JIRACHI",PkInit11.p310());
p_.addEntry("JOLIFLOR",PkInit11.p311());
p_.addEntry("JUDOKRAK",PkInit11.p312());
p_.addEntry("JUNGKO",PkInit11.p313());
p_.addEntry("KABUTO",PkInit11.p314());
p_.addEntry("KABUTOPS",PkInit11.p315());
p_.addEntry("KADABRA",PkInit11.p316());
p_.addEntry("KAIMINUS",PkInit11.p317());
p_.addEntry("KAIMORSE",PkInit11.p318());
p_.addEntry("KANGOUREX",PkInit11.p319());
p_.addEntry("KAORINE",PkInit11.p320());
p_.addEntry("KAPOERA",PkInit11.p321());
p_.addEntry("KARACLEE",PkInit11.p322());
p_.addEntry("KECLEON",PkInit11.p323());
p_.addEntry("KELDEO",PkInit12.p324());
p_.addEntry("KEUNOTOR",PkInit12.p325());
p_.addEntry("KICKLEE",PkInit12.p326());
p_.addEntry("KIRLIA",PkInit12.p327());
p_.addEntry("KOKIYAS",PkInit12.p328());
p_.addEntry("KORILLON",PkInit12.p329());
p_.addEntry("KRABBOSS",PkInit12.p330());
p_.addEntry("KRABBY",PkInit12.p331());
p_.addEntry("KRAKNOIX",PkInit12.p332());
p_.addEntry("KRANIDOS",PkInit12.p333());
p_.addEntry("KRAVARECH",PkInit12.p334());
p_.addEntry("KUNGFOUINE",PkInit12.p335());
p_.addEntry("KYOGRE",PkInit12.p336());
p_.addEntry("KYUREM",PkInit12.p337());
p_.addEntry("LAGGRON",PkInit12.p338());
p_.addEntry("LAINERGIE",PkInit12.p339());
p_.addEntry("LAKMECYGNE",PkInit12.p340());
p_.addEntry("LAMANTINE",PkInit12.p341());
p_.addEntry("LAMPEROIE",PkInit12.p342());
p_.addEntry("LANCARGOT",PkInit12.p343());
p_.addEntry("LANTURN",PkInit12.p344());
p_.addEntry("LAPOREILLE",PkInit12.p345());
p_.addEntry("LARVEYETTE",PkInit12.p346());
p_.addEntry("LATIAS",PkInit12.p347());
p_.addEntry("LATIOS",PkInit12.p348());
p_.addEntry("LEOPARDUS",PkInit12.p349());
p_.addEntry("LEPIDONILLE",PkInit12.p350());
p_.addEntry("LEUPHORIE",PkInit13.p351());
p_.addEntry("LEVEINARD",PkInit13.p352());
p_.addEntry("LEVIATOR",PkInit13.p353());
p_.addEntry("LEWSOR",PkInit13.p354());
p_.addEntry("LIANAJA",PkInit13.p355());
p_.addEntry("LIBEGON",PkInit13.p356());
p_.addEntry("LILIA",PkInit13.p357());
p_.addEntry("LIMAGMA",PkInit13.p358());
p_.addEntry("LIMASPEED",PkInit13.p359());
p_.addEntry("LIMONDE",PkInit13.p360());
p_.addEntry("LINEON",PkInit13.p361());
p_.addEntry("LIPPOUTI",PkInit13.p362());
p_.addEntry("LIPPOUTOU",PkInit13.p363());
p_.addEntry("LIXY",PkInit13.p364());
p_.addEntry("LOCKPIN",PkInit13.p365());
p_.addEntry("LOKHLASS",PkInit13.p366());
p_.addEntry("LOMBRE",PkInit13.p367());
p_.addEntry("LOUPIO",PkInit13.p368());
p_.addEntry("LOVDISC",PkInit13.p369());
p_.addEntry("LUCARIO",PkInit13.p370());
p_.addEntry("LUDICOLO",PkInit13.p371());
p_.addEntry("LUGIA",PkInit13.p372());
p_.addEntry("LUGULABRE",PkInit13.p373());
p_.addEntry("LUMINEON",PkInit13.p374());
p_.addEntry("LUMIVOLE",PkInit13.p375());
p_.addEntry("LUXIO",PkInit13.p376());
p_.addEntry("LUXRAY",PkInit13.p377());
p_.addEntry("MACHOC",PkInit14.p378());
p_.addEntry("MACHOPEUR",PkInit14.p379());
p_.addEntry("MACKOGNEUR",PkInit14.p380());
p_.addEntry("MACRONIUM",PkInit14.p381());
p_.addEntry("MAGANON",PkInit14.p382());
p_.addEntry("MAGBY",PkInit14.p383());
p_.addEntry("MAGICARPE",PkInit14.p384());
p_.addEntry("MAGIREVE",PkInit14.p385());
p_.addEntry("MAGMAR",PkInit14.p386());
p_.addEntry("MAGNETI",PkInit14.p387());
p_.addEntry("MAGNETON",PkInit14.p388());
p_.addEntry("MAGNEZONE",PkInit14.p389());
p_.addEntry("MAJASPIC",PkInit14.p390());
p_.addEntry("MAKUHITA",PkInit14.p391());
p_.addEntry("MALOSSE",PkInit14.p392());
p_.addEntry("MAMANBO",PkInit14.p393());
p_.addEntry("MAMMOCHON",PkInit14.p394());
p_.addEntry("MANAPHY",PkInit14.p395());
p_.addEntry("MANGRIFF",PkInit14.p396());
p_.addEntry("MANTERNEL",PkInit14.p397());
p_.addEntry("MANZAI",PkInit14.p398());
p_.addEntry("MARACACHI",PkInit14.p399());
p_.addEntry("MARAISTE",PkInit14.p400());
p_.addEntry("MARCACRIN",PkInit14.p401());
p_.addEntry("MARILL",PkInit14.p402());
p_.addEntry("MARISSON",PkInit14.p403());
p_.addEntry("MASCAIMAN",PkInit14.p404());
p_.addEntry("MASKADRA",PkInit15.p405());
p_.addEntry("MASSKO",PkInit15.p406());
p_.addEntry("MASTOUFFE",PkInit15.p407());
p_.addEntry("MATELOUTRE",PkInit15.p408());
p_.addEntry("MEDHYENA",PkInit15.p409());
p_.addEntry("MEDITIKKA",PkInit15.p410());
p_.addEntry("MEGANIUM",PkInit15.p411());
p_.addEntry("MEGAPAGOS",PkInit15.p412());
p_.addEntry("MEIOS",PkInit15.p413());
p_.addEntry("MELANCOLUX",PkInit15.p414());
p_.addEntry("MELO",PkInit15.p415());
p_.addEntry("MELODELFE",PkInit15.p416());
p_.addEntry("MELOETTA",PkInit15.p417());
p_.addEntry("MELOFEE",PkInit15.p418());
p_.addEntry("MELOKRIK",PkInit15.p419());
p_.addEntry("MENTALI",PkInit15.p420());
p_.addEntry("MESMERELLA",PkInit15.p421());
p_.addEntry("METALOSSE",PkInit15.p422());
p_.addEntry("METAMORPH",PkInit15.p423());
p_.addEntry("METANG",PkInit15.p424());
p_.addEntry("MEW",PkInit15.p425());
p_.addEntry("MEWTWO",PkInit15.p426());
p_.addEntry("MIAMIASME",PkInit15.p427());
p_.addEntry("MIAOUSS",PkInit15.p428());
p_.addEntry("MIASMAX",PkInit15.p429());
p_.addEntry("MIGALOS",PkInit15.p430());
p_.addEntry("MILOBELLUS",PkInit15.p431());
p_.addEntry("MIME_JR",PkInit16.p432());
p_.addEntry("MIMIGAL",PkInit16.p433());
p_.addEntry("MIMITOSS",PkInit16.p434());
p_.addEntry("MINIDRACO",PkInit16.p435());
p_.addEntry("MINOTAUPE",PkInit16.p436());
p_.addEntry("MIRADAR",PkInit16.p437());
p_.addEntry("MISTIGRIX",PkInit16.p438());
p_.addEntry("MOMARTIK",PkInit16.p439());
p_.addEntry("MONAFLEMIT",PkInit16.p440());
p_.addEntry("MONORPALE",PkInit16.p441());
p_.addEntry("MORPHEO",PkInit16.p442());
p_.addEntry("MOTISMA",PkInit16.p443());
p_.addEntry("MOUFFLAIR",PkInit16.p444());
p_.addEntry("MOUFOUETTE",PkInit16.p445());
p_.addEntry("MOUSTILLON",PkInit16.p446());
p_.addEntry("MOYADE",PkInit16.p447());
p_.addEntry("MUCIOLE",PkInit16.p448());
p_.addEntry("MUCUSCULE",PkInit16.p449());
p_.addEntry("MUNJA",PkInit16.p450());
p_.addEntry("MUNNA",PkInit16.p451());
p_.addEntry("MUPLODOCUS",PkInit16.p452());
p_.addEntry("MUSHANA",PkInit16.p453());
p_.addEntry("MUSTEBOUEE",PkInit16.p454());
p_.addEntry("MUSTEFLOTT",PkInit16.p455());
p_.addEntry("MYGAVOLT",PkInit16.p456());
p_.addEntry("MYSDIBULE",PkInit16.p457());
p_.addEntry("MYSTHERBE",PkInit16.p458());
p_.addEntry("M_MIME",PkInit17.p459());
p_.addEntry("NANMEOUIE",PkInit17.p460());
p_.addEntry("NATU",PkInit17.p461());
p_.addEntry("NEGAPI",PkInit17.p462());
p_.addEntry("NEITRAM",PkInit17.p463());
p_.addEntry("NEMELIOS",PkInit17.p464());
p_.addEntry("NENUPIOT",PkInit17.p465());
p_.addEntry("NIDOKING",PkInit17.p466());
p_.addEntry("NIDOQUEEN",PkInit17.p467());
p_.addEntry("NIDORAN_F",PkInit17.p468());
p_.addEntry("NIDORAN_M",PkInit17.p469());
p_.addEntry("NIDORINA",PkInit17.p470());
p_.addEntry("NIDORINO",PkInit17.p471());
p_.addEntry("NINGALE",PkInit17.p472());
p_.addEntry("NINJASK",PkInit17.p473());
p_.addEntry("NIRONDELLE",PkInit17.p474());
p_.addEntry("NOACIER",PkInit17.p475());
p_.addEntry("NOADKOKO",PkInit17.p476());
p_.addEntry("NOARFANG",PkInit17.p477());
p_.addEntry("NOCTALI",PkInit17.p478());
p_.addEntry("NOCTUNOIR",PkInit17.p479());
p_.addEntry("NODULITHE",PkInit17.p480());
p_.addEntry("NOEUNOEUF",PkInit17.p481());
p_.addEntry("NOSFERALTO",PkInit17.p482());
p_.addEntry("NOSFERAPTI",PkInit17.p483());
p_.addEntry("NOSTENFER",PkInit17.p484());
p_.addEntry("NUCLEOS",PkInit17.p485());
p_.addEntry("NYMPHALI",PkInit18.p486());
p_.addEntry("OBALIE",PkInit18.p487());
p_.addEntry("OCTILLERY",PkInit18.p488());
p_.addEntry("OHMASSACRE",PkInit18.p489());
p_.addEntry("OKEOKE",PkInit18.p490());
p_.addEntry("ONIGLALI",PkInit18.p491());
p_.addEntry("ONIX",PkInit18.p492());
p_.addEntry("OPERMINE",PkInit18.p493());
p_.addEntry("ORTIDE",PkInit18.p494());
p_.addEntry("OSSATUEUR",PkInit18.p495());
p_.addEntry("OSSELAIT",PkInit18.p496());
p_.addEntry("OTARIA",PkInit18.p497());
p_.addEntry("OUISTICRAM",PkInit18.p498());
p_.addEntry("OUVRIFIER",PkInit18.p499());
p_.addEntry("PACHIRISU",PkInit18.p500());
p_.addEntry("PALKIA",PkInit18.p501());
p_.addEntry("PANDARBARE",PkInit18.p502());
p_.addEntry("PANDESPIEGLE",PkInit18.p503());
p_.addEntry("PAPILORD",PkInit18.p504());
p_.addEntry("PAPILUSION",PkInit18.p505());
p_.addEntry("PAPINOX",PkInit18.p506());
p_.addEntry("PARAS",PkInit18.p507());
p_.addEntry("PARASECT",PkInit18.p508());
p_.addEntry("PARECOOL",PkInit18.p509());
p_.addEntry("PASHMILLA",PkInit18.p510());
p_.addEntry("PASSEROUGE",PkInit18.p511());
p_.addEntry("PEREGRAIN",PkInit18.p512());
p_.addEntry("PERSIAN",PkInit19.p513());
p_.addEntry("PHANPY",PkInit19.p514());
p_.addEntry("PHARAMP",PkInit19.p515());
p_.addEntry("PHIONE",PkInit19.p516());
p_.addEntry("PHOGLEUR",PkInit19.p517());
p_.addEntry("PHYLLALI",PkInit19.p518());
p_.addEntry("PIAFABEC",PkInit19.p519());
p_.addEntry("PICHU",PkInit19.p520());
p_.addEntry("PIFEUIL",PkInit19.p521());
p_.addEntry("PIJAKO",PkInit19.p522());
p_.addEntry("PIKACHU",PkInit19.p523());
p_.addEntry("PINGOLEON",PkInit19.p524());
p_.addEntry("PITROUILLE",PkInit19.p525());
p_.addEntry("POICHIGEON",PkInit19.p526());
p_.addEntry("POISSIRENE",PkInit19.p527());
p_.addEntry("POISSOROY",PkInit19.p528());
p_.addEntry("POLAGRIFFE",PkInit19.p529());
p_.addEntry("POLARHUME",PkInit19.p530());
p_.addEntry("POLICHOMBR",PkInit19.p531());
p_.addEntry("POMDEPIK",PkInit19.p532());
p_.addEntry("PONCHIEN",PkInit19.p533());
p_.addEntry("PONCHIOT",PkInit19.p534());
p_.addEntry("PONYTA",PkInit19.p535());
p_.addEntry("PORYGON",PkInit19.p536());
p_.addEntry("PORYGON2",PkInit19.p537());
p_.addEntry("PORYGON_Z",PkInit19.p538());
p_.addEntry("POSIPI",PkInit19.p539());
p_.addEntry("POUSSIFEU",PkInit20.p540());
p_.addEntry("PRINPLOUF",PkInit20.p541());
p_.addEntry("PRISMILLON",PkInit20.p542());
p_.addEntry("PSYKOKWAK",PkInit20.p543());
p_.addEntry("PSYSTIGRI",PkInit20.p544());
p_.addEntry("PTERA",PkInit20.p545());
p_.addEntry("PTIRAVI",PkInit20.p546());
p_.addEntry("PTITARD",PkInit20.p547());
p_.addEntry("PTYRANIDUR",PkInit20.p548());
p_.addEntry("PYRAX",PkInit20.p549());
p_.addEntry("PYROLI",PkInit20.p550());
p_.addEntry("PYRONILLE",PkInit20.p551());
p_.addEntry("QUEULORIOR",PkInit20.p552());
p_.addEntry("QULBUTOKE",PkInit20.p553());
p_.addEntry("QWILFISH",PkInit20.p554());
p_.addEntry("RACAILLOU",PkInit20.p555());
p_.addEntry("RAFFLESIA",PkInit20.p556());
p_.addEntry("RAICHU",PkInit20.p557());
p_.addEntry("RAIKOU",PkInit20.p558());
p_.addEntry("RAMBOUM",PkInit20.p559());
p_.addEntry("RAMOLOSS",PkInit20.p560());
p_.addEntry("RAPASDEPIC",PkInit20.p561());
p_.addEntry("RAPION",PkInit20.p562());
p_.addEntry("RATENTIF",PkInit20.p563());
p_.addEntry("RATTATA",PkInit20.p564());
p_.addEntry("RATTATAC",PkInit20.p565());
p_.addEntry("RAYQUAZA",PkInit20.p566());
p_.addEntry("REGICE",PkInit21.p567());
p_.addEntry("REGIGIGAS",PkInit21.p568());
p_.addEntry("REGIROCK",PkInit21.p569());
p_.addEntry("REGISTEEL",PkInit21.p570());
p_.addEntry("RELICANTH",PkInit21.p571());
p_.addEntry("REMORAID",PkInit21.p572());
p_.addEntry("REPTINCEL",PkInit21.p573());
p_.addEntry("RESHIRAM",PkInit21.p574());
p_.addEntry("REXILLIUS",PkInit21.p575());
p_.addEntry("RHINASTOC",PkInit21.p576());
p_.addEntry("RHINOCORNE",PkInit21.p577());
p_.addEntry("RHINOFEROS",PkInit21.p578());
p_.addEntry("RHINOLOVE",PkInit21.p579());
p_.addEntry("RIOLU",PkInit21.p580());
p_.addEntry("ROIGADA",PkInit21.p581());
p_.addEntry("ROITIFLAM",PkInit21.p582());
p_.addEntry("RONDOUDOU",PkInit21.p583());
p_.addEntry("RONFLEX",PkInit21.p584());
p_.addEntry("ROSABYSS",PkInit21.p585());
p_.addEntry("ROSELIA",PkInit21.p586());
p_.addEntry("ROSERADE",PkInit21.p587());
p_.addEntry("ROTOTAUPE",PkInit21.p588());
p_.addEntry("ROUCARNAGE",PkInit21.p589());
p_.addEntry("ROUCOOL",PkInit21.p590());
p_.addEntry("ROUCOUPS",PkInit21.p591());
p_.addEntry("ROUSSIL",PkInit21.p592());
p_.addEntry("ROZBOUTON",PkInit21.p593());
p_.addEntry("SABELETTE",PkInit22.p594());
p_.addEntry("SABLAIREAU",PkInit22.p595());
p_.addEntry("SALAMECHE",PkInit22.p596());
p_.addEntry("SANCOKI",PkInit22.p597());
p_.addEntry("SAPEREAU",PkInit22.p598());
p_.addEntry("SAQUEDENEU",PkInit22.p599());
p_.addEntry("SCALPION",PkInit22.p600());
p_.addEntry("SCALPROIE",PkInit22.p601());
p_.addEntry("SCARABRUTE",PkInit22.p602());
p_.addEntry("SCARHINO",PkInit22.p603());
p_.addEntry("SCOBOLIDE",PkInit22.p604());
p_.addEntry("SCORPLANE",PkInit22.p605());
p_.addEntry("SCORVOL",PkInit22.p606());
p_.addEntry("SCRUTELLA",PkInit22.p607());
p_.addEntry("SELEROC",PkInit22.p608());
p_.addEntry("SEPIATOP",PkInit22.p609());
p_.addEntry("SEPIATROCE",PkInit22.p610());
p_.addEntry("SERACRAWL",PkInit22.p611());
p_.addEntry("SERPANG",PkInit22.p612());
p_.addEntry("SEVIPER",PkInit22.p613());
p_.addEntry("SHAOFOUINE",PkInit22.p614());
p_.addEntry("SHARPEDO",PkInit22.p615());
p_.addEntry("SHAYMIN",PkInit22.p616());
p_.addEntry("SIDERELLA",PkInit22.p617());
p_.addEntry("SIMIABRAZ",PkInit22.p618());
p_.addEntry("SIMULARBRE",PkInit22.p619());
p_.addEntry("SKELENOX",PkInit22.p620());
p_.addEntry("SKITTY",PkInit23.p621());
p_.addEntry("SMOGO",PkInit23.p622());
p_.addEntry("SMOGOGO",PkInit23.p623());
p_.addEntry("SNUBBULL",PkInit23.p624());
p_.addEntry("SOLAROC",PkInit23.p625());
p_.addEntry("SOLOCHI",PkInit23.p626());
p_.addEntry("SONISTRELLE",PkInit23.p627());
p_.addEntry("SOPORIFIK",PkInit23.p628());
p_.addEntry("SORBEBE",PkInit23.p629());
p_.addEntry("SORBOUBOUL",PkInit23.p630());
p_.addEntry("SORBOUL",PkInit23.p631());
p_.addEntry("SPECTRUM",PkInit23.p632());
p_.addEntry("SPINDA",PkInit23.p633());
p_.addEntry("SPIRITOMB",PkInit23.p634());
p_.addEntry("SPOINK",PkInit23.p635());
p_.addEntry("STALGAMIN",PkInit23.p636());
p_.addEntry("STARI",PkInit23.p637());
p_.addEntry("STAROSS",PkInit23.p638());
p_.addEntry("STATITIK",PkInit23.p639());
p_.addEntry("STEELIX",PkInit23.p640());
p_.addEntry("STRASSIE",PkInit23.p641());
p_.addEntry("SUCROQUIN",PkInit23.p642());
p_.addEntry("SUICUNE",PkInit23.p643());
p_.addEntry("SULFURA",PkInit23.p644());
p_.addEntry("SYMBIOS",PkInit23.p645());
p_.addEntry("TADMORV",PkInit23.p646());
p_.addEntry("TARINOR",PkInit23.p647());
p_.addEntry("TARINORME",PkInit24.p648());
p_.addEntry("TARPAUD",PkInit24.p649());
p_.addEntry("TARSAL",PkInit24.p650());
p_.addEntry("TARTARD",PkInit24.p651());
p_.addEntry("TAUPIQUEUR",PkInit24.p652());
p_.addEntry("TAUROS",PkInit24.p653());
p_.addEntry("TEDDIURSA",PkInit24.p654());
p_.addEntry("TENEFIX",PkInit24.p655());
p_.addEntry("TENGALICE",PkInit24.p656());
p_.addEntry("TENTACOOL",PkInit24.p657());
p_.addEntry("TENTACRUEL",PkInit24.p658());
p_.addEntry("TERACLOPE",PkInit24.p659());
p_.addEntry("TERHAL",PkInit24.p660());
p_.addEntry("TERRAKIUM",PkInit24.p661());
p_.addEntry("TETARTE",PkInit24.p662());
p_.addEntry("TIC",PkInit24.p663());
p_.addEntry("TIPLOUF",PkInit24.p664());
p_.addEntry("TOGEKISS",PkInit24.p665());
p_.addEntry("TOGEPI",PkInit24.p666());
p_.addEntry("TOGETIC",PkInit24.p667());
p_.addEntry("TORTANK",PkInit24.p668());
p_.addEntry("TORTERRA",PkInit24.p669());
p_.addEntry("TORTIPOUSS",PkInit24.p670());
p_.addEntry("TOUDOUDOU",PkInit24.p671());
p_.addEntry("TOURNEGRIN",PkInit24.p672());
p_.addEntry("TRANCHODON",PkInit24.p673());
p_.addEntry("TRIOPIKEUR",PkInit24.p674());
p_.addEntry("TRIOXHYDRE",PkInit25.p675());
p_.addEntry("TRITONDE",PkInit25.p676());
p_.addEntry("TRITOSOR",PkInit25.p677());
p_.addEntry("TROMPIGNON",PkInit25.p678());
p_.addEntry("TROPIUS",PkInit25.p679());
p_.addEntry("TROUSSELIN",PkInit25.p680());
p_.addEntry("TUTAFEH",PkInit25.p681());
p_.addEntry("TUTANKAFER",PkInit25.p682());
p_.addEntry("TYGNON",PkInit25.p683());
p_.addEntry("TYLTON",PkInit25.p684());
p_.addEntry("TYPHLOSION",PkInit25.p685());
p_.addEntry("TYRANOCIF",PkInit25.p686());
p_.addEntry("URSARING",PkInit25.p687());
p_.addEntry("VACILYS",PkInit25.p688());
p_.addEntry("VAUTUTRICE",PkInit25.p689());
p_.addEntry("VENALGUE",PkInit25.p690());
p_.addEntry("VENIPATTE",PkInit25.p691());
p_.addEntry("VIBRANINF",PkInit25.p692());
p_.addEntry("VICTINI",PkInit25.p693());
p_.addEntry("VIGOROTH",PkInit25.p694());
p_.addEntry("VIPELIERRE",PkInit25.p695());
p_.addEntry("VIRIDIUM",PkInit25.p696());
p_.addEntry("VISKUSE",PkInit25.p697());
p_.addEntry("VIVALDAIM",PkInit25.p698());
p_.addEntry("VOLCANION",PkInit25.p699());
p_.addEntry("VOLCAROPOD",PkInit25.p700());
p_.addEntry("VOLTALI",PkInit25.p701());
p_.addEntry("VOLTORBE",PkInit26.p702());
p_.addEntry("VORTENTE",PkInit26.p703());
p_.addEntry("VOSTOURNO",PkInit26.p704());
p_.addEntry("WAILMER",PkInit26.p705());
p_.addEntry("WAILORD",PkInit26.p706());
p_.addEntry("WATTOUAT",PkInit26.p707());
p_.addEntry("XATU",PkInit26.p708());
p_.addEntry("XERNEAS",PkInit26.p709());
p_.addEntry("YANMA",PkInit26.p710());
p_.addEntry("YANMEGA",PkInit26.p711());
p_.addEntry("YMPHECT",PkInit26.p712());
p_.addEntry("YVELTAL",PkInit26.p713());
p_.addEntry("ZARBI",PkInit26.p714());
p_.addEntry("ZEBIBRON",PkInit26.p715());
p_.addEntry("ZEBLITZ",PkInit26.p716());
p_.addEntry("ZEKROM",PkInit26.p717());
p_.addEntry("ZIGZATON",PkInit26.p718());
p_.addEntry("ZOROARK",PkInit26.p719());
p_.addEntry("ZORUA",PkInit26.p720());
p_.addEntry("ZYGARDE",PkInit26.p721());
return p_;
}
}
