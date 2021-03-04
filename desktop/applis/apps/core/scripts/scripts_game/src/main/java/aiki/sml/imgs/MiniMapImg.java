package aiki.sml.imgs;
import code.util.*;
public final class MiniMapImg{
private MiniMapImg(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(new CollCapacity(8));
i.addEntry("city_locked.txt",MiniMapImg0.i0());
i.addEntry("city_unlocked.txt",MiniMapImg0.i1());
i.addEntry("ground.txt",MiniMapImg0.i2());
i.addEntry("league.txt",MiniMapImg0.i3());
i.addEntry("road.txt",MiniMapImg0.i4());
i.addEntry("rock.txt",MiniMapImg0.i5());
i.addEntry("sea.txt",MiniMapImg0.i6());
i.addEntry("sea_way.txt",MiniMapImg0.i7());
return i;
}
}
