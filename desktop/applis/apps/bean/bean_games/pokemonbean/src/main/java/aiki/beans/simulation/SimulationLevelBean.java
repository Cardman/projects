package aiki.beans.simulation;

import aiki.beans.AbsLevelBean;
import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Campaign;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;

public class SimulationLevelBean extends AbsLevelBean {
    private int noFight;

    @Override
    public void beforeDisplaying() {
        initTiles();
        noFight = getForms().getValInt(CST_NO_FIGHT);
    }
    public static String cancel() {
        return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public String clickTile(int _index) {
        if (noFight < 0) {
            noFight = 0;
        }
        Point pt_ = getTiles().getKey(_index);
        //Level level_ = (Level) getForms().getVal(LEVEL_MAP);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        //getForms().put(FROM_LIST, false);
        if (p_ instanceof City) {
            return city(pt_, (short) pl_, (City) p_);
        }
        if (!(p_ instanceof Campaign)) {
            return DataBase.EMPTY_STRING;
        }
        Campaign c_ = (Campaign) p_;
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevelsMap().getVal((byte) lev_);
        if (l_.getDualFights().contains(pt_)) {
            Coords coords_ = new Coords();
            coords_.setNumberPlace((short) pl_);
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex((byte) lev_);
            coords_.getLevel().setPoint(new Point(pt_));
            getForms().put(CST_COORDS, coords_);
            getForms().put(CST_NO_FIGHT, noFight);
            return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (l_.getCharacters().contains(pt_)) {
            CharacterInRoadCave char_ = l_.getCharacters().getVal(pt_);
            if (char_ instanceof TrainerMultiFights) {
                TrainerMultiFights tr_ = (TrainerMultiFights) char_;
                if (tr_.getTeamsRewards().size() <= noFight) {
                    noFight = tr_.getTeamsRewards().size();
                    noFight--;
                }
                getForms().put(CST_NO_FIGHT, noFight);
                Coords coords_ = new Coords();
                coords_.setNumberPlace((short) pl_);
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex((byte) lev_);
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS, coords_);
                //noFight
                return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
            }
        }
        for (Point ptKey_: l_.getDualFights().getKeys()) {
            DualFight d_ = l_.getDualFights().getVal(ptKey_);
            if (Point.eq(d_.getPt(), pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace((short) pl_);
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex((byte) lev_);
                coords_.getLevel().setPoint(new Point(ptKey_));
                getForms().put(CST_NO_FIGHT, noFight);
                getForms().put(CST_COORDS, coords_);
                return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
            }
        }
        return DataBase.EMPTY_STRING;
    }

    private String city(Point _pt, short _pl, City _p) {
        Point ptInside_ = getForms().getValPt(CST_INSIDE);
        Building b_ = _p.getBuildings().getVal(ptInside_);
        Gym g_ = (Gym) b_;
        if (g_.getIndoor().getGymTrainers().contains(_pt)) {
            Coords coords_ = new Coords();
            coords_.setNumberPlace(_pl);
            coords_.setLevel(new LevelPoint());
            coords_.setInsideBuilding(new Point(ptInside_));
            coords_.getLevel().setPoint(new Point(_pt));
            getForms().put(CST_COORDS, coords_);
            getForms().put(CST_NO_FIGHT, noFight);
            return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (Point.eq(g_.getIndoor().getGymLeaderCoords(), _pt)) {
            Coords coords_ = new Coords();
            coords_.setNumberPlace(_pl);
            coords_.setLevel(new LevelPoint());
            coords_.setInsideBuilding(new Point(ptInside_));
            coords_.getLevel().setPoint(new Point(_pt));
            getForms().put(CST_COORDS, coords_);
            getForms().put(CST_NO_FIGHT, noFight);
            return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        return DataBase.EMPTY_STRING;
    }

    public int getNoFight() {
        return noFight;
    }

    public void setNoFight(int _n) {
        this.noFight = _n;
    }

}