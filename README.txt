

-----------------------ERRORS TO NOTE------------------------------------------------
A referential integrity issue occured when trying to say that the following professors were qualified for the following non-existant classes:
(204,'CS371'),
(207,'CS431'),

-The following throws an error because the DOB field should not be null
--Add student Tom Servo, CIS major, id = 2030, no phone, date of birth unknown.
--INSERT INTO Student VALUES(2030,'Tom','Servo',null,'CIS');

-The following throws an error because the DOB field should not be null
--Add student Jay Garrick, id = 2020, CS major, no phone, date of birth unknown.
--INSERT INTO Student VALUES(2020,'Jay','Garrick',null,'CS');

-the program fails to delete the class cs451 while its references are still required
--Delete CS451 from the Class table (do not delete the associated sections first).
--DELETE FROM Classes WHERE c_ID= 'CS451';
-----------------------------------------------------------------------------------
- the tables are created by typing "source info.sql;", this will create and populate the tables, and will then create the desired view 
- The view is lovingly named "smarg"
- The desired queries are found by typing "\. queryFile.sql"
- The alterations during the activity phase can be executed by typing "\. activity_cmds.sql"
- the audit table is known as "Changes" because "Audit" is a reserved name
- all output will go to a file named "results.txt"