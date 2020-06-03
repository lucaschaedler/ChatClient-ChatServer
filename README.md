# Chat Project

Menu Features:

- Aktualisieren: Die aktuell anwählbaren Chatrooms werden aktualisiert
- Beenden: Programm wird beendet,  Änderungen (Chatrooms, Accounts, Configs) werden abgespeichert
- Neuer Account erstellen: GUI zum Erstellen eines neuen Accounts wird geöffnet. (nur möglich wenn User nicht angemeldet ist, sonst disabled)
- Anmelden: Anmelde-GUI wird geöffnet. (nur möglich wenn User nicht angemeldet ist, sonst disabled)
- Mein Profil: MyProfile-GUI wird geöffnet. In diesem werden Benuztername und Status (Online/Offline) angezeigt.
- Passwort ändern: Passwort-GUI wird geöffnet. In diesem kann der User sein Passwort ändern.
- Abmelden: User meldet sich mit Klick ab. Wenn die Abmeldung erfolgreich war, wird das Chatroom-GUI geschlossen und das Login-GUI geöffnet (nur möglich wenn angemeldet, sonst disabled)
- Benutzer Online?: BenutzerOnline-GUI wird geöffnet. Darin kann ein beliebiger Benutzername eingegeben werden. Der Status (Online/Offline) wird nach Bestätigung der Suche im Fenster ausgegeben.
- Sprache: Hier kann zwischen den Sprachen Deutsch und Englisch gewechselt werden. Wenn das Programm beendet wird, wird die beim Beenden aktuelle Sprache beim nächsten Start als Standard-Sprache genommen.
- Account löschen: Aktueller Account wird mit einem Klick gelöscht. (nur möglich wenn User angemeldet ist)

App Features:

- Der Chatroom-GUI ist in Raumliste (links), Nachrichten-Fenster (mitte), Userliste (die sich im akutellen Raum befinden) (rechts), Benutzerbereich mit verschiedenen Buttons und einem Schreib-Feld
Nachfolgend werden die Funktionalitäten der Buttons beschreiben
- Raum hinzufügen: RaumHinzufüg-GUI wird geöffnet. Der Benutzer kann einen beliebigen Raum erstellen (sofern der eingegebene Raumname nicht schon vergriffen ist)
- Raum betreten: Der Raum, welcher in der Raumliste (links) angewählt ist, wird betreten. Ein Label (unten-links) zeigt an in welchem Raum man sich momentan befindet.
- Raum löschen: Der Raum, welcher in der Raumliste (links) angewählt ist, wird gelöscht sofern derselbe User in erstellt hat.
- Raum verlassen: Der aktuelle Raum wird verlassen.
- Senden: eingegebene Nachricht wird versendet. Alternativ kann auch mit der ENTER-Taste benutzt werden. Wenn eine Nachricht gesendet werden will der User sich jedoch in keinem Raum befindet wird ein NoRoom-GUI geöffnet das dem User mitteilt, dass er zuerste einen Raum betreten soll.