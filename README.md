# SimpleShop Dynamic Web Project

一個適合 Java Web 初學者練習的「簡易購物網站」範例，使用 **JDK 17 + MySQL 8.0 + Eclipse Dynamic Web Project + Tomcat 10.0 + JSP/Servlet** 製作，後端依照 **MVC + DAO Pattern** 分層設計。

本專案適合作為 Java Web、JDBC、Servlet、JSP、MVC、DAO、Session 購物車的教學範例，也適合上傳到 GitHub 作為課堂示範專案。

---

## 專案功能

### 會員管理

- 會員登入
- 會員列表查詢
- 新增會員
- 修改會員
- 刪除會員
- 會員密碼使用 SHA-256 教學版雜湊

### 商品與購物車

- 商品列表
- 加入購物車
- 查看購物車
- 修改購物車數量
- 移除購物車品項
- 清空購物車 / 結帳示範

---

## 開發環境

| 項目 | 版本 / 工具 |
|---|---|
| Java | JDK 17 |
| Database | MySQL 8.0 |
| IDE | Eclipse IDE for Enterprise Java and Web Developers |
| Project Type | Dynamic Web Project |
| Web Server | Apache Tomcat 10.0 |
| View | JSP |
| Backend | Servlet + JDBC |
| Architecture | MVC + DAO Pattern |
| Servlet API | Jakarta Servlet 5.x |

> 注意：Tomcat 10 使用 `jakarta.*` 套件，不是舊版 Tomcat 9 的 `javax.*`。

---

## 專案架構

```text
SimpleShop_DynamicWebProject
├─ sql
│  └─ simple_shop.sql
├─ src/main/java/com/codebyx/shop
│  ├─ entity
│  │  ├─ Member.java
│  │  ├─ Product.java
│  │  └─ CartItem.java
│  ├─ dao
│  │  ├─ MemberDAO.java
│  │  ├─ ProductDAO.java
│  │  └─ impl
│  │     ├─ MemberDAOImpl.java
│  │     └─ ProductDAOImpl.java
│  ├─ controller
│  │  ├─ LoginController.java
│  │  ├─ LogoutController.java
│  │  ├─ MemberController.java
│  │  ├─ ProductController.java
│  │  └─ CartController.java
│  └─ util
│     ├─ DBUtil.java
│     └─ PasswordUtil.java
└─ src/main/webapp
   ├─ index.jsp
   ├─ assets
   │  ├─ css/style.css
   │  └─ img/*.svg
   └─ WEB-INF
      ├─ web.xml
      ├─ lib
      │  └─ README.txt
      └─ views
         ├─ login.jsp
         ├─ member-list.jsp
         ├─ member-form.jsp
         ├─ product-list.jsp
         ├─ cart.jsp
         ├─ message.jsp
         └─ common
            ├─ header.jsp
            └─ footer.jsp
```

---

## MVC + DAO Pattern 說明

本專案將程式分成以下幾層：

| 分層 | 套件 / 位置 | 說明 |
|---|---|---|
| Entity | `com.codebyx.shop.entity` | 對應資料表或系統資料模型 |
| DAO Interface | `com.codebyx.shop.dao` | 定義資料存取方法 |
| DAO Impl | `com.codebyx.shop.dao.impl` | 使用 JDBC 實作 SQL 操作 |
| Controller | `com.codebyx.shop.controller` | 接收 HTTP Request，處理流程控制 |
| Util | `com.codebyx.shop.util` | 資料庫連線與密碼工具 |
| View | `WEB-INF/views/*.jsp` | JSP 顯示頁面 |

DAO Pattern 的優點是將 SQL 與商業流程分離，未來若要改成 JPA、MyBatis 或 Spring Data JDBC，可以降低 Controller 修改幅度。

---

## 資料庫設定

### 1. 建立資料庫

使用 MySQL Workbench 或命令列執行：

```sql
source sql/simple_shop.sql;
```

