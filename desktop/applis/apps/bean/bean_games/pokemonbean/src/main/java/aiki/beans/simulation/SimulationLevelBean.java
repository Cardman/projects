package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.game.DifficultyBeanForm;
import aiki.comparators.DictionaryComparator;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
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
import code.scripts.pages.aiki.*;
import code.util.*;

public final class SimulationLevelBean extends AbsLevelBean {
    private IntBeanChgLong noFight = new BeanChgLong();
    private IntBeanChgSubmit ok;

    public SimulationLevelBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        initTitle(file(),MessagesDataSimulationLevelsimu.M_P_85_TITLE_LEVEL_PLACE,MessagesDataSimulationLevelsimu.M_P_85_TITLE_OUT_ROAD,MessagesDataSimulationLevelsimu.M_P_85_TITLE_OUT_CITY,MessagesDataSimulationLevelsimu.M_P_85_TITLE_GYM,MessagesDataSimulationLevelsimu.M_P_85_TITLE_PK_CENTER);
//        if (getPossibleMultiLayer()) {
//            setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_LEVEL_PLACE),getPlaceName(),Long.toString(getLevelIndex())));
//        } else if (getOutside()) {
//            if (getRoad()) {
//                setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_OUT_ROAD),getPlaceName()));
//            } else {
//                setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_OUT_CITY),getPlaceName()));
//            }
//        } else {
//            if (getGym()) {
//                setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_GYM),getPlaceName()));
//            }
//            if (getPokemonCenter()) {
//                setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_PK_CENTER),getPlaceName()));
//            }
//        }
        initLine();
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMU_LEVEL,MessagesDataSimulationLevelsimu.M_P_85_NO_FIGHT);
        noFight = DifficultyBeanForm.iv(getBuilder().getGenInput(), this, getForms().getValLong(CST_NO_FIGHT));
        feedParents();
        initLine();
        ok = getBuilder().button(formatMessageRend(MessagesPkBean.SIMU_LEVEL,MessagesDataSimulationLevelsimu.M_P_85_OK));
        getOk().addEvt(new SimulationLevelBeanValidateNoFightAction(this));
        feedParents();
        formatMessageDir(Long.toString(noFight.valueLong()));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,this),MessagesPkBean.SIMU_LEVEL,MessagesDataSimulationLevelsimu.M_P_85_CANCEL);
        initGrid();
        getBuilder().colCount(getMapWidth());
        DictionaryComparator<Point, int[][]> tiles_ = getTiles();
        int len_ = tiles_.size();
        for (int i = 0; i < len_; i++) {
            int[][] img_ = tiles_.getValue(i);
            getBuilder().addImgCtsAnc(img_,"",new SimulationLevelBeanClickTile(this, i));
        }
        feedParents();
    }

    public IntBeanChgSubmit getOk() {
        return ok;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU_LEVEL).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        initTiles(false);
        noFight.valueLong(getForms().getValLong(CST_NO_FIGHT));
    }
    public String clickTile(int _index) {
        if (noFight.valueLong() < 0) {
            noFight.valueLong(0);
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
                getForms().put(CST_COORDS_TR, coords_);
                getForms().put(CST_NO_FIGHT, noFight.valueLong());
                return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
            }
            if (Point.eq(g_.getIndoor().getGymLeaderCoords(), pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.affectInside(new Point(ptInside_));
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS_TR, coords_);
                getForms().put(CST_NO_FIGHT, noFight.valueLong());
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
            getForms().put(CST_COORDS_TR, coords_);
            getForms().put(CST_NO_FIGHT, noFight.valueLong());
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (l_.getCharacters().contains(pt_)) {
            CharacterInRoadCave char_ = l_.getCharacters().getVal(pt_);
            if (char_ instanceof TrainerMultiFights) {
                TrainerMultiFights tr_ = (TrainerMultiFights) char_;
                updateNbFight(tr_);
                getForms().put(CST_NO_FIGHT, noFight.valueLong());
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex(lev_);
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS_TR, coords_);
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
                getForms().put(CST_NO_FIGHT, noFight.valueLong());
                getForms().put(CST_COORDS_TR, coords_);
                return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
            }
        }
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
    }

    private void updateNbFight(TrainerMultiFights _tr) {
        if (_tr.getTeamsRewards().size() <= noFight.valueLong()) {
            noFight.valueLong(_tr.getTeamsRewards().size()-1L);
        }
    }

    public IntBeanChgLong getNoFight() {
        return noFight;
    }

    public void noFight(long _n) {
        getForms().put(CST_NO_FIGHT, _n);
    }

}