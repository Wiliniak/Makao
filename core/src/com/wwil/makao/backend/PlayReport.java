package com.wwil.makao.backend;

//Raport karty, którą zagrał gracz lub dobrania
public class PlayReport {
    private final PlayerHand playerHand;
    private final AbilityReport abilityReport;
    private final Play play;
    private final Card drawn;
    private final boolean isCardCorrect;
    private final boolean block;

    public PlayReport(PlayerHand playerHand, AbilityReport abilityReport, Play play, Card drawn, boolean isCardCorrect, boolean block) {
        this.playerHand = playerHand;
        this.abilityReport = abilityReport;
        this.play = play;
        this.drawn = drawn;
        this.isCardCorrect = isCardCorrect;
        this.block = block;
    }

    public AbilityReport getAbilityReport() {
        return abilityReport;
    }

    public boolean isBlock() {
        return block;
    }

    public Card getDrawn() {
        return drawn;
    }

    public Play getPlay() {
        return play;
    }

    public boolean isCardCorrect() {
        return isCardCorrect;
    }

}
