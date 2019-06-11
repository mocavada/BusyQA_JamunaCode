INSERT INTO teams(name) VALUES('TEAM_SALES');
INSERT INTO teams(name) VALUES('TEAM_ACCOUNTS');
INSERT INTO teams(name) VALUES('TEAM_ADMIN');
INSERT INTO teams(name) VALUES('TEAM_UNASSIGNED');


INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT into users(email, name, password, roles, status, status_as_of_day,teams,username) VALUES ('administrator@busyqa.com', 'administrator','$2a$10$R8eL2njsXCpCc/ez8pUpPeJReiAe7UYeXpooENPD7trY5Wu/.OYXG', NULL,'YES',NULL,NULL,'administrator');
INSERT into user_team_role(roles_id,teams_id,user_id) VALUES(3,3,1);


//pw passoword1234
INSERT into users(email, name, password, roles, status, status_as_of_day,teams,username) VALUES ('aminmarc@mail.ca', 'adminmarc','$2a$10$lI/ysVedoV.SzpkJMjnwGey1WC4V/gxv./3kJI01uX9eEIhy2mhCK', NULL,'YES',NULL,NULL,'adminmarc');
INSERT into user_team_role(roles_id,teams_id,user_id) VALUES(3,3,2);

INSERT into users(email, name, password, roles, status, status_as_of_day,teams,username) VALUES ('salesmarc@mail.ca', 'salesmarc','$2a$10$lI/ysVedoV.SzpkJMjnwGey1WC4V/gxv./3kJI01uX9eEIhy2mhCK', NULL,'YES',NULL,NULL,'salesmarc');
INSERT into user_team_role(roles_id,teams_id,user_id) VALUES(8,3,4);