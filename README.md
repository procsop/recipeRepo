P.E. elképzelés:

Recept/életmód témával foglakozó webes alkalmazást készítünk, melynek a lényege, hogy a felhasználók kiválaszthatják, melyik receptet készítik el az adott napon, majd megfelelő mennyiségű adat begyűjtése után képes lesz a szoftver ajánlani recepteket a felhasználónak, attól függően, hogy a korábbi választásai alapján valószínűleg milyen tápanyagokra/ásványi anyagokra/vitaminokra lenne leginkább szüksége. (az ajánlott napi/heti bevitelhez képest).
 
Ezáltal hosszú távon egy kiegyensúlyozottabb étrendet érhet el a felhasználó. 

A felhasználók elmenthetik kedvenc receptjeiket, ezáltal egy saját receptkönyvet készíthetnek, mely lehet publikus vagy privát.
Célunk az átlátható, mai kornak megfelelő, letisztult felület, a receptek közötti könnyű keresés, és azok jó kategorizálása.
A felhasznált technológia backend oldalon Java Spring lesz, frontend pedig HTML/CSS/Javascript, JS frameworkről még nem döntöttünk.

Csapattagok(4 csoport):

- Lacza Péter (LRRVU8) 
- Németh Dávid (HO92MS) 
- Szilágyi Erzsébet (BEIK6E)
- Varga Péter (NY7TGR)



A fejlesztés jelenlegi követelményei (fenntartva a módosítás jogát):

0.) előzetes követelmények 


git 2.21 install
java jdk telepítése (8u202)
java jre telepítése (8u202)
PATH-ba jdk bin mappájára mutatni.

1.) vscode insider telepítése, configolása.


2.) szükséges extensions: spring boot extension pack, Spring Initializr , java extension pack, (opc.: angular és maven for java.)


3.) selected dependencies (a módosítás jogát fenntartva, későbbi kiegészítéssel):
- WEB, + kell Tomcat vagy apache alá
- Jersey, REST elosztó
- talán kellene postgreSQL is, de ezt utólag eldöntjük
- gitLens


4.) git clone https://github.com/procsop/recipeRepo.git -val lehet csatlakozni a fejlesztéshez.


5.) két ágon haladunk, egy development és master. 
Alapvetően a development-re fejlesztünk, ha mérföldkövet érünk el, áttesszük a master-be a már működő és nem hibás (letesztelt) részt, majd a következő mérföldkőig szintén a development-be fejlesztünk.

Minden függvényt, szubrutint érdemes /*..*/ -fejlesztői megjegyzés fejléccel ellátni a későbbi doksigenerálás céljából, továbbá gyakran commitolni, az ésszerűbb debuggolás szempontjából. Fontos, hogy commit előtt nézzétek meg egy pull-al, hogy előttetek valaki
commitolt-e vajon.

Konvenció legyen, hogy branchek merge-elését, továbbá egyéb commit&push-tól különböző git parancsokat az adjon ki elsősorban, aki tudja, hogy azzal valójában mit csinál, vállalva a felelősséget is.


6.) Az app F5-el indul, ctrl+F5-tel zárul a fejlesztői környezetben. Megnézni böngészőben a localhost:8080 -on lehet (ha configoláskor mást nem állítottunk be). 

Pro