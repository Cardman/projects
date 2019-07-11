package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.characters.TempTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.InitializedPlace;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.map.util.Limits;
import aiki.map.util.ScreenCoords;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.images.Image;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.*;
import code.util.ObjectMap;
import code.util.comparators.ComparatorBoolean;


public abstract class Level {

    /**
     * Left top for key, rectangle block for value that is an obstacle or an
     * area of wild pokemon.
     */
    private ObjectMap<Point, Block> blocks;

    /**
     * @param _data
     */
    public void validate(DataBase _data, LevelArea _level) {
        long sum_ = 0;
        EqList<Point> used_ = new EqList<Point>();
        for (EntryCust<Point, Block> e : blocks.entryList()) {
            if (!e.getValue().isValid()) {
                _data.setError(true);
                continue;
            }
            Point id_ = e.getKey();
            Block block_ = e.getValue();
            int x_ = id_.getx();
            int y_ = id_.gety();
            int w_ = block_.getWidth();
            int h_ = block_.getHeight();
            int xMax_ = x_ + w_;
            int yMax_ = y_ + h_;
            for (int x = x_; x < xMax_; x++) {
                for (int y = y_; y < yMax_; y++) {
                    Point pt_ = new Point((short) x, (short) y);
                    if (used_.containsObj(pt_)) {
                        _data.setError(true);
                    }
                    used_.add(pt_);
                }
            }
            sum_ += h_ * w_;
        }
        if (sum_ != _level.size()) {
            _data.setError(true);
        }
    }


    public boolean hasValidImage(DataBase _data) {
        for (EntryCust<Point, Block> e : blocks.entryList()) {
            if (!e.getValue().hasValidImage(_data)) {
                return false;
            }
        }
        return true;
    }

    public static ObjectMap<Point, int[][]> getLevelBackgroundImage(
            DataBase _data, Coords _coords) {
        int sideLen_ = _data.getMap().getSideLength();
        Place pl_ = _data.getMap().getPlaces().getVal(_coords.getNumberPlace());
        Level lev_ = pl_.getLevelByCoords(_coords);
        ObjectMap<Point, int[][]> tiles_ = new ObjectMap<Point, int[][]>();
        for (Point p : lev_.getBlocks().getKeys()) {
            Block bl_ = lev_.getBlocks().getVal(p);
            int[][] image_ = _data.getImage(bl_.getTileFileName());
            short w_ = bl_.getWidth();
            short h_ = bl_.getHeight();
            for (short x = CustList.FIRST_INDEX; x < w_; x++) {
                for (short y = CustList.FIRST_INDEX; y < h_; y++) {
                    int[][] img_ = Image.clipSixtyFour(image_, x * sideLen_, y
                            * sideLen_, sideLen_, sideLen_);
                    tiles_.put(
                            new Point((short) (x + p.getx()), (short) (y + p
                                    .gety())), img_);
                }
            }
        }
        return tiles_;
    }

