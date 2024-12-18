package aiki.map.tree;

import aiki.fight.pokemon.GenderName;
import aiki.map.levels.*;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.util.Dims;
import aiki.map.util.Limits;
import aiki.util.*;
import code.util.*;
import code.util.core.IndexConstants;

public class LevelArea {

    private Point leftTop;
    private PointEqList inacessiblePoints;
    private Points< Dims> dimsBlocks;
    private Points< Short> indexes;
    private CustList<CustList<GenderName>> pokemon;
    private int height;
    private int width;

    public void initialize(Level _level) {
        Limits limits_ = _level.limits();
        leftTop = limits_.getTopLeft();
        height = limits_.getBottomRight().gety() - leftTop.gety() + 1;
        width = limits_.getBottomRight().getx() - leftTop.getx() + 1;
        inacessiblePoints = new PointEqList();
        indexes = new PointsShort();
        dimsBlocks = new PointsDims();
        for (EntryCust<Point,Block> e : _level.getBlocks().entryList()) {
            Block block_ = e.getValue();
            Point id_ = e.getKey();
            if (block_.getType() != EnvironmentType.NOTHING) {
                if (block_.getIndexApparition() != IndexConstants.INDEX_NOT_FOUND_ELT) {
                    indexes.put(id_, block_.getIndexApparition());
                }
                dimsBlocks.put(id_,
                        new Dims(block_.getWidth(), block_.getHeight()));
                continue;
            }
            short x_ = id_.getx();
            short y_ = id_.gety();
            int w_ = block_.getWidth();
            int h_ = block_.getHeight();
            int xMax_ = w_ + x_;
            int yMax_ = h_ + y_;
            for (short x = x_; x < xMax_; x++) {
                for (short y = y_; y < yMax_; y++) {
                    inacessiblePoints.add(new Point(x, y));
                }
            }
        }
        listPk(_level);
    }

    private void listPk(Level _level) {
        pokemon = new CustList<CustList<GenderName>>();
        if (_level instanceof LevelWithWildPokemon) {
            for (AbsAreaApparition a : ((LevelWithWildPokemon) _level)
                    .getWildPokemonAreas()) {
                CustList<GenderName> list_ = new CustList<GenderName>();
                for (WildPk p : a.getWildPokemon()) {
                    list_.add(new GenderName(p.getGender(), p.getName()));
                }
                for (WildPk p : a.getWildPokemonFishing()) {
                    list_.add(new GenderName(p.getGender(), p.getName()));
                }
                pokemon.add(list_);
            }
        }
    }

    public boolean isValid(Point _pt, boolean _accessible) {
        if (_accessible && inacessiblePoints.containsObj(_pt)) {
            return false;
        }
        if (_pt.getx() < leftTop.getx()) {
            return false;
        }
        if (_pt.gety() < leftTop.gety()) {
            return false;
        }
        if (_pt.getx() >= leftTop.getx() + width) {
            return false;
        }
        return _pt.gety() < leftTop.gety() + height;
    }

    public boolean isAccessible(Point _pt) {
        return !inacessiblePoints.containsObj(_pt);
    }

    public boolean allAccessible() {
        return inacessiblePoints.isEmpty();
    }

    public long size() {
        return (long)height * width;
    }

    public CustList<GenderName> getPokemon(Point _pt) {
        int index_ = getIndex(_pt);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return new CustList<GenderName>();
        }
        return pokemon.get(index_);
    }

    public int getIndex(Point _pt) {
        for (Point k : dimsBlocks.getKeys()) {
            if (_pt.getx() >= k.getx() && _pt.gety() >= k.gety() && _pt.getx() < k.getx() + dimsBlocks.getVal(k).getWidth() && _pt.gety() < k.gety() + dimsBlocks.getVal(k).getHeight() && indexes.contains(k)) {
                return indexes.getVal(k);
            }
            // _pt.getx() >= k.getx()
            // _pt.gety() >= k.gety()
            // _pt.getx() < k.getx() + width
            // _pt.gety() < k.gety() + height
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public PointEqList getInacessiblePoints() {
        return inacessiblePoints;
    }

    public Points< Dims> getDimsBlocks() {
        return dimsBlocks;
    }

    public Points< Short> getIndexes() {
        return indexes;
    }

    public CustList<CustList<GenderName>> getPokemon() {
        return pokemon;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
