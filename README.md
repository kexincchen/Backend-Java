# SIMENS-SDE-Intern

## 新闻网站的API设计

### 新闻文章管理

| Header   | api           | Description |
|----------|---------------|-------------|
| `<GET>`  | `/press/<id>` | 获取该id的新闻    |
| `<GET>`  | `/press/all`  | 获取所有新闻      |
| `<POST>` | `/press/new`  | 上传新闻        |
| `<PUT>`  | `/press/<id>` | 修改新闻        |

### 新闻评论管理
| Header     | api             | Description |
|------------|-----------------|-------------|
| `<GET>`    | `/comment`      | 获取所有评论      |
| `<POST>`   | `/comment/add`  | 发布评论        |
| `<DELETE>` | `/comment/<id>` | 删除评论        |

### 用户管理
| Header   | api                          | Description |
|----------|------------------------------|-------------|
| `<PUT>`  | `/user/<id>/update-nickname` | 用户nicheng修改 |
| `<POST>` | `/user/register`             | 用户注册        |

### 广告管理
| Header   | api                | Description |
|----------|--------------------|-------------|
| `<GET>`  | `/ad/by-placement` | 特定类型的广告获取    |
| `<POST>` | `/ad/add`          | 广告上传    |
| `<PUT>`  | `/ad/<id>/click`   | 广告点击反馈    |


注意，评论内广告和新闻内嵌广告这两种类型的广告，
在上述“新闻文章”接口的返回内容中直接包含。