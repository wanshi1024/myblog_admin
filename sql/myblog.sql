-- MySQL dump 10.13  Distrib 5.5.36, for Win64 (x86)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	5.5.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_article`
--

DROP TABLE IF EXISTS `t_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `article_title` varchar(255) NOT NULL,
  `article_content` varchar(10000) NOT NULL,
  `article_label` varchar(100) NOT NULL COMMENT '文章标签字符串,比如 1,2,3',
  `read_count` int(10) NOT NULL DEFAULT '0' COMMENT '阅读数',
  `comment_count` int(10) NOT NULL DEFAULT '0' COMMENT '评论数',
  `publish_date` varchar(20) NOT NULL COMMENT '发布日期',
  `state` varchar(1) NOT NULL DEFAULT '1' COMMENT '1-未删除 0-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article`
--

LOCK TABLES `t_article` WRITE;
/*!40000 ALTER TABLE `t_article` DISABLE KEYS */;
INSERT INTO `t_article` VALUES (24,11,'测试1','<p>测试1</p>',' javascript',1,0,'2020-03-31 17:40','1');
/*!40000 ALTER TABLE `t_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_article_collection`
--

DROP TABLE IF EXISTS `t_article_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_article_collection` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) NOT NULL,
  `articleId` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article_collection`
--

LOCK TABLES `t_article_collection` WRITE;
/*!40000 ALTER TABLE `t_article_collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_article_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_article_label`
--

DROP TABLE IF EXISTS `t_article_label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_article_label` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(50) NOT NULL,
  `label_classify` int(2) NOT NULL COMMENT '1-开发语言 2-平台框架 3-服务器 4-数据库 5开发工具 6-其他',
  `use_count` int(10) NOT NULL DEFAULT '0' COMMENT '标签使用次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article_label`
--

LOCK TABLES `t_article_label` WRITE;
/*!40000 ALTER TABLE `t_article_label` DISABLE KEYS */;
INSERT INTO `t_article_label` VALUES (1,' javascript',1,4),(2,' php',1,4),(3,' css',1,2),(4,' html',1,1),(5,' html5',1,1),(6,' java',1,7),(7,' node.js',1,2),(8,' python',1,2),(9,' c++',1,0),(10,' c',1,0),(11,' golang',1,0),(12,' objective-c',1,0),(13,' typescript',1,0),(14,' shell',1,0),(15,' swift',1,0),(16,' c#',1,0),(17,' sass',1,0),(18,' ruby',1,0),(19,' bash',1,0),(20,' less',1,0),(21,' asp.net',1,0),(22,' lua',1,0),(23,' scala',1,0),(24,' coffeescript',1,0),(25,' actionscript',1,0),(26,' rust',1,0),(27,' erlang',1,0),(28,' perl',1,0),(29,' laravel',2,0),(30,' spring',2,0),(31,' express',2,0),(32,' django',2,0),(33,' flask',2,0),(34,' yii',2,0),(35,' ruby-on-rails',2,0),(36,' tornado',2,0),(37,' koa',2,0),(38,' struts',2,0),(39,' linux',3,0),(40,' nginx',3,0),(41,' docker',3,0),(42,' apache',3,0),(43,' ubuntu',3,0),(44,' centos',3,0),(45,' 缓存 tomcat',3,0),(46,' 负载均衡',3,0),(47,' unix',3,0),(48,' hadoop',3,0),(49,' windows-server',3,0),(50,' mysql',4,0),(51,' redis',4,0),(52,' mongodb',4,0),(53,' sql',4,0),(54,' oracle',4,0),(55,' nosql memcached',4,0),(56,' sqlserver',4,0),(57,' postgresql',4,0),(58,' sqlite',4,0),(59,' git',5,0),(60,' github',5,0),(61,' visual-studio-code',5,0),(62,' vim',5,0),(63,' sublime-text',5,0),(64,' xcode intellij-idea',5,0),(65,' eclipse',5,0),(66,' maven',5,0),(67,' ide',5,0),(68,' svn',5,0),(69,' visual-studio',5,0),(70,' atom emacs',5,0),(71,' textmate',5,0),(72,' hg',5,0),(73,' 广告',6,0),(74,' 求职',6,0),(75,' 招聘',6,0);
/*!40000 ALTER TABLE `t_article_label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment_1`
--

DROP TABLE IF EXISTS `t_comment_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment_1` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) NOT NULL,
  `articleId` int(10) NOT NULL,
  `commentContent` varchar(255) NOT NULL COMMENT '评论内容',
  `commentDate` varchar(255) NOT NULL COMMENT '评论日期',
  `complimentCount` int(10) NOT NULL DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment_1`
--

LOCK TABLES `t_comment_1` WRITE;
/*!40000 ALTER TABLE `t_comment_1` DISABLE KEYS */;
INSERT INTO `t_comment_1` VALUES (9,11,24,'哈哈哈哈','2020-03-31 17:40:46',0);
/*!40000 ALTER TABLE `t_comment_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment_2`
--

DROP TABLE IF EXISTS `t_comment_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment_2` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(10) NOT NULL COMMENT '用户的id',
  `toUserId` int(10) NOT NULL COMMENT '被评论的用户id',
  `parentCommentId` int(10) NOT NULL COMMENT '父级评论id',
  `commentContent` varchar(255) NOT NULL COMMENT '评论内容',
  `commentDate` varchar(255) NOT NULL COMMENT '评论时间',
  `complimentCount` int(10) NOT NULL DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment_2`
--

LOCK TABLES `t_comment_2` WRITE;
/*!40000 ALTER TABLE `t_comment_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment_example`
--

DROP TABLE IF EXISTS `t_comment_example`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment_example` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fromCommentUserName` varchar(255) NOT NULL,
  `toUserId` int(10) NOT NULL,
  `articleId` int(10) NOT NULL,
  `articleTitle` varchar(255) NOT NULL,
  `commentContent` varchar(255) NOT NULL,
  `commentDate` varchar(30) NOT NULL,
  `readState` int(1) NOT NULL DEFAULT '0' COMMENT '0-未读 1-已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment_example`
--

LOCK TABLES `t_comment_example` WRITE;
/*!40000 ALTER TABLE `t_comment_example` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment_example` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(30) NOT NULL,
  `avatar` varchar(500) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (11,'wanshi','123456','2672575663@qq.com','http://123.56.23.25:1111/images/1585647643173-111.jpg');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-31 17:44:26
