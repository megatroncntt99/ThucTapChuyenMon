-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 18, 2020 at 03:45 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appmucis`
--

-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

CREATE TABLE `advertisement` (
  `idAdvertisement` int(11) NOT NULL,
  `imgAdvertisement` varchar(255) NOT NULL,
  `Content` varchar(255) NOT NULL,
  `idSong` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `advertisement`
--

INSERT INTO `advertisement` (`idAdvertisement`, `imgAdvertisement`, `Content`, `idSong`) VALUES
(1, 'https://megtroncntt99.000webhostapp.com/image/imgAdvertisement/qc_chaccotadaubiet.jpg', 'Chắc cô ta đâu biết-Huỳnh Ái Vy', '18'),
(2, 'https://megtroncntt99.000webhostapp.com/image/imgAdvertisement/qc_dearmom.jpg', 'Dear Mom (Single) INSOLENT, Linh Dino, CM1X', '17'),
(3, 'https://megtroncntt99.000webhostapp.com/image/imgAdvertisement/nen_utc2.png', 'MSSV:5851071087', '19');

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE `album` (
  `idAlbum` int(11) NOT NULL,
  `nameAlbum` varchar(255) NOT NULL,
  `singerAlbum` varchar(255) NOT NULL,
  `imgAlbum` varchar(255) NOT NULL,
  `likeAlbum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`idAlbum`, `nameAlbum`, `singerAlbum`, `imgAlbum`, `likeAlbum`) VALUES
(1, 'Thích Thì Đến (Single)', 'Lê Bảo Bình', 'https://megtroncntt99.000webhostapp.com/image/imgAlbum/ttd.jpg', 0),
(2, 'Em Không Sai Chúng Ta Sai (Single)', 'ERIK', 'https://megtroncntt99.000webhostapp.com/image/imgAlbum/ekscts.jpg', 2),
(3, 'Speak Now World Tour Live (Live 2011)', 'Taylor Swift', 'https://megtroncntt99.000webhostapp.com/image/imgAlbum/sqeak_now_world_tour.jpg', 0),
(4, 'Chỉ Có Thể Là Mỹ Tâm', 'Mỹ Tâm', 'https://megtroncntt99.000webhostapp.com/image/imgAlbum/chicothelamytam.jpg', 12),
(5, 'Người Tình Ánh Trăng (Moon Lovers OST)', 'Various Artists', 'https://megtroncntt99.000webhostapp.com/image/imgAlbum/nguoitinhanhtrang.jpg', 12);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `idCategory` int(11) NOT NULL,
  `idTheme` varchar(255) NOT NULL,
  `nameCategory` varchar(255) NOT NULL,
  `imgCategory` varchar(255) NOT NULL,
  `likeCategory` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`idCategory`, `idTheme`, `nameCategory`, `imgCategory`, `likeCategory`) VALUES
