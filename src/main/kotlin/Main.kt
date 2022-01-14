import okhttp3.OkHttpClient
import java.util.*

fun main(){

    println("Reading currencies csv")
    val listOfCurrencies = ReadCurrenciesCSV().readCsvOfCurrencies()

    var readUserInputFlag = true

    var userEnteredCurrencyCode = ""

    val scanner = Scanner(System.`in`)

    while(readUserInputFlag){

        println("Please enter the currency code you would like view:")

        userEnteredCurrencyCode = scanner.nextLine()

        if(listOfCurrencies.containsKey(userEnteredCurrencyCode.uppercase())){
            println("You have entered: $userEnteredCurrencyCode".uppercase())
            readUserInputFlag = false
        }else{
            println("You have entered an invalid currency code, please try again")
        }

    }

    val client = OkHttpClient()
    val request = OkHttpRequest(client)

    CoinbaseRequests().getCurrencyExchangeRates(request, userEnteredCurrencyCode, listOfCurrencies)

}

