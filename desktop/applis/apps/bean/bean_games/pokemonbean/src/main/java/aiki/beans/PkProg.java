package aiki.beans;

import aiki.beans.game.AikiBeansGameStd;
import aiki.beans.game.GameProgressionBean;
import aiki.beans.game.TrainerPlaceNamesGetPlace;
import aiki.beans.game.TrainerPlaceNamesGetTrainer;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.bean.nat.analyze.NatConfigurationCore;
import code.util.CustList;

public final class PkProg extends PokemonStandards {
    private static final String GET_TRAINER = "getTrainer";
    private static final String GET_PLACE = "getPlace";

    @Override
    public void buildAddon() {
        AikiBeansGameStd.buildGameProgressionBean(this);
        buildTrainerPlaceNames(this);
    }

    private static void buildTrainerPlaceNames(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_TRAINER,BeanNatCommonLgNames.STRING, new TrainerPlaceNamesGetTrainer()));
        methods_.add( new SpecNatMethod(GET_PLACE,BeanNatCommonLgNames.STRING, new TrainerPlaceNamesGetPlace()));
        _std.getStds().addEntry(TYPE_TRAINER_PLACE_NAMES, type_);
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getBeansStruct().setValue(0, initProg(_language));
    }

    public PokemonBeanStruct initProg(String _language) {
        return bean(new GameProgressionBean(), _language);
    }
}
