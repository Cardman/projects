package aiki.beans.facade.simulation.enums;
import aiki.beans.PokemonStandards;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;

public final class AikiBeansFacadeSimulationEnumsStd {
    public static final String TYPE_SIMULATION_STEPS = "aiki.beans.facade.simulation.enums.SimulationSteps";


    public static void build(PokemonStandards _std) {
        buildSimulationSteps(_std);
    }
    private static void buildSimulationSteps(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_SIMULATION_STEPS, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_SIMULATION_STEPS, type_);
    }
}
