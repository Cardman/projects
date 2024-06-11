package cards.network.threads;

import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public abstract class ServerActLoopCardsActedByClientAll extends ServerActLoopCardsActedByClientReceived{
    @Override
    protected void loopReceive(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        if (Net.getGames(_instance).enCoursDePartieBelote()) {
            loopBelote(_input,_instance,_fct,_common);
        }
        if (Net.getGames(_instance).enCoursDePartiePresident()) {
            loopPresident(_input,_instance,_fct,_common);
        }
        if (Net.getGames(_instance).enCoursDePartieTarot()) {
            loopTarot(_input,_instance,_fct,_common);
        }
    }


    protected abstract void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common);

    protected abstract void loopPresident(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common);

    protected abstract void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common);
}
