Sääsovellus (Retrofit + OpenWeather API)
Tämä on Android-sovellus, joka hakee reaaliaikaisia säätietoja OpenWeatherMap API:sta käyttäen Retrofit-kirjastoa ja esittää ne Jetpack Compose -käyttöliittymässä.

Tekninen toteutus
1. HTTP-pyyntöjen hallinta (Retrofit)
Sovellus käyttää Retrofit-kirjastoa verkkopyyntöjen tekemiseen. Retrofit hoitaa yhteyden muodostamisen API-palvelimeen, polkujen hallinnan ja parametrien (kuten kaupungin nimi ja API-avain) liittämisen pyyntöön.

2. JSON-muunnos dataluokiksi (Gson)
Rajapinnalta saatava data on JSON-muodossa. Gson-konvertteri hoitaa automaattisesti JSON-vastauksen muuntamisen Kotlinin dataluokiksi (WeatherResponse). Tämä mahdollistaa tyyppitervetulon datan käsittelyn koodissa.

3. Coroutines (Taustasäikeistys)
API-kutsut suoritetaan Kotlin Coroutines -tekniikalla.

Verkkokutsut tehdään taustasäikeessä, jotta käyttöliittymä (UI) ei jumiudu haun aikana.

Kun data on haettu, se päivitetään UI-tilaan, mikä laukaisee näkymän uudelleenpiirtämisen.

4. UI-tila ja ViewModel
Sovellus noudattaa MVVM-arkkitehtuuria:

ViewModel hallitsee WeatherUiState-oliota, joka sisältää tiedon latauksesta, onnistumisesta tai virheestä.

Jetpack Compose -käyttöliittymä reagoi tilamuutoksiin automaattisesti. Kun ViewModelissa oleva tila muuttuu, Compose päivittää vain tarvittavat osat näkymästä.

5. API-avaimen suojaus
Turvallisuussyistä API-avainta ei ole kovakoodattu lähdekoodiin:

Avain on tallennettu local.properties -tiedostoon.

Gradle lukee avaimen ja vie sen BuildConfig-luokkaan.

Retrofit hakee avaimen BuildConfig.OPENWEATHER_API_KEY -muuttujasta pyyntöä tehdessään.

Käyttöohje
Syötä kaupungin nimi tekstikenttään (esim. "Oulu").

Paina "Hae sää" -painiketta.

Sovellus näyttää lämpötilan ja sääkuvauksen tai virheilmoituksen, jos kaupunkia ei löydy.
