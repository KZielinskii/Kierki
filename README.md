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
