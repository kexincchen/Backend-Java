DROP TABLE IF EXISTS news;

CREATE TABLE News
(
    NewsID BIGINT AUTO_INCREMENT,
    Title VARCHAR(255),
    Content TEXT,
    CoverImageUrl VARCHAR(255),
    PublishDatetime DATETIME,
    Author VARCHAR(100),
    ViewCount INT DEFAULT 0,
    FavoriteCount INT DEFAULT 0,
    ShareCount INT DEFAULT 0,
    PaidPromotion BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (NewsID)
);