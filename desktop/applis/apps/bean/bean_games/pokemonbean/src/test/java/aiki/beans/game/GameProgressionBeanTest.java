package aiki.beans.game;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringMap;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameProgressionBeanTest extends InitDbGameProgressionBean {
    static final String GIRL = "G";
    static final String BOY = "B";
    static final String MONEY_REM = "20";

    @Test
    public void nickname1() {
        assertEq(GIRL, callGameProgressionBeanNicknameGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void nickname2() {
        assertEq(BOY, callGameProgressionBeanNicknameGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void heroImage1() {
        assertEq(one(IMG_H_1), callGameProgressionBeanHeroImageGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void heroImage2() {
        assertEq(one(IMG_H_2), callGameProgressionBeanHeroImageGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void heroImageOppositeSex1() {
        assertEq(one(IMG_H_2), callGameProgressionBeanHeroImageOppositeSexGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void heroImageOppositeSex2() {
        assertEq(one(IMG_H_1), callGameProgressionBeanHeroImageOppositeSexGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void finish1() {
        assertFalse(callGameProgressionBeanFinishedGameGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void finish2() {
        assertFalse(callGameProgressionBeanFinishedGameGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void finish3() {
        assertTrue(callGameProgressionBeanFinishedGameGet(beanProg(EN, finish(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void finish4() {
        assertTrue(callGameProgressionBeanFinishedGameGet(beanProg(EN, finish(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void endGameImage1() {
        assertEq(one(IMG_H_5), callGameProgressionBeanEndGameImageGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void endGameImage2() {
        assertEq(one(IMG_H_5), callGameProgressionBeanEndGameImageGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void repel1() {
        assertEq(15, callGameProgressionBeanRemainStepsRepelGet(beanProg(EN, incomplete(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void repel2() {
        assertEq(15, callGameProgressionBeanRemainStepsRepelGet(beanProg(EN, incomplete(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void money1() {
        assertEq(LgInt.newLgInt(MONEY_REM), callGameProgressionBeanMoneyGet(beanProg(EN, incomplete(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void money2() {
        assertEq(LgInt.newLgInt(MONEY_REM), callGameProgressionBeanMoneyGet(beanProg(EN, incomplete(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void eggs1() {
        assertEq(1, callGameProgressionBeanNbRemainingEggsGet(beanProg(EN, incomplete(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void eggs2() {
        assertEq(1, callGameProgressionBeanNbRemainingEggsGet(beanProg(EN, incomplete(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void nbRemainingNotMaxLevel1() {
        assertEq(1, callGameProgressionBeanNbRemainingNotMaxLevelGet(beanProg(EN, incomplete(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void nbRemainingNotMaxLevel2() {
        assertEq(1, callGameProgressionBeanNbRemainingNotMaxLevelGet(beanProg(EN, incomplete(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void nbRemainingNotMaxHappiness1() {
        assertEq(1, callGameProgressionBeanNbRemainingNotMaxHappinessGet(beanProg(EN, incomplete(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void nbRemainingNotMaxHappiness2() {
        assertEq(1, callGameProgressionBeanNbRemainingNotMaxHappinessGet(beanProg(EN, incomplete(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void unvisitedPlaces1() {
        assertSizeEq(1,callGameProgressionBeanUnVisitedPlacesGet(beanProg(EN, visit(progressPlaces(), GIRL, Sex.GIRL))));
    }

    @Test
    public void unvisitedPlaces2() {
        assertSizeEq(1,callGameProgressionBeanUnVisitedPlacesGet(beanProg(EN, visit(progressPlaces(), BOY, Sex.BOY))));
    }

    @Test
    public void unvisitedPlaces3() {
        assertEq(CI_2,elt(callGameProgressionBeanUnVisitedPlacesGet(beanProg(EN, visit(progressPlaces(), GIRL, Sex.GIRL))),0));
    }

    @Test
    public void unvisitedPlaces4() {
        assertEq(CI_2,elt(callGameProgressionBeanUnVisitedPlacesGet(beanProg(EN, visit(progressPlaces(), BOY, Sex.BOY))),0));
    }

    @Test
    public void visitedPlaces1() {
        assertSizeEq(1,callGameProgressionBeanVisitedPlacesGet(beanProg(EN, visit(progressPlaces(), GIRL, Sex.GIRL))));
    }

    @Test
    public void visitedPlaces2() {
        assertSizeEq(1,callGameProgressionBeanVisitedPlacesGet(beanProg(EN, visit(progressPlaces(), BOY, Sex.BOY))));
    }

    @Test
    public void visitedPlaces3() {
        assertEq(CI_1,elt(callGameProgressionBeanVisitedPlacesGet(beanProg(EN, visit(progressPlaces(), GIRL, Sex.GIRL))),0));
    }

    @Test
    public void visitedPlaces4() {
        assertEq(CI_1,elt(callGameProgressionBeanVisitedPlacesGet(beanProg(EN, visit(progressPlaces(), BOY, Sex.BOY))),0));
    }

    @Test
    public void partial1() {
        assertSizeEq(1,callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void partial2() {
        assertSizeEq(1,callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void partial3() {
        assertEq(PROG_PK_TR1,firstFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void partial4() {
        assertEq(PROG_PK_TR1,firstFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void partial5() {
        assertSizeEq(2,secondFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void partial6() {
        assertSizeEq(2,secondFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void partial7() {
        assertSizeEq(0,eltFam2(secondFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)),0));
    }

    @Test
    public void partial8() {
        assertSizeEq(0,eltFam2(secondFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)),0));
    }

    @Test
    public void partial9() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)),1));
    }

    @Test
    public void partial10() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)),1));
    }

    @Test
    public void partial11() {
        assertEq(PROG_PK2,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)),1),0));
    }

    @Test
    public void partial12() {
        assertEq(PROG_PK2,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)),1),0));
    }

    @Test
    public void not1() {
        assertSizeEq(2,callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void not2() {
        assertSizeEq(2,callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void not3() {
        assertEq(PROG_PK_TR3,firstFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void not4() {
        assertEq(PROG_PK_TR3,firstFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void not5() {
        assertSizeEq(2,secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void not6() {
        assertSizeEq(2,secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void not7() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)),0));
    }

    @Test
    public void not8() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)),0));
    }

    @Test
    public void not9() {
        assertEq(PROG_PK3,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)),0),0));
    }

    @Test
    public void not10() {
        assertEq(PROG_PK3,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)),0),0));
    }
    @Test
    public void not11() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)),1));
    }

    @Test
    public void not12() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)),1));
    }

    @Test
    public void not13() {
        assertEq(PROG_PK4,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), GIRL, Sex.GIRL))),0)),1),0));
    }

    @Test
    public void not14() {
        assertEq(PROG_PK4,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanNotAtAllFamiliesBaseGet(beanProg(EN, fac(progress(), BOY, Sex.BOY))),0)),1),0));
    }

    @Test
    public void all1() {
        assertSizeEq(3,callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), GIRL, Sex.GIRL))));
    }

    @Test
    public void all2() {
        assertSizeEq(3,callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), BOY, Sex.BOY))));
    }

    @Test
    public void all3() {
        assertEq(PROG_PK_TR1,firstFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void all4() {
        assertEq(PROG_PK_TR1,firstFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void all5() {
        assertSizeEq(2,secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void all6() {
        assertSizeEq(2,secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void all7() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), GIRL, Sex.GIRL))),0)),0));
    }

    @Test
    public void all8() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), BOY, Sex.BOY))),0)),0));
    }

    @Test
    public void all9() {
        assertEq(PROG_PK1,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), GIRL, Sex.GIRL))),0)),0),0));
    }

    @Test
    public void all10() {
        assertEq(PROG_PK1,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), BOY, Sex.BOY))),0)),0),0));
    }
    @Test
    public void all11() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), GIRL, Sex.GIRL))),0)),1));
    }

    @Test
    public void all12() {
        assertSizeEq(1,eltFam2(secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), BOY, Sex.BOY))),0)),1));
    }

    @Test
    public void all13() {
        assertEq(PROG_PK2,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), GIRL, Sex.GIRL))),0)),1),0));
    }

    @Test
    public void all14() {
        assertEq(PROG_PK2,elt(eltFam2(secondFam(eltFam(callGameProgressionBeanFullFamiliesBaseGet(beanProg(EN, finish(progress(), BOY, Sex.BOY))),0)),1),0));
    }

    @Test
    public void unbeat1() {
        assertSizeEq(1,callGameProgressionBeanUnBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), GIRL, Sex.GIRL))));
    }

    @Test
    public void unbeat2() {
        assertSizeEq(1,callGameProgressionBeanUnBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), BOY, Sex.BOY))));
    }

    @Test
    public void unbeat3() {
        assertEq(SECOND_TRAINER,callTrainerPlaceNamesGetTrainer(eltTr(callGameProgressionBeanUnBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void unbeat4() {
        assertEq(SECOND_TRAINER,callTrainerPlaceNamesGetTrainer(eltTr(callGameProgressionBeanUnBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void unbeat5() {
        assertEq(CI_2,callTrainerPlaceNamesGetPlace(eltTr(callGameProgressionBeanUnBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void unbeat6() {
        assertEq(CI_2,callTrainerPlaceNamesGetPlace(eltTr(callGameProgressionBeanUnBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void beat1() {
        assertSizeEq(1,callGameProgressionBeanBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), GIRL, Sex.GIRL))));
    }

    @Test
    public void beat2() {
        assertSizeEq(1,callGameProgressionBeanBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), BOY, Sex.BOY))));
    }

    @Test
    public void beat3() {
        assertEq(FIRST_TRAINER,callTrainerPlaceNamesGetTrainer(eltTr(callGameProgressionBeanBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void beat4() {
        assertEq(FIRST_TRAINER,callTrainerPlaceNamesGetTrainer(eltTr(callGameProgressionBeanBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void beat5() {
        assertEq(CI_1,callTrainerPlaceNamesGetPlace(eltTr(callGameProgressionBeanBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void beat6() {
        assertEq(CI_1,callTrainerPlaceNamesGetPlace(eltTr(callGameProgressionBeanBeatenImportantTrainersGet(beanProg(EN, trainer(progressTrainers(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void ord1() {
        assertSizeEq(1,callGameProgressionBeanRemainingOtherTrainerPlacesGet(beanProg(EN, visit(progressOrdTrainers(), GIRL, Sex.GIRL))));
    }

    @Test
    public void ord2() {
        assertSizeEq(1,callGameProgressionBeanRemainingOtherTrainerPlacesGet(beanProg(EN, visit(progressOrdTrainers(), BOY, Sex.BOY))));
    }

    @Test
    public void ord3() {
        assertEq(2,firstPlaceNamePk(eltPlaceNamePk(callGameProgressionBeanRemainingOtherTrainerPlacesGet(beanProg(EN, visit(progressOrdTrainers(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void ord4() {
        assertEq(2,firstPlaceNamePk(eltPlaceNamePk(callGameProgressionBeanRemainingOtherTrainerPlacesGet(beanProg(EN, visit(progressOrdTrainers(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void ord5() {
        assertEq(1,secondPlaceNamePk(eltPlaceNamePk(callGameProgressionBeanRemainingOtherTrainerPlacesGet(beanProg(EN, visit(progressOrdTrainers(), GIRL, Sex.GIRL))),0)));
    }

    @Test
    public void ord6() {
        assertEq(1,secondPlaceNamePk(eltPlaceNamePk(callGameProgressionBeanRemainingOtherTrainerPlacesGet(beanProg(EN, visit(progressOrdTrainers(), BOY, Sex.BOY))),0)));
    }

    @Test
    public void pl1() {
        assertEq(RO_1,callGameProgressionBeanGetRemainingOtherTrainersPlaceName(beanProg(EN, visit(progressOrdTrainers(), GIRL, Sex.GIRL)),0));
    }

    @Test
    public void pl2() {
        assertEq(RO_1,callGameProgressionBeanGetRemainingOtherTrainersPlaceName(beanProg(EN, visit(progressOrdTrainers(), BOY, Sex.BOY)),0));
    }

    @Test
    public void keyPk1() {
        assertSizeEq(1,callGameProgressionBeanGetKeyPokemon(beanProg(EN, fac(progress(), GIRL, Sex.GIRL)),0,0));
    }

    @Test
    public void keyPk2() {
        assertSizeEq(1,callGameProgressionBeanGetKeyPokemon(beanProg(EN, fac(progress(), BOY, Sex.BOY)),0,0));
    }

    @Test
    public void keyPk3() {
        assertEq(PROG_PK1,elt(callGameProgressionBeanGetKeyPokemon(beanProg(EN, fac(progress(), GIRL, Sex.GIRL)),0,0),0));
    }

    @Test
    public void keyPk4() {
        assertEq(PROG_PK1,elt(callGameProgressionBeanGetKeyPokemon(beanProg(EN, fac(progress(), BOY, Sex.BOY)),0,0),0));
    }

    @Test
    public void trPart1() {
        assertEq(PROG_PK_TR1,callGameProgressionBeanGetTrPokemonPartial(beanProg(EN, fac(progress(), GIRL, Sex.GIRL)),0,0,0));
    }

    @Test
    public void trPart2() {
        assertEq(PROG_PK_TR1,callGameProgressionBeanGetTrPokemonPartial(beanProg(EN, fac(progress(), BOY, Sex.BOY)),0,0,0));
    }

    @Test
    public void imgPart1() {
        assertEq(one(IMG_PR_1),callGameProgressionBeanGetImagePokemonPartial(beanProg(EN, fac(progress(), GIRL, Sex.GIRL)),0,0,0));
    }

    @Test
    public void imgPart2() {
        assertEq(one(IMG_PR_1),callGameProgressionBeanGetImagePokemonPartial(beanProg(EN, fac(progress(), BOY, Sex.BOY)),0,0,0));
    }

    @Test
    public void trPartNot1() {
        assertEq(PROG_PK_TR2,callGameProgressionBeanGetTrPokemonPartialNot(beanProg(EN, fac(progress(), GIRL, Sex.GIRL)),0,1,0));
    }

    @Test
    public void trPartNot2() {
        assertEq(PROG_PK_TR2,callGameProgressionBeanGetTrPokemonPartialNot(beanProg(EN, fac(progress(), BOY, Sex.BOY)),0,1,0));
    }

    @Test
    public void imgPartNot1() {
        assertEq(one(IMG_PR_2),callGameProgressionBeanGetImagePokemonPartialNot(beanProg(EN, fac(progress(), GIRL, Sex.GIRL)),0,1,0));
    }

    @Test
    public void imgPartNot2() {
        assertEq(one(IMG_PR_2),callGameProgressionBeanGetImagePokemonPartialNot(beanProg(EN, fac(progress(), BOY, Sex.BOY)),0,1,0));
    }

    @Test
    public void trNotAll1() {
        assertEq(PROG_PK_TR4,callGameProgressionBeanGetTrPokemonNotAll(beanProg(EN, fac(progress(), GIRL, Sex.GIRL)),0,1,0));
    }

    @Test
    public void trNotAll2() {
        assertEq(PROG_PK_TR4,callGameProgressionBeanGetTrPokemonNotAll(beanProg(EN, fac(progress(), BOY, Sex.BOY)),0,1,0));
    }

    @Test
    public void imgNotAll1() {
        assertEq(one(IMG_PR_4),callGameProgressionBeanGetImagePokemonNotAll(beanProg(EN, fac(progress(), GIRL, Sex.GIRL)),0,1,0));
    }

    @Test
    public void imgNotAll2() {
        assertEq(one(IMG_PR_4),callGameProgressionBeanGetImagePokemonNotAll(beanProg(EN, fac(progress(), BOY, Sex.BOY)),0,1,0));
    }

    @Test
    public void trAll1() {
        assertEq(PROG_PK_TR1,callGameProgressionBeanGetTrPokemonFull(beanProg(EN, finish(progress(), GIRL, Sex.GIRL)),0,0,0));
    }

    @Test
    public void trAll2() {
        assertEq(PROG_PK_TR1,callGameProgressionBeanGetTrPokemonFull(beanProg(EN, finish(progress(), BOY, Sex.BOY)),0,0,0));
    }

    @Test
    public void imgAll1() {
        assertEq(one(IMG_PR_1),callGameProgressionBeanGetImagePokemonFull(beanProg(EN, finish(progress(), GIRL, Sex.GIRL)),0,0,0));
    }

    @Test
    public void imgAll2() {
        assertEq(one(IMG_PR_1),callGameProgressionBeanGetImagePokemonFull(beanProg(EN, finish(progress(), BOY, Sex.BOY)),0,0,0));
    }

    private FacadeGame trainer(DataBase _init, String _nickname,Sex _s) {
        FacadeGame facadeGame_ = visit(_init, _nickname, _s);
        facadeGame_.getGame().beatGymLeader(newCoords(0,0,0,0,0,0));
        return facadeGame_;
    }

    private FacadeGame visit(DataBase _init, String _nickname,Sex _s) {
        FacadeGame facadeGame_ = common(_init);
        Game game_ = new Game(_init);
        game_.initUserInteract(_nickname, _s, game_.getDifficulty(), _init);
        game_.setPlayerOrientation(Direction.UP);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }
    private FacadeGame incomplete(DataBase _init, String _nickname,Sex _s) {
        _init.addConstNumTest(DataBase.NIVEAU_PK_MAX, Rate.newRate("10"));
        _init.addConstNumTest(DataBase.MAX_BONHEUR, Rate.newRate("128"));
        FacadeGame fac_ = fac(_init, _nickname, _s);
        fac_.getGame().getPlayer().setRemainingRepelSteps(15);
        fac_.getGame().getPlayer().setMoney(LgInt.newLgInt(MONEY_REM));
        fac_.getGame().getPlayer().recupererOeufPensions(Egg.newEgg(PROG_PK1));
        return fac_;
    }
    private FacadeGame finish(DataBase _init, String _nickname,Sex _s) {
        FacadeGame fac_ = fac(_init, _nickname, _s);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK1, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK2, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK3, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK4, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK5, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK6, BoolVal.TRUE);
        PokemonPlayer pk_ = fac_.getGame().getPlayer().getPokemonPlayerList().getValue(0);
        pk_.setLevel( fac_.getData().getMaxLevel());
        pk_.setHappiness( fac_.getData().getHappinessMax());
        return fac_;
    }
    private FacadeGame fac(DataBase _init, String _nickname,Sex _s) {
        FacadeGame facadeGame_ = common(_init);
        Game game_ = new Game(_init);
        game_.initUtilisateur(_nickname, _s, game_.getDifficulty(), _init);
        game_.setPlayerOrientation(Direction.UP);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }

    private FacadeGame common(DataBase _init) {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(_init);
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.put(EN,EN);
        facadeGame_.setLanguages(indexes());
        facadeGame_.setDisplayLanguages(displayLanguages_);
        facadeGame_.setLanguage(EN);
        facadeGame_.setZipName("zip");
        facadeGame_.setLanguages(indexes());
        _init.setMessages(facadeGame_.getData());
        facadeGame_.setLoadedData(true);
        return facadeGame_;
    }
}
