create
database fuxi;
    use
fuxi;
create table tiny_url
(
    id         bigint auto_increment
        primary key,
    url        varchar(50) null,
    createTime timestamp    default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP,
    updateTime timestamp    default '0000-00-00 00:00:00' not null,
    sourceUrl  varchar(100) default ''                    not null,
    constraint uni_url
        unique (url)
);

