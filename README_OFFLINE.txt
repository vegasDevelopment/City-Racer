OFFLINE MOD — Bu proje internetsiz çalışacak şekilde yapılandırıldı.

NELER DEĞİŞTİ:
- AndroidManifest.xml'de INTERNET izni YOK.
- android:usesCleartextTraffic kaldırıldı.
- MainActivity.java, WebView'de http:// ve https:// isteklerini engelliyor.
- WebView yalnızca app/src/main/assets/www/ altındaki yerel dosyaları yükler.

DİKKAT:
HTML dosyanızdaki TÜM dış kaynaklar (CDN scriptleri, Google Fonts,
uzak resimler, API çağrıları) çalışmayacaktır. Sayfa eksik görünebilir.

NE YAPMALISINIZ:
1. HTML'nizdeki <script src="https://..."> ve <link href="https://...">
   gibi tüm dış referansları bulun.
2. Bu dosyaları indirin.
3. ZIP oluştururken HTML dosyanızla birlikte bir .zip içine koyun
   veya projede app/src/main/assets/www/ altına elle yerleştirin.
4. HTML içindeki yolları yerel hale getirin:
   <script src="https://cdn.../lib.js">  →  <script src="./lib.js">
5. Projeyi yeniden oluşturun.

İnternetli mod istiyorsanız bu ZIP'i silip yeniden "İnternetli" seçerek
oluşturun.
