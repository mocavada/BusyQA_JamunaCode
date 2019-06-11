INSERT INTO teams(name) VALUES('TEAM_SALES');
INSERT INTO teams(name) VALUES('TEAM_ACCOUNTS');
INSERT INTO teams(name) VALUES('TEAM_ADMIN');
INSERT INTO teams(name) VALUES('TEAM_UNASSIGNED');
INSERT INTO teams(name) VALUES('TEAM_CLIENT');

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_CLIENT');


INSERT INTO client_status(name) VALUES('LEADS');
INSERT INTO client_status(name) VALUES('INTERN');
INSERT INTO client_status(name) VALUES('ALUMINI');
INSERT INTO client_status(name) VALUES('STUDENT');


INSERT INTO payment_status(name) VALUES('ON_SCHEDULE');
INSERT INTO payment_status(name) VALUES('DUE_FOR_A_WEEK');
INSERT INTO payment_status(name) VALUES('SERIOUS_OVER_DUE');
INSERT INTO payment_status(name) VALUES('UNSCHEDULED');


INSERT INTO payment_plan(name) VALUES('FULL_AMOUNT__FULL_PAY');
INSERT INTO payment_plan(name) VALUES('INSENTIVE__FULL_PAY');
INSERT INTO payment_plan(name) VALUES('FULL_AMOUNT__PLAN__CREDIT_AUTO');
INSERT INTO payment_plan(name) VALUES('INSENTIVE__PLAN__CREDIT_AUTO');
INSERT INTO payment_plan(name) VALUES('FULL_AMOUNT__PLAN__MANUAL');
INSERT INTO payment_plan(name) VALUES('INSENTIVE__PLAN__MANUAL');
INSERT INTO payment_plan(name) VALUES('UNSCHEDULED');

INSERT INTO client_course(name, amount) VALUES('AUTOMATION_TESTING',2000);
INSERT INTO client_course(name, amount) VALUES('AUTOMATION_TESTING_ONLINE',1000);
INSERT INTO client_course(name, amount) VALUES('BUSINESS_ANALYSIS',2500);
INSERT INTO client_course(name, amount) VALUES('BIGDATA_DATA_SCIENCE',1000);
INSERT INTO client_course(name, amount) VALUES('SCRUM_MASTER',2200);
INSERT INTO client_course(name, amount) VALUES('FULL_STACK_JAVA_DEV',3500);
INSERT INTO client_course(name, amount) VALUES('PERFORMANCE_TESTING',1000);
INSERT INTO client_course(name, amount) VALUES('NOT_DECIDED',0);

INSERT into users(email, name, password, roles, status, status_as_of_day,teams,username) VALUES ('administrator@busyqa.com', 'administrator','$2a$10$R8eL2njsXCpCc/ez8pUpPeJReiAe7UYeXpooENPD7trY5Wu/.OYXG', NULL,'YES',NULL,NULL,'administrator');
INSERT into user_team_role(roles_id,teams_id,user_id) VALUES(3,3,1);

SELECT * FROM client_course;