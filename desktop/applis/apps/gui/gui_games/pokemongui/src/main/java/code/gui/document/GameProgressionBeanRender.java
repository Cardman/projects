package code.gui.document;

import aiki.beans.*;
import aiki.beans.game.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GameProgressionBeanRender extends AbsBeanRender {
    private final GameProgressionBean bean = new GameProgressionBean();
    @Override
    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade, _form);
        AbsPanel form_ = _api.getCompoFactory().newPageBox();
        form_.setBackground(GuiConstants.WHITE);
        form_.setTitledBorder(file(_api).getVal(MessagesProgGameprog.M_P_95_TITLE));
        AbsPanel line_ = _api.getCompoFactory().newPageBox();
        displayBool(_api,line_,bean.getFinishedGame(),CommonBean.TRUE_VALUE,bean.getHeroImage());
        displayBool(_api,line_,bean.getFinishedGame(),CommonBean.TRUE_VALUE,bean.getHeroImageOppositeSex());
        displayBool(_api,line_,bean.getFinishedGame(),CommonBean.TRUE_VALUE,bean.getEndGameImage());
        displayBoolFalse(_api,line_,MessagesPkBean.GAMEPROG,bean.getFinishedGame(),MessagesProgGameprog.M_P_95_NICKNAME,bean.getNickname());
        displayBool(_api,line_,bean.getFinishedGame(),CommonBean.FALSE_VALUE,bean.getHeroImage());
        feedParents(form_,line_);
        nextPart();
        feedParents(form_,build(_api,bean.getNotAtAllFamiliesBase(),file(_api).getVal(MessagesProgGameprog.M_P_95_TITLENOTATALL)));
        nextPart();
        feedParents(form_,buildPart(_api,file(_api).getVal(MessagesProgGameprog.M_P_95_TITLEPART)));
        nextPart();
        feedParents(form_,build(_api,bean.getFullFamiliesBase(),file(_api).getVal(MessagesProgGameprog.M_P_95_TITLEFULL)));
        nextPart();
        displayTrainerPlaceNamesList(_api,form_,MessagesPkBean.GAMEPROG,bean.getUnBeatenImportantTrainers(),MessagesProgGameprog.M_P_95_UNBEATTRAINER);
        nextPart();
        displayTrainerPlaceNamesList(_api,form_,MessagesPkBean.GAMEPROG,bean.getBeatenImportantTrainers(),MessagesProgGameprog.M_P_95_BEATTRAINER);
        nextPart();
        AbsPanel table_ = _api.getCompoFactory().newGrid();
        display(_api,table_,MessagesPkBean.GAMEPROG,bean.getRemainingOtherTrainerPlaces(),MessagesProgGameprog.M_P_95_OTHERTRAINERS);
        headerCols(_api,table_,MessagesPkBean.GAMEPROG,bean.getRemainingOtherTrainerPlaces(),MessagesProgGameprog.M_P_95_OTHERTRAINERSPLACE,MessagesProgGameprog.M_P_95_OTHERTRAINERSNUMBER);
        for (EntryCust<Integer,PlaceNamePk> e: bean.getRemainingOtherTrainerPlaces().entryList()) {
            formatMessageDir(_api,table_,_api.getCompoFactory().newGridCts(),e.getValue().getName());
            formatMessageDir(_api,table_,remainder(_api),Long.toString(e.getValue().getIndex()));
        }
        feedParents(form_,table_);
        nextPart();
        displayStringList(_api,form_,MessagesPkBean.GAMEPROG,bean.getUnVisitedPlaces(),MessagesProgGameprog.M_P_95_UNVISITPLACE);
        nextPart();
        displayStringList(_api,form_,MessagesPkBean.GAMEPROG,bean.getVisitedPlaces(),MessagesProgGameprog.M_P_95_VISITPLACE);
        nextPart();
        AbsPanel lineSec_ = _api.getCompoFactory().newPageBox();
        formatMessage(_api,lineSec_,MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMPKLEVEL,Long.toString(bean.getNbRemainingNotMaxLevel()));
        formatMessage(_api,lineSec_,MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMPKHAPPINESS,Long.toString(bean.getNbRemainingNotMaxHappiness()));
        formatMessage(_api,lineSec_,MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMEGG,Long.toString(bean.getNbRemainingEggs()));
        formatMessage(_api,lineSec_,MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_REPEL,Long.toString(bean.getRemainStepsRepel()));
        formatMessage(_api,lineSec_,MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_MONEY,bean.getMoney().toNumberString());
        return form_;
    }
    private AbsPanel build(AbstractProgramInfos _api, NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> _groups, String _title) {
        AbsPanel page_ = _api.getCompoFactory().newPageBox();
        page_.setTitledBorder(_title);
        for (EntryCust<String,CustList<CustList<ImgPkPlayer>>> e: _groups.entryList()) {
            AbsPanel line_ = _api.getCompoFactory().newLineBox();
            formatMessageDir(_api,line_,e.getKey());
            nextPart();
            for (CustList<ImgPkPlayer> s:e.getValue()) {
                AbsPanel pageSub_ = _api.getCompoFactory().newLineBox();
                buildPkList(_api,s,pageSub_);
                feedParents(line_,pageSub_);
            }
            feedParents(page_,line_);
        }
        return page_;
    }

    private AbsPanel buildPart(AbstractProgramInfos _api, String _title) {
        AbsPanel page_ = _api.getCompoFactory().newPageBox();
        page_.setTitledBorder(_title);
        int len_ = bean.getPartialFamiliesBaseNotCaught().size();
        for (int i = 0; i < len_; i++) {
            EntryCust<String, CustList<CustList<ImgPkPlayer>>> e_ = bean.getPartialFamiliesBaseNotCaught().getEntry(i);
            AbsPanel line_ = _api.getCompoFactory().newLineBox();
            formatMessageDir(_api,line_,e_.getKey());
            formatMessage(_api,MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NOTCAUGHTPKCAUGHTNOTPART);
            nextPart();
            for (CustList<ImgPkPlayer> s:e_.getValue()) {
                AbsPanel pageSub_ = _api.getCompoFactory().newLineBox();
                buildPkList(_api,s,pageSub_);
                feedParents(line_,pageSub_);
            }
            formatMessage(_api,MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NOTCAUGHTPKCAUGHTNOTPART);
            nextPart();
            int s_ = e_.getValue().size();
            for (int j = 0; j < s_; j++) {
                AbsPanel pageSub_ = _api.getCompoFactory().newLineBox();
                buildPkList(_api,bean.getPartialFamiliesBaseCaught().getValue(i).get(j),pageSub_);
                feedParents(line_,pageSub_);
            }
            feedParents(page_,line_);
        }
        return page_;
    }

    public static StringMap<String> file(AbstractProgramInfos _api) {
        return files(_api, MessagesPkBean.APP_BEAN).getVal(MessagesPkBean.GAMEPROG).getMapping();
    }
    @Override
    public String appName() {
        return MessagesPkBean.APP_BEAN;
    }
}
