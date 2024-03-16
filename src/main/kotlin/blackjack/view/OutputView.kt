package blackjack.view

import blackjack.model.card.Card
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.participant.Role

class OutputView {
    fun printInitCard(
        dealer: Dealer,
        players: Players,
    ) {
        val playersNameMessage = players.playerGroup.map { it.name }.joinToString(PLAYERS_NAME_SEPARATOR)
        lineBreak()
        println(DIVIDE_CARD_FINISH_MESSAGE.format(playersNameMessage))
        println("딜러: ${dealer.getDealerInitCardsMessage()}")

        players.playerGroup.forEach {
            println(it.getCardsMessage(it.name.toString()))
        }
        lineBreak()
    }

    fun printDealerAdditionalCardMessage() {
        lineBreak()
        println(DEALER_ADDITIONAL_CARD_MESSAGE)
    }

    fun printPlayerCardsMessage(player: Player) {
        println(player.getCardsMessage(player.name.toString()))
    }

    fun printPlayersCardResult(
        dealer: Dealer,
        players: Players,
    ) {
        lineBreak()
        println(dealer.getCardsMessage(DEALER_NAME_MESSAGE) + dealer.getPlayerCardResult())
        players.playerGroup.forEach {
            println(it.getCardsMessage(it.name.toString()) + it.getPlayerCardResult())
        }
        lineBreak()
    }

    private fun Role.getDealerInitCardsMessage(): String {
        return getCards()[0].toCardMessage()
    }

    private fun Role.getCardsMessage(name: String): String {
        return "${name}카드: ${getCards().joinToString(separator = CARDS_SEPARATOR, transform = { it.toCardMessage() })}"
    }

    private fun Card.toCardMessage() = "${denomination.value}${suite.value}"

    private fun Role.getPlayerCardResult() = " - 결과: ${getCardSum()}"

    private fun lineBreak() = println()

    companion object {
        private const val PLAYERS_NAME_SEPARATOR = ", "
        private const val CARDS_SEPARATOR = ", "
        private const val DIVIDE_CARD_FINISH_MESSAGE = "딜러와 %s에게 2장의 나누었습니다."
        private const val DEALER_ADDITIONAL_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_NAME_MESSAGE = "딜러 "
    }
}
