package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.util.*;

public final class FormWildPk {
    private final WindowPkEditor window;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslationList;
    private GeneComponentModelEltEnumSub<String> name;
    private GeneComponentModelEltEnumSub<String> ability;
    private GeneComponentModelEltEnumSub<String> item;
    private GeneComponentModelEltEnumSub<Gender> gender;
    private final GeneComponentModelInt level;
    private WildPk wildPk;
    private AbsPanel form;
    public FormWildPk(WindowPkEditor _ed, FacadeGame _facade, SubscribedTranslationList _sub) {
        window = _ed;
        facadeGame = _facade;
        subscribedTranslationList = _sub;
        level = new GeneComponentModelInt(window.getFrames());
    }

    public void feedForm(WildPk _wp) {
        wildPk = ConverterCommonMapUtil.copyWildPk(_wp);
        form = window.getFrames().getCompoFactory().newPageBox();
        form.add(level.geneInt());
        level.valueInt(wildPk.getLevel());
        name = ConverterCommonMapUtil.buildPkFull(window.getFrames(), facadeGame, subscribedTranslationList);
        form.add(name.geneEnum());
        name.setupValue(wildPk.getName());
        ability = ConverterCommonMapUtil.buildAbFull(window.getFrames(), facadeGame, subscribedTranslationList, new IdMap<String, String>());
        form.add(ability.geneEnum());
        ability.setupValue(wildPk.getName());
        item = ConverterCommonMapUtil.buildItFull(window.getFrames(), facadeGame, subscribedTranslationList, ConverterCommonMapUtil.defKeyEmpty(" "));
        form.add(item.geneEnum());
        item.setupValue(wildPk.getName());
        gender = ConverterCommonMapUtil.buildGender(window.getFrames(), facadeGame, subscribedTranslationList);
        form.add(gender.geneEnum());
        gender.setupValue(wildPk.getGender());
    }

    public void feedSubs(IdList<SubscribedTranslation> _subs) {
        _subs.addAllElts(name.getSubs());
        _subs.addAllElts(ability.getSubs());
        _subs.addAllElts(item.getSubs());
        _subs.addAllElts(gender.getSubs());
    }

    public WildPk buildEntity() {
        wildPk.setLevel((short) level.valueInt());
        wildPk.setName(name.tryRet());
        wildPk.setAbility(ability.tryRet());
        wildPk.setItem(item.tryRet());
        wildPk.setGender(gender.tryRet());
        return getWildPk();
    }

    public WildPk getWildPk() {
        return wildPk;
    }

    public AbsPanel getForm() {
        return form;
    }

    public GeneComponentModelInt getLevel() {
        return level;
    }

    public GeneComponentModelEltEnumSub<String> getName() {
        return name;
    }

    public GeneComponentModelEltEnumSub<String> getAbility() {
        return ability;
    }

    public GeneComponentModelEltEnumSub<Gender> getGender() {
        return gender;
    }

    public GeneComponentModelEltEnumSub<String> getItem() {
        return item;
    }
}
