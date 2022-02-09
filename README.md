# My-API-Client

Det här är en API-klient som skickar API-förfrågningar (requests) till http://127.0.0.1:8080.

Denna klient kan exekveras om man antingen har BlogServern igång eller med en Docker container.

Med den här klienten kan du:

- Lista bloggar (till /api/v1/blogs/**list**)
- Lägga till blog (till /api/v1/blogs/**create**)
- Rensa listan av filmer på servern (till /api/v1/**clear**)
- Ta bort en specifik blog m.h.a. ID (till /api/v1/blogs/**delete/{id}**)
- Uppdatera en specifik blog m.h.a. ID (till /api/v1/blogs/**update/{id}**)
- Hämta en specifik blog m.h.a. ID (till /api/v1/blogs/**view/{id}**)