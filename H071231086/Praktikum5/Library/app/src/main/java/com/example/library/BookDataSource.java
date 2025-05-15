package com.example.library;

import com.example.library.BookModel;
import com.example.library.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BookDataSource {
    private static final List<BookModel> allBooks = new ArrayList<>();
    private static int currentId = 1;

    static {
        // Dummy data awal
        allBooks.add(new BookModel(currentId++, "Second Sister", "Chan Ho-Kei", 2017, "A schoolgirl—Siu-Man—has committed suicide, leaping from her twenty-second floor window to the pavement below. Siu-Man is an orphan and the librarian older sister who’s been raising her refuses to believe there was no foul play—nothing seemed amiss. She contacts a man known only as N.—a hacker, and an expert in cybersecurity and manipulating human behavior. But can Nga-Yee interest him sufficiently to take her case, and can she afford it if he says yes?", R.drawable.book1, false, false));
        allBooks.add(new BookModel(currentId++, "Cantik Itu Luka", "Eka Kurniawan", 2002, "Di satu sore, seorang perempuan bangkit dari kuburannya setelah dua puluh satu tahun kematian. Kebangkitannya menguak kutukan dan tragedi keluarga, yang terentang sejak akhir masa kolonial. Perpaduan antara epik keluarga yang dibalut roman, kisah hantu, kekejaman politik, mitologi, dan petualangan. Dari kekasih yang lenyap ditelan kabut hingga seorang ibu yang menginginkan bayi buruk rupa.", R.drawable.book2, false, false));
        allBooks.add(new BookModel(currentId++, "The Little Prince", "AAntoine de Saint-Exupéry", 1943, "A pilot stranded in the desert awakes one morning to see, standing before him, the most extraordinary little fellow. \"Please,\" asks the stranger, \"draw me a sheep.\" And the pilot realizes that when life's events are too difficult to understand, there is no choice but to succumb to their mysteries. He pulls out pencil and paper... And thus begins this wise and enchanting fable that, in teaching the secret of what is really important in life, has changed forever the world for its readers.", R.drawable.book3, false, false));
        allBooks.add(new BookModel(currentId++, "Totto Chan", "Tetsuko Kuroyanagi\n", 1981, "This engaging series of childhood recollections tells about an ideal school in Tokyo during World War II that combined learning with fun, freedom, and love. This unusual school had old railroad cars for classrooms, and it was run by an extraordinary man--its founder and headmaster, Sosaku Kobayashi--who was a firm believer in freedom of expression and activity.", R.drawable.book4, false, false));
        allBooks.add(new BookModel(currentId++, "Dona Dona", "Toshikazu Kawaguchi", 2018, "Di sebuah lereng indah tak bernama di Hakodate, Hokkaido, berdiri Kafe Dona Dona yang menawarkan layanan istimewa kepada pengunjungnya: perjalananan melintasi waktu. Seperti di Funiculi Funicula yang ada di Tokyo, hal tersebut hanya dapat dilakukan jika berbagai peraturan yang merepotkan dipenuhi dan dengan secangkir kopi yang dituangkan oleh perempuan di keluarga Tokita.\n" +
                "\n" +
                "Mereka yang ingin memutar waktu adalah seorang wanita muda yang menyimpan dendam kepada orangtua yang menjadikannya yatim piatu kesepian, seorang komedian yang kehilangan tujuan hidup setelah berhasil mewujudkan impian mendiang istrinya, seorang adik yang khawatir kakaknya takkan bisa tersenyum lagi setelah kepergiannya, dan seorang pemuda yang tak mampu mengungkapkan cinta terpendam kepada sahabatnya.", R.drawable.book5, false, false));
        allBooks.add(new BookModel(currentId++, "Pasta Kacang Merah", "Durian Sukegawa", 2013, "Sentaro gagal menjalani kehidupan. Ia memiliki catatan kriminal, sulit meninggalkan kebiasaan minum alkohol, dan impiannya menjadi penulis semakin lama semakin pudar. Ia menghabiskan hari-hari monoton di sebuah kedai dorayaki yang berada di bawah pohon sakura yang berubah seiring perubahan musim. Namun, suatu ketika segalanya mulai berubah. Seorang wanita tua bernama Tokue, dengan jemari yang aneh bentuknya, datang ke kehidupan Sentaro. Dengan metode pengajaran yang sama anehnya, Tokue mewariskan pengalaman lima puluh tahunnya membuat pasta kacang merah kepada Sentaro. Namun, seiring persahabatan di antara keduanya mulai terjalin, tekanan dari masyarakat terhadap kondisi Tokue mulai mengungkap rahasia gelap yang wanita itu simpan rapat-rapat. Rahasia itu kemudian menuntut harga yang sangat mahal. Pasta Kacang Merah adalah sebuah cerita yang mengharmonisasikan kudapan manis dengan persahabatan, menggambarkan bagaimana harapan dapat membantu manusia menghadapi kelamnya masa lalu.", R.drawable.book6, false, false));
        allBooks.add(new BookModel(currentId++, "Yellow Face", "R.F. Kuang", 2023, "Authors Juniper Hayward and Athena Liu were supposed to be twin rising stars. But Athena is a literary darling while June is a nobody. Who wants stories about basic white girls?, June thinks. So when June witnesses Athena’s death in a freak accident, she acts on impulse, stealing Athena’s just-finished masterpiece, an experimental novel about the unsung contributions of Chinese laborers during World War I.\n" +
                "\n" +
                "So what if June edits Athena’s novel and sends it to her agent as her own work? So what if she lets her new publisher rebrand her as Juniper Song—complete with an ambiguously ethnic author photo? This piece of history deserve to be told, whoever the teller. That is what June believes, and The New York Times bestseller list agrees.", R.drawable.book7, false, false));
        allBooks.add(new BookModel(currentId++, "The Devotion Of Suspect X", "Keigo Higashino", 2005, "4.17\n" +
                "Yasuko lives a quiet life, working in a Tokyo bento shop, a good mother to her only child. But when her ex-husband appears at her door without warning one day, her comfortable world is shattered.\n" +
                "\n" +
                "When Detective Kusanagi of the Tokyo Police tries to piece together the events of that day, he finds himself confronted by the most puzzling, mysterious circumstances he has ever investigated. Nothing quite makes sense, and it will take a genius to understand the genius behind this particular crime", R.drawable.book8, false, false));
        allBooks.add(new BookModel(currentId++, "Teka Teki Rumah Aneh", "Uketsu", 2021, "PSeorang kenalan ingin membeli rumah seken di Tokyo dan memperlihatkan denah rumahnya padaku karena merasa ada yang ganjil. Sekilas, rumah ini kelihatan seperti rumah-rumah lain pada umumnya dengan interior yang luas dan terang. Namun, ketika mencermatinya baik-baik, aku mendapati bahwa memang ada keanehan di sana-sini. Keanehan demi keanehan itu bertumpuk, kemudian terjalin membentuk satu “kenyataan”. Kenyataan yang teramat sangat mengerikan, dan sama sekali tidak ingin kupercaya.", R.drawable.book9, false, false));
        allBooks.add(new BookModel(currentId++, "Hello", "Tere Liye", 2023, "Hello Apakah kamu di sana? Aku tahu kamu di sana. Aku tahu kamu mendengarkan suaraku.\n" +
                "\n" +
                "Hello Aku tahu kita belum bisa bicara. Tapi aku tidak bisa menahan diriku untuk meneleponmu. Aku hanya hendak bilang, aku tidak akan menyerah.\n" +
                "\n" +
                "Aku akan selalu menyayangimu.", R.drawable.book10, false, false));
        allBooks.add(new BookModel(currentId++, "Convenience Store Woman", "Sayaka Murata\n", 2021, "PKeiko has never fit in, neither in her family, nor in school, but when at the age of eighteen she begins working at the Hiiromachi branch of “Smile Mart,” she finds peace and purpose in her life. In the store, unlike anywhere else, she understands the rules of social interaction―many are laid out line by line in the store’s manual―and she does her best to copy the dress, mannerisms, and speech of her colleagues, playing the part of a “normal” person excellently, more or less. Keiko is very happy, but the people close to her, from her family to her coworkers, increasingly pressure her to find a husband, and to start a proper career, prompting her to take desperate action…\n", R.drawable.book11, false, false));
        allBooks.add(new BookModel(currentId++, "Laut Bercerita", "Leila S. Chudori", 2009, "Di sebuah senja, di sebuah rumah susun di Jakarta, mahasiswa bernama Biru Laut disergap empat lelaki tak dikenal. Bersama kawan-kawannya, Daniel Tumbuan, Sunu Dyantoro, Alex Perazon, dia dibawa ke sebuah tempat yang tak dikenal. Berbulan-bulan mereka disekap, diinterogasi, dipukul, ditendang, digantung, dan disetrum agar bersedia menjawab satu pertanyaan penting: siapakah yang berdiri di balik gerakan aktivis dan mahasiswa saat itu.", R.drawable.book12, false, false));
        allBooks.add(new BookModel(currentId++, "Funiculi FUnicula", "Toshikazu Kawaguchi", 2009, "Funiculi Funicula, sebuah kafe di gang sempit di Tokyo, masih kerap didatangi orang-orang yang ingin menjelajahi waktu. Peraturan-peraturan yang merepotkan masih berlaku, tetapi itu semua tidak menyurutkan harapan mereka untuk memutar waktu.\n" +
                "\n" +
                "Kali ini ada seorang pria yang ingin kembali ke masa lalu untuk menemui sahabat yang putrinya ia besarkan, seorang putra putus asa yang tidak menghadiri pemakaman ibunya, seorang pria sekarat yang ingin melompat ke dua tahun kemudian untuk memastikan kekasihnya bahagia, dan seorang detektif yang ingin memberi istrinya hadiah ulang tahun untuk pertama sekaligus terakhir kalinya.", R.drawable.book13, false, false));
        allBooks.add(new BookModel(currentId++, "Keajaiban Toko Kelontong Namiya", "Keigo Higashino", 2009, "Ketika tiga pemuda berandal bersembunyi di toko kelontong tak berpenghuni setelah melakukan pencurian, sepucuk surat misterius mendadak diselipkan ke dalam toko melalui lubang surat.\n" +
                "\n" +
                "Surat yang berisi permintaan saran. Sungguh aneh.\n" +
                "\n" +
                "Namun, surat aneh itu ternyata membawa mereka dalam petualangan melintasi waktu, menggantikan peran kakek pemilik toko kelontong yang menghabiskan tahun-tahun terakhirnya memberikan nasihat tulus kepada orang-orang yang meminta bantuan.\n" +
                "\n" +
                "Hanya untuk satu malam.\n" +
                "\n" +
                "Dan saat fajar menjelang, hidup ketiga sahabat itu tidak akan pernah sama lagi..", R.drawable.book14, false, false));
        // Tambah lagi sesuai kebutuhan
    }

    public static List<BookModel> getAllBooks() {
        return allBooks;
    }

    public static List<BookModel> getFavoriteBooks() {
        List<BookModel> favs = new ArrayList<>();
        for (BookModel b : allBooks) {
            if (b.isFavorite()) favs.add(b);
        }
        return favs;
    }

    public static List<BookModel> getRecommendedBooks() {
        List<BookModel> recommendationBooks = new ArrayList<>(allBooks);
        Collections.shuffle(recommendationBooks);
        recommendationBooks = recommendationBooks.subList(0, Math.min(5, recommendationBooks.size()));

        return recommendationBooks;
    }

    public static List<BookModel> getAllNonFavoriteBooks() {
        List<BookModel> nonFavs = new ArrayList<>();
        for (BookModel b : allBooks) {
            if (!b.isFavorite()) nonFavs.add(b);
        }
        return nonFavs;
    }


    public static void addBook(BookModel book) {
        allBooks.add(book);
    }

    public static int generateId() {
        return currentId++;
    }

    public static BookModel getRandomNonFavoriteBook() {
        List<BookModel> candidates = new ArrayList<>();
        for (BookModel b : allBooks) {
            if (!b.isFavorite()) candidates.add(b);
        }
        if (candidates.isEmpty()) return null;
        return candidates.get(new Random().nextInt(candidates.size()));
    }
}