package aiki.sml.trs;
import code.util.*;
public final class Cst{
private Cst(){}
public static StringMap<String> tr(){
StringMap<String> i = new StringMap<String>(new CollCapacity(8));
i.addEntry("const_num.txt",Cst0.tr());
i.addEntry("constantes_non_num.txt",Cst1.tr());
i.addEntry("courbe_pts_exp.txt",Cst2.tr());
i.addEntry("ct_cs.txt",Cst3.tr());
i.addEntry("lois_random.txt",Cst4.tr());
i.addEntry("rate_won_points.txt",Cst5.tr());
i.addEntry("table_types.txt",Cst6.tr());
i.addEntry("types_color.txt",Cst7.tr());
return i;
}
}
