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
import code.sml.util.TranslationsLg;
import code.threads.ConcreteBoolean;
import code.threads.ConcreteInteger;
import code.util.StringMap;
import org.junit.Test;

public final class GamesPkTest extends EquallablePkFileUtil {
    @Test
    public void t1() {
        DataBase d_ = new DataBase(new DefaultGenerator(new CustomSeedGene()));
        GamesPk.initMessages(d_,"en");
        GamesPk.initMessages(d_,"fr");
        assertFalse(d_.isError());
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
        GamesPk.loadRomAndCheck(new DefaultGenerator(new CustomSeedGene()),f_,"",new StringMap<String>(),new ConcreteInteger(),new ConcreteBoolean(false));
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t12() {
        DataMap map_ = Instances.newDataMap();
        map_.setMiniMap(new MiniMapCoordsList());
        City city_ = Instances.newCity();
        Block block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        city_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        city_.getSavedlinks().addEntry(new PlaceInterConnect(new Point((short)0,(short)0), Direction.RIGHT),newCoords(1,0,0,0));
        map_.getPlaces().add( city_);
        Road road_ = Instances.newRoad();
        block_ = Instances.newBlock();
        block_.setHeight((short) 2);
        block_.setWidth((short) 2);
        road_.getLevel().getBlocks().addEntry(new Point((short)0,(short)0), block_);
        road_.getSavedlinks().addEntry(new PlaceInterConnect(new Point((short)0,(short)0),Direction.LEFT),newCoords(2,0,1,1));
        map_.getPlaces().add( road_);
        StringMap<String> fs_ = new StringMap<String>();
        fs_.addEntry("_",DocumentWriterAikiCoreUtil.setDataMap(map_));
        FacadeGame f_ = new FacadeGame();
        GamesPk.loadRomAndCheck(new DefaultGenerator(new CustomSeedGene()),f_,"", fs_,new ConcreteInteger(),new ConcreteBoolean(false));
        assertFalse(f_.isLoadedData());
    }
    @Test
    public void t13() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        GamesPk.enTr(GamesPk.initAppliTr(en_));
        GamesPk.frTr(GamesPk.initAppliTr(fr_));
        assertFalse(GamesPk.getPkGameDetailContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getConsultHostContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getHerosContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorButtonsContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorEggContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorSelEggContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorPkContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorSelPkContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorMvContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorSelMvContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getSelectDialogContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getFightActionContentTr(GamesPk.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPkGameDetailContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getConsultHostContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getHerosContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorButtonsContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorEggContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorSelEggContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorPkContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorSelPkContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorMvContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getPaginatorSelMvContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getSelectDialogContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
        assertFalse(GamesPk.getFightActionContentTr(GamesPk.getAppliTr(fr_)).getMapping().isEmpty());
    }
    protected static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    protected static Point newPoint(int _x, int _y) {
        return new Point((short)_x, (short)_y);
    }
    private DataBase core() {
        DataBase d_ = new DataBase(new DefaultGenerator(new CustomSeedGene()));
        d_.setCombos(Instances.newCombos());
        d_.setMap(Instances.newDataMap());
        return d_;
    }
}
