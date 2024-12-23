package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class FormWildPk {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslationList;
    private GeneComponentModelEltEnumSub<String> name;
    private GeneComponentModelEltEnumSub<String> ability;
    private GeneComponentModelEltEnumSub<String> item;
    private GeneComponentModelEltEnumSub<Gender> gender;
    private final GeneComponentModelInt level;
    private WildPk wildPk;
    private AbsPanel form;
    private AbsScrollPane view;
    private final AbsCommonFrame frame;
    public FormWildPk(AbstractProgramInfos _ed, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _f) {
        api = _ed;
        facadeGame = _facade;
        subscribedTranslationList = _sub;
        frame = _f;
        level = new GeneComponentModelInt(api);
    }

    public void feedForm() {
        wildPk = Instances.newWildPk();
        form = api.getCompoFactory().newPageBox();
        form.add(level.geneInt());
        level.getSpinner().addChangeListener(new RefreshWildPkMoves(facadeGame,this));
        name = ConverterCommonMapUtil.buildPkFull(api, facadeGame, subscribedTranslationList);
        form.add(name.geneEnum());
        name.getSelectUniq().getSelect().addListener(new RefreshWildPkMoves(facadeGame,this));
        ability = ConverterCommonMapUtil.buildAbFull(api, facadeGame, subscribedTranslationList, new IdMap<String, String>());
        form.add(ability.geneEnum());
        item = ConverterCommonMapUtil.buildItFull(api, facadeGame, subscribedTranslationList, ConverterCommonMapUtil.defKeyEmpty(" "));
        form.add(item.geneEnum());
        gender = ConverterCommonMapUtil.buildGender(api, facadeGame, subscribedTranslationList);
        form.add(gender.geneEnum());
        view = api.getCompoFactory().newAbsScrollPane();
        form.add(view);
    }

    public void feedForm(WildPk _wp) {
        wildPk = ConverterCommonMapUtil.copyWildPk(_wp);
        level.valueInt(wildPk.getLevel());
        name.setupValue(wildPk.getName());
        ability.setupValue(wildPk.getAbility());
        item.setupValue(wildPk.getItem());
        gender.setupValue(wildPk.getGender());
        RefreshWildPkMoves.act(api,facadeGame,this);
    }

    public void feedSubs(IdList<SubscribedTranslation> _subs) {
        _subs.addAllElts(name.getSubs());
        _subs.add(new SubscribedTranslationSelectChangeEvtsText<String>(name.getSelectUniq()));
        _subs.addAllElts(ability.getSubs());
        _subs.addAllElts(item.getSubs());
        _subs.addAllElts(gender.getSubs());
        _subs.add(new RefreshWildPkMoves(facadeGame,this));
    }

    public WildPk buildEntity() {
        wildPk.setLevel((short) level.valueInt());
        wildPk.setName(name.tryRet());
        wildPk.setAbility(ability.tryRet());
        wildPk.setItem(item.tryRet());
        wildPk.setGender(gender.tryRet());
        return getWildPk();
    }

    public AbstractProgramInfos getApi() {
        return api;
    }

    public AbsCommonFrame getFrame() {
        return frame;
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

    public AbsScrollPane getView() {
        return view;
    }
}
