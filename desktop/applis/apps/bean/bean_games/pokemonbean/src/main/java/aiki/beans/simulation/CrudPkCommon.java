package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.game.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;
import code.scripts.pages.aiki.MessagesDataSimulation;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.EntryCust;

public final class CrudPkCommon {
    private IntBeanChgString gender;
    private IntBeanChgLong level;
    private DictionaryComparator<String,String> genders;

    public CrudPkCommon() {
        gender = new BeanChgString();
        gender.setupValue(Gender.NO_GENDER.getGenderName());
        level = new BeanChgLong();
    }
    public void initForm(CommonBean _bean) {
        _bean.initLine();
        _bean.formatMessage(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_GENDER_PK);
        setGender(DifficultyBeanForm.select(_bean.getBuilder().getGenInput(), _bean,genders,getGender().tryRet()));
        _bean.feedParents();
        _bean.initLine();
        _bean.formatMessage(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_LEVEL_PK);
        setLevel(DifficultyBeanForm.iv(_bean.getBuilder().getGenInput(), _bean, getLevel().valueLong()));
        _bean.feedParents();
    }
    public void init(DataBase _data,String _lg) {
        genders = DictionaryComparatorUtil.buildGenderStr(_data,_lg);
    }

    public void updateGender() {
        getGender().setupValue(Gender.NO_GENDER.getGenderName());
        for (EntryCust<String,String> e: genders.entryList()) {
            getGender().setupValue(e.getKey());
        }
    }

    public void patchLevel(DataBase _data) {
        if (level.valueLong() < _data.getMinLevel()) {
            level.valueLong(_data.getMinLevel());
        }
        if (level.valueLong() > _data.getMaxLevel()) {
            level.valueLong(_data.getMaxLevel());
        }
    }
    public DictionaryComparator<String,String> getGenders() {
        return genders;
    }

    public IntBeanChgString getGender() {
        return gender;
    }

    public void setGender(IntBeanChgString _gender) {
        gender = _gender;
    }

    public void setLevel(IntBeanChgLong _level) {
        level = _level;
    }

    public IntBeanChgLong getLevel() {
        return level;
    }
}
