# java-sv2-adv-project-SzalayTamas

# Vizsgaremek

## Leírás

Mindig tetszett mennyi adatot tárol rólunk a villamosszogáltató ezért válsztottam ezt a projektet, hogy olyan kihivás elé állítsam magam amiből tanulni tudok.

---

## Felépítés

### Customer

A `Customer` entitás a következő attribútumokkal rendelkezik:

* `id`
* `meter` (Ügyfél kiheleyzett mérőóra, enélkül nem lehet a felhasználónak számlát kiállítani)
* `invoices` (Ügyfél számára kiadott számlák, lista)
* `contact` (Ügyfél elérési adata, egy beágyazott osztály: email address,zipcode,address,city,phone number, nem lehetnek üresek)
* `pricePerLiloWatt` (Az ár amin a ügyfél kapja az áramot. nem lehet null)

Vágpontok:

| HTTP metódus | Végpont                 | Leírás                                                                 |
| ------------ | ----------------------- | ---------------------------------------------------------------------- |
| GET          | `"/api/cutomer"`        | lekérdezi az összes Ügyfelet                                         |
| GET          | `"/api/cutomer/{id}"`        | lekérdez egy ügyfelet `id` alapján                                         |
| GET          | `"/api/cutomer/{id}/invoices"`        | lekérdezi az ügyfél összes számláját                                      |
| POST          | `"/api/cutomer"`        | Létrehoz egy ügyfelet                                         |
| PUT          | `"/api/cutomer/{id}/price"`        | Frissíti az ügyfél díjszabását                                       |
| PUT          | `"/api/cutomer/{id}/contact"`        | Frissíti az ügyfél elérési adatait                                         |
| DELETE          | `"/api/cutomer"`        | Törli az összes ügyfelet                                         |
| DELETE          | `"/api/cutomer/{id}"`        | Töröl egy ügyfelet  `id` alapján                                        |

Egy email cím csak egyszer szerepelhet az adatbázisban.

---

### Meter 

A `Meter ` entitás a következő attribútumokkal rendelkezik:

* `id`
* `customer` (Ügyfél akihez tartozik a mérőóra)
* `measurements`  (Mérések amiket az  órán végeztek, Dátum és óraállás, lista)
A `Customer` és a `Meter` entitások között kétirányú, 1-1 kapcsolat van. 
Végpontok:


| HTTP metódus | Végpont                 | Leírás                                                                 |
| ------------ | ----------------------- | ---------------------------------------------------------------------- |
| GET          | `"/api/meter"`        | lekérdezi az összes mérőórát                                         |
| GET          | `"/api/meter/{id}"`   | lekérdez egy mérőórát `id` alapján                                      |
| POST          | `"/api/meter/"`   | létrehoz egy új mérőórát egy felhasználóhoz, dátummal és óra indulásiértékével.                                     |
| POST          | `"/api/meter/{id}"`   | létrehoz egy mérést egy mérőórán `id` alapján                                      |
| DELETE          | `"/api/meter/"`   | töröl minden órát az adatbázisból                                 |
| DELETE          | `"/api/meter/{id}"`   | töröl egy órát id alapján az adatbázisból                                    |

Egy órához csak egy ügyfél tartozhat és forditva is.
Az új mérést nem lehet felvenni egy órához,ha az egy már meglévő mérés előtt készült, vagy kevesebb a mérés mint egy már szereplő adatt.

### Invoice 

A `Invoice ` entitás a következő attribútumokkal rendelkezik:

* `id`
* `meter` (Számlához tartozó óra ami mérése alapján a számla készült, nem lehet null)
* `customer`(Ügyfél akihez tartozik a számla, nem lehet null)
* `status` (Enum, Csak Pending és Paid lehett, minden más érték hibás érték.)
* `debt` 
* `dateOfInvoiceCreation`
* `pricePerKiloWatt`
* `billedElectricity`



Végpontok:

| HTTP metódus | Végpont                 | Leírás                                                                 |
| ------------ | ----------------------- | ---------------------------------------------------------------------- |
| GET          | `"/api/invoice"`        | lekérdezi az összes számlát                                         |
| GET          | `"/api/invoice/{id}"`   | lekérdez egy számlát `id` alapján                                      |
| PUT          | `"/api/invoice/{id}/status"`   | Frissíti egy számla állapotát(Pending,Paid)                                      |
| POST          | `"/api/invoice/"`   | létrehoz egy számlát, a megadott ügyfélnek a megadott felhasználás alapján.                                     |
| DELETE          | `"/api/invoice/"`   | töröl minden számlát                                     |
| DELETE          | `"/api/invoice/{id}"`   | töröl egy számlát `id` alapján                                       |

Ide még leírhattok sepciális üzleti logikát, pl a dátum nem lehet nagyobb az előzőnél stb stb

---

## Technológiai részletek

Itt le tudjátok írni, hogy háromrétegű, MariaDb, SwaggerUI, Repository, Service, Controller, Docker, Nem kell hogy
hosszú legyen.

---
