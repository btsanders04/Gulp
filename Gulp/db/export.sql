--------------------------------------------------------
--  File created - Monday-August-24-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RESTAURANTS
--------------------------------------------------------

  CREATE TABLE "TESTDB"."RESTAURANTS" 
   (	"RESTAURANT_ID" NUMBER(*,0), 
	"AVG_RATING" NUMBER(3,2), 
	"NUM_RATING" NUMBER(*,0), 
	"RESTAURANT_NAME" VARCHAR2(255 BYTE), 
	"RESTAURANT_ADD" VARCHAR2(255 BYTE), 
	"RESTAURANT_DES" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table REVIEW
--------------------------------------------------------

  CREATE TABLE "TESTDB"."REVIEW" 
   (	"USER_ID" NUMBER(*,0), 
	"RESTAURANT_ID" NUMBER(*,0), 
	"REVIEW_DATE" DATE, 
	"REVIEW_DES" VARCHAR2(255 BYTE), 
	"RATING" NUMBER(3,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USERPROFILE
--------------------------------------------------------

  CREATE TABLE "TESTDB"."USERPROFILE" 
   (	"USER_ID" NUMBER(*,0), 
	"USER_NAME" VARCHAR2(255 BYTE), 
	"USER_EMAIL" VARCHAR2(255 BYTE), 
	"USER_ZIPCODE" VARCHAR2(20 BYTE), 
	"USER_PASS" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into TESTDB.RESTAURANTS
SET DEFINE OFF;
Insert into TESTDB.RESTAURANTS (RESTAURANT_ID,AVG_RATING,NUM_RATING,RESTAURANT_NAME,RESTAURANT_ADD,RESTAURANT_DES) values (1,4,1,'The Grill','222 haven st, MD','American resturant');
Insert into TESTDB.RESTAURANTS (RESTAURANT_ID,AVG_RATING,NUM_RATING,RESTAURANT_NAME,RESTAURANT_ADD,RESTAURANT_DES) values (2,2,1,'Banda express','100 university st, MD','Chiness');
Insert into TESTDB.RESTAURANTS (RESTAURANT_ID,AVG_RATING,NUM_RATING,RESTAURANT_NAME,RESTAURANT_ADD,RESTAURANT_DES) values (4,4,4,'Pho','99 university st, MD','Vietnamese');
Insert into TESTDB.RESTAURANTS (RESTAURANT_ID,AVG_RATING,NUM_RATING,RESTAURANT_NAME,RESTAURANT_ADD,RESTAURANT_DES) values (5,4,4,'Dogfish Head Alehouse','800 W Diamond Ave
Gaithersburg, MD 20878','American');
Insert into TESTDB.RESTAURANTS (RESTAURANT_ID,AVG_RATING,NUM_RATING,RESTAURANT_NAME,RESTAURANT_ADD,RESTAURANT_DES) values (3,4.9,2,'Trapezaria','11 N Washington St
Rockville, MD 20850','Greek');
REM INSERTING into TESTDB.REVIEW
SET DEFINE OFF;
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (1,5,to_date('24-AUG-15','DD-MON-RR'),'My friend from Mumbai and I came here to celebrate the end of the week. We stayed here and in the parking lot for several hours talking about high school cross country, Jake Krampf, Janae, Winzelle "too fresh, make that money" Steele, and steroids. ',5);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (2,5,to_date('24-AUG-15','DD-MON-RR'),'Didnt try the food, however Dogfish head craft beers!!!If you love craft beers this is your place, being from New York trying this place out was an experience! My friend whos a craft beer fiend basically ran through the doors out of excitement.',4);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (3,5,to_date('24-AUG-15','DD-MON-RR'),'Everything is great so far I have a awesome server his name is sam I got seated upstairs  boy is it busy up here but Im pleased with my food and the service!',4);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (1,4,to_date('24-AUG-15','DD-MON-RR'),'Enjoyed our dinner.  Choose it because of the good reviews and the crab cakes were delicious for a novice crabber

',5);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (2,4,to_date('24-AUG-15','DD-MON-RR'),' They Love the crab Cake Diner! And also they Mac and cheese with chicken. I love the chicken wings(Buffalo). I also love the fact that they are open late on the weekends and sometimes they have a live band!',5);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (3,4,to_date('24-AUG-15','DD-MON-RR'),'Came here tonight for dinner with the boyfriend. Got there around 9 and was happy to hear that we just avoided the wait list. Our waiter Adam was very friendly and helpful! ',4);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (3,1,to_date('24-AUG-15','DD-MON-RR'),'A new neighborhood means finding new places to satisfy all my various food cravings. Vietnamese has been taken care of right here.',4);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (2,1,to_date('24-AUG-15','DD-MON-RR'),'Normally this is a go to place for pho when Im in Rockville. But today there was a slight lack in flavor of food. Servers there slack off or often are not friendly as some would like servers to be. 
',4);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (1,1,to_date('24-AUG-15','DD-MON-RR'),'Get the Iced Thai Tea though. It is really good. I would like to come here again for Pho. 
',4);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (1,2,to_date('24-AUG-15','DD-MON-RR'),'I was greatly surprised with Pho Nom Nom. My dining partner had been here two years back and commented on their pho being a disappointment.',4);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (2,2,to_date('24-AUG-15','DD-MON-RR'),'Tip: Knowing how to use chopsticks is an essential skill, as youll need it to handle the noodles and meat (or not :D).',5);
Insert into TESTDB.REVIEW (USER_ID,RESTAURANT_ID,REVIEW_DATE,REVIEW_DES,RATING) values (3,2,to_date('24-AUG-15','DD-MON-RR'),'I strongly recommend trying this place as you will fall in love with their food at once.',5);
REM INSERTING into TESTDB.USERPROFILE
SET DEFINE OFF;
Insert into TESTDB.USERPROFILE (USER_ID,USER_NAME,USER_EMAIL,USER_ZIPCODE,USER_PASS) values (1,'Smith','Smith@gmail.com','2222','12345678');
Insert into TESTDB.USERPROFILE (USER_ID,USER_NAME,USER_EMAIL,USER_ZIPCODE,USER_PASS) values (2,'Tim','Tim@gmail.com','33000','11111111');
Insert into TESTDB.USERPROFILE (USER_ID,USER_NAME,USER_EMAIL,USER_ZIPCODE,USER_PASS) values (3,'Ted','Ted@gmail.com','00000','password');
--------------------------------------------------------
--  DDL for Index SYS_C007438
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."SYS_C007438" ON "TESTDB"."RESTAURANTS" ("RESTAURANT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_REVIEW
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."PK_REVIEW" ON "TESTDB"."REVIEW" ("RESTAURANT_ID", "USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C007437
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."SYS_C007437" ON "TESTDB"."USERPROFILE" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table RESTAURANTS
--------------------------------------------------------

  ALTER TABLE "TESTDB"."RESTAURANTS" ADD PRIMARY KEY ("RESTAURANT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table REVIEW
--------------------------------------------------------

  ALTER TABLE "TESTDB"."REVIEW" ADD CONSTRAINT "PK_REVIEW" PRIMARY KEY ("RESTAURANT_ID", "USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERPROFILE
--------------------------------------------------------

  ALTER TABLE "TESTDB"."USERPROFILE" ADD PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REVIEW
--------------------------------------------------------

  ALTER TABLE "TESTDB"."REVIEW" ADD FOREIGN KEY ("USER_ID")
	  REFERENCES "TESTDB"."USERPROFILE" ("USER_ID") ENABLE;
  ALTER TABLE "TESTDB"."REVIEW" ADD FOREIGN KEY ("RESTAURANT_ID")
	  REFERENCES "TESTDB"."RESTAURANTS" ("RESTAURANT_ID") ENABLE;
