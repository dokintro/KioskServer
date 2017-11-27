
# Use Cases

## Use case diagram
|             | Use case:     | Bruger | Gæst | 
|-------------|---------------|--------|------|
| Use case 1: | Opret bruger: |        |   x  |
| Use case 2: | Login: |   x    |     |
| Use case 3: | Oprette events: |   x    |     |
| Use case 4: | Opdatere egne events: |   x    |     |
| Use case 5: | Se liste med events: |   x    |     |
| Use case 6: | Se deltagerliste på valgte events: |   x    |     |
| Use case 7: | Slette egne events: |   x    |     |
| Use case 8: | Log ud: |   x    |     |

## Use cases med beskrivelser

- **Use case 1 - Opret bruger:**
	- **Beskrivelse:** Gæsten kan oprette en ny bruger med følgende oplysninger: fornavn, efternavn, e-mail, kodeord, køn og alder. 
	- **Forudsætninger:** -
	- **Uddybende beskrivelse:**
		1. Applikationen starter op, og gæsten får vist en brugerflade, hvor der er mulighed for at logge ind eller oprette en ny bruger.
		2. Gæsten vælger at oprette en ny bruger og bliver ført videre til en ny brugerflade.
		3. Gæsten indtaster de påkrævede informationer og bliver herefter oprettet i systemet.
		4. Gæsten bliver ført tilbage til startsiden, og har nu mulighed for at logge ind. 
	- **Supplerende oplysninger:** -

- **Use case 2 - Login:**
	- **Beskrivelse:** Brugeren skal kunne logge ind efter at have oprettet sig i systemet.
	- **Forudsætninger:** For at kunne logge ind skal man være oprettet som bruger. Derudover skal indtastede username (e-mail) og password stemme overens.
	- **Uddybende beskrivelse:**
		1. Systemet viser startsiden, hvor en bruger kan indtaste brugernavn og password.
		2. Hvis brugernavn og password matcher en eksisterende konto, er det muligt at logge ind.
	- **Supplerende oplysninger:** Brugernavnet (e-mail) skal være unikt.

- **Use case 3 - Oprette events:**
	- **Beskrivelse:** Brugeren skal kunne oprette egne events.
	- **Forudsætninger:** Man skal være logget ind.
	- **Uddybende beskrivelse:**
		1. En bruger skal kunne oprette et event med navn, dato, billede, pris og beskrivelse.
	- **Supplerende oplysninger:**
		1. Når eventet er oprettet el. aflyst skal ejeren få en be-/afkræftigelsesbesked på skærmen.
		2. Andre brugere skal kunne tilmelde sig et arrangement.
		3. Det skal være muligt at vælge at have et maksimum på antal tilmeldte.
		4. Prisen skal kun være oplyst, og skal kunne betales via eksempelvis mobilepay. 

- **Use case 4 - Opdatere egne events:**
	- **Beskrivelse:** Brugeren skal kunne opdatere sine egne events 
	- **Forudsætninger:** Man skal være logget ind.
	- **Uddybende beskrivelse:**
		1. Etter at en bruker er logget inn skal den kunne endre informasjonen på events den inloggede bruker tidligere har oprettet
	- **Supplerende oplysninger:**
		1. Det er kun mulig å oppdatere et event av gangen
		2. Når oppdateringen er gjort skal brukeren få en beskjed om at informasjonen ble endret
		3. Hvis oppdateringen ikke var vellykket skal bruker få informasjon om det
		4. Gjesten blir ført tilbake til oversikten over events

- **Use case 5 - Se liste med events:**
	- **Beskrivelse:** Aktøren har mulighed for, at se en liste over de mulige events.
	- **Forudsætninger:** Man skal være logget ind.
	- **Uddybende beskrivelse:**
		1. Systemet viser en brugerflade.
		2. Aktøren har her mulighed for at se en liste over alle events.
	- **Supplerende oplysninger:**
		Et event vil kun forekomme på denne liste, såfremt det er oprettet. 

- **Use case 6 - Se deltagerliste på valgt event:**
	- **Beskrivelse:** Brugeren skal kunne få en liste vist over alle deltagende på et valgt event. 
	- **Forudsætninger:** Man skal være logget ind.
	- **Uddybende beskrivelse:**
		1. En bruger logger ind.
		2. Brugeren får vist feed med tilgængelige events.
		3. Brugeren kan trykke på knappen “Vis deltagere” for et af de events som feeden viser.
		4. Brugeren får vist en liste over alle deltagere på eventet.	
	- **Supplerende oplysninger:**
		-
	
- **Use case 7 - Slette egne events:**
	- **Forudsætninger:** Aktøren skal være logget ind
	- **Kort beskrivelse:** En bruger skal have mulighed for at slette egne events. 
	- **Uddybende beskrivelse:**
		1. Brugeren vælger “Mine events”.
		2. Applikationen viser brugerens oprettede events. 
		3. Brugeren trykker “rediger” på det pågældende event, som brugeren  ønsker at slette.
		4. Brugeren trykker på slet event.   
		5. Brugeren føres tilbage til oversigten over events. 
	- **Supplerende oplysninger:**
		1. For at slette et event, skal det være oprettet forinden. 

- **Use Case 8 - Log ud:**
	- **Beskrivelse:** Brugeren skal kunne logge ud af systemet igen.
	- **Forudsætninger:** Man skal være logget ind. 
	- **Uddybende beskrivelse:**
		1. Brugeren trykker på log-ud knappen
		2. Man får et vindue der fortæller at man er blevet logget ud
		3. Herefter vises log-in skærmen igen
	- **Supplerende oplysninger:**