或直接打開以下檔案，整份 SQL 執行：

```text
sql/simple_shop.sql
```

SQL 會建立：

```text
Database: simple_shop
Table 1: members
Table 2: products
```

### 2. 預設 MySQL 連線設定

```text
Database: simple_shop
User: root
Password: root
```

若你的 MySQL 密碼不是 `root`，請修改：

```text
src/main/java/com/codebyx/shop/util/DBUtil.java
```

```java
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/simple_shop?useSSL=false&serverTimezone=Asia/Taipei&characterEncoding=utf8";
private static final String JDBC_USER = "root";
private static final String JDBC_PASSWORD = "root";
```

---

## 加入 MySQL JDBC Driver

本 ZIP 沒有內建 MySQL JDBC Driver，請自行下載 `mysql-connector-j-8.x.x.jar`，並放到：

```text
src/main/webapp/WEB-INF/lib/
```

放入後，請在 Eclipse 中對專案按右鍵：

```text
Refresh
```

再重新部署到 Tomcat。

---

## 匯入 Eclipse 執行

### 1. 匯入專案

1. 解壓縮專案 ZIP。
2. 開啟 Eclipse。
3. 選擇：

```text
File > Import > Existing Projects into Workspace
```

4. 選擇 `SimpleShop_DynamicWebProject` 資料夾。
5. 匯入完成後，確認專案沒有紅色錯誤。

### 2. 設定 Tomcat 10

若尚未設定 Tomcat：

```text
Window > Preferences > Server > Runtime Environments > Add
```

選擇：

```text
Apache Tomcat v10.0
```

並指定你的 Tomcat 安裝路徑。

### 3. 指定 Targeted Runtime

若專案出現 Runtime 錯誤，請設定：

```text
專案右鍵 > Properties > Targeted Runtimes > 勾選 Apache Tomcat v10.0
```

### 4. 設定 Java Compiler

確認 Java Compiler 為 17：

```text
專案右鍵 > Properties > Java Compiler > Compiler compliance level: 17
```

### 5. 啟動專案

```text
專案右鍵 > Run As > Run on Server
```

選擇 Tomcat 10 後啟動。

---

## 執行網址

預設首頁：

```text
http://localhost:8080/SimpleShop_DynamicWebProject/
```

主要功能網址：

| 功能 | URL |
|---|---|
| 首頁 | `/` |
| 商品列表 | `/product/list` |
| 購物車 | `/cart/view` |
| 會員登入 | `/login` |
| 會員登出 | `/logout` |
| 會員列表 | `/member/list` |
| 新增會員 | `/member/create` |

完整範例：

```text
http://localhost:8080/SimpleShop_DynamicWebProject/product/list
http://localhost:8080/SimpleShop_DynamicWebProject/login
http://localhost:8080/SimpleShop_DynamicWebProject/member/list
http://localhost:8080/SimpleShop_DynamicWebProject/cart/view
```

---

## 測試帳號

| 身分 | 帳號 | 密碼 |
|---|---|---|
| 管理員 | `admin` | `admin123` |
| 一般會員 | `test` | `test123` |

---

## Servlet URL Mapping

本專案在 `WEB-INF/web.xml` 中設定 Servlet 對應路徑：

| Controller | URL Pattern | 功能 |
|---|---|---|
| `LoginController` | `/login` | 會員登入 |
| `LogoutController` | `/logout` | 會員登出 |
| `MemberController` | `/member/*` | 會員 CRUD |
| `ProductController` | `/product/*` | 商品列表 |
| `CartController` | `/cart/*` | 購物車操作 |

---

## GitHub 上傳方式

### 方式一：使用 Git 指令

進入專案資料夾：

```bash
cd SimpleShop_DynamicWebProject
```

初始化 Git：

```bash
git init
```

加入檔案：

```bash
git add .
```

建立第一次 commit：

```bash
git commit -m "Initial commit: SimpleShop Dynamic Web Project"
```

設定 GitHub 遠端 repository：

