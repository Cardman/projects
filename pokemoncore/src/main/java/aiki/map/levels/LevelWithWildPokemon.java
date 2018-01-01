package aiki.map.levels;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.util.Point;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.annot.RwXml;

@RwXml
public class LevelWithWildPokemon extends Level {

    private CustList<AreaApparition> wildPokemonAreas;

    private ObjectMap<Point,CharacterInRoadCave> characters;

    private ObjectMap<Point,DualFight> dualFights;

    private ObjectMap<Point,WildPk> legendaryPks;

    private ObjectMap<Point,String> items;

    private ObjectMap<Point,Short> tm;

    private ObjectMap<Point,Short> hm;

    @Override
    public void validate(DataBase _data,LevelArea _level) {
        super.validate(_data, _level);
        int index_ = 0;
        for (AreaApparition a: wildPokemonAreas) {
            a.validate(_data);
            boolean existBlock_ = false;
            for (Block b: getBlocks().values()) {
                if (b.getIndexApparition() == index_) {
                    existBlock_ = true;
                    break;
                }
            }
            if (!existBlock_) {
                throw new DataException();
            }
            index_++;
        }
        for (EntryCust<Point,Block> e: getBlocks().entryList()) {
            AreaApparition a_ = getAreaByBlockId(e.getKey());
            if (a_.isVirtual()) {
                continue;
            }
            if (!a_.getWildPokemonFishing().isEmpty()) {
                if (e.getValue().getType() != EnvironmentType.WATER) {
                    throw new DataException();
                }
            }
        }
        EqList<Point> keys_ = new EqList<Point>();
        for (EntryCust<Point,CharacterInRoadCave> e: characters.entryList()) {
            if (!(e.getValue() instanceof Person)) {
                continue;
            }
            if (!_level.isValid(e.getKey(),true)) {
                throw new DataException();
            }
            e.getValue().validate(_data);
            keys_.add(e.getKey());
        }
        for (EntryCust<Point,DualFight> e: dualFights.entryList()) {
            Point id_ = e.getKey();
            if (!_level.isValid(id_,true)) {
                throw new DataException();
            }
            DualFight dual_ = e.getValue();
            if (!_level.isValid(dual_.getPt(),true)) {
                throw new DataException();
            }
            boolean isNext_ = false;
            for (Direction d: Direction.values()) {
                Point next_ = new Point(id_);
                next_.moveTo(d);
                if (Point.eq(next_, dual_.getPt())) {
                    isNext_ = true;
                    break;
                }
            }
            if (!isNext_) {
                throw new DataException();
            }
            dual_.validate(_data);
            keys_.add(e.getKey());
            keys_.add(dual_.getPt());
        }
        for (EntryCust<Point,WildPk> e: legendaryPks.entryList()) {
            if (!_level.isValid(e.getKey(),true)) {
                throw new DataException();
            }
            e.getValue().validate(_data, true);
//            if (!e.getValue().isValid(_data)) {
//                throw new DataException();
//            }
            PokemonData fPk_ = _data.getPokemon(e.getValue().getName());
            if (fPk_.getGenderRep() != GenderRepartition.LEGENDARY) {
                throw new DataException();
            }
            keys_.add(e.getKey());
        }
        for (EntryCust<Point,String> e: items.entryList()) {
            if (!_level.isValid(e.getKey(),true)) {
                throw new DataException();
            }
            if (!_data.getItems().contains(e.getValue())) {
                throw new DataException();
            }
            keys_.add(e.getKey());
        }
        for (EntryCust<Point,Short> e: tm.entryList()) {
            if (!_level.isValid(e.getKey(),true)) {
                throw new DataException();
            }
            if (!_data.getTm().contains(e.getValue())) {
                throw new DataException();
            }
            keys_.add(e.getKey());
        }
        for (EntryCust<Point,Short> e: hm.entryList()) {
            if (!_level.isValid(e.getKey(),true)) {
                throw new DataException();
            }
            if (!_data.getHm().contains(e.getValue())) {
                throw new DataException();
            }
            keys_.add(e.getKey());
        }
        int size_ = keys_.size();
        keys_.removeDuplicates();
        if (size_ != keys_.size()) {
            throw new DataException();
        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        EqList<Point> keys_ = new EqList<Point>();
        for (EntryCust<Point,String> e: items.entryList()) {
            if (!_data.getItems().contains(e.getValue())) {
                keys_.add(e.getKey());
            }
        }
        for (Point p: keys_) {
            items.removeKey(p);
        }
        keys_.clear();
        for (EntryCust<Point,Short> e: tm.entryList()) {
            if (!_data.getTm().contains(e.getValue())) {
                keys_.add(e.getKey());
            }
        }
        for (Point p: keys_) {
            tm.removeKey(p);
        }
        keys_.clear();
        for (EntryCust<Point,Short> e: hm.entryList()) {
            if (!_data.getTm().contains(e.getValue())) {
                keys_.add(e.getKey());
            }
        }
        for (Point p: keys_) {
            hm.removeKey(p);
        }
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        if (characters.contains(_point)) {
            return false;
        }
        if (legendaryPks.contains(_point)) {
            return false;
        }
        if (items.contains(_point)) {
            return false;
        }
        if (tm.contains(_point)) {
            return false;
        }
        if (hm.contains(_point)) {
            return false;
        }
        if (dualFights.contains(_point)) {
            return false;
        }
        for (DualFight d: dualFights.values()) {
            if (Point.eq(d.getPt(),_point)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        for (EntryCust<Point,DualFight> e: dualFights.entryList()) {
            if (!e.getValue().getFoeTrainer().hasValidImage(_data)) {
                return false;
            }
        }
        for (EntryCust<Point,CharacterInRoadCave> e: characters.entryList()) {
            if (!e.getValue().hasValidImage(_data)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty(Point _point) {
        return !characters.contains(_point);
    }

    public boolean containsPokemon(Point _point) {
        return legendaryPks.contains(_point);
    }

    public WildPk getPokemon(Point _point) {
        return legendaryPks.getVal(_point);
    }

    public boolean containsObject(Point _point) {
        return items.contains(_point);
    }

    public String getObject(Point _point) {
        return items.getVal(_point);
    }

    public void setItem(Point _point, String _object) {
        items.put(_point, _object);
    }

    public boolean containsTm(Point _point) {
        return tm.contains(_point);
    }

    public short getTm(Point _point) {
        return tm.getVal(_point);
    }

    public void setTm(Point _pt, short _tm) {
        tm.put(_pt, _tm);
    }

    public boolean containsHm(Point _point) {
        return hm.contains(_point);
    }

    public short getHm(Point _point) {
        return hm.getVal(_point);
    }

    public void setHm(Point _pt, short _hm) {
        hm.put(_pt, _hm);
    }

    public boolean containsPerson(Point _point) {
        return characters.contains(_point);
    }

    public Person getPerson(Point _point) {
        return (Person) characters.getVal(_point);
    }

    public boolean containsDualFight(Point _point) {
        return dualFights.contains(_point);
    }

    public DualFight getDualFight(Point _point) {
        return dualFights.getVal(_point);
    }

    @Override
    public void clearElements(Point _point) {
        characters.removeKey(_point);
        legendaryPks.removeKey(_point);
        items.removeKey(_point);
        tm.removeKey(_point);
        hm.removeKey(_point);
        dualFights.removeKey(_point);
        for (EntryCust<Point,DualFight> e: dualFights.entryList()) {
            if (Point.eq(e.getValue().getPt(),_point)) {
                dualFights.removeKey(e.getKey());
                return;
            }
        }
    }

    @Override
    public void translateByLine(short _y,short _dir) {
        super.translateByLine(_y, _dir);
        Level.translateCharacterInRoadCaveLineData(characters, _y, _dir);
        Level.translateWildPkLineData(legendaryPks, _y, _dir);
        Level.translateStringLineData(items, _y, _dir);
        Level.translateShortLineData(tm, _y, _dir);
        Level.translateShortLineData(hm, _y, _dir);
        Level.translateDualFightLineData(dualFights, _y, _dir);
        for (DualFight k: dualFights.values()) {
            k.getPt().sety((short) (k.getPt().gety()+_dir));
        }
    }

    @Override
    public void translateByColumn(short _x,short _dir) {
        super.translateByColumn(_x, _dir);
        Level.translateCharacterInRoadCaveColumnData(characters, _x, _dir);
        Level.translateWildPkColumnData(legendaryPks, _x, _dir);
        Level.translateStringColumnData(items, _x, _dir);
        Level.translateShortColumnData(tm, _x, _dir);
        Level.translateShortColumnData(hm, _x, _dir);
        Level.translateDualFightColumnData(dualFights, _x, _dir);
        for (DualFight k: dualFights.values()) {
            k.getPt().setx((short) (k.getPt().getx()+_dir));
        }
    }

    @Override
    public void translateElement(Point _id, Point _target) {
        if (!isEmptyForAdding(_target)) {
            return;
        }
        if (characters.contains(_id)) {
            characters.move(_id, _target);
        }
        if (legendaryPks.contains(_id)) {
            legendaryPks.move(_id, _target);
        }
        if (items.contains(_id)) {
            items.move(_id, _target);
        }
        if (tm.contains(_id)) {
            tm.move(_id, _target);
        }
        if (hm.contains(_id)) {
            hm.move(_id, _target);
        }
        if (dualFights.contains(_id)) {
            dualFights.move(_id, _target);
        }
    }
    public void initializeWildPokemon() {
        for (AreaApparition a: wildPokemonAreas) {
            a.initializeWildPokemon();
        }
    }
    public void linkBlockAreaApparition(Point _id, int _indexOfApparition) {
        getBlocks().getVal(_id).setIndexApparition((short) _indexOfApparition);
    }
    public void unlinkBlockAreaApparition(Point _id) {
        getBlocks().getVal(_id).setIndexApparition(CustList.INDEX_NOT_FOUND_ELT);
    }
    public AreaApparition getAreaByBlockId(Point _key) {
        int index_ = getBlocks().getVal(_key).getIndexApparition();
        if (index_ < 0) {
            return new AreaApparition();
        }
        return wildPokemonAreas.get(index_);
    }
    public AreaApparition getAreaByPoint(Point _point) {
        int index_ = getBlockByPoint(_point).getIndexApparition();
        if (index_ < 0) {
            return new AreaApparition();
        }
        return wildPokemonAreas.get(index_);
    }
    public CustList<AreaApparition> getWildPokemonAreas() {
        return wildPokemonAreas;
    }

    public void setWildPokemonAreas(CustList<AreaApparition> _wildPokemonAreas) {
        wildPokemonAreas = _wildPokemonAreas;
    }

    public ObjectMap<Point,CharacterInRoadCave> getCharacters() {
        return characters;
    }

    public void setCharacters(ObjectMap<Point,CharacterInRoadCave> _characters) {
        characters = _characters;
    }

    public ObjectMap<Point,DualFight> getDualFights() {
        return dualFights;
    }

    public void setDualFights(ObjectMap<Point,DualFight> _dualFights) {
        dualFights = _dualFights;
    }

    public ObjectMap<Point,WildPk> getLegendaryPks() {
        return legendaryPks;
    }

    public void setLegendaryPks(ObjectMap<Point,WildPk> _legendaryPks) {
        legendaryPks = _legendaryPks;
    }

    public ObjectMap<Point,String> getItems() {
        return items;
    }

    public void setItems(ObjectMap<Point,String> _items) {
        items = _items;
    }

    public ObjectMap<Point,Short> getTm() {
        return tm;
    }

    public void setTm(ObjectMap<Point,Short> _tm) {
        tm = _tm;
    }

    public ObjectMap<Point,Short> getHm() {
        return hm;
    }

    public void setHm(ObjectMap<Point,Short> _hm) {
        hm = _hm;
    }

//    @Override
//    public void beforeSave() {
////        for (EntryCust<Point, Pokemon> p: legendaryPks.entryList()) {
////            p.setValue(new WildPk(p.getValue()));
////        }
//    }
//
//    @Override
//    public void afterLoad() {
//    }
}
