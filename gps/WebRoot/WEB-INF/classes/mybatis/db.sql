CREATE TABLE jcdf_user 
(
user_id VARCHAR2(50 BYTE) not null,
user_name VARCHAR2(200 BYTE) not null,
user_pass VARCHAR2(100 BYTE),
create_time VARCHAR2(100 BYTE),
user_type VARCHAR2(4 BYTE),
 PRIMARY KEY
  (USER_ID)
);

--DROP TABLE JCDF_AUTH CASCADE CONSTRAINTS;

CREATE TABLE JCDF_AUTH
(
  USER_ROLE_ID  VARCHAR2(50 BYTE)               NOT NULL,
  AUTH_TYPE     NUMBER                          NOT NULL,
  MENU_CODE     VARCHAR2(50 BYTE)               NOT NULL,
  AUTH_PARA1    NUMBER,
  AUTH_PARA2    NUMBER,
  AUTH_PARA3    VARCHAR2(300 BYTE),
  AUTH_PARA4    VARCHAR2(50 BYTE),
  primary key (USER_ROLE_ID, AUTH_TYPE, MENU_CODE)
);


--DROP TABLE JCDF_LOG CASCADE CONSTRAINTS;

CREATE TABLE JCDF_LOG
(
  LOG_ID           VARCHAR2(50 BYTE)            NOT NULL,
  LOG_IP           VARCHAR2(30 BYTE),
  LOG_MAC          VARCHAR2(30 BYTE),
  USER_ID          VARCHAR2(50 BYTE),
  USER_NAME        VARCHAR2(50 BYTE),
  OPERATOR_TIME    DATE,
  OPERATO_CONTENT  VARCHAR2(3000 BYTE),
  MODULE_NAME      VARCHAR2(100 BYTE),
  LOG_PARA1        NUMBER,
  LOG_PARA2        NUMBER,
  LOG_PARA3        VARCHAR2(200 BYTE),
  LOG_PARA4        VARCHAR2(200 BYTE)
);

CREATE UNIQUE INDEX PK_JCDF_LOG ON JCDF_LOG (LOG_ID);


CREATE TABLE JCDF_MENU
(
  MENU_CODE         VARCHAR2(50 BYTE)           NOT NULL,
  PARENT_MENU_CODE  VARCHAR2(50 BYTE)           NOT NULL,
  MENU_NAME         VARCHAR2(50 BYTE),
  MENU_TYPE         NUMBER,
  MENU_MARK         VARCHAR2(200 BYTE),
  CREATE_TIME       DATE,
  MENU_PARA1        NUMBER,
  MENU_PARA2        NUMBER,
  MENU_PARA3        VARCHAR2(50 BYTE),
  MENU_PARA4        VARCHAR2(50 BYTE),
  primary key (MENU_CODE, PARENT_MENU_CODE)
 
);


CREATE TABLE JCDF_ROLE
(
  ROLE_ID      VARCHAR2(50 BYTE)                NOT NULL,
  ROLE_NAME    VARCHAR2(50 BYTE),
  ROLE_MARK    VARCHAR2(200 BYTE),
  CREATE_TIME  DATE,
  ROLE_PARA1   NUMBER,
  ROLE_PARA2   NUMBER,
  ROLE_PARA3   VARCHAR2(50 BYTE),
  ROLE_PARA4   VARCHAR2(50 BYTE),
   PRIMARY KEY (ROLE_ID)
);


--DROP TABLE JCDF_ROLE_USER_MAP CASCADE CONSTRAINTS;

CREATE TABLE JCDF_ROLE_USER_MAP
(
  USER_ID   VARCHAR2(50 BYTE)                   NOT NULL,
  ROLE_ID   VARCHAR2(50 BYTE)                   NOT NULL,
  RU_PARA1  NUMBER,
  RU_PARA2  NUMBER,
  RU_PARA3  VARCHAR2(50 BYTE),
  RU_PARA4  VARCHAR2(50 BYTE),
   PRIMARY KEY
  (USER_ID, ROLE_ID)
);