    public static ObjectMap<Point, int[][]> getLevelForegroundImage(
            DataBase _data, Coords _coords) {
        Place pl_ = _data.getMap().getPlaces().getVal(_coords.getNumberPlace());
        Level lev_ = pl_.getLevelByCoords(_coords);
        ObjectMap<Point, int[][]> frontTiles_ = new ObjectMap<Point, int[][]>();
        for (Place p : _data.getMap().getPlaces().values()) {
            if (!(p instanceof League)) {
                continue;
            }
            Coords access_ = ((League) p).getAccessCoords();
            if (!Numbers.eq(access_.getNumberPlace(), _coords.getNumberPlace())) {
                continue;
            }
            // if (access_.isInside() != _coords.isInside()) {
            // continue;
            // }
            if (ComparatorBoolean.diff(access_.isInside(), _coords.isInside())) {
                continue;
            }
            if (access_.getInsideBuilding() == null) {
                if (_coords.getInsideBuilding() != null) {
                    continue;
                }
            } else if (_coords.getInsideBuilding() == null){
                continue;
            } else if (!Point.eq(access_.getInsideBuilding(),
                    _coords.getInsideBuilding())) {
                continue;
            }
            if (!Numbers.eq(access_.getLevel().getLevelIndex(), _coords
                    .getLevel().getLevelIndex())) {
                continue;
            }
            Point pt_ = access_.getLevel().getPoint();
            frontTiles_.put(new Point(pt_),
                    _data.getLink(((League) p).getFileName()));
        }
        if (lev_ instanceof LevelLeague) {
            LevelLeague lv_ = (LevelLeague) lev_;
            Person person_ = lv_.getTrainer();
            frontTiles_.put(new Point(lv_.getTrainerCoords()),
                    _data.getPerson(person_.getImageMiniFileName()));
            frontTiles_.put(new Point(lv_.getAccessPoint()),
                    _data.getLink(lv_.getFileName()));
        }
        if (lev_ instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon lv_ = (LevelWithWildPokemon) lev_;
            for (Point p : lv_.getCharacters().getKeys()) {
                Person person_ = lv_.getPerson(p);
                frontTiles_.put(new Point(p),
                        _data.getPerson(person_.getImageMiniFileName()));
            }
            for (Point p : lv_.getLegendaryPks().getKeys()) {
                WildPk pk_ = lv_.getPokemon(p);
                frontTiles_.put(new Point(p),
                        _data.getMiniPk().getVal(pk_.getName()));
            }
            for (Point p : lv_.getItems().getKeys()) {
                frontTiles_.put(new Point(p),
                        _data.getMiniItems().getVal(lv_.getItems().getVal(p)));
            }
            for (Point p : lv_.getTm().getKeys()) {
                frontTiles_.put(new Point(p), _data.getImageTmHm());
            }
            for (Point p : lv_.getHm().getKeys()) {
                frontTiles_.put(new Point(p), _data.getImageTmHm());
            }
            for (Point p : lv_.getDualFights().getKeys()) {
                DualFight dual_ = lv_.getDualFights().getVal(p);
                TempTrainer tmp_ = dual_.getFoeTrainer();
                frontTiles_.put(new Point(p),
                        _data.getPerson(tmp_.getImageMiniFileName()));
            }
            for (DualFight d : lv_.getDualFights().values()) {
                TempTrainer tmp_ = d.getFoeTrainer();
                frontTiles_.put(new Point(d.getPt()), _data.getPerson(tmp_
                        .getImageMiniSecondTrainerFileName()));
            }
        }
        if (_coords.isInside()) {
            City city_ = (City) pl_;
            Building building_ = city_.getBuildings().getVal(
                    _coords.getInsideBuilding());
            frontTiles_.put(new Point(building_.getExitCity()),
                    _data.getLink(building_.getImageFileName()));
        }
        if (lev_ instanceof LevelIndoorPokemonCenter) {
            LevelIndoorPokemonCenter lv_ = (LevelIndoorPokemonCenter) lev_;
            for (Point p : lv_.getGerants().getKeys()) {
                Person person_ = lv_.getGerants().getVal(p);
                frontTiles_.put(new Point(p),
                        _data.getPerson(person_.getImageMiniFileName()));
            }
            frontTiles_.put(new Point(lv_.getStorageCoords()),
                    _data.getStorage());
        }
        if (lev_ instanceof LevelIndoorGym) {
            LevelIndoorGym lv_ = (LevelIndoorGym) lev_;
            for (Point p : lv_.getGymTrainers().getKeys()) {
                Person person_ = lv_.getGymTrainers().getVal(p);
                frontTiles_.put(new Point(p),
                        _data.getPerson(person_.getImageMiniFileName()));
            }
            Person person_ = lv_.getGymLeader();
            frontTiles_.put(new Point(lv_.getGymLeaderCoords()),
                    _data.getPerson(person_.getImageMiniFileName()));
        }
        if (pl_ instanceof Cave) {
            Cave cave_ = (Cave) pl_;
            for (LevelPoint lp_ : cave_.getLinksWithOtherPlaces().getKeys()) {
                if (!Numbers.eq(lp_.getLevelIndex(), _coords.getLevel()
                        .getLevelIndex())) {
                    continue;
                }
                Link link_ = cave_.getLinksWithOtherPlaces().getVal(lp_);
                frontTiles_.put(new Point(lp_.getPoint()),
                        _data.getLink(link_.getFileName()));
            }
            LevelCave lv_ = cave_.getLevelCave(_coords.getLevel());
            for (Point p : lv_.getLinksOtherLevels().getKeys()) {
                Link link_ = lv_.getLinksOtherLevels().getVal(p);
                frontTiles_.put(new Point(p),
                        _data.getLink(link_.getFileName()));
            }
        }
        if (pl_ instanceof InitializedPlace) {
            InitializedPlace init_ = (InitializedPlace) pl_;
            for (Point p : init_.getLinksWithCaves().getKeys()) {
                Link link_ = init_.getLinksWithCaves().getVal(p);
                frontTiles_.put(new Point(p),
                        _data.getLink(link_.getFileName()));
            }
        }
        return frontTiles_;
    }

    public static void translateShortLineData(ObjectMap<Point, Short> _data,
            short _y, short _dir) {
        CustList<Point> links_ = _data.getKeys();
        ObjectMap<Point, Point> deplLinks_ = new ObjectMap<Point, Point>();
        for (Point c : links_) {
            if (c.gety() < _y) {
                deplLinks_.put(c, c);
                continue;
            }
            deplLinks_.put(c, new Point(c.getx(), (short) (c.gety() + _dir)));
        }
        while (!links_.isEmpty()) {
            for (Point c : links_) {
                Point dest_ = deplLinks_.getVal(c);
                if (!containsPt(links_,dest_)) {
                    Short movedBlock_ = _data.getVal(c);
                    _data.removeKey(c);
                    _data.put(dest_, movedBlock_);
                    deplLinks_.removeKey(c);
                }
            }
            for (Point c : links_) {
                Point dest_ = deplLinks_.getVal(c);
                if (dest_ == null) {
                    continue;
                }
                if (Point.eq(c, dest_)) {
                    deplLinks_.removeKey(c);
                }
            }
            links_ = deplLinks_.getKeys();
        }
    }

