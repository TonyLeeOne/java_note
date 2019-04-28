create table info
(
  id varchar(32) not null
    primary key,
  nid varchar(32) null,
  count int null comment '浏览数量',
  recommend int default '1' null,
  modify int null comment '参与修改人数',
  creator varchar(10) default 'DBA' null,
  create_date varchar(30) null,
  modifier varchar(10) default 'DBA' null,
  modify_date varchar(30) null
);

create table note
(
  id varchar(32) not null
    primary key,
  note_name varchar(100) not null,
  category varchar(30) not null,
  content mediumtext null,
  state char default '1' null comment '1为发布，2为草稿，默认为1',
  is_delete char default '1' null comment '默认为1 删除为2',
  creator varchar(20) default 'DBA' null,
  create_date varchar(30) null,
  modifier varchar(20) default 'DBA' null,
  modify_date varchar(30) null,
  image_url varchar(100) null
);

create table user
(
  id varchar(30) not null
    primary key,
  username varchar(10) null,
  avatar varchar(100) null,
  state char default '1' null comment '1为有效，2为锁定',
  git_url varchar(100) null
);

create table subject (
  id          varchar(30)  not null primary key,
  sub_name    varchar(30) not null comment '专题名',
  descriptiion        varchar(100) comment '描述',
  image varchar(30) comment '主题图片',
  creator     varchar(30),
  create_date varchar(20),
  is_delete   char(1) default '1',
  modify_date varchar(20),
  modifier    varchar(20)
);

create table r_sub_note(
  id varchar(30) not null primary key ,
  sub_id varchar(30),
  note_id varchar(32),
  is_delete char(1) default '1'
);





