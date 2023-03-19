----------------User-----------------------
INSERT INTO user (email,password,first_name,last_name,gender,age,phone_number,address,status,role_name)
       select 'rishireiko@gmail.com','$2a$10$s9XT/zwrgEbtBIUcySUUSe2hftB7tvXmhrJY9hfw.uwVcAAr7LtYu','Admin','Admin','Female',25,'1234567890','Point-Pedro, Jaffna',true,'ADMIN'
       WHERE NOT EXISTS (SELECT true FROM user WHERE id=1);
       
--------------Mail Properties-----------------
INSERT INTO mail_properties (created_at, updated_at, host, password, port, username, active, protocol)
       select  now(), now(), 'smtp.gmail.com', 'psswwflzelitnimc', 587, 'rishireiko@gmail.com', true, 'smtp'
       WHERE NOT EXISTS (SELECT true FROM mail_properties WHERE username='rishireiko@gmail.com' and password='psswwflzelitnimc');
       