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
    private AbsPanel form;
    public FormWildPk(WindowPkEditor _ed, FacadeGame _facade, SubscribedTranslationList _sub) {
        window = _ed;
        facadeGame = _facade;
        subscribedTranslationList = _sub;
        level = new GeneComponentModelInt(window.getFrames());
    }

    public void feedForm(WildPk _wp) {
        form = window.getFrames().getCompoFactory().newPageBox();
        form.add(level.geneInt());
        level.valueInt(_wp.getLevel());
        name = ConverterCommonMapUtil.buildPkFull(window.getFrames(), facadeGame, subscribedTranslationList);
        form.add(name.geneEnum());
        name.setupValue(_wp.getName());
        ability = ConverterCommonMapUtil.buildAbFull(window.getFrames(), facadeGame, subscribedTranslationList, new IdMap<String, String>());
        form.add(ability.geneEnum());
        ability.setupValue(_wp.getName());
        item = ConverterCommonMapUtil.buildItFull(window.getFrames(), facadeGame, subscribedTranslationList, new IdMap<String, String>());
        form.add(item.geneEnum());
        item.setupValue(_wp.getName());
        gender = ConverterCommonMapUtil.buildGender(window.getFrames(), facadeGame, subscribedTranslationList);
        form.add(gender.geneEnum());
        gender.setupValue(_wp.getGender());
    }

    public void feedSubs(IdList<SubscribedTranslation> _subs) {
        _subs.addAllElts(name.getSubs());
        _subs.addAllElts(ability.getSubs());
        _subs.addAllElts(item.getSubs());
        _subs.addAllElts(gender.getSubs());
    }

    public void buildEntity(WildPk _wp) {
        _wp.setLevel((short) level.valueInt());
        _wp.setName(name.tryRet());
        _wp.setAbility(ability.tryRet());
        _wp.setItem(item.tryRet());
        _wp.setGender(gender.tryRet());
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
