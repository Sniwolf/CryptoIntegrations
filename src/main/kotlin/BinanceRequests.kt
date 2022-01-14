import com.google.gson.JsonIOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class BinanceRequests {

    fun updateCryptoPercentageChanges(request: OkHttpRequest, cryptoInfoMap: HashMap<String, CryptoInfo>, cryptoOps: CryptoMapOperations){

        for(crypto in cryptoInfoMap){
            getBitcoinPercentageChange(request, cryptoInfoMap[crypto.key], cryptoOps)
        }


    }

    private fun getBitcoinPercentageChange(request: OkHttpRequest, cryptoInfo: CryptoInfo?, cryptoOps: CryptoMapOperations){

        //Basic testing urls to Binance
//        val baseBinanceUrl = "https://api.binance.com/api/v3/ticker/24hr?symbol=BNBBTC"
//        val invalidSymbolUrlCase = "https://api.binance.com/api/v3/ticker/24hr?symbol=SSPBTC"


        //Binance get request
        if (cryptoInfo != null) {
            request.GET(cryptoInfo.getBinanceBTCSymbol(), object: Callback{

                override fun onFailure(call: Call, e: IOException) {
                    println("Request Failure to Binance")
                    println(e.stackTrace)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body?.string()

                    try{
                        val jsonData = JSONObject(responseData)

                        try{
                            val priceChangePercent = jsonData.get("priceChangePercent")
                            cryptoInfo.setPercentageAgainstBitcoin((priceChangePercent as String) + "%")
                        }catch (e: JSONException){
                            cryptoInfo.setPercentageAgainstBitcoin("Binance did not have info on the following URL: ${cryptoInfo.getBinanceBTCSymbol()}")
                        }

                        cryptoOps.printCryptoInfo(cryptoInfo)
                        response.body?.close()

                    }catch (e: JsonIOException){
                        e.printStackTrace()
                    }

                }



            })
        }
    }
}