```bash
git remote add origin https://github.com/你的帳號/SimpleShop_DynamicWebProject.git
```

推送到 GitHub：

```bash
git branch -M main
git push -u origin main
```

### 方式二：使用 GitHub 網頁上傳

1. 到 GitHub 建立新 repository。
2. Repository 名稱可用：

```text
SimpleShop_DynamicWebProject
```

3. 不要勾選自動產生 README，因為本專案已經有 `README.md`。
4. 點選 `uploading an existing file`。
5. 上傳整個專案內容。
6. Commit changes。

---

## 建議 `.gitignore`

若要上傳 GitHub，建議新增 `.gitignore`，避免把編譯檔與暫存檔上傳：

```gitignore
# Java compiled files
*.class

# Eclipse build output
/bin/
/build/
/target/

# WAR files
*.war

# Logs
*.log

# OS files
.DS_Store
Thumbs.db

# Temporary files
*.tmp
*.bak
```

> 本專案保留 `.project`、`.classpath`、`.settings`，方便學生直接用 Eclipse 匯入。如果你要做更乾淨的 GitHub 專案，也可以改成 Maven Web Project。

---

## 常見問題排除

### 1. HTTP 404 Not Found

請確認網址是否包含正確 Context Path：

```text
http://localhost:8080/SimpleShop_DynamicWebProject/product/list
```

也請確認 Tomcat Server 中已經加入此專案：

```text
Servers 視窗 > Tomcat 10 > Add and Remove > 加入 SimpleShop_DynamicWebProject
```

### 2. ClassNotFoundException: com.mysql.cj.jdbc.Driver

代表沒有加入 MySQL JDBC Driver。請將：

```text
mysql-connector-j-8.x.x.jar
```

放到：

```text
src/main/webapp/WEB-INF/lib/
```

### 3. Access denied for user 'root'

代表 MySQL 帳號或密碼錯誤。請修改：

```text
DBUtil.java
```

將 `JDBC_USER` 與 `JDBC_PASSWORD` 改成你的 MySQL 帳號密碼。

### 4. Table doesn't exist

代表尚未執行資料庫 SQL。請執行：

```text
sql/simple_shop.sql
```

### 5. JSP 無法直接開啟

本專案 JSP 放在：

```text
WEB-INF/views/
```

這是正確做法。`WEB-INF` 內的 JSP 不能直接用瀏覽器開啟，必須透過 Controller 轉發，例如：

```text
/product/list
/login
/member/list
/cart/view
```

---

## 教學重點整理

本專案可以用來教以下 Java Web 核心觀念：

1. Dynamic Web Project 建立與部署
2. Tomcat 10 與 Jakarta Servlet
3. Servlet Controller 寫法
4. JSP View 頁面設計
5. JDBC 連線 MySQL
6. DAO Pattern 分層
7. MVC 架構
8. Session 登入狀態
9. Session 購物車設計
10. 基礎 CRUD 實作

---

## 後續可擴充方向

下一版可以加入：

- 會員註冊頁
- 商品後台 CRUD
- 訂單資料表 `orders`
- 訂單明細資料表 `order_items`
- Filter 登入權限控管
- 角色權限：ADMIN / USER
- Bootstrap RWD 版面
- 商品圖片上傳
- 分頁查詢
- 搜尋功能
- BCrypt 密碼加密
- REST API 版本
- jQuery AJAX 串接版
- Spring Boot + JPA 升級版

---

## 安全性提醒

本專案是教學版範例，為了讓初學者容易理解，安全性設計保持簡化。

正式系統建議加入：

- BCrypt 或 Argon2 密碼雜湊
- PreparedStatement 輸入參數驗證
- CSRF 防護
- XSS 防護
- 登入失敗次數限制
- 權限 Filter
- HTTPS
- 資料庫帳號最小權限原則

---

## License

This project is for educational use.

你可以自由修改、教學展示與延伸成更完整的 Java Web 專案。
