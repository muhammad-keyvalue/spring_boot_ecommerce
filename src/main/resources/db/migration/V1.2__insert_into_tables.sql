insert into customer(name,phone,email,street,city,state,profile_pic,country_code)
values
('Jamaal Kiehn MD','865-458-4163','greenfelder.alf@conn.com','sequi,ipsum','kochi','kerala','http://osinski.biz/molestiae-harum','IE'),
('Ibrahim Zboncak Jr','+16509693956','wolff.cloyd@yahoo.com','consequuntur','kochi','kerala','http://www.veum.info/','GL'),
('Ms. Alverta Berge Jr.','351-989-1476','shaina.mueller@murphy.com','quisquam','kochi','kerala','http://www.glover.com/adipisci-quia-et-iure','NL'),
('Elwin Larson V','1-956-433-9852','aida38@graham.com','blanditiis','kochi','kerala','http://ortiz.org/et-excepturi','TR'),
('Jameson Lemke','1-609-650-1693','hill.jessika@gmail.com','et','kochi','kerala','http://schulist.org/molestiae-sunt.html','AX');

insert into category(id,name,parent_category)
values
(1,'Electronics',NULL),
(2,'Clothing',NULL),
(3,'Mobiles',1),
(4,'Tshirt',2);

insert into product(name,image,unit_price,description,category_id)
values
('Washing Machine','http://schulist.org/molestiae-sunt',1,'Home Appliance',1),
('Galaxy A7','http://schulist.org/molestiae-sunt',4,'Smartphone',3),
('Polo tshirt','http://schulist.org/molestiae-sunt',6,'Men Clothing',4),
('Adidas tshirt','http://schulist.org/molestiae-sunt',3,'Men Clothing',4),
('Lenovo M7','http://schulist.org/molestiae-sunt',2,'Tablet',3),
('Woodland Shoes','http://schulist.org/molestiae-sunt',6,'Men Footwear',2);
