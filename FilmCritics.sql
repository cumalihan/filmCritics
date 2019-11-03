-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.3.13-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- filmcritics için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `filmcritics` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `filmcritics`;

-- tablo yapısı dökülüyor filmcritics.dil
CREATE TABLE IF NOT EXISTS `dil` (
  `dil_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `dil` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`dil_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.dil: ~6 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `dil` DISABLE KEYS */;
INSERT INTO `dil` (`dil_id`, `dil`) VALUES
	(1, 'Turkce'),
	(2, 'Ingilizce'),
	(3, 'Almanca'),
	(9, 'Testss'),
	(43, 'dasdaf'),
	(46, 'dsad');
/*!40000 ALTER TABLE `dil` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.dosya
CREATE TABLE IF NOT EXISTS `dosya` (
  `dosya_id` int(11) NOT NULL AUTO_INCREMENT,
  `dosya_ismi` varchar(100) CHARACTER SET utf8 NOT NULL,
  `dosya_konumu` varchar(350) CHARACTER SET utf8 NOT NULL,
  `dosya_tipi` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`dosya_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.dosya: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `dosya` DISABLE KEYS */;
INSERT INTO `dosya` (`dosya_id`, `dosya_ismi`, `dosya_konumu`, `dosya_tipi`) VALUES
	(1, 'D6OoJw5XkAIdDS-.jpg', '\\Users\\Burak\\Documents\\NetBeansProjects\\JavaWeb\\yukleme', 'image/jpeg'),
	(2, 'hangover1.jpg', '\\Users\\Burak\\Documents\\NetBeansProjects\\JavaWeb\\yukleme', 'image/jpeg'),
	(3, 'RickAndMorty.jpg', '\\Users\\Burak\\Documents\\NetBeansProjects\\JavaWeb\\yukleme', 'image/jpeg');
/*!40000 ALTER TABLE `dosya` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.film
CREATE TABLE IF NOT EXISTS `film` (
  `film_id` int(11) NOT NULL AUTO_INCREMENT,
  `baslik` varchar(100) CHARACTER SET utf8 NOT NULL,
  `puan` float NOT NULL,
  `aciklama` text CHARACTER SET utf8 DEFAULT NULL,
  `vizyon_tarihi` int(11) DEFAULT NULL,
  `dil_id` smallint(6) NOT NULL,
  `dosya_id` int(11) NOT NULL,
  `son_guncelleme` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`film_id`),
  KEY `dil_id` (`dil_id`),
  KEY `FK_film_dosya` (`dosya_id`),
  CONSTRAINT `FK_film_dil` FOREIGN KEY (`dil_id`) REFERENCES `dil` (`dil_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_film_dosya` FOREIGN KEY (`dosya_id`) REFERENCES `dosya` (`dosya_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.film: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` (`film_id`, `baslik`, `puan`, `aciklama`, `vizyon_tarihi`, `dil_id`, `dosya_id`, `son_guncelleme`) VALUES
	(25, 'Felekten Bir Gece', 7.8, 'Three buddies wake up from a bachelor party in Las Vegas, with no memory of the previous night and the bachelor missing. They make their way around the city in order to find their friend before his wedding.', 2009, 2, 2, '2019-05-16 01:02:56'),
	(26, 'Tests', 8, 'Test', 2013, 1, 1, '2019-05-16 12:06:37'),
	(27, 'Rick and Morty', 9.3, 'An animated series that follows the exploits of a super scientist and his not-so-bright grandson.', 2013, 2, 3, '2019-05-31 04:11:45');
/*!40000 ALTER TABLE `film` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.film_kategori
CREATE TABLE IF NOT EXISTS `film_kategori` (
  `film_kategori_id` int(11) NOT NULL AUTO_INCREMENT,
  `film_id` int(11) NOT NULL,
  `kategori_id` smallint(5) NOT NULL,
  PRIMARY KEY (`film_kategori_id`,`film_id`),
  KEY `kategori_id` (`kategori_id`),
  KEY `film_id` (`film_id`),
  CONSTRAINT `FK_film_kategori_film_film_id` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_film_kategori_kategori_kategori_id` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.film_kategori: ~8 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `film_kategori` DISABLE KEYS */;
INSERT INTO `film_kategori` (`film_kategori_id`, `film_id`, `kategori_id`) VALUES
	(23, 27, 1),
	(24, 27, 2),
	(29, 25, 2),
	(33, 26, 3),
	(25, 27, 4),
	(34, 26, 9),
	(26, 27, 10),
	(35, 26, 12);
/*!40000 ALTER TABLE `film_kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.film_oyuncu
CREATE TABLE IF NOT EXISTS `film_oyuncu` (
  `film_oyucu_id` int(11) NOT NULL AUTO_INCREMENT,
  `film_id` int(11) NOT NULL,
  `oyuncu_id` int(11) NOT NULL,
  PRIMARY KEY (`film_oyucu_id`),
  KEY `FK_film_oyuncu_film` (`film_id`),
  KEY `FK_film_oyunu_oyuncu` (`oyuncu_id`),
  CONSTRAINT `FK_film_oyuncu_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_film_oyunu_oyuncu` FOREIGN KEY (`oyuncu_id`) REFERENCES `oyuncu` (`oyuncu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.film_oyuncu: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `film_oyuncu` DISABLE KEYS */;
/*!40000 ALTER TABLE `film_oyuncu` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.film_ulke
CREATE TABLE IF NOT EXISTS `film_ulke` (
  `film_ulke_id` int(11) NOT NULL AUTO_INCREMENT,
  `film_id` int(11) NOT NULL,
  `ulke_id` smallint(6) NOT NULL,
  PRIMARY KEY (`film_ulke_id`),
  KEY `FK_film_ulke_film` (`film_id`),
  KEY `FK_film_ulke_ulke` (`ulke_id`),
  CONSTRAINT `FK_film_ulke_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_film_ulke_ulke` FOREIGN KEY (`ulke_id`) REFERENCES `ulke` (`ulke_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.film_ulke: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `film_ulke` DISABLE KEYS */;
/*!40000 ALTER TABLE `film_ulke` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.film_yayinci
CREATE TABLE IF NOT EXISTS `film_yayinci` (
  `film_yayinci_id` int(11) NOT NULL AUTO_INCREMENT,
  `film_id` int(11) NOT NULL,
  `yayinci_id` int(11) NOT NULL,
  PRIMARY KEY (`film_yayinci_id`),
  KEY `FK_film_yayinci_yayinci` (`yayinci_id`),
  KEY `FK_film_yayinci_film` (`film_id`),
  CONSTRAINT `FK_film_yayinci_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_film_yayinci_yayinci` FOREIGN KEY (`yayinci_id`) REFERENCES `yayinci` (`yayinci_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.film_yayinci: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `film_yayinci` DISABLE KEYS */;
/*!40000 ALTER TABLE `film_yayinci` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.film_yazar
CREATE TABLE IF NOT EXISTS `film_yazar` (
  `film_yazar_id` int(11) NOT NULL AUTO_INCREMENT,
  `film_id` int(11) NOT NULL,
  `yazar_id` int(11) NOT NULL,
  PRIMARY KEY (`film_yazar_id`),
  KEY `FK_film_yazar_yazar` (`yazar_id`),
  KEY `FK_film_yazar_film` (`film_id`),
  CONSTRAINT `FK_film_yazar_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_film_yazar_yazar` FOREIGN KEY (`yazar_id`) REFERENCES `yazar` (`yazar_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.film_yazar: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `film_yazar` DISABLE KEYS */;
/*!40000 ALTER TABLE `film_yazar` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.film_yonetmen
CREATE TABLE IF NOT EXISTS `film_yonetmen` (
  `film_yonetmen_id` int(11) NOT NULL AUTO_INCREMENT,
  `film_id` int(11) NOT NULL,
  `yonetmen_id` int(11) NOT NULL,
  PRIMARY KEY (`film_yonetmen_id`),
  KEY `FKd_film_yonetmen_yonetmen` (`yonetmen_id`),
  KEY `FKd_film_yonetmen_film` (`film_id`),
  CONSTRAINT `FKd_film_yonetmen_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKd_film_yonetmen_yonetmen` FOREIGN KEY (`yonetmen_id`) REFERENCES `yonetmen` (`yonetmen_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.film_yonetmen: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `film_yonetmen` DISABLE KEYS */;
/*!40000 ALTER TABLE `film_yonetmen` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.kategori
CREATE TABLE IF NOT EXISTS `kategori` (
  `kategori_id` smallint(5) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`kategori_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.kategori: ~22 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` (`kategori_id`, `kategori`) VALUES
	(1, 'Aksiyon'),
	(2, 'Komedi'),
	(3, 'Korku'),
	(4, 'Animasyon'),
	(9, 'Belgesel'),
	(10, 'Bilim Kurgu'),
	(11, 'Dram'),
	(12, 'Macera'),
	(13, 'Savaş'),
	(14, 'Polisiye'),
	(15, 'Gerilim'),
	(16, 'Spor'),
	(17, 'Gizem'),
	(18, 'Suç'),
	(19, 'Fantastik'),
	(20, 'Aile'),
	(21, 'Romantik'),
	(22, 'Biyografi'),
	(23, 'Tarih'),
	(24, 'Genclik'),
	(25, 'Müzikal'),
	(26, 'Western');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.kullanici
CREATE TABLE IF NOT EXISTS `kullanici` (
  `kullanici_id` int(11) NOT NULL AUTO_INCREMENT,
  `kullanici_adi` varchar(50) CHARACTER SET utf8 NOT NULL,
  `sifre` varchar(50) CHARACTER SET utf8 NOT NULL,
  `ad` varchar(50) CHARACTER SET utf8 NOT NULL,
  `soyad` varchar(50) CHARACTER SET utf8 NOT NULL,
  `e_posta` varchar(50) CHARACTER SET utf8 NOT NULL,
  `yetki_id` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`kullanici_id`),
  KEY `FK_kullanici_yetki_yetki_id` (`yetki_id`),
  CONSTRAINT `FK_kullanici_yetki_yetki_id` FOREIGN KEY (`yetki_id`) REFERENCES `yetki` (`yetki_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.kullanici: ~7 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` (`kullanici_id`, `kullanici_adi`, `sifre`, `ad`, `soyad`, `e_posta`, `yetki_id`) VALUES
	(1, 'test', 'test', 'test', 'test', 'test@hotmail.com', 2),
	(2, 'dasdas', 'dsadsa', 'dasd', 'asdasd', 'sadsadas', 1),
	(3, 'asdas', 'dsadsa', 'dsadsa', 'dsadsa', 'dasdsad', 1),
	(4, 'admin', 'admin', 'admin', 'admin', 'admin@hotmail.com', 2),
	(5, 'ppp', 'pppppppp', 'ppppppp', 'pppppp', 'ppppp', 1),
	(6, 'aaaasdw', 'sadsad', 'dsadsa', 'dasd', 'asdasd', 1),
	(7, 'kullanici', '12345', 'asdfg', 'asdfg', 'kullanici@hotmail.com', 1),
	(8, 'tetete', 'dasdsa', 'asdas', 'dasdasd', 'asdasd', 1),
	(9, 'pss', 'dsadas', 'dasdas', 'dasdas', 'dasdsa', 1);
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.oyuncu
CREATE TABLE IF NOT EXISTS `oyuncu` (
  `oyuncu_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) CHARACTER SET utf8 NOT NULL,
  `soyad` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `cinsiyet` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`oyuncu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.oyuncu: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `oyuncu` DISABLE KEYS */;
INSERT INTO `oyuncu` (`oyuncu_id`, `ad`, `soyad`, `cinsiyet`) VALUES
	(1, 'Bradley', 'Cooper', 'Erkek'),
	(3, 'Zach', 'Galifianakis', 'Erkek');
/*!40000 ALTER TABLE `oyuncu` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.ulke
CREATE TABLE IF NOT EXISTS `ulke` (
  `ulke_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `ulke` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`ulke_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.ulke: ~6 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ulke` DISABLE KEYS */;
INSERT INTO `ulke` (`ulke_id`, `ulke`) VALUES
	(1, 'Türkiye'),
	(2, 'İngiltere'),
	(3, 'Almanya'),
	(4, 'ABD'),
	(12, 'Fransa'),
	(13, 'Deneme');
/*!40000 ALTER TABLE `ulke` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.yayinci
CREATE TABLE IF NOT EXISTS `yayinci` (
  `yayinci_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`yayinci_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.yayinci: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `yayinci` DISABLE KEYS */;
INSERT INTO `yayinci` (`yayinci_id`, `ad`) VALUES
	(1, 'Warner Bros.'),
	(2, 'Marvel Studios');
/*!40000 ALTER TABLE `yayinci` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.yazar
CREATE TABLE IF NOT EXISTS `yazar` (
  `yazar_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) CHARACTER SET utf8 NOT NULL,
  `soyad` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`yazar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.yazar: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `yazar` DISABLE KEYS */;
INSERT INTO `yazar` (`yazar_id`, `ad`, `soyad`) VALUES
	(1, 'Scott', 'Moore'),
	(2, 'Jon', 'Lucas');
/*!40000 ALTER TABLE `yazar` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.yetki
CREATE TABLE IF NOT EXISTS `yetki` (
  `yetki_id` int(11) NOT NULL AUTO_INCREMENT,
  `grup` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`yetki_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.yetki: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `yetki` DISABLE KEYS */;
INSERT INTO `yetki` (`yetki_id`, `grup`) VALUES
	(1, 'kullanici'),
	(2, 'admin');
/*!40000 ALTER TABLE `yetki` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.yonetici
CREATE TABLE IF NOT EXISTS `yonetici` (
  `yonetici_id` tinyint(3) NOT NULL AUTO_INCREMENT,
  `kullanici_adi` varchar(50) CHARACTER SET utf8 NOT NULL,
  `sifre` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`yonetici_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.yonetici: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `yonetici` DISABLE KEYS */;
INSERT INTO `yonetici` (`yonetici_id`, `kullanici_adi`, `sifre`) VALUES
	(1, 'admin', '123456');
/*!40000 ALTER TABLE `yonetici` ENABLE KEYS */;

-- tablo yapısı dökülüyor filmcritics.yonetmen
CREATE TABLE IF NOT EXISTS `yonetmen` (
  `yonetmen_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) CHARACTER SET utf8 NOT NULL,
  `soyad` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`yonetmen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- filmcritics.yonetmen: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `yonetmen` DISABLE KEYS */;
INSERT INTO `yonetmen` (`yonetmen_id`, `ad`, `soyad`) VALUES
	(1, 'Todd', 'Phillips'),
	(2, 'dasas', 'dasdass');
/*!40000 ALTER TABLE `yonetmen` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
