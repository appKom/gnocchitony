CREATE TABLE onlineuser
(
    id       INT         NOT NULL PRIMARY KEY IDENTITY (1,1),
    email    VARCHAR(55) NOT NULL,
    onlineid VARCHAR(55) NOT NULL,
    fullname VARCHAR(55) NOT NULL
);

CREATE TABLE committee
(
    id   INT         NOT NULL PRIMARY KEY IDENTITY (1,1),
    name VARCHAR(55) NOT NULL
);

CREATE TABLE receipt
(
    id            INT            NOT NULL PRIMARY KEY IDENTITY (1,1),
    amount        DECIMAL(10, 2) NOT NULL,
    committee_id  INT            NOT NULL references committee (id),
    name          VARCHAR(255)   NOT NULL,
    description   VARCHAR(500)   NOT NULL,
    createdat     DATETIME       NOT NULL,
    onlineuser_id INT            NOT NULL references onlineuser (id),
    card_number VARCHAR(16) NOT NULL,
    account_number VARCHAR(55) NOT NULL
);

CREATE TABLE attachment
(
    id         INT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    receipt_id INT          NOT NULL references receipt (id),
    name       VARCHAR(255) NOT NULL
);


CREATE TABLE economicrequest
(
    id                 INT            NOT NULL PRIMARY KEY IDENTITY (1,1),
    subject            VARCHAR(55)    NOT NULL,
    purpose            VARCHAR(500)   NOT NULL,
    date               DATE           NOT NULL,
    duration           VARCHAR(255)   NOT NULL,
    description        VARCHAR(500)   NOT NULL,
    amount             DECIMAL(10, 2) NOT NULL,
    personcount        INT            NOT NULL,
    names              VARCHAR(500)   NOT NULL,
    paymentdescription varchar(255)   NOT NULL,
    otherinformation   VARCHAR(500)   NOT NULL,
    createdat          DATETIME       NOT NULL,
    onlineuser_id      INT            NOT NULL references onlineuser (id)
);

CREATE TABLE receiptreview
(
    id            INT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    receipt_id    INT          NOT NULL references receipt (id),
    status        VARCHAR(20)  NOT NULL CHECK (status IN ('APPROVED', 'DENIED')),
    comment       VARCHAR(500) NOT NULL,
    createdat     DATETIME     NOT NULL,
    onlineuser_id INT          NOT NULL references onlineuser (id)
);

CREATE TABLE economicrequestreview
(
    id            INT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    economicrequest_id    INT          NOT NULL references economicrequest (id),
    status        VARCHAR(20)  NOT NULL CHECK (status IN ('APPROVED', 'DENIED')),
    comment       VARCHAR(500) NOT NULL,
    createdat     DATETIME     NOT NULL,
    onlineuser_id INT          NOT NULL references onlineuser (id)
);
