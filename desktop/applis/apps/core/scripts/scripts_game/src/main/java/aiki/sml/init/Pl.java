package aiki.sml.init;
import aiki.map.characters.enums.*;
import aiki.map.characters.*;
import aiki.map.places.*;
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
public final class Pl extends CstIgame{
private Pl(){}
public static City p0(){
City m2002city_=Instances.newCity();
PointsBuilding m2002objectMapPointBuilding_=new PointsBuilding(new CollCapacity(2));
PokemonCenter m2002pokemonCenter_ = Instances.newPokemonCenter();
m2002pokemonCenter_.setLevel(Lv.pc0());
m2002pokemonCenter_.setImageFileName(F_BUILDING_INSIDE_LINK_TXT);
m2002pokemonCenter_.setExitCity(new Point((short)5,(short)9));
m2002objectMapPointBuilding_.addEntry(new Point((short)6,(short)7),m2002pokemonCenter_);
Gym m2002gym_ = Instances.newGym();
m2002gym_.setLevel(Lv.p0());
m2002gym_.setImageFileName(F_BUILDING_INSIDE_LINK_TXT);
m2002gym_.setExitCity(new Point((short)5,(short)9));
m2002objectMapPointBuilding_.addEntry(new Point((short)18,(short)8),m2002gym_);
m2002city_.setBuildings(m2002objectMapPointBuilding_);
m2002city_.setLevel(Lv.po0());
m2002city_.setName(I_BOURG_PALETTE);
PlaceInterConnects m2002objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(3));
m2002objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)25,(short)9),Direction.RIGHT),LvUt.newCoords(9,0,new Point((short)0,(short)4)));
m2002objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)9,(short)0),Direction.UP),LvUt.newCoords(1,0,new Point((short)4,(short)11)));
m2002objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)9),Direction.LEFT),LvUt.newCoords(11,0,new Point((short)11,(short)4)));
m2002city_.setSavedlinks(m2002objectMapPlaceInterConnectCoords_);
return m2002city_;
}
public static Road p1(){
Road m2003road_=Instances.newRoad();
m2003road_.setName(P_R_1);
m2003road_.setLevel(Lv.p1());
PlaceInterConnects m2003objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
m2003objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)4,(short)11),Direction.DOWN),LvUt.newCoords(0,0,new Point((short)9,(short)0)));
m2003objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)4,(short)0),Direction.UP),LvUt.newCoords(2,0,new Point((short)9,(short)18)));
m2003road_.setSavedlinks(m2003objectMapPlaceInterConnectCoords_);
return m2003road_;
}
public static City p2(){
City m2004city_=Instances.newCity();
PointsBuilding m2004objectMapPointBuilding_=new PointsBuilding(new CollCapacity(2));
Gym m2004gym_ = Instances.newGym();
m2004gym_.setLevel(Lv.p2());
m2004gym_.setImageFileName(F_BUILDING_INSIDE_LINK_TXT);
m2004gym_.setExitCity(new Point((short)5,(short)9));
m2004objectMapPointBuilding_.addEntry(new Point((short)18,(short)8),m2004gym_);
PokemonCenter m2004pokemonCenter_ = Instances.newPokemonCenter();
m2004pokemonCenter_.setLevel(Lv.pc2());
m2004pokemonCenter_.setImageFileName(F_BUILDING_INSIDE_LINK_TXT);
m2004pokemonCenter_.setExitCity(new Point((short)5,(short)9));
m2004objectMapPointBuilding_.addEntry(new Point((short)6,(short)7),m2004pokemonCenter_);
m2004city_.setBuildings(m2004objectMapPointBuilding_);
m2004city_.setLevel(Lv.po2());
m2004city_.setName(I_JUMIELLE);
PlaceInterConnects m2004objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
m2004objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)25,(short)9),Direction.RIGHT),LvUt.newCoords(3,0,new Point((short)0,(short)4)));
m2004objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)9,(short)18),Direction.DOWN),LvUt.newCoords(1,0,new Point((short)4,(short)0)));
m2004city_.setSavedlinks(m2004objectMapPlaceInterConnectCoords_);
return m2004city_;
}
public static Road p3(){
Road m2005road_=Instances.newRoad();
m2005road_.setName(P_R_2);
m2005road_.setLevel(Lv.p3());
PlaceInterConnects m2005objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
m2005objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)11,(short)4),Direction.RIGHT),LvUt.newCoords(4,0,new Point((short)0,(short)9)));
m2005objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)4),Direction.LEFT),LvUt.newCoords(2,0,new Point((short)25,(short)9)));
m2005road_.setSavedlinks(m2005objectMapPlaceInterConnectCoords_);
return m2005road_;
}
public static City p4(){
City m2006city_=Instances.newCity();
PointsBuilding m2006objectMapPointBuilding_=new PointsBuilding(new CollCapacity(2));
PokemonCenter m2006pokemonCenter_ = Instances.newPokemonCenter();
m2006pokemonCenter_.setLevel(Lv.pc4());
m2006pokemonCenter_.setImageFileName(F_BUILDING_INSIDE_LINK_TXT);
m2006pokemonCenter_.setExitCity(new Point((short)5,(short)9));
m2006objectMapPointBuilding_.addEntry(new Point((short)6,(short)7),m2006pokemonCenter_);
Gym m2006gym_ = Instances.newGym();
m2006gym_.setLevel(Lv.p4());
m2006gym_.setImageFileName(F_BUILDING_INSIDE_LINK_TXT);
m2006gym_.setExitCity(new Point((short)5,(short)9));
m2006objectMapPointBuilding_.addEntry(new Point((short)18,(short)8),m2006gym_);
m2006city_.setBuildings(m2006objectMapPointBuilding_);
m2006city_.setLevel(Lv.po4());
m2006city_.setName(I_TERREE);
PlaceInterConnects m2006objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
m2006objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)9,(short)18),Direction.DOWN),LvUt.newCoords(5,0,new Point((short)4,(short)0)));
m2006objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)9),Direction.LEFT),LvUt.newCoords(3,0,new Point((short)11,(short)4)));
m2006city_.setSavedlinks(m2006objectMapPlaceInterConnectCoords_);
return m2006city_;
}
public static Road p5(){
Road m2007road_=Instances.newRoad();
m2007road_.setName(P_R_3);
m2007road_.setLevel(Lv.p5());
PointsLink m2007objectMapPointLink_=new PointsLink(new CollCapacity(1));
m2007objectMapPointLink_.addEntry(new Point((short)7,(short)6),LvUt.newLink(F_LINK_OUTSIDE_TXT,LvUt.newCoords(6,0,new Point((short)6,(short)4)),null));
m2007road_.setLinksWithCaves(m2007objectMapPointLink_);
PlaceInterConnects m2007objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(1));
m2007objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)4,(short)0),Direction.UP),LvUt.newCoords(4,0,new Point((short)9,(short)18)));
m2007road_.setSavedlinks(m2007objectMapPlaceInterConnectCoords_);
return m2007road_;
}
public static Cave p6(){
Cave m2008cave_=Instances.newCave();
m2008cave_.setName(P_GRANDE_CAVE);
CustList<LevelCave> m2008custListLevelCave_=new CustList<LevelCave>(new CollCapacity(4));
m2008custListLevelCave_.add(Lv.p6l0());
m2008custListLevelCave_.add(Lv.p6l1());
m2008cave_.setLevels(m2008custListLevelCave_);
LevelPoints m2008objectMapLevelPointLink_=new LevelPoints(new CollCapacity(2));
m2008objectMapLevelPointLink_.addEntry(LvUt.newLevelPoint(0,new Point((short)6,(short)23)),LvUt.newLink(F_LINK_INSIDE_TXT,LvUt.newCoords(7,0,new Point((short)6,(short)4)),Direction.UP));
m2008objectMapLevelPointLink_.addEntry(LvUt.newLevelPoint(0,new Point((short)6,(short)4)),LvUt.newLink(F_LINK_INSIDE_TXT,LvUt.newCoords(5,0,new Point((short)7,(short)5)),Direction.DOWN));
m2008cave_.setLinksWithOtherPlaces(m2008objectMapLevelPointLink_);
return m2008cave_;
}
public static Road p7(){
Road m2009road_=Instances.newRoad();
m2009road_.setName(P_R_4);
m2009road_.setLevel(Lv.p7());
PointsLink m2009objectMapPointLink_=new PointsLink(new CollCapacity(1));
m2009objectMapPointLink_.addEntry(new Point((short)6,(short)3),LvUt.newLink(F_LINK_OUTSIDE_2_TXT,LvUt.newCoords(6,0,new Point((short)6,(short)23)),null));
m2009road_.setLinksWithCaves(m2009objectMapPointLink_);
PlaceInterConnects m2009objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(1));
m2009objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)4,(short)9),Direction.DOWN),LvUt.newCoords(8,0,new Point((short)9,(short)0)));
m2009road_.setSavedlinks(m2009objectMapPlaceInterConnectCoords_);
return m2009road_;
}
public static City p8(){
City m2010city_=Instances.newCity();
PointsBuilding m2010objectMapPointBuilding_=new PointsBuilding(new CollCapacity(1));
PokemonCenter m2010pokemonCenter_ = Instances.newPokemonCenter();
m2010pokemonCenter_.setLevel(Lv.pc8());
m2010pokemonCenter_.setImageFileName(F_BUILDING_INSIDE_LINK_TXT);
m2010pokemonCenter_.setExitCity(new Point((short)5,(short)9));
m2010objectMapPointBuilding_.addEntry(new Point((short)6,(short)7),m2010pokemonCenter_);
m2010city_.setBuildings(m2010objectMapPointBuilding_);
m2010city_.setLevel(Lv.po8());
m2010city_.setName(I_SACANGI);
PlaceInterConnects m2010objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
m2010objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)9,(short)0),Direction.UP),LvUt.newCoords(7,0,new Point((short)4,(short)9)));
m2010objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)9),Direction.LEFT),LvUt.newCoords(9,0,new Point((short)11,(short)4)));
m2010city_.setSavedlinks(m2010objectMapPlaceInterConnectCoords_);
return m2010city_;
}
public static Road p9(){
Road m2011road_=Instances.newRoad();
m2011road_.setName(P_R_5);
m2011road_.setLevel(Lv.p9());
PlaceInterConnects m2011objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
m2011objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)11,(short)4),Direction.RIGHT),LvUt.newCoords(8,0,new Point((short)0,(short)9)));
m2011objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)4),Direction.LEFT),LvUt.newCoords(0,0,new Point((short)25,(short)9)));
m2011road_.setSavedlinks(m2011objectMapPlaceInterConnectCoords_);
return m2011road_;
}
public static League p10(){
League m2012league_=Instances.newLeague();
m2012league_.setName(P_LIGUE_DE_BASE);
CustList<LevelLeague> m2012custListLevelLeague_=new CustList<LevelLeague>(new CollCapacity(2));
m2012custListLevelLeague_.add(Lv.p10t0());
m2012custListLevelLeague_.add(Lv.p10t1());
m2012league_.setRooms(m2012custListLevelLeague_);
m2012league_.setAccessCoords(LvUt.newCoords(9,0,new Point((short)7,(short)7)));
m2012league_.setFileName(F_LINK_LEAGUE_LEVELS_TXT);
m2012league_.setBegin(new Point((short)4,(short)0));
return m2012league_;
}
public static Road p11(){
Road m2013road_=Instances.newRoad();
m2013road_.setName(P_R_6);
m2013road_.setLevel(Lv.p11());
PlaceInterConnects m2013objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(1));
m2013objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)11,(short)4),Direction.RIGHT),LvUt.newCoords(0,0,new Point((short)0,(short)9)));
m2013road_.setSavedlinks(m2013objectMapPlaceInterConnectCoords_);
return m2013road_;
}
}
