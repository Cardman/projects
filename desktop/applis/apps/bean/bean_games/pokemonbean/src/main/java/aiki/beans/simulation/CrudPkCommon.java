package aiki.beans.simulation;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;

public final class CrudPkCommon {
    private String gender = Gender.NO_GENDER.getGenderName();
    private int level;
    private DictionaryComparator<String,String> genders;

    public void init(DataBase _data,String _lg) {
        genders = DictionaryComparatorUtil.buildGenderStr(_data,_lg);
    }
    public void patchLevel(DataBase _data) {
        if (level < _data.getMinLevel()) {
            level = (short) _data.getMinLevel();
        }
        if (level > _data.getMaxLevel()) {
            level = (short) _data.getMaxLevel();
        }
    }
    public DictionaryComparator<String,String> getGenders() {
        return genders;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String _gender) {
        gender = _gender;
    }

    public void setLevel(int _level) {
        level = _level;
    }

    public int getLevel() {
        return level;
    }
}