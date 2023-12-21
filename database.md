## Background

这次是完成一个功能类似 MSN 的技术新闻门户的完整后端系统。
在这一阶段, 需要设计一套数据库表结构。
其中必须包含的表有：用户表、内容表、评论表、广告表。

用户表需要记录用户的基本信息，例如：uid、绑定的手机号、头像、昵称、收
藏列表、新闻偏好、浏览历史记录、评论历史记录、最后一次登录时间等。

内容表用来记录门户新闻内容。必须包含以下字段：新闻 id、标题、封面图、
投放日期时间、作者、新闻正文、浏览量、收藏量、转发量、付费推广标记等。

评论表用来记录新闻下的评论内容，必须包含以下字段：用户 id、评论 id、新闻
id、评论内容、评论时间、引用评论 id、点赞数、点踩数等。

广告是新闻平台营收的最重要来源之一。该表用来记录平台各处投放的广告内
容，必须包含以下字段：标题、文本内容、图像链接、投放位置（splash、弹
窗、首页列表内嵌广告、评论内嵌广告、新闻文章内嵌广告、新闻文章末尾广
告、相关新闻推荐内嵌广告）、投放方（甲方）标识符、投放有效期、地区限
制、设备限制、点击量、曝光量、投放权重等。

数据类型和元素名需要自行判断，并且欢迎参考互联网上成熟的设计方案进行
设计。

进阶：评论表的内容适合使用非关系型数据库进行存储，推荐同时使用
MongoDB 进行配合维护。

## 创建表单

Tips: 
- 表的列名最好使用下划线命名法，因为MySQL在windows下，忽略大小写。
- ID字段一般使用BigInt来定义

### 用户表

```sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT,
    phone_number VARCHAR(15),
    avatar_url VARCHAR(255),
    nickname VARCHAR(50),
    news_preferences TEXT,  -- JSON格式，存储用户的新闻偏好
    last_login DATETIME,
    PRIMARY KEY (user_id)
);

CREATE TABLE UserFavorites (
    UserFavID Int AUTO_INCREMENT,
    UserID INT,
    NewsID INT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (NewsID) REFERENCES News(NewsID)
);

CREATE TABLE UserBrowsingHistory (
    UserID INT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

```

### 内容表

```sql
CREATE TABLE News (
    NewsID INT AUTO_INCREMENT,
    Title VARCHAR(255),
    CoverImageUrl VARCHAR(255),
    PublishDatetime DATETIME,
    Author VARCHAR(100),
    Body TEXT,
    ViewCount INT DEFAULT 0,
    FavoriteCount INT DEFAULT 0,  -- 这种索引信息是否应当记录在表中，还是每一次查看时索引？（可能得看查看频率是否高再进行选择？）
    ShareCount INT DEFAULT 0,
    PaidPromotion BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (NewsID)
);
```

### 评论表

```sql
CREATE TABLE Comments (
    CommentID INT,
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

CREATE TABLE CommentHistory (
    UserCommentID INT AUTO_INCREMENT,
    CommentID INT,
    UserID INT,
    PRIMARY KEY (UserCommentID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (CommentID) REFERENCES Comments(CommentID)
);
```

非关系型数据库实现：

```json
{
    "_id": ObjectId("..."),
    "user_id": 123,
    "comment_id": 456,
    "news_id": 789,
    "content": "这是一条评论",
    "comment_time": ISODate("..."),
    "reference_comment_id": 321,
    "likes": 100,
    "dislikes": 5
}
```

### 广告表

```sql
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


CREATE TABLE Advertiser (
    AdvertiserID INT AUTO_INCREMENT,
    PRIMARY KEY (AdvertiserID)
);
```

## 常见场景及其 SQL 指令

- 通过邮箱创建用户

```sql
INSERT INTO Users (Email, Nickname, LastLogin)
VALUES ('user@example.com', 'nickname', NOW());
```

- 用户编辑自己的昵称等账号信息

```sql
UPDATE Users
SET Nickname = 'new_nickname', AvatarUrl = 'new_avatar_url'
WHERE uid = 1;
```

- 用户登陆

```sql
SELECT * FROM Users
WHERE Email = 'user@example.com';
```

- 用户获取主页新闻列表

```sql
SELECT * FROM News
ORDER BY PublishDatetime DESC
LIMIT 10;
```

- 用户按分区浏览新闻列表

```sql
SELECT * FROM News
WHERE Category = 'technology'
ORDER BY PublishDatetime DESC;
```

- 用户按标题和内容搜索新闻列表

```sql
SELECT * FROM News
WHERE Title LIKE '%关键词%' OR Body LIKE '%关键词%';
```

- 用户访问新闻，并且加载新闻下对应的评论

```sql
-- 增加浏览量
UPDATE News
SET ViewCount = ViewCount + 1
WHERE NewsID = 1;  -- 假设新闻ID是1

-- 加载新闻内容
SELECT * FROM News
WHERE NewsID = 1;

-- 加载评论（MongoDB操作，不包含在此SQL指令中）
```

- 用户给新闻进行收藏、分享

```sql
-- 收藏新闻
INSERT INTO UserFavorites (UserID, NewsID)
VALUES (1, 1);  -- 假设用户ID是1，新闻ID是1
```

- 用户对新闻评论

```sql
INSERT INTO Comments (UserID, NewsID, Content)
VALUES (1, 1, "这是一条评论");
```

- 用户对评论进行点赞/点踩

```sql
-- 点赞
UPDATE Comments
SET NumLikes = NumLikes + 1
WHERE CommentID = 1;

-- 点踩
UPDATE Comments
SET NumDislikes = NumDislikes + 1
WHERE CommentID = 1;
```

> 是否需要记录是哪个用户点了赞？

- 用户删除自己的评论

```sql
DELETE FROM Comments WHERE UserID = 123 AND CommentID = 1;  -- 删除指定的评论
```

- 用户在个人页中观看自己的评论历史

```sql
SELECT * FROM Comments
WHERE UserID = 1
ORDER BY CommentTime DESC;
```

- 用户在个人页中观看自己的浏览历史

```sql
SELECT n.* FROM News n
JOIN UserBrowsingHistory ubh ON n.NewsID = ubh.NewsID
WHERE ubh.UserID = 1;  -- 假设用户ID是1
```

- （模拟）投放新闻

```sql
INSERT INTO News (Title, CoverImageUrl, PublishDatetime, Author, Body)
VALUES ('新闻标题', '封面图链接', NOW(), '作者', '新闻正文');
```

- 按照内容类

```sql
SELECT * FROM Advertisements
WHERE Placement = 'homepage';  -- 假设是获取首页广告
```

- 投放广告

```sql
INSERT INTO Advertisements (Title, TextContent, ImageUrl, Placement, AdvertiserID, ValidUntil)
VALUES ('广告标题', '广告文本内容', '图像链接', 'homepage', 1, '2023-12-31');  -- 假设广告投放方ID是1
```

## Questions

- 如何关联 MySQL 和 MongoDB？
- 用户账号的密码该如何存储？用什么格式？
- 用户偏好是什么样的格式？是多选新闻的类别吗？
