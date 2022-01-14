import org.json.JSONObject

class CryptoMapOperations {

    fun filterOutCryptocurrencies(jsonOfCurrentRates: JSONObject, nonCryptoCurrencies: Map<String, String>): Map<String, Any>{

        val cryptos = jsonOfCurrentRates.toMap()

        for(currency in nonCryptoCurrencies){
            if(cryptos.containsKey(currency.key)){
                cryptos.remove(currency.key)
            }
        }

        return cryptos
    }

    fun buildCryptoMap(cryptoCurrentRates: Map<String, Any>, userEnteredCurrencyCode: String?): HashMap<String, CryptoInfo>{

        val cryptoInfoMap = hashMapOf<String, CryptoInfo>()

        for(crypto in cryptoCurrentRates){
            val cryptoInfo = CryptoInfo(
                crypto.key,
                userEnteredCurrencyCode,
                cryptoCurrentRates[crypto.key],
                "")
            cryptoInfo.updateBinanceURL(crypto.key+"BTC")
            cryptoInfoMap[crypto.key] = cryptoInfo
        }

        return cryptoInfoMap
    }

    fun printCryptoInfo(cryptoInfo: CryptoInfo){
        print("Crypto Currency Code: ${cryptoInfo.getCurrencyCode()} ,")
        print("Current Exchange Rate: 1 ${cryptoInfo.getUserEnteredCurrencyCode()} = ${cryptoInfo.getCurrentExchangeRate()} ${cryptoInfo.getCurrencyCode()}, ")
        print("Binance API URL: ${cryptoInfo.getBinanceBTCSymbol()}, ")
        println("Percentage Change in last 24 hours compared to BitCoin(BTC): ${cryptoInfo.getPercentageAgainstBitcoin()}")

    }
}