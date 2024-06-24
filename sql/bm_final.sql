create table if not exists book_out
(
    ID         int auto_increment comment '序号'
        primary key,
    ISBN       varchar(255)   null comment 'ISBN',
    bookname   varchar(20)    null comment '书名',
    out_num    int            null comment '购买数量',
    mark_price decimal(10, 2) null comment '标价',
    discount   varchar(255)   null comment '折扣',
    sum        varchar(30)    null comment '应付',
    receive    varchar(30)    null comment '收取',
    `return`   varchar(30)    null comment '找零',
    time       varchar(30)    null comment '时间'
)
    charset = utf8
    row_format = DYNAMIC;

create table if not exists book_stack
(
    ISBN       varchar(255)   not null comment 'ISBN'
        primary key,
    bookname   varchar(255)   null comment '书名',
    author     varchar(50)    null comment '作者',
    num        int            null comment '数量',
    mark_price decimal(10, 2) null comment '标价'
)
    charset = utf8
    row_format = DYNAMIC;

create index bookname
    on book_stack (bookname);

create index num
    on book_stack (num);

create table if not exists manager
(
    ID       int auto_increment comment 'ID'
        primary key,
    user     varchar(255) not null comment '用户名',
    password varchar(255) not null comment '密码'
)
    charset = utf8
    row_format = DYNAMIC;

create table if not exists new_book_in
(
    ID         int auto_increment
        primary key,
    ISBN       varchar(255)   null comment 'ISBN',
    bookname   varchar(255)   null comment '书名',
    author     varchar(255)   null comment '作者',
    cost_price decimal(10, 2) null comment '进价',
    num        int            null comment '数量',
    time       varchar(255)   null comment '入库时间',
    mark_price decimal(10, 2) null comment '标价'
)
    charset = utf8
    row_format = DYNAMIC;

INSERT INTO bm.book_stack (ISBN, bookname, author, num, mark_price) VALUES ('7-5111-5645-2', '有机食品加工技术', '邹磊', 3, 48.00);
INSERT INTO bm.book_stack (ISBN, bookname, author, num, mark_price) VALUES ('9-7871-0011-7', '英语的故事', '克里斯特尔', 5, 25.00);
INSERT INTO bm.book_stack (ISBN, bookname, author, num, mark_price) VALUES ('9-7875-2073-8', '“四史”精要学习公开课', '李忠杰', 2, 78.00);

INSERT INTO bm.manager (ID, user, password) VALUES (1, 'admin', 'admin');
