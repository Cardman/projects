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
public final class Pl{
private Pl(){}
public static City p0(){
City city_=Instances.newCity();
PointsBuilding objectMapPointBuilding_=new PointsBuilding(new CollCapacity(2));
PokemonCenter pokemonCenter_ = Instances.newPokemonCenter();
pokemonCenter_.setLevel(Lv.pc0());
pokemonCenter_.setImageFileName("building_inside_link.txt");
pokemonCenter_.setExitCity(new Point((short)5,(short)9));
objectMapPointBuilding_.addEntry(new Point((short)6,(short)7),pokemonCenter_);
Gym gym_ = Instances.newGym();
gym_.setLevel(Lv.p0());
gym_.setImageFileName("building_inside_link.txt");
gym_.setExitCity(new Point((short)5,(short)9));
objectMapPointBuilding_.addEntry(new Point((short)18,(short)8),gym_);
city_.setBuildings(objectMapPointBuilding_);
city_.setLevel(Lv.po0());
city_.setName("BOURG_PALETTE");
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(3));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)25,(short)9),Direction.RIGHT),LvUt.newCoords(9,0,new Point((short)0,(short)4)));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)9,(short)0),Direction.UP),LvUt.newCoords(1,0,new Point((short)4,(short)11)));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)9),Direction.LEFT),LvUt.newCoords(11,0,new Point((short)11,(short)4)));
city_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return city_;
}
public static Road p1(){
Road road_=Instances.newRoad();
road_.setName("R 1");
road_.setLevel(Lv.p1());
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)4,(short)11),Direction.DOWN),LvUt.newCoords(0,0,new Point((short)9,(short)0)));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)4,(short)0),Direction.UP),LvUt.newCoords(2,0,new Point((short)9,(short)18)));
road_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return road_;
}
public static City p2(){
City city_=Instances.newCity();
PointsBuilding objectMapPointBuilding_=new PointsBuilding(new CollCapacity(2));
Gym gym_ = Instances.newGym();
gym_.setLevel(Lv.p2());
gym_.setImageFileName("building_inside_link.txt");
gym_.setExitCity(new Point((short)5,(short)9));
objectMapPointBuilding_.addEntry(new Point((short)18,(short)8),gym_);
PokemonCenter pokemonCenter_ = Instances.newPokemonCenter();
pokemonCenter_.setLevel(Lv.pc2());
pokemonCenter_.setImageFileName("building_inside_link.txt");
pokemonCenter_.setExitCity(new Point((short)5,(short)9));
objectMapPointBuilding_.addEntry(new Point((short)6,(short)7),pokemonCenter_);
city_.setBuildings(objectMapPointBuilding_);
city_.setLevel(Lv.po2());
city_.setName("JUMIELLE");
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)25,(short)9),Direction.RIGHT),LvUt.newCoords(3,0,new Point((short)0,(short)4)));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)9,(short)18),Direction.DOWN),LvUt.newCoords(1,0,new Point((short)4,(short)0)));
city_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return city_;
}
public static Road p3(){
Road road_=Instances.newRoad();
road_.setName("R 2");
road_.setLevel(Lv.p3());
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)11,(short)4),Direction.RIGHT),LvUt.newCoords(4,0,new Point((short)0,(short)9)));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)4),Direction.LEFT),LvUt.newCoords(2,0,new Point((short)25,(short)9)));
road_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return road_;
}
public static City p4(){
City city_=Instances.newCity();
PointsBuilding objectMapPointBuilding_=new PointsBuilding(new CollCapacity(2));
PokemonCenter pokemonCenter_ = Instances.newPokemonCenter();
pokemonCenter_.setLevel(Lv.pc4());
pokemonCenter_.setImageFileName("building_inside_link.txt");
pokemonCenter_.setExitCity(new Point((short)5,(short)9));
objectMapPointBuilding_.addEntry(new Point((short)6,(short)7),pokemonCenter_);
Gym gym_ = Instances.newGym();
gym_.setLevel(Lv.p4());
gym_.setImageFileName("building_inside_link.txt");
gym_.setExitCity(new Point((short)5,(short)9));
objectMapPointBuilding_.addEntry(new Point((short)18,(short)8),gym_);
city_.setBuildings(objectMapPointBuilding_);
city_.setLevel(Lv.po4());
city_.setName("TERREE");
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)9,(short)18),Direction.DOWN),LvUt.newCoords(5,0,new Point((short)4,(short)0)));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)9),Direction.LEFT),LvUt.newCoords(3,0,new Point((short)11,(short)4)));
city_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return city_;
}
public static Road p5(){
Road road_=Instances.newRoad();
road_.setName("R 3");
road_.setLevel(Lv.p5());
PointsLink objectMapPointLink_=new PointsLink(new CollCapacity(1));
objectMapPointLink_.addEntry(new Point((short)7,(short)6),LvUt.newLink("link_outside.txt",LvUt.newCoords(6,0,new Point((short)6,(short)4)),null));
road_.setLinksWithCaves(objectMapPointLink_);
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(1));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)4,(short)0),Direction.UP),LvUt.newCoords(4,0,new Point((short)9,(short)18)));
road_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return road_;
}
public static Cave p6(){
Cave cave_=Instances.newCave();
cave_.setName("Grande Cave");
CustList<LevelCave> custListLevelCave_=new CustList<LevelCave>(new CollCapacity(4));
custListLevelCave_.add(Lv.p6l0());
custListLevelCave_.add(Lv.p6l1());
cave_.setLevels(custListLevelCave_);
LevelPoints objectMapLevelPointLink_=new LevelPoints(new CollCapacity(2));
objectMapLevelPointLink_.addEntry(LvUt.newLevelPoint(0,new Point((short)6,(short)23)),LvUt.newLink("link_inside.txt",LvUt.newCoords(7,0,new Point((short)6,(short)4)),Direction.UP));
objectMapLevelPointLink_.addEntry(LvUt.newLevelPoint(0,new Point((short)6,(short)4)),LvUt.newLink("link_inside.txt",LvUt.newCoords(5,0,new Point((short)7,(short)5)),Direction.DOWN));
cave_.setLinksWithOtherPlaces(objectMapLevelPointLink_);
return cave_;
}
public static Road p7(){
Road road_=Instances.newRoad();
road_.setName("R 4");
road_.setLevel(Lv.p7());
PointsLink objectMapPointLink_=new PointsLink(new CollCapacity(1));
objectMapPointLink_.addEntry(new Point((short)6,(short)3),LvUt.newLink("link_outside_2.txt",LvUt.newCoords(6,0,new Point((short)6,(short)23)),null));
road_.setLinksWithCaves(objectMapPointLink_);
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(1));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)4,(short)9),Direction.DOWN),LvUt.newCoords(8,0,new Point((short)9,(short)0)));
road_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return road_;
}
public static City p8(){
City city_=Instances.newCity();
PointsBuilding objectMapPointBuilding_=new PointsBuilding(new CollCapacity(1));
PokemonCenter pokemonCenter_ = Instances.newPokemonCenter();
pokemonCenter_.setLevel(Lv.pc8());
pokemonCenter_.setImageFileName("building_inside_link.txt");
pokemonCenter_.setExitCity(new Point((short)5,(short)9));
objectMapPointBuilding_.addEntry(new Point((short)6,(short)7),pokemonCenter_);
city_.setBuildings(objectMapPointBuilding_);
city_.setLevel(Lv.po8());
city_.setName("SACANGI");
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)9,(short)0),Direction.UP),LvUt.newCoords(7,0,new Point((short)4,(short)9)));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)9),Direction.LEFT),LvUt.newCoords(9,0,new Point((short)11,(short)4)));
city_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return city_;
}
public static Road p9(){
Road road_=Instances.newRoad();
road_.setName("R 5");
road_.setLevel(Lv.p9());
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(2));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)11,(short)4),Direction.RIGHT),LvUt.newCoords(8,0,new Point((short)0,(short)9)));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)0,(short)4),Direction.LEFT),LvUt.newCoords(0,0,new Point((short)25,(short)9)));
road_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return road_;
}
public static League p10(){
League league_=Instances.newLeague();
league_.setName("Ligue de base");
CustList<LevelLeague> custListLevelLeague_=new CustList<LevelLeague>(new CollCapacity(2));
custListLevelLeague_.add(Lv.p10t0());
custListLevelLeague_.add(Lv.p10t1());
league_.setRooms(custListLevelLeague_);
league_.setAccessCoords(LvUt.newCoords(9,0,new Point((short)7,(short)7)));
league_.setFileName("link_league_levels.txt");
league_.setBegin(new Point((short)4,(short)0));
return league_;
}
public static Road p11(){
Road road_=Instances.newRoad();
road_.setName("R 6");
road_.setLevel(Lv.p11());
PlaceInterConnects objectMapPlaceInterConnectCoords_=new PlaceInterConnects(new CollCapacity(1));
objectMapPlaceInterConnectCoords_.addEntry(new PlaceInterConnect(new Point((short)11,(short)4),Direction.RIGHT),LvUt.newCoords(0,0,new Point((short)0,(short)9)));
road_.setSavedlinks(objectMapPlaceInterConnectCoords_);
return road_;
}
}
