package aiki.beans;

import aiki.beans.pokemon.evolutions.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayEvolution implements BeanDisplayEltGrid<EvolutionBean> {
    @Override
    public int displayEltGrid(CommonBean _rend, EvolutionBean _info) {
        evo(_rend, _info);
        return 2;
    }

    private void evo(CommonBean _rend, EvolutionBean _sub) {
        _rend.formatMessageDirCts(_sub.getName());
        if (_sub instanceof EvolutionLevelGenderBean) {
            _rend.formatMessageCts(MessagesPkBean.EVO_LEVEL_GENDER, MessagesDataEvolutionsEvolevelgender.M_P_76_GENDER, _sub.getDisplayBase(),Long.toString(((EvolutionLevelGenderBean) _sub).getLevel()),((EvolutionLevelGenderBean) _sub).getGender());
        } else if (_sub instanceof EvolutionLevelBean) {
            _rend.formatMessageCts(MessagesPkBean.EVO_LEVEL, MessagesDataEvolutionsEvolevel.M_P_75_LEVEL, _sub.getDisplayBase(),Long.toString(((EvolutionLevelBean) _sub).getLevel()));
        } else if (_sub instanceof EvolutionHappinessBean) {
            _rend.formatMessageCts(MessagesPkBean.EVO_HAPPINESS, MessagesDataEvolutionsEvohappiness.M_P_73_HAPPY, _sub.getDisplayBase(),Long.toString(((EvolutionHappinessBean) _sub).getMin()));
        } else if (_sub instanceof EvolutionMoveBean) {
            _rend.initLine();
            _rend.formatMessage(MessagesPkBean.EVO_MOVE, MessagesDataEvolutionsEvomove.M_P_77_MOVE, _sub.getDisplayBase());
            _rend.formatMessageDir(((EvolutionMoveBean) _sub).getMove());
            _rend.feedParentsCts();
        } else if (_sub instanceof EvolutionItemBean) {
            _rend.initLine();
            _rend.formatMessage(MessagesPkBean.EVO_ITEM,MessagesDataEvolutionsEvoitem.M_P_74_ITEM, _sub.getDisplayBase());
            _rend.formatMessageDir(((EvolutionItemBean) _sub).getItem());
            _rend.feedParentsCts();
        } else if (_sub instanceof EvolutionStoneGenderBean) {
            _rend.initLine();
            _rend.formatMessage(MessagesPkBean.EVO_STONE_GENDER,MessagesDataEvolutionsEvostonegender.M_P_79_STONE_GENDER, _sub.getDisplayBase(),((EvolutionStoneGenderBean) _sub).getGender());
            _rend.formatMessageDir(((EvolutionStoneGenderBean) _sub).getStone());
            _rend.feedParentsCts();
        } else if (_sub instanceof EvolutionStoneBean) {
            _rend.initLine();
            _rend.formatMessage(MessagesPkBean.EVO_STONE,MessagesDataEvolutionsEvostone.M_P_78_STONE, _sub.getDisplayBase());
            _rend.formatMessageDir(((EvolutionStoneBean) _sub).getStone());
            _rend.feedParentsCts();
        } else if (_sub instanceof EvolutionMoveTypeBean) {
            _rend.initLine();
            _rend.formatMessage(MessagesPkBean.EVO_TYPE,MessagesDataEvolutionsEvotype.M_P_81_TYPE, _sub.getDisplayBase(),((EvolutionMoveTypeBean)_sub).getType());
            _rend.feedParentsCts();
        } else {
            _rend.initLine();
            _rend.formatMessage(MessagesPkBean.EVO_TEAM,MessagesDataEvolutionsEvoteam.M_P_80_TEAM, _sub.getDisplayBase());
            _rend.formatMessageDir(((EvolutionTeamBean) _sub).getOther());
            _rend.feedParentsCts();
        }
    }

}
