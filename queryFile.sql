--List the contents of the Professor relation in order according to department, then name. 
SELECT * FROM Professor ORDER BY Dept_Code, F_Name, L_Name;
--List the contents of the are qualified to teach relation in alphabetic order according to ClassId. 
SELECT * FROM prof_qual ORDER BY c_ID;
--List the contents of the section relation in alphabetic order according to class, semester, and section number. 
SELECT * FROM Class_Section ORDER BY c_ID, Semester, SecNO;
--For each class that has at least one qualified professor to teach it, list the number of professors qualified to teach the class. 
SELECT c_ID, COUNT(*) FROM prof_qual GROUP BY c_ID;
--For each professor that has taught a class, list the classes they taught and the semester they were taught in. 
SELECT Emp_ID, c_ID, Semester FROM Teaches ORDER BY Emp_ID;