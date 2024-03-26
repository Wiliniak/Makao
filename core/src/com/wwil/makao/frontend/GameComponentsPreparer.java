package com.wwil.makao.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.wwil.makao.backend.MakaoBackend;
import com.wwil.makao.backend.Card;
import com.wwil.makao.backend.Rank;
import com.wwil.makao.backend.Suit;
import com.wwil.makao.frontend.cardChooserWindow.CardChooserGroup;

import java.util.Arrays;
import java.util.List;

//Przygotowanie elementów graficznych ekranu
public class GameComponentsPreparer {
    private final GameController controller;
    private final MakaoBackend backend;
    private final List<PlayerHandGroup> handGroups;
    private final CardActorFactory cardActorFactory;
    private final Stage stage;

    public GameComponentsPreparer(GameController controller, Stage stage) {
        this.controller = controller;
        this.backend = controller.getBackend();
        this.handGroups = controller.getHandGroups();
        this.cardActorFactory = controller.getCardActorFactory();
        this.stage = stage;
    }

    public void execute() {
        prepareStackCardsGroup();
        preparePullButton();
        prepareHandGroups();
        createCardChooser();
        createTextLabel();
    }

    private void prepareStackCardsGroup() {
        controller.addCardActorToStackGroup(cardActorFactory.createCardActor(backend.getStack().peekCard()));
        stage.addActor(controller.getStackCardsGroup());
        controller.getStackCardsGroup().setPosition(GUIparams.WIDTH / 2f, GUIparams.HEIGHT / 2f);
    }

    private void preparePullButton() {
        PullButtonActor pullButton = new PullButtonActor();
        pullButton.addListener(new PullButtonManager(pullButton, controller));
        stage.addActor(pullButton);
        pullButton.setPosition(GUIparams.WIDTH / 2f - 300, GUIparams.HEIGHT / 2f - 100);
        controller.setPullButtonActor(pullButton);
    }

    private void prepareHandGroups() {
        createHandGroups();
        adjustHumanCards();
        positionHandGroupsOnStage();
    }

    public void createHandGroups() {
        for (int i = 0; i < 4; i++) {
            handGroups.add(new PlayerHandGroup(backend.getPlayers().get(i)));
        }
        setPlayersCardActorsAlignmentParams();

        //todo metoda testowa:
        //test(3);

        for (PlayerHandGroup handGroup : handGroups) {
            for (Card card : handGroup.getPlayerHand().getCards()) {
                CardActor cardActor = controller.getCardActorFactory().createCardActor(card);
                handGroup.addActor(cardActor);
            }
        }
    }


//    private void test(int subject) {
//        handGroups.get(subject).getPlayerHand().getCards().clear();
//        handGroups.get(subject).getPlayerHand().addCardsToHand(Arrays.asList(new Card(Rank.J, Suit.CLUB),
//                new Card(Rank.J, Suit.SPADE), new Card(Rank.J, Suit.DIAMOND), new Card(Rank.J, Suit.HEART)));
//        Card jokerRed = new Card(Rank.JOKER, Suit.RED);
//        Card jokerBlack = new Card(Rank.JOKER, Suit.BLACK);
//        Card card3 = new Card(Rank.AS, Suit.SPADE);
//        Card card4 = new Card(Rank.FIVE, Suit.HEART);
//        handGroups.get(subject).getPlayerHand().addCardToHand(jokerRed);
//        handGroups.get(0).getPlayerHand().addCardToHand(jokerBlack);
//        //handGroups.get(subject).getPlayerHand().addCardToHand(card3);
//        handGroups.get(subject).getPlayerHand().addCardToHand(card4);
//    }

    private void setPlayersCardActorsAlignmentParams() {
        handGroups.get(0).setCardsAlignment(CardsAlignmentParams.SOUTH);
        handGroups.get(1).setCardsAlignment(CardsAlignmentParams.EAST);
        handGroups.get(2).setCardsAlignment(CardsAlignmentParams.NORTH);
        handGroups.get(3).setCardsAlignment(CardsAlignmentParams.WEST);
    }


    private void adjustHumanCards() {
        for (CardActor card : controller.getHumanHand().getCardActors()) {
            card.setUpSideDown(false);
            controller.getDragAndDropManager().prepareDragAndDrop(card);
        }
    }

    private void positionHandGroupsOnStage() {
        for (PlayerHandGroup handGroup : controller.getHandGroups()) {
            stage.addActor(handGroup);
        }
        setRotationOfHandGroups();
        setPositionOfHandGroups();
    }

    private void setRotationOfHandGroups() {
        controller.getHandGroups().get(1).setRotation(90);
        controller.getHandGroups().get(2).setRotation(180);
        controller.getHandGroups().get(3).setRotation(-90);
    }

    private void setPositionOfHandGroups() {
        //South
        controller.getHandGroups().get(0).setPosition
                (GUIparams.WIDTH / 2f,
                        0);
        //East
        controller.getHandGroups().get(1).setPosition(GUIparams.WIDTH + GUIparams.CARD_HEIGHT - 10,
                GUIparams.HEIGHT / 2.0f);
        //North
        controller.getHandGroups().get(2).setPosition(GUIparams.WIDTH / 2f + GUIparams.CARD_WIDTH,
                GUIparams.HEIGHT + GUIparams.CARD_HEIGHT - 25);
        //West
        controller.getHandGroups().get(3).setPosition(GUIparams.CARD_WIDTH / 5f - 32,
                GUIparams.HEIGHT / 2f + GUIparams.CARD_HEIGHT / 2f + 25);
    }

    private void createCardChooser() {
        CardChooserGroup cardChooser = new CardChooserGroup(controller);
        stage.addActor(cardChooser);
        cardChooser.setPosition(0, 0);
        controller.setCardChooser(cardChooser);
    }

    private void createTextLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("fonts/messageFont.fnt"));
        labelStyle.fontColor = Color.valueOf("151515");
        //https://libgdx.com/wiki/graphics/2d/fonts/bitmap-fonts

        Label label = new Label("Player 1 is demanding 8 DIAMOND",labelStyle);
        label.setSize(15,15);
        label.setPosition(GUIparams.WIDTH/2f+75,GUIparams.HEIGHT/2f + 275);
        label.setAlignment(Align.center);
        stage.addActor(label);
    }
}
