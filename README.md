# Emanat Intern Tasking

⚠️ **App Haqqında Məlumat** 🏳

Bu application Spring Boot RESTful istifadə olunaraq yazılmışdır. App-da siz CRUD əməliyyatlarını heç bir RDBMS xüsusiyyətlərindən istifadə etmədən, Java da "OPEN CSV" kitabxanasından
istifadə olunaraq client-dan aldığınız dataları ".csv" formatlı faylda saxlıya bilərsiniz. Task-ın əsas məqsədi, RDBMS istifadə etmədən tamamən core programming biliklərindən faydalanara
CRUD əməliyyatlarını həyata keçirtməkdir.

---

## İstifadə qaydası

### :İlkin şərtlər

Bu proyekt tamamən Java proqramlaşdırma dilindən istifadə olunaraq və Spring Boot framework-dən istifadə olunaraq yazılmışdır və JDK 11 istifadə olunmuşdur. 

İlk öncə repository-ni git clone etməyiniz lazımdır.

```bash
git clone repo-url
```

Bu bir az vaxt aparacaq və Emanat-İntern-App və onun tapşırıqlarını yerinə yetirmək üçün lazım olan bütün paketləri quraşdıracaq.

## Structure

```
|--src                 // Əsas paket
|  |--az              // domain name-ə uyğun
|  |--emanat          // domain name-ə uyğun
|  |--controller      //Bu paketdə RESTful WS yazılmışdır
|  |--data            //Bu paket db ilə əlaqəli bütün paketləri ehtiva edir
   |  |--dto          //Bu paketdə Request ve Response dto-lar yaradılmışdır
   |  |--dao          //Bu paketdə abstract custom database yaradılmışdır
      |  |--impl     // Bu paketdə abstract custom database implementasiyasi yaradılmışdır
|  |--exception      // Bu paketdə exception handler və custom exception yaradılmışdır
|  |--config         // Swagger Configurasiyasi bu paket də quraşdırılmışdır
```

## Enjoy!! 😸
