-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 03, 2020 at 08:40 AM
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
(2, 'Em Không Sai Chúng Ta Sai (Single)', 'ERIK', 'https://megtroncntt99.000webhostapp.com/image/imgAlbum/ekscts.jpg', 1),
(3, 'Speak Now World Tour Live (Live 2011)', 'Taylor Swift', 'https://megtroncntt99.000webhostapp.com/image/imgAlbum/sqeak_now_world_tour.jpg', 0);

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
(3, '2', 'Acoustic EDM', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/acousticedm.jpg', 0),
(4, '2', 'Acoustic Relax', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/acousticrelax.jpg', 0),
(5, '3', 'Những Bản Nhạc Kích Thích Trí Não', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/nhung_ban_nhac_kich_thich_tri_nao.jpg', 0),
(6, '3', 'Nhạc Baroque Giúp Tập Trung Làm Việc', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/nhac_giup_tap_trung_lam_viec.jpg', 0),
(7, '3', 'New Age Sound', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/new_age_sound.jpg', 0),
(8, '4', 'Thả Thính Là Phải Dính!', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/tha_thich_la_phai_dich.jpg', 0),
(9, '4', 'Yêu Không Cần Cớ, Cần Anh Cơ!', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/yeu_khong_can_co.jpg', 0),
(10, '4', 'Do You Wanna MARRY ME?', 'https://megtroncntt99.000webhostapp.com/image/imgCategory/do_you_wanna_mary_me.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `mv_music`
--

CREATE TABLE `mv_music` (
  `idMV` varchar(255) NOT NULL,
  `idSong` int(11) NOT NULL,
  `imgSong` varchar(255) NOT NULL,
  `imgSinger` varchar(255) NOT NULL,
  `txtTimeMV` varchar(255) NOT NULL,
  `likeMVMusic` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mv_music`
--

INSERT INTO `mv_music` (`idMV`, `idSong`, `imgSong`, `imgSinger`, `txtTimeMV`, `likeMVMusic`) VALUES
('iwGuiSnr2Qc', 2, 'https://megtroncntt99.000webhostapp.com/image/imgMV/emkhongsaichungtasai.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/erik.jpg', '06:28', 2),
('j4Jj29mUYS8', 1, 'https://megtroncntt99.000webhostapp.com/image/imgMV/thichthiden.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/lebaobinh.jpg', '06:04', 0),
('rvBF-cov4TE', 18, 'https://megtroncntt99.000webhostapp.com/image/imgMV/chaccotadaubiet_mv.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/huynh_ai_vi.jpg', '04:45', 0),
('RYV1s0ylNFM', 13, 'https://megtroncntt99.000webhostapp.com/image/imgMV/cheerup_mv.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgSinger/hong_jin_young.jpg', '04:41', 0);

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
(1, 'Top 100 Nhạc Hàn Quốc Hay Nhất', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nen_top100kpop.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/top100kpop.jpg', 3),
(2, 'Top 100 Bài Hát Nhạc Trẻ Hay Nhất', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nen_top100_nhactre.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/top100nhactre.jpg', 9),
(3, 'Top 100 Pop Âu Mỹ Hay Nhất', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nen_top100popaumy.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/top100popaumy.jpg', 0),
(4, 'Nghe Thôi... Đã Thấy HIT', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nen.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nghethoidahit.jpg', 0),
(5, 'Đỉnh Cao TRENDING', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nen.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/dinhcaotreding.jpg', 0),
(6, 'USUK Nghe Nhiều 2020', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/nen.jpg', 'https://megtroncntt99.000webhostapp.com/image/imgPlaylist/usuk_nghe_nhieu_nhat2020.jpg', 0),
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
(1, '1', '0', '2', '1', 3, 'Thích Thì Đến ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/thichthiden.jpg', 'Lê Bảo Bình', 'https://vancntt99.000webhostapp.com/filemp3/thich_thi_den.mp3', 22),
(2, '2', '0', '2', '1', 1, 'Em Không Sai Chúng Ta Sai', 'https://megtroncntt99.000webhostapp.com/image/imgSong/ekscts.jpg', 'ERIK', 'https://vancntt99.000webhostapp.com/filemp3/emkhongsaichungtasai.mp3', 100),
(3, '2', '0', '0', '0', 0, 'Em Không Sai Chúng Ta Sai (Beat)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/ekscts.jpg', 'ERIK', 'https://vancntt99.000webhostapp.com/filemp3/Em-Khong-Sai-Chung-Ta-Sai-Beat-ERIK.mp3', 0),
(4, '0', '1', '0', '0', 0, 'How Would I Know', 'https://megtroncntt99.000webhostapp.com/image/imgSong/howwouldIknow.jpg', 'Kygo&Oh-Wonder', 'https://vancntt99.000webhostapp.com/filemp3/How-Would-I-Know-Kygo-Oh-Wonder.mp3', 0),
(5, '0', '1', '0', '0', 0, 'Turn Me On ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/turn_me_onjpg.jpg', 'Riton,Oliver-Heldens,Vula', 'https://vancntt99.000webhostapp.com/filemp3/Turn-Me-On-Riton-Oliver-Heldens-Vula.mp3', 0),
(6, '0', '1', '0', '0', 0, 'Secrets', 'https://megtroncntt99.000webhostapp.com/image/imgSong/Secrets.jpg', 'Regard,RAYE', 'https://vancntt99.000webhostapp.com/filemp3/Secrets-Regard-RAYE.mp3', 0),
(7, '0', '2', '0', '3', 6, 'Roses', 'https://megtroncntt99.000webhostapp.com/image/imgSong/roses.jpg', 'SAINt JHN, Imanbek', 'https://vancntt99.000webhostapp.com/filemp3/Roses-SAINt-JHN-Imanbek.mp3', 26),
(8, '0', '2', '0', '0', 0, 'Alone, Pt.II', 'https://megtroncntt99.000webhostapp.com/image/imgSong/alone.jpg', 'Alan Walker, Ava Max', 'https://vancntt99.000webhostapp.com/filemp3/Alone-Pt-II-Alan-Walker-Ava-Max.mp3', 9),
(9, '0', '3', '0', '0', 0, 'Used To Love (Acoustic Version)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/usedtolove.jpg', 'Martin Garrix, Dean Lewis', 'https://vancntt99.000webhostapp.com/filemp3/Used-To-Love-Acoustic-Version-Martin-Garrix-Dean-Lewis.mp3', 0),
(10, '0', '3', '0', '0', 0, 'Electricity(Acoustic)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/electricity.jpg', 'Silk City, Diplo, Dua lipa', 'https://vancntt99.000webhostapp.com/filemp3/Electricity-Acoustic-Nhieu-nghe-si.mp3', 0),
(11, '0', '4', '0', '0', 0, 'Amaranth', 'https://megtroncntt99.000webhostapp.com/image/imgSong/amaranth.jpg', 'CHEN', 'https://vancntt99.000webhostapp.com/filemp3/Amaranth-CHEN.mp3', 0),
(12, '0', '4', '0', '0', 0, 'Aewol', 'https://megtroncntt99.000webhostapp.com/image/imgSong/aewol.jpg', 'MoonMoon', 'https://vancntt99.000webhostapp.com/filemp3/Aewol-MoonMoon.mp3', 0),
(13, '0', '0', '1', '2', 4, 'Cheer Up', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cheerup.jpg', 'Hong Jin Young', 'https://vancntt99.000webhostapp.com/filemp3/Cheer-Up-Hong-Jin-Young.mp3', 0),
(14, '0', '0', '1', '2', 6, 'Way Back Home', 'https://megtroncntt99.000webhostapp.com/image/imgSong/waybackhome.jpg', 'Shaun', 'https://vancntt99.000webhostapp.com/filemp3/Way-Back-Home-Shaun.mp3', 1),
(15, '0', '0', '3', '3', 1, 'Savage Love (Laxed-Siren Beat)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/savagelove.jpg', 'Jawsh 685, Jason Derulo', 'https://vancntt99.000webhostapp.com/filemp3/Savage-Love-Laxed-Siren-Beat.mp3', 0),
(16, '0', '0', '3', '3', 3, 'Dancing With Your Ghost', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dancingwithyourghost.jpg', 'Sasha Sloan', 'https://vancntt99.000webhostapp.com/filemp3/Dancing-With-Your-Ghost-Sasha-Sloan.mp3', 0),
(17, '0', '0', '0', '0', 0, 'Dear Mom (Single)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dearmom.jpg', 'INSOLENT, Linh Dino, CM1X', 'https://vancntt99.000webhostapp.com/filemp3/Dear-Mom-INSOLENT-Linh-Dino-CM1X.mp3', 0),
(18, '0', '0', '0', '0', 0, 'Chắc Cô Ta Đâu Biết (Single)', 'https://megtroncntt99.000webhostapp.com/image/imgSong/chaccotadaubiet.jpg', 'Huỳnh Ái Vy', 'https://vancntt99.000webhostapp.com/filemp3/Chac-Co-Ta-Dau-Biet-Huynh-Ai-Vy.mp3', 10),
(19, '0', '0', '0', '0', 0, 'Nguyễn Văn Vân _ CNTT_K58', 'https://megtroncntt99.000webhostapp.com/image/imgSong/utc2.png', '0', '0', 0),
(20, '0', '0', '0', '1', 2, 'Cứ Thế Rời Xa', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cutheroixa.jpg', 'Yến Tatoo, Great', 'https://vancntt99.000webhostapp.com/filemp3/Cu-The-Roi-Xa-Yen-Tatoo-Great.mp3', 99),
(21, '0', '0', '0', '1', 6, 'Tình Anh', 'https://megtroncntt99.000webhostapp.com/image/imgSong/tinhanh.jpg', 'Đình Dũng, ACV', 'https://vancntt99.000webhostapp.com/filemp3/tinhanh.mp3', 0),
(22, '0', '0', '0', '1', 5, 'Đừng Thương', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dungthuong.jpg', 'KatKaa', 'https://vancntt99.000webhostapp.com/filemp3/Dung-Thuong-DatKaa.mp3', 0),
(23, '0', '0', '0', '1', 7, 'Tình Sầu Thiên Thu Muôn Lối', 'https://megtroncntt99.000webhostapp.com/image/imgSong/t%C3%ADnhauthienthumuonloi.jpg', 'Doãn Hiếu', 'https://vancntt99.000webhostapp.com/filemp3/Tinh-Sau-Thien-Thu-Muon-Loi-Doan-Hieu.mp3', 0),
(24, '0', '0', '0', '1', 8, 'Yêu Từ Đâu Mà Ra?', 'https://megtroncntt99.000webhostapp.com/image/imgSong/yeutudaumara.jpg', 'Lil Zpoet', 'https://vancntt99.000webhostapp.com/filemp3/Yeu-Tu-Dau-Ma-Ra-Lil-Zpoet.mp3', 0),
(25, '0', '0', '0', '1', 4, 'Cho Anh Say', 'https://megtroncntt99.000webhostapp.com/image/imgSong/choanhsay.jpg', 'Phan Duy Anh, ACV', 'https://vancntt99.000webhostapp.com/filemp3/Cho-Anh-Say-Phan-Duy-Anh-ACV.mp3', 0),
(26, '0', '0', '0', '1', 9, 'Cứ Ngỡ Là Anh', 'https://megtroncntt99.000webhostapp.com/image/imgSong/cungolaanh.jpg', 'Đinh Tùng Huy', 'https://vancntt99.000webhostapp.com/filemp3/Cu-Ngo-La-Anh-Dinh-Tung-Huy.mp3', 0),
(27, '0', '0', '0', '1', 10, 'Đừng Lo Anh Đợi Mà', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dungloanhdoima.jpg', 'Mr Siro', 'https://vancntt99.000webhostapp.com/filemp3/Dung-Lo-Anh-Doi-Ma-Mr-Siro.mp3', 0),
(28, '0', '0', '0', '2', 1, 'How You Like That', 'https://megtroncntt99.000webhostapp.com/image/imgSong/howyoulikethat.jpg', 'BLACKPINK', 'https://vancntt99.000webhostapp.com/filemp3/HowYouLikeThat-BlackPink-6309489.mp3', 0),
(29, '0', '0', '0', '2', 2, 'Eight', 'https://megtroncntt99.000webhostapp.com/image/imgSong/eight.jpg', 'IU, SUGA', 'https://vancntt99.000webhostapp.com/filemp3/eight-IUSugaBTS-6271822.mp3', 0),
(30, '0', '0', '0', '2', 3, 'Kill This Love', 'https://megtroncntt99.000webhostapp.com/image/imgSong/killthislove.jpg', 'BLACKPINK', 'https://vancntt99.000webhostapp.com/filemp3/Kill-This-Love-BLACKPINK.mp3', 0),
(31, '0', '0', '0', '2', 7, 'Psycho', 'https://megtroncntt99.000webhostapp.com/image/imgSong/psycho.jpg', 'Red Velvet', 'https://vancntt99.000webhostapp.com/filemp3/Psycho-Red-Velvet.mp3', 0),
(32, '0', '0', '0', '2', 8, 'DDU-DU-DDU', 'https://megtroncntt99.000webhostapp.com/image/imgSong/ddu_du_du.jpg', 'BLACKPINK', 'https://vancntt99.000webhostapp.com/filemp3/DDU-DU-DDU.mp3', 0),
(33, '0', '0', '0', '2', 10, 'Beginning', 'https://megtroncntt99.000webhostapp.com/image/imgSong/beginning.jpg', 'GAHO', 'https://vancntt99.000webhostapp.com/filemp3/Beginning-Gaho.mp3', 0),
(34, '0', '0', '0', '2', 5, 'SOLO', 'https://megtroncntt99.000webhostapp.com/image/imgSong/solo.jpg', 'JENNIE', 'https://vancntt99.000webhostapp.com/filemp3/SOLO-JENNIE.mp3', 0),
(35, '0', '0', '0', '2', 9, 'DUN DUN', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dundun.jpg', 'EVERGLOW', 'https://vancntt99.000webhostapp.com/filemp3/DUN-DUN-EVERGLOW.mp3', 0),
(36, '0', '0', '0', '3', 2, 'I Don\'t Care', 'https://megtroncntt99.000webhostapp.com/image/imgSong/idon\'tcare.jpg', 'Ed Sheeran, Justin Bieber', 'https://vancntt99.000webhostapp.com/filemp3/I-Don-t-Care-Ed-Sheeran-Justin-Bieber.mp3', 0),
(37, '0', '0', '0', '3', 4, 'FRIENDS', 'https://megtroncntt99.000webhostapp.com/image/imgSong/friends.jpg', 'Marshmello, Anne', 'https://vancntt99.000webhostapp.com/filemp3/FRIENDS-Marshmello-Anne.mp3', 0),
(38, '0', '0', '0', '3', 5, 'Wrap Me In Plastic', 'https://megtroncntt99.000webhostapp.com/image/imgSong/wrapmeinplastin.jpg', 'CHROMANCE', 'https://vancntt99.000webhostapp.com/filemp3/Wrap-Me-In-Plastic-CHROMANCE.mp3', 0),
(39, '0', '0', '0', '3', 7, 'The River', 'https://megtroncntt99.000webhostapp.com/image/imgSong/theriver.jpg', 'Axel Johansson', 'https://vancntt99.000webhostapp.com/filemp3/The-River-Axel-Johansson.mp3', 0),
(40, '0', '0', '0', '3', 8, 'How Long', 'https://megtroncntt99.000webhostapp.com/image/imgSong/howlong.jpg', 'Charlie Puth', 'https://vancntt99.000webhostapp.com/filemp3/How-Long-Charlie-Puth.mp3', 0),
(41, '', '0', '0', '3', 9, 'Attention', 'https://megtroncntt99.000webhostapp.com/image/imgSong/attention.jpg', 'J.Fla', 'https://vancntt99.000webhostapp.com/filemp3/Attention-J-Fla.mp3', 0),
(42, '0', '0', '0', '3', 10, 'Dusk Till Dawn', 'https://megtroncntt99.000webhostapp.com/image/imgSong/dusktilldawn.jpg', 'ZAYN, Sia', 'https://vancntt99.000webhostapp.com/filemp3/Dusk-Till-Dawn-ZAYN-Sia.mp3', 0);

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
-- Indexes for table `mv_music`
--
ALTER TABLE `mv_music`
  ADD PRIMARY KEY (`idMV`);

--
-- Indexes for table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`idPlaylist`);

--
-- Indexes for table `ranksong`
--
ALTER TABLE `ranksong`
  ADD PRIMARY KEY (`IdRankSong`);

--
-- Indexes for table `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`idSong`);

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
  MODIFY `idAlbum` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `idCategory` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
  MODIFY `idSong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `theme`
--
ALTER TABLE `theme`
  MODIFY `idTheme` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
