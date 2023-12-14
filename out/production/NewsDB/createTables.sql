CREATE TABLE News (
    NewsID INT AUTO_INCREMENT,
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
    CommentID INT AUTO_INCREMENT,
    UserID INT,
    NewsID INT,
    Content TEXT,
    CommentTime DATETIME,
    ReferenceCommentID INT,
    NumLikes INT,
    NumDislikes INT,
    PRIMARY KEY (CommentID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (NewsID) REFERENCES News(NewsID),
    FOREIGN KEY (ReferenceCommentID) REFERENCES Comments(CommentID)
);

CREATE TABLE Users (
    UserID INT AUTO_INCREMENT,
    PhoneNumber VARCHAR(15),
    AvatarUrl VARCHAR(255),
    Nickname VARCHAR(50),
    NewsPreferences TEXT,  -- JSON格式，存储用户的新闻偏好
    LastLogin DATETIME,
    PRIMARY KEY (UserID)
);

CREATE TABLE UserFavorites (
    UserFavID Int AUTO_INCREMENT,
    UserID INT,
    NewsID INT,
    PRIMARY KEY (UserFavID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (NewsID) REFERENCES News(NewsID)
);

CREATE TABLE UserBrowsingHistory (
    UserBrowsingID INT AUTO_INCREMENT,
    UserID INT,
    NewsID INT,
    PRIMARY KEY (UserBrowsingID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (NewsID) REFERENCES News(NewsID)
);

CREATE TABLE CommentHistory (
    UserCommentID INT AUTO_INCREMENT,
    CommentID INT,
    UserID INT,
    PRIMARY KEY (UserCommentID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (CommentID) REFERENCES Comments(CommentID)
);

CREATE TABLE Advertiser (
    AdvertiserID INT AUTO_INCREMENT,
    Name VARCHAR(255),
    PRIMARY KEY (AdvertiserID)
);

CREATE TABLE Advertisements (
    AdID INT AUTO_INCREMENT,
    Title VARCHAR(255),
    TextContent TEXT,
    ImageUrl VARCHAR(255),
    Placement ENUM('splash', 'popup', 'homepage', 'comment', 'article', 'article_end', 'related_news'),
    AdvertiserID INT,
    ValidUntil DATETIME,
    RegionRestriction TEXT,
    DeviceRestriction TEXT,
    ClickCount INT DEFAULT 0,
    ExposureCount INT DEFAULT 0,
    Weight INT DEFAULT 0,
    PRIMARY KEY (AdID),
    FOREIGN KEY (AdvertiserID) REFERENCES Advertiser(AdvertiserID)
);

