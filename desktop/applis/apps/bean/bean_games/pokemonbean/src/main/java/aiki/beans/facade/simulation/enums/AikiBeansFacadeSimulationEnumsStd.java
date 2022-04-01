package aiki.beans.facade.simulation.enums;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansFacadeSimulationEnumsStd{
    public static final String TYPE_SIMULATION_STEPS = "aiki.beans.facade.simulation.enums.SimulationSteps";
    private AikiBeansFacadeSimulationEnumsStd(){}
    public static void build(PokemonStandards _std) {
        buildSimulationSteps(_std);
    }
    private static void buildSimulationSteps(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_SIMULATION_STEPS, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_SIMULATION_STEPS, type_);
    }
}
