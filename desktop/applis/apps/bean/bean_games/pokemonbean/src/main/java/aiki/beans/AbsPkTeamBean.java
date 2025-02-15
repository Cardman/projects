package aiki.beans;

import aiki.beans.map.elements.*;
import aiki.map.pokemon.*;
import code.util.*;

public abstract class AbsPkTeamBean extends CommonBean {
    private CustList<TranslatedPkElements> team;

    protected void initTeam(CustList<PkTrainer> _l) {
        team = retrieveTeam(_l);
    }

    protected CustList<TranslatedPkElements> retrieveTeam(CustList<PkTrainer> _l) {
        CustList<TranslatedPkElements> team_;
        team_ = new CustList<TranslatedPkElements>();
        for (PkTrainer p: _l) {
            team_.add(new TranslatedPkElements(getFacade(),p));
        }
        return team_;
    }

    public int[][] getImage(CustList<TranslatedPkElements> _list,int _index) {
        return _list.get(_index).getImage();
//        DataBase data_ = getDataBase();
//        PkTrainer pk_;
//        pk_ = _list.get(_index);
//        String name_ = pk_.getName();
//        return data_.getMaxiPkFront().getVal(name_).getImage();
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName(int _index) {
        return team.get(_index).getName().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsPokemon_;
//        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        String name_ = pk_.getName();
//        return translationsPokemon_.getVal(name_);
    }
    protected String clickName(CustList<TranslatedPkElements> _list,int _index) {
        return tryRedirect(_list.get(_index).getName());
    }

    public String getGender(int _index) {
        return team.get(_index).getGender().getTranslation();
//        DataBase data_ = getDataBase();
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        return data_.getTranslatedGenders().getVal(getLanguage()).getVal(pk_.getGender());
    }

    public String getAbility(int _index) {
        return team.get(_index).getAbility().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsAbilities_;
//        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        String ability_ = pk_.getAbility();
//        return translationsAbilities_.getVal(ability_);
    }
    protected String clickAbility(CustList<TranslatedPkElements> _list,int _index) {
        return tryRedirect(_list.get(_index).getAbility());
    }
    public String getItem(int _index) {
        return team.get(_index).getItem().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        String item_ = pk_.getItem();
//        return translationsItems_.getVal(item_);
    }
    public String clickItem(CustList<TranslatedPkElements> _list,int _index) {
        return tryRedirect(_list.get(_index).getItem());
    }
    public String getMove(int _index, int _moveIndex) {
        return team.get(_index).getMoves().get(_moveIndex).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        PkTrainer pk_;
//        pk_ = team.get(_index);
//        String move_ = pk_.getMoves().get(_moveIndex);
//        return translationsMoves_.getVal(move_);
    }
    public String clickMove(CustList<TranslatedPkElements> _list,int _index, int _moveIndex) {
        return tryRedirect(_list.get(_index).getMoves().get(_moveIndex));
    }
    public CustList<TranslatedPkElements> getTeam() {
        return team;
    }
}