(1, '1', 'Deep House Hits', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/edmdeephouse.jpg', 2),
(2, '1', 'EDM Now', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/edmnow.jpg', 1),
(3, '2', 'Acoustic EDM', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/acousticedm.jpg', 2),
(4, '2', 'Acoustic Relax', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/acousticrelax.jpg', 0),
(5, '3', 'Những Bản Nhạc Kích Thích Trí Não', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/nhung_ban_nhac_kich_thich_tri_nao.jpg', 0),
(6, '3', 'Nhạc Baroque Giúp Tập Trung Làm Việc', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/nhac_giup_tap_trung_lam_viec.jpg', 1),
(7, '3', 'New Age Sound', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/new_age_sound.jpg', 0),
(8, '4', 'Thả Thính Là Phải Dính!', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/tha_thich_la_phai_dich.jpg', 0),
(9, '4', 'Yêu Không Cần Cớ, Cần Anh Cơ!', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/yeu_khong_can_co.jpg', 0),
(10, '4', 'Do You Wanna MARRY ME?', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/do_you_wanna_mary_me.jpg', 0),
(11, '1', 'Dance Pop', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/dancepop.jpg', 0),
(12, '1', 'Dance Rewind', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/dancerewind.jpg', 0),
(13, '1', 'The Biggest Drop', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/thebiggestdrop.jpg', 0),
(14, '1', 'Nhạc Dance Hay Nhất', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/nhacvietdancehaynhat.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `keyhot`
--

CREATE TABLE `keyhot` (
  `idKeyHot` int(11) NOT NULL,
  `nameKeyHot` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `keyhot`
--

INSERT INTO `keyhot` (`idKeyHot`, `nameKeyHot`) VALUES
(1, 'how you like that'),
(2, 'co chac yeu la day'),
(3, 'bigcityboi'),
(4, 'mot cu lua'),
(5, 'Em khong sao'),
(6, 'em cua ngay hom qua'),
(7, 'noinsert');

-- --------------------------------------------------------

--
-- Table structure for table `mv_music`
--

CREATE TABLE `mv_music` (
  `idMV` int(11) NOT NULL,
  `keyMV` varchar(255) NOT NULL,
  `idSong` int(11) NOT NULL,
  `imgMV` varchar(255) NOT NULL,
  `imgSinger` varchar(255) NOT NULL,
  `txtTimeMV` varchar(255) NOT NULL,
  `likeMVMusic` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mv_music`
--

INSERT INTO `mv_music` (`idMV`, `keyMV`, `idSong`, `imgMV`, `imgSinger`, `txtTimeMV`, `likeMVMusic`) VALUES
(1, 'iwGuiSnr2Qc', 2, 'https://megtroncntt99.000webhostapp.com/image/imgMV/emkhongsaichungtasai.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/erik.jpg', '06:28', 1201),
(2, 'j4Jj29mUYS8', 1, 'https://megtroncntt99.000webhostapp.com/image/imgMV/thichthiden.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/lebaobinh.jpg', '06:04', 1300000),
(3, 'rvBF-cov4TE', 18, 'https://megtroncntt99.000webhostapp.com/image/imgMV/chaccotadaubiet_mv.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/huynh_ai_vi.jpg', '04:45', 1),
(4, 'RYV1s0ylNFM', 13, 'https://megtroncntt99.000webhostapp.com/image/imgMV/cheerup_mv.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/hong_jin_young.jpg', '04:41', 500000),
(5, 'ioNng23DkIM', 28, 'https://megtroncntt99.000webhostapp.com/image/imgMV/howyoulikethat.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/blackpink.jpg', '03:03', 100),
(6, 'IHNzOHi8sJs', 32, 'https://megtroncntt99.000webhostapp.com/image/imgMV/duu_du.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/blackpink.jpg', '03:35', 100),
(7, 'NoYKBAajoyo', 35, 'https://megtroncntt99.000webhostapp.com/image/imgMV/dundunmv.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/everglow.jpg', '03:28', 1299),
(8, 'mUuyEX2M0gc', 15, 'https://megtroncntt99.000webhostapp.com/image/imgMV/savagelovemv.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/jawsh.jpg', '02:57', 999999),
(9, 'J40QinN_ySg', 21, 'https://megtroncntt99.000webhostapp.com/image/imgMV/tinhanhmv.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/%C4%91ingdung.jpg', '04:56', 13),
(10, 'uR8Mrt1IpXg', 31, 'https://megtroncntt99.000webhostapp.com/image/imgMV/psychomv.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/redvelvet.jpg', '03:35', 25);

-- --------------------------------------------------------

--
-- Table structure for table `playlist`
--

CREATE TABLE `playlist` (
  `idPlaylist` int(11) NOT NULL,
  `namePlayList` varchar(255) NOT NULL,
  `imgPlayList` varchar(255) NOT NULL,
  `iconPlayList` varchar(255) NOT NULL,
  `likePlaylist` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `playlist`
--

INSERT INTO `playlist` (`idPlaylist`, `namePlayList`, `imgPlayList`, `iconPlayList`, `likePlaylist`) VALUES
(1, 'Các Ca Khúc Pop Ballad Việt Nổi Bật 2020', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/pop_ballad_v_noi_bat.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/pop_ballad_v_noi_bat.jpg', 4),
(2, 'Nhạc Việt Nghe Nhiều 2020', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nhacvietnghenhieunhat2020.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nhacvietnghenhieunhat2020.jpg', 9),
(3, 'K-POP: Những Bản Hits Quốc Dân', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/kpophitquocdan.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/kpophitquocdan.jpg', 100),
(4, 'Nghe Thôi Đã... Thấy HIT', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nghethoidahit.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nghethoidahit.jpg', 0),
(5, 'Đỉnh Cao TRENDING', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nen.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/dinhcaotreding.jpg', 0),
(6, 'USUK Nghe Nhiều 2020', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/usuk_nghe_nhieu_nhat2020.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/usuk_nghe_nhieu_nhat2020.jpg', 1),
(7, 'Nhạc Trẻ Gây Nghiện', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nen.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nhactregaynghiem.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ranksong`
--

CREATE TABLE `ranksong` (
  `IdRankSong` int(11) NOT NULL,
  `nameRankSong` varchar(255) NOT NULL,
  `imgRankSong` varchar(255) NOT NULL,
  `iconRankSong` varchar(255) NOT NULL,
  `likeRankSong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ranksong`
--

INSERT INTO `ranksong` (`IdRankSong`, `nameRankSong`, `imgRankSong`, `iconRankSong`, `likeRankSong`) VALUES
(1, 'Top 100 Bài Hát Nhạc Trẻ Hay Nhất', 'https://megtroncntt99.000webhostapp.com/image/imgTop100/nen_top100_nhactre.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgTop100/top100nhactre.jpg', 0),
(2, 'Top 100 Nhạc Hàn Quốc Hay Nhất', 'https://megtroncntt99.000webhostapp.com/image/imgTop100/nen_top100kpop.png', 'https://megtroncntt99.000webhostapp.com/image/imgTop100/top100kpop.jpg', 0),
(3, 'Top 100 Pop Âu Mỹ Hay Nhất', 'https://megtroncntt99.000webhostapp.com/image/imgTop100/nen_top100popaumy.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgTop100/top100popaumy.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `song`
--

CREATE TABLE `song` (
  `idSong` int(11) NOT NULL,
  `idAlbum` varchar(255) NOT NULL,
  `idCategory` varchar(255) NOT NULL,
  `idPlaylist` varchar(255) NOT NULL,
  `idRankSong` varchar(255) NOT NULL,
  `rank` int(11) NOT NULL,
  `nameSong` varchar(255) NOT NULL,
  `imgSong` varchar(255) NOT NULL,
  `singer` varchar(255) NOT NULL,
  `linkSong` varchar(255) NOT NULL,
  `likeSong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `song`
--

INSERT INTO `song` (`idSong`, `idAlbum`, `idCategory`, `idPlaylist`, `idRankSong`, `rank`, `nameSong`, `imgSong`, `singer`, `linkSong`, `likeSong`) VALUES
(1, '1', '0', '1,2,4,5,7', '1', 5, 'Thích Thì Đến ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/thichthiden.jpg', 'Lê Bảo Bình', 'https://vancntt99.000webhostapp.com/filemp3/thich_thi_den.mp3', 29),
(2, '2', '0', '1,2,4,5,7', '1', 4, 'Em Không Sai Chúng Ta Sai', 'https://megtroncntt99.000webhostapp.com/image/imgSong/ekscts.jpg', 'ERIK', 'https://vancntt99.000webhostapp.com/filemp3/emkhongsaichungtasai.mp3', 102),
(3, '2', '0', '0', '0', 0, 'Em Không Sai Chúng Ta Sai (Beat)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/ekscts.jpg', 'ERIK', 'https://vancntt99.000webhostapp.com/filemp3/Em-Khong-Sai-Chung-Ta-Sai-Beat-ERIK.mp3', 0),
(4, '0', '1', '0', '0', 0, 'How Would I Know', 'https://megtroncntt99.000webhostapp.com/image/imgSong/howwouldIknow.jpg', 'Kygo&Oh-Wonder', 'https://vancntt99.000webhostapp.com/filemp3/How-Would-I-Know-Kygo-Oh-Wonder.mp3', 0),
(5, '0', '1', '0', '0', 0, 'Turn Me On ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/turn_me_onjpg.jpg', 'Riton,Oliver-Heldens,Vula', 'https://vancntt99.000webhostapp.com/filemp3/Turn-Me-On-Riton-Oliver-Heldens-Vula.mp3', 0),
(6, '0', '1', '0', '0', 0, 'Secrets', 'https://megtroncntt99.000webhostapp.com/image/imgSong/Secrets.jpg', 'Regard,RAYE', 'https://vancntt99.000webhostapp.com/filemp3/Secrets-Regard-RAYE.mp3', 0),
(7, '0', '2,1', '6', '3', 6, 'Roses', 'https://megtroncntt99.000webhostapp.com/image/imgSong/roses.jpg', 'SAINt JHN, Imanbek', 'https://vancntt99.000webhostapp.com/filemp3/Roses-SAINt-JHN-Imanbek.mp3', 30),
(8, '0', '2', '6', '0', 0, 'Alone, Pt.II', 'https://megtroncntt99.000webhostapp.com/image/imgSong/alone.jpg', 'Alan Walker, Ava Max', 'https://vancntt99.000webhostapp.com/filemp3/Alone-Pt-II-Alan-Walker-Ava-Max.mp3', 11),
(9, '0', '3', '0', '0', 0, 'Used To Love (Acoustic Version)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/usedtolove.jpg', 'Martin Garrix, Dean Lewis', 'https://vancntt99.000webhostapp.com/filemp3/Used-To-Love-Acoustic-Version-Martin-Garrix-Dean-Lewis.mp3', 0),
(10, '0', '3', '0', '0', 0, 'Electricity(Acoustic)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/electricity.jpg', 'Silk City, Diplo, Dua lipa', 'https://vancntt99.000webhostapp.com/filemp3/Electricity-Acoustic-Nhieu-nghe-si.mp3', 0),
(11, '0', '4', '0', '0', 0, 'Amaranth', 'https://megtroncntt99.000webhostapp.com/image/imgSong/amaranth.jpg', 'CHEN', 'https://vancntt99.000webhostapp.com/filemp3/Amaranth-CHEN.mp3', 0),
(12, '0', '4', '0', '0', 0, 'Aewol', 'https://megtroncntt99.000webhostapp.com/image/imgSong/aewol.jpg', 'MoonMoon', 'https://vancntt99.000webhostapp.com/filemp3/Aewol-MoonMoon.mp3', 0),
(13, '0', '0', '3', '2', 4, 'Cheer Up', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cheerup.jpg', 'Hong Jin Young', 'https://vancntt99.000webhostapp.com/filemp3/Cheer-Up-Hong-Jin-Young.mp3', 2),
(14, '0', '0', '3', '2', 6, 'Way Back Home', 'https://megtroncntt99.000webhostapp.com/image/imgSong/waybackhome.jpg', 'Shaun', 'https://vancntt99.000webhostapp.com/filemp3/Way-Back-Home-Shaun.mp3', 2),
(15, '0', '0', '6', '3', 1, 'Savage Love (Laxed-Siren Beat)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/savagelove.jpg', 'Jawsh 685, Jason Derulo', 'https://vancntt99.000webhostapp.com/filemp3/Savage-Love-Laxed-Siren-Beat.mp3', 3),
(16, '0', '0', '6', '3', 3, 'Dancing With Your Ghost', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dancingwithyourghost.jpg', 'Sasha Sloan', 'https://vancntt99.000webhostapp.com/filemp3/Dancing-With-Your-Ghost-Sasha-Sloan.mp3', 0),
(17, '0', '0', '0', '0', 0, 'Dear Mom (Single)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dearmom.jpg', 'INSOLENT, Linh Dino, CM1X', 'https://vancntt99.000webhostapp.com/filemp3/Dear-Mom-INSOLENT-Linh-Dino-CM1X.mp3', 0),
(18, '0', '0', '0', '0', 0, 'Chắc Cô Ta Đâu Biết (Single)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/chaccotadaubiet.jpg', 'Huỳnh Ái Vy', 'https://vancntt99.000webhostapp.com/filemp3/Chac-Co-Ta-Dau-Biet-Huynh-Ai-Vy.mp3', 12),
(19, '0', '0', '0', '0', 0, 'Nguyễn Văn Vân _ CNTT_K58', 'https://megtroncntt99.000webhostapp.com/image/imgSong/utc2.png', '0', 'https://www.facebook.com/profile.php?id=100029315182429', -1000),
(20, '0', '0', '1,4,7', '1', 3, 'Cứ Thế Rời Xa', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cutheroixa.jpg', 'Yến Tatoo, Great', 'https://vancntt99.000webhostapp.com/filemp3/Cu-The-Roi-Xa-Yen-Tatoo-Great.mp3', 105),
(21, '0', '0', '7', '1', 7, 'Tình Anh', 'https://megtroncntt99.000webhostapp.com/image/imgSong/tinhanh.jpg', 'Đình Dũng, ACV', 'https://vancntt99.000webhostapp.com/filemp3/tinhanh.mp3', 0),
(22, '0', '0', '0', '1', 6, 'Đừng Thương', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dungthuong.jpg', 'KatKaa', 'https://vancntt99.000webhostapp.com/filemp3/Dung-Thuong-DatKaa.mp3', 0),
(23, '0', '0', '2,7', '1', 8, 'Tình Sầu Thiên Thu Muôn Lối', 'https://megtroncntt99.000webhostapp.com/image/imgSong/t%C3%ADnhauthienthumuonloi.jpg', 'Doãn Hiếu', 'https://vancntt99.000webhostapp.com/filemp3/Tinh-Sau-Thien-Thu-Muon-Loi-Doan-Hieu.mp3', 0),
(24, '0', '0', '7', '1', 9, 'Yêu Từ Đâu Mà Ra?', 'https://megtroncntt99.000webhostapp.com/image/imgSong/yeutudaumara.jpg', 'Lil Zpoet', 'https://vancntt99.000webhostapp.com/filemp3/Yeu-Tu-Dau-Ma-Ra-Lil-Zpoet.mp3', 0),
(25, '0', '0', '1,2,4,7', '1', 2, 'Cho Anh Say', 'https://megtroncntt99.000webhostapp.com/image/imgSong/choanhsay.jpg', 'Phan Duy Anh, ACV', 'https://vancntt99.000webhostapp.com/filemp3/Cho-Anh-Say-Phan-Duy-Anh-ACV.mp3', 0),
(26, '0', '0', '0', '1', 10, 'Cứ Ngỡ Là Anh', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cungolaanh.jpg', 'Đinh Tùng Huy', 'https://vancntt99.000webhostapp.com/filemp3/Cu-Ngo-La-Anh-Dinh-Tung-Huy.mp3', 0),
(27, '0', '0', '2,4', '1', 11, 'Đừng Lo Anh Đợi Mà', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dungloanhdoima.jpg', 'Mr Siro', 'https://vancntt99.000webhostapp.com/filemp3/Dung-Lo-Anh-Doi-Ma-Mr-Siro.mp3', 0),
(28, '0', '0', '0', '2', 1, 'How You Like That', 'https://megtroncntt99.000webhostapp.com/image/imgSong/howyoulikethat.jpg', 'BLACKPINK', 'https://vancntt99.000webhostapp.com/filemp3/HowYouLikeThat-BlackPink-6309489.mp3', 1),
(29, '0', '0', '0', '2', 2, 'Eight', 'https://megtroncntt99.000webhostapp.com/image/imgSong/eight.jpg', 'IU, SUGA', 'https://vancntt99.000webhostapp.com/filemp3/eight-IUSugaBTS-6271822.mp3', 0),
(30, '0', '0', '3', '2', 3, 'Kill This Love', 'https://megtroncntt99.000webhostapp.com/image/imgSong/killthislove.jpg', 'BLACKPINK', 'https://vancntt99.000webhostapp.com/filemp3/Kill-This-Love-BLACKPINK.mp3', 2),
(31, '0', '0', '3', '2', 7, 'Psycho', 'https://megtroncntt99.000webhostapp.com/image/imgSong/psycho.jpg', 'Red Velvet', 'https://vancntt99.000webhostapp.com/filemp3/Psycho-Red-Velvet.mp3', 0),
(32, '0', '0', '0', '2', 8, 'DDU-DU DDU-DU', 'https://megtroncntt99.000webhostapp.com/image/imgSong/ddu_du_du.jpg', 'BLACKPINK', 'https://vancntt99.000webhostapp.com/filemp3/DDU-DU-DDU.mp3', 0),
(33, '0', '0', '0', '2', 10, 'Beginning', 'https://megtroncntt99.000webhostapp.com/image/imgSong/beginning.jpg', 'GAHO', 'https://vancntt99.000webhostapp.com/filemp3/Beginning-Gaho.mp3', 1),
(34, '0', '0', '3', '2', 5, 'SOLO', 'https://megtroncntt99.000webhostapp.com/image/imgSong/solo.jpg', 'JENNIE', 'https://vancntt99.000webhostapp.com/filemp3/SOLO-JENNIE.mp3', 0),
(35, '0', '0', '0', '2', 9, 'DUN DUN', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dundun.jpg', 'EVERGLOW', 'https://vancntt99.000webhostapp.com/filemp3/DUN-DUN-EVERGLOW.mp3', 0),
(36, '0', '0', '0', '3', 2, 'I Don\'t Care', 'https://megtroncntt99.000webhostapp.com/image/imgSong/idontcare.jpg', 'Ed Sheeran, Justin Bieber', 'https://vancntt99.000webhostapp.com/filemp3/I-Don-t-Care-Ed-Sheeran-Justin-Bieber.mp3', 0),
(37, '0', '0', '0', '3', 4, 'FRIENDS', 'https://megtroncntt99.000webhostapp.com/image/imgSong/friends.jpg', 'Marshmello, Anne', 'https://vancntt99.000webhostapp.com/filemp3/FRIENDS-Marshmello-Anne.mp3', 0),
(38, '0', '0', '6', '3', 5, 'Wrap Me In Plastic', 'https://megtroncntt99.000webhostapp.com/image/imgSong/wrapmeinplastin.jpg', 'CHROMANCE', 'https://vancntt99.000webhostapp.com/filemp3/Wrap-Me-In-Plastic-CHROMANCE.mp3', 0),
(39, '0', '0', '0', '3', 7, 'The River', 'https://megtroncntt99.000webhostapp.com/image/imgSong/theriver.jpg', 'Axel Johansson', 'https://vancntt99.000webhostapp.com/filemp3/The-River-Axel-Johansson.mp3', 0),
(40, '0', '0', '0', '3', 8, 'How Long', 'https://megtroncntt99.000webhostapp.com/image/imgSong/howlong.jpg', 'Charlie Puth', 'https://vancntt99.000webhostapp.com/filemp3/How-Long-Charlie-Puth.mp3', 0),
(41, '', '0', '0', '3', 9, 'Attention', 'https://megtroncntt99.000webhostapp.com/image/imgSong/attention.jpg', 'J.Fla', 'https://vancntt99.000webhostapp.com/filemp3/Attention-J-Fla.mp3', 0),
(42, '0', '0', '0', '3', 10, 'Dusk Till Dawn', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dusktilldawn.jpg', 'ZAYN, Sia', 'https://vancntt99.000webhostapp.com/filemp3/Dusk-Till-Dawn-ZAYN-Sia.mp3', 0),
(43, '0', '0', '1,2,4,5', '0', 0, 'Không Thể Cùng Nhau Suốt Kiếp', 'https://megtroncntt99.000webhostapp.com/image/imgSong/khongthecungnhausuotkiep.jpg', 'Hòa Minzy, Mr Siro', 'https://vancntt99.000webhostapp.com/filemp3/Khong-The-Cung-Nhau-Suot-Kiep-Hoa-Minzy-Mr-Siro.mp3', 0),
(44, '0', '0', '1,2,4,5,7', '0', 0, 'Hơn Cả Yêu', 'https://megtroncntt99.000webhostapp.com/image/imgSong/honcayeu.jpg', 'Đức Phúc', 'https://vancntt99.000webhostapp.com/filemp3/Hon-Ca-Yeu-Duc-Phuc.mp3', 0),
(45, '0', '0', '1,2,5,7', '0', 0, 'Tặng Anh Cho Cô Ấy', 'https://megtroncntt99.000webhostapp.com/image/imgSong/tanganhchocoay.jpg', 'Hương Giang', 'https://vancntt99.000webhostapp.com/filemp3/Tang-Anh-Cho-Co-Ay-Huong-Giang.mp3', 0),
(46, '0', '0', '1,2', '0', 0, 'Cướp Đi Cả Thế Giới', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cuopdicathegio.jpg', 'Bảo Yến Rosie, Great', 'https://vancntt99.000webhostapp.com/filemp3/Cuop-Di-Ca-The-Gioi-Bao-Yen-Rosie-Great.mp3', 0),
(47, '0', '0', '1,2', '0', 0, 'Cô Đơn Không Muốn Về Nhà', 'https://megtroncntt99.000webhostapp.com/image/imgSong/codonkhongmuonvenha.jpg', 'Mr Siro', 'https://vancntt99.000webhostapp.com/filemp3/Co-Don-Khong-Muon-Ve-Nha-Mr-Siro.mp3', 1),
(48, '0', '0', '1,2,5', '0', 0, 'Lá Xa Lìa Cành', 'https://megtroncntt99.000webhostapp.com/image/imgSong/laxaliacanh.jpg', 'Lê Bảo Bình', 'https://vancntt99.000webhostapp.com/filemp3/La-Xa-Lia-Canh-Le-Bao-Binh.mp3', 0),
(49, '0', '0', '2', '0', 0, 'Một Cú Lừa', 'https://megtroncntt99.000webhostapp.com/image/imgSong/motculua.jpg', 'Bích Phương', 'https://vancntt99.000webhostapp.com/filemp3/motculua.mp3', 0),
(50, '0', '0', '2,5,7', '0', 0, 'Bánh Mì Không', 'https://megtroncntt99.000webhostapp.com/image/imgSong/banhmikhong.jpg', 'Đạt G, DuUyen', 'https://vancntt99.000webhostapp.com/filemp3/Banh-Mi-Khong-Dat-G-DuUyen.mp3', 0),
(51, '0', '0', '2', '0', 0, 'Yêu Một Người Tổn Thương', 'https://megtroncntt99.000webhostapp.com/image/imgSong/yeumotnguoitonthuong.jpg', 'Nhật Phong', 'https://vancntt99.000webhostapp.com/filemp3/Yeu-Mot-Nguoi-Ton-Thuong-Nhat-Phong.mp3', 0),
(52, '0', '0', '2,5', '0', 0, 'Anh Ta Bỏ Em Rồ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/anhtaboemroi.jpg', 'Hương Giang', 'https://vancntt99.000webhostapp.com/filemp3/Anh-Ta-Bo-Em-Roi-Huong-Giang.mp3', 0),
(54, '0', '0', '1', '1', 1, 'Có Chắc Yêu Là Đây', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cochacyeuladay.jpg', 'Sơn Tùng M-TP', 'https://vancntt99.000webhostapp.com/filemp3/Co-Chac-Yeu-La-Day-M-TP.mp3', 1002),
(55, '0', '0', '3', '0', 0, 'Gee', 'https://megtroncntt99.000webhostapp.com/image/imgSong/gee.jpg', 'SNSD', 'https://vancntt99.000webhostapp.com/filemp3/Gee-SNSD.mp3', 0),
(56, '0', '0', '3', '0', 0, 'Haru Haru', 'https://megtroncntt99.000webhostapp.com/image/imgSong/haru_haru.jpg', 'BIGBANG', 'https://vancntt99.000webhostapp.com/filemp3/Haru-Haru-BIGBANG.mp3', 0),
(57, '0', '0', '3', '0', 0, 'Roly Poly', 'https://megtroncntt99.000webhostapp.com/image/imgSong/roly_roly.jpg', 'T-ARA', 'https://vancntt99.000webhostapp.com/filemp3/Roly-Poly-T-ARA.mp3', 0),
(58, '0', '0', '3', '0', 0, 'I Am The Best', 'https://megtroncntt99.000webhostapp.com/image/imgSong/iamthebeat.jpg', '2NE1', 'https://vancntt99.000webhostapp.com/filemp3/I-Am-The-Best-2NE1.mp3', 0),
(59, '0', '0', '3', '0', 0, 'Fantastic Baby', 'https://megtroncntt99.000webhostapp.com/image/imgSong/fantasticbaby.jpg', 'BIGBANG', 'https://vancntt99.000webhostapp.com/filemp3/Fantastic-Baby-BIGBANG.mp3', 0),
(60, '0', '0', '3', '0', 0, 'Nobody', 'https://megtroncntt99.000webhostapp.com/image/imgSong/nobody.jpg', 'Wonder Girls', 'https://vancntt99.000webhostapp.com/filemp3/Nobody-Wonder-Girls.mp3', 0),
(61, '0', '0', '3', '0', 0, 'Fiction', 'https://megtroncntt99.000webhostapp.com/image/imgSong/fiction.jpg', 'BEAST', 'https://vancntt99.000webhostapp.com/filemp3/Fiction-BEAST.mp3', 0),
(62, '0', '0', '3', '0', 0, 'BANG BANG BANG', 'https://megtroncntt99.000webhostapp.com/image/imgSong/bangbangbang.jpg', 'BIGBANG', 'https://vancntt99.000webhostapp.com/filemp3/BANG-BANG-BANG-BIGBANG.mp3', 0),
(63, '0', '0', '3', '0', 0, 'I Got A Boy', 'https://megtroncntt99.000webhostapp.com/image/imgSong/igotaboy.jpg', 'SNSD', 'https://vancntt99.000webhostapp.com/filemp3/I-Got-A-Boy-SNSD.mp3', 0),
(64, '0', '0', '3', '0', 0, 'LOVE SCENARIO', 'https://megtroncntt99.000webhostapp.com/image/imgSong/love_scenario.jpg', 'iKON', 'https://vancntt99.000webhostapp.com/filemp3/LOVE-SCENARIO-iKON.mp3', 0),
(65, '0', '0', '4,2', '0', 0, 'Bigcityboi', 'https://megtroncntt99.000webhostapp.com/image/imgSong/Bigcityboi.jpg', 'Binz, Touliver', 'https://vancntt99.000webhostapp.com/filemp3/Bigcityboi-Binz-Touliver.mp3', 0),
(66, '0', '0', '4,5', '0', 0, 'Duyên Âm', 'https://megtroncntt99.000webhostapp.com/image/imgSong/duyenam.jpg', 'Hoàng Thùy Linh', 'https://vancntt99.000webhostapp.com/filemp3/Duyen-Am-Hoang-Thuy-Linh.mp3', 0),
(67, '0', '0', '4,7', '0', 0, 'Yêu Thì Yêu Không Yêu Thì Yêu', 'https://megtroncntt99.000webhostapp.com/image/imgSong/yeuthiyeukhongyeuthiyeu.jpg', 'AMEE', 'https://vancntt99.000webhostapp.com/filemp3/Yeu-Thi-Yeu-Khong-Yeu-Thi-Yeu-AMEE.mp3', 0),
(68, '0', '0', '4,2,5,7', '0', 0, 'Sao Anh Chưa Về Nhà', 'https://megtroncntt99.000webhostapp.com/image/imgSong/saoanhchuave.jpg', 'AMEE, Ricky Star', 'https://vancntt99.000webhostapp.com/filemp3/Sao-Anh-Chua-Ve-Nha-AMEE-Ricky-Star.mp3', 0),
(69, '0', '0', '4,2', '0', 0, 'Ai Đợi Mình Được Mãi', 'https://megtroncntt99.000webhostapp.com/image/imgSong/aidoiminhduocmai.jpg', 'Thanh Hưng', 'https://vancntt99.000webhostapp.com/filemp3/Ai-Doi-Minh-Duoc-Mai-Thanh-Hung.mp3', 0),
(70, '0', '0', '4,5', '0', 0, 'Cố Giang Tình', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cogiangtinh.jpg', 'X2X', 'https://vancntt99.000webhostapp.com/filemp3/Co-Giang-Tinh-X2X.mp3', 0),
(71, '0', '0', '5', '0', 0, 'Kẻ Cắp Gặp Bà Già', 'https://megtroncntt99.000webhostapp.com/image/imgSong/kecapgapbagia.jpg', 'Hoàng Thùy Linh', 'https://vancntt99.000webhostapp.com/filemp3/Ke-Cap-Gap-Ba-Gia-Hoang-Thuy-Linh-BINZ.mp3', 0),
(72, '0', '0', '5', '0', 0, 'Em Gì Ơi', 'https://megtroncntt99.000webhostapp.com/image/imgSong/emgioi.jpg', 'Jack, K-ICM', 'https://vancntt99.000webhostapp.com/filemp3/Em-Gi-Oi-Jack-K-ICM.mp3', 0),
(73, '0', '0', '5,7', '0', 0, 'Simple Love', 'https://megtroncntt99.000webhostapp.com/image/imgSong/simplelove.jpg', 'Obito, Seachains, Davis', 'https://vancntt99.000webhostapp.com/filemp3/Simple-Love-Obito-Seachains-Davis.mp3', 0),
(74, '0', '0', '5', '0', 0, 'Để Mị Nói Cho Mà Nghe', 'https://megtroncntt99.000webhostapp.com/image/imgSong/deminoichomanghe.jpg', 'Hoàng Thùy Linh', 'https://vancntt99.000webhostapp.com/filemp3/De-Mi-Noi-Cho-Ma-Nghe-Hoang-Thuy-Linh.mp3', 0),
(75, '0', '0', '5', '0', 0, 'Hết Thương Cạn Nhớ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/hetthuongcannho.jpg', 'Đức Phúc', 'https://vancntt99.000webhostapp.com/filemp3/Het-Thuong-Can-Nho-Duc-Phuc.mp3', 0),
(76, '0', '0', '6', '3', 14, 'Don\'t Start Now', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dontstartnow.jpg', 'Dua Lipa', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Don-t-Start-Now-Dua-Lipa.mp3', 0),
(77, '0', '0', '6', '3', 12, 'End Of Time', 'https://megtroncntt99.000webhostapp.com/image/imgSong/endoftime.jpg', 'K-391, Alan Walker, Ahrix', 'https://appmusiccntt1999.000webhostapp.com/filemp3/End-Of-Time-K-391-Alan-Walker-Ahrix.mp3', 0),
(78, '0', '0', '6', '3', 13, 'Dance Monkey', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dance_monkey.jpg', 'Tones And I', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Dance-Monkey-Tones-And-I.mp3', 0),
(79, '0', '0', '6', '0', 0, 'Say So', 'https://megtroncntt99.000webhostapp.com/image/imgSong/sayno.jpg', 'Doja Cat, Nicki Minaj', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Say-So-Doja-Cat-Nicki-Minaj.mp3', 0),
(80, '0', '0', '6', '0', 0, 'Can We Kiss Forever?', 'https://megtroncntt99.000webhostapp.com/image/imgSong/canwekissforever.jpg', 'Kina, Adriana Proenza', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Can-We-Kiss-Forever-Kina-Adriana-Proenza.mp3', 0),
(81, '0', '0', '6', '0', 0, 'Break My Heart', 'https://megtroncntt99.000webhostapp.com/image/imgSong/break_my_heart.jpg', 'Dua Lipa', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Break-My-Heart-Dua-Lipa.mp3', 0),
(82, '0', '0', '6', '0', 0, 'ROXANNE', 'https://megtroncntt99.000webhostapp.com/image/imgSong/roxanne.jpg', 'Arizona Zervas', 'https://appmusiccntt1999.000webhostapp.com/filemp3/ROXANNE-Arizona-Zervas.mp3', 0),
(83, '0', '0', '6', '0', 0, 'Play', 'https://megtroncntt99.000webhostapp.com/image/imgSong/Play.jpg', 'K-391, Alan Walker, Martin Tungev', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Play-K-391-Alan-Walker-Martin-Tungev.mp3', 0),
(84, '0', '0', '6', '0', 0, 'Physical', 'https://megtroncntt99.000webhostapp.com/image/imgSong/physical.jpg', 'Dua Lipa', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Physical-Dua-Lipa.mp3', 0),
(85, '0', '0', '6', '3', 14, 'I Love You 3000', 'https://megtroncntt99.000webhostapp.com/image/imgSong/iloveyou3000.jpg', 'Stephanie Poetri', 'https://appmusiccntt1999.000webhostapp.com/filemp3/I-Love-You-3000-Stephanie-Poetri.mp3', 0),
(86, '0', '0', '6', '0', 0, 'Kings & Queens', 'https://megtroncntt99.000webhostapp.com/image/imgSong/king_queen.jpg', 'Ava Max', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Kings-Queens-Ava-Max.mp3', 0),
(87, '0', '0', '3', '2', 14, 'I Will Show You', 'https://megtroncntt99.000webhostapp.com/image/imgSong/iwillshowyou.jpg', 'Ailee', 'https://appmusiccntt1999.000webhostapp.com/filemp3/I-Will-Show-You-Ailee.mp3', 0),
(88, '0', '0', '7', '0', 0, 'Cần Gì Hơn', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cangihon.jpg', 'Tiên Tiên, JustaTee', 'https://vancntt99.000webhostapp.com/filemp3/Can-Gi-Hon-Tien-Tien-JustaTee.mp3', 0),
(89, '0', '0', '7', '0', 0, 'Mascara', 'https://megtroncntt99.000webhostapp.com/image/imgSong/mascara.jpg', 'Chillies', 'https://vancntt99.000webhostapp.com/filemp3/Mascara-Chillies.mp3', 0),
(90, '0', '0', '7', '0', 0, 'Lười Yêu (Dance Version)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/luoiyeu.jpg', 'Bảo Anh, Brittanya Karma', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Luoi-Yeu-Dance-Version-Bao-Anh-Brittanya-Karma.mp3', 0),
(91, '4', '0', '0', '0', 0, 'Cây Đàn Sinh Viên', 'https://megtroncntt99.000webhostapp.com/image/imgSong/caydansinhvien.jpg', 'Mỹ Tâm', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Cay-Dan-Sinh-Vien-My-Tam.mp3', 2),
(92, '4', '0', '0', '0', 0, 'Nhé Anh', 'https://megtroncntt99.000webhostapp.com/image/imgSong/nheanh.jpg', 'Mỹ Tâm', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Nhe-Anh-My-Tam.mp3', 4),
(93, '4', '0', '0', '0', 0, 'Ngỡ Đâu Tình Đã Quên Mình', 'https://megtroncntt99.000webhostapp.com/image/imgSong/ngodautinhdaquenminh.jpg', 'Mỹ Tâm', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Ngo-Dau-Tinh-Da-Quen-Minh-My-Tam.mp3', 7),
(94, '4', '0', '0', '0', 0, 'Tình Xót Xa Thôi', 'https://megtroncntt99.000webhostapp.com/image/imgSong/tinhxotxathoi.jpg', 'Mỹ Tâm', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Tinh-Xot-Xa-Thoi-My-Tam.mp3', 7),
(95, '4', '0', '0', '0', 0, 'Hát Cho Người Ở Lại', 'https://megtroncntt99.000webhostapp.com/image/imgSong/hatchonguoiolai.jpg', 'Mỹ Tâm', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Hat-Cho-Nguoi-O-Lai-My-Tam.mp3', 2),
(96, '4', '0', '0', '0', 0, 'Mãi Yêu', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Mai-Yeu-My-Tam.mp3', 'Mỹ Tâm', 'https://megtroncntt99.000webhostapp.com/image/imgSong/maiyeu.jpg', 9),
(97, '4', '0', '0', '0', 0, 'Hát Lên Bạn Nhé', 'https://megtroncntt99.000webhostapp.com/image/imgSong/hatlenbannhe.jpg', 'Mỹ Tâm', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Hat-Len-Ban-Nhe-My-Tam.mp3', 42),
(98, '3', '0', '0', '0', 0, 'Sparks Fly (Live 2011)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/taylotswift.jpg', 'Taylor Swift, David Cook', 'https://appmusiccntt1999.000webhostapp.com/filemp3/SparksFlyLive2011-TaylorSwiftDavidCook-5046590.mp3', 0),
(99, '3', '0', '0', '0', 0, 'Mine (Live 2011) ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/taylotswift.jpg', 'Taylor Swift, David Cook', 'https://appmusiccntt1999.000webhostapp.com/filemp3/MineLive2011-TaylorSwiftDavidCook-5046591.mp3', 0),
(100, '3', '0', '0', '0', 0, 'The Story Of Us (Live 2011) ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/taylotswift.jpg', 'Taylor Swift, David Cook', 'https://appmusiccntt1999.000webhostapp.com/filemp3/TheStoryOfUsLive2011-TaylorSwiftDavidCook-5046592.mp3', 0),
(101, '3', '0', '0', '0', 0, 'Mean (Live 2011)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/taylotswift.jpg', 'Taylor Swift, David Cook', 'https://appmusiccntt1999.000webhostapp.com/filemp3/MeanLive2011-TaylorSwiftDavidCook-5046593.mp3', 0),
(102, '3', '0', '0', '0', 0, 'Ours (Live 2011)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/taylotswift.jpg', 'Taylor Swift, David Cook', 'https://appmusiccntt1999.000webhostapp.com/filemp3/OursLive2011-TaylorSwiftDavidCook-5046594.mp3', 0),
(103, '5', '0', '0', '0', 0, 'For You', 'https://megtroncntt99.000webhostapp.com/image/imgSong/foryou.jpg', 'CHEN, Baekhyun, XIUMIN', 'https://appmusiccntt1999.000webhostapp.com/filemp3/For-You-CHEN-Baekhyun-XIUMIN.mp3', 0),
(104, '5', '0', '0', '0', 0, 'Say Yes', 'https://megtroncntt99.000webhostapp.com/image/imgSong/sayyes.jpg', 'Loco, Punch', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Say-Yes-Loco-Punch.mp3', 0),
(105, '5', '0', '0', '0', 0, 'I Love You, I Remember You', 'https://megtroncntt99.000webhostapp.com/image/imgSong/iloveyou.jpg', 'I.O.I', 'https://appmusiccntt1999.000webhostapp.com/filemp3/I-Love-You-I-Remember-You-I-O-I.mp3', 0),
(106, '5', '0', '0', '0', 0, 'Forgetting You', 'https://megtroncntt99.000webhostapp.com/image/imgSong/forgettingyou.png', 'Davichi', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Forgetting-You-Davichi.mp3', 0),
(107, '5', '0', '0', '0', 0, 'All With You', 'https://megtroncntt99.000webhostapp.com/image/imgSong/allwithyou.jpg', 'TAEYEON', 'https://appmusiccntt1999.000webhostapp.com/filemp3/All-With-You-TAEYEON.mp3', 0),
(108, '5', '0', '0', '0', 0, 'Can You Hear My Heart', 'https://megtroncntt99.000webhostapp.com/image/imgSong/canyouhrearmyheart.jpg', 'Epik High, LEE HI', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Can-You-Hear-My-Heart-Epik-High-LEE-HI.mp3', 0),
(109, '5', '0', '0', '0', 0, 'A Lot Like Love', 'https://megtroncntt99.000webhostapp.com/image/imgSong/alotlikelove.jpg', 'Baek A Yeon', 'https://appmusiccntt1999.000webhostapp.com/filemp3/A-Lot-Like-Love-Baek-A-Yeon.mp3', 0),
(110, '5', '0', '0', '0', 0, 'Confess', 'https://megtroncntt99.000webhostapp.com/image/imgSong/confess.jpg', 'SG Wannabe', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Confess-SG-Wannabe.mp3', 0),
(111, '5', '0', '0', '0', 0, 'Will Be Back', 'https://megtroncntt99.000webhostapp.com/image/imgSong/willbeback.png', 'Sunhae Im', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Will-Be-Back-Sunhae-Im.mp3', 0),
(112, '5', '0', '0', '0', 0, 'My Love', 'https://megtroncntt99.000webhostapp.com/image/imgSong/mylove.jpg', 'LEE HI', 'https://appmusiccntt1999.000webhostapp.com/filemp3/My-Love-LEE-HI.mp3', 0),
(113, '0', '14', '0', '0', 0, 'Cần Xa', 'https://megtroncntt99.000webhostapp.com/image/imgSong/canxa.jpg', 'Hiền Hồ, Phúc Bồ, SlimV', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Can-Xa-Hien-Ho-Phuc-Bo-SlimV.mp3', 0),
(114, '0', '14', '0', '0', 0, 'Duyên Trời Lấy 2 (Remix)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/duyentroilayremix.jpg', 'Chung Thanh Duy', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Duyen-Troi-Lay-2-Remix-Chung-Thanh-Duy.mp3', 0),
(115, '0', '14', '0', '0', 0, 'Hai Phút Hơn', 'https://megtroncntt99.000webhostapp.com/image/imgSong/haiphuthon.jpg', 'Pháo, CM1X', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Hai-Phut-Hon-Phao-CM1X.mp3', 0),
(116, '0', '14', '0', '0', 0, 'Lười Yêu', 'https://megtroncntt99.000webhostapp.com/image/imgSong/luoiyeubaoanh.jpg', 'Bảo Anh', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Luoi-Yeu-Bao-Anh.mp3', 0),
(117, '0', '14', '0', '0', 0, 'Nắm (EDM Version)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/namedm.jpg', 'Minh Vương M4U, Hương Ly, ACV', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Nam-EDM-Version-Minh-Vuong-M4U-Huong-Ly-ACV.mp3', 0),
(118, '0', '14', '0', '0', 0, 'Túy Âm', 'https://megtroncntt99.000webhostapp.com/image/imgSong/tuyam.jpg', 'Xesi,Masew,Nhật Nguyễn', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Tuy-Am-Xesi-Masew-Nhat-Nguyen.mp3', 0),
(119, '0', '14', '0', '0', 0, 'Cùng Anh', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cunganh.jpg', 'Ngọc Dolil,Hagii,STee', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Cung-Anh-Ngoc-Dolil-Hagii-STee.mp3', 0),
(120, '0', '14', '0', '0', 0, 'Thằng Hầu (Remix)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/thanghauremix.jpg', 'Nhất Phong,DinhLong', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Thang-Hau-Remix-Nhat-Phong-DinhLong.mp3', 0),
(121, '0', '14', '0', '0', 0, 'Crush', 'https://megtroncntt99.000webhostapp.com/image/imgSong/crush.jpg', 'W,Vani,An An', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Crush-W-Vani-An-An.mp3', 0),
(122, '0', '14', '0', '0', 0, 'Như Lời Đồn', 'https://megtroncntt99.000webhostapp.com/image/imgSong/nhuloidon.jpg', 'Bảo Anh', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Nhu-Loi-Don-Bao-Anh.mp3', 0),
(123, '0', '5', '0', '0', 0, 'A Distant Shade of Green', 'https://megtroncntt99.000webhostapp.com/image/imgSong/adistantshade.jpg', 'Kevin Kern', 'https://appmusiccntt1999.000webhostapp.com/filemp3/A-Distant-Shade-of-Green-Kevin-Kern.mp3', 0),
(124, '0', '5', '0', '0', 0, 'Smile Smile Smile', 'https://megtroncntt99.000webhostapp.com/image/imgSong/smilesmilesmile.jpg', 'Jeon Soo Yeon', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Smile-Smile-Smile-Jeon-Soo-Yeon.mp3', 0),
(125, '0', '5', '0', '0', 0, 'Concerto For Flute, Harp And Orchestra In C Major, KV 299 - Allegro', 'https://megtroncntt99.000webhostapp.com/image/imgSong/connerttiforflute.jpg', 'Various Artists', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Concerto-For-Flute-Harp-And-Orchestra-In-C-Major-KV-299-Allegro.mp3', 0),
(126, '0', '5', '0', '0', 0, 'Clarinet Concerto In A Majo', 'https://megtroncntt99.000webhostapp.com/image/imgSong/clarinetconcerttoinamajo.jpg', 'Various Artists', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Clarinet-Concerto-In-A-Majo-Various-Artists.mp3', 0),
(127, '0', '5', '0', '0', 0, 'Piano Sonata No. 11 In A Major, K.331 Andante Grandioso', 'https://megtroncntt99.000webhostapp.com/image/imgSong/pianosonata.jpg', 'Various Artists', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Piano-Sonata-No-11-In-A-Major-K-331-Andante-Grandioso-Various-Artists.mp3', 0),
(128, '0', '6', '0', '0', 0, 'Peace (La Paix) - G. F. Handel', 'https://megtroncntt99.000webhostapp.com/image/imgSong/peace(lapaix).jpg', 'Stephanie Bennett', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Peace-La-Paix-G-F-Handel.mp3', 0),
(129, '0', '6', '0', '0', 0, 'Handel: Sarabande And Variations From Suite', 'https://megtroncntt99.000webhostapp.com/image/imgSong/handel.jpg', 'Jacques Loussier Trio', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Handel-Sarabande-And-Variations-From-Suite-No-11-Variation-No-2-Jacques-Loussier-Trio.mp3', 0),
(130, '0', '6', '0', '0', 0, 'Kommst Du Nun, Jesu, Vom Himmel Herunter, BWV 650', 'https://megtroncntt99.000webhostapp.com/image/imgSong/kommstdunun.jpg', 'Yo Yo Ma', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Kommst-Du-Nun-Jesu-Vom-Himmel-Herunter-BWV-650-Yo-Yo-Ma.mp3', 0),
(131, '0', '6', '0', '0', 0, 'Lachrymae - Pavane: Lachrymae Antique', 'https://megtroncntt99.000webhostapp.com/image/imgSong/lachrymae.jpg', 'Various artists', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Lachrymae-Pavane-Lachrymae-Antique-Various-Artists.mp3', 0),
(132, '0', '6', '0', '0', 0, 'Music For The Royal Fireworks, HWV 351 - I. Ouverture', 'https://megtroncntt99.000webhostapp.com/image/imgSong/musicfortheroyal.jpg', 'Jean François Paillard,Orchestre de Chambre', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Music-For-The-Royal-Fireworks-HWV-351-I-Ouverture.mp3', 0),
(133, '0', '7', '0', '0', 0, 'Thanksgiving', 'https://megtroncntt99.000webhostapp.com/image/imgSong/thanksgiving.jpg', 'George Winston', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Thanksgiving-George-Winston.mp3', 0),
(134, '0', '7', '0', '0', 0, 'Before I Go', 'https://megtroncntt99.000webhostapp.com/image/imgSong/beforeigo.jpg', 'Yanni', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Before-I-Go-Yanni.mp3', 0),
(135, '0', '7', '0', '0', 0, 'Only Time', 'https://megtroncntt99.000webhostapp.com/image/imgSong/onlytim.jpg', 'Enya', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Only-Time-Enya.mp3', 0),
(136, '0', '7', '0', '0', 0, 'Theme From Silk Road', 'https://megtroncntt99.000webhostapp.com/image/imgSong/themefromsilk.jpg', 'Kitaro', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Theme-From-Silk-Road-Kitaro.mp3', 0),
(137, '0', '7', '0', '0', 0, 'Sound Of Wind', 'https://megtroncntt99.000webhostapp.com/image/imgSong/soundofwind.jpg', 'Jupiter Blues', 'https://appmusiccntt1999.000webhostapp.com/filemp3/Sound-Of-Wind-Jupiter-Blues.mp3', 0);

-- --------------------------------------------------------

--
-- Table structure for table `theme`
--

CREATE TABLE `theme` (
  `idTheme` int(11) NOT NULL,
  `nameTheme` varchar(255) NOT NULL,
  `imgTheme` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `theme`
--

INSERT INTO `theme` (`idTheme`, `nameTheme`, `imgTheme`) VALUES
(1, 'EDM ', 'https://megtroncntt99.000webhostapp.com/image/imgTheme/edm.jpeg'),
(2, 'Acoustic', 'https://megtroncntt99.000webhostapp.com/image/imgTheme/acoustic.jpg'),
(3, 'Nhạc Không Lời', 'https://megtroncntt99.000webhostapp.com/image/imgTheme/nhackhongloi.jpg'),
(4, 'Tình Yêu', 'https://megtroncntt99.000webhostapp.com/image/imgTheme/nhactinhyeu.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD PRIMARY KEY (`idAdvertisement`);

--
-- Indexes for table `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`idAlbum`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`idCategory`);

--
-- Indexes for table `keyhot`
--
ALTER TABLE `keyhot`
  ADD PRIMARY KEY (`idKeyHot`);

--
-- Indexes for table `mv_music`
--
ALTER TABLE `mv_music`
  ADD PRIMARY KEY (`idMV`);

--
-- Indexes for table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`idPlaylist`);
ALTER TABLE `playlist` ADD FULLTEXT KEY `namePlayList` (`namePlayList`);

--
-- Indexes for table `ranksong`
--
ALTER TABLE `ranksong`
  ADD PRIMARY KEY (`IdRankSong`);

--
-- Indexes for table `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`idSong`),
  ADD KEY `nameSong` (`nameSong`);
ALTER TABLE `song` ADD FULLTEXT KEY `nameSong_2` (`nameSong`);

--
-- Indexes for table `theme`
--
ALTER TABLE `theme`
  ADD PRIMARY KEY (`idTheme`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `advertisement`
--
ALTER TABLE `advertisement`
  MODIFY `idAdvertisement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `album`
--
ALTER TABLE `album`
  MODIFY `idAlbum` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `idCategory` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `keyhot`
--
ALTER TABLE `keyhot`
  MODIFY `idKeyHot` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `mv_music`
--
ALTER TABLE `mv_music`
  MODIFY `idMV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `playlist`
--
ALTER TABLE `playlist`
  MODIFY `idPlaylist` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ranksong`
--
ALTER TABLE `ranksong`
  MODIFY `IdRankSong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `song`
--
ALTER TABLE `song`
  MODIFY `idSong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;

--
-- AUTO_INCREMENT for table `theme`
--
ALTER TABLE `theme`
  MODIFY `idTheme` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
