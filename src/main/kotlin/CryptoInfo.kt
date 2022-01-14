class CryptoInfo(currencyCode: String, userEnteredCurrencyCode: String?, currentExchangeRate: Any?, percentageAgainstBitcoin: Any) {

    private var currencyCode = currencyCode
    private var userEnteredCurrencyCode = userEnteredCurrencyCode
    private var currentExchangeRate = currentExchangeRate
    private var percentageAgainstBitcoin = percentageAgainstBitcoin
    private var binanceBtcUrl = "https://api.binance.com/api/v3/ticker/24hr?symbol="

    fun setPercentageAgainstBitcoin(percentage: String){
        this.percentageAgainstBitcoin = percentage
    }

    fun updateBinanceURL(binanceSymbol: String){
        this.binanceBtcUrl = this.binanceBtcUrl+binanceSymbol
    }

    fun getCurrencyCode(): String{
        return this.currencyCode
    }

    fun getCurrentExchangeRate(): Any?{
        return this.currentExchangeRate
    }

    fun getPercentageAgainstBitcoin(): Any {
        return this.percentageAgainstBitcoin
    }

    fun getUserEnteredCurrencyCode(): String?{
        return this.userEnteredCurrencyCode
    }

    fun getBinanceBTCSymbol(): String{
        return this.binanceBtcUrl
    }
}