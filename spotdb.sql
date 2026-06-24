/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - spot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `spot`;

/*Table structure for table `app_allocation` */

DROP TABLE IF EXISTS `app_allocation`;

CREATE TABLE `app_allocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUS_id` bigint(20) NOT NULL,
  `DRIVER_id` bigint(20) NOT NULL,
  `OWER_id` bigint(20) NOT NULL,
  `ROUTE_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_allocation_BUS_id_4f77ca51` (`BUS_id`),
  KEY `app_allocation_DRIVER_id_b8c82c84` (`DRIVER_id`),
  KEY `app_allocation_OWER_id_20400d1b` (`OWER_id`),
  KEY `app_allocation_ROUTE_id_25fa43cd` (`ROUTE_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `app_allocation` */

insert  into `app_allocation`(`id`,`BUS_id`,`DRIVER_id`,`OWER_id`,`ROUTE_id`) values 
(7,1,3,3,8),
(8,3,3,3,7);

/*Table structure for table `app_bus` */

DROP TABLE IF EXISTS `app_bus`;

CREATE TABLE `app_bus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bus_number` varchar(200) NOT NULL,
  `bus_name` varchar(200) NOT NULL,
  `OWER_id` bigint(20) NOT NULL,
  `status` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_bus_OWER_id_e1e6842c` (`OWER_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `app_bus` */

insert  into `app_bus`(`id`,`bus_number`,`bus_name`,`OWER_id`,`status`) values 
(1,'003','b1',3,'Active'),
(3,'002','b2',3,'blocked');

/*Table structure for table `app_checkin` */

DROP TABLE IF EXISTS `app_checkin`;

CREATE TABLE `app_checkin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` time(6) NOT NULL,
  `BUS_id` bigint(20) NOT NULL,
  `Checkin_stop_id` bigint(20) NOT NULL,
  `Checkout_stop_id` bigint(20) NOT NULL,
  `PASSENGER_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_checkin_BUS_id_9c99e579` (`BUS_id`),
  KEY `app_checkin_Checkin_stop_id_039b83ce` (`Checkin_stop_id`),
  KEY `app_checkin_Checkout_stop_id_f4f98c19` (`Checkout_stop_id`),
  KEY `app_checkin_PASSENGER_id_f7c6004b` (`PASSENGER_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `app_checkin` */

/*Table structure for table `app_complaints` */

DROP TABLE IF EXISTS `app_complaints`;

CREATE TABLE `app_complaints` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `complaint` varchar(200) NOT NULL,
  `reply` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL,
  `PASSENGER_id` bigint(20) NOT NULL,
  `BUS_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_complaints_PASSENGER_id_b2cb01fd` (`PASSENGER_id`),
  KEY `app_complaints_BUS_id_f7840ff2` (`BUS_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `app_complaints` */

insert  into `app_complaints`(`id`,`complaint`,`reply`,`date`,`PASSENGER_id`,`BUS_id`) values 
(1,'heyy','pending','2024-07-16',4,3),
(2,'b11','pending','2024-07-16',4,1),
(3,'uu','pending','2024-07-16',4,1);

/*Table structure for table `app_driver` */

DROP TABLE IF EXISTS `app_driver`;

CREATE TABLE `app_driver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGIN_id` bigint(20) NOT NULL,
  `driver_name` varchar(200) NOT NULL,
  `contact_number` varchar(200) NOT NULL,
  `place` varchar(200) NOT NULL,
  `post` varchar(200) NOT NULL,
  `pincode` varchar(200) NOT NULL,
  `city` varchar(200) NOT NULL,
  `district` varchar(200) NOT NULL,
  `photo` varchar(100) NOT NULL,
  `licenses` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_driver_LOGIN_id_ec2ba39d` (`LOGIN_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `app_driver` */

insert  into `app_driver`(`id`,`LOGIN_id`,`driver_name`,`contact_number`,`place`,`post`,`pincode`,`city`,`district`,`photo`,`licenses`) values 
(3,5,'renji','7305778898','kochi','abc place, xyz street','767662','Manjeri','ernklm','enlarge_renjik_8Hgow23.jpeg','Online_Marriage_Registration_System_1.pdf'),
(5,13,'elsa','9306000957','kochi','abc place, xyz street','767662','Kochi','ernklm','callmeshzham_vvQhJJK.png','Black_Purple_Electro_Party_DJ_Poster_KluGJaD_w4nSLCt.pdf'),
(7,15,'das','7306033950','ERFWq','kochi,nagar','767662','Manjeri','ewdew','satoru-gojo-jujutsu-3840x2743-9292_1_KjqXwet.jpg','Directory_Management_System_.pdf');

/*Table structure for table `app_feedback` */

DROP TABLE IF EXISTS `app_feedback`;

CREATE TABLE `app_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `complaint` varchar(200) NOT NULL,
  `reply` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL,
  `PASSENGER_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_feedback_PASSENGER_id_9aa5df1f` (`PASSENGER_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `app_feedback` */

insert  into `app_feedback`(`id`,`complaint`,`reply`,`date`,`PASSENGER_id`) values 
(1,'heyy','pending','2024-07-16',4),
(2,'hello','pending','2024-07-16',4),
(3,'h','pending','2024-07-16',4),
(4,'huu','pending','2024-08-03',4),
(5,'hyyyyyyyyyyyy','pending','2024-08-03',4);

/*Table structure for table `app_login` */

DROP TABLE IF EXISTS `app_login`;

CREATE TABLE `app_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `usertype` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `app_login` */

insert  into `app_login`(`id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(19,'a','123','passenger'),
(16,'uu1','123','pending'),
(15,'d6','123','driver'),
(5,'rr','123','driver'),
(14,'d5','123','driver'),
(13,'d4','123','driver'),
(12,'dd','123','driver'),
(11,'uu','123','owner'),
(22,'h','123','passenger');

/*Table structure for table `app_owner` */

DROP TABLE IF EXISTS `app_owner`;

CREATE TABLE `app_owner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGIN_id` bigint(20) NOT NULL,
  `ower_name` varchar(200) NOT NULL,
  `contact_number` varchar(200) NOT NULL,
  `place` varchar(200) NOT NULL,
  `post` varchar(200) NOT NULL,
  `pincode` varchar(200) NOT NULL,
  `district` varchar(200) NOT NULL,
  `photo` varchar(100) NOT NULL,
  `id_proof` varchar(100) NOT NULL,
  `city` varchar(200) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_ower_LOGIN_id_52743392` (`LOGIN_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `app_owner` */

insert  into `app_owner`(`id`,`LOGIN_id`,`ower_name`,`contact_number`,`place`,`post`,`pincode`,`district`,`photo`,`id_proof`,`city`,`status`) values 
(4,16,'dd','7306033950','kochi','abc place, xyz street','767662','ernklm','callmeshzham_Sbqprtj.png','Black_Purple_Electro_Party_DJ_Poster_KluGJaD_HYEgv9k.pdf','Manjeri','rejected'),
(3,11,'joji','7306033950','kochi','abc place, xyz street','767662','ernklm','satoru-gojo-jujutsu-3840x2743-9292_1_7Gmrje2.jpg','Black_Purple_Electro_Party_DJ_Poster_KluGJaD.pdf','Manjeri','Approved');

/*Table structure for table `app_passenger` */

DROP TABLE IF EXISTS `app_passenger`;

CREATE TABLE `app_passenger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pname` varchar(200) NOT NULL,
  `place` varchar(200) NOT NULL,
  `post` varchar(200) NOT NULL,
  `pincode` varchar(200) NOT NULL,
  `city` varchar(200) NOT NULL,
  `district` varchar(200) NOT NULL,
  `photo` varchar(100) NOT NULL,
  `LOGIN_id` bigint(20) NOT NULL,
  `phone` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_passenger_LOGIN_id_bab16c32` (`LOGIN_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `app_passenger` */

insert  into `app_passenger`(`id`,`pname`,`place`,`post`,`pincode`,`city`,`district`,`photo`,`LOGIN_id`,`phone`) values 
(1,'rr','jj','kakak','123456','ajja','akka','1720678527984_ac07h5n.png',19,'1234567891'),
(4,'kk','fhf','ydh','232323','hfud','yddhd','1720678838234_bzABEva.png',22,'676767676767');

/*Table structure for table `app_rating` */

DROP TABLE IF EXISTS `app_rating`;

CREATE TABLE `app_rating` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rating` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL,
  `PASSENGER_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_rating_PASSENGER_id_2fb3ee3d` (`PASSENGER_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `app_rating` */

/*Table structure for table `app_route` */

DROP TABLE IF EXISTS `app_route`;

CREATE TABLE `app_route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `routes_start` varchar(200) NOT NULL,
  `routes_End` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `app_route` */

insert  into `app_route`(`id`,`routes_start`,`routes_End`) values 
(7,'South','Kaloor'),
(8,'North','JLN');

/*Table structure for table `app_stops` */

DROP TABLE IF EXISTS `app_stops`;

CREATE TABLE `app_stops` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `point_name` varchar(200) NOT NULL,
  `stop_no` varchar(200) NOT NULL,
  `ROUTE_id` bigint(20) NOT NULL,
  `charge` varchar(200) NOT NULL,
  `lattitude` varchar(200) NOT NULL,
  `longitude` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_stops_ROUTE_id_56b16cc7` (`ROUTE_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `app_stops` */

insert  into `app_stops`(`id`,`point_name`,`stop_no`,`ROUTE_id`,`charge`,`lattitude`,`longitude`) values 
(1,'north town hall','01',8,'15','9.990834202763686','76.28739684820175'),
(2,'mg road','01',7,'15','9.990834202763686','76.28739684820175');

/*Table structure for table `app_trip` */

DROP TABLE IF EXISTS `app_trip`;

CREATE TABLE `app_trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` varchar(200) NOT NULL,
  `End_time` varchar(200) NOT NULL,
  `ALLOCATION_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_trip_ALLOCATION_id_a21da20d` (`ALLOCATION_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `app_trip` */

insert  into `app_trip`(`id`,`start_time`,`End_time`,`ALLOCATION_id`) values 
(10,'00:09','14:09',7),
(7,'10:52','00:52',8);

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_group_id_b120cbf9` (`group_id`),
  KEY `auth_group_permissions_permission_id_84c5c92e` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  KEY `auth_permission_content_type_id_2f476e4b` (`content_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=85 DEFAULT CHARSET=latin1;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add log entry',1,'add_logentry'),
(2,'Can change log entry',1,'change_logentry'),
(3,'Can delete log entry',1,'delete_logentry'),
(4,'Can view log entry',1,'view_logentry'),
(5,'Can add permission',2,'add_permission'),
(6,'Can change permission',2,'change_permission'),
(7,'Can delete permission',2,'delete_permission'),
(8,'Can view permission',2,'view_permission'),
(9,'Can add group',3,'add_group'),
(10,'Can change group',3,'change_group'),
(11,'Can delete group',3,'delete_group'),
(12,'Can view group',3,'view_group'),
(13,'Can add user',4,'add_user'),
(14,'Can change user',4,'change_user'),
(15,'Can delete user',4,'delete_user'),
(16,'Can view user',4,'view_user'),
(17,'Can add content type',5,'add_contenttype'),
(18,'Can change content type',5,'change_contenttype'),
(19,'Can delete content type',5,'delete_contenttype'),
(20,'Can view content type',5,'view_contenttype'),
(21,'Can add session',6,'add_session'),
(22,'Can change session',6,'change_session'),
(23,'Can delete session',6,'delete_session'),
(24,'Can view session',6,'view_session'),
(25,'Can add bus',7,'add_bus'),
(26,'Can change bus',7,'change_bus'),
(27,'Can delete bus',7,'delete_bus'),
(28,'Can view bus',7,'view_bus'),
(29,'Can add login',8,'add_login'),
(30,'Can change login',8,'change_login'),
(31,'Can delete login',8,'delete_login'),
(32,'Can view login',8,'view_login'),
(33,'Can add route',9,'add_route'),
(34,'Can change route',9,'change_route'),
(35,'Can delete route',9,'delete_route'),
(36,'Can view route',9,'view_route'),
(37,'Can add stops',10,'add_stops'),
(38,'Can change stops',10,'change_stops'),
(39,'Can delete stops',10,'delete_stops'),
(40,'Can view stops',10,'view_stops'),
(41,'Can add passenger',11,'add_passenger'),
(42,'Can change passenger',11,'change_passenger'),
(43,'Can delete passenger',11,'delete_passenger'),
(44,'Can view passenger',11,'view_passenger'),
(45,'Can add ower',12,'add_ower'),
(46,'Can change ower',12,'change_ower'),
(47,'Can delete ower',12,'delete_ower'),
(48,'Can view ower',12,'view_ower'),
(49,'Can add location',13,'add_location'),
(50,'Can change location',13,'change_location'),
(51,'Can delete location',13,'delete_location'),
(52,'Can view location',13,'view_location'),
(53,'Can add driver',14,'add_driver'),
(54,'Can change driver',14,'change_driver'),
(55,'Can delete driver',14,'delete_driver'),
(56,'Can view driver',14,'view_driver'),
(57,'Can add complaints',15,'add_complaints'),
(58,'Can change complaints',15,'change_complaints'),
(59,'Can delete complaints',15,'delete_complaints'),
(60,'Can view complaints',15,'view_complaints'),
(61,'Can add allocation',16,'add_allocation'),
(62,'Can change allocation',16,'change_allocation'),
(63,'Can delete allocation',16,'delete_allocation'),
(64,'Can view allocation',16,'view_allocation'),
(65,'Can add owner',12,'add_owner'),
(66,'Can change owner',12,'change_owner'),
(67,'Can delete owner',12,'delete_owner'),
(68,'Can view owner',12,'view_owner'),
(69,'Can add checkin',17,'add_checkin'),
(70,'Can change checkin',17,'change_checkin'),
(71,'Can delete checkin',17,'delete_checkin'),
(72,'Can view checkin',17,'view_checkin'),
(73,'Can add trip',18,'add_trip'),
(74,'Can change trip',18,'change_trip'),
(75,'Can delete trip',18,'delete_trip'),
(76,'Can view trip',18,'view_trip'),
(77,'Can add rating',19,'add_rating'),
(78,'Can change rating',19,'change_rating'),
(79,'Can delete rating',19,'delete_rating'),
(80,'Can view rating',19,'view_rating'),
(81,'Can add feedback',20,'add_feedback'),
(82,'Can change feedback',20,'change_feedback'),
(83,'Can delete feedback',20,'delete_feedback'),
(84,'Can view feedback',20,'view_feedback');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user` */

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_user_id_6a12ed8b` (`user_id`),
  KEY `auth_user_groups_group_id_97559544` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_user_id_a95ead1b` (`user_id`),
  KEY `auth_user_user_permissions_permission_id_1fbb5f2c` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(1,'admin','logentry'),
(2,'auth','permission'),
(3,'auth','group'),
(4,'auth','user'),
(5,'contenttypes','contenttype'),
(6,'sessions','session'),
(7,'app','bus'),
(8,'app','login'),
(9,'app','route'),
(10,'app','stops'),
(11,'app','passenger'),
(12,'app','owner'),
(13,'app','location'),
(14,'app','driver'),
(15,'app','complaints'),
(16,'app','allocation'),
(17,'app','checkin'),
(18,'app','trip'),
(19,'app','rating'),
(20,'app','feedback');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'contenttypes','0001_initial','2024-06-28 10:47:12.194164'),
(2,'auth','0001_initial','2024-06-28 10:47:12.423226'),
(3,'admin','0001_initial','2024-06-28 10:47:12.484377'),
(4,'admin','0002_logentry_remove_auto_add','2024-06-28 10:47:12.491431'),
(5,'admin','0003_logentry_add_action_flag_choices','2024-06-28 10:47:12.501016'),
(6,'app','0001_initial','2024-06-28 10:47:12.727941'),
(7,'contenttypes','0002_remove_content_type_name','2024-06-28 10:47:12.759323'),
(8,'auth','0002_alter_permission_name_max_length','2024-06-28 10:47:12.776860'),
(9,'auth','0003_alter_user_email_max_length','2024-06-28 10:47:12.798537'),
(10,'auth','0004_alter_user_username_opts','2024-06-28 10:47:12.811010'),
(11,'auth','0005_alter_user_last_login_null','2024-06-28 10:47:12.831750'),
(12,'auth','0006_require_contenttypes_0002','2024-06-28 10:47:12.837968'),
(13,'auth','0007_alter_validators_add_error_messages','2024-06-28 10:47:12.853071'),
(14,'auth','0008_alter_user_username_max_length','2024-06-28 10:47:12.872822'),
(15,'auth','0009_alter_user_last_name_max_length','2024-06-28 10:47:12.895847'),
(16,'auth','0010_alter_group_name_max_length','2024-06-28 10:47:12.914906'),
(17,'auth','0011_update_proxy_permissions','2024-06-28 10:47:12.929091'),
(18,'auth','0012_alter_user_first_name_max_length','2024-06-28 10:47:12.946102'),
(19,'sessions','0001_initial','2024-06-28 10:47:12.994686'),
(20,'app','0002_driver_city','2024-06-28 11:28:44.810830'),
(21,'app','0003_ower_city','2024-06-29 04:46:07.396761'),
(22,'app','0004_ower_status','2024-06-29 05:13:14.804723'),
(23,'app','0005_bus_ower','2024-06-29 11:21:41.474365'),
(24,'app','0006_auto_20240701_0918','2024-07-01 03:48:45.711293'),
(25,'app','0007_rename_busroute_bus_route','2024-07-01 09:33:37.298502'),
(26,'app','0008_bus_status','2024-07-01 09:57:00.256047'),
(27,'app','0009_auto_20240702_1146','2024-07-02 06:16:47.515029'),
(28,'app','0010_auto_20240702_1210','2024-07-02 06:40:32.259133'),
(29,'app','0011_auto_20240703_0915','2024-07-03 03:45:59.832209');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('r892bwp24ywwub9bccssmpe1wsoz4lqa','eyJsaWQiOjExLCJvaWQiOjN9:1sNSEe:V6LaNOLtFncG_0qjB8pMZJC1bFhorCOoNVW-IpA973k','2024-07-13 07:11:00.547921'),
('70hsdtyjmmt4jsrljwp3wf342o48u9g5','eyJsaWQiOjEsIm9pZCI6M30:1sRPEk:JZA_OTAqOLPmCDPxpUjpeJUyt0MB6R0sKGSTYF5S9BY','2024-07-24 04:47:26.159479'),
('2n534dbxaqsh4kxlc4mdk7u4airvglpe','eyJsaWQiOjF9:1sZlkQ:JOVeroMVa0-tXqt4eBQNAk290FBlEgYCFpFNUV7Q0C0','2024-08-16 06:26:42.100632');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
