# Contributing 

<img align="right" src="http://i.imgur.com/k8NZMmV.gif">

Hej og velkommen til vores projekt! Vi er glade for du har besluttet at programmere og tilføje funktionalitet til vores server :+1: 
<br /> <br /> <br /> 

# Installation

1. [Lav et *fork* af dette repo](https://help.github.com/articles/fork-a-repo/); Dette er din egen offentlige kopi på GitHub for dig at arbejde i før du deler det. 
2. [Hent Git.](https://git-scm.com/) Du kan også overveje at hente [GitHub Desktop](https://desktop.github.com/) klienten for at have et grafisk interface at arbejde med.
3. Brug git clone til at hente din *fork* ned. 
4. Du har nu en lokal kopi af dit fork i den lokation/mappe du valgte da du clonet repo'et. 

## Opsætning og hent fra upstream

OBS: Hvis du allerede har opsat upstream, så kan du springe direkte til step 5. 

1. I din terminal så navigér til den lokation du har clonet og skriv `git remote -v`.
2. I skulle gerne blive mødt af noget lignende det her: <img src="https://i.imgur.com/fwVey9d.png">
3. Skriv nu `git remote add upstream https://github.com/Distribuerede-Systemer-2017/STFU.git`.
4. Tjek at hvis i skriver `git remote -v` så får i noget lignende frem: <img src="https://i.imgur.com/N2HMsvw.png">
5. Hvis i nu skriver `git checkout master` og derefter skriver `git fetch upstream` for at hente de nyeste ændringer ned ned. 
6. Derefter skriver i `git merge upstream/master` for at slå dem sammen. 
7. Løs eventuelle merge konflikter.
8. Skriv `git push origin` for at pushe de nyeste ændringer til jeres fork, og de bør derfor nu være synkroniseret med hovedprojektet. 

Tjek eventuelt [her for configuration af remote](https://help.github.com/articles/configuring-a-remote-for-a-fork/) eller [synkronisering af forks](https://help.github.com/articles/syncing-a-fork/) for mere information, hvis dette ikke var fyldestgørende. 

# Redigering

Lav en `git checkout -b my-new-feature` for at oprette en branch, som du nu kan arbejde i uden at ændre i din egen master branch. Dette betyder at du kan arbejde i forskellige dele, uden at de har konflikter med hinanden. Begynd hellere med noget småt end et kæmpe projekt, og se hvordan alting fungerer. 

# Dele ændringerne

Når du er færdige med dine ændringer, er du klar til at [committe dine ændringer](http://dont-be-afraid-to-commit.readthedocs.io/en/latest/git/commandlinegit.html). Du kan sagtens committe af flere omgange, uden problemer inden du laver et push. Når du er færdige med alle dine planlagte ændringer, så pusher du dine ændringer til **din** fork, og det burde vise en knap til at *create pull request*, hvilket oftest vil være den nemmeste vej for dig at lave et pull request. I dette pull request, beskriver hvad du har gjort og hvorfor du har gjort det (dog kan man argumentere at hvis det er nødvendigt at forklare hvorfor man har gjort noget, bør det stå i kommentarer i koden). Derefter vil en eller flere gruppemedlemmer gennemgå din kode for at tjekke efter åbenlyse fejl eller mangler, og kommentere på pull requestet, inden det bliver merget med master branchen. 


[Her](https://github.com/Distribuerede-Systemer-2017/STFU/pull/21) er et eksempel på et pull request, jeg lavet lynhurtigt - ellers se et meget uddybende eksempel med mange kommentarer [her](https://github.com/WoWAnalyzer/WoWAnalyzer/pull/468)


**ALDRIG MERGE UDEN MINDST ÉN HAR GENNEMGÅET KODEN OG SAGT GOD FOR DET, OG HUSK AT LØSE MERGE KONFLIKTE MED OMHU** 

<p align="center">
   <img src="https://media.giphy.com/media/111ebonMs90YLu/giphy.gif">
</p>

HUSK at skrive ordentlige commit meddelelser og pull requests, så det er nemmere for alle at følge med i hvad der er sket, omskrevet og hvorfor. Gør det nu nemt for os alle sammen :pray: 

<table align="center">
  <tr>
    <td align="center" width="100"><img src="https://cdn1.iconfinder.com/data/icons/CrystalClear/48x48/apps/important.png" alt="Important"></td>
    <td>Husk det kan være nemmere og hurtigere for et gruppemedlem at gennemgå koden, hvis pull requestet ikke er for stort, dog skal dette ikke betyde at du laver små ændringer og pull requeste hver enkel ændring. Find en balance mellem større pull requests og mængden af dem.</td>
  </tr>
</table>
