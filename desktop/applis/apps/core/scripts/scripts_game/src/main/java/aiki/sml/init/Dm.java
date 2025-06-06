package aiki.sml.init;
import aiki.map.Condition;
import aiki.map.places.*;
import aiki.map.DataMap;
import aiki.instances.*;
import aiki.map.util.*;
import aiki.util.*;
import code.util.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
public final class Dm extends CstIgame{
private static final int X0 = 0;
private static final int X1 = 1;
private static final int X2 = 2;
private static final int X3 = 3;
private static final int X4 = 4;
private static final int X5 = 5;
private static final int Y0 = 0;
private static final int Y1 = 1;
private static final int Y2 = 2;
private static final int Y3 = 3;
private static final int Y4 = 4;
private static final int P0 = 0;
private static final int P1 = 1;
private static final int P2 = 2;
private static final int P3 = 3;
private static final int P4 = 4;
private static final int P5 = 5;
private static final int P6 = 6;
private static final int P7 = 7;
private static final int P8 = 8;
private static final int P9 = 9;
private static final int P10 = 10;
private static final int P11 = 11;
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
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point(6,7)),Condition.newList(LvUt.newCoords(P2,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(8,7)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point(4,6)),Condition.newList(LvUt.newCoords(P0,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point(7,6)),Condition.newList(LvUt.newCoords(P0,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(6,4)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point(11,9)),Condition.newList(LvUt.newCoords(P10,0,new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point(11,4)),Condition.newList(LvUt.newCoords(P10,0,new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point(6,2)),Condition.newList(LvUt.newCoords(P4,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point(9,2)),Condition.newList(LvUt.newCoords(P4,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point(6,6)),Condition.newList(LvUt.newCoords(P0,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point(6,8)),Condition.newList(LvUt.newCoords(P2,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point(6,4)),Condition.newList(LvUt.newCoords(P2,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(6,5)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(6,8)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point(11,6)),Condition.newList(LvUt.newCoords(P10,0,new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(7,7)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point(8,2)),Condition.newList(LvUt.newCoords(P4,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point(5,6)),Condition.newList(LvUt.newCoords(P0,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(8,8)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(8,4)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(8,5)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(6,6)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point(7,2)),Condition.newList(LvUt.newCoords(P4,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point(6,9)),Condition.newList(LvUt.newCoords(P2,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point(6,6)),Condition.newList(LvUt.newCoords(P2,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point(11,7)),Condition.newList(LvUt.newCoords(P10,0,new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P3,0,new Point(6,5)),Condition.newList(LvUt.newCoords(P2,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point(5,2)),Condition.newList(LvUt.newCoords(P4,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(8,6)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(8,9)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point(11,8)),Condition.newList(LvUt.newCoords(P10,0,new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(6,9)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point(8,6)),Condition.newList(LvUt.newCoords(P0,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P1,0,new Point(9,6)),Condition.newList(LvUt.newCoords(P0,0,new Point(18,8),new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P11,0,new Point(11,5)),Condition.newList(LvUt.newCoords(P10,0,new Point(4,0))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P9,0,new Point(6,7)),Condition.newList(LvUt.newCoords(P9,0,new Point(9,7))));
m199objectMapCoordsEqListCoords_.addEntry(LvUt.newCoords(P5,0,new Point(4,2)),Condition.newList(LvUt.newCoords(P4,0,new Point(18,8),new Point(4,0))));
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
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X0,Y1),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace(-1);
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
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X2,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_CITY_LOCKED_TXT);
m199tileMiniMap_.setPlace(P4);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X5,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X0,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P1);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X1,Y1),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace(-1);
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
m199tileMiniMap_.setPlace(-1);
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
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X3,Y1),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X4,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROCK_TXT);
m199tileMiniMap_.setPlace(P6);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X5,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X4,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P3);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X2,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X3,Y2),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_ROAD_TXT);
m199tileMiniMap_.setPlace(P3);
m199tileMiniMap_.setHeros(true);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X3,Y0),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X2,Y3),m199tileMiniMap_);
m199tileMiniMap_=Instances.newTileMiniMap();
m199tileMiniMap_.setFile(F_GROUND_TXT);
m199tileMiniMap_.setPlace(-1);
m199tileMiniMap_.setHeros(false);
m199objectMapMiniMapCoordsTileMiniMap_.addEntry(new MiniMapCoords(X4,Y1),m199tileMiniMap_);
m199dataMap_.setMiniMap(m199objectMapMiniMapCoordsTileMiniMap_);
m199dataMap_.setUnlockedCity(F_CITY_UNLOCKED_TXT);
m199dataMap_.setBegin(LvUt.newCoords(P0,0,new Point(6,8)));
WildPk m199wildPk_=Instances.newWildPk();
m199wildPk_.setName(I_EVOLI);
m199wildPk_.setLevel(1);
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
