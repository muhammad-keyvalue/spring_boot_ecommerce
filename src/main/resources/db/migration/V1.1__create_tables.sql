create table category (id serial not null, name varchar(255), parent_category integer, primary key (id));
create table customer (id serial not null, city varchar(255), country_code varchar(255), created_at timestamp(6) default(current_timestamp), email varchar(255), name varchar(255), phone varchar(255), profile_pic varchar(255), state varchar(255), street varchar(255), primary key (id));
create table "order" (id serial not null, created_at timestamp(6) default(current_timestamp), status varchar(255), updated_at timestamp(6) default(current_timestamp), customer_id integer, primary key (id));
create table order_item (id serial not null, quantity varchar(255), order_id integer, product_id integer, primary key (id));
create table product (id serial not null, description varchar(255), image varchar(255), name varchar(255), unit_price varchar(255), category_id integer, primary key (id));
alter table if exists "order" add constraint FKb8tboo4d95mh8gavvovwbb7vg foreign key (customer_id) references customer;
alter table if exists order_item add constraint FKt6wv8m7eshksp5kp8w4b2d1dm foreign key (order_id) references "order";
alter table if exists order_item add constraint FK551losx9j75ss5d6bfsqvijna foreign key (product_id) references product;
alter table if exists product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category;
