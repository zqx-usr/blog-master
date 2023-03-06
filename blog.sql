/*
Navicat MySQL Data Transfer

Source Server         : Aliyun
Source Server Version : 50732
Source Host           : rm-bp1645nmmp7uxf5uoso.mysql.rds.aliyuncs.com:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50732
File Encoding         : 65001

Date: 2023-01-08 13:56:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `log_type` varchar(50) NOT NULL COMMENT '日志类型',
  `create_user_code` varchar(64) NOT NULL COMMENT '创建用户编码',
  `create_user_name` varchar(100) NOT NULL COMMENT '创建用户名称',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `request_uri` varchar(500) DEFAULT NULL COMMENT '请求URI',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方式',
  `request_params` text COMMENT '请求参数',
  `request_ip` varchar(20) NOT NULL COMMENT '请求IP',
  `server_address` varchar(50) NOT NULL COMMENT '请求服务器地址',
  `is_exception` varchar(5) DEFAULT NULL COMMENT '是否异常',
  `exception_info` text COMMENT '异常信息',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `execute_time` int(11) DEFAULT NULL COMMENT '执行时间',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `device_name` varchar(100) DEFAULT NULL COMMENT '操作系统',
  `browser_name` varchar(100) DEFAULT NULL COMMENT '浏览器名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_log_lt` (`log_type`) USING BTREE,
  KEY `idx_sys_log_cub` (`create_user_code`) USING BTREE,
  KEY `idx_sys_log_ie` (`is_exception`) USING BTREE,
  KEY `idx_sys_log_cd` (`create_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '作者',
  `category_id` int(11) DEFAULT NULL COMMENT '文章分类',
  `article_cover` varchar(1024) DEFAULT NULL COMMENT '文章缩略图',
  `article_title` varchar(50) NOT NULL COMMENT '标题',
  `article_content` longtext NOT NULL COMMENT '内容',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '文章类型 1原创 2转载 3翻译',
  `original_url` varchar(255) DEFAULT NULL COMMENT '原文链接',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶 0否 1是',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除  0否 1是',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态值 1公开 2私密 3评论可见',
  `create_time` datetime NOT NULL COMMENT '发表时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_article
-- ----------------------------

-- ----------------------------
-- Table structure for tb_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_tag`;
CREATE TABLE `tb_article_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_article_tag_1` (`article_id`) USING BTREE,
  KEY `fk_article_tag_2` (`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) NOT NULL COMMENT '分类名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('1', '技术类', '2023-01-08 13:45:20', null);
INSERT INTO `tb_category` VALUES ('2', '生活类', '2023-01-08 13:45:54', null);
INSERT INTO `tb_category` VALUES ('3', '综合类', '2023-01-08 13:46:16', null);

-- ----------------------------
-- Table structure for tb_chat_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_chat_record`;
CREATE TABLE `tb_chat_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `avatar` varchar(255) NOT NULL COMMENT '头像',
  `content` varchar(1000) NOT NULL COMMENT '聊天内容',
  `ip_address` varchar(50) NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(255) NOT NULL COMMENT 'ip来源',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_chat_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '评论用户Id',
  `topic_id` int(11) DEFAULT NULL COMMENT '评论主题id',
  `comment_content` text NOT NULL COMMENT '评论内容',
  `reply_user_id` int(11) DEFAULT NULL COMMENT '回复用户id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父评论id',
  `type` tinyint(4) NOT NULL COMMENT '评论类型 1.文章 2.友链 3.说说',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除  0否 1是',
  `is_review` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否审核',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_comment_user` (`user_id`) USING BTREE,
  KEY `fk_comment_parent` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_friend_link`;
CREATE TABLE `tb_friend_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link_name` varchar(20) NOT NULL COMMENT '链接名',
  `link_avatar` varchar(255) NOT NULL COMMENT '链接头像',
  `link_address` varchar(50) NOT NULL COMMENT '链接地址',
  `link_intro` varchar(100) NOT NULL COMMENT '链接介绍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_friend_link_user` (`link_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_friend_link
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '菜单名',
  `path` varchar(50) NOT NULL COMMENT '菜单路径',
  `component` varchar(50) NOT NULL COMMENT '组件',
  `icon` varchar(50) NOT NULL COMMENT '菜单icon',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `order_num` tinyint(1) NOT NULL COMMENT '排序',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  `is_hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏  0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '首页', '/', '/home/Home.vue', 'el-icon-myshouye', '2021-01-26 17:06:51', '2021-01-26 17:06:53', '1', null, '0');
INSERT INTO `tb_menu` VALUES ('2', '文章管理', '/article-submenu', 'Layout', 'el-icon-mywenzhang-copy', '2021-01-25 20:43:07', '2021-01-25 20:43:09', '2', null, '0');
INSERT INTO `tb_menu` VALUES ('3', '消息管理', '/message-submenu', 'Layout', 'el-icon-myxiaoxi', '2021-01-25 20:44:17', '2021-01-25 20:44:20', '3', null, '0');
INSERT INTO `tb_menu` VALUES ('4', '系统管理', '/system-submenu', 'Layout', 'el-icon-myshezhi', '2021-01-25 20:45:57', '2021-01-25 20:45:59', '5', null, '0');
INSERT INTO `tb_menu` VALUES ('5', '个人中心', '/setting', '/setting/Setting.vue', 'el-icon-myuser', '2021-01-26 17:22:38', '2021-01-26 17:22:41', '7', null, '0');
INSERT INTO `tb_menu` VALUES ('6', '发布文章', '/articles', '/article/Article.vue', 'el-icon-myfabiaowenzhang', '2021-01-26 14:30:48', '2021-01-26 14:30:51', '1', '2', '0');
INSERT INTO `tb_menu` VALUES ('7', '修改文章', '/articles/*', '/article/Article.vue', 'el-icon-myfabiaowenzhang', '2021-01-26 14:31:32', '2023-01-03 10:19:24', '2', '2', '1');
INSERT INTO `tb_menu` VALUES ('8', '文章列表', '/article-list', '/article/ArticleList.vue', 'el-icon-mywenzhangliebiao', '2021-01-26 14:32:13', '2021-01-26 14:32:16', '3', '2', '0');
INSERT INTO `tb_menu` VALUES ('9', '分类管理', '/categories', '/category/Category.vue', 'el-icon-myfenlei', '2021-01-26 14:33:42', '2021-01-26 14:33:43', '4', '2', '0');
INSERT INTO `tb_menu` VALUES ('10', '标签管理', '/tags', '/tag/Tag.vue', 'el-icon-myicontag', '2021-01-26 14:34:33', '2021-01-26 14:34:36', '5', '2', '0');
INSERT INTO `tb_menu` VALUES ('11', '评论管理', '/comments', '/comment/Comment.vue', 'el-icon-mypinglunzu', '2021-01-26 14:35:31', '2021-01-26 14:35:34', '1', '3', '0');
INSERT INTO `tb_menu` VALUES ('12', '留言管理', '/messages', '/message/Message.vue', 'el-icon-myliuyan', '2021-01-26 14:36:09', '2021-01-26 14:36:13', '2', '3', '0');
INSERT INTO `tb_menu` VALUES ('13', '用户列表', '/users', '/user/User.vue', 'el-icon-myyonghuliebiao', '2021-01-26 14:38:09', '2021-01-26 14:38:12', '1', '202', '0');
INSERT INTO `tb_menu` VALUES ('14', '角色管理', '/roles', '/role/Role.vue', 'el-icon-myjiaoseliebiao', '2021-01-26 14:39:01', '2021-01-26 14:39:03', '2', '213', '0');
INSERT INTO `tb_menu` VALUES ('15', '接口管理', '/resources', '/resource/Resource.vue', 'el-icon-myjiekouguanli', '2021-01-26 14:40:14', '2021-08-07 20:00:28', '2', '213', '0');
INSERT INTO `tb_menu` VALUES ('16', '菜单管理', '/menus', '/menu/Menu.vue', 'el-icon-mycaidan', '2021-01-26 14:40:54', '2021-08-07 10:18:49', '2', '213', '0');
INSERT INTO `tb_menu` VALUES ('17', '友链管理', '/links', '/friendLink/FriendLink.vue', 'el-icon-mydashujukeshihuaico-', '2021-01-26 14:41:35', '2021-01-26 14:41:37', '3', '4', '0');
INSERT INTO `tb_menu` VALUES ('18', '关于我', '/about', '/about/About.vue', 'el-icon-myguanyuwo', '2021-01-26 14:42:05', '2021-01-26 14:42:10', '4', '4', '0');
INSERT INTO `tb_menu` VALUES ('19', '日志管理', '/log-submenu', 'Layout', 'el-icon-myguanyuwo', '2021-01-31 21:33:56', '2021-01-31 21:33:59', '6', null, '0');
INSERT INTO `tb_menu` VALUES ('20', '操作日志', '/operation/log', '/log/Operation.vue', 'el-icon-myguanyuwo', '2021-01-31 15:53:21', '2021-01-31 15:53:25', '1', '19', '0');
INSERT INTO `tb_menu` VALUES ('201', '在线用户', '/online/users', '/user/Online.vue', 'el-icon-myyonghuliebiao', '2021-02-05 14:59:51', '2021-02-05 14:59:53', '7', '202', '0');
INSERT INTO `tb_menu` VALUES ('202', '用户管理', '/users-submenu', 'Layout', 'el-icon-myyonghuliebiao', '2021-02-06 23:44:59', '2021-02-06 23:45:03', '4', null, '0');
INSERT INTO `tb_menu` VALUES ('205', '相册管理', '/album-submenu', 'Layout', 'el-icon-myimage-fill', '2021-08-03 15:10:54', '2021-08-07 20:02:06', '5', null, '0');
INSERT INTO `tb_menu` VALUES ('206', '相册列表', '/albums', '/album/Album.vue', 'el-icon-myzhaopian', '2021-08-03 20:29:19', '2021-08-04 11:45:47', '1', '205', '0');
INSERT INTO `tb_menu` VALUES ('208', '照片管理', '/albums/:albumId', '/album/Photo.vue', 'el-icon-myzhaopian', '2021-08-03 21:37:47', '2021-08-05 10:24:08', '1', '205', '1');
INSERT INTO `tb_menu` VALUES ('209', '页面管理', '/pages', '/page/Page.vue', 'el-icon-myyemianpeizhi', '2021-08-04 11:36:27', '2021-08-07 20:01:26', '2', '4', '0');
INSERT INTO `tb_menu` VALUES ('210', '照片回收站', '/photos/delete', '/album/Delete.vue', 'el-icon-myhuishouzhan', '2021-08-05 13:55:19', '2023-01-03 10:14:15', '3', '205', '1');
INSERT INTO `tb_menu` VALUES ('213', '权限管理', '/permission-submenu', 'Layout', 'el-icon-mydaohanglantubiao_quanxianguanli', '2021-08-07 19:56:55', '2021-08-07 19:59:40', '4', null, '0');
INSERT INTO `tb_menu` VALUES ('214', '网站管理', '/website', '/website/Website.vue', 'el-icon-myxitong', '2021-08-07 20:06:41', '2023-01-03 10:14:18', '1', '4', '0');
INSERT INTO `tb_menu` VALUES ('215', '说说管理', '/talk-submenu', 'Layout', 'el-icon-mypinglun', '2022-01-23 20:17:59', '2022-01-23 20:38:06', '5', null, '0');
INSERT INTO `tb_menu` VALUES ('216', '发布说说', '/talks', '/talk/Talk.vue', 'el-icon-myfabusekuai', '2022-01-23 20:18:43', '2022-01-23 20:38:19', '1', '215', '0');
INSERT INTO `tb_menu` VALUES ('217', '说说列表', '/talk-list', '/talk/TalkList.vue', 'el-icon-myiconfontdongtaidianji', '2022-01-23 23:28:24', '2022-01-24 00:02:48', '2', '215', '0');
INSERT INTO `tb_menu` VALUES ('218', '修改说说', '/talks/:talkId', '/talk/Talk.vue', 'el-icon-myshouye', '2022-01-24 00:10:44', '2023-01-03 10:14:22', '3', '215', '1');

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `avatar` varchar(255) NOT NULL COMMENT '头像',
  `message_content` varchar(255) NOT NULL COMMENT '留言内容',
  `ip_address` varchar(50) NOT NULL COMMENT '用户ip',
  `ip_source` varchar(255) NOT NULL COMMENT '用户地址',
  `time` tinyint(1) DEFAULT NULL COMMENT '弹幕速度',
  `is_review` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否审核',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_message
-- ----------------------------

-- ----------------------------
-- Table structure for tb_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operation_log`;
CREATE TABLE `tb_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `opt_module` varchar(20) NOT NULL COMMENT '操作模块',
  `opt_type` varchar(20) NOT NULL COMMENT '操作类型',
  `opt_url` varchar(255) NOT NULL COMMENT '操作url',
  `opt_method` varchar(255) NOT NULL COMMENT '操作方法',
  `opt_desc` varchar(255) NOT NULL COMMENT '操作描述',
  `request_param` longtext NOT NULL COMMENT '请求参数',
  `request_method` varchar(20) NOT NULL COMMENT '请求方式',
  `response_data` longtext NOT NULL COMMENT '返回数据',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `nickname` varchar(50) NOT NULL COMMENT '用户昵称',
  `ip_address` varchar(255) NOT NULL COMMENT '操作ip',
  `ip_source` varchar(255) NOT NULL COMMENT '操作地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for tb_page
-- ----------------------------
DROP TABLE IF EXISTS `tb_page`;
CREATE TABLE `tb_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '页面id',
  `page_name` varchar(10) NOT NULL COMMENT '页面名',
  `page_label` varchar(20) DEFAULT NULL COMMENT '页面标签',
  `page_cover` varchar(255) NOT NULL COMMENT '页面封面',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='页面';

-- ----------------------------
-- Records of tb_page
-- ----------------------------
INSERT INTO `tb_page` VALUES ('1', '首页', 'home', 'http://www.static.yehonghan.top/config/home.jpg', '2021-08-07 10:32:36', '2021-12-27 12:19:01');
INSERT INTO `tb_page` VALUES ('2', '归档', 'archive', 'http://www.static.yehonghan.top/config/archive.jpg', '2021-08-07 10:32:36', '2021-10-04 15:43:14');
INSERT INTO `tb_page` VALUES ('3', '分类', 'category', 'http://www.static.yehonghan.top/config/category.jpg', '2021-08-07 10:32:36', '2021-10-04 15:43:31');
INSERT INTO `tb_page` VALUES ('4', '标签', 'tag', 'http://www.static.yehonghan.top/config/tag.jpg', '2021-08-07 10:32:36', '2021-10-04 15:43:38');
INSERT INTO `tb_page` VALUES ('5', '相册', 'album', 'http://www.static.yehonghan.top/config/album.jpg', '2021-08-07 10:32:36', '2021-12-27 12:23:12');
INSERT INTO `tb_page` VALUES ('6', '友链', 'link', 'http://www.static.yehonghan.top/config/link.jpg', '2021-08-07 10:32:36', '2021-10-04 15:44:02');
INSERT INTO `tb_page` VALUES ('7', '关于', 'about', 'http://www.static.yehonghan.top/config/about.jpg', '2021-08-07 10:32:36', '2021-10-04 15:44:08');
INSERT INTO `tb_page` VALUES ('8', '留言', 'message', 'http://www.static.yehonghan.top/config/message.jpg', '2021-08-07 10:32:36', '2021-10-04 16:11:45');
INSERT INTO `tb_page` VALUES ('9', '个人中心', 'user', 'http://www.static.yehonghan.top/config/user.jpg', '2021-08-07 10:32:36', '2021-10-04 15:45:17');
INSERT INTO `tb_page` VALUES ('10', '文章列表', 'articleList', 'http://www.static.yehonghan.top/config/articleList.jpg', '2021-08-10 15:36:19', '2021-10-04 15:45:45');
INSERT INTO `tb_page` VALUES ('11', '说说', 'talk', 'http://www.static.yehonghan.top/config/talk.jpg', '2022-01-23 00:51:24', '2022-01-23 03:01:21');

-- ----------------------------
-- Table structure for tb_photo
-- ----------------------------
DROP TABLE IF EXISTS `tb_photo`;
CREATE TABLE `tb_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_id` int(11) NOT NULL COMMENT '相册id',
  `photo_name` varchar(20) NOT NULL COMMENT '照片名',
  `photo_desc` varchar(50) DEFAULT NULL COMMENT '照片描述',
  `photo_src` varchar(255) NOT NULL COMMENT '照片地址',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='照片';

-- ----------------------------
-- Records of tb_photo
-- ----------------------------

-- ----------------------------
-- Table structure for tb_photo_album
-- ----------------------------
DROP TABLE IF EXISTS `tb_photo_album`;
CREATE TABLE `tb_photo_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_name` varchar(20) NOT NULL COMMENT '相册名',
  `album_desc` varchar(50) NOT NULL COMMENT '相册描述',
  `album_cover` varchar(255) NOT NULL COMMENT '相册封面',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态值 1公开 2私密',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='相册';

-- ----------------------------
-- Records of tb_photo_album
-- ----------------------------

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_name` varchar(50) NOT NULL COMMENT '资源名',
  `url` varchar(255) DEFAULT NULL COMMENT '权限路径',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方式',
  `parent_id` int(11) DEFAULT NULL COMMENT '父权限id',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否匿名访问 0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES ('165', '分类模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('166', '博客信息模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('167', '友链模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('168', '文章模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('169', '日志模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('170', '标签模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('171', '照片模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('172', '用户信息模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('173', '用户账号模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('174', '留言模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('175', '相册模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('176', '菜单模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('177', '角色模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('178', '评论模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('179', '资源模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('180', '页面模块', null, null, null, '0', '2021-08-11 21:04:21', null);
INSERT INTO `tb_resource` VALUES ('181', '查看博客信息', '/', 'GET', '166', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:29');
INSERT INTO `tb_resource` VALUES ('182', '查看关于我信息', '/about', 'GET', '166', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:29');
INSERT INTO `tb_resource` VALUES ('183', '查看后台信息', '/admin', 'GET', '166', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('184', '修改关于我信息', '/admin/about', 'PUT', '166', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('185', '查看后台文章', '/admin/articles', 'GET', '168', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('186', '添加或修改文章', '/admin/articles', 'POST', '168', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('187', '恢复或删除文章', '/admin/articles', 'PUT', '168', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('188', '物理删除文章', '/admin/articles', 'DELETE', '168', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('189', '上传文章图片', '/admin/articles/images', 'POST', '168', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('190', '修改文章置顶', '/admin/articles/top', 'PUT', '168', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('191', '根据id查看后台文章', '/admin/articles/*', 'GET', '168', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('192', '查看后台分类列表', '/admin/categories', 'GET', '165', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('193', '添加或修改分类', '/admin/categories', 'POST', '165', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('194', '删除分类', '/admin/categories', 'DELETE', '165', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('195', '搜索文章分类', '/admin/categories/search', 'GET', '165', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('196', '查询后台评论', '/admin/comments', 'GET', '178', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('197', '删除评论', '/admin/comments', 'DELETE', '178', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('198', '审核评论', '/admin/comments/review', 'PUT', '178', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('199', '查看后台友链列表', '/admin/links', 'GET', '167', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('200', '保存或修改友链', '/admin/links', 'POST', '167', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('201', '删除友链', '/admin/links', 'DELETE', '167', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('202', '查看菜单列表', '/admin/menus', 'GET', '176', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('203', '新增或修改菜单', '/admin/menus', 'POST', '176', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('204', '删除菜单', '/admin/menus/*', 'DELETE', '176', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('205', '查看后台留言列表', '/admin/messages', 'GET', '174', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('206', '删除留言', '/admin/messages', 'DELETE', '174', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('207', '审核留言', '/admin/messages/review', 'PUT', '174', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('208', '查看操作日志', '/admin/operation/logs', 'GET', '169', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('209', '删除操作日志', '/admin/operation/logs', 'DELETE', '169', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('210', '获取页面列表', '/admin/pages', 'GET', '180', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('211', '保存或更新页面', '/admin/pages', 'POST', '180', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('212', '删除页面', '/admin/pages/*', 'DELETE', '180', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('213', '根据相册id获取照片列表', '/admin/photos', 'GET', '171', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('214', '保存照片', '/admin/photos', 'POST', '171', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('215', '更新照片信息', '/admin/photos', 'PUT', '171', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('216', '删除照片', '/admin/photos', 'DELETE', '171', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('217', '移动照片相册', '/admin/photos/album', 'PUT', '171', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('218', '查看后台相册列表', '/admin/photos/albums', 'GET', '175', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('219', '保存或更新相册', '/admin/photos/albums', 'POST', '175', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('220', '上传相册封面', '/admin/photos/albums/cover', 'POST', '175', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('221', '获取后台相册列表信息', '/admin/photos/albums/info', 'GET', '175', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('222', '根据id删除相册', '/admin/photos/albums/*', 'DELETE', '175', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('223', '根据id获取后台相册信息', '/admin/photos/albums/*/info', 'GET', '175', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('224', '更新照片删除状态', '/admin/photos/delete', 'PUT', '171', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('225', '查看资源列表', '/admin/resources', 'GET', '179', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('226', '新增或修改资源', '/admin/resources', 'POST', '179', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('227', '导入swagger接口', '/admin/resources/import/swagger', 'GET', '179', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('228', '删除资源', '/admin/resources/*', 'DELETE', '179', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('229', '保存或更新角色', '/admin/role', 'POST', '177', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('230', '查看角色菜单选项', '/admin/role/menus', 'GET', '176', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('231', '查看角色资源选项', '/admin/role/resources', 'GET', '179', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('232', '查询角色列表', '/admin/roles', 'GET', '177', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('233', '删除角色', '/admin/roles', 'DELETE', '177', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('234', '查询后台标签列表', '/admin/tags', 'GET', '170', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('235', '添加或修改标签', '/admin/tags', 'POST', '170', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('236', '删除标签', '/admin/tags', 'DELETE', '170', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('237', '搜索文章标签', '/admin/tags/search', 'GET', '170', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('238', '查看当前用户菜单', '/admin/user/menus', 'GET', '176', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('239', '查询后台用户列表', '/admin/users', 'GET', '173', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('240', '修改用户禁用状态', '/admin/users/disable', 'PUT', '172', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('241', '查看在线用户', '/admin/users/online', 'GET', '172', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('242', '修改管理员密码', '/admin/users/password', 'PUT', '173', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('243', '查询用户角色选项', '/admin/users/role', 'GET', '177', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('244', '修改用户角色', '/admin/users/role', 'PUT', '172', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('245', '下线用户', '/admin/users/*/online', 'DELETE', '172', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('246', '获取网站配置', '/admin/website/config', 'GET', '166', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('247', '更新网站配置', '/admin/website/config', 'PUT', '166', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('248', '根据相册id查看照片列表', '/albums/*/photos', 'GET', '171', '1', '2021-08-11 21:04:22', '2021-08-11 21:06:35');
INSERT INTO `tb_resource` VALUES ('249', '查看首页文章', '/articles', 'GET', '168', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:45');
INSERT INTO `tb_resource` VALUES ('250', '查看文章归档', '/articles/archives', 'GET', '168', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:47');
INSERT INTO `tb_resource` VALUES ('251', '根据条件查询文章', '/articles/condition', 'GET', '168', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:47');
INSERT INTO `tb_resource` VALUES ('252', '搜索文章', '/articles/search', 'GET', '168', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:48');
INSERT INTO `tb_resource` VALUES ('253', '根据id查看文章', '/articles/*', 'GET', '168', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:49');
INSERT INTO `tb_resource` VALUES ('254', '点赞文章', '/articles/*/like', 'POST', '168', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('255', '查看分类列表', '/categories', 'GET', '165', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:26');
INSERT INTO `tb_resource` VALUES ('256', '查询评论', '/comments', 'GET', '178', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:33');
INSERT INTO `tb_resource` VALUES ('257', '添加评论', '/comments', 'POST', '178', '0', '2021-08-11 21:04:22', '2021-08-11 21:10:05');
INSERT INTO `tb_resource` VALUES ('258', '评论点赞', '/comments/*/like', 'POST', '178', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('259', '查询评论下的回复', '/comments/*/replies', 'GET', '178', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:30');
INSERT INTO `tb_resource` VALUES ('260', '查看友链列表', '/links', 'GET', '167', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:41');
INSERT INTO `tb_resource` VALUES ('261', '查看留言列表', '/messages', 'GET', '174', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:14');
INSERT INTO `tb_resource` VALUES ('262', '添加留言', '/messages', 'POST', '174', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:15');
INSERT INTO `tb_resource` VALUES ('263', '获取相册列表', '/photos/albums', 'GET', '175', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:20');
INSERT INTO `tb_resource` VALUES ('264', '用户注册', '/register', 'POST', '173', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:01');
INSERT INTO `tb_resource` VALUES ('265', '查询标签列表', '/tags', 'GET', '170', '1', '2021-08-11 21:04:22', '2021-08-11 21:06:30');
INSERT INTO `tb_resource` VALUES ('267', '更新用户头像', '/users/avatar', 'POST', '172', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('268', '发送邮箱验证码', '/users/code', 'GET', '173', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:02');
INSERT INTO `tb_resource` VALUES ('269', '绑定用户邮箱', '/users/email', 'POST', '172', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('270', '更新用户信息', '/users/info', 'PUT', '172', '0', '2021-08-11 21:04:22', null);
INSERT INTO `tb_resource` VALUES ('271', 'qq登录', '/users/oauth/qq', 'POST', '173', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:06');
INSERT INTO `tb_resource` VALUES ('273', '修改密码', '/users/password', 'PUT', '173', '1', '2021-08-11 21:04:22', '2021-08-11 21:07:09');
INSERT INTO `tb_resource` VALUES ('274', '上传语音', '/voice', 'POST', '166', '1', '2021-08-11 21:04:22', '2021-08-11 21:05:33');
INSERT INTO `tb_resource` VALUES ('275', '上传访客信息', '/report', 'POST', '166', '1', '2021-08-24 00:32:05', '2021-08-24 00:32:07');
INSERT INTO `tb_resource` VALUES ('276', '获取用户区域分布', '/admin/users/area', 'GET', '173', '0', '2021-08-24 00:32:35', '2021-09-24 16:25:34');
INSERT INTO `tb_resource` VALUES ('278', '说说模块', null, null, null, '0', '2022-01-24 01:29:13', null);
INSERT INTO `tb_resource` VALUES ('279', '查看首页说说', '/home/talks', 'GET', '278', '1', '2022-01-24 01:29:29', '2022-01-24 01:31:56');
INSERT INTO `tb_resource` VALUES ('280', '查看说说列表', '/talks', 'GET', '278', '1', '2022-01-24 01:29:52', '2022-01-24 01:31:56');
INSERT INTO `tb_resource` VALUES ('281', '根据id查看说说', '/talks/*', 'GET', '278', '1', '2022-01-24 01:30:10', '2022-01-24 01:31:57');
INSERT INTO `tb_resource` VALUES ('282', '点赞说说', '/talks/*/like', 'POST', '278', '0', '2022-01-24 01:30:30', null);
INSERT INTO `tb_resource` VALUES ('283', '上传说说图片', '/admin/talks/images', 'POST', '278', '0', '2022-01-24 01:30:46', null);
INSERT INTO `tb_resource` VALUES ('284', '保存或修改说说', '/admin/talks', 'POST', '278', '0', '2022-01-24 01:31:04', null);
INSERT INTO `tb_resource` VALUES ('285', '删除说说', '/admin/talks', 'DELETE', '278', '0', '2022-01-24 01:31:22', null);
INSERT INTO `tb_resource` VALUES ('286', '查看后台说说', '/admin/talks', 'GET', '278', '0', '2022-01-24 01:31:38', null);
INSERT INTO `tb_resource` VALUES ('287', '根据id查看后台说说', '/admin/talks/*', 'GET', '278', '0', '2022-01-24 01:31:53', '2022-01-24 01:33:14');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(20) NOT NULL COMMENT '角色名',
  `role_label` varchar(50) NOT NULL COMMENT '角色描述',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用  0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理员', 'admin', '0', '2021-03-22 14:10:21', '2023-01-03 10:13:55');
INSERT INTO `tb_role` VALUES ('2', '用户', 'user', '0', '2021-03-22 14:25:25', '2022-01-24 01:32:21');
INSERT INTO `tb_role` VALUES ('3', '测试', 'test', '0', '2021-03-22 14:42:23', '2022-01-24 01:32:59');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2527 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('2461', '1', '1');
INSERT INTO `tb_role_menu` VALUES ('2462', '1', '2');
INSERT INTO `tb_role_menu` VALUES ('2463', '1', '6');
INSERT INTO `tb_role_menu` VALUES ('2464', '1', '7');
INSERT INTO `tb_role_menu` VALUES ('2465', '1', '8');
INSERT INTO `tb_role_menu` VALUES ('2466', '1', '9');
INSERT INTO `tb_role_menu` VALUES ('2467', '1', '10');
INSERT INTO `tb_role_menu` VALUES ('2468', '1', '3');
INSERT INTO `tb_role_menu` VALUES ('2469', '1', '11');
INSERT INTO `tb_role_menu` VALUES ('2470', '1', '12');
INSERT INTO `tb_role_menu` VALUES ('2471', '1', '202');
INSERT INTO `tb_role_menu` VALUES ('2472', '1', '13');
INSERT INTO `tb_role_menu` VALUES ('2473', '1', '201');
INSERT INTO `tb_role_menu` VALUES ('2474', '1', '213');
INSERT INTO `tb_role_menu` VALUES ('2475', '1', '14');
INSERT INTO `tb_role_menu` VALUES ('2476', '1', '15');
INSERT INTO `tb_role_menu` VALUES ('2477', '1', '16');
INSERT INTO `tb_role_menu` VALUES ('2478', '1', '4');
INSERT INTO `tb_role_menu` VALUES ('2479', '1', '214');
INSERT INTO `tb_role_menu` VALUES ('2480', '1', '209');
INSERT INTO `tb_role_menu` VALUES ('2481', '1', '17');
INSERT INTO `tb_role_menu` VALUES ('2482', '1', '18');
INSERT INTO `tb_role_menu` VALUES ('2483', '1', '205');
INSERT INTO `tb_role_menu` VALUES ('2484', '1', '206');
INSERT INTO `tb_role_menu` VALUES ('2485', '1', '208');
INSERT INTO `tb_role_menu` VALUES ('2486', '1', '210');
INSERT INTO `tb_role_menu` VALUES ('2487', '1', '215');
INSERT INTO `tb_role_menu` VALUES ('2488', '1', '216');
INSERT INTO `tb_role_menu` VALUES ('2489', '1', '217');
INSERT INTO `tb_role_menu` VALUES ('2490', '1', '218');
INSERT INTO `tb_role_menu` VALUES ('2491', '1', '19');
INSERT INTO `tb_role_menu` VALUES ('2492', '1', '20');
INSERT INTO `tb_role_menu` VALUES ('2493', '1', '5');
INSERT INTO `tb_role_menu` VALUES ('2494', '3', '1');
INSERT INTO `tb_role_menu` VALUES ('2495', '3', '2');
INSERT INTO `tb_role_menu` VALUES ('2496', '3', '6');
INSERT INTO `tb_role_menu` VALUES ('2497', '3', '7');
INSERT INTO `tb_role_menu` VALUES ('2498', '3', '8');
INSERT INTO `tb_role_menu` VALUES ('2499', '3', '9');
INSERT INTO `tb_role_menu` VALUES ('2500', '3', '10');
INSERT INTO `tb_role_menu` VALUES ('2501', '3', '3');
INSERT INTO `tb_role_menu` VALUES ('2502', '3', '11');
INSERT INTO `tb_role_menu` VALUES ('2503', '3', '12');
INSERT INTO `tb_role_menu` VALUES ('2504', '3', '202');
INSERT INTO `tb_role_menu` VALUES ('2505', '3', '13');
INSERT INTO `tb_role_menu` VALUES ('2506', '3', '201');
INSERT INTO `tb_role_menu` VALUES ('2507', '3', '213');
INSERT INTO `tb_role_menu` VALUES ('2508', '3', '14');
INSERT INTO `tb_role_menu` VALUES ('2509', '3', '15');
INSERT INTO `tb_role_menu` VALUES ('2510', '3', '16');
INSERT INTO `tb_role_menu` VALUES ('2511', '3', '4');
INSERT INTO `tb_role_menu` VALUES ('2512', '3', '214');
INSERT INTO `tb_role_menu` VALUES ('2513', '3', '209');
INSERT INTO `tb_role_menu` VALUES ('2514', '3', '17');
INSERT INTO `tb_role_menu` VALUES ('2515', '3', '18');
INSERT INTO `tb_role_menu` VALUES ('2516', '3', '205');
INSERT INTO `tb_role_menu` VALUES ('2517', '3', '206');
INSERT INTO `tb_role_menu` VALUES ('2518', '3', '208');
INSERT INTO `tb_role_menu` VALUES ('2519', '3', '210');
INSERT INTO `tb_role_menu` VALUES ('2520', '3', '215');
INSERT INTO `tb_role_menu` VALUES ('2521', '3', '216');
INSERT INTO `tb_role_menu` VALUES ('2522', '3', '217');
INSERT INTO `tb_role_menu` VALUES ('2523', '3', '218');
INSERT INTO `tb_role_menu` VALUES ('2524', '3', '19');
INSERT INTO `tb_role_menu` VALUES ('2525', '3', '20');
INSERT INTO `tb_role_menu` VALUES ('2526', '3', '5');

-- ----------------------------
-- Table structure for tb_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource`;
CREATE TABLE `tb_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `resource_id` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4981 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role_resource
-- ----------------------------
INSERT INTO `tb_role_resource` VALUES ('4751', '2', '254');
INSERT INTO `tb_role_resource` VALUES ('4752', '2', '267');
INSERT INTO `tb_role_resource` VALUES ('4753', '2', '269');
INSERT INTO `tb_role_resource` VALUES ('4754', '2', '270');
INSERT INTO `tb_role_resource` VALUES ('4755', '2', '257');
INSERT INTO `tb_role_resource` VALUES ('4756', '2', '258');
INSERT INTO `tb_role_resource` VALUES ('4757', '2', '282');
INSERT INTO `tb_role_resource` VALUES ('4853', '3', '192');
INSERT INTO `tb_role_resource` VALUES ('4854', '3', '195');
INSERT INTO `tb_role_resource` VALUES ('4855', '3', '183');
INSERT INTO `tb_role_resource` VALUES ('4856', '3', '246');
INSERT INTO `tb_role_resource` VALUES ('4857', '3', '199');
INSERT INTO `tb_role_resource` VALUES ('4858', '3', '185');
INSERT INTO `tb_role_resource` VALUES ('4859', '3', '191');
INSERT INTO `tb_role_resource` VALUES ('4860', '3', '254');
INSERT INTO `tb_role_resource` VALUES ('4861', '3', '208');
INSERT INTO `tb_role_resource` VALUES ('4862', '3', '234');
INSERT INTO `tb_role_resource` VALUES ('4863', '3', '237');
INSERT INTO `tb_role_resource` VALUES ('4864', '3', '213');
INSERT INTO `tb_role_resource` VALUES ('4865', '3', '241');
INSERT INTO `tb_role_resource` VALUES ('4866', '3', '239');
INSERT INTO `tb_role_resource` VALUES ('4867', '3', '276');
INSERT INTO `tb_role_resource` VALUES ('4868', '3', '205');
INSERT INTO `tb_role_resource` VALUES ('4869', '3', '218');
INSERT INTO `tb_role_resource` VALUES ('4870', '3', '221');
INSERT INTO `tb_role_resource` VALUES ('4871', '3', '223');
INSERT INTO `tb_role_resource` VALUES ('4872', '3', '202');
INSERT INTO `tb_role_resource` VALUES ('4873', '3', '230');
INSERT INTO `tb_role_resource` VALUES ('4874', '3', '238');
INSERT INTO `tb_role_resource` VALUES ('4875', '3', '232');
INSERT INTO `tb_role_resource` VALUES ('4876', '3', '243');
INSERT INTO `tb_role_resource` VALUES ('4877', '3', '196');
INSERT INTO `tb_role_resource` VALUES ('4878', '3', '257');
INSERT INTO `tb_role_resource` VALUES ('4879', '3', '258');
INSERT INTO `tb_role_resource` VALUES ('4880', '3', '225');
INSERT INTO `tb_role_resource` VALUES ('4881', '3', '231');
INSERT INTO `tb_role_resource` VALUES ('4882', '3', '210');
INSERT INTO `tb_role_resource` VALUES ('4883', '3', '282');
INSERT INTO `tb_role_resource` VALUES ('4884', '3', '286');
INSERT INTO `tb_role_resource` VALUES ('4885', '3', '287');
INSERT INTO `tb_role_resource` VALUES ('4886', '1', '165');
INSERT INTO `tb_role_resource` VALUES ('4887', '1', '192');
INSERT INTO `tb_role_resource` VALUES ('4888', '1', '193');
INSERT INTO `tb_role_resource` VALUES ('4889', '1', '194');
INSERT INTO `tb_role_resource` VALUES ('4890', '1', '195');
INSERT INTO `tb_role_resource` VALUES ('4891', '1', '166');
INSERT INTO `tb_role_resource` VALUES ('4892', '1', '183');
INSERT INTO `tb_role_resource` VALUES ('4893', '1', '184');
INSERT INTO `tb_role_resource` VALUES ('4894', '1', '246');
INSERT INTO `tb_role_resource` VALUES ('4895', '1', '247');
INSERT INTO `tb_role_resource` VALUES ('4896', '1', '167');
INSERT INTO `tb_role_resource` VALUES ('4897', '1', '199');
INSERT INTO `tb_role_resource` VALUES ('4898', '1', '200');
INSERT INTO `tb_role_resource` VALUES ('4899', '1', '201');
INSERT INTO `tb_role_resource` VALUES ('4900', '1', '168');
INSERT INTO `tb_role_resource` VALUES ('4901', '1', '185');
INSERT INTO `tb_role_resource` VALUES ('4902', '1', '186');
INSERT INTO `tb_role_resource` VALUES ('4903', '1', '187');
INSERT INTO `tb_role_resource` VALUES ('4904', '1', '188');
INSERT INTO `tb_role_resource` VALUES ('4905', '1', '189');
INSERT INTO `tb_role_resource` VALUES ('4906', '1', '190');
INSERT INTO `tb_role_resource` VALUES ('4907', '1', '191');
INSERT INTO `tb_role_resource` VALUES ('4908', '1', '254');
INSERT INTO `tb_role_resource` VALUES ('4909', '1', '169');
INSERT INTO `tb_role_resource` VALUES ('4910', '1', '208');
INSERT INTO `tb_role_resource` VALUES ('4911', '1', '209');
INSERT INTO `tb_role_resource` VALUES ('4912', '1', '170');
INSERT INTO `tb_role_resource` VALUES ('4913', '1', '234');
INSERT INTO `tb_role_resource` VALUES ('4914', '1', '235');
INSERT INTO `tb_role_resource` VALUES ('4915', '1', '236');
INSERT INTO `tb_role_resource` VALUES ('4916', '1', '237');
INSERT INTO `tb_role_resource` VALUES ('4917', '1', '171');
INSERT INTO `tb_role_resource` VALUES ('4918', '1', '213');
INSERT INTO `tb_role_resource` VALUES ('4919', '1', '214');
INSERT INTO `tb_role_resource` VALUES ('4920', '1', '215');
INSERT INTO `tb_role_resource` VALUES ('4921', '1', '216');
INSERT INTO `tb_role_resource` VALUES ('4922', '1', '217');
INSERT INTO `tb_role_resource` VALUES ('4923', '1', '224');
INSERT INTO `tb_role_resource` VALUES ('4924', '1', '172');
INSERT INTO `tb_role_resource` VALUES ('4925', '1', '240');
INSERT INTO `tb_role_resource` VALUES ('4926', '1', '241');
INSERT INTO `tb_role_resource` VALUES ('4927', '1', '244');
INSERT INTO `tb_role_resource` VALUES ('4928', '1', '245');
INSERT INTO `tb_role_resource` VALUES ('4929', '1', '267');
INSERT INTO `tb_role_resource` VALUES ('4930', '1', '269');
INSERT INTO `tb_role_resource` VALUES ('4931', '1', '270');
INSERT INTO `tb_role_resource` VALUES ('4932', '1', '173');
INSERT INTO `tb_role_resource` VALUES ('4933', '1', '239');
INSERT INTO `tb_role_resource` VALUES ('4934', '1', '242');
INSERT INTO `tb_role_resource` VALUES ('4935', '1', '276');
INSERT INTO `tb_role_resource` VALUES ('4936', '1', '174');
INSERT INTO `tb_role_resource` VALUES ('4937', '1', '205');
INSERT INTO `tb_role_resource` VALUES ('4938', '1', '206');
INSERT INTO `tb_role_resource` VALUES ('4939', '1', '207');
INSERT INTO `tb_role_resource` VALUES ('4940', '1', '175');
INSERT INTO `tb_role_resource` VALUES ('4941', '1', '218');
INSERT INTO `tb_role_resource` VALUES ('4942', '1', '219');
INSERT INTO `tb_role_resource` VALUES ('4943', '1', '220');
INSERT INTO `tb_role_resource` VALUES ('4944', '1', '221');
INSERT INTO `tb_role_resource` VALUES ('4945', '1', '222');
INSERT INTO `tb_role_resource` VALUES ('4946', '1', '223');
INSERT INTO `tb_role_resource` VALUES ('4947', '1', '176');
INSERT INTO `tb_role_resource` VALUES ('4948', '1', '202');
INSERT INTO `tb_role_resource` VALUES ('4949', '1', '203');
INSERT INTO `tb_role_resource` VALUES ('4950', '1', '204');
INSERT INTO `tb_role_resource` VALUES ('4951', '1', '230');
INSERT INTO `tb_role_resource` VALUES ('4952', '1', '238');
INSERT INTO `tb_role_resource` VALUES ('4953', '1', '177');
INSERT INTO `tb_role_resource` VALUES ('4954', '1', '229');
INSERT INTO `tb_role_resource` VALUES ('4955', '1', '232');
INSERT INTO `tb_role_resource` VALUES ('4956', '1', '233');
INSERT INTO `tb_role_resource` VALUES ('4957', '1', '243');
INSERT INTO `tb_role_resource` VALUES ('4958', '1', '178');
INSERT INTO `tb_role_resource` VALUES ('4959', '1', '196');
INSERT INTO `tb_role_resource` VALUES ('4960', '1', '197');
INSERT INTO `tb_role_resource` VALUES ('4961', '1', '198');
INSERT INTO `tb_role_resource` VALUES ('4962', '1', '257');
INSERT INTO `tb_role_resource` VALUES ('4963', '1', '258');
INSERT INTO `tb_role_resource` VALUES ('4964', '1', '179');
INSERT INTO `tb_role_resource` VALUES ('4965', '1', '225');
INSERT INTO `tb_role_resource` VALUES ('4966', '1', '226');
INSERT INTO `tb_role_resource` VALUES ('4967', '1', '227');
INSERT INTO `tb_role_resource` VALUES ('4968', '1', '228');
INSERT INTO `tb_role_resource` VALUES ('4969', '1', '231');
INSERT INTO `tb_role_resource` VALUES ('4970', '1', '180');
INSERT INTO `tb_role_resource` VALUES ('4971', '1', '210');
INSERT INTO `tb_role_resource` VALUES ('4972', '1', '211');
INSERT INTO `tb_role_resource` VALUES ('4973', '1', '212');
INSERT INTO `tb_role_resource` VALUES ('4974', '1', '278');
INSERT INTO `tb_role_resource` VALUES ('4975', '1', '282');
INSERT INTO `tb_role_resource` VALUES ('4976', '1', '283');
INSERT INTO `tb_role_resource` VALUES ('4977', '1', '284');
INSERT INTO `tb_role_resource` VALUES ('4978', '1', '285');
INSERT INTO `tb_role_resource` VALUES ('4979', '1', '286');
INSERT INTO `tb_role_resource` VALUES ('4980', '1', '287');

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) NOT NULL COMMENT '标签名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
INSERT INTO `tb_tag` VALUES ('1', '技术日志', '2023-01-08 13:47:07', null);
INSERT INTO `tb_tag` VALUES ('2', '生活分享', '2023-01-08 13:47:34', null);
INSERT INTO `tb_tag` VALUES ('3', '生活经历', '2023-01-08 13:48:09', null);
INSERT INTO `tb_tag` VALUES ('4', '日常感悟', '2023-01-08 13:48:19', null);

-- ----------------------------
-- Table structure for tb_talk
-- ----------------------------
DROP TABLE IF EXISTS `tb_talk`;
CREATE TABLE `tb_talk` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '说说id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `content` varchar(2000) NOT NULL COMMENT '说说内容',
  `images` varchar(2500) DEFAULT NULL COMMENT '图片',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1.公开 2.私密',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_talk
-- ----------------------------

-- ----------------------------
-- Table structure for tb_unique_view
-- ----------------------------
DROP TABLE IF EXISTS `tb_unique_view`;
CREATE TABLE `tb_unique_view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `views_count` int(11) NOT NULL COMMENT '访问量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_unique_view
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_auth`;
CREATE TABLE `tb_user_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_info_id` int(11) NOT NULL COMMENT '用户信息id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `login_type` tinyint(1) NOT NULL COMMENT '登录类型',
  `ip_address` varchar(50) DEFAULT NULL COMMENT '用户登录ip',
  `ip_source` varchar(50) DEFAULT NULL COMMENT 'ip来源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=997 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user_auth
-- ----------------------------
INSERT INTO `tb_user_auth` VALUES ('1', '1', 'yehonghan@foxmail.com', '$2a$10$X2UTeRmX41bRhWKZB7uc1OevfjjRjj1zoxofAUosWMoiSEPEm5/Q.', '1', '127.0.0.1', '', '2021-08-12 15:43:18', '2023-01-08 13:43:53', '2023-01-08 13:43:53');

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱号',
  `nickname` varchar(50) NOT NULL COMMENT '用户昵称',
  `avatar` varchar(1024) NOT NULL DEFAULT '' COMMENT '用户头像',
  `intro` varchar(255) DEFAULT NULL COMMENT '用户简介',
  `web_site` varchar(255) DEFAULT NULL COMMENT '个人网站',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES ('1', 'yehonghan@foxmail.com', '管理员', 'http://yhh-blog-oss.oss-cn-hangzhou.aliyuncs.com/upload/avatar/7691f12e533684f34c0885aa1f5a3911.jpg', 'yehonghan@foxmail.com', 'www.yehonghan.top', '0', '2021-08-12 15:43:17', '2023-01-07 14:59:26');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1001', '1', '1');

-- ----------------------------
-- Table structure for tb_website_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_website_config`;
CREATE TABLE `tb_website_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config` varchar(2000) DEFAULT NULL COMMENT '配置信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_website_config
-- ----------------------------
INSERT INTO `tb_website_config` VALUES ('1', '{\"alipayQRCode\":\"http://yhh-blog-oss.oss-cn-hangzhou.aliyuncs.com/upload/config/d5d96fee974020620241336fd206f458.jpg\",\"gitee\":\"https://gitee.com/ye-honghan\",\"github\":\"https://github.com/yehonghanscu\",\"isChatRoom\":1,\"isCommentReview\":0,\"isEmailNotice\":1,\"isMessageReview\":0,\"isMusicPlayer\":1,\"isReward\":1,\"qq\":\"1297737082\",\"socialLoginList\":[],\"socialUrlList\":[\"github\",\"gitee\",\"qq\"],\"touristAvatar\":\"http://yhh-blog-oss.oss-cn-hangzhou.aliyuncs.com/upload/config/0bca52afdb2b9998132355d716390c9f.png\",\"userAvatar\":\"http://yhh-blog-oss.oss-cn-hangzhou.aliyuncs.com/upload/config/3b456182cb82044decbadc2b5460e828.png\",\"websiteAuthor\":\"YHH_cs\",\"websiteAvatar\":\"http://yhh-blog-oss.oss-cn-hangzhou.aliyuncs.com/upload/config/7691f12e533684f34c0885aa1f5a3911.jpg\",\"websiteCreateTime\":\"2022-12-01\",\"websiteIntro\":\"YHH\'s  博客，用于记录技术日志、生活经历和日常感悟。\",\"websiteName\":\"YHH_cs\'s blog\",\"websiteNotice\":\"学习与交流用博客项目，系统暂未开启留言与评论审核，请大家文明发言，理性交流。\",\"websiteRecordNo\":\"蜀ICP备2022005526号\",\"websocketUrl\":\"ws://www.ws.yehonghan.top\",\"weiXinQRCode\":\"http://yhh-blog-oss.oss-cn-hangzhou.aliyuncs.com/upload/config/ddb407dc15ddd7344687a05b67997b6b.jpg\"}', '2021-08-09 19:37:30', '2023-01-08 13:52:42');
