DROP TABLE IF EXISTS attachment;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS receipt;
DROP TABLE IF EXISTS onlineuser;
DROP TABLE IF EXISTS committee;
DROP TABLE IF EXISTS economicrequest;
DROP TABLE IF EXISTS economicRequestReview;

CREATE TABLE committee (
                           id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(55) NOT NULL
);

CREATE TABLE onlineuser (
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            email VARCHAR(55) NOT NULL,
                            onlineId VARCHAR(55) NOT NULL UNIQUE
);

CREATE TABLE receipt (
                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         amount DECIMAL(10,2) NOT NULL,
                         committee_id INT NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         description BLOB NOT NULL,
                         onlineuser_id INT NOT NULL,
                         FOREIGN KEY (committee_id) REFERENCES committee(id),
                         FOREIGN KEY (onlineuser_id) REFERENCES onlineuser(id)
);

CREATE TABLE attachment (
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            img BLOB NOT NULL,
                            receipt_id INT NOT NULL,
                            FOREIGN KEY (receipt_id) REFERENCES receipt(id)
);

CREATE TABLE payment (
                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         receipt_id INT NOT NULL,
                         account_number VARCHAR(55) NOT NULL,
                         FOREIGN KEY (receipt_id) REFERENCES receipt(id)
);

CREATE TABLE card (
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      receipt_id INT NOT NULL,
                      card_number VARCHAR(16) NOT NULL,
                      FOREIGN KEY (receipt_id) REFERENCES receipt(id)
);

CREATE TABLE economicrequest (
                                 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 subject VARCHAR(200),
                                 purpose VARCHAR(500),
                                 date DATETIME,
                                 duration VARCHAR(200),
                                 description VARCHAR(1000),
                                 amount DECIMAL(10,2) NOT NULL,
                                 personcount INT,
                                 names VARCHAR(500),
                                 paymentdescription VARCHAR(500),
                                 otherinformation VARCHAR(1000),
                                 onlineuserId VARCHAR(55) NOT NULL,
                                 FOREIGN KEY (onlineuserId) REFERENCES onlineuser(onlineId)
);

CREATE TABLE economicRequestReview (
                                       economicRequestReviewId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       economicRequestId INT NOT NULL,
                                       date DATETIME,
                                       description VARCHAR(1000),
                                       status VARCHAR(55) NOT NULL,
                                       adminId VARCHAR(55) NOT NULL,
                                       FOREIGN KEY (economicRequestId) REFERENCES economicrequest(id),
                                       FOREIGN KEY (adminId) REFERENCES onlineuser(onlineId)
);
