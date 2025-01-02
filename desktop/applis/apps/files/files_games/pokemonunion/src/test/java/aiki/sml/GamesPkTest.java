package aiki.sml;

import aiki.db.*;
import aiki.facade.FacadeGame;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.enums.Direction;
import aiki.map.levels.Block;
import aiki.map.places.City;
import aiki.map.places.Road;
import aiki.map.util.MiniMapCoordsList;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.montecarlo.*;
import code.sml.util.*;
import code.threads.ConcreteBoolean;
import code.threads.ConcreteInteger;
import code.util.StringMap;
import org.junit.Test;

public final class GamesPkTest extends EquallablePkFileUtil {
    @Test
    public void t1() {
        DataBase d_ = new DataBase(new DefaultGenerator(new CustomSeedGene()));
        GamesPk.initMessages(d_, null);
        assertFalse(d_.isError());
        TranslationsAppli app_ = new TranslationsAppli();
        TranslationsFile file_ = new TranslationsFile();
        file_.add("","");
        app_.getMapping().addEntry("_", file_);
        assertEq(1,GamesPk.messages(app_,"_").size());
    }
    @Test
    public void t2() {
        DataBase d_ = new DataBase(new DefaultGenerator(new CustomSeedGene()));
        FacadeGame f_ = new FacadeGame();
        f_.setData(d_);
        GamesPk.finish(f_,"",new ConcreteInteger(),new ConcreteBoolean(true),d_);
        assertTrue(f_.isLoadedData());
    }
    @Test
    public void t3() {
        DataBase d_ = core();
        FacadeGame f_ = new FacadeGame();
        GamesPk.finish(f_,"",new ConcreteInteger(),new ConcreteBoolean(true),d_);
        assertTrue(f_.isLoadedData());
    }
    @Test
    public void t4() {
        DataBase d_ = core();
        FacadeGame f_ = new FacadeGame();
        GamesPk.finish(f_,"",new ConcreteInteger(),new ConcreteBoolean(false),d_);
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t5() {
        DataBase d_ = core();
        FacadeGame f_ = new FacadeGame();
        GamesPk.postValidate(f_,"",new ConcreteInteger(),new ConcreteBoolean(true),d_);
        assertTrue(f_.isLoadedData());
    }
    @Test
    public void t6() {
        DataBase d_ = core();
        d_.setError(true);
        FacadeGame f_ = new FacadeGame();
        GamesPk.postValidate(f_,"",new ConcreteInteger(),new ConcreteBoolean(true),d_);
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t7() {
        DataBase d_ = core();
        FacadeGame f_ = new FacadeGame();
        GamesPk.postValidate(f_,"",new ConcreteInteger(),new ConcreteBoolean(false),d_);
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t8() {
        FacadeGame f_ = new FacadeGame();
        GamesPk.postValidate(f_,"",new ConcreteInteger(),new ConcreteBoolean(false),null);
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t9() {
        DataBase d_ = core();
        FacadeGame f_ = new FacadeGame();
        GamesPk.postLoad(f_,"",new ConcreteInteger(),new ConcreteBoolean(false),d_);
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t10() {
        FacadeGame f_ = new FacadeGame();
        GamesPk.postLoad(f_,"",new ConcreteInteger(),new ConcreteBoolean(false),null);
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t11() {
        FacadeGame f_ = new FacadeGame();
        GamesPk.loadRomAndCheck(new DefaultGenerator(new CustomSeedGene()),f_,"",new StringMap<String>(),new ConcreteInteger(),new ConcreteBoolean(false), BASE);
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t12() {
        DataMap map_ = Instances.newDataMap();
        map_.setMiniMap(new MiniMapCoordsList());
        City city_ = Instances.newCity();
        Block block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0), Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight(2);
        block_.setWidth(2);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(2,0,1,1));
        map_.getPlaces().add( road_);
        StringMap<String> fs_ = new StringMap<String>();
        fs_.addEntry("_",DocumentWriterAikiCoreUtil.setDataMap(map_));
        FacadeGame f_ = new FacadeGame();
        GamesPk.loadRomAndCheck(new DefaultGenerator(new CustomSeedGene()),f_,"", fs_,new ConcreteInteger(),new ConcreteBoolean(false), BASE);
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t13() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesPkGame.enTr(MessagesPkGame.initAppliTr(en_));
        MessagesPkGame.frTr(MessagesPkGame.initAppliTr(fr_));
        assertFalse(MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getSoftParamsContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getConsultHostContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getHerosContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorButtonsContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorEggContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelEggContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorPkContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelPkContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorMvContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelMvContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorItContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelItContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorHealItContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelHealItContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getSelectDialogContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getFightActionContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getWindowPkContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getScenePanelContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getBattleContentTr(MessagesPkGame.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getSoftParamsContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getConsultHostContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getHerosContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorButtonsContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorEggContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelEggContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorPkContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelPkContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorMvContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelMvContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorItContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelItContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorHealItContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getPaginatorSelHealItContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getSelectDialogContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getFightActionContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getWindowPkContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getScenePanelContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(MessagesPkGame.getBattleContentTr(MessagesPkGame.getAppliTr(fr_)).getMapping().isEmpty());
    }
    protected static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace(_place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(_level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    protected static Point newPoint(int _x, int _y) {
        return new Point(_x,_y);
    }
    private DataBase core() {
        DataBase d_ = new DataBase(new DefaultGenerator(new CustomSeedGene()));
        d_.setCombos(Instances.newCombos());
        d_.setMap(Instances.newDataMap());
        return d_;
    }
}
