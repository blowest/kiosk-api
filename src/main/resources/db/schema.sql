drop table if exists menu;

drop table if exists top_menu;

drop table if exists store;

create table store
(
    store_id           bigint auto_increment
        primary key,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    activation_status  varchar(255) null,
    name               varchar(255) null
);

create table top_menu
(
    top_menu_id        bigint auto_increment
        primary key,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    activation_status  varchar(255) null,
    name               varchar(255) null,
    store_id           bigint       null,
    constraint FKeifuc76k0t9k2kf2d0c53alhb
        foreign key (store_id) references store (store_id)
);

create table menu
(
    menu_id            bigint auto_increment
        primary key,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    activation_status  varchar(255) null,
    cost               int          null,
    image_path         varchar(255) null,
    menu_type          varchar(255) null,
    name               varchar(255) null,
    tier_status        varchar(255) null,
    top_menu_id        bigint       null,
    constraint FKpy7hky05cu1o7d3sgkwyq9e0o
        foreign key (top_menu_id) references top_menu (top_menu_id)
);

