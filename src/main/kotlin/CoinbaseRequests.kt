import com.google.gson.JsonIOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class CoinbaseRequests {

    fun getCurrencyExchangeRates(request: OkHttpRequest, userEnteredCurrencyCode: String?, listOfCurrencies: Map<String, String>){

        // Update the url string for coinbase with the users' currency of choice.
        val url = "https://api.coinbase.com/v2/exchange-rates?currency=$userEnteredCurrencyCode"

        val cryptoOps = CryptoMapOperations()
        val binanceRequest = BinanceRequests()

        request.GET(url, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Request Failure.")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()

                try{
                    val jsonData = JSONObject(responseData)

                    println("Coinbase Request Successful!")

                    val data = jsonData.getJSONObject("data")
                    val rates = data.getJSONObject("rates")

                    //Filter out the non crypto-currencies
                    val onlyCryptoRatesForTargetedCurrency = cryptoOps.filterOutCryptocurrencies(rates, listOfCurrencies)

                    //Build the initial map of crypto-currency info <currencyCode, cryptoInfo>
                    val cryptoMap = cryptoOps.buildCryptoMap(onlyCryptoRatesForTargetedCurrency, userEnteredCurrencyCode)

                    binanceRequest.updateCryptoPercentageChanges(request, cryptoMap, cryptoOps)



                }catch (e: JsonIOException){
                    e.printStackTrace()
                }
                response.body?.close()



            }

        })
    }
}