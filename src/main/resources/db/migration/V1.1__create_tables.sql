create table category (id serial not null, name varchar(255), parent_category integer, primary key (id));
create table customer (id serial not null, city varchar(255), country_code varchar(255), created_at timestamp(6), email varchar(255), name varchar(255), phone varchar(255), profile_pic varchar(255), state varchar(255), street varchar(255), primary key (id));
create table "order" (id serial not null, created_at timestamp(6), status varchar(255), total_price integer not null, total_quantity integer not null, updated_at timestamp(6), customer_id integer, primary key (id));
create table order_item (id serial not null, quantity integer, order_id integer, product_id integer, primary key (id));
create table product (id serial not null, description varchar(255), image varchar(255), name varchar(255), unit_price integer, category_id integer, primary key (id));
alter table "order" add constraint "FK_Order_Customer" foreign key (customer_id) references customer;
alter table order_item add constraint "FK_OrderItem_Order" foreign key (order_id) references "order";
alter table order_item add constraint "FK_OrderItem_Product" foreign key (product_id) references product;
alter table product add constraint "FK_Product_Category" foreign key (category_id) references category;
