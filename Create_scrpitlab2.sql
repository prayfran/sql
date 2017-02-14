DROP TABLE IF EXISTS students,
                     phone,
                     enrolled,
                     c_section, 
                     u_class,
					 professor,
					 teaches,
					 can_teach;

CREATE TABLE students (
    S_ID        INT             NOT NULL,
    birth_date  DATE            NOT NULL,
    first_name  VARCHAR(14)     NOT NULL,
    last_name   VARCHAR(16)     NOT NULL,
    Major       VARCHAR(2)     NOT NULL,
    PRIMARY KEY (S_ID)
);

CREATE TABLE phone (
    P_NUM       VARCHAR(12)     NOT NULL,
    P_TYPE      CHAR(1)         NOT NULL,
	S_ID        INT             NOT NULL,
	PRIMARY KEY (P_NUM, S_ID),
    FOREIGN KEY (S_ID) REFERENCES students (S_ID) ON DELETE CASCADE
);



CREATE TABLE u_class (
    C_ID        VARCHAR(7)      NOT NULL,
	CREDITS     INT				NOT NULL,
    DES         VARCHAR(50)     NOT NULL,
    PreReq      VARCHAR(6),
    PRIMARY KEY (C_ID)
);

CREATE TABLE c_section (
    C_ID        VARCHAR(7)      NOT NULL,
    SEC_NO      VARCHAR(10)      NOT NULL,
    Semester    VARCHAR(4)      NOT NULL,
	BeginTime	TIME			NOT NULL,
	EndTime		TIME			NOT NULL,
	Days		VARCHAR(3)		NOT NULL,
    Room_NO     VARCHAR(6)      NOT NULL,
    PRIMARY KEY (SEC_NO, C_ID, Semester),
	FOREIGN KEY (C_ID)  REFERENCES u_class (C_ID)  ON DELETE CASCADE

); 

CREATE TABLE enrolled (
   S_ID    		                INT                 NOT NULL,
   C_ID                         VARCHAR(7)          NOT NULL,
   SEC_NO       				VARCHAR(10)          NOT NULL,
   Semester						VARCHAR(4)          NOT NULL,
   Grade_Or_Drop_Date           VARCHAR(10)         NOT NULL,
   FOREIGN KEY (S_ID)  REFERENCES students (S_ID)    ON DELETE CASCADE,
   FOREIGN KEY (C_ID) REFERENCES u_class (C_ID) ON DELETE CASCADE,
   FOREIGN KEY (SEC_NO) REFERENCES c_section (SEC_NO) ON DELETE CASCADE
); 

CREATE TABLE professor (
   EMP_ID    		            VARCHAR(4)                 NOT NULL,
   F_NAME                       VARCHAR(10)          NOT NULL,
   L_NAME       				VARCHAR(10)          NOT NULL,
   DEPT_CODE					VARCHAR(4)          NOT NULL,
   PRIMARY KEY (EMP_ID)
   
); 

CREATE TABLE can_teach(
   EMP_ID 					  VARCHAR(4)          NOT NULL,
   C_ID                       VARCHAR(7)          NOT NULL,
   FOREIGN KEY(EMP_ID) REFERENCES professor (EMP_ID) ON DELETE CASCADE,
   FOREIGN KEY(C_ID) REFERENCES u_class (C_ID) ON DELETE CASCADE
   
);

CREATE TABLE teaches(
   C_ID                       VARCHAR(7)           NOT NULL,
   SEC_NO                     VARCHAR(10) 		   NOT NULL,
   Semester					  VARCHAR(4)           NOT NULL,
   EMP_ID                     VARCHAR(4)           NOT NULL,
   FOREIGN KEY(C_ID) REFERENCES c_section (C_ID) ON DELETE CASCADE,
   FOREIGN KEY(SEC_NO) REFERENCES c_section (SEC_NO) ON DELETE CASCADE,
   FOREIGN KEY(Semester) REFERENCES c_section (Semester) ON DELETE CASCADE,
   FOREIGN KEY(EMP_ID) REFERENCES professor (EMP_ID) ON DELETE CASCADE
); 

CREATE TABLE AUDITS(
   ID                         INT                   NOT NULL AUTO_INCREMENT,
   T_NAME                     VARCHAR(12)           NOT NULL,
   ACTION                     VARCHAR(1)            NOT NULL,
   HOURS                      TIME                  NOT NULL,
   CALENDAR_THING             DATE                  NOT NULL,
   PRIMARY KEY(ID);
   
);


