# Crypto Integrations

Crypto Integrations is a small integration program used to gather information
about various cryptocurrencies from sources such as Binance and Coinbase. The
user enters a valid country code as defined in ISO-4217 
(https://developers.google.com/adsense/management/appendix/currencies), such
as USD for US Dollar. CryptoIntegrations then uses Coinbase's API to get the
current exchange rate for 1 of the entered currency with all the 
cryptocurrencies that currently available in the Coinbase market. It then
attempts to compare the percentage change of the cryptocurrencies vs Bitcoin
from the last 24 hours. 

For example, if the user enters USD then one entry returned to the user may
look like:

Crypto Currency Code: DOGE,
Current Exchange Rate: 1 USD = 5.295207836907599 DOGE, 
Binance API URL: https://api.binance.com/api/v3/ticker/24hr?symbol=DOGEBTC, 
Percentage Change in last 24 hours compared to BitCoin(BTC): 11.735%

Where the cryptocurrency code, the current exchange rate for the entered 
currency, and the percentage change of that crypto vs BitCoin are given. Also
given is the API endpoint being used for Binance to get the percentage change
of the cryptocurrency. 

## Getting Started
This project was written in Kotlin and uses Gradle to build the project. Once
you have cloned this repository to your machine you can use either Intellij 
to run the project or you can run it from the command line. 

To run from the
command line start by navigating to the directory and then run the following
command to have gradle build the project:

```
gradlew build
```

Next you will use the following command to run the program:

```
gradlew run 
```
You will see the following message if the program was built successfully:
```
Reading currencies csv
Please enter the currency code you would like view:
```

Here you can enter the currency code that you would like to get cryptocurrency
information against. 