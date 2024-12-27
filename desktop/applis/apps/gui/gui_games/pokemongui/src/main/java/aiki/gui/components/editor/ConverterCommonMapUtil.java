package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.status.*;
import aiki.fight.status.effects.*;
import aiki.fight.util.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.tree.util.*;
import aiki.map.util.*;
import aiki.util.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.maths.*;
import code.maths.litteral.*;
import code.maths.montecarlo.*;
import code.util.*;
import code.util.core.*;

public final class ConverterCommonMapUtil {
    private ConverterCommonMapUtil() {
    }

    public static GeneComponentModelEltEnumSub<String> buildAbFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty) {
        return merge(_api, _facade, _sub.getFactoryAb(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildPkFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return merge(_api, _facade, _sub.getFactoryPk(), new CustList<String>(), new StringMap<String>());
    }

    public static GeneComponentModelEltEnumSub<String> buildMvFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return buildMvFull(_api, _facade, _sub, new StringMap<String>());
    }

    public static GeneComponentModelEltEnumSub<String> buildMvFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty) {
        return merge(_api, _facade, _sub.getFactoryMv(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildItFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub) {
        return buildItFull(_api, _facade, _sub, new StringMap<String>());
    }
    public static GeneComponentModelEltEnumSub<String> buildItFull(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty) {
        return merge(_api, _facade, _sub.getFactoryIt(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildTypeElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return buildTypeElt(_api, _facade, _sub, new StringMap<String>());
    }
    public static GeneComponentModelEltEnumSub<String> buildTypeElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty){
        return merge(_api, _facade, _sub.getFactoryTy(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildCatElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return buildCatElt(_api, _facade, _sub, new StringMap<String>());
    }
    public static GeneComponentModelEltEnumSub<String> buildCatElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty){
        return merge(_api, _facade, _sub.getFactoryCa(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelEltEnumSub<String> buildStatus(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub, AbsMap<String,String> _empty){
        return merge(_api, _facade, _sub.getFactorySt(), new CustList<String>(), _empty);
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildAbilityList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryAb());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildItemList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryIt());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildMoveList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryMv());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildPkList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryPk());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildStatusList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactorySt());
    }
    public static GeneComponentModelLsStrSub<String,StringList> buildTypeList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLs(_api, _facade, _sub.getFactoryTy());
    }
    public static GeneComponentModelLsStrSub<Short,Shorts> buildTmList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLsNb(_api, _facade, _sub.getFactoryMv(), _sub.getFactoryTm());
    }
    public static GeneComponentModelLsStrSub<Short,Shorts> buildHmList(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeLsNb(_api, _facade, _sub.getFactoryMv(), _sub.getFactoryHm());
    }
    public static GeneComponentModelEltEnumSub<Short> buildTm(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeNb(_api, _facade, _sub.getFactoryMv(), _sub.getFactoryTm());
    }
    public static GeneComponentModelEltEnumSub<Short> buildHm(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _sub){
        return mergeNb(_api, _facade, _sub.getFactoryMv(), _sub.getFactoryHm());
    }
    public static GeneComponentModelEltEnumSub<String> buildImg(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationMessagesFactoryImgName _img) {
        return new StringSubscribeBuilderUtil(_img).mergeQuick(_api, _facade, new CustList<String>(), new StringMap<String>(), new EmptyDefValue());
    }
    public static GeneComponentModelEltEnumSub<String> merge(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactoryCoreMessages<String> _builder, CustList<String> _excluded, AbsMap<String,String> _withEmptyStr) {
        return new StringSubscribeBuilderUtil(_builder).merge(_api,_sub,_excluded,_withEmptyStr);
    }

    public static GeneComponentModelLsStrSub<String,StringList> mergeLs(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builder) {
        return new StringSubscribeBuilderUtil(_builder).mergeLs(_api, _sub);
    }
    private static GeneComponentModelLsStrSub<Short,Shorts> mergeLsNb(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builderMv, SubscribedTranslationMessagesNbFactory _builder) {
        ShortMap<String> map_ = _builder.retrieveMap(_api, _sub);
        AbsMap<String, String> messages_ = _builderMv.getContainer().buildMessages(_api, _sub);
        ShortMap<String> sub_ = map(map_, messages_);
        TreeMap<Short, String> treeFilter_ = feedTreeNb(sub_, sub_.getKeys());
        GeneComponentModelLs<Short> sel_ = new GeneComponentModelLs<Short>(_api, treeFilter_);
        GeneComponentModelLsStrSub<Short,Shorts> g_ = new GeneComponentModelLsStrSub<Short,Shorts>(sel_,new IntListConvertShort());
        feedSubNb(_builderMv,_builder, sub_, treeFilter_, sel_, g_.getSubs(), messages_);
        return g_;
    }
    private static GeneComponentModelEltEnumSub<Short> mergeNb(AbstractProgramInfos _api, FacadeGame _sub, SubscribedTranslationMessagesFactory _builderMv, SubscribedTranslationMessagesNbFactory _builder) {
        ShortMap<String> map_ = _builder.retrieveMap(_api, _sub);
        AbsMap<String, String> messages_ = _builderMv.getContainer().buildMessages(_api, _sub);
        ShortMap<String> sub_ = map(map_, messages_);
        TreeMap<Short, String> treeFilter_ = feedTreeNb(sub_, sub_.getKeys());
        GeneComponentModelElt<Short> sel_ = new GeneComponentModelElt<Short>(_api, treeFilter_,new NbDefValue<Short>((short)0));
        GeneComponentModelEltEnumSub<Short> g_ = new GeneComponentModelEltEnumSub<Short>(sel_);
        feedSubNb(_builderMv,_builder, sub_, treeFilter_, sel_, g_.getSubs(), messages_);
        return g_;
    }
    public static ShortMap<String> map(ShortMap<String> _map, AbsMap<String,String> _messages) {
        ShortMap<String> messages_ = new ShortMap<String>();
        for (EntryCust<Short,String> e: _map.entryList()) {
            messages_.addEntry(e.getKey(),StringUtil.nullToEmpty(_messages.getVal(e.getValue()))+":"+e.getKey());
        }
        return messages_;
    }

    private static void feedSubNb(SubscribedTranslationMessagesFactory _builderMv, SubscribedTranslationMessagesNbFactory _builder, ShortMap<String> _sub, TreeMap<Short, String> _treeFilter, GeneComponentModelStr _sel, IdList<SubscribedTranslation> _subs, AbsMap<String, String> _messages) {
        _subs.add(_builderMv.getContainer().buildSub(_messages, new StringMap<String>()));
        _subs.add(_builder.buildSub(_sub, _messages));
        _subs.add(_builder.buildSub(_treeFilter, _messages));
        _subs.add(new SubscribedTranslationSelect(_sel));
    }

    public static TreeMap<Short, String> feedTreeNb(AbsMap<Short, String> _messages, CustList<Short> _rem) {
        TreeMap<Short, String> tree_ = new TreeMap<Short, String>(new ComparatorTrWrapper<Short>().wrap(_messages));
        for (short s: _rem) {
            tree_.put(s,_messages.getVal(s));
        }
        return tree_;
    }
    public static GeneComponentModelEltEnumSub<EnvironmentType> buildEnvironmentType(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact){
        return new SubscribeBuilderUtil<EnvironmentType>(_fact.getFactoryEnvironmentType()).merge(_api,_facade,new CustList<EnvironmentType>(),new IdMap<EnvironmentType, String>());
    }
    public static GeneComponentModelEltEnumSub<Gender> buildGender(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact){
        return new SubscribeBuilderUtil<Gender>(_fact.getFactoryGender()).merge(_api,_facade,new CustList<Gender>(),new IdMap<Gender, String>());
    }
    public static GeneComponentModelEltEnumSub<TargetChoice> buildTargetChoice(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact) {
        return new SubscribeBuilderUtil<TargetChoice>(_fact.getFactoryTarget()).merge(_api, _facade, new CustList<TargetChoice>(), new IdMap<TargetChoice, String>());
    }
    public static GeneComponentModelEltEnumSub<Statistic> buildStatisticsElt(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact) {
        return new SubscribeBuilderUtil<Statistic>(_fact.getFactoryStat()).merge(_api, _facade, new CustList<Statistic>(), new IdMap<Statistic, String>());
    }
    public static GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> buildStatisticsLs(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _fact) {
        return new SubscribeBuilderUtil<Statistic>(_fact.getFactoryStat()).mergeLs(_api,_facade);
    }
    public static GeneComponentModelElt<GenderRepartition> buildGenderRepartition(AbstractProgramInfos _api){
        return new GeneComponentModelElt<GenderRepartition>(_api,messages(MessagesPkEditor.getMessagesEditorSelectGenderRepTr(MessagesPkEditor.getAppliTr(_api.currentLg())).getMapping()));
    }
    public static GeneComponentModelElt<ExpType> buildExpType(AbstractProgramInfos _api){
        IdMap<ExpType,String> messages_ = new IdMap<ExpType, String>();
        for (EntryCust<String,String> e: MessagesPkEditor.getMessagesEditorExpTypeTr(MessagesPkEditor.getAppliTr(_api.currentLg())).getMapping().entryList()) {
            messages_.addEntry(ExpType.getExpTypeByName(e.getKey()),e.getValue());
        }
        return new GeneComponentModelElt<ExpType>(_api,messages_);
    }
    public static StringMap<StringMap<String>> toEntityLg(StringMap<StringMap<String>> _map) {
        StringMap<StringMap<String>> inv_ = new StringMap<StringMap<String>>();
        StringList next_ = new StringList();
        for (EntryCust<String,StringMap<String>> e: _map.entryList()) {
            next_.addAllElts(e.getValue().getKeys());
        }
        next_.removeDuplicates();
        for (String e: next_) {
            StringMap<String> trs_ = new StringMap<String>();
            for (EntryCust<String,StringMap<String>> l: _map.entryList()) {
                trs_.addEntry(l.getKey(), StringUtil.nullToEmpty(l.getValue().getVal(e)));
            }
            inv_.addEntry(e, trs_);
        }
        return inv_;
    }
    private static IdMap<GenderRepartition,String> messages(StringMap<String> _m) {
        IdMap<GenderRepartition,String> i_ = new IdMap<GenderRepartition, String>();
        for (EntryCust<String,String> e: _m.entryList()) {
            i_.addEntry(GenderRepartition.getGenderRepartitionByName(e.getKey()),e.getValue());
        }
        return i_;
    }
    public static AbstractImage buildImg(AbstractProgramInfos _api,FacadeGame _f,Points<Block> _bk, Point _tl, int _r, int _c) {
        int side_ = _f.getMap().getSideLength();
        Limits limits_ = Level.limits(_bk);
        Point topLeft_ = limits_.getTopLeft();
        int height_ = _r*side_;
        int width_ = _c*side_;
        AbstractImage img_ = _api.getImageFactory().newImageArgb(width_, height_);
        for (EntryCust<Point,Block> e: _bk.entryList()) {
            img_.drawImage(buildImg(_api,_f,e.getValue()),(e.getKey().getx() - topLeft_.getx() - _tl.getx()) * side_, (e.getKey().gety() - topLeft_.gety() - _tl.gety()) * side_);
        }
        return img_;
    }
    public static AbstractImage buildImgFore(AbstractProgramInfos _api,FacadeGame _f, Limits _lims,Points<int[][]> _bk, Point _tl, int _r, int _c) {
        int side_ = _f.getMap().getSideLength();
        Point topLeft_ = _lims.getTopLeft();
        int height_ = _r*side_;
        int width_ = _c*side_;
        AbstractImage img_ = _api.getImageFactory().newImageArgb(width_, height_);
        for (EntryCust<Point,int[][]> e: _bk.entryList()) {
            drawImage(_api,img_,true,e.getValue(),(e.getKey().getx() - topLeft_.getx() - _tl.getx()) * side_, (e.getKey().gety() - topLeft_.gety() - _tl.gety()) * side_);
        }
        return img_;
    }
    public static AbstractImage buildImg(AbstractProgramInfos _api,FacadeGame _f,Block _bk) {
        int side_ = _f.getMap().getSideLength();
        int height_ = _bk.getHeight() * side_;
        int width_ = _bk.getWidth() * side_;
        AbstractImage img_ = _api.getImageFactory().newImageArgb(width_, height_);
        int[][] pixels_ = _f.getData().getImage(_bk.getTileFileName());
        Dims dims_ = dims(pixels_);
        drawImage(_api,img_,false,pixels_,NumberUtil.quot(width_ - dims_.getWidth(), 2),NumberUtil.quot(height_ - dims_.getHeight(), 2));
        return img_;
    }
    public static void drawImage(AbstractProgramInfos _api, AbstractImage _target, boolean _tr, int[][] _pixels, int _x, int _y) {
        if (_pixels.length > 0) {
            AbstractImage image_ = ConverterGraphicBufferedImage.decodeToImage(_api.getImageFactory(), _pixels);
            if (_tr) {
                ConverterGraphicBufferedImage.transparentAllWhite(image_);
            }
            _target.drawImage(image_,_x,_y);
        }
    }
    public static Dims dims(int[][] _pixels) {
        if (_pixels.length == 0) {
            return new Dims();
        }
        return new Dims(_pixels[0].length, _pixels.length);
    }

    public static PointsBuilding copyPointsBuilding(Points<Building> _e) {
        PointsBuilding cp_ = new PointsBuilding(new CollCapacity(_e.size()));
        for (EntryCust<Point,Building> f:_e.entryList()) {
            if (f.getValue() instanceof Gym) {
                cp_.addEntry(new Point(f.getKey()),ConverterCommonMapUtil.copyGym((Gym) f.getValue()));
            } else {
                cp_.addEntry(new Point(f.getKey()),ConverterCommonMapUtil.copyPkCenter((PokemonCenter) f.getValue()));
            }
        }
        return cp_;
    }
    public static PokemonCenter copyPkCenter(PokemonCenter _e) {
        PokemonCenter cp_ = new PokemonCenter();
        LevelIndoorPokemonCenter in_ = new LevelIndoorPokemonCenter();
        in_.setStorageCoords(copyNullablePoint(_e.getIndoor().getStorageCoords()));
        Points<Person> gt_ = LevelIndoorPokemonCenter.tryAdd(_e.getIndoor().getGerants(),new PointPersonMapper(),new GerantPersonMapper(),new SellerPersonMapper());
        in_.setGerants(gt_);
        in_.setBlocks(copyPointsBlock(_e.getIndoor().getBlocks()));
        cp_.setLevel(in_);
        cp_.setImageFileName(_e.getImageFileName());
        cp_.setExitCity(copyNullablePoint(_e.getExitCity()));
        return cp_;
    }

    public static Gym copyGym(Gym _e) {
        Gym cp_ = new Gym();
        LevelIndoorGym in_ = new LevelIndoorGym();
        in_.setGymLeaderCoords(copyNullablePoint(_e.getIndoor().getGymLeaderCoords()));
        in_.setGymLeader(copyGymLeader(_e.getIndoor().getGymLeader()));
        Points<GymTrainer> gt_ = new PointsGymTrainer(new CollCapacity(_e.getIndoor().getGymTrainers().size()));
        for (EntryCust<Point,GymTrainer> f:_e.getIndoor().getGymTrainers().entryList()) {
            gt_.addEntry(new Point(f.getKey()),copyGymTrainer(f.getValue()));
        }
        in_.setGymTrainers(gt_);
        in_.setBlocks(copyPointsBlock(_e.getIndoor().getBlocks()));
        cp_.setLevel(in_);
        cp_.setImageFileName(_e.getImageFileName());
        cp_.setExitCity(copyNullablePoint(_e.getExitCity()));
        return cp_;
    }

    public static GymLeader copyGymLeader(GymLeader _e) {
        GymLeader cp_ = new GymLeader();
        cp_.setTm(_e.getTm());
        cp_.setName(_e.getName());
        cp_.setTeam(copyListPkTrainer(_e.getTeam()));
        cp_.setReward(_e.getReward());
        copyTrainer(cp_,_e);
        return cp_;
    }

    public static GymTrainer copyGymTrainer(GymTrainer _e) {
        GymTrainer cp_ = new GymTrainer();
        cp_.setTeam(copyListPkTrainer(_e.getTeam()));
        cp_.setReward(_e.getReward());
        copyTrainer(cp_,_e);
        return cp_;
    }
    public static LevelCave copyLevelCave(LevelCave _e) {
        LevelCave cp_ = new LevelCave();
        copyLevelWithWildPokemon(cp_,_e);
        Points<Link> links_ = new PointsLink(new CollCapacity(_e.getLinksOtherLevels().size()));
        for (EntryCust<Point,Link> f:_e.getLinksOtherLevels().entryList()) {
            links_.addEntry(new Point(f.getKey()),copyLink(f.getValue()));
        }
        cp_.setLinksOtherLevels(links_);
        return cp_;
    }

    public static Link copyLink(Link _e){
        Link cp_ = new Link();
        cp_.setFileName(_e.getFileName());
        cp_.setCoords(new Coords(_e.getCoords()));
        return cp_;
    }

    public static LevelRoad copyLevelRoad(LevelRoad _e) {
        LevelRoad cp_ = new LevelRoad();
        copyLevelWithWildPokemon(cp_,_e);
        return cp_;
    }
    public static void copyLevelWithWildPokemon(LevelWithWildPokemon _cp, LevelWithWildPokemon _e) {
        _cp.setBlocks(copyPointsBlock(_e.getBlocks()));
        _cp.setWildPokemonAreas(copyListArea(_e.getWildPokemonAreas()));
        Points<DualFight> duals_ = new PointsDualFight(new CollCapacity(_e.getDualFights().size()));
        for (EntryCust<Point,DualFight> f:_e.getDualFights().entryList()) {
            duals_.addEntry(new Point(f.getKey()),copyDualFight(f.getValue()));
        }
        _cp.setDualFights(duals_);
        Points<CharacterInRoadCave> chars_ = new PointsCharacterInRoadCave(new CollCapacity(_e.getCharacters().size()));
        for (EntryCust<Point,CharacterInRoadCave> f:_e.getCharacters().entryList()) {
            if (f.getValue() instanceof TrainerMultiFights) {
                chars_.addEntry(new Point(f.getKey()),copyTrainerMultiFights((TrainerMultiFights) f.getValue()));
            } else {
                chars_.addEntry(new Point(f.getKey()),copyDealerItem((DealerItem) f.getValue()));
            }
        }
        _cp.setCharacters(chars_);
        Points<WildPk> legs_ = new PointsWildPk(new CollCapacity(_e.getLegendaryPks().size()));
        for (EntryCust<Point,WildPk> f:_e.getLegendaryPks().entryList()) {
            legs_.addEntry(new Point(f.getKey()),copyWildPk(f.getValue()));
        }
        _cp.setLegendaryPks(legs_);
        Points<Short> hm_ = new PointsShort(new CollCapacity(_e.getHm().size()));
        for (EntryCust<Point,Short> f:_e.getHm().entryList()) {
            hm_.addEntry(new Point(f.getKey()),f.getValue());
        }
        _cp.setHm(hm_);
        Points<Short> tm_ = new PointsShort(new CollCapacity(_e.getTm().size()));
        for (EntryCust<Point,Short> f:_e.getTm().entryList()) {
            tm_.addEntry(new Point(f.getKey()),f.getValue());
        }
        _cp.setTm(tm_);
        Points<String> items_ = new PointsString(new CollCapacity(_e.getItems().size()));
        for (EntryCust<Point,String> f:_e.getItems().entryList()) {
            items_.addEntry(new Point(f.getKey()),f.getValue());
        }
        _cp.setItems(items_);
    }
    public static PointsBlock copyPointsBlock(Points<Block> _e){
        PointsBlock cp_ = new PointsBlock(new CollCapacity(_e.size()));
        for (EntryCust<Point,Block> f: _e.entryList()) {
            cp_.addEntry(new Point(f.getKey().getx(), f.getKey().gety()),copyBlock(f.getValue()));
        }
        return cp_;
    }
    public static Block copyBlock(Block _e){
        Block cp_ = new Block();
        cp_.setTileFileName(_e.getTileFileName());
        cp_.setIndexApparition(_e.getIndexApparition());
        cp_.setWidth(_e.getWidth());
        cp_.setHeight(_e.getHeight());
        cp_.setType(_e.getType());
        return cp_;
    }
    public static DualFight copyDualFight(DualFight _e) {
        DualFight cp_ = new DualFight();
        cp_.setPt(copyNullablePoint(_e.getPt()));
        cp_.setNames(new StringList(_e.getNames()));
        Ally a_ = new Ally();
        a_.setTeam(copyListPkTrainer(_e.getAlly().getTeam()));
        cp_.setAlly(a_);
        TempTrainer t_ = new TempTrainer();
        t_.setTeam(copyListPkTrainer(_e.getFoeTrainer().getTeam()));
        t_.setReward(_e.getFoeTrainer().getReward());
        t_.setImageMiniSecondTrainerFileName(_e.getFoeTrainer().getImageMiniSecondTrainerFileName());
        copyTrainer(t_,_e.getFoeTrainer());
        cp_.setFoeTrainer(t_);
        return cp_;
    }
    public static NullablePoint copyNullablePoint(NullablePoint _e) {
        if (_e.isDefined()) {
            return new NullablePoint(new Point(_e.getPoint()));
        }
        return new NullablePoint();
    }

    public static DealerItem copyDealerItem(DealerItem _e) {
        DealerItem cp_ = new DealerItem();
        cp_.setItems(new StringList(_e.getItems()));
        cp_.setTechnicalMoves(new Shorts(_e.getTechnicalMoves()));
        cp_.setImageMiniFileName(_e.getImageMiniFileName());
        return cp_;
    }
    public static TrainerMultiFights copyTrainerMultiFights(TrainerMultiFights _e) {
        TrainerMultiFights cp_ = new TrainerMultiFights();
        CustList<PokemonTeam> l_ = new CustList<PokemonTeam>();
        for (PokemonTeam f:_e.getTeamsRewards()) {
            l_.add(copyPokemonTeam(f));
        }
        cp_.setTeamsRewards(l_);
        copyTrainer(cp_,_e);
        return cp_;
    }

    public static void copyTrainer(Trainer _cp,Trainer _e) {
        _cp.setImageMaxiFileName(_e.getImageMaxiFileName());
        _cp.setImageMiniFileName(_e.getImageMiniFileName());
        _cp.setMultiplicityFight(_e.getMultiplicityFight());
    }
    public static PokemonTeam copyPokemonTeam(PokemonTeam _e) {
        PokemonTeam cp_ = new PokemonTeam();
        cp_.setTeam(copyListPkTrainer(_e.getTeam()));
        cp_.setReward(_e.getReward());
        return cp_;
    }
    public static CustList<PkTrainer> copyListPkTrainer(CustList<PkTrainer> _e) {
        CustList<PkTrainer> cp_ = new CustList<PkTrainer>(new CollCapacity(_e.size()));
        for (PkTrainer f: _e) {
            cp_.add(copyPkTrainer(f));
        }
        return cp_;
    }
    public static CustList<AbsAreaApparition> copyListArea(CustList<AbsAreaApparition> _e) {
        CustList<AbsAreaApparition> cp_ = new CustList<AbsAreaApparition>(new CollCapacity(_e.size()));
        for (AbsAreaApparition f:_e) {
            cp_.add(copyArea(f));
        }
        return cp_;
    }
    public static AbsAreaApparition copyArea(AbsAreaApparition _e) {
        if (_e instanceof AreaApparition) {
            return copyAreaApparition((AreaApparition)_e);
        }
        return copyMultAreaApparition((MultAreaApparition)_e);
    }
    public static AreaApparition copyAreaApparition(AreaApparition _e) {
        AreaApparition cp_ = new AreaApparition();
        cp_.setWildPokemon(copyListWildPk(_e.getWildPokemon()));
        cp_.setWildPokemonFishing(copyListWildPk(_e.getWildPokemonFishing()));
        cp_.setMultFight(_e.getMultFight());
        cp_.setAvgNbSteps(_e.getAvgNbSteps());
        return cp_;
    }
    public static MultAreaApparition copyMultAreaApparition(MultAreaApparition _e) {
        MultAreaApparition cp_ = new MultAreaApparition();
        cp_.setWildPokemonList(copyListListWildPk(_e.getWildPokemonList()));
        cp_.setWildPokemonFishingList(copyListListWildPk(_e.getWildPokemonFishingList()));
        cp_.setAvgNbSteps(_e.getAvgNbSteps());
        return cp_;
    }
    public static CustList<CustList<WildPk>> copyListListWildPk(CustList<CustList<WildPk>> _e) {
        CustList<CustList<WildPk>> cp_ = new CustList<CustList<WildPk>>(new CollCapacity(_e.size()));
        for (CustList<WildPk> f:_e) {
            cp_.add(copyListWildPk(f));
        }
        return cp_;
    }
    public static CustList<WildPk> copyListWildPk(CustList<WildPk> _e) {
        CustList<WildPk> cp_ = new CustList<WildPk>(new CollCapacity(_e.size()));
        for (WildPk f:_e) {
            cp_.add(copyWildPk(f));
        }
        return cp_;
    }
    public static PkTrainer copyPkTrainer(PkTrainer _e) {
        PkTrainer cp_ = new PkTrainer();
        cp_.setMoves(new StringList(_e.getMoves()));
        copyPokemon(cp_,_e);
        return cp_;
    }
    public static WildPk copyWildPk(WildPk _e) {
        WildPk cp_ = new WildPk();
        copyPokemon(cp_,_e);
        return cp_;
    }
    public static void copyPokemon(Pokemon _cp,Pokemon _e) {
        _cp.setName(_e.getName());
        _cp.setAbility(_e.getAbility());
        _cp.setItem(_e.getItem());
        _cp.setGender(_e.getGender());
        _cp.setLevel(_e.getLevel());
    }
    public static TileMiniMap copyTileMiniMap(TileMiniMap _e){
        TileMiniMap cp_ = new TileMiniMap();
        cp_.setFile(_e.getFile());
        cp_.setPlace(_e.getPlace());
        cp_.setHeros(_e.isHeros());
        return cp_;
    }
    public static AbilityData copyAbilityData(AbilityData _e){
        AbilityData cp_ = new AbilityData();
        cp_.setBreakFoeImmune(copyListTypesDuo(_e.getBreakFoeImmune()));
        cp_.setForbidUseBerryAgainstFoes(_e.isForbidUseBerryAgainstFoes());
        cp_.setChgtTypeByWeather(new StringMap<String>(_e.getChgtTypeByWeather()));
        cp_.setChgtTypeByDamage(_e.isChgtTypeByDamage());
        cp_.setRecoilDamageFoe(new Rate(_e.getRecoilDamageFoe()));
        cp_.setDecreaseNecStepsHatch(_e.getDecreaseNecStepsHatch());
        cp_.setDivideStatusRound(copyStringMapRate(_e.getDivideStatusRound()));
        cp_.setHealHpByWeather(copyStringMapRate(_e.getHealHpByWeather()));
        cp_.setIgnAbility(new StringList(_e.getIgnAbility()));
        cp_.setIgnFoeTeamMove(new StringList(_e.getIgnFoeTeamMove()));
        cp_.setIgnFoeStatisBoost(_e.isIgnFoeStatisBoost());
        cp_.setImmuMove(new StringList(_e.getImmuMove()));
        cp_.setImmuLowStat(new IdList<Statistic>(_e.getImmuLowStat()));
        cp_.setImmuLowStatIfStatus(copyListStatisticStatus(_e.getImmuLowStatIfStatus()));
        cp_.setImmuCh(_e.isImmuCh());
        cp_.setImmuWeather(new StringList(_e.getImmuWeather()));
        cp_.setImmuDamageTrappingMoves(_e.isImmuDamageTrappingMoves());
        cp_.setImmuDamageAllyMoves(_e.isImmuDamageAllyMoves());
        cp_.setImmuDamageRecoil(_e.isImmuDamageRecoil());
        cp_.setImmuAbility(new StringList(_e.getImmuAbility()));
        cp_.setImmuStatusBeginRound(new StringList(_e.getImmuStatusBeginRound()));
        cp_.setImmuRechargeRound(_e.isImmuRechargeRound());
        cp_.setImmuStatus(copyStringMapStringList(_e.getImmuStatus()));
        cp_.setSlowing(_e.isSlowing());
        cp_.setMultDamageFoe(copyStringMapRate(_e.getMultDamageFoe()));
        cp_.setMultDamageCh(new Rate(_e.getMultDamageCh()));
        cp_.setMultAllyDamage(new Rate(_e.getMultAllyDamage()));
        cp_.setMultSufferedDamageSuperEff(new Rate(_e.getMultSufferedDamageSuperEff()));
        cp_.setImmuSufferedDamageLowEff(_e.isImmuSufferedDamageLowEff());
        cp_.setMultEvtRateCh(new Rate(_e.getMultEvtRateCh()));
        cp_.setCancelSecEffectOther(_e.isCancelSecEffectOther());
        cp_.setCancelSecEffectOwner(_e.isCancelSecEffectOwner());
        cp_.setMultEvtRateSecEffectOwner(new Rate(_e.getMultEvtRateSecEffectOwner()));
        cp_.setMultPower(_e.getMultPower());
        cp_.setMultDamage(_e.getMultDamage());
        cp_.setMultStab(new Rate(_e.getMultStab()));
        cp_.setBonusStatRank(new IdMap<Statistic,Byte>(_e.getBonusStatRank()));
        cp_.setBoostStatRankProtected(new IdMap<Statistic,Byte>(_e.getBoostStatRankProtected()));
        cp_.setBoostStatRankEndRound(new IdMap<Statistic,Byte>(_e.getBoostStatRankEndRound()));
        cp_.setMultStatAlly(new IdMap<Statistic,Rate>(_e.getMultStatAlly()));
        cp_.setMultStatIfKoFoe(new IdMap<Statistic,Byte>(_e.getMultStatIfKoFoe()));
        cp_.setMultStatIfLowStat(new IdMap<Statistic,Byte>(_e.getMultStatIfLowStat()));
        cp_.setMultStatIfCat(copyStatisticCategoryRate(_e.getMultStatIfCat()));
        cp_.setMultStatIfStatutRank(copyStatisticStatusList(_e.getMultStatIfStatutRank()));
        cp_.setMultStatIfDamageCat(copyStatisticCategoryByte(_e.getMultStatIfDamageCat()));
        cp_.setMultStatIfDamgeType(copyStatisticTypeByte(_e.getMultStatIfDamgeType()));
        cp_.setMultStat(new IdMap<Statistic,String>(_e.getMultStat()));
        cp_.setInflictingDamageInsteadOfSuffering(_e.isInflictingDamageInsteadOfSuffering());
        cp_.setMultVarBoost(new Rate(_e.getMultVarBoost()));
        cp_.setNbUsedPp(_e.getNbUsedPp());
        cp_.setNbHits(_e.isNbHits());
        cp_.setBreakProtection(_e.isBreakProtection());
        cp_.setPlate(_e.isPlate());
        cp_.setHealedStatusBySwitch(_e.isHealedStatusBySwitch());
        cp_.setHealedHpRateBySwitch(new Rate(_e.getHealedHpRateBySwitch()));
        cp_.setIncreasedPrio(new StringMap<Short>(_e.getIncreasedPrio()));
        cp_.setIncreasedPrioTypes(new StringMap<Short>(_e.getIncreasedPrioTypes()));
        cp_.setMaxStatisticsIfCh(new IdList<Statistic>(_e.getMaxStatisticsIfCh()));
        cp_.setSingleStatus(copyMonteCarloString(_e.getSingleStatus()));
        cp_.setAchievedDisappearedPk(_e.isAchievedDisappearedPk());
        cp_.setForwardStatus(new StringMap<String>(_e.getForwardStatus()));
        cp_.setFailStatus(new StringMap<String>(_e.getFailStatus()));
        cp_.setTypeForMoves(_e.getTypeForMoves());
        cp_.setMaxHpForUsingBerry(new Rate(_e.getMaxHpForUsingBerry()));
        cp_.setMumy(_e.isMumy());
        cp_.setHealHpByTypeIfWeather(copyWeatherTypes(_e.getHealHpByTypeIfWeather()));
        cp_.setImmuMoveTypesByWeather(copyStringMapStringList(_e.getImmuMoveTypesByWeather()));
        cp_.setEffectEndRound(copyEffectEndRoundList(_e.getEffectEndRound()));
        cp_.setEffectSending(copyEffectWhileSendingWithStatisticList(_e.getEffectSending()));
        cp_.setChangingBoostTypes(copyStringMapTypeDamageBoost(_e.getChangingBoostTypes()));
        cp_.setImmuAllyFromMoves(new StringList(_e.getImmuAllyFromMoves()));
        cp_.setImmuStatusTypes(copyStringMapStringList(_e.getImmuStatusTypes()));
        cp_.setImmuLowStatisTypes(copyStringMapListStatistic(_e.getImmuLowStatisTypes()));
        cp_.setLowStatFoeHit(new IdMap<Statistic,Byte>(_e.getLowStatFoeHit()));
        cp_.setCopyMovesTypes(_e.isCopyMovesTypes());
        cp_.setMultPowerMovesTypesGlobal(copyStringMapRate(_e.getMultPowerMovesTypesGlobal()));
        cp_.setReverseEffectsPowerMovesTypesGlobal(_e.isReverseEffectsPowerMovesTypesGlobal());
        cp_.setHealHpWhileUsingBerry(new Rate(_e.getHealHpWhileUsingBerry()));
        cp_.setTakeItemByDamagingMove(_e.isTakeItemByDamagingMove());
        cp_.setGiveItemToAllyHavingUsed(_e.isGiveItemToAllyHavingUsed());
        return cp_;
    }
    public static CustList<EffectEndRound> copyEffectEndRoundList(CustList<EffectEndRound> _ls) {
        CustList<EffectEndRound> cp_= new CustList<EffectEndRound>();
        for (EffectEndRound f:_ls){
            cp_.add(copyEffectEndRound(f));
        }
        return cp_;
    }
    public static CustList<EffectWhileSendingWithStatistic> copyEffectWhileSendingWithStatisticList(CustList<EffectWhileSendingWithStatistic> _ls) {
        CustList<EffectWhileSendingWithStatistic> cp_= new CustList<EffectWhileSendingWithStatistic>();
        for (EffectWhileSendingWithStatistic f:_ls){
            cp_.add(copyEffectWhileSendingWithStatistic(f));
        }
        return cp_;
    }

