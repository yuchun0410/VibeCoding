# SimpleShop_DynamicWebProject

簡易購物網站範例，符合以下條件：

- JDK 17
- MySQL 8.0
- Eclipse IDE for Enterprise Java and Web Developers
- Dynamic Web Project
- Tomcat 10.0
- JSP + Servlet
- MVC + DAO Pattern
- 會員管理：CRUD + 登入
- 購物車：加入、查看、修改數量、移除、結帳

---

## 1. 專案結構

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
   ├─ assets/css/style.css
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

## 2. 匯入 Eclipse

1. 解壓縮 ZIP。
2. Eclipse 選擇：`File > Import > Existing Projects into Workspace`。
3. 選擇 `SimpleShop_DynamicWebProject` 資料夾。
4. 若 Tomcat Runtime 名稱不同，請到：
   - 專案右鍵 `Properties`
   - `Targeted Runtimes`
   - 勾選你的 `Apache Tomcat v10.0`
5. 若 Java Compiler 不是 17，請調整為 Java 17。

---

## 3. 建立 MySQL 資料庫

使用 MySQL Workbench 或命令列執行：

```sql
source sql/simple_shop.sql;
```

或直接打開 `sql/simple_shop.sql`，整份執行。

預設資料庫：

```text
Database: simple_shop
User: root
Password: root
```

若你的 MySQL 密碼不是 `root`，請修改：

```java
src/main/java/com/codebyx/shop/util/DBUtil.java
```

```java
private static final String JDBC_USER = "root";
private static final String JDBC_PASSWORD = "root";
```

---

## 4. 加入 MySQL JDBC Driver

請將 `mysql-connector-j-8.x.x.jar` 放到：

```text
src/main/webapp/WEB-INF/lib/
```

之後在 Eclipse 對專案按右鍵：

```text
Refresh
```

再重新部署到 Tomcat。

---

## 5. 執行網址

部署後開啟：

```text
http://localhost:8080/SimpleShop_DynamicWebProject/
```

主要網址：

```text
商品列表：/product/list
購物車：/cart/view
會員登入：/login
會員管理：/member/list
```

完整範例：

```text
http://localhost:8080/SimpleShop_DynamicWebProject/product/list
http://localhost:8080/SimpleShop_DynamicWebProject/login
http://localhost:8080/SimpleShop_DynamicWebProject/member/list
http://localhost:8080/SimpleShop_DynamicWebProject/cart/view
```

---

## 6. 測試帳號

```text
admin / admin123
```

```text
test / test123
```

本範例使用 Java 內建 SHA-256 雜湊密碼，方便教學。正式系統建議改用 BCrypt、Argon2，並加入角色權限控管、CSRF 防護、輸入驗證與訂單資料表。

---

## 7. 教學重點

### MVC 分層

- Controller：接收 HTTP request，呼叫 DAO，決定轉發 JSP 或重新導向。
- DAO：封裝 JDBC 與 SQL。
- Entity：對應資料表或購物車物件。
- JSP：只負責畫面顯示與表單提交。

### DAO Pattern

`MemberDAO`、`ProductDAO` 是介面，`MemberDAOImpl`、`ProductDAOImpl` 是實作類別。這樣未來可以把 JDBC 改成 JPA、MyBatis 或 Spring Data，而不需要大幅修改 Controller。

---

## 8. 可延伸功能

下一版可加入：

1. 訂單資料表 `orders`、`order_items`
2. 商品後台 CRUD
3. 會員註冊頁
4. Filter 權限控管
5. Bootstrap RWD 版面
6. 分頁查詢
7. 圖片上傳
8. 金流串接模擬
9. BCrypt 密碼加密
10. REST API + jQuery AJAX 前端版
