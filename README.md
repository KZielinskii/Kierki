# Kierki
Gra w kierki.
Wstęp:
Kierki składają się z dwóch oddzielnych aplikacji. Pierwszą z aplikacji jest Serwer, który jest potrzebny do komunikacji między klientami. Drugą aplikacją jest Klient, który rozgrywa grę.

Serwer:
Serwer może wyświetlać listę graczy w pokojach. Wszystkie algorytmy gry są po jego stronie co uniemożliwia klientom na niedowzolony wpływ na wynik gry.

Klient:
Klient ma do dyspozycji aplikację GUI co pomaga klientowi w zrozumieniu stanu rozgrywki. Klient ma do dyspozycji cztery sceny: 
Menu – Do logowania klienta.
Rejestracja – Do rejestrowania nowych klientów.
Korytarz  - Do wyboru pokoju.
Pokój - Do rozegrania gry w kierki.

Rozgrywka i zasady gry:
Gra dla 4 osób z użyciem pełnej talii. Aby zagrać w kierki należy zalogować się na konto, lub utworzyć nowe. Po poprawnym zalogowaniu należy wybrać jeden z ośmiu pokói w którym chemy zagrać. Następnie należy poczekać aż pokój się zapełni. Kiedy to nastąpi należy przycisnąć przycisk “Gotowy”, aby rozpocząć grę.
Pełna rozgrywka składa się z 11 rozdań.
W każdym rozdaniu jeden z graczy jest rozdającym karty. W pierwszym rozdaniu jest nim losowo wybrany gracz; w każdym następnym -- osoba siedząca po lewej stronie gracza ostatnio rozdającego.
Karty są rozdzielane po równo między wszystkich graczy.


Jako pierwsza kartę wykłada osoba siedząca po lewej stronie rozdającego, po czym karty wykładają zgodnie z ruchem wskazówek zegara pozostali gracze. Istnieje obowiązek dokładania kart do koloru; jeśli gracz nie ma kart w wymaganym kolorze, może rzucać w dowolnym.
Komplet czterech kart to lewa. Gracz, który wyłożył najstarszą kartę w kolorze karty wyjściowej, bierze lewę i wychodzi jako pierwszy do następnej.
Starszeństwo kart (od najsłabszej): 2, 3, 4, ..., 9, 10, walet, dama, król, as.
W grze nie ma obowiązku przebijania kartą starszą.


W kolejnych rozdaniach chodzi o unikanie brania określonych lew, za które zdobywa się punkty ujemne.
rozdanie 1. -- bez lew; -20 pkt. za każdą wziętą lewę
rozdanie 2. -- bez kierów; -20 pkt. za każdego wziętego kiera
rozdanie 3. -- bez dam; -60 pkt. za każdą wziętą damę
rozdanie 4. -- bez panów; -30 pkt. za każdego wziętego króla lub waleta
rozdanie 5. -- bez króla kier, -150 za jego wzięcie
rozdanie 6. -- bez siódmej i ostatniej lewy, po -75 pkt. za każdą z nich
rozdanie 7. (tzw. rozbójnik) -- wszystkie ograniczenia z rozdań 1-6.
W rozdaniach 2., 5. i 7. nie można wychodzić w kiery, jeśli ma się inny kolor.

Działanie aplikacji:
Serwer komunikuję się z klientami za pomocą socketów. Dzięki wielowątkowości aplikacja pozwala grać w wielu pokojach równoczceśnie.
Każda komenda wywołana przez klienta jest najpierw sprawdzana, a następnie wykonywane są odpowiednie polecenia.
Klient w odpowiedzi na naciśnięcie dowolnego przycisku najpierw dostaję komendę główną, a następnie dane do przetworzenia przez klienta.

# Rozgrywka:
### 1. Logowanie:
![Zrzut ekranu 2023-12-17 222419](https://github.com/KZielinskii/Kierki/assets/58587948/4a3ee33c-0588-4437-9a8e-2df390509667)
### 2. Rejestracja:
![Zrzut ekranu 2023-12-17 222433](https://github.com/KZielinskii/Kierki/assets/58587948/584030a0-2a7f-4238-9d2c-6ab6585fc6a6)
### 3. Ekran z pokojami:
![Zrzut ekranu 2023-12-17 222542](https://github.com/KZielinskii/Kierki/assets/58587948/4bcb469c-4883-4fe3-8e19-853126a9f8e2)
### 4. Ekran z pokojami gdy pierwszy pokój jest prawie zapełniony:
![Zrzut ekranu 2023-12-17 222800](https://github.com/KZielinskii/Kierki/assets/58587948/0adbd00a-5769-444f-9319-3a2ee1cc3896)
### 5. Oczekiwanie na graczy:
![Zrzut ekranu 2023-12-17 222556](https://github.com/KZielinskii/Kierki/assets/58587948/d42ef589-e8a9-4678-ab8d-6d8c76872f6b)
### 6. Rozgrywka:
(Rozgrywka jest intuicyjna, mylące mogą okazać się zrzuty ekranu wykonane z widoków różnych graczy)
![Zrzut ekranu 2023-12-17 222838](https://github.com/KZielinskii/Kierki/assets/58587948/01de54f9-876a-427b-9e42-c7301994af75)
![Zrzut ekranu 2023-12-17 222856](https://github.com/KZielinskii/Kierki/assets/58587948/cb2eb0a1-771d-448e-8706-bcc775038125)
![Zrzut ekranu 2023-12-17 222923](https://github.com/KZielinskii/Kierki/assets/58587948/01e6ca4c-32fd-40f5-baed-59fb10062a0a)
![Zrzut ekranu 2023-12-17 222941](https://github.com/KZielinskii/Kierki/assets/58587948/5d7a2980-5afa-4c98-949a-5ec895b19086)
![Zrzut ekranu 2023-12-17 222959](https://github.com/KZielinskii/Kierki/assets/58587948/7f60bb95-5034-4898-b260-bcb61b6e61ae)
![Zrzut ekranu 2023-12-17 223010](https://github.com/KZielinskii/Kierki/assets/58587948/6e5b17b0-9024-4341-b63f-06f02f9b2180)
