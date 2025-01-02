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
import code.scripts.confs.PkScriptPages;

public class SimulationLevelBean extends AbsLevelBean {
    private int noFight;

    @Override
    public void beforeDisplaying() {
        initTiles(false);
        noFight = getForms().getValInt(CST_NO_FIGHT);
    }
    public String clickTile(int _index) {
        if (noFight < 0) {
            noFight = 0;
        }
        Point pt_ = getTiles().getKey(_index);
        //Level level_ = (Level) getForms().getVal(LEVEL_MAP);
        Coords sel_ = getForms().getValCoords(CST_COORDS);
        int pl_ = sel_.getNumberPlace();
        int lev_ = sel_.getLevel().getLevelIndex();
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        //getForms().put(FROM_LIST, false);
        if (p_ instanceof City&&sel_.isInside()) {
            City c_ = (City) p_;
            Point ptInside_ = sel_.getInsideBuilding();
            Building b_ = c_.getBuildings().getVal(ptInside_);
            Gym g_ = (Gym) b_;
            if (g_.getIndoor().getGymTrainers().contains(pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.affectInside(new Point(ptInside_));
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS, coords_);
                getForms().put(CST_NO_FIGHT, noFight);
                return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
            }
            if (Point.eq(g_.getIndoor().getGymLeaderCoords(), pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.affectInside(new Point(ptInside_));
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS, coords_);
                getForms().put(CST_NO_FIGHT, noFight);
                return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
            }
        }
        if (!(p_ instanceof Campaign)) {
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
        }
        Campaign c_ = (Campaign) p_;
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevelsMap().getVal(lev_);
        if (l_.getDualFights().contains(pt_)) {
            Coords coords_ = new Coords();
            coords_.setNumberPlace(pl_);
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex(lev_);
            coords_.getLevel().setPoint(new Point(pt_));
            getForms().put(CST_COORDS, coords_);
            getForms().put(CST_NO_FIGHT, noFight);
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (l_.getCharacters().contains(pt_)) {
            CharacterInRoadCave char_ = l_.getCharacters().getVal(pt_);
            if (char_ instanceof TrainerMultiFights) {
                TrainerMultiFights tr_ = (TrainerMultiFights) char_;
                updateNbFight(tr_);
                getForms().put(CST_NO_FIGHT, noFight);
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex(lev_);
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS, coords_);
                //noFight
                return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
            }
        }
        for (Point ptKey_: l_.getDualFights().getKeys()) {
            DualFight d_ = l_.getDualFights().getVal(ptKey_);
            if (Point.eq(d_.getPt(), pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex(lev_);
                coords_.getLevel().setPoint(new Point(ptKey_));
                getForms().put(CST_NO_FIGHT, noFight);
                getForms().put(CST_COORDS, coords_);
                return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
            }
        }
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
    }

    private void updateNbFight(TrainerMultiFights _tr) {
        if (_tr.getTeamsRewards().size() <= noFight) {
            noFight = _tr.getTeamsRewards().size();
            noFight--;
        }
    }

    public int getNoFight() {
        return noFight;
    }

    public void setNoFight(int _n) {
        getForms().put(CST_NO_FIGHT,_n);
        this.noFight = _n;
    }

}