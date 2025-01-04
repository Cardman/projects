package aiki.sml;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.*;
import aiki.fight.abilities.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.pokemon.*;
import aiki.fight.status.*;
import aiki.game.*;
import aiki.map.*;
import code.maths.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Assert;

public abstract class EquallableAikiSerialUtil {
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    public static final String NAME = "_";

    public static AbilityData save(AbilityData _a) {
        return retrieve(_a).getData();
    }
    public static PkFileElement<AbilityData> retrieve(AbilityData _a) {
        return DocumentReaderAikiCoreUtil.getAbilityData(DocumentWriterAikiCoreUtil.setAbilityData(_a,NAME));
    }
    public static PokemonData save(PokemonData _a) {
        return retrieve(_a).getData();
    }
    public static PkFileElement<PokemonData> retrieve(PokemonData _a) {
        return DocumentReaderAikiCoreUtil.getPokemonData(DocumentWriterAikiCoreUtil.setPokemonData(_a,NAME));
    }
    public static DamagingMoveData save(DamagingMoveData _a) {
        return (DamagingMoveData) move(_a);
    }
    public static StatusMoveData save(StatusMoveData _a) {
        return (StatusMoveData) move(_a);
    }

    private static MoveData move(MoveData _m) {
        return retrieve(_m).getData();
    }

    public static StatusSimple save(StatusSimple _a) {
        return (StatusSimple) st(_a);
    }
    public static StatusBeginRoundAutoDamage save(StatusBeginRoundAutoDamage _a) {
        return (StatusBeginRoundAutoDamage) st(_a);
    }
    public static StatusBeginRoundSimple save(StatusBeginRoundSimple _a) {
        return (StatusBeginRoundSimple) st(_a);
    }

    private static Status st(Status _s) {
        return retrieve(_s).getData();
    }
    public static PkFileElement<Status> retrieve(Status _s) {
        return DocumentReaderAikiCoreUtil.getStatus(DocumentWriterAikiCoreUtil.setStatus(_s,NAME));
    }
    public static PkFileElement<MoveData> retrieve(MoveData _s) {
        return DocumentReaderAikiCoreUtil.getMoveData(DocumentWriterAikiCoreUtil.setMoveData(_s,NAME));
    }
    public static ItemForBattle save(ItemForBattle _a) {
        return (ItemForBattle) it(_a);
    }

    public static Berry save(Berry _a) {
        return (Berry) it(_a);
    }

    public static Ball save(Ball _a) {
        return (Ball) it(_a);
    }

    public static Boost save(Boost _a) {
        return (Boost) it(_a);
    }

    public static EvolvingItem save(EvolvingItem _a) {
        return (EvolvingItem) it(_a);
    }

    public static EvolvingStone save(EvolvingStone _a) {
        return (EvolvingStone) it(_a);
    }

    public static Fossil save(Fossil _a) {
        return (Fossil) it(_a);
    }

    public static HealingHp save(HealingHp _a) {
        return (HealingHp) it(_a);
    }

    public static HealingPp save(HealingPp _a) {
        return (HealingPp) it(_a);
    }

    public static HealingSimpleItem save(HealingSimpleItem _a) {
        return (HealingSimpleItem) it(_a);
    }

    public static HealingHpStatus save(HealingHpStatus _a) {
        return (HealingHpStatus) it(_a);
    }

    public static HealingSimpleStatus save(HealingSimpleStatus _a) {
        return (HealingSimpleStatus) it(_a);
    }

    public static Repel save(Repel _a) {
        return (Repel) it(_a);
    }

    public static SellingItem save(SellingItem _a) {
        return (SellingItem) it(_a);
    }

    private static Item it(Item _i) {
        return retrieve(_i).getData();
    }
    public static PkFileElement<Item> retrieve(Item _s) {
        return DocumentReaderAikiCoreUtil.getItem(DocumentWriterAikiCoreUtil.setItem(_s,NAME));
    }
    public static Combos save(Combos _a) {
        return DocumentReaderAikiCoreUtil.getCombos(DocumentWriterAikiCoreUtil.setCombos(_a));
    }
    public static DataMap save(DataMap _a) {
        return DocumentReaderAikiCoreUtil.getDataMap(DocumentWriterAikiCoreUtil.setDataMap(_a));
    }
    public static Game save(Game _a) {
        return DocumentReaderAikiCoreUtil.getGame(DocumentWriterAikiCoreUtil.setGame(_a),new SexListImpl());
    }

    public static LoadingGame save(LoadingGame _a) {
        return DocumentReaderAikiCoreUtil.getLoadingGame(DocumentWriterAikiCoreUtil.setLoadingGame(_a));
    }

    public static FacadeGame save(FacadeGame _a) {
        StringMap<String> txt_ = DocumentWriterAikiCoreUtil.getTextFiles(_a.getData());
        DocumentReaderAikiCoreUtil.loadRom(_a.getData(),txt_,new ConcreteInteger(),new SexListImpl(), BASE);
        return _a;
    }
    public static FacadeGame saveQuick(FacadeGame _a) {
        StringMap<String> txt_ = DocumentWriterAikiCoreUtil.getTextFiles(_a.getData());
        _a.setData(DocumentReaderAikiCoreUtil.loadRomQuick(_a.getData().getGenerator(),_a,txt_, BASE));
        return _a;
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertNull(Game _g) {
        Assert.assertNull(_g);
    }
    public static void assertNotNull(Game _g) {
        Assert.assertNotNull(_g);
    }
    public static void assertNull(LoadingGame _g) {
        Assert.assertNull(_g);
    }
    public static void assertNotNull(LoadingGame _g) {
        Assert.assertNotNull(_g);
    }

    public static ImageArrayBaseSixtyFour instance(int[][] _img) {
        return ImageArrayBaseSixtyFour.instance(_img,BASE);
    }
}
