alter table "order" drop constraint "FK_Order_Customer" ;
alter table order_item drop constraint "FK_OrderItem_Order";
alter table order_item drop constraint "FK_OrderItem_Product";
alter table product drop constraint "FK_Product_Category";
drop table category;
drop table customer;
drop table "order";
drop table order_item;
drop table product;
