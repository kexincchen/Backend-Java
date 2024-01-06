# SIMENS-SDE-Intern

## 新闻网站的API设计

### 新闻文章管理

| Header   | api           | Description | Checked |
|----------|---------------|-------------|-------------|
| `<GET>`  | `/press/<id>` | 获取该id的新闻    |&check;   |
| `<GET>`  | `/press/all`  | 获取所有新闻      |&check;   |
| `<POST>` | `/press/add`  | 上传新闻        |&check;   |
| `<PUT>`  | `/press/<id>` | 修改新闻        |&check;   |

### 新闻评论管理
| Header     | api             | Description | Checked |
|------------|-----------------|-------------|--------|
| `<GET>`    | `/comment/all`  | 获取所有评论      | &check;  |
| `<POST>`   | `/comment/add`  | 发布评论        | &check;   |
| `<DELETE>` | `/comment/<id>` | 删除评论        | &check;   |

### 用户管理
| Header   | api                          | Description | Checked |
|----------|------------------------------|-------------|---------|
| `<PUT>`  | `/user/<id>/update-nickname` | 用户昵称修改      | &check; |
| `<POST>` | `/user/register`             | 用户注册        | &check; |

### 广告管理
| Header   | api                | Description | Checked  |
|----------|--------------------|-------------|----------|
| `<GET>`  | `/ad/by-placement` | 特定类型的广告获取    |  &check;  |
| `<POST>` | `/ad/add`          | 广告上传    | &check;  |
| `<PUT>`  | `/ad/<id>/click`   | 广告点击反馈    | &check;  |


注意，评论内广告和新闻内嵌广告这两种类型的广告，
在上述“新闻文章”接口的返回内容中直接包含。