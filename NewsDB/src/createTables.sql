CREATE TABLE News (
    NewsID BIGINT AUTO_INCREMENT,
    Title VARCHAR(255),
    CoverImageUrl VARCHAR(255),
    PublishDatetime DATETIME,
    Author VARCHAR(100),
    Body TEXT,
    ViewCount INT DEFAULT 0,
    FavoriteCount INT DEFAULT 0,
    ShareCount INT DEFAULT 0,
    PaidPromotion BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (NewsID)
);

CREATE TABLE Comments (
    CommentID BIGINT AUTO_INCREMENT,
    UserID BIGINT,
    NewsID BIGINT,
    Content TEXT,
    CommentTime DATETIME,
    ReferenceCommentID BIGINT,
    NumLikes INT,
    NumDislikes INT,
    PRIMARY KEY (CommentID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (NewsID) REFERENCES News(NewsID),
    FOREIGN KEY (ReferenceCommentID) REFERENCES Comments(CommentID)
);

CREATE TABLE Users (
    UserID BIGINT AUTO_INCREMENT,
    PhoneNumber VARCHAR(15),
    AvatarUrl VARCHAR(255),
    Nickname VARCHAR(50),
    NewsPreferences TEXT,  -- JSON格式，存储用户的新闻偏好
    LastLogin DATETIME,
    PRIMARY KEY (UserID)
);

CREATE TABLE UserFavorites (
    UserFavID BIGINT AUTO_INCREMENT,
    UserID BIGINT,
    NewsID BIGINT,
    PRIMARY KEY (UserFavID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (NewsID) REFERENCES News(NewsID)
);

CREATE TABLE UserBrowsingHistory (
    UserBrowsingID BIGINT AUTO_INCREMENT,
    UserID BIGINT,
    NewsID BIGINT,
    PRIMARY KEY (UserBrowsingID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (NewsID) REFERENCES News(NewsID)
);

CREATE TABLE CommentHistory (
    UserCommentID BIGINT AUTO_INCREMENT,
    CommentID BIGINT,
    UserID BIGINT,
    PRIMARY KEY (UserCommentID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (CommentID) REFERENCES Comments(CommentID)
);

CREATE TABLE Advertiser (
    AdvertiserID BIGINT AUTO_INCREMENT,
    Name VARCHAR(255),
    PRIMARY KEY (AdvertiserID)
);

CREATE TABLE Advertisements (
    AdID BIGINT AUTO_INCREMENT,
    Title VARCHAR(255),
    TextContent TEXT,
    ImageUrl VARCHAR(255),
    Placement ENUM('splash', 'popup', 'homepage', 'comment', 'article', 'article_end', 'related_news'),
    AdvertiserID BIGINT,
    ValidUntil DATETIME,
    RegionRestriction TEXT,
    DeviceRestriction TEXT,
    ClickCount INT DEFAULT 0,
    ExposureCount INT DEFAULT 0,
    Weight INT DEFAULT 0,
    PRIMARY KEY (AdID),
    FOREIGN KEY (AdvertiserID) REFERENCES Advertiser(AdvertiserID)
);