//    public static Combos copyCombos(Combos _e){
//        Combos cp_ = new Combos();
//        cp_.setEffects(new ListEffectCombos());
//        for (ListEffectCombo f:_e.getEffects()){
//            cp_.getEffects().add(new ListEffectCombo(f.getList(),copyEffectCombo(f.getCombo())));
//        }
//        return cp_;
//    }

    public static EffectWhileSendingWithStatistic copyEffectWhileSendingWithStatistic(EffectWhileSendingWithStatistic _e){
        EffectWhileSendingWithStatistic cp_ = new EffectWhileSendingWithStatistic();
        cp_.setEffect(copyEffectStatistic(_e.getEffect()));
        cp_.setWithEffect(_e.isWithEffect());
        cp_.setDisableWeather(_e.getDisableWeather());
        cp_.setEnabledWeather(_e.getEnabledWeather());
        cp_.setCopyingAbility(_e.getCopyingAbility());
        cp_.setMultWeight(new Rate(_e.getMultWeight()));
        return cp_;
    }

    public static Ball copyBall(Ball _e){
        Ball cp_ = new Ball();
        cp_.setCatchingRate(_e.getCatchingRate());
        copyItem(cp_,_e);
        return cp_;
    }

    public static Berry copyBerry(Berry _e){
        Berry cp_ = new Berry();
        cp_.setHealHpBySuperEffMove(new Rate(_e.getHealHpBySuperEffMove()));
        cp_.setLawForAttackFirst(_e.getLawForAttackFirst());
        cp_.setMultFoesDamage(copyStringMapEfficiencyRate(_e.getMultFoesDamage()));
        cp_.setMultStat(copyIdMapStatisticBoostHpRate(_e.getMultStat()));
        cp_.setWithoutFail(_e.getWithoutFail());
        cp_.setHealPp(_e.getHealPp());
        cp_.setHealHp(new Rate(_e.getHealHp()));
        cp_.setMaxHpHealingHp(new Rate(_e.getMaxHpHealingHp()));
        cp_.setHealStatus(new StringList(_e.getHealStatus()));
        cp_.setHealHpRate(new Rate(_e.getHealHpRate()));
        cp_.setMaxHpHealingHpRate(new Rate(_e.getMaxHpHealingHpRate()));
        cp_.setDamageRateRecoilFoe(copyStringMapRate(_e.getDamageRateRecoilFoe()));
        cp_.setCategoryBoosting(_e.getCategoryBoosting());
        cp_.setBoostStatis(new IdMap<Statistic,Byte>(_e.getBoostStatis()));
        copyItem(cp_,_e);
        return cp_;
    }

    public static Boost copyBoost(Boost _e){
        Boost cp_ = new Boost();
        cp_.setWinPp(new Rate(_e.getWinPp()));
        cp_.setHappiness(new StringMap<Short>(_e.getHappiness()));
        cp_.setEvs(new IdMap<Statistic,Short>(_e.getEvs()));
        copyItem(cp_,_e);
        return cp_;
    }

    public static EvolvingItem copyEvolvingItem(EvolvingItem _e){
        EvolvingItem cp_ = new EvolvingItem();
        copyItem(cp_,_e);
        return cp_;
    }

    public static EvolvingStone copyEvolvingStone(EvolvingStone _e){
        EvolvingStone cp_ = new EvolvingStone();
        copyItem(cp_,_e);
        return cp_;
    }

    public static Fossil copyFossil(Fossil _e){
        Fossil cp_ = new Fossil();
        cp_.setPokemon(_e.getPokemon());
        cp_.setLevel(_e.getLevel());
        copyItem(cp_,_e);
        return cp_;
    }

    public static HealingHp copyHealingHp(HealingHp _e){
        HealingHp cp_ = new HealingHp();
        cp_.setHp(new Rate(_e.getHp()));
        copyHealingItem(cp_,_e);
        return cp_;
    }

    public static HealingHpStatus copyHealingHpStatus(HealingHpStatus _e){
        HealingHpStatus cp_ = new HealingHpStatus();
        cp_.setHealedHpRate(new Rate(_e.getHealedHpRate()));
        copyHealingStatus(cp_,_e);
        return cp_;
    }

    public static void copyHealingItem(HealingItem _cp,HealingItem _e){
        _cp.setHappiness(new StringMap<Short>(_e.getHappiness()));
        _cp.setHealingTeam(_e.getHealingTeam());
        copyItem(_cp,_e);
    }

    public static HealingPp copyHealingPp(HealingPp _e){
        HealingPp cp_ = new HealingPp();
        cp_.setHealedMovePp(_e.getHealedMovePp());
        cp_.setHealingAllMovesFullpp(_e.getHealingAllMovesFullpp());
        cp_.setHealingAllMovesPp(_e.isHealingAllMovesPp());
        cp_.setHealingMoveFullpp(_e.getHealingMoveFullpp());
        copyHealingItem(cp_,_e);
        return cp_;
    }

    public static HealingSimpleItem copyHealingSimpleItem(HealingSimpleItem _e){
        HealingSimpleItem cp_ = new HealingSimpleItem();
        copyHealingItem(cp_,_e);
        return cp_;
    }

    public static HealingSimpleStatus copyHealingSimpleStatus(HealingSimpleStatus _e){
        HealingSimpleStatus cp_ = new HealingSimpleStatus();
        copyHealingStatus(cp_,_e);
        return cp_;
    }

    public static void copyHealingStatus(HealingStatus _cp,HealingStatus _e){
        _cp.setStatus(new StringList(_e.getStatus()));
        _cp.setHealingKo(_e.getHealingKo());
        copyHealingItem(_cp,_e);
    }

    public static void copyItem(Item _cp,Item _e){
        _cp.setPrice(_e.getPrice());
    }
    public static Item copyItem(Item _e){
        if (_e instanceof Ball){
            return copyBall((Ball)_e);
        }
        if (_e instanceof Berry){
            return copyBerry((Berry)_e);
        }
        if (_e instanceof Boost){
            return copyBoost((Boost)_e);
        }
        if (_e instanceof EvolvingItem){
            return copyEvolvingItem((EvolvingItem)_e);
        }
        if (_e instanceof EvolvingStone){
            return copyEvolvingStone((EvolvingStone)_e);
        }
        if (_e instanceof Fossil){
            return copyFossil((Fossil)_e);
        }
        if (_e instanceof HealingHp){
            return copyHealingHp((HealingHp)_e);
        }
        if (_e instanceof HealingHpStatus){
            return copyHealingHpStatus((HealingHpStatus)_e);
        }
        if (_e instanceof HealingPp){
            return copyHealingPp((HealingPp)_e);
        }
        if (_e instanceof HealingSimpleItem){
            return copyHealingSimpleItem((HealingSimpleItem)_e);
        }
        if (_e instanceof HealingSimpleStatus){
            return copyHealingSimpleStatus((HealingSimpleStatus)_e);
        }
        if (_e instanceof ItemForBattle){
            return copyItemForBattle((ItemForBattle)_e);
        }
        if (_e instanceof Repel){
            return copyRepel((Repel)_e);
        }
        return copySellingItem((SellingItem)_e);
    }

    public static ItemForBattle copyItemForBattle(ItemForBattle _e){
        ItemForBattle cp_ = new ItemForBattle();
        cp_.setTypesPk(new StringList(_e.getTypesPk()));
        cp_.setCancelImmuType(_e.getCancelImmuType());
        cp_.setAgainstEvo(_e.getAgainstEvo());
        cp_.setAttackLast(_e.getAttackLast());
        cp_.setBoostExp(_e.getBoostExp());
        cp_.setImmuStatus(new StringList(_e.getImmuStatus()));
        cp_.setImmuLowStatis(_e.getImmuLowStatis());
        cp_.setIncreasingMaxNbRoundTrap(new StringMap<Short>(_e.getIncreasingMaxNbRoundTrap()));
        cp_.setAttacksSoon(_e.getAttacksSoon());
        cp_.setSynchroStatus(new StringList(_e.getSynchroStatus()));
        cp_.setFailStatus(new StringMap<String>(_e.getFailStatus()));
        cp_.setProtectAgainstKo(new Rate(_e.getProtectAgainstKo()));
        cp_.setProtectAgainstKoIfFullHp(new Rate(_e.getProtectAgainstKoIfFullHp()));
        cp_.setDrainedHpByDamageRate(new Rate(_e.getDrainedHpByDamageRate()));
        cp_.setWinEvFight(new IdMap<Statistic,Short>(_e.getWinEvFight()));
        cp_.setLawForAttackFirst(copyMonteCarloBool(_e.getLawForAttackFirst()));
        cp_.setMultTrappingDamage(new Rate(_e.getMultTrappingDamage()));
        cp_.setMultWinningHappiness(new Rate(_e.getMultWinningHappiness()));
        cp_.setMultWinningEv(new Rate(_e.getMultWinningEv()));
        cp_.setMultWinningExp(new Rate(_e.getMultWinningExp()));
        cp_.setMultPower(_e.getMultPower());
        cp_.setMultDamage(_e.getMultDamage());
        cp_.setMultDrainedHp(new Rate(_e.getMultDrainedHp()));
        cp_.setDamageRecoil(new Rate(_e.getDamageRecoil()));
        cp_.setMultStatRank(new IdMap<Statistic,Byte>(_e.getMultStatRank()));
        cp_.setMultStatPokemonRank(copyStatisticPokemons(_e.getMultStatPokemonRank()));
        cp_.setMultStat(new IdMap<Statistic,String>(_e.getMultStat()));
        cp_.setIncreasingMaxNbRoundGlobalMove(new StringMap<Short>(_e.getIncreasingMaxNbRoundGlobalMove()));
        cp_.setIncreasingMaxNbRoundTeamMove(new StringMap<Short>(_e.getIncreasingMaxNbRoundTeamMove()));
        cp_.setImmuMoves(new StringList(_e.getImmuMoves()));
        cp_.setHatching(new StringList(_e.getHatching()));
        cp_.setImmuTypes(new StringList(_e.getImmuTypes()));
        cp_.setImmuWeather(new StringList(_e.getImmuWeather()));
        cp_.setBoostStatisSuperEff(new IdMap<Statistic,Byte>(_e.getBoostStatisSuperEff()));
        cp_.setBoostStatisTypes(copyStringMapMapStatistic(_e.getBoostStatisTypes()));
        cp_.setEffectEndRound(copyEffectEndRoundList(_e.getEffectEndRound()));
        cp_.setEffectSending(copyEffectWhileSendingWithStatisticList(_e.getEffectSending()));
        copyItem(cp_,_e);
        return cp_;
    }

    public static Repel copyRepel(Repel _e){
        Repel cp_ = new Repel();
        cp_.setSteps(_e.getSteps());
        copyItem(cp_,_e);
        return cp_;
    }

    public static SellingItem copySellingItem(SellingItem _e){
        SellingItem cp_ = new SellingItem();
        copyItem(cp_,_e);
        return cp_;
    }

    public static DamagingMoveData copyDamagingMoveData(DamagingMoveData _e){
        DamagingMoveData cp_ = new DamagingMoveData();
        cp_.setCategory(_e.getCategory());
        cp_.setDirect(_e.isDirect());
        cp_.setCannotKo(_e.getCannotKo());
        cp_.setStoppableMoveKoSingle(_e.getStoppableMoveKoSingle());
        copyMoveData(cp_,_e);
        return cp_;
    }

    public static void copyEffect(Effect _cp,Effect _e){
        _cp.setTargetChoice(_e.getTargetChoice());
        _cp.setFail(_e.getFail());
        _cp.setRequiredSuccessfulEffects(new Ints(_e.getRequiredSuccessfulEffects()));
    }
    public static Effect copyEffect(Effect _e){
        if (_e instanceof EffectAccuracy){
            return copyEffectAccuracy((EffectAccuracy)_e);
        }
        if (_e instanceof EffectAlly){
            return copyEffectAlly((EffectAlly)_e);
        }
        if (_e instanceof EffectBatonPass){
            return copyEffectBatonPass((EffectBatonPass)_e);
        }
        if (_e instanceof EffectClone){
            return copyEffectClone((EffectClone)_e);
        }
        if (_e instanceof EffectCommonStatistics){
            return copyEffectCommonStatistics((EffectCommonStatistics)_e);
        }
        if (_e instanceof EffectCopyFighter){
            return copyEffectCopyFighter((EffectCopyFighter)_e);
        }
        if (_e instanceof EffectCopyMove){
            return copyEffectCopyMove((EffectCopyMove)_e);
        }
        if (_e instanceof EffectCounterAttack){
            return copyEffectCounterAttack((EffectCounterAttack)_e);
        }
        if (_e instanceof EffectDamage){
            return copyEffectDamage((EffectDamage)_e);
        }
        if (_e instanceof EffectDamageRate){
            return copyEffectDamageRate((EffectDamageRate)_e);
        }
        return copyEffect2(_e);
    }

    private static Effect copyEffect2(Effect _e) {
        if (_e instanceof EffectEndRound){
            return copyEffectEndRound((EffectEndRound) _e);
        }
        if (_e instanceof EffectFullHpRate){
            return copyEffectFullHpRate((EffectFullHpRate) _e);
        }
        if (_e instanceof EffectGlobal){
            return copyEffectGlobal((EffectGlobal) _e);
        }
        if (_e instanceof EffectInvoke){
            return copyEffectInvoke((EffectInvoke) _e);
        }
        if (_e instanceof EffectMultSufferedMovePower){
            return copyEffectMultSufferedMovePower((EffectMultSufferedMovePower) _e);
        }
        if (_e instanceof EffectMultUsedMovePower){
            return copyEffectMultUsedMovePower((EffectMultUsedMovePower) _e);
        }
        if (_e instanceof EffectOrder){
            return copyEffectOrder((EffectOrder) _e);
        }
        if (_e instanceof EffectProtectFromTypes){
            return copyEffectProtectFromTypes((EffectProtectFromTypes) _e);
        }
        if (_e instanceof EffectProtection){
            return copyEffectProtection((EffectProtection) _e);
        }
        if (_e instanceof EffectRemainedHpRate){
            return copyEffectRemainedHpRate((EffectRemainedHpRate) _e);
        }
        if (_e instanceof EffectRestriction){
            return copyEffectRestriction((EffectRestriction) _e);
        }
        if (_e instanceof EffectStatistic){
            return copyEffectStatistic((EffectStatistic) _e);
        }
        return copyEffect1(_e);
    }

    private static Effect copyEffect1(Effect _e) {
        if (_e instanceof EffectStatus){
            return copyEffectStatus((EffectStatus) _e);
        }
        if (_e instanceof EffectSwitchAbilities){
            return copyEffectSwitchAbilities((EffectSwitchAbilities) _e);
        }
        if (_e instanceof EffectSwitchItems){
            return copyEffectSwitchItems((EffectSwitchItems) _e);
        }
        if (_e instanceof EffectSwitchMoveTypes){
            return copyEffectSwitchMoveTypes((EffectSwitchMoveTypes) _e);
        }
        if (_e instanceof EffectSwitchPointView){
            return copyEffectSwitchPointView((EffectSwitchPointView) _e);
        }
        if (_e instanceof EffectSwitchPosition){
            return copyEffectSwitchPosition((EffectSwitchPosition) _e);
        }
        if (_e instanceof EffectSwitchTypes){
            return copyEffectSwitchTypes((EffectSwitchTypes) _e);
        }
        if (_e instanceof EffectTeam){
            return copyEffectTeam((EffectTeam) _e);
        }
        if (_e instanceof EffectTeamWhileSendFoe){
            return copyEffectTeamWhileSendFoe((EffectTeamWhileSendFoe) _e);
        }
        if (_e instanceof EffectUnprotectFromTypes){
            return copyEffectUnprotectFromTypes((EffectUnprotectFromTypes) _e);
        }
        if (_e instanceof EffectVarPP){
            return copyEffectVarPP((EffectVarPP) _e);
        }
        return copyEffectWinMoney((EffectWinMoney) _e);
    }

    public static EffectAccuracy copyEffectAccuracy(EffectAccuracy _e){
        EffectAccuracy cp_ = new EffectAccuracy();
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectAlly copyEffectAlly(EffectAlly _e){
        EffectAlly cp_ = new EffectAlly();
        cp_.setMultAllyDamage(new Rate(_e.getMultAllyDamage()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectBatonPass copyEffectBatonPass(EffectBatonPass _e){
        EffectBatonPass cp_ = new EffectBatonPass();
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectClone copyEffectClone(EffectClone _e){
        EffectClone cp_ = new EffectClone();
        cp_.setHpRateClone(new Rate(_e.getHpRateClone()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectCombo copyEffectCombo(EffectCombo _e){
        EffectCombo cp_ = new EffectCombo();
        cp_.setMultEvtRateSecEff(new Rate(_e.getMultEvtRateSecEff()));
        cp_.setRepeatedRoundsLaw(_e.getRepeatedRoundsLaw().copy());
        cp_.setRankIncrementNbRound(_e.getRankIncrementNbRound());
        cp_.setEffectEndRound(copyListEffectEndRoundFoe(_e.getEffectEndRound()));
        cp_.setTeamMove(copyListEffectTeam(_e.getTeamMove()));
        return cp_;
    }

    public static CustList<EffectEndRoundFoe> copyListEffectEndRoundFoe(CustList<EffectEndRoundFoe> _e) {
        CustList<EffectEndRoundFoe> cp_= new CustList<EffectEndRoundFoe>();
        for (EffectEndRoundFoe f:_e){
            cp_.add(copyEffectEndRoundFoe(f));
        }
        return cp_;
    }

    public static CustList<EffectTeam> copyListEffectTeam(CustList<EffectTeam> _e) {
        CustList<EffectTeam> cp_= new CustList<EffectTeam>();
        for (EffectTeam f:_e){
            cp_.add(copyEffectTeam(f));
        }
        return cp_;
    }

    public static EffectCommonStatistics copyEffectCommonStatistics(EffectCommonStatistics _e){
        EffectCommonStatistics cp_ = new EffectCommonStatistics();
        cp_.setCommonValue(new IdMap<Statistic,String>(_e.getCommonValue()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectCopyFighter copyEffectCopyFighter(EffectCopyFighter _e){
        EffectCopyFighter cp_ = new EffectCopyFighter();
        cp_.setPpForMoves(_e.getPpForMoves());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectCopyMove copyEffectCopyMove(EffectCopyMove _e){
        EffectCopyMove cp_ = new EffectCopyMove();
        cp_.setCopyingMoveForUser(_e.getCopyingMoveForUser());
        cp_.setCopyingMoveForUserDef(_e.getCopyingMoveForUserDef());
        cp_.setMovesNotToBeCopied(new StringList(_e.getMovesNotToBeCopied()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectCounterAttack copyEffectCounterAttack(EffectCounterAttack _e){
        EffectCounterAttack cp_ = new EffectCounterAttack();
        cp_.setSufferingDamageTypes(copyStringMapRate(_e.getSufferingDamageTypes()));
        cp_.setDroppedStatDirectMove(new IdMap<Statistic,Byte>(_e.getDroppedStatDirectMove()));
        cp_.setSufferingDamageDirectMove(new Rate(_e.getSufferingDamageDirectMove()));
        cp_.setProtectFail(_e.getProtectFail());
        cp_.setCounterFail(_e.getCounterFail());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectDamage copyEffectDamage(EffectDamage _e){
        EffectDamage cp_ = new EffectDamage();
        cp_.setChRate(_e.getChRate());
        cp_.setConstDamage(_e.getConstDamage());
        cp_.setDamageLaw(copyMonteCarloString(_e.getDamageLaw()));
        cp_.setMultDamageAgainst(copyStringMapRate(_e.getMultDamageAgainst()));
        cp_.setChLaw(_e.getChLaw().copy());
        cp_.setHitsLaw(_e.getHitsLaw().copy());
        cp_.setPower(_e.getPower());
        cp_.setRandMax(_e.getRandMax());
        cp_.setSummingUserTeamOkFighter(_e.getSummingUserTeamOkFighter());
        cp_.setIgnVarStatTargetPos(new IdList<Statistic>(_e.getIgnVarStatTargetPos()));
        cp_.setIgnVarStatUserNeg(new IdList<Statistic>(_e.getIgnVarStatUserNeg()));
        cp_.setUserAttack(_e.isUserAttack());
        cp_.setStatisAtt(_e.getStatisAtt());
        cp_.setTargetDefense(_e.isTargetDefense());
        cp_.setStatisDef(_e.getStatisDef());
        cp_.setBoostStatisOnceKoFoe(new IdMap<Statistic,Byte>(_e.getBoostStatisOnceKoFoe()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectDamageRate copyEffectDamageRate(EffectDamageRate _e){
        EffectDamageRate cp_ = new EffectDamageRate();
        cp_.setRateDamage(new Rate(_e.getRateDamage()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static void copyEffectEndRound(EffectEndRound _cp,EffectEndRound _e){
        _cp.setFailEndRound(_e.getFailEndRound());
        _cp.setEndRoundRank(_e.getEndRoundRank());
        copyEffect(_cp,_e);
    }
    public static EffectEndRound copyEffectEndRound(EffectEndRound _e){
        if (_e instanceof EffectEndRoundFoe){
            return copyEffectEndRoundFoe((EffectEndRoundFoe)_e);
        }
        if (_e instanceof EffectEndRoundGlobal){
            return copyEffectEndRoundGlobal((EffectEndRoundGlobal)_e);
        }
        if (_e instanceof EffectEndRoundIndividual){
            return copyEffectEndRoundIndividual((EffectEndRoundIndividual)_e);
        }
        if (_e instanceof EffectEndRoundMultiRelation){
            return copyEffectEndRoundMultiRelation((EffectEndRoundMultiRelation)_e);
        }
        if (_e instanceof EffectEndRoundPositionRelation){
            return copyEffectEndRoundPositionRelation((EffectEndRoundPositionRelation)_e);
        }
        if (_e instanceof EffectEndRoundPositionTargetRelation){
            return copyEffectEndRoundPositionTargetRelation((EffectEndRoundPositionTargetRelation)_e);
        }
        if (_e instanceof EffectEndRoundSingleRelation){
            return copyEffectEndRoundSingleRelation((EffectEndRoundSingleRelation)_e);
        }
        if (_e instanceof EffectEndRoundStatus){
            return copyEffectEndRoundStatus((EffectEndRoundStatus)_e);
        }
        return copyEffectEndRoundTeam((EffectEndRoundTeam)_e);
    }

    public static EffectEndRoundFoe copyEffectEndRoundFoe(EffectEndRoundFoe _e){
        EffectEndRoundFoe cp_ = new EffectEndRoundFoe();
        cp_.setInflictedRateHpTarget(new Rate(_e.getInflictedRateHpTarget()));
        copyEffectEndRound(cp_,_e);
        return cp_;
    }

    public static EffectEndRoundGlobal copyEffectEndRoundGlobal(EffectEndRoundGlobal _e){
        EffectEndRoundGlobal cp_ = new EffectEndRoundGlobal();
        copyEffectEndRound(cp_,_e);
        return cp_;
    }

    public static EffectEndRoundIndividual copyEffectEndRoundIndividual(EffectEndRoundIndividual _e){
        EffectEndRoundIndividual cp_ = new EffectEndRoundIndividual();
        cp_.setDeleteAllStatus(new Rate(_e.getDeleteAllStatus()));
        cp_.setRecoilDamage(new Rate(_e.getRecoilDamage()));
        cp_.setHealHp(new Rate(_e.getHealHp()));
        cp_.setHealHpByOwnerTypes(copyStringMapRate(_e.getHealHpByOwnerTypes()));
        cp_.setMultDamageStatus(copyStringMapRate(_e.getMultDamageStatus()));
        cp_.setUserStatusEndRound(_e.getUserStatusEndRound());
        copyEffectEndRound(cp_,_e);
        return cp_;
    }

    public static EffectEndRoundMultiRelation copyEffectEndRoundMultiRelation(EffectEndRoundMultiRelation _e){
        EffectEndRoundMultiRelation cp_ = new EffectEndRoundMultiRelation();
        cp_.setDamageByStatus(copyStringMapRate(_e.getDamageByStatus()));
        copyEffectEndRound(cp_,_e);
        return cp_;
    }

    public static EffectEndRoundPositionRelation copyEffectEndRoundPositionRelation(EffectEndRoundPositionRelation _e){
        EffectEndRoundPositionRelation cp_ = new EffectEndRoundPositionRelation();
        cp_.setHealHp(new Rate(_e.getHealHp()));
        copyEffectEndRound(cp_,_e);
        return cp_;
    }

    public static EffectEndRoundPositionTargetRelation copyEffectEndRoundPositionTargetRelation(EffectEndRoundPositionTargetRelation _e){
        EffectEndRoundPositionTargetRelation cp_ = new EffectEndRoundPositionTargetRelation();
        copyEffectEndRound(cp_,_e);
        return cp_;
    }

    public static EffectEndRoundSingleRelation copyEffectEndRoundSingleRelation(EffectEndRoundSingleRelation _e){
        EffectEndRoundSingleRelation cp_ = new EffectEndRoundSingleRelation();
        cp_.setRateDamageFunctionOfNbRounds(copyLongMapRate(_e.getRateDamageFunctionOfNbRounds()));
        cp_.setLawForEnablingEffect(_e.getLawForEnablingEffect().copy());
        copyEffectEndRound(cp_,_e);
        return cp_;
    }

    public static EffectEndRoundSingleStatus copyEffectEndRoundSingleStatus(EffectEndRoundSingleStatus _e){
        EffectEndRoundSingleStatus cp_ = new EffectEndRoundSingleStatus();
        cp_.setIncrementingDamageByRounds(_e.isIncrementingDamageByRounds());
        copyEffectEndRoundStatus(cp_,_e);
        return cp_;
    }

    public static void copyEffectEndRoundStatus(EffectEndRoundStatus _cp,EffectEndRoundStatus _e){
        _cp.setInflictedRateHpTarget(new Rate(_e.getInflictedRateHpTarget()));
        copyEffectEndRound(_cp,_e);
    }
    public static EffectEndRoundStatus copyEffectEndRoundStatus(EffectEndRoundStatus _e){
        if (_e instanceof EffectEndRoundSingleStatus){
            return copyEffectEndRoundSingleStatus((EffectEndRoundSingleStatus)_e);
        }
        return copyEffectEndRoundStatusRelation((EffectEndRoundStatusRelation)_e);
    }

    public static EffectEndRoundStatusRelation copyEffectEndRoundStatusRelation(EffectEndRoundStatusRelation _e){
        EffectEndRoundStatusRelation cp_ = new EffectEndRoundStatusRelation();
        cp_.setThievedHpRateTargetToUser(new Rate(_e.getThievedHpRateTargetToUser()));
        copyEffectEndRoundStatus(cp_,_e);
        return cp_;
    }

    public static EffectEndRoundTeam copyEffectEndRoundTeam(EffectEndRoundTeam _e){
        EffectEndRoundTeam cp_ = new EffectEndRoundTeam();
        cp_.setDeleteAllStatus(new Rate(_e.getDeleteAllStatus()));
        cp_.setDeleteAllStatusAlly(new Rate(_e.getDeleteAllStatusAlly()));
        copyEffectEndRound(cp_,_e);
        return cp_;
    }

    public static EffectFullHpRate copyEffectFullHpRate(EffectFullHpRate _e){
        EffectFullHpRate cp_ = new EffectFullHpRate();
        cp_.setLeftUserHp(new Rate(_e.getLeftUserHp()));
        cp_.setRestoredHp(_e.getRestoredHp());
        cp_.setClosestFoeDamageRateHp(new Rate(_e.getClosestFoeDamageRateHp()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectGlobal copyEffectGlobal(EffectGlobal _e){
        EffectGlobal cp_ = new EffectGlobal();
        cp_.setWeather(_e.getWeather());
        cp_.setCanceledIfUsed(_e.getCanceledIfUsed());
        cp_.setReverseOrderOfSortBySpeed(_e.getReverseOrderOfSortBySpeed());
        cp_.setPuttingKo(_e.getPuttingKo());
        cp_.setMultAccuracy(new Rate(_e.getMultAccuracy()));
        cp_.setUnusableItem(_e.getUnusableItem());
        cp_.setPreventStatus(new StringList(_e.getPreventStatus()));
        cp_.setImmuneTypes(new StringList(_e.getImmuneTypes()));
        cp_.setDamageEndRound(new Rate(_e.getDamageEndRound()));
        cp_.setHealingEndRound(new Rate(_e.getHealingEndRound()));
        cp_.setHealingEndRoundGround(new Rate(_e.getHealingEndRoundGround()));
        cp_.setEfficiencyMoves(copyTypesDuos(_e.getEfficiencyMoves()));
        cp_.setDisableImmuAgainstTypes(new StringList(_e.getDisableImmuAgainstTypes()));
        cp_.setCancelProtectingAbilities(new StringList(_e.getCancelProtectingAbilities()));
        cp_.setUnusableMoves(new StringList(_e.getUnusableMoves()));
        cp_.setMultDamagePrepaRound(copyStringMapRate(_e.getMultDamagePrepaRound()));
        cp_.setMovesUsedByTargetedFighters(new StringList(_e.getMovesUsedByTargetedFighters()));
        cp_.setMultEffectLovingAlly(new Rate(_e.getMultEffectLovingAlly()));
        cp_.setMultPowerMoves(copyStringMapRate(_e.getMultPowerMoves()));
        cp_.setMultStatIfContainsType(copyStatisticTypeRate(_e.getMultStatIfContainsType()));
        cp_.setCancelEffects(new StringList(_e.getCancelEffects()));
        cp_.setMultDamageTypesMoves(copyStringMapRate(_e.getMultDamageTypesMoves()));
        cp_.setCancelChgtStat(new IdList<Statistic>(_e.getCancelChgtStat()));
        cp_.setInvokedMoveTerrain(_e.getInvokedMoveTerrain());
        cp_.setChangedTypesTerrain(new StringList(_e.getChangedTypesTerrain()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectInvoke copyEffectInvoke(EffectInvoke _e){
        EffectInvoke cp_ = new EffectInvoke();
        cp_.setMoveFctEnv(new IdMap<EnvironmentType,String>(_e.getMoveFctEnv()));
        cp_.setInvokingMoveButUser(_e.getInvokingMoveButUser());
        cp_.setInvokingTargetChosenMove(_e.getInvokingTargetChosenMove());
        cp_.setInvokingUserMoveWhileSleep(_e.getInvokingUserMoveWhileSleep());
        cp_.setInvokingAllyMove(_e.getInvokingAllyMove());
        cp_.setInvokingTargetSuccesfulMove(_e.getInvokingTargetSuccesfulMove());
        cp_.setInvokingSufferedMove(_e.getInvokingSufferedMove());
        cp_.setInvokingMoveByUserTypes(new StringMap<String>(_e.getInvokingMoveByUserTypes()));
        cp_.setMovesNotToBeInvoked(new StringList(_e.getMovesNotToBeInvoked()));
        cp_.setRateInvokationMove(new Rate(_e.getRateInvokationMove()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static void copyEffectMultMovePower(EffectMultMovePower _cp, EffectMultMovePower _e){
        _cp.setMultMovePowerFctType(copyStringMapRate(_e.getMultMovePowerFctType()));
        copyEffect(_cp,_e);
    }

    public static EffectMultSufferedMovePower copyEffectMultSufferedMovePower(EffectMultSufferedMovePower _e){
        EffectMultSufferedMovePower cp_ = new EffectMultSufferedMovePower();
        copyEffectMultMovePower(cp_,_e);
        return cp_;
    }

    public static EffectMultUsedMovePower copyEffectMultUsedMovePower(EffectMultUsedMovePower _e){
        EffectMultUsedMovePower cp_ = new EffectMultUsedMovePower();
        copyEffectMultMovePower(cp_,_e);
        return cp_;
    }

    public static EffectOrder copyEffectOrder(EffectOrder _e){
        EffectOrder cp_ = new EffectOrder();
        cp_.setTargetAttacksLast(_e.getTargetAttacksLast());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectProtectFromTypes copyEffectProtectFromTypes(EffectProtectFromTypes _e){
        EffectProtectFromTypes cp_ = new EffectProtectFromTypes();
        cp_.setImmuAgainstTypes(new StringList(_e.getImmuAgainstTypes()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectProtection copyEffectProtection(EffectProtection _e){
        EffectProtection cp_ = new EffectProtection();
        cp_.setProtSingle(_e.getProtSingle());
        cp_.setProtSingleAgainstKo(new Rate(_e.getProtSingleAgainstKo()));
        cp_.setProtTeamAgainstMultTargets(_e.getProtTeamAgainstMultTargets());
        cp_.setProtTeamAgainstPrio(_e.getProtTeamAgainstPrio());
        cp_.setProtTeamAgainstStatusMoves(_e.isProtTeamAgainstStatusMoves());
        cp_.setProtTeamAgainstDamageMoves(_e.isProtTeamAgainstDamageMoves());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectRemainedHpRate copyEffectRemainedHpRate(EffectRemainedHpRate _e){
        EffectRemainedHpRate cp_ = new EffectRemainedHpRate();
        cp_.setRateHp(new Rate(_e.getRateHp()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectRestriction copyEffectRestriction(EffectRestriction _e){
        EffectRestriction cp_ = new EffectRestriction();
        cp_.setForbidTargetUsingItem(_e.getForbidTargetUsingItem());
        cp_.setChoiceRestriction(_e.getChoiceRestriction());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectStatistic copyEffectStatistic(EffectStatistic _e){
        EffectStatistic cp_ = new EffectStatistic();
        cp_.setStatisVarRank(new IdMap<Statistic,Byte>(_e.getStatisVarRank()));
        cp_.setLocalFailStatis(new IdMap<Statistic,String>(_e.getLocalFailStatis()));
        cp_.setEvtRate(new Rate(_e.getEvtRate()));
        cp_.setCopyBoost(new IdList<Statistic>(_e.getCopyBoost()));
        cp_.setSwapBoostStatis(new IdList<Statistic>(_e.getSwapBoostStatis()));
        cp_.setLocalFailSwapBoostStatis(new IdMap<Statistic,String>(_e.getLocalFailSwapBoostStatis()));
        cp_.setLawBoost(copyMonteCarloEnumStatistic(_e.getLawBoost()));
        cp_.setCancelLowStat(new IdList<Statistic>(_e.getCancelLowStat()));
        cp_.setCancelChgtStat(new IdList<Statistic>(_e.getCancelChgtStat()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectStatus copyEffectStatus(EffectStatus _e){
        EffectStatus cp_ = new EffectStatus();
        cp_.setLawStatus(copyMonteCarloString(_e.getLawStatus()));
        cp_.setDeletedStatus(new StringList(_e.getDeletedStatus()));
        cp_.setLocalFailStatus(new StringMap<String>(_e.getLocalFailStatus()));
        cp_.setKoUserHealSubst(_e.getKoUserHealSubst());
        cp_.setStatusFromUser(_e.getStatusFromUser());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectSwitchAbilities copyEffectSwitchAbilities(EffectSwitchAbilities _e){
        EffectSwitchAbilities cp_ = new EffectSwitchAbilities();
        cp_.setExchangeAbility(_e.getExchangeAbility());
        cp_.setConstAbility(_e.getConstAbility());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectSwitchItems copyEffectSwitchItems(EffectSwitchItems _e){
        EffectSwitchItems cp_ = new EffectSwitchItems();
        cp_.setMoveObject(_e.getMoveObject());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectSwitchMoveTypes copyEffectSwitchMoveTypes(EffectSwitchMoveTypes _e){
        EffectSwitchMoveTypes cp_ = new EffectSwitchMoveTypes();
        cp_.setChangeTypes(new StringMap<String>(_e.getChangeTypes()));
        cp_.setReplacingTypes(new StringList(_e.getReplacingTypes()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectSwitchPointView copyEffectSwitchPointView(EffectSwitchPointView _e){
        EffectSwitchPointView cp_ = new EffectSwitchPointView();
        cp_.setPointViewChangement(_e.getPointViewChangement());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectSwitchPosition copyEffectSwitchPosition(EffectSwitchPosition _e){
        EffectSwitchPosition cp_ = new EffectSwitchPosition();
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectSwitchTypes copyEffectSwitchTypes(EffectSwitchTypes _e){
        EffectSwitchTypes cp_ = new EffectSwitchTypes();
        cp_.setChgtTypeByEnv(new IdMap<EnvironmentType,String>(_e.getChgtTypeByEnv()));
        cp_.setConstValuesType(_e.getConstValuesType());
        cp_.setExchangeTypes(_e.getExchangeTypes());
        cp_.setConstTypes(new StringList(_e.getConstTypes()));
        cp_.setAddedTypes(new StringList(_e.getAddedTypes()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectTeam copyEffectTeam(EffectTeam _e){
        EffectTeam cp_ = new EffectTeam();
        cp_.setForbiddingHealing(_e.getForbiddingHealing());
        cp_.setForbiddenBoost(new IdList<Statistic>(_e.getForbiddenBoost()));
        cp_.setUnusableMoves(new StringList(_e.getUnusableMoves()));
        cp_.setCancelChgtStatFoeTeam(new IdList<Statistic>(_e.getCancelChgtStatFoeTeam()));
        cp_.setCancelChgtStatTeam(new IdList<Statistic>(_e.getCancelChgtStatTeam()));
        cp_.setMultDamage(copyCategoryMults(_e.getMultDamage()));
        cp_.setMultStatistic(new IdMap<Statistic,Rate>(_e.getMultStatistic()));
        cp_.setMultStatisticFoe(new IdMap<Statistic,Rate>(_e.getMultStatisticFoe()));
        cp_.setProtectAgainstLowStat(new IdList<Statistic>(_e.getProtectAgainstLowStat()));
        cp_.setProtectAgainstCh(_e.getProtectAgainstCh());
        cp_.setProtectAgainstStatus(new StringList(_e.getProtectAgainstStatus()));
        cp_.setDisableFoeTeamEffects(new StringList(_e.getDisableFoeTeamEffects()));
        cp_.setDisableFoeTeamStatus(new StringList(_e.getDisableFoeTeamStatus()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectTeamWhileSendFoe copyEffectTeamWhileSendFoe(EffectTeamWhileSendFoe _e){
        EffectTeamWhileSendFoe cp_ = new EffectTeamWhileSendFoe();
        cp_.setFailSending(_e.getFailSending());
        ShortMap<String> s_ = new ShortMap<String>();
        s_.addAllEntries(_e.getStatusByNbUses());
        cp_.setStatusByNbUses(s_);
        cp_.setDeletedByFoeTypes(new StringList(_e.getDeletedByFoeTypes()));
        cp_.setDamageRateAgainstFoe(_e.getDamageRateAgainstFoe());
        cp_.setStatistics(new IdMap<Statistic,Byte>(_e.getStatistics()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectUnprotectFromTypes copyEffectUnprotectFromTypes(EffectUnprotectFromTypes _e){
        EffectUnprotectFromTypes cp_ = new EffectUnprotectFromTypes();
        cp_.setTypes(copyListTypesDuo(_e.getTypes()));
        cp_.setDisableImmuAgainstTypes(new StringList(_e.getDisableImmuAgainstTypes()));
        cp_.setDisableImmuFromMoves(new StringList(_e.getDisableImmuFromMoves()));
        cp_.setAttackTargetWithTypes(new StringList(_e.getAttackTargetWithTypes()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectVarPP copyEffectVarPP(EffectVarPP _e){
        EffectVarPP cp_ = new EffectVarPP();
        cp_.setDeletePp(_e.getDeletePp());
        copyEffect(cp_,_e);
        return cp_;
    }

    public static EffectWinMoney copyEffectWinMoney(EffectWinMoney _e){
        EffectWinMoney cp_ = new EffectWinMoney();
        cp_.setWinningRateBySumTargetUser(new Rate(_e.getWinningRateBySumTargetUser()));
        copyEffect(cp_,_e);
        return cp_;
    }

    public static void copyMoveData(MoveData _cp,MoveData _e){
        _cp.setPp(_e.getPp());
        _cp.setTypes(new StringList(_e.getTypes()));
        _cp.setBoostedTypes(new StringList(_e.getBoostedTypes()));
        _cp.setPriority(_e.getPriority());
        _cp.setAccuracy(_e.getAccuracy());
        _cp.setEffects(copyListEffect(_e.getEffects()));
        _cp.setNbPrepaRound(_e.getNbPrepaRound());
        _cp.setDisappearBeforeUse(_e.getDisappearBeforeUse());
        _cp.setRepeatRoundLaw(_e.getRepeatRoundLaw().copy());
        _cp.setRankIncrementNbRound(_e.getRankIncrementNbRound());
        _cp.setRechargeRound(_e.getRechargeRound());
        _cp.setConstUserChoice(_e.getConstUserChoice());
        _cp.setStoppableMoveSolo(_e.getStoppableMoveSolo());
        _cp.setStoppableMoveMulti(_e.getStoppableMoveMulti());
        _cp.setStoppableMovePrio(_e.getStoppableMovePrio());
        _cp.setSecEffectIfNoDamage(_e.getSecEffectIfNoDamage());
        _cp.setSecEffectsByItem(copyStringMapInts(_e.getSecEffectsByItem()));
        _cp.setIgnVarAccurUserNeg(_e.getIgnVarAccurUserNeg());
        _cp.setIgnVarEvasTargetPos(_e.getIgnVarEvasTargetPos());
        _cp.setAchieveDisappearedPkUsingMove(new StringList(_e.getAchieveDisappearedPkUsingMove()));
        _cp.setSwitchType(_e.getSwitchType());
        _cp.setTypesByOwnedItem(new StringMap<String>(_e.getTypesByOwnedItem()));
        _cp.setTypesByWeather(new StringMap<String>(_e.getTypesByWeather()));
        _cp.setTargetChoice(_e.getTargetChoice());
        _cp.setDeletedStatus(new StringList(_e.getDeletedStatus()));
        _cp.setRequiredStatus(new StringList(_e.getRequiredStatus()));
    }
    public static CustList<Effect> copyListEffect(CustList<Effect> _e) {
        CustList<Effect> cp_= new CustList<Effect>();
        for (Effect f:_e){
            cp_.add(copyEffect(f));
        }
        return cp_;
    }
    public static MoveData copyMoveData(MoveData _e){
        if (_e instanceof DamagingMoveData){
            return copyDamagingMoveData((DamagingMoveData)_e);
        }
        return copyStatusMoveData((StatusMoveData)_e);
    }

    public static StatusMoveData copyStatusMoveData(StatusMoveData _e){
        StatusMoveData cp_ = new StatusMoveData();
        cp_.setThievableMove(_e.getThievableMove());
        cp_.setCounterableMove(_e.getCounterableMove());
        copyMoveData(cp_,_e);
        return cp_;
    }

    public static Evolution copyEvolution(Evolution _e){
        if (_e instanceof EvolutionItem){
            return copyEvolutionItem((EvolutionItem)_e);
        }
        if (_e instanceof EvolutionLevelGender){
            return copyEvolutionLevelGender((EvolutionLevelGender)_e);
        }
        if (_e instanceof EvolutionLevelSimple){
            return copyEvolutionLevelSimple((EvolutionLevelSimple)_e);
        }
        if (_e instanceof EvolutionMove){
            return copyEvolutionMove((EvolutionMove)_e);
        }
        if (_e instanceof EvolutionMoveType){
            return copyEvolutionMoveType((EvolutionMoveType)_e);
        }
        if (_e instanceof EvolutionStoneGender){
            return copyEvolutionStoneGender((EvolutionStoneGender)_e);
        }
        if (_e instanceof EvolutionStoneSimple){
            return copyEvolutionStoneSimple((EvolutionStoneSimple)_e);
        }
        if (_e instanceof EvolutionTeam){
            return copyEvolutionTeam((EvolutionTeam)_e);
        }
        return copyEvolutionHappiness();
    }

    public static EvolutionHappiness copyEvolutionHappiness(){
        return new EvolutionHappiness();
    }

    public static EvolutionItem copyEvolutionItem(EvolutionItem _e){
        EvolutionItem cp_ = new EvolutionItem();
        cp_.setItem(_e.getItem());
        return cp_;
    }

    public static void copyEvolutionLevel(EvolutionLevel _cp,EvolutionLevel _e){
        _cp.setLevel(_e.getLevel());
    }

    public static EvolutionLevelGender copyEvolutionLevelGender(EvolutionLevelGender _e){
        EvolutionLevelGender cp_ = new EvolutionLevelGender();
        cp_.setGender(_e.getGender());
        copyEvolutionLevel(cp_,_e);
        return cp_;
    }

    public static EvolutionLevelSimple copyEvolutionLevelSimple(EvolutionLevelSimple _e){
        EvolutionLevelSimple cp_ = new EvolutionLevelSimple();
        copyEvolutionLevel(cp_,_e);
        return cp_;
    }

    public static EvolutionMove copyEvolutionMove(EvolutionMove _e){
        EvolutionMove cp_ = new EvolutionMove();
        cp_.setMove(_e.getMove());
        return cp_;
    }

    public static EvolutionMoveType copyEvolutionMoveType(EvolutionMoveType _e){
        EvolutionMoveType cp_ = new EvolutionMoveType();
        cp_.setType(_e.getType());
        return cp_;
    }

    public static void copyEvolutionStone(EvolutionStone _cp,EvolutionStone _e){
        _cp.setStone(_e.getStone());
    }

    public static EvolutionStoneGender copyEvolutionStoneGender(EvolutionStoneGender _e){
        EvolutionStoneGender cp_ = new EvolutionStoneGender();
        cp_.setGender(_e.getGender());
        copyEvolutionStone(cp_,_e);
        return cp_;
    }

    public static EvolutionStoneSimple copyEvolutionStoneSimple(EvolutionStoneSimple _e){
        EvolutionStoneSimple cp_ = new EvolutionStoneSimple();
        copyEvolutionStone(cp_,_e);
        return cp_;
    }

    public static EvolutionTeam copyEvolutionTeam(EvolutionTeam _e){
        EvolutionTeam cp_ = new EvolutionTeam();
        cp_.setPokemon(_e.getPokemon());
        return cp_;
    }

    public static PokemonData copyPokemonData(PokemonData _e){
        PokemonData cp_ = new PokemonData();
        cp_.setWeight(new Rate(_e.getWeight()));
        cp_.setTypes(new StringList(_e.getTypes()));
        cp_.setStatistics(new IdMap<Statistic,StatBaseEv>(_e.getStatistics()));
        cp_.setLevMoves(copyListLevelMove(_e.getLevMoves()));
        cp_.setGenderRep(_e.getGenderRep());
        cp_.setAbilities(new StringList(_e.getAbilities()));
        cp_.setMoveTutors(new StringList(_e.getMoveTutors()));
        cp_.setHiddenMoves(new Shorts(_e.getHiddenMoves()));
        cp_.setTechnicalMoves(new Shorts(_e.getTechnicalMoves()));
        cp_.setBaseEvo(_e.getBaseEvo());
        cp_.setEvolutions(copyMapEvolution(_e.getEvolutions()));
        cp_.setCatchingRate(_e.getCatchingRate());
        cp_.setHeight(new Rate(_e.getHeight()));
        cp_.setExpEvo(_e.getExpEvo());
        cp_.setExpRate(_e.getExpRate());
        cp_.setEggGroups(new StringList(_e.getEggGroups()));
        cp_.setHatchingSteps(new LgInt(_e.getHatchingSteps()));
        cp_.setHappiness(_e.getHappiness());
        cp_.setHappinessHatch(_e.getHappinessHatch());
        return cp_;
    }
    public static StringMap<Evolution> copyMapEvolution(StringMap<Evolution> _e) {
        StringMap<Evolution> cp_= new StringMap<Evolution>();
        for (EntryCust<String,Evolution> f: _e.entryList()){
            cp_.addEntry(f.getKey(),copyEvolution(f.getValue()));
        }
        return cp_;
    }
    public static EffectPartnerStatus copyEffectPartnerStatus(EffectPartnerStatus _e){
        EffectPartnerStatus cp_ = new EffectPartnerStatus();
        cp_.setMultDamageAgainstFoe(new Rate(_e.getMultDamageAgainstFoe()));
        cp_.setWeddingAlly(_e.getWeddingAlly());
        cp_.setRestoredHpRateLovedAlly(new Rate(_e.getRestoredHpRateLovedAlly()));
        return cp_;
    }

    public static void copyStatus(Status _cp,Status _e){
        _cp.setStatusType(_e.getStatusType());
        _cp.setCatchingRate(new Rate(_e.getCatchingRate()));
        _cp.setEffectEndRound(copyListEffectEndRoundStatus(_e.getEffectEndRound()));
        _cp.setEffectsPartner(copyListEffectPartnerStatus(_e.getEffectsPartner()));
        _cp.setDisabledEffIfSwitch(_e.getDisabledEffIfSwitch());
        _cp.setIncrementEndRound(_e.getIncrementEndRound());
        _cp.setIncrementingEndRound(_e.getIncrementingEndRound());
        _cp.setMultStat(new IdMap<Statistic,Rate>(_e.getMultStat()));
        _cp.setFail(_e.getFail());
    }
    public static CustList<EffectEndRoundStatus> copyListEffectEndRoundStatus(CustList<EffectEndRoundStatus> _e) {
        CustList<EffectEndRoundStatus> cp_= new CustList<EffectEndRoundStatus>();
        for (EffectEndRoundStatus f: _e){
            cp_.add(copyEffectEndRoundStatus(f));
        }
        return cp_;
    }
    public static CustList<EffectPartnerStatus> copyListEffectPartnerStatus(CustList<EffectPartnerStatus> _e) {
        CustList<EffectPartnerStatus> cp_= new CustList<EffectPartnerStatus>();
        for (EffectPartnerStatus f: _e){
            cp_.add(copyEffectPartnerStatus(f));
        }
        return cp_;
    }
    public static Status copyStatus(Status _e){
        if (_e instanceof StatusBeginRoundAutoDamage){
            return copyStatusBeginRoundAutoDamage((StatusBeginRoundAutoDamage)_e);
        }
        if (_e instanceof StatusBeginRoundSimple){
            return copyStatusBeginRoundSimple((StatusBeginRoundSimple)_e);
        }
        return copyStatusSimple((StatusSimple)_e);
    }

    public static void copyStatusBeginRound(StatusBeginRound _cp,StatusBeginRound _e){
        _cp.setLawForUsingAMove(copyMonteCarloBool(_e.getLawForUsingAMove()));
        _cp.setLawForUsingAMoveNbRound(_e.getLawForUsingAMoveNbRound().copy());
        _cp.setLawForUsingAMoveIfFoe(copyMonteCarloBool(_e.getLawForUsingAMoveIfFoe()));
        _cp.setLawForFullHealIfMove(copyMonteCarloBool(_e.getLawForFullHealIfMove()));
        copyStatus(_cp,_e);
    }

    public static StatusBeginRoundAutoDamage copyStatusBeginRoundAutoDamage(StatusBeginRoundAutoDamage _e){
        StatusBeginRoundAutoDamage cp_ = new StatusBeginRoundAutoDamage();
        cp_.setPower(new Rate(_e.getPower()));
        cp_.setAttack(_e.getAttack());
        cp_.setDefense(_e.getDefense());
        copyStatusBeginRound(cp_,_e);
        return cp_;
    }

    public static StatusBeginRoundSimple copyStatusBeginRoundSimple(StatusBeginRoundSimple _e){
        StatusBeginRoundSimple cp_ = new StatusBeginRoundSimple();
        copyStatusBeginRound(cp_,_e);
        return cp_;
    }

    public static StatusSimple copyStatusSimple(StatusSimple _e){
        StatusSimple cp_ = new StatusSimple();
        copyStatus(cp_,_e);
        return cp_;
    }


    public static MonteCarloString buildMonteCarloString(CustList<EditedCrudPair<String, LgInt>> _m) {
        MonteCarloString c_ = new MonteCarloString(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,LgInt>().feedMap(_m, c_);
        return c_;
    }
    public static MonteCarloString copyMonteCarloString(MonteCarloString _m) {
        MonteCarloString c_ = new MonteCarloString(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,LgInt>().copy(new IdTechnicalCopier<String>(),new LgIntTechnicalCopier(), c_.getLaw(),_m.getLaw());
        return c_;
    }
    public static MonteCarloNumber buildMonteCarloNumber(CustList<EditedCrudPair<Rate, LgInt>> _m) {
        MonteCarloNumber c_ = new MonteCarloNumber(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Rate,LgInt>().feedMap(_m, c_);
        return c_;
    }
    public static MonteCarloBoolean buildMonteCarloBool(CustList<EditedCrudPair<BoolVal, LgInt>> _m) {
        MonteCarloBoolean c_ = new MonteCarloBoolean(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<BoolVal,LgInt>().feedMap(_m, c_);
        return c_;
    }
    public static MonteCarloBoolean copyMonteCarloBool(MonteCarloBoolean _m) {
        MonteCarloBoolean c_ = new MonteCarloBoolean(new CollCapacity(_m.size()));
        for (EventFreq<BoolVal> e: _m.getEvents()) {
            c_.addQuickEvent(e.getEvent(),new LgInt(e.getFreq()));
        }
        return c_;
    }
    public static MonteCarloEnum<Statistic> buildMonteCarloEnumStatistic(CustList<EditedCrudPair<Statistic, LgInt>> _m) {
        MonteCarloEnum<Statistic> c_ = new MonteCarloEnum<Statistic>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,LgInt>().feedMap(_m, c_);
        return c_;
    }
    public static MonteCarloEnum<Statistic> copyMonteCarloEnumStatistic(MonteCarloEnum<Statistic> _m) {
        MonteCarloEnum<Statistic> c_ = new MonteCarloEnum<Statistic>(new CollCapacity(_m.size()));
        new ConverterCommonMap<Statistic,LgInt>().copy(new IdTechnicalCopier<Statistic>(),new LgIntTechnicalCopier(), c_.getLaw(), _m.getLaw());
        return c_;
    }
    public static LongMap<Rate> buildLongMapRate(CustList<EditedCrudPair<Long, Rate>> _m) {
        LongMap<Rate> c_ = new LongMap<Rate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Long,Rate>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<String> buildStringMapString(CustList<EditedCrudPair<String, String>> _m) {
        StringMap<String> c_ = new StringMap<String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,String>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<StringList> buildStringMapStringList(CustList<EditedCrudPair<String, StringList>> _m) {
        StringMap<StringList> c_ = new StringMap<StringList>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,StringList>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<StringList> copyStringMapStringList(StringMap<StringList> _m) {
        StringMap<StringList> c_ = new StringMap<StringList>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,StringList>().copy(new IdTechnicalCopier<String>(),new StringListTechnicalCopier(),c_, _m);
        return c_;
    }
    public static StringMap<Ints> copyStringMapInts(StringMap<Ints> _m) {
        StringMap<Ints> c_ = new StringMap<Ints>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,Ints>().copy(new IdTechnicalCopier<String>(),new IntsTechnicalCopier(),c_, _m);
        return c_;
    }
    public static StringMap<IdList<Statistic>> buildStringMapIdListStatistic(CustList<EditedCrudPair<String, IdList<Statistic>>> _m) {
        StringMap<IdList<Statistic>> c_ = new StringMap<IdList<Statistic>>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,IdList<Statistic>>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<IdMap<Statistic, Byte>> buildStringMapIdMapStatisticByte(CustList<EditedCrudPair<String, IdMap<Statistic, Byte>>> _m) {
        StringMap<IdMap<Statistic, Byte>> c_ = new StringMap<IdMap<Statistic, Byte>>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,IdMap<Statistic, Byte>>().feedMap(_m, c_);
        return c_;
    }
    public static ShortMap<String> buildShortMapString(CustList<EditedCrudPair<Short, String>> _m) {
        ShortMap<String> c_ = new ShortMap<String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Short,String>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<EfficiencyRate> buildStringMapEfficiencyRate(CustList<EditedCrudPair<String, EfficiencyRate>> _m) {
        StringMap<EfficiencyRate> c_ = new StringMap<EfficiencyRate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,EfficiencyRate>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<EfficiencyRate> copyStringMapEfficiencyRate(StringMap<EfficiencyRate> _m) {
        StringMap<EfficiencyRate> c_ = new StringMap<EfficiencyRate>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,EfficiencyRate>().copy(new IdTechnicalCopier<String>(),new EfficiencyRateTechnicalCopier(),c_, _m);
        return c_;
    }
    public static StringMap<Rate> buildStringMapRate(CustList<EditedCrudPair<String, Rate>> _m) {
        StringMap<Rate> c_ = new StringMap<Rate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Rate>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<Rate> copyStringMapRate(StringMap<Rate> _m) {
        StringMap<Rate> c_ = new StringMap<Rate>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,Rate>().copy(new IdTechnicalCopier<String>(),new RateTechnicalCopier(), c_, _m);
        return c_;
    }
    public static StringMap<Short> buildStringMapShort(CustList<EditedCrudPair<String, Short>> _m) {
        StringMap<Short> c_ = new StringMap<Short>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Short>().feedMap(_m, c_);
        return c_;
    }
    public static StringMap<Ints> buildStringMapInts(CustList<EditedCrudPair<String, Ints>> _m) {
        StringMap<Ints> c_ = new StringMap<Ints>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Ints>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,BoostHpRate> buildIdMapStatisticBoostHpRate(CustList<EditedCrudPair<Statistic, BoostHpRate>> _m) {
        IdMap<Statistic,BoostHpRate> c_ = new IdMap<Statistic,BoostHpRate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,BoostHpRate>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,BoostHpRate> copyIdMapStatisticBoostHpRate(IdMap<Statistic,BoostHpRate> _m) {
        IdMap<Statistic,BoostHpRate> c_ = new IdMap<Statistic,BoostHpRate>(new CollCapacity(_m.size()));
        new ConverterCommonMap<Statistic,BoostHpRate>().copy(new IdTechnicalCopier<Statistic>(),new BoostHpRateTechnicalCopier(), c_,_m);
        return c_;
    }
    public static IdMap<Statistic,Byte> buildIdMapStatisticByte(CustList<EditedCrudPair<Statistic, Byte>> _m) {
        IdMap<Statistic,Byte> c_ = new IdMap<Statistic,Byte>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,Byte>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,Short> buildIdMapStatisticShort(CustList<EditedCrudPair<Statistic, Short>> _m) {
        IdMap<Statistic,Short> c_ = new IdMap<Statistic,Short>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,Short>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,Rate> buildIdMapStatisticRate(CustList<EditedCrudPair<Statistic, Rate>> _m) {
        IdMap<Statistic,Rate> c_ = new IdMap<Statistic,Rate>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,Rate>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<Statistic,String> buildIdMapStatisticString(CustList<EditedCrudPair<Statistic, String>> _m) {
        IdMap<Statistic,String> c_ = new IdMap<Statistic,String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<Statistic,String>().feedMap(_m, c_);
        return c_;
    }
    public static IdMap<EnvironmentType,String> buildIdMapEnvironmentTypeString(CustList<EditedCrudPair<EnvironmentType, String>> _m) {
        IdMap<EnvironmentType,String> c_ = new IdMap<EnvironmentType,String>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<EnvironmentType,String>().feedMap(_m, c_);
        return c_;
    }
    public static CategoryMults buildCategoryMults(CustList<EditedCrudPair<CategoryMult, Rate>> _m) {
        CategoryMults c_ = new CategoryMults(new CollCapacity(_m.size()));
        new ConverterCommonMap<CategoryMult, Rate>().feed(c_,_m);
        return c_;
    }
    public static CategoryMults copyCategoryMults(CategoryMults _m) {
        CategoryMults c_ = new CategoryMults(new CollCapacity(_m.size()));
        new ConverterCommonMap<CategoryMult, Rate>().copy(new CategoryMultTechnicalCopier(),new RateTechnicalCopier(),c_,_m);
        return c_;
    }
    public static StatisticCategoryByte buildStatisticCategoryByte(CustList<EditedCrudPair<StatisticCategory, Byte>> _m) {
        StatisticCategoryByte c_ = new StatisticCategoryByte(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticCategory, Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticCategoryByte copyStatisticCategoryByte(StatisticCategoryList<Byte> _m) {
        StatisticCategoryByte c_ = new StatisticCategoryByte(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticCategory, Byte>().copy(new StatisticCategoryTechnicalCopier(),new IdTechnicalCopier<Byte>(),c_,_m);
        return c_;
    }
    public static StatisticCategoryRate buildStatisticCategoryRate(CustList<EditedCrudPair<StatisticCategory, Rate>> _m) {
        StatisticCategoryRate c_ = new StatisticCategoryRate(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticCategory, Rate>().feed(c_,_m);
        return c_;
    }
    public static StatisticCategoryRate copyStatisticCategoryRate(StatisticCategoryList<Rate> _m) {
        StatisticCategoryRate c_ = new StatisticCategoryRate(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticCategory, Rate>().copy(new StatisticCategoryTechnicalCopier(),new RateTechnicalCopier(),c_,_m);
        return c_;
    }
    public static StatisticPokemons buildStatisticPokemons(CustList<EditedCrudPair<StatisticPokemon,Byte>> _m) {
        StatisticPokemons c_ = new StatisticPokemons(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticPokemon,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticPokemons copyStatisticPokemons(StatisticPokemons _m) {
        StatisticPokemons c_ = new StatisticPokemons(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticPokemon,Byte>().copy(new StatisticPokemonTechnicalCopier(),new IdTechnicalCopier<Byte>(),c_, _m);
        return c_;
    }
    public static StatisticStatusList buildStatisticStatusList(CustList<EditedCrudPair<StatisticStatus,Byte>> _m) {
        StatisticStatusList c_ = new StatisticStatusList(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticStatus,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticStatusList copyStatisticStatusList(StatisticStatusList _m) {
        StatisticStatusList c_ = new StatisticStatusList(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticStatus,Byte>().copy(new StatisticStatusTechnicalCopier(),new IdTechnicalCopier<Byte>(),c_,_m);
        return c_;
    }
    public static StatisticTypeByte buildStatisticTypeByte(CustList<EditedCrudPair<StatisticType,Byte>> _m) {
        StatisticTypeByte c_ = new StatisticTypeByte(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticType,Byte>().feed(c_,_m);
        return c_;
    }
    public static StatisticTypeByte copyStatisticTypeByte(StatisticTypeList<Byte> _m) {
        StatisticTypeByte c_ = new StatisticTypeByte(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticType,Byte>().copy(new StatisticTypeTechnicalCopier(),new IdTechnicalCopier<Byte>(),c_,_m);
        return c_;
    }
    public static StatisticTypeRate buildStatisticTypeRate(CustList<EditedCrudPair<StatisticType,Rate>> _m) {
        StatisticTypeRate c_ = new StatisticTypeRate(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticType,Rate>().feed(c_,_m);
        return c_;
    }
    public static StatisticTypeRate copyStatisticTypeRate(StatisticTypeList<Rate> _m) {
        StatisticTypeRate c_ = new StatisticTypeRate(new CollCapacity(_m.size()));
        new ConverterCommonMap<StatisticType,Rate>().copy(new StatisticTypeTechnicalCopier(),new RateTechnicalCopier(),c_,_m);
        return c_;
    }
    public static TypesDuos buildTypesDuos(CustList<EditedCrudPair<TypesDuo,Rate>> _m) {
        TypesDuos c_ = new TypesDuos(new CollCapacity(_m.size()));
        new ConverterCommonMap<TypesDuo,Rate>().feed(c_,_m);
        return c_;
    }
    public static TypesDuos copyTypesDuos(TypesDuos _m) {
        TypesDuos c_ = new TypesDuos(new CollCapacity(_m.size()));
        new ConverterCommonMap<TypesDuo,Rate>().copy(new TypesDuoTechnicalCopier(),new RateTechnicalCopier(),c_,_m);
        return c_;
    }
    public static WeatherTypes buildWeatherTypes(CustList<EditedCrudPair<WeatherType,Rate>> _m) {
        WeatherTypes c_ = new WeatherTypes(new CollCapacity(_m.size()));
        new ConverterCommonMap<WeatherType,Rate>().feed(c_,_m);
        return c_;
    }
    public static WeatherTypes copyWeatherTypes(WeatherTypes _m) {
        WeatherTypes c_ = new WeatherTypes(new CollCapacity(_m.size()));
        new ConverterCommonMap<WeatherType,Rate>().copy(new WeatherTypeTechnicalCopier(),new RateTechnicalCopier(),c_,_m);
        return c_;
    }
    public static StringMap<TypeDamageBoost> buildStringMapTypeDamageBoost(CustList<EditedCrudPair<String,TypeDamageBoost>> _m) {
        StringMap<TypeDamageBoost> c_ = new StringMap<TypeDamageBoost>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,TypeDamageBoost>().feed(c_,_m);
        return c_;
    }
    public static StringMap<TypeDamageBoost> copyStringMapTypeDamageBoost(StringMap<TypeDamageBoost> _m) {
        StringMap<TypeDamageBoost> c_ = new StringMap<TypeDamageBoost>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,TypeDamageBoost>().copy(new IdTechnicalCopier<String>(),new TypeDamageBoostTechnicalCopier(),c_,_m);
        return c_;
    }
    public static StringMap<Evolution> buildStringMapEvolution(CustList<EditedCrudPair<String,Evolution>> _m) {
        StringMap<Evolution> c_ = new StringMap<Evolution>(new CollCapacity(_m.size()));
        new MapToEntriesListUtil<String,Evolution>().feedMap(_m, c_);
        return c_;
    }
    public static LongMap<Rate> copyLongMapRate(LongMap<Rate> _m) {
        LongMap<Rate> c_ = new LongMap<Rate>(new CollCapacity(_m.size()));
        new ConverterCommonMap<Long,Rate>().copy(new IdTechnicalCopier<Long>(),new RateTechnicalCopier(),c_,_m);
        return c_;
    }
    public static StringMap<IdList<Statistic>> copyStringMapListStatistic(StringMap<IdList<Statistic>> _m) {
        StringMap<IdList<Statistic>> c_ = new StringMap<IdList<Statistic>>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,IdList<Statistic>>().copy(new IdTechnicalCopier<String>(),new ListStatisticTechnicalCopier(),c_,_m);
        return c_;
    }
    public static StringMap<IdMap<Statistic,Byte>> copyStringMapMapStatistic(StringMap<IdMap<Statistic,Byte>> _m) {
        StringMap<IdMap<Statistic,Byte>> c_ = new StringMap<IdMap<Statistic,Byte>>(new CollCapacity(_m.size()));
        new ConverterCommonMap<String,IdMap<Statistic,Byte>>().copy(new IdTechnicalCopier<String>(),new IdMapImmutableTechnicalCopier<Statistic,Byte>(),c_,_m);
        return c_;
    }
    public static CustList<TypesDuo> copyListTypesDuo(CustList<TypesDuo> _m) {
        return new IdTechnicalCopier<TypesDuo>().copy(new TypesDuoTechnicalCopier(),_m);
    }
    public static CustList<StatisticStatus> copyListStatisticStatus(CustList<StatisticStatus> _m) {
        return new IdTechnicalCopier<StatisticStatus>().copy(new StatisticStatusTechnicalCopier(),_m);
    }
    public static CustList<LevelMove> copyListLevelMove(CustList<LevelMove> _m) {
        return new IdTechnicalCopier<LevelMove>().copy(new LevelMoveTechnicalCopier(),_m);
    }
    public static ImageArrayBaseSixtyFour copyImageArrayBaseSixtyFour(ImageArrayBaseSixtyFour _m) {
        return ImageArrayBaseSixtyFour.instance(copyImg(_m.getImage()), _m.getBase());
    }
    public static int[][] copyImg(int[][] _a) {
        int len_ = _a.length;
        int[][] cp_ = new int[len_][];
        for (int i = 0; i < len_; i++) {
            cp_[i] = copy(_a[i]);
        }
        return cp_;
    }
    public static int[] copy(int[] _a) {
        int len_ = _a.length;
        int[] cp_ = new int[len_];
        for (int i = 0; i < len_; i++) {
            setElt(cp_,_a,i);
        }
        return cp_;
    }
    private static void setElt(int[] _a, int[] _b, int _i){
        _a[_i] = _b[_i];
    }
    public static StringMap<StringMap<String>> backUp(StringMap<StringMap<String>> _tr) {
        StringMap<StringMap<String>> bk_ = new StringMap<StringMap<String>>();
        for (EntryCust<String, StringMap<String>> e: _tr.entryList()) {
            bk_.addEntry(e.getKey(),new StringMap<String>(e.getValue()));
        }
        return bk_;
    }

    public static void setKey(String _key, StringMap<String> _value, StringMap<StringMap<String>> _tr) {
        for (EntryCust<String, StringMap<String>> v: _tr.entryList()) {
            v.getValue().set(_key, StringUtil.nullToEmpty(_value.getVal(v.getKey())));
        }
    }

    public static void addKey(String _key, StringMap<String> _value, StringMap<StringMap<String>> _tr) {
        for (EntryCust<String, StringMap<String>> v: _tr.entryList()) {
            v.getValue().addEntry(_key, StringUtil.nullToEmpty(_value.getVal(v.getKey())));
        }
    }

    public static void removeKey(String _key, StringMap<StringMap<String>> _tr) {
        for (StringMap<String> v: _tr.values()) {
            v.removeKey(_key);
        }
    }
    public static StringMap<String> defKeyEmpty(String _v) {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(DataBase.EMPTY_STRING,_v);
        return map_;
    }
    public static void trigger(GeneComponentModelElt<String> _sel, String _key) {
        _sel.setupValue(_key);
        _sel.getSelect().events(null);
    }

    public static CrudGeneFormMonteCarloSub<String> buildStatusLaw(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        CrudGeneFormMonteCarloSub<String> law_ = new CrudGeneFormMonteCarloSub<String>(_f, _core);
        law_.initFormKeys(buildStatus(_core,_fac,_fact, defKeyEmpty(" ")),new DisplayEntryCustSubElementLgIntImpl<String>(_fact.getFactorySt(), _core, _fac, defKeyEmpty(" ")));
        return law_;
    }


    public static CrudGeneFormMonteCarlo<BoolVal> buildMcBool(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<BoolVal> out_ = new CrudGeneFormMonteCarlo<BoolVal>(_f, _core, new ComparingBoolKey<LgInt>());
        out_.initFormKeys(new BoolLgIntDisplayEntryCust(),new GeneComponentModelEventBoolVal(_core), new ComparingBoolKey<LgInt>());
        return out_;
    }
    public static CrudGeneFormMonteCarlo<Rate> buildMcRate(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<Rate> out_ = new CrudGeneFormMonteCarlo<Rate>(_f, _core, new ComparingRateKey<LgInt>());
        out_.initFormKeys(new RateLgIntDisplayEntryCust(),new GeneComponentModelEventRate(_core), new ComparingRateKey<LgInt>());
        return out_;
    }

    public static StringMap<AbsTextField> fields(AbsPanel _line, StringMap<String> _map, AbstractProgramInfos _api) {
        StringMap<AbsTextField> fs_ = new StringMap<AbsTextField>();
        for (EntryCust<String, String> l: _map.entryList()) {
            AbsTextField txt_ = _api.getCompoFactory().newTextField(l.getValue());
            _line.add(txt_);
            fs_.addEntry(l.getKey(),txt_);
        }
        return fs_;
    }

    public static StringMap<CustList<AbsTxtComponent>> areasList(AbsPanel _line, StringMap<String> _map, AbstractProgramInfos _api) {
        StringMap<CustList<AbsTxtComponent>> fs_ = new StringMap<CustList<AbsTxtComponent>>();
        for (EntryCust<String, String> l: _map.entryList()) {
            CustList<AbsTxtComponent> ls_ = new CustList<AbsTxtComponent>();
            StringList parts_ = StringUtil.splitChar(l.getValue(), '\t');
            int len_ = NumberUtil.min(parts_.size(),3);
            for (int i = 0; i < len_; i++) {
                AbsTxtComponent txt_;
                if (i <= 1) {
                    txt_ = _api.getCompoFactory().newTextField(32);
                    txt_.setText(parts_.get(i));
                } else {
                    txt_ = _api.getCompoFactory().newTextPane();
                    txt_.setText(StringUtil.join(parts_.mid(2),'\t'));
                }
                _line.add(txt_);
                ls_.add(txt_);
            }
            fs_.addEntry(l.getKey(),ls_);
        }
        return fs_;
    }
    public static StringList complete(DataBase _db, AbstractProgramInfos _api, String _text, int _caret) {
        MbDelimiters dels_ = MathResolver.checkSyntax(_text, new ErrorStatus());
        int count_ = 0;
        for (int i = 0; i < _caret; i++) {
            if (dels_.getDelStringsChars().contains(i)) {
                count_++;
            }
        }
        StringList infos_ = new StringList();
        infos_.addAllElts(variables(_db, _api));
        if (count_ % 2 == 1) {
            infos_.addAllElts(elements(_db, _api));
        }
        return infos_;
    }
    public static StringList elements(DataBase _db, AbstractProgramInfos _api) {
        StringList str_ = new StringList();
        appendSetVars(_db.getTranslatedAbilities(), _api, str_);
        appendSetVars(_db.getTranslatedCategories(), _api, str_);
        appendSetVars(_db.getTranslatedItems(), _api, str_);
        appendSetVars(_db.getTranslatedMoves(), _api, str_);
        appendSetVars(_db.getTranslatedPokemon(), _api, str_);
        appendSetVars(_db.getTranslatedStatus(), _api, str_);
        appendSetVars(_db.getTranslatedTypes(), _api, str_);
        return str_;
    }
    public static StringList variables(DataBase _db, AbstractProgramInfos _api) {
        StringList str_ = new StringList();
        str_.add(_db.prefixNiveau());
        str_.add(_db.prefixLevelLooser());
        str_.add(_db.prefixLevelWinner());
        str_.add(_db.prefixFighterNiveau());
        str_.add(_db.prefixCibleNiveau());
        str_.add(_db.prefixLanceurNiveau());
        str_.add(_db.prefixPkSauvageNiveau());
        str_.add(_db.prefixPkUtNiveau());
        str_.add(_db.prefixBoost());
        str_.add(_db.prefixPower());
        str_.add(_db.prefixAttack());
        str_.add(_db.prefixDefense());
        str_.add(_db.prefixBaseCaptPk());
        str_.add(_db.prefixRateBallStatus());
        str_.add(_db.prefixFoePkMaxHp());
        str_.add(_db.prefixFoePkRemoteHp());
        str_.add(_db.prefixCiblePvRestants());
        str_.add(_db.prefixFighterPvRestants());
        str_.add(_db.prefixLanceurPvRestants());
        str_.add(_db.prefixCiblePvMax());
        str_.add(_db.prefixFighterPvMax());
        str_.add(_db.prefixLanceurPvMax());
        str_.add(_db.prefixSommeBoostPosCible());
        str_.add(_db.prefixSommeBoostPosLanceur());
        str_.add(_db.prefixSommeBoostPosFighter());
        str_.add(_db.prefixCibleAttaques());
        str_.add(_db.prefixCibleAttaqueChoisie());
        str_.add(_db.prefixCibleAttaquesTypes());
        str_.add(_db.prefixCibleClone());
        str_.add(_db.prefixCibleDegatsRecusTotal());
        str_.add(_db.prefixCibleDegatsRecusTotalTour());
        str_.add(_db.prefixCibleDisparait());
        str_.add(_db.prefixCibleJoue());
        str_.add(_db.prefixCibleMasse());
        str_.add(_db.prefixCibleTaille());
        str_.add(_db.prefixCibleCapacite());
        str_.add(_db.prefixCibleObjet());
        str_.add(_db.prefixCibleStatuts());
        str_.add(_db.prefixCibleTypes());
        str_.add(_db.prefixCibleGenre());
        str_.add(_db.prefixCibleBonheur());
        str_.add(_db.prefixCibleNom());
        str_.add(_db.prefixCibleDerJoue());
        str_.add(_db.prefixNbKoEquipeCible());
        str_.add(_db.prefixNbKoEquipeAdvCible());
        str_.add(_db.prefixPasPpAttaqueCible());
        str_.add(_db.prefixPasUtilisAttaqueCible());
        str_.add(_db.prefixLanceurAttaques());
        str_.add(_db.prefixLanceurAttaqueChoisie());
        str_.add(_db.prefixLanceurAttaquesTypes());
        str_.add(_db.prefixLanceurClone());
        str_.add(_db.prefixLanceurDegatsRecusTotal());
        str_.add(_db.prefixLanceurDegatsRecusTotalTour());
        str_.add(_db.prefixLanceurDisparait());
        str_.add(_db.prefixLanceurJoue());
        str_.add(_db.prefixLanceurMasse());
        str_.add(_db.prefixLanceurTaille());
        str_.add(_db.prefixLanceurCapacite());
        str_.add(_db.prefixLanceurObjet());
        str_.add(_db.prefixLanceurStatuts());
        str_.add(_db.prefixLanceurTypes());
        str_.add(_db.prefixLanceurGenre());
        str_.add(_db.prefixLanceurBonheur());
        str_.add(_db.prefixLanceurNom());
        str_.add(_db.prefixLanceurDerJoue());
        str_.add(_db.prefixNbKoEquipeLanceur());
        str_.add(_db.prefixNbKoEquipeAdvLanceur());
        str_.add(_db.prefixFighterAttaques());
        str_.add(_db.prefixFighterAttaqueChoisie());
        str_.add(_db.prefixFighterAttaquesTypes());
        str_.add(_db.prefixFighterClone());
        str_.add(_db.prefixFighterDegatsRecusTotal());
        str_.add(_db.prefixFighterDegatsRecusTotalTour());
        str_.add(_db.prefixFighterDisparait());
        str_.add(_db.prefixFighterJoue());
        str_.add(_db.prefixFighterMasse());
        str_.add(_db.prefixFighterTaille());
        str_.add(_db.prefixFighterCapacite());
        str_.add(_db.prefixFighterObjet());
        str_.add(_db.prefixFighterStatuts());
        str_.add(_db.prefixFighterTypes());
        str_.add(_db.prefixFighterGenre());
        str_.add(_db.prefixFighterBonheur());
        str_.add(_db.prefixFighterNom());
        str_.add(_db.prefixFighterDerJoue());
        str_.add(_db.prefixNbKoEquipeFighter());
        str_.add(_db.prefixNbKoEquipeAdvFighter());
        str_.add(_db.prefixPkSauvageGenre());
        str_.add(_db.prefixPkSauvageMasse());
        str_.add(_db.prefixPkSauvageVitesse());
        str_.add(_db.prefixPkSauvageTypesBase());
        str_.add(_db.prefixPkSauvagePierresEvos());
        str_.add(_db.prefixPkUtGenre());
        str_.add(_db.prefixPkUtMasse());
        str_.add(_db.prefixPkUtVitesse());
        str_.add(_db.prefixPkUtTypesBase());
        str_.add(_db.prefixPkUtPierresEvos());
        str_.add(_db.prefixCombattantEntrantClone());
        str_.add(_db.prefixCombattantEntrantTypes());
        str_.add(_db.prefixAucunBoostPossible());
        str_.add(_db.prefixTypesAttaquesResVide());
        str_.add(_db.prefixPasPartenaire());
        str_.add(_db.prefixPasPartenaireArriere());
        str_.add(_db.prefixPasPartenaireTerrain());
        str_.add(_db.prefixPasTourTerrain());
        str_.add(_db.prefixExisteGenreAssexue());
        str_.add(_db.prefixGenresEgaux());
        str_.add(_db.prefixRateEffMoveAgainstTarget());
        str_.add(_db.prefixCoeffEff());
        str_.add(_db.prefixNbUtilisationConsecutif());
        str_.add(_db.prefixAttaqueCategorie());
        str_.add(_db.prefixAttaqueTypes());
        str_.add(_db.prefixAttaqueNom());
        str_.add(_db.prefixPuissanceBase());
        str_.add(_db.prefixPasAttaqueInvoc());
        str_.add(_db.prefixPasAttaquesCopiables());
        str_.add(_db.prefixDejaCapture());
        str_.add(_db.prefixNbFlees());
        str_.add(_db.prefixMasseMoyennePk());
        str_.add(_db.prefixClimats());
        str_.add(_db.prefixNbCombattantsTerrain());
        str_.add(_db.prefixLieuCombat());
        str_.add(_db.prefixTempsTour());
        appendVars(_db, _api, str_, StringUtil.concat(_db.cibleStatis(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.fighterStatis(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.lanceurStatis(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.cibleBoost(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.fighterBoost(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, StringUtil.concat(_db.lanceurBoost(),DataBase.SEP_BETWEEN_KEYS));
        appendVars(_db, _api, str_, _db.typesPart(), _db.getTranslatedTypes());
        appendVars(_db, _api, str_, _db.movesPart(), _db.getTranslatedMoves());
        appendVars(_db, _api, str_, _db.categoriesPart(), _db.getTranslatedCategories());
        appendVars(_db, _api, str_, _db.statusPart(), _db.getTranslatedStatus());
        return str_;
    }

    private static void appendVars(DataBase _db, AbstractProgramInfos _api, StringList _str, String _p) {
        for (Statistic t: _db.getTranslatedStatistics().getVal(_api.getLanguage()).getKeys()) {
            _str.add(_db.prefixVar()+DataBase.SEP_BETWEEN_KEYS+ _p +t.getStatName());
        }
    }

    private static void appendSetVars(StringMap<StringMap<String>> _trs, AbstractProgramInfos _api, StringList _str) {
        _str.addAllElts(_trs.getVal(_api.getLanguage()).getKeys());
    }

    private static void appendVars(DataBase _db, AbstractProgramInfos _api, StringList _str, StringList _prefixes, StringMap<StringMap<String>> _trs) {
        for (String p: _prefixes) {
            for (String t: _trs.getVal(_api.getLanguage()).getKeys()) {
                _str.add(_db.getConstNonNum().getPrefixVar()+DataBase.SEP_BETWEEN_KEYS+p+t);
            }
        }
    }
}
