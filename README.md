# Java Spring Boot 新闻网站

## API设计

### 新闻文章管理

| Header   | API                 | Description | Checked | 需Admin权限 | 
|----------|---------------------|-------------|-------------|----------|
| `<GET>`  | `/press/<id>`       | 获取该id的新闻    |&check;   |          |
| `<GET>`  | `/press/all`        | 获取所有新闻      |&check;   |          |
| `<POST>` | `/press/add`        | 上传新闻        |&check;   | &check;  |
| `<PUT>`  | `/press/<id>`       | 修改新闻        |&check;   | &check;  |
| `<PUT>` | `/press/<id>/click` | 用户点击新闻      |  |  |
| `<PUT>` | `/press/<id>/share` | 用户分享新闻      |  |  |
| `<PUT>` | `/press/<id>/like`  | 用户收藏新闻      |  |  |

### 新闻评论管理
| Header     | API                     | Description | Checked |需Admin权限 |
|------------|-------------------------|-------------|-------|----------|
| `<GET>`    | `/comment/all`          | 获取所有评论      | &check; | |
| `<POST>`   | `/comment/add`          | 发布评论        | &check;  | |
| `<DELETE>` | `/comment/<id>`         | 删除评论        | &check;  | |
| `<POST>`   | `/comment/reply/<id>`   | 回复评论        |  | |
| `<PUT>`    | `/comment/<id>/like`    | 点赞评论        |  | |
| `<PUT>`    | `/comment/<id>/dislike` | 点踩评论        |  | |

### 用户管理
| Header   | API                          | Description | Checked |需Admin权限 |
|----------|------------------------------|-------------|---------|---------|
| `<PUT>`  | `/user/<id>/update-nickname` | 用户昵称修改      | &check; | |
| `<POST>` | `/user/register`             | 用户注册        | &check; | |
 | `<PUT>`  | `/user/<id>/authorize`        |  赋予用户管理员权限 | &check; |&check; |
 | `<GET>` | `/user/by-username` | 获取该用户名的用户 | &check; | |

### 广告管理
| Header   | API                | Description | Checked  |需Admin权限 |
|----------|--------------------|-------------|----------|------|
| `<GET>`  | `/ad/by-placement` | 特定类型的广告获取    |  &check;  | |
| `<POST>` | `/ad/add`          | 广告上传    | &check;  | &check;  | 
| `<PUT>`  | `/ad/<id>/click`   | 广告点击反馈    | &check;  | |

注意，评论内广告和新闻内嵌广告这两种类型的广告，
在上述“新闻文章”接口的返回内容中直接包含。

### 测试接口
| Header   | API               | Description   | Checked  |需Admin权限 |
|----------|-------------------|---------------|----------|------|
| `<POST>` | `/api/auth/login` | 用户密码登陆获取JWT令牌 |  &check;  | |
| `<GET>`  | `/admin/hello`    | 测试admin权限     |  &check;  | &check; |

