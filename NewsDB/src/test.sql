SHOW TABLES;
--
-- INSERT INTO News (Title, CoverImageUrl, PublishDatetime, Author, Body)
-- VALUES ('这是新闻标题', '这是封面图链接', NOW(), '这是作者', '这是新闻正文');

-- DELETE FROM Comments;

-- SELECT * FROM Comments;

-- INSERT INTO Comments (Content, CommentTime)
-- VALUES ('Here is comment 1', NOW());

-- SET FOREIGN_KEY_CHECKS = 0; -- For MySQL, disables foreign key checks
--
-- DROP TABLE Advertisements, Advertiser, CommentHistory, Comments, News, UserBrowsingHistory, UserFavorites, Users; -- List all tables
--
-- SET FOREIGN_KEY_CHECKS = 1; -- Re-enable foreign key checks

-- SELECT * FROM Advertisements;


-- ALTER TABLE News
-- ADD COLUMN AdID BIGINT;
--
-- ALTER TABLE News
-- ADD CONSTRAINT AdID
-- FOREIGN KEY (AdID) REFERENCES Advertisements(AdID);

-- SELECT * FROM News;


-- SELECT * FROM Advertisements WHERE newsid = 1;

-- ALTER TABLE Users
-- ADD COLUMN Password VARCHAR(255);
--
-- ALTER TABLE Users
-- ADD COLUMN Role VARCHAR(10);

INSERT INTO Users (Nickname, Password, Role)
VALUES ('user', 'user', 'USER');

INSERT INTO Users (Nickname, Password, Role)
VALUES ('admin', 'admin', 'ADMIN');

    SELECT * FROM Users;