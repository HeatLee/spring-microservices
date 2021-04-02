ALTER TABLE script
MODIFY command_name varchar(30) NOT NULL;

ALTER TABLE script
MODIFY modified_at timestamp NULL;

ALTER TABLE script
MODIFY filename_extension varchar(10) NOT NULL;

ALTER TABLE script
MODIFY description varchar(150) NOT NULL;