    public static void translateShortColumnData(ObjectMap<Point, Short> _data,
            short _x, short _dir) {
        CustList<Point> links_ = _data.getKeys();
        ObjectMap<Point, Point> deplLinks_ = new ObjectMap<Point, Point>();
        for (Point c : links_) {
            if (c.getx() < _x) {
                deplLinks_.put(c, c);
                continue;
            }
            deplLinks_.put(c, new Point((short) (c.getx() + _dir), c.gety()));
        }
        while (!links_.isEmpty()) {
            for (Point c : links_) {
                Point dest_ = deplLinks_.getVal(c);
                if (!containsPt(links_,dest_)) {
                    Short movedBlock_ = _data.getVal(c);
                    _data.removeKey(c);
                    _data.put(dest_, movedBlock_);
                    deplLinks_.removeKey(c);
                }
            }
            for (Point c : links_) {
                Point dest_ = deplLinks_.getVal(c);
                if (dest_ == null) {
                    continue;
                }
                if (Point.eq(c, dest_)) {
                    deplLinks_.removeKey(c);
                }
            }
            links_ = deplLinks_.getKeys();
        }
    }

    public abstract boolean isEmptyForAdding(Point _point);

    public abstract boolean isEmpty(Point _point);

    public ScreenCoords getScreenCoordsByPoint(Point _point) {
        ScreenCoords out_ = new ScreenCoords();
        for (Point i : blocks.getKeys()) {
            Block block_ = blocks.getVal(i);
            short w_ = block_.getWidth();
            short h_ = block_.getHeight();
            short xi_ = i.getx();
            short yi_ = i.gety();
            int xr_ = xi_ + w_;
            int yb_ = yi_ + h_;
            short xp_ = _point.getx();
            short yp_ = _point.gety();
            if (inRange(xi_, xr_, xp_)) {
                if (inRange(yi_, yb_, yp_)) {
                    out_.setXcoords(_point.getx() - i.getx());
                    out_.setYcoords(_point.gety() - i.gety());
                    return out_;
                }
            }
        }
        out_.setXcoords(-1);
        out_.setYcoords(-1);
        return out_;
    }

    public Block getEnvBlockByPoint(Point _point) {
        Block bl_ = getBlockByPoint(_point);
        if (bl_.getType() == EnvironmentType.NOTHING) {
            return new Block();
        }
        return bl_;
    }

    public Block getBlockByPoint(Point _point) {
        for (Point i : blocks.getKeys()) {
            Block block_ = blocks.getVal(i);
            short w_ = block_.getWidth();
            short h_ = block_.getHeight();
            short xi_ = i.getx();
            short yi_ = i.gety();
            int xr_ = xi_ + w_;
            int yb_ = yi_ + h_;
            short xp_ = _point.getx();
            short yp_ = _point.gety();
            if (inRange(xi_, xr_, xp_)) {
                if (inRange(yi_, yb_, yp_)) {
                    return block_;
                }
            }
        }
        return new Block();
    }

    public static boolean inRange(short _min, int _max, short _xp) {
        return _xp >= _min && _xp < _max;
    }
    public Limits limits() {
        Point leftTopPoint_ = new Point((short) 0, (short) 0);
        Point rightBottomPoint_ = new Point((short) 0, (short) 0);
        CustList<Point> cles_ = blocks.getKeys();
        for (Point p : cles_) {
            leftTopPoint_.affect(p);
            rightBottomPoint_.affect(p);
            break;
        }
        for (Point p : cles_) {
            if (p.getx() < leftTopPoint_.getx()) {
                leftTopPoint_.setx(p.getx());
            }
            if (p.gety() < leftTopPoint_.gety()) {
                leftTopPoint_.sety(p.gety());
            }
            Block block_ = blocks.getVal(p);
            short tmp_ = (short) (p.getx() + block_.getWidth() - 1);
            if (tmp_ > rightBottomPoint_.getx()) {
                rightBottomPoint_.setx(tmp_);
            }
            tmp_ = (short) (p.gety() + block_.getHeight() - 1);
            if (tmp_ > rightBottomPoint_.gety()) {
                rightBottomPoint_.sety(tmp_);
            }
        }
        return new Limits(leftTopPoint_, rightBottomPoint_);
    }

    public ObjectMap<Point, Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ObjectMap<Point, Block> _blocks) {
        blocks = _blocks;
    }

    private static boolean containsPt(CustList<Point> _l, Point _pt) {
        for (Point p: _l) {
            if (_pt.eq(p)) {
                return true;
            }
        }
        return false;
    }
}
