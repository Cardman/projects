package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class FormAbsPk {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslationList;
    private GeneComponentModelEltEnumSub<String> name;
    private GeneComponentModelEltEnumSub<String> ability;
    private GeneComponentModelEltEnumSub<String> item;
    private GeneComponentModelEltEnumSub<Gender> gender;
    private final GeneComponentModelInt level;
    private AbsPanel form;
    private final AbsCommonFrame frame;
    protected FormAbsPk(AbstractProgramInfos _ed, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _f) {
        api = _ed;
        facadeGame = _facade;
        subscribedTranslationList = _sub;
        frame = _f;
        level = new GeneComponentModelInt(api);
    }

    public void feedFormQuick() {
        form = api.getCompoFactory().newPageBox();
        form.add(level.geneInt());
        name = ConverterCommonMapUtil.buildPkFull(api, facadeGame, subscribedTranslationList);
        form.add(name.geneEnum());
        ability = ConverterCommonMapUtil.buildAbFull(api, facadeGame, subscribedTranslationList, new IdMap<String, String>());
        form.add(ability.geneEnum());
        item = ConverterCommonMapUtil.buildItFull(api, facadeGame, subscribedTranslationList, ConverterCommonMapUtil.defKeyEmpty(" "));
        form.add(item.geneEnum());
        gender = ConverterCommonMapUtil.buildGender(api, facadeGame, subscribedTranslationList);
        form.add(gender.geneEnum());
    }

    public void feedFormQuick(Pokemon _wildPk) {
        level.valueInt(_wildPk.getLevel());
        name.setupValue(_wildPk.getName());
        ability.setupValue(_wildPk.getAbility());
        item.setupValue(_wildPk.getItem());
        gender.setupValue(_wildPk.getGender());
    }

    public void feedSubsQuick(IdList<SubscribedTranslation> _subs) {
        _subs.addAllElts(name.getSubs());
        _subs.add(new SubscribedTranslationSelectChangeEvtsText(name.getSelectUniq().getSelect()));
        _subs.addAllElts(ability.getSubs());
        _subs.addAllElts(item.getSubs());
        _subs.addAllElts(gender.getSubs());
    }

    public void buildEntity(Pokemon _wildPk) {
        _wildPk.setLevel(level.valueInt());
        _wildPk.setName(name.tryRet());
        _wildPk.setAbility(ability.tryRet());
        _wildPk.setItem(item.tryRet());
        _wildPk.setGender(gender.tryRet());
    }

    public AbstractProgramInfos getApi() {
        return api;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

    public SubscribedTranslationList getSubscribedTranslationList() {
        return subscribedTranslationList;
    }

    public AbsCommonFrame getFrame() {
        return frame;
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
