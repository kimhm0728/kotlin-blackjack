package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardProvider

abstract class Role(private val handCards: HandCards) {
    abstract fun decideMoreCard(): Boolean

    fun receiveCard(cards: List<Card>) {
        handCards.add(cards)
    }

    fun initCard(cardProvider: CardProvider) {
        receiveCard(Card.provideCards(cardProvider, INIT_RECEIVE_CARD_COUNT))
    }

    fun isBurst() = handCards.score(BLACKJACK_VALUE) > BLACKJACK_VALUE

    fun isBlackjack(): Boolean {
        return handCards.score(BLACKJACK_VALUE) == BLACKJACK_VALUE &&
            handCards.size == BLACKJACK_COUNT
    }

    fun isMaxCardSum(): Boolean {
        return handCards.score(BLACKJACK_VALUE) == BLACKJACK_VALUE
    }

    fun score() = handCards.score(BLACKJACK_VALUE)

    fun cards() = handCards.cards

    companion object {
        private const val BLACKJACK_VALUE = 21
        private const val BLACKJACK_COUNT = 2
        private const val INIT_RECEIVE_CARD_COUNT = 2
    }
}
