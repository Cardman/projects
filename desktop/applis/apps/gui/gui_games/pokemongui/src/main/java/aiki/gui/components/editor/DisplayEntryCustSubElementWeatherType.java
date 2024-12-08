package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementWeatherType<T> implements DisplayEntryCustSubElement<EditedCrudPair<WeatherType, T>> {
    private final AbsMap<String, String> moves;
    private final AbsMap<String, String> types;
    private final SubscribedTranslationMessagesFactory factoryTy;
    private final SubscribedTranslationMessagesFactory factoryMv;

    public DisplayEntryCustSubElementWeatherType(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryMv = _sub.getFactoryMv();
        this.moves = factoryMv.getContainer().buildMessages(_fact,_facade);
        factoryTy = _sub.getFactoryTy();
        this.types = factoryTy.getContainer().buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<String>(moves, factoryMv, new StringMap<String>()));
        ids_.add(new SubscribedTranslationMessages<String>(types,factoryTy, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<WeatherType, T>> buildDisplay() {
        return new DisplayEntryCustWeatherType<T>(moves,types);
    }

    @Override
    public Comparing<EditedCrudPair<WeatherType, T>> buildCmp() {
        return new ComparingKeyWeatherType<T>(moves,types);
    }
}
