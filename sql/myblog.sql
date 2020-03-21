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
  `article_label` varchar(10) NOT NULL COMMENT '文章标签字符串,比如 1,2,3',
  `read_count` int(10) NOT NULL DEFAULT '0' COMMENT '阅读数',
  `comment_count` int(10) NOT NULL DEFAULT '0' COMMENT '评论数',
  `publish_date` varchar(20) NOT NULL COMMENT '发布日期',
  `state` varchar(1) NOT NULL DEFAULT '1' COMMENT '1-未删除 0-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article`
--

LOCK TABLES `t_article` WRITE;
/*!40000 ALTER TABLE `t_article` DISABLE KEYS */;
INSERT INTO `t_article` VALUES (3,10,'test222','<p>我是内容啊啊O(∩_∩)O哈哈~</p>','1,2',9,0,'2020-03-16 16:37','1'),(4,10,'test583839339','<p>反反复复付方法法国吧<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png\" alt=\"[舔屏]\" data-w-e=\"1\"><img src=\"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif\" alt=\"[草泥马]\" data-w-e=\"1\"></p>','9,15',7,0,'2020-03-16 16:40','1'),(6,10,'4444444444444444444444444444444444','<h1><span style=\"background-color: rgb(249, 150, 59);\">本田</span></h1>','3',10,0,'2020-03-16 16:46','1'),(7,10,'java好雪吗  我在修改一个','<p><img src=\"http://123.56.23.25:1111/images/1584441474052-111.jpg\" style=\"max-width:100%;\"><br></p><p>图片好大 啊&nbsp;<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\" data-w-e=\"1\"></p>','2,4,5',18,0,'2020-03-17 22:13','1'),(9,10,'再来一条','<table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th><span style=\"font-style: italic; text-decoration-line: underline line-through;\">&nbsp;1</span></th><th><span style=\"background-color: rgb(0, 0, 0);\">1&nbsp;</span></th><th><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\"><br></th><th>1&nbsp;</th><th>1&nbsp;</th></tr><tr><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td></tr><tr><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td></tr><tr><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td></tr><tr><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;1</td><td>&nbsp;</td></tr></tbody></table><p><br></p>','13,21',3,0,'2020-03-17 18:42','0'),(10,10,'哈哈哈哈5555','<blockquote><span style=\"font-weight: bold; background-color: rgb(194, 79, 74); font-family: 宋体; font-size: xx-large;\">哈哈哈哈5555</span></blockquote>','5',2,0,'2020-03-17 22:02','0'),(11,9,'我的账号是shisiping','<p>测试一下啦啦啦</p>','10',100,0,'2020-03-20 16:57','1'),(12,9,'3333','<p>33333</p>','5',0,0,'2020-03-20 16:59','1');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article_collection`
--

LOCK TABLES `t_article_collection` WRITE;
/*!40000 ALTER TABLE `t_article_collection` DISABLE KEYS */;
INSERT INTO `t_article_collection` VALUES (4,10,7),(5,10,4),(6,9,7);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article_label`
--

LOCK TABLES `t_article_label` WRITE;
/*!40000 ALTER TABLE `t_article_label` DISABLE KEYS */;
INSERT INTO `t_article_label` VALUES (1,' javascript',1),(2,' php',1),(3,' css',1),(4,' html',1),(5,' html5',1),(6,' java',1),(7,' node.js',1),(8,' python',1),(9,' c++',1),(10,' c',1),(11,' golang',1),(12,' objective-c',1),(13,' typescript',1),(14,' shell',1),(15,' swift',1),(16,' c#',1),(17,' sass',1),(18,' ruby',1),(19,' bash',1),(20,' less',1),(21,' asp.net',1),(22,' lua',1),(23,' scala',1),(24,' coffeescript',1),(25,' actionscript',1),(26,' rust',1),(27,' erlang',1),(28,' perl',1),(29,' laravel',2),(30,' spring',2),(31,' express',2),(32,' django',2),(33,' flask',2),(34,' yii',2),(35,' ruby-on-rails',2),(36,' tornado',2),(37,' koa',2),(38,' struts',2),(39,' linux',3),(40,' nginx',3),(41,' docker',3),(42,' apache',3),(43,' ubuntu',3),(44,' centos',3),(45,' 缓存 tomcat',3),(46,' 负载均衡',3),(47,' unix',3),(48,' hadoop',3),(49,' windows-server',3),(50,' mysql',4),(51,' redis',4),(52,' mongodb',4),(53,' sql',4),(54,' oracle',4),(55,' nosql memcached',4),(56,' sqlserver',4),(57,' postgresql',4),(58,' sqlite',4),(59,' git',5),(60,' github',5),(61,' visual-studio-code',5),(62,' vim',5),(63,' sublime-text',5),(64,' xcode intellij-idea',5),(65,' eclipse',5),(66,' maven',5),(67,' ide',5),(68,' svn',5),(69,' visual-studio',5),(70,' atom emacs',5),(71,' textmate',5),(72,' hg',5),(73,' 广告',6),(74,' 求职',6),(75,' 招聘',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment_1`
--

LOCK TABLES `t_comment_1` WRITE;
/*!40000 ALTER TABLE `t_comment_1` DISABLE KEYS */;
INSERT INTO `t_comment_1` VALUES (1,10,11,'滴滴打车 才才 才v vv ','2020-03-20 18:21:58',2),(2,10,11,'风非常非常飞飞飞iFile','2020-03-20 18:59:57',3),(3,9,11,'哈哈哈','2020-03-21 14:42:18',2),(4,9,11,'我在来个','2020-03-21 15:56:14',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment_2`
--

LOCK TABLES `t_comment_2` WRITE;
/*!40000 ALTER TABLE `t_comment_2` DISABLE KEYS */;
INSERT INTO `t_comment_2` VALUES (1,9,10,1,'滴滴车吗','2020-03-21 14:58:16',1),(2,10,9,4,'恩额嗯 你哈样的','2020-03-21 16:04:01',2),(3,10,9,4,'fff从VBv','2020-03-21 22:45:47',0),(8,10,0,4,'急急急','2020-03-21 23:04:03',0),(9,10,9,1,'不不不','2020-03-21 23:04:43',1);
/*!40000 ALTER TABLE `t_comment_2` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (7,'test','123456','test1@qq.com',''),(9,'shisiping','123456','2672575663@qq.com','http://123.56.23.25:1111/images/1584447711225-Koala.jpg'),(10,'顽石mua','ssp1999','2942504702@qq.com','http://123.56.23.25:1111/images/1584257348960-198cc91bf8ad8b26.jpg');
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

-- Dump completed on 2020-03-21 23:06:50
