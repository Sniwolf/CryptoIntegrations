import org.json.JSONObject
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CryptoMapOperationsTest {

    private val testReadCurrenciesCSV: ReadCurrenciesCSV = ReadCurrenciesCSV()
    private val testCurrencies = testReadCurrenciesCSV.readCsvOfCurrencies()
    private val testCoinbaseResponse: JSONObject = JSONObject("{\"AED\":\"3.6731\",\"AFN\":\"105.025\",\"ALL\":\"106.75\",\"BTC\":\"2.290900098084888e-05\",\"ETH\":\"0.000296417937435066\",\"SUSHI\":\"0.14074595355383532\",\"DOGE\":\"6.218905472636816\",\"SHIB\":\"31897.926634768737\"}")
    private val testCryptoMapOperations = CryptoMapOperations()

    //AFN and ALL are currency codes for Afghanistan's Afghani (AFN) and Albania's Lek (ALL) but they are not in the list of currency codes at
    //https://developers.google.com/adsense/management/appendix/currencies so we are considering them crypto for the sake of this test
    private val expectedCryptoMap = mutableMapOf(Pair("BTC", "2.290900098084888e-05"),
        Pair("ETH", "0.000296417937435066"),
        Pair("SUSHI", "0.14074595355383532"),
        Pair("DOGE", "6.218905472636816"),
        Pair("SHIB", "31897.926634768737"),
        Pair("AFN", "105.025"),
        Pair("ALL", "106.75")
    )

    private val cryptoMapForBuildCryptoMapTest = mutableMapOf(Pair("DOGE", "6.218905472636816"))

    @Test
    fun filterOutCryptocurrencies() {
        assertEquals(expectedCryptoMap, testCryptoMapOperations.filterOutCryptocurrencies(testCoinbaseResponse,testCurrencies))
    }

    @Test
    fun buildCryptoMap() {
        val testCryptoInfoA = CryptoInfo(
            "DOGE",
            "USD",
            "6.218905472636816",
            "14.921")
        testCryptoInfoA.updateBinanceURL("DOGEBTC")

        val testCryptoMap = testCryptoMapOperations.buildCryptoMap(cryptoMapForBuildCryptoMapTest, "USD")
        val testCryptoInfoInMap = testCryptoMap["DOGE"]
        testCryptoInfoInMap?.setPercentageAgainstBitcoin("14.921")

        val testCurrencyCode = testCryptoInfoInMap?.getCurrencyCode()
        val testUserEnteredCurrencyCode = testCryptoInfoInMap?.getUserEnteredCurrencyCode()
        val testBinanceSymbol = testCryptoInfoInMap?.getBinanceBTCSymbol()
        val testCurrentExchangeRate = testCryptoInfoInMap?.getCurrentExchangeRate()
        val testPercentageAgainstBitCoin = testCryptoInfoInMap?.getPercentageAgainstBitcoin()

        assertEquals(testCryptoInfoA.getCurrencyCode(), testCurrencyCode)
        assertEquals(testCryptoInfoA.getBinanceBTCSymbol(), testBinanceSymbol)
        assertEquals(testCryptoInfoA.getUserEnteredCurrencyCode(), testUserEnteredCurrencyCode)
        assertEquals(testCryptoInfoA.getCurrentExchangeRate(), testCurrentExchangeRate)
        assertEquals(testCryptoInfoA.getPercentageAgainstBitcoin(), testPercentageAgainstBitCoin)

    }
}