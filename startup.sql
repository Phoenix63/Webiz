CREATE TABLE LIST (
	ID INT AUTO_INCREMENT PRIMARY KEY, 
	TITLE VARCHAR2(200), 
	DESCRIPTION VARCHAR2(500)
);

CREATE TABLE ITEM (
	ID INT AUTO_INCREMENT PRIMARY KEY, 
	CREATION DATE, 
	LAST_MODIFICATION DATE, 
	TITLE VARCHAR2(200), 
	DESCRIPTION VARCHAR2(500),
	LIST_ID INT NOT NULL,
	FOREIGN KEY (LIST_ID) REFERENCES LIST(ID)
);

CREATE ROLE User;
GRANT all ON List TO User;
GRANT all ON Item TO User;

CREATE USER Kay PASSWORD 'dop';
GRANT User TO Kay;

COMMIT;
