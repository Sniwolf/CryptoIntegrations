import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Files
import java.nio.file.Paths

class ReadCurrenciesCSV {

    fun readCsvOfCurrencies(): Map<String, String>{
        val currenciesPath = "currencies.csv"

        val reader = Files.newBufferedReader(Paths.get(currenciesPath))

        val csvParser = CSVParser(reader, CSVFormat.DEFAULT)

        val currencyMap = hashMapOf<String, String>()

        for (csvRecord in csvParser) {


            // Accessing Values by Column Index
            val currencyCode = csvRecord.get(0)
            val currencyName = csvRecord.get(1)

            currencyMap[currencyCode] = currencyName
        }

        return currencyMap
    }
}