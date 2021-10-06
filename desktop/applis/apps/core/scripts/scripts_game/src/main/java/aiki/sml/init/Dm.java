package aiki.sml.init;
import aiki.map.Condition;
import aiki.map.characters.enums.*;
import aiki.map.characters.*;
import aiki.map.places.*;
import aiki.map.DataMap;
import aiki.map.buildings.*;
import aiki.instances.*;
import aiki.fight.util.*;
import aiki.map.util.*;
import aiki.map.enums.*;
import aiki.util.*;
import aiki.fight.*;
import aiki.fight.effects.*;
import aiki.fight.moves.effects.*;
import aiki.fight.status.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.status.*;
import aiki.fight.enums.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
import aiki.map.pokemon.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.enums.*;
public final class Dm extends CstIgame{
private static final short X0 = (short)0;
private static final short X1 = (short)1;
private static final short X2 = (short)2;
private static final short X3 = (short)3;
private static final short X4 = (short)4;
private static final short X5 = (short)5;
private static final short Y0 = (short)0;
private static final short Y1 = (short)1;
private static final short Y2 = (short)2;
private static final short Y3 = (short)3;
private static final short Y4 = (short)4;
private static final short P0 = (short)0;
private static final short P1 = (short)1;
private static final short P2 = (short)2;
private static final short P3 = (short)3;
private static final short P4 = (short)4;
private static final short P5 = (short)5;
private static final short P6 = (short)6;
private static final short P7 = (short)7;
private static final short P8 = (short)8;
private static final short P9 = (short)9;
private static final short P10 = (short)10;
private static final short P11 = (short)11;
private Dm(){}
public static DataMap map(){
DataMap m199dataMap_ = Instances.newDataMap();
CustList<Place> m199custListPlace_=new CustList<Place>(new CollCapacity(12));
m199custListPlace_.add(Pl.p0());
m199custListPlace_.add(Pl.p1());
m199custListPlace_.add(Pl.p2());
m199custListPlace_.add(Pl.p3());
m199custListPlace_.add(Pl.p4());
m199custListPlace_.add(Pl.p5());
m199custListPlace_.add(Pl.p6());
m199custListPlace_.add(Pl.p7());
m199custListPlace_.add(Pl.p8());
m199custListPlace_.add(Pl.p9());
m199custListPlace_.add(Pl.p10());
m199custListPlace_.add(Pl.p11());
m199dataMap_.setPlaces(m199custListPlace_);
CoordsLists m199objectMapCoordsEqListCoords_=new CoordsLists(new CollCapacity(37));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point((short)6,(short)7)),new Condition(LvUt.newCoords(P2,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)8,(short)7)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point((short)4,(short)6)),new Condition(LvUt.newCoords(P0,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point((short)7,(short)6)),new Condition(LvUt.newCoords(P0,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)6,(short)4)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point((short)11,(short)9)),new Condition(LvUt.newCoords(P10,0,new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point((short)11,(short)4)),new Condition(LvUt.newCoords(P10,0,new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point((short)6,(short)2)),new Condition(LvUt.newCoords(P4,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point((short)9,(short)2)),new Condition(LvUt.newCoords(P4,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point((short)6,(short)6)),new Condition(LvUt.newCoords(P0,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point((short)6,(short)8)),new Condition(LvUt.newCoords(P2,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point((short)6,(short)4)),new Condition(LvUt.newCoords(P2,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)6,(short)5)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)6,(short)8)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point((short)11,(short)6)),new Condition(LvUt.newCoords(P10,0,new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)7,(short)7)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point((short)8,(short)2)),new Condition(LvUt.newCoords(P4,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point((short)5,(short)6)),new Condition(LvUt.newCoords(P0,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)8,(short)8)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)8,(short)4)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)8,(short)5)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)6,(short)6)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point((short)7,(short)2)),new Condition(LvUt.newCoords(P4,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point((short)6,(short)9)),new Condition(LvUt.newCoords(P2,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point((short)6,(short)6)),new Condition(LvUt.newCoords(P2,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point((short)11,(short)7)),new Condition(LvUt.newCoords(P10,0,new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point((short)6,(short)5)),new Condition(LvUt.newCoords(P2,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point((short)5,(short)2)),new Condition(LvUt.newCoords(P4,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)8,(short)6)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)8,(short)9)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point((short)11,(short)8)),new Condition(LvUt.newCoords(P10,0,new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)6,(short)9)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point((short)8,(short)6)),new Condition(LvUt.newCoords(P0,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point((short)9,(short)6)),new Condition(LvUt.newCoords(P0,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point((short)11,(short)5)),new Condition(LvUt.newCoords(P10,0,new Point((short)4,(short)0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point((short)6,(short)7)),new Condition(LvUt.newCoords(P9,0,new Point((short)9,(short)7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point((short)4,(short)2)),new Condition(LvUt.newCoords(P4,0,new Point((short)18,(short)8),new Point((short)4,(short)0))));
m199dataMap_.setAccessCondition(m199objectMapCoordsEqListCoords_);
MiniMapCoordsList m199objectMapMiniMapCoordsTileMiniMap_=new MiniMapCoordsList(new CollCapacity(30));
TileMiniMap m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_CITY_LOCKED_TXT);
m199tileMiniMap_.setPlace(P0);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X1,Y4),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_CITY_LOCKED_TXT);
m199tileMiniMap_.setPlace(P8);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X5,Y4),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P3);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X4,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P11);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X0,Y4),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P9);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X2,Y4),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X0,Y1),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X0,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P9);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X4,Y4),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X1,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P9);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X3,Y4),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_LEAGUE_TXT);
m199tileMiniMap_.setPlace(P10);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X3,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X2,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_CITY_LOCKED_TXT);
m199tileMiniMap_.setPlace(P4);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X5,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X0,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P1);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X1,Y1),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X2,Y1),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P5);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X5,Y1),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_CITY_LOCKED_TXT);
m199tileMiniMap_.setPlace(P2);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X1,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X0,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X1,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P7);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X5,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X3,Y1),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X4,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROCK_TXT);
m199tileMiniMap_.setPlace(P6);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X5,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X4,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P3);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X2,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X3,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P3);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X3,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X2,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace((short)-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X4,Y1),m199tileMiniMap_);
m199dataMap_.setMiniMap(m199objectMapMiniMapCoordsTileMiniMap_);
m199dataMap_.setUnlockedCity(F_CITY_UNLOCKED_TXT);
m199dataMap_.setBegin(LvUt.newCoords(P0,0,new Point((short)6,(short)8)));
WildPk m199wildPk_=Instances.newWildPk();
m199wildPk_.setName(I_EVOLI);
m199wildPk_.setLevel((short)1);
m199wildPk_.setGender(Gender.NO_GENDER);
m199wildPk_.setAbility(I_ADAPTABILITE);
m199dataMap_.setFirstPokemon(m199wildPk_);
m199dataMap_.setScreenWidth(9);
m199dataMap_.setScreenHeight(9);
m199dataMap_.setSpaceBetweenLeftAndHeros(4);
m199dataMap_.setSpaceBetweenTopAndHeros(4);
m199dataMap_.setSideLength(32);
return m199dataMap_;
}
}
