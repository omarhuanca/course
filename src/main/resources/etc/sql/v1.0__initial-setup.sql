/*==============================================================*/
/* Table: Student (tsst)                                        */
/*==============================================================*/
CREATE TABLE tsst_student (
  tsst_uid              BIGINT                  NOT NULL AUTO_INCREMENT,
  tsst_status           INTEGER   DEFAULT 1     NOT NULL,
  tsst_first_name       VARCHAR(70)             NOT NULL,
  tsst_last_name        VARCHAR(70)             NOT NULL,
  tsst_address          VARCHAR(500)                NULL,
  tsst_mobile_phone     VARCHAR(11)                 NULL,
  tsst_create_date      TIMESTAMP DEFAULT NOW() NOT NULL,
  tsst_last_update_date TIMESTAMP DEFAULT NOW() NOT NULL,
  PRIMARY KEY (tsst_uid)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

/*==============================================================*/
/* Table: Category (tsct)                                       */
/*==============================================================*/
CREATE TABLE tsct_category (
  tsct_uid              BIGINT                  NOT NULL AUTO_INCREMENT,
  tsct_status           INTEGER   DEFAULT 1     NOT NULL,
  tsct_name             VARCHAR(30)             NOT NULL,
  tsct_create_date      TIMESTAMP DEFAULT NOW() NOT NULL,
  tsct_last_update_date TIMESTAMP DEFAULT NOW() NOT NULL,
  PRIMARY KEY (tsct_uid)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

/*==============================================================*/
/* Table: Course (tscr)                                         */
/*==============================================================*/
CREATE TABLE tscr_course (
  tscr_uid              BIGINT                  NOT NULL AUTO_INCREMENT,
  tscr_tsct_uid         BIGINT                  NOT NULL,
  tscr_status           INTEGER   DEFAULT 1     NOT NULL,
  tscr_price            NUMERIC(9,2)            NOT NULL,
  tscr_title            VARCHAR(30)             NOT NULL,
  tscr_description      VARCHAR(500)            NOT NULL,
  tscr_create_date      TIMESTAMP DEFAULT NOW() NOT NULL,
  tscr_last_update_date TIMESTAMP DEFAULT NOW() NOT NULL,
  PRIMARY KEY (tscr_uid),
  CONSTRAINT fk_tscr_tsct_uid FOREIGN KEY (tscr_tsct_uid) REFERENCES tsct_category(tsct_uid) ON UPDATE CASCADE ON DELETE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

/*==============================================================*/
/* Table: Enroll (tsnr)                                         */
/*==============================================================*/
CREATE TABLE tsnr_enroll (
  tsnr_uid              BIGINT                  NOT NULL AUTO_INCREMENT,
  tsnr_tsst_uid         BIGINT                  NOT NULL,
  tsnr_tscr_uid         BIGINT                  NOT NULL,
  tsnr_status           INTEGER   DEFAULT 1     NOT NULL,
  tsnr_create_date      TIMESTAMP DEFAULT NOW() NOT NULL,
  tsnr_last_update_date TIMESTAMP DEFAULT NOW() NOT NULL,
  PRIMARY KEY (tsnr_uid),
  CONSTRAINT fk_tsnr_tsst_uid FOREIGN KEY (tsnr_tsst_uid) REFERENCES tsst_student(tsst_uid) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_tsnr_tscr_uid FOREIGN KEY (tsnr_tscr_uid) REFERENCES tscr_course(tscr_uid) ON UPDATE CASCADE ON DELETE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

/* **********************************************
 * REFERENCE DATA                               *
 ************************************************/
INSERT INTO tsct_category(tsct_uid, tsct_status, tsct_name)
 VALUES (10, 1, 'UX/UI'),
        (11, 1, 'Programming'),
        (12, 1, 'DevOps'),
        (13, 1, 'QA');



INSERT INTO tsst_student(tsst_uid, tsst_status, tsst_first_name, tsst_last_name, tsst_address, tsst_mobile_phone)
 VALUES (20, 1, 'mauricio', 'morales', 'Av Suecia', '59172731406'),
        (21, 1, 'pablo', 'flores', '', '');


INSERT INTO tscr_course(tscr_uid, tscr_tsct_uid, tscr_status, tscr_price, tscr_title, tscr_description)
 VALUES (30, 11, 1, 15.20, 'React Basic', 'User state of Redux'),
        (31, 12, 1, 60.99, 'Docker advace', 'Use command to generate application alone on Java, Python and NodeJS');



INSERT INTO tsnr_enroll(tsnr_uid, tsnr_tsst_uid, tsnr_tscr_uid, tsnr_status)
 VALUES (40, 20, 30, 1),
        (41, 20, 31, 1);


