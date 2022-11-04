package aiki.beans;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.map.pokemon.PkTrainer;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbsPkTeamBean extends CommonBean {
    private CustList<PkTrainer> team;

    protected void initTeam(CustList<PkTrainer> _l) {
        DataBase data_ = getDataBase();
        CustList<PkTrainer> team_;
        team_ = new CustList<PkTrainer>();
        for (PkTrainer p: _l) {
            PkTrainer pk_;
            pk_ = new PkTrainer();
            pk_.setName(p.getName());
            pk_.setGender(p.getGender());
            pk_.setAbility(p.getAbility());
            pk_.setItem(p.getItem());
            pk_.setLevel(p.getLevel());
            pk_.setMoves(new StringList());
            pk_.getMoves().addAllElts(p.getMoves());
            pk_.getMoves().sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
            team_.add(pk_);
        }
        team = team_;
    }

    public String getImage(CustList<PkTrainer> _list,int _index) {
        DataBase data_ = getDataBase();
        PkTrainer pk_;
        pk_ = _list.get(_index);
        String name_ = pk_.getName();
        return BaseSixtyFourUtil.getStringByImage(data_.getMaxiPkFront().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index);
        String name_ = pk_.getName();
        return translationsPokemon_.getVal(name_);
    }
    protected String clickName(CustList<PkTrainer> _list,int _index) {
        PkTrainer pk_;
        pk_ = _list.get(_index);
        String name_ = pk_.getName();
        getForms().put(CST_PK, name_);
        return CST_POKEMON;
    }
    public String getAbility(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index);
        String ability_ = pk_.getAbility();
        return translationsAbilities_.getVal(ability_);
    }
    protected String clickAbility(CustList<PkTrainer> _list,int _index) {
        PkTrainer pk_;
        pk_ = _list.get(_index);
        String ability_ = pk_.getAbility();
        getForms().put(CST_ABILITY, ability_);
        return CST_ABILITY;
    }
    public String getItem(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index);
        String item_ = pk_.getItem();
        return translationsItems_.getVal(item_);
    }
    public String clickItem(CustList<PkTrainer> _list,int _index) {
        PkTrainer pk_;
        pk_ = _list.get(_index);
        String item_ = pk_.getItem();
        return tryRedirectIt(item_);
    }
    public String getMove(int _index, int _moveIndex) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        PkTrainer pk_;
        pk_ = team.get(_index);
        String move_ = pk_.getMoves().get(_moveIndex);
        return translationsMoves_.getVal(move_);
    }
    public String clickMove(CustList<PkTrainer> _list,int _index, int _moveIndex) {
        DataBase data_ = getDataBase();
        PkTrainer pk_;
        pk_ = _list.get(_index);
        StringList moves_ = new StringList(pk_.getMoves());
        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        String move_ = moves_.get(_moveIndex);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public CustList<PkTrainer> getTeam() {
        return team;
    }
}
