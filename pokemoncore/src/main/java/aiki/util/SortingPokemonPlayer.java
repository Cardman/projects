package aiki.util;
import aiki.map.pokemon.enums.Gender;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Cmp;
import code.util.pagination.Sorting;

public final class SortingPokemonPlayer implements Sorting,Cmp<SortingPokemonPlayer> {

    private int index;

    private String name;

    private String keyName;

    private short level;

    private String ability;

    private String keyAbility;

    private String item;

    private String keyItem;

    private Gender gender;

    private short nbPossEvos;

    @Override
    public boolean eq(SortingPokemonPlayer _g) {
        return cmp(_g) == CustList.EQ_CMP;
    }

    @Override
    public int cmp(SortingPokemonPlayer _o2) {
        return Numbers.compare(getIndex(), _o2.getIndex());
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String _keyName) {
        keyName = _keyName;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String _ability) {
        ability = _ability;
    }

    public String getKeyAbility() {
        return keyAbility;
    }

    public void setKeyAbility(String _keyAbility) {
        keyAbility = _keyAbility;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }

    public String getKeyItem() {
        return keyItem;
    }

    public void setKeyItem(String _keyItem) {
        keyItem = _keyItem;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender _gender) {
        gender = _gender;
    }

    public short getNbPossEvos() {
        return nbPossEvos;
    }

    public void setNbPossEvos(short _nbPossEvos) {
        nbPossEvos = _nbPossEvos;
    }
}
