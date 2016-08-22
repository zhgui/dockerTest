/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2016-07-19 17:34:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `business_merchant`
-- ----------------------------
DROP TABLE IF EXISTS `business_merchant`;
CREATE TABLE `business_merchant` (
  `merchId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchantName` varchar(255) DEFAULT NULL,
  `businessLicense` varchar(255) DEFAULT NULL,
  `legalPerson` varchar(255) DEFAULT NULL,
  `identityNumber` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`merchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business_merchant
-- ----------------------------

-- ----------------------------
-- Table structure for `pcf_department_ath_t`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_department_ath_t`;
CREATE TABLE `pcf_department_ath_t` (
  `department_ath_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '��֯-�û�ӳ��ID',
  `department_id` bigint(11) NOT NULL COMMENT '����ID',
  `user_id` bigint(11) NOT NULL COMMENT '�û�ID',
  `department_main` varchar(1) NOT NULL COMMENT '�������־λ(0���������� 1��������)',
  `delete_flag` varchar(1) NOT NULL COMMENT 'ɾ����־λ(0����Ч 1����Ч)',
  `sort_key` bigint(15) NOT NULL COMMENT '�����',
  `create_user_cd` varchar(100) NOT NULL COMMENT '������',
  `create_date` datetime NOT NULL COMMENT '������',
  `record_user_cd` varchar(100) NOT NULL COMMENT '��������',
  `record_date` datetime NOT NULL COMMENT '��������',
  PRIMARY KEY (`department_ath_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='��֯-�û�ӳ���';

-- ----------------------------
-- Records of pcf_department_ath_t
-- ----------------------------
INSERT INTO `pcf_department_ath_t` VALUES ('1', '1', '1', '0', '0', '0', 'admin', '2016-03-14 16:48:15', 'admin', '2016-03-14 16:48:15');

-- ----------------------------
-- Table structure for `pcf_department_inc_ath_t`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_department_inc_ath_t`;
CREATE TABLE `pcf_department_inc_ath_t` (
  `department_inc_ath_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '��֯�ڰ�ID',
  `parent_department_id` bigint(11) NOT NULL COMMENT '������֯ID',
  `department_id` bigint(11) NOT NULL COMMENT '��֯ID',
  `depth` bigint(1) NOT NULL COMMENT '�ײ㼶',
  `delete_flag` varchar(1) NOT NULL COMMENT 'ɾ����־λ(0����Ч 1����Ч)',
  `sort_key` bigint(15) NOT NULL COMMENT '�����',
  `create_user_cd` varchar(100) NOT NULL COMMENT '������',
  `create_date` datetime NOT NULL COMMENT '������',
  `record_user_cd` varchar(100) NOT NULL COMMENT '��������',
  `record_date` datetime NOT NULL COMMENT '��������',
  PRIMARY KEY (`department_inc_ath_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='��֯�ڰ�';

-- ----------------------------
-- Records of pcf_department_inc_ath_t
-- ----------------------------
INSERT INTO `pcf_department_inc_ath_t` VALUES ('1', '1', '1', '0', '0', '0', 'admin', '2016-03-14 16:46:49', 'admin', '2016-03-14 16:46:49');
INSERT INTO `pcf_department_inc_ath_t` VALUES ('2', '2', '2', '0', '0', '0', 'admin', '2016-03-14 19:01:36', 'admin', '2016-03-14 19:01:36');
INSERT INTO `pcf_department_inc_ath_t` VALUES ('3', '1', '2', '1', '0', '0', 'admin', '2016-03-14 19:01:36', 'admin', '2016-03-14 19:01:36');
INSERT INTO `pcf_department_inc_ath_t` VALUES ('5', '4', '4', '0', '0', '0', 'admin', '2016-03-14 19:06:01', 'admin', '2016-03-14 19:06:01');
INSERT INTO `pcf_department_inc_ath_t` VALUES ('6', '1', '4', '1', '0', '0', 'admin', '2016-03-14 19:06:01', 'admin', '2016-03-14 19:06:01');

-- ----------------------------
-- Table structure for `pcf_department_t`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_department_t`;
CREATE TABLE `pcf_department_t` (
  `department_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `department_cd` varchar(100) NOT NULL COMMENT '���ű��',
  `department_name` varchar(1000) DEFAULT NULL COMMENT '������',
  `department_short_name` varchar(1000) DEFAULT NULL COMMENT '���ż��',
  `department_search_name` varchar(1000) DEFAULT NULL COMMENT '���ż�����',
  `country_cd` varchar(100) DEFAULT NULL COMMENT '���ұ��(CN:�й� JP:�ձ� US:����)',
  `zip_code` varchar(50) DEFAULT NULL COMMENT '�������',
  `address1` varchar(1000) DEFAULT NULL COMMENT '��ַ1',
  `address2` varchar(1000) DEFAULT NULL COMMENT '��ַ2',
  `address3` varchar(1000) DEFAULT NULL COMMENT '��ַ3',
  `telephone_number` varchar(50) DEFAULT NULL COMMENT '�绰����',
  `extension_number` varchar(50) DEFAULT NULL COMMENT '�ֻ���',
  `fax_number` varchar(50) DEFAULT NULL COMMENT '�����',
  `extension_fax_number` varchar(50) DEFAULT NULL COMMENT '����ֻ���',
  `email_address1` varchar(256) DEFAULT NULL COMMENT '�ʼ���ַ1',
  `email_address2` varchar(256) DEFAULT NULL COMMENT '�ʼ���ַ2',
  `url` varchar(1000) DEFAULT NULL COMMENT 'URL',
  `notes` varchar(4000) DEFAULT NULL COMMENT '��ע',
  `delete_flag` varchar(1) NOT NULL COMMENT 'ɾ����־(0����Ч 1����Ч)',
  `sort_key` bigint(20) NOT NULL COMMENT '�����',
  `create_user_cd` varchar(100) NOT NULL COMMENT '������',
  `create_date` datetime NOT NULL COMMENT '������',
  `record_user_cd` varchar(100) NOT NULL COMMENT '��������',
  `record_date` datetime NOT NULL COMMENT '��������',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='��֯';

-- ----------------------------
-- Records of pcf_department_t
-- ----------------------------
INSERT INTO `pcf_department_t` VALUES ('1', '001', '总部', '总部', '总部', '', '034000', '', '', '', '1520199294', '1520199294', '', '', '', '', null, '', '0', '0', 'admin', '2016-03-14 16:46:49', 'admin', '2016-03-14 16:46:49');
INSERT INTO `pcf_department_t` VALUES ('2', '江西', '江西', '江西', '江西', '', '', '', '', '', '', '', '', '', '', '', null, '', '0', '0', 'admin', '2016-03-14 19:01:36', 'admin', '2016-03-14 19:01:36');
INSERT INTO `pcf_department_t` VALUES ('4', '北京', '北京', '北京', '北京', '', '', '', '', '', '', '', '', '', '', '', null, '', '0', '0', 'admin', '2016-03-14 19:06:01', 'admin', '2016-03-14 19:06:01');

-- ----------------------------
-- Table structure for `pcf_permission_t`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_permission_t`;
CREATE TABLE `pcf_permission_t` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Ȩ������',
  `permission_name` varchar(1000) NOT NULL COMMENT 'Ȩ����',
  `permission` varchar(25) NOT NULL COMMENT 'Ȩ�ޱ�ʶ',
  `notes` varchar(4000) DEFAULT NULL COMMENT '��ע',
  `delete_flag` varchar(1) NOT NULL COMMENT '��־��0����Ч 1����Ч',
  `sort_key` varchar(15) DEFAULT NULL COMMENT '�����',
  `create_user_cd` varchar(100) DEFAULT NULL COMMENT '������',
  `create_date` datetime DEFAULT NULL COMMENT '������',
  `record_user_cd` varchar(100) DEFAULT NULL COMMENT '��������',
  `record_date` datetime DEFAULT NULL COMMENT '��������',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1105 DEFAULT CHARSET=utf8 COMMENT='Ȩ�ޱ�';

-- ----------------------------
-- Records of pcf_permission_t
-- ----------------------------
INSERT INTO `pcf_permission_t` VALUES ('1100', '所有', '*', '所有权限', '0', '1', '', '2014-12-27 20:27:53', '', '2014-12-27 20:27:53');
INSERT INTO `pcf_permission_t` VALUES ('1101', '添加', 'create', '添加权限', '0', '2', '', '2016-01-22 20:59:58', '', '2015-01-16 14:34:32');
INSERT INTO `pcf_permission_t` VALUES ('1102', '删除', 'delete', '删除权限', '0', '3', '', '2016-01-22 21:00:02', '', '2015-01-16 14:32:59');
INSERT INTO `pcf_permission_t` VALUES ('1103', '更新', 'update', '更新权限', '0', '4', '', '2014-12-27 20:29:08', '', '2014-12-27 20:29:08');
INSERT INTO `pcf_permission_t` VALUES ('1104', '查看', 'view', '查看权限', '0', '5', '', '2016-01-22 21:00:04', '', '2014-12-28 18:47:30');

-- ----------------------------
-- Table structure for `pcf_resource_t`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_resource_t`;
CREATE TABLE `pcf_resource_t` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '��Դ������',
  `resource_name` varchar(4000) NOT NULL COMMENT '��Դ����',
  `identify` varchar(200) DEFAULT NULL COMMENT '��Դ��ʶ',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL',
  `notes` varchar(4000) DEFAULT NULL COMMENT '��ע',
  `delete_flag` varchar(1) NOT NULL COMMENT 'ɾ����־ 0����Ч 1����Ч',
  `sort_key` bigint(20) NOT NULL COMMENT '�����',
  `create_user_id` bigint(20) NOT NULL COMMENT '������',
  `create_date` datetime NOT NULL COMMENT '������',
  `record_user_id` bigint(20) NOT NULL COMMENT '��������',
  `record_date` datetime NOT NULL COMMENT '��������',
  `icon` varchar(200) DEFAULT NULL COMMENT 'Ȩ��ͼ��',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '����ԴID',
  `parent_ids` varchar(400) DEFAULT NULL COMMENT '����Դid�ַ���',
  PRIMARY KEY (`resource_id`),
  UNIQUE KEY `identify` (`identify`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10360 DEFAULT CHARSET=utf8 COMMENT='��Դ��';

-- ----------------------------
-- Records of pcf_resource_t
-- ----------------------------
INSERT INTO `pcf_resource_t` VALUES ('1', '资源', 'root', 'root', '资源根节点', '0', '0', '0', '2014-11-30 00:00:00', '0', '2014-11-30 00:00:00', '', '-1', '');
INSERT INTO `pcf_resource_t` VALUES ('2', '权限管理', 'permission', '/pcf/permission/toList', '权限列表', '0', '1', '0', '2014-11-30 00:00:00', '0', '2014-11-30 00:00:00', '', '1404', '1/1404/');
INSERT INTO `pcf_resource_t` VALUES ('3', '资源管理', 'resource', '/pcf/resource/toList', '资源列表', '0', '2', '0', '2014-11-30 00:00:00', '0', '2014-11-30 00:00:00', '', '1404', '1/1404/');
INSERT INTO `pcf_resource_t` VALUES ('1300', '角色管理', 'role', '/pcf/role/toList', '角色管理', '0', '3', '0', '2014-12-27 20:31:48', '0', '2014-12-27 20:31:48', '', '1404', '1/1404/');
INSERT INTO `pcf_resource_t` VALUES ('1350', '用户管理', 'user', '/pcf/user/toList', '用户管理', '0', '4', '0', '2014-12-28 10:31:25', '0', '2014-12-28 10:31:25', '', '1404', '1/1404/');
INSERT INTO `pcf_resource_t` VALUES ('1351', '组织机构管理', 'department', '/pcf/department/toTreeList', '组织机构管理', '0', '5', '0', '2014-12-28 10:32:07', '0', '2014-12-28 10:32:07', '', '1404', '1/1404/');
INSERT INTO `pcf_resource_t` VALUES ('1404', '权限管理', 'pcf', '', '权限管理', '0', '1', '0', '2014-12-28 11:55:35', '0', '2014-12-28 11:55:35', 'fa fa-user-secret', '1', '1/');
INSERT INTO `pcf_resource_t` VALUES ('10302', '系统管理', 'system', '', '任务管理', '0', '3', '0', '2015-08-05 14:03:29', '0', '2015-08-05 14:03:29', 'fa fa-tasks', '1', '1/');
INSERT INTO `pcf_resource_t` VALUES ('10303', '任务信息列表', 'taskList', '/pcf/task/taskList', '任务信息列表', '1', '10', '0', '2015-08-05 14:04:03', '0', '2015-08-05 14:04:03', '', '10302', '1/10302/');
INSERT INTO `pcf_resource_t` VALUES ('10304', '系统监控', 'druid', '/druid', '', '0', '2', '0', '2015-09-16 15:27:39', '0', '2015-09-16 15:27:39', '', '10302', '1/10302/');

-- ----------------------------
-- Table structure for `pcf_role_ath_t`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_role_ath_t`;
CREATE TABLE `pcf_role_ath_t` (
  `role_ath_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '�û�-��ɫӳ��ID',
  `role_id` bigint(20) NOT NULL COMMENT '��ɫID',
  `user_id` bigint(20) NOT NULL COMMENT '�û�ID',
  `delete_flag` varchar(1) NOT NULL COMMENT 'ɾ����־λ(0����Ч 1����Ч)',
  `sort_key` bigint(20) NOT NULL COMMENT '�����',
  `create_user_cd` varchar(100) NOT NULL COMMENT '������',
  `create_date` datetime NOT NULL COMMENT '������',
  `record_user_cd` varchar(100) NOT NULL COMMENT '��������',
  `record_date` datetime NOT NULL COMMENT '��������',
  PRIMARY KEY (`role_ath_id`)
) ENGINE=InnoDB AUTO_INCREMENT=658 DEFAULT CHARSET=utf8 COMMENT='�û�-��ɫӳ���';

-- ----------------------------
-- Records of pcf_role_ath_t
-- ----------------------------
INSERT INTO `pcf_role_ath_t` VALUES ('650', '109', '1', '0', '0', 'default', '2014-12-28 11:21:25', 'default', '2014-12-28 11:21:25');
INSERT INTO `pcf_role_ath_t` VALUES ('653', '113', '1', '0', '0', 'admin', '2016-03-13 21:30:44', 'admin', '2016-03-13 21:30:44');
INSERT INTO `pcf_role_ath_t` VALUES ('654', '113', '3', '0', '0', 'admin', '2016-03-14 11:17:35', 'admin', '2016-03-14 11:17:35');
INSERT INTO `pcf_role_ath_t` VALUES ('656', '109', '4', '0', '0', 'zhougui', '2016-03-17 18:05:08', 'zhougui', '2016-03-17 18:05:08');
INSERT INTO `pcf_role_ath_t` VALUES ('657', '109', '5', '0', '0', 'admin', '2016-04-18 16:45:55', 'admin', '2016-04-18 16:45:55');

-- ----------------------------
-- Table structure for `pcf_role_resource_permission`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_role_resource_permission`;
CREATE TABLE `pcf_role_resource_permission` (
  `rrp_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '��ɫ��ȨȨ�ޱ�ID',
  `role_id` bigint(20) NOT NULL COMMENT '��ɫID',
  `resource_id` bigint(20) NOT NULL COMMENT '��ԴID',
  `permission_ids` varchar(200) NOT NULL COMMENT 'Ȩ��IDS',
  `create_user_id` bigint(20) NOT NULL COMMENT '������',
  `create_date` datetime NOT NULL COMMENT '������',
  PRIMARY KEY (`rrp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1226 DEFAULT CHARSET=utf8 COMMENT='��ɫ��Դ��Ȩ��';

-- ----------------------------
-- Records of pcf_role_resource_permission
-- ----------------------------
INSERT INTO `pcf_role_resource_permission` VALUES ('1218', '109', '2', '1100,1101,1102,1103,1104', '0', '2016-07-19 10:03:41');
INSERT INTO `pcf_role_resource_permission` VALUES ('1219', '109', '3', '1100,1101,1102,1103,1104', '0', '2016-07-19 10:03:41');
INSERT INTO `pcf_role_resource_permission` VALUES ('1220', '109', '1300', '1100,1101,1102,1103,1104', '0', '2016-07-19 10:03:41');
INSERT INTO `pcf_role_resource_permission` VALUES ('1221', '109', '1350', '1100,1101,1102,1103,1104', '0', '2016-07-19 10:03:41');
INSERT INTO `pcf_role_resource_permission` VALUES ('1222', '109', '1351', '1100,1101,1102,1103,1104', '0', '2016-07-19 10:03:41');
INSERT INTO `pcf_role_resource_permission` VALUES ('1223', '109', '10304', '1100,1101,1102,1103,1104', '0', '2016-07-19 10:03:41');
INSERT INTO `pcf_role_resource_permission` VALUES ('1224', '109', '10348', '1100,1101,1102,1103,1104', '0', '2016-07-19 10:03:41');
INSERT INTO `pcf_role_resource_permission` VALUES ('1225', '109', '10303', '1100,1101,1102,1103,1104', '0', '2016-07-19 10:03:41');

-- ----------------------------
-- Table structure for `pcf_role_t`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_role_t`;
CREATE TABLE `pcf_role_t` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '��ɫ������',
  `role_name` varchar(1000) NOT NULL COMMENT '��ɫ��',
  `role` varchar(200) NOT NULL COMMENT '��ɫ��ʶ',
  `notes` varchar(4000) DEFAULT NULL COMMENT '��ע',
  `delete_flag` varchar(1) NOT NULL COMMENT 'ɾ����־ 0����Ч 1����Ч',
  `sort_key` bigint(15) DEFAULT NULL COMMENT '�����',
  `create_user_id` int(10) unsigned NOT NULL COMMENT '������',
  `create_date` datetime NOT NULL COMMENT '������',
  `record_user_id` bigint(20) NOT NULL COMMENT '��������',
  `record_date` datetime NOT NULL COMMENT '��������',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='��ɫ��';

-- ----------------------------
-- Records of pcf_role_t
-- ----------------------------
INSERT INTO `pcf_role_t` VALUES ('109', '系统管理员', 'admin', '系统管理员', '0', '1', '0', '2016-07-19 10:03:41', '0', '2016-07-19 10:03:41');

-- ----------------------------
-- Table structure for `pcf_user_t`
-- ----------------------------
DROP TABLE IF EXISTS `pcf_user_t`;
CREATE TABLE `pcf_user_t` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '�û�ID',
  `user_cd` varchar(100) NOT NULL COMMENT '�û����',
  `password` varchar(255) DEFAULT NULL COMMENT '����',
  `user_name` varchar(1000) DEFAULT NULL COMMENT '�û���',
  `user_search_name` varchar(1000) DEFAULT NULL COMMENT '�û�������',
  `sex` varchar(1) DEFAULT NULL COMMENT '�Ա�(0:�� 1:Ů)',
  `id_card_cd` varchar(100) DEFAULT NULL COMMENT '���֤��',
  `passport_cd` varchar(100) DEFAULT NULL COMMENT '���պ�',
  `eep_hk_m_cd` varchar(100) DEFAULT NULL COMMENT '�۰�ͨ��֤',
  `eep_taiwan_cd` varchar(100) DEFAULT NULL COMMENT '����̨��ͨ��֤',
  `country_cd` varchar(100) DEFAULT NULL COMMENT '���ұ���(CN:�й� JP:�ձ� US:����)',
  `zip_code` varchar(50) DEFAULT NULL COMMENT '�������',
  `address1` varchar(1000) DEFAULT NULL COMMENT '��ַ1',
  `address2` varchar(1000) DEFAULT NULL COMMENT '��ַ2',
  `address3` varchar(1000) DEFAULT NULL COMMENT '��ַ3',
  `telephone_number` varchar(50) DEFAULT NULL COMMENT '�绰����',
  `extension_number` varchar(50) DEFAULT NULL COMMENT '�ֻ���',
  `fax_number` varchar(50) DEFAULT NULL COMMENT '�����',
  `extension_fax_number` varchar(50) DEFAULT NULL COMMENT '����ֻ���',
  `mobile_number` varchar(50) DEFAULT NULL COMMENT '�ֻ���',
  `email_address1` varchar(256) DEFAULT NULL COMMENT '�ʼ���ַ1',
  `email_address2` varchar(256) DEFAULT NULL COMMENT '�ʼ���ַ2',
  `mobile_email_address` varchar(256) DEFAULT NULL COMMENT '�ֻ������ַ',
  `url` varchar(1000) DEFAULT NULL COMMENT 'URL',
  `notes` varchar(4000) DEFAULT NULL COMMENT '��ע',
  `delete_flag` varchar(1) NOT NULL COMMENT 'ɾ����־(0����Ч 1����Ч)',
  `sort_key` bigint(20) NOT NULL COMMENT '�����',
  `create_user_cd` varchar(100) NOT NULL COMMENT '������',
  `create_date` datetime NOT NULL COMMENT '������',
  `record_user_cd` varchar(100) NOT NULL COMMENT '��������',
  `record_date` datetime NOT NULL COMMENT '��������',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='�û���';

-- ----------------------------
-- Records of pcf_user_t
-- ----------------------------
INSERT INTO `pcf_user_t` VALUES ('1', 'admin', 'f81f783fa2b58ae83269f96cbf642761', '系统管理员', '系统管理员', '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null, '', '0', '0', 'default', '2014-12-28 11:21:16', 'anita', '2016-07-18 13:33:27');
