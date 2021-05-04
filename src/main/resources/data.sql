drop table if exists User_Role;
drop table if exists Subscribe;
drop table if exists User;
drop table if exists Financial_Report;
drop table if exists Company_Shares;
drop table if exists Candle;
drop table if exists Company_News;
drop table if exists Company;

create table User_Role(
                          id int auto_increment primary key,
                          role varchar(60)
);

create table Subscribe(
                          id int auto_increment primary key,
                          type varchar(60),
                          cost double
);


create table User(
                     id int auto_increment primary key,
                     first_name varchar(60),
                     last_name varchar(60),
                     password varchar(60),
                     email varchar(60),
                     created_at datetime(6),
                     isBlocked bit(1),
                     id_role int,
                     id_subscribe int,
                     FOREIGN KEY (id_role) REFERENCES User_Role (id)
                         ON DELETE CASCADE
                         ON UPDATE CASCADE,
                     FOREIGN KEY (id_subscribe) REFERENCES Subscribe (id)
                         ON DELETE CASCADE
                         ON UPDATE CASCADE
);

create table Company(
                        id varchar(60) primary key,
                        country varchar(60),
                        currency varchar(60),
                        name varchar(60),
                        phone varchar(60),
                        weburl varchar(60),
                        cik varchar(60)
);

create table Financial_Report(
                                 id int auto_increment primary key,
                                 company_ticker varchar(60),
                                 FOREIGN KEY (company_ticker) REFERENCES Company (id)
                                     ON DELETE CASCADE
                                     ON UPDATE CASCADE
);

create table Company_Shares(
                               id int auto_increment primary key,
                               exchange varchar(60),
                               market_capitaliz bigint,
                               share_outstanding bigint,
                               company_ticker varchar(60),
                               FOREIGN KEY (company_ticker) REFERENCES Company (id)
                                   ON DELETE CASCADE
                                   ON UPDATE CASCADE
);

create table Candle(
                       id int auto_increment primary key,
                       open_price double,
                       close_price double,
                       low_price double,
                       high_price double,
                       datetime datetime,
                       volume_data bigint,
                       company_ticker varchar(60),
                       FOREIGN KEY (company_ticker) REFERENCES Company (id)
                           ON DELETE CASCADE
                           ON UPDATE CASCADE

);

create table Company_News(
                             id int auto_increment primary key,
                             category varchar(60),
                             datetime datetime,
                             headline varchar(60),
                             source varchar(60),
                             summary varchar(60),
                             company_ticker varchar(60),
                             FOREIGN KEY (company_ticker) REFERENCES Company (id)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE
);

insert into User_Role (role) values('ROLE_UER'), ('ROlE_ADMIN');