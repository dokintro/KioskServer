# Server v1 
- *Sidst opdateret d. 03/11/2017*


## Kort intro
- Før at serveren kan testes skal der bruges et program, som kan sende dummy data til serveren, som om den var en klient. Til dette antager jeg at du benytter **Advanced REST Client**, men **Postman** kan også benyttes. 
- Derudover giver det god mening benytte sig af enten **Sequel Pro** eller **MySQL Workbench** til at administere databasen. 
- Som i gennemgår guiden, bør i følge med live både i jeres REST client og i jeres database, da det kan ellers være forvirrende. Husk at bruge data og tal der giver mening når i sender data med jeres REST client. 

### Kryptering eller ej? 
- Overvej om i vil have tingene krypteret eller ej. 
1. For at ændre dette kan du navigere til `STFU-new\src\main\resources\config.json`
2. Derefter kan i ændre encryption til enten `true` eller `false`
 

## Register Endpoint
- Før en bruger kan benytte sig af vores system, skal de oprette en bruger. Når disse sker, kommer der en ny bruger i databasen, og dette gøres således:
1. **Request URL** sættes til `http://localhost:8080/api/register`
2. **Method** sættes til `POST`
3. I **Headers** fanen sættes **Header name** til `Content-Type` og **Header value** til `application/json`
4. I **Body** fanen sættes **Body content type** til `application/json`, og **Editor view** kan sættes til `JSON editor`
5. Hertil udfyldes felterne `firstName` sættes til `myFirstName`, `lastName` sættes til `myLastName`, `password` sættes til `myPassword123`, `email` sættes til `myEmail` og `verifyPassword`til `myPassword123`. 
6. Når du nu trykker på **send** bliver en bruger forsøgt oprettet med disse oplysninger.

## Login Endpoint
- Herefter skal vores nye bruger logge ind, og dettes gøres således: 
1. Ændre **Request URL** til `http://localhost:8080/api/login`
2. I tekstfeltet skal vi kun benytte os af `Email` og `Password` for at kunne logge ind, så de andre kan slettes. 
3. Sidst, tryk **Send**

## Tokens 
- Her kommer en kort guide til at sætte tokens ind i jeres **Advanced REST Client**. 
1. Åbn jeres database værktøj, og find **students** tabellen, og derefter notér jer, jeres nyoprettede brugers `studentId`
2. Derefter navigerer i til **tokens** tabellen, og find den token der er tilknyttede jeres `studentId` og her kopierer i jeres token. 
3. I jeres **Advanced REST Client** navigerer i til **Headers** og tilføjer ne header og kalder den `Authorization` og i **Header value** indsætter i jeres token. 

## Student Endpoint
- Nu er brugeren logget ind, dette betyder at man fra nu af skal benytte sig af tokens, og guiden antager at i har indsat jeres token som beskrevet ovenfor. 

### profile
- Dette gør det muligt for brugeren at se information om sig selv. 
1. For at gøre dettes **Method** til **GET** 
2. **Request URL** sættes til `http://localhost:8080/api/students/profile`
3. Tryk på **SEND**, og den indlogget brugers information burde printes. 

### getAttendingEvents
- Man skal kunne hente hvilke events en bruger har tilmeldt sig, og dette gøres således:
1. Ændre **METHOD** til **GET** 
2. **Request URL** sættes til `http://localhost:8080/api/students/{idStudent}/events`, hvor idStudent er id'et på den student du er logget ind som
3. Derefter bør de events som brugeren er tilmeldt blive printet i konsollen når der trykkes på **SEND**

### logout
- Selvfølgelig skal en bruger også kunne logges ud. Vær dog opmærksom på at token slettes for brugeren når brugeren logges ud (en ny genereres når brugeren logger ind igen) 
1. Ændre **Method** til **POST** 
2. **Request URL** sættes til `http://localhost:8080/api/students/logout`
3. Intet skal skrives i body, og når der trykkes på **SEND** bliver brugeren logget ud. 


## Event Endpoint
### createEvent
- Metoden der gør det muligt at oprette nye events i databasen. 
1. Ændre **Method** til **POST** 
2. Ændre **Request URL** til `http://localhost:8080/api/events`
3. Udfyld følgende felter `eventName`, `location`, `price` (OBS: dette skal være en int og ikke en string som resten), `description`, `eventDate` (OBS: Dette er en string! Dette skal formateres korrekt i klienten).
4. Sidst, tryk **Send**, og eventet burde oprettes.

### deleteEvent
- Metoden gør det muligt at slette et event.
1. Ændre **Method** til **PUT** 
2. Ændre **Request URL** til `http://localhost:8080/api/events/{idEvent}/delete-event`
3. Når der trykkes på **Send** bliver `isDeleted` i databasen sat til true (1) og de vises ikke længere når man søger efter dem.

### updateEvent
- Man skal også kunne opdatere sine events og dette gøres således, og husk nu at man kun kan opdatere sine egne events - og derfor skal `owner` være lig med det id på den student der er logget ind. 
1. Ændre **Method** til **PUT** 
2. Ændre **Request URL** til `http://localhost:8080/api/events/{idEvent}/update-event`
3. Udfyld følgende felter `eventName`, `location`, `price` (OBS: dette skal være en int og ikke en string som resten), `description`, `date` (OBS: Dette er en string! Dette skal formateres korrekt i klienten).
4. Sidst, tryk **Send**, og eventet burde opdateres med ny information.

### getEvents
- Man skal kunne se en liste med alle de mulige events 
1. Ændre **Method** til **GET** 
2. Ændre **Request URL** til `http://localhost:8080/api/events`
3. Når der trykkes på **Send** bør du få en liste printet med alle de events der eksisterer i databasen, og som ikke er blevet slettede. 

### getMyEvents
- Man skal kunne se en liste med alle events man selv har oprettede
1. Ændre **Method** til **GET** 
2. Ændre **Request URL** til `http://localhost:8080/api/events/myEvents`
3. Når der trykkes på **Send** bør du få en liste printet med alle de events der eksisterer i databasen, og som er blevet oprettet af en selv, og som ikke er blevet slettede.

### joinEvent
- Man skal som bruger kunne tilmelde sig et event og dette gøres således: 
1. Ændre **Method** til **POST**
2. Ændre **Request URL** til `http://localhost:8080/api/events/join`
3. Sæt `idEvent` til det tal som er det event du vil tilmeldes id.
4. Når du trykker på **Send** vil brugeren tilmeldes eventet.

### getAttendingStudents
- Man skal kunne se hvem der er tilmeldt de forskellige events.
1. Ændre **Method** til **GET**
2. Ændre **Request URL** til `http://localhost:8080/api/events/{idEvent}/students`
3. Når der trykkes på **Send** får du en liste printet med de students som er tilmeldt dette event. 

### leaveEvent
- Man skal kunne se hvem der er tilmeldt de forskellige events.
1. Ændre **Method** til **DELETE**
2. Ændre **Request URL** til `http://localhost:8080/api/events/{idEvent}/leave`
3. Når der trykkes på **Send** får du en liste printet med de students som er tilmeldt dette event. 
