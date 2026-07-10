# Study.com 課程作業 / Coursework

**繁體中文：** 本資料夾收錄 Study.com 相關課程作業，涵蓋資料庫、資料結構、字串搜尋、機器學習與數位邏輯等主題。

**English:** This folder contains coursework from Study.com, covering databases, data structures, string search, machine learning, and digital logic.

---

## 目錄結構 / Directory Structure

```
Study.com/
├── Public_Library.sql              # MySQL 公共圖書館資料庫 / Public library database
├── 8_bit_ALU/                      # 8 位元 ALU 電路 / 8-bit ALU circuit (Logisim)
├── AI Survey Program/              # 政治傾向問卷 / Political survey classifier
├── Searching Text & String Data/   # Boyer-Moore 字串搜尋 / Boyer-Moore string search
└── Creating a Binary Search Tree/  # 二元搜尋樹 / Binary search tree
```

---

## Public_Library.sql

### 繁體中文

MySQL 公共圖書館資料庫專案，包含完整的 schema 設計、種子資料與分析查詢。

**資料表**
- `Author` — 作者（姓名、國籍）
- `Book` — 書籍（書名、作者、類型）
- `Client` — 讀者（姓名、出生年、職業）
- `Borrower` — 借閱紀錄（讀者、書籍、借閱日期）

**主要內容**
- 建立資料庫與四張資料表
- 插入作者、書籍、讀者與借閱紀錄
- 建立索引以提升查詢效能
- 14 道分析查詢（熱門作者、借閱趨勢、年齡分布等）
- 建立 `PopularBooks` VIEW（被至少 20% 讀者借閱過的書籍）

**執行方式**

```bash
mysql -u <username> -p < Public_Library.sql
```

### English

A MySQL public library database project with full schema design, seed data, and analytical queries.

**Tables**
- `Author` — Authors (name, nationality)
- `Book` — Books (title, author, genre)
- `Client` — Library patrons (name, birth year, occupation)
- `Borrower` — Borrowing records (patron, book, borrow date)

**Highlights**
- Creates the database and four related tables
- Inserts sample data for authors, books, clients, and borrow records
- Adds indexes to improve query performance
- Includes 14 analytical queries (popular authors, borrowing trends, age distribution, etc.)
- Defines a `PopularBooks` VIEW for books borrowed by at least 20% of clients

**How to Run**

```bash
mysql -u <username> -p < Public_Library.sql
```

---

## 8_bit_ALU/

### 繁體中文

使用 [Logisim](http://www.cburch.com/logisim/) 設計的 8 位元算術邏輯單元（ALU）電路。

| 檔案 | 說明 |
|------|------|
| `8_bit_ALU.circ` | 8 位元 ALU 主電路 |
| `Assignment_1.circ` | 作業 1 電路檔 |

**使用方式**
1. 安裝 Logisim
2. 開啟 `.circ` 檔案
3. 使用模擬功能測試 ALU 運算

### English

An 8-bit Arithmetic Logic Unit (ALU) circuit designed in [Logisim](http://www.cburch.com/logisim/).

| File | Description |
|------|-------------|
| `8_bit_ALU.circ` | Main 8-bit ALU circuit |
| `Assignment_1.circ` | Assignment 1 circuit file |

**How to Use**
1. Install Logisim
2. Open the `.circ` file
3. Use the simulation feature to test ALU operations

---

## AI Survey Program/

### 繁體中文

以 Java 與 [Weka](https://www.cs.waikato.ac.nz/ml/weka/) 機器學習函式庫實作的互動式政治傾向問卷。程式以 Random Forest 分類器，根據 16 道政策問題預測使用者的政黨傾向。

**支援政黨**
- Democrat（民主黨）
- Republican（共和黨）
- Green（綠黨）
- Libertarian（自由意志黨）

| 檔案 | 說明 |
|------|------|
| `Version 2_Final_AISurvey.java` | 主程式 |
| `survey.arff` | 訓練資料（Weka ARFF 格式） |

**執行方式**

```bash
cd "AI Survey Program"
javac -cp weka.jar Version\ 2_Final_AISurvey.java
java -cp .:weka.jar Main
```

> 需先下載 Weka 並將 `weka.jar` 加入 classpath。執行時請確保 `survey.arff` 在同一目錄。

### English

An interactive political survey built with Java and the [Weka](https://www.cs.waikato.ac.nz/ml/weka/) machine learning library. A Random Forest classifier predicts the user's likely political party based on 16 policy questions.

**Supported Parties**
- Democrat
- Republican
- Green
- Libertarian

| File | Description |
|------|-------------|
| `Version 2_Final_AISurvey.java` | Main program |
| `survey.arff` | Training data (Weka ARFF format) |

**How to Run**

```bash
cd "AI Survey Program"
javac -cp weka.jar Version\ 2_Final_AISurvey.java
java -cp .:weka.jar Main
```

> Download Weka first and add `weka.jar` to the classpath. Make sure `survey.arff` is in the same directory when running.

---

## Searching Text & String Data/

### 繁體中文

以 **Boyer-Moore 字串搜尋演算法**（Bad Character Heuristic）在美國 50 州名稱中進行關鍵字搜尋的 Java 程式。

**功能**
1. 顯示所有州名
2. 輸入關鍵字搜尋（支援部分比對）
3. 離開程式

**執行方式**

```bash
cd "Searching Text & String Data"
javac DS.java
java DS
```

### English

A Java program that searches U.S. state names using the **Boyer-Moore string search algorithm** (Bad Character Heuristic).

**Features**
1. Display all state names
2. Search by keyword (supports partial matching)
3. Exit the program

**How to Run**

```bash
cd "Searching Text & String Data"
javac DS.java
java DS
```

---

## Creating a Binary Search Tree/

### 繁體中文

完整的**二元搜尋樹（BST）** Java 實作，支援建立、新增、刪除與三種走訪方式。

**功能**
1. 由排序陣列建立平衡 BST
2. 新增節點
3. 刪除節點
4. 中序（In-Order）走訪
5. 前序（Pre-Order）走訪
6. 後序（Post-Order）走訪

**執行方式**

```bash
cd "Creating a Binary Search Tree"
javac BST.java
java BST
```

### English

A full **Binary Search Tree (BST)** implementation in Java, supporting creation, insertion, deletion, and three traversal methods.

**Features**
1. Build a balanced BST from a sorted array
2. Insert a node
3. Delete a node
4. In-Order traversal
5. Pre-Order traversal
6. Post-Order traversal

**How to Run**

```bash
cd "Creating a Binary Search Tree"
javac BST.java
java BST
```

---

## 環境需求 / Requirements

| 專案 / Project | 所需工具 / Tools Required |
|----------------|---------------------------|
| Public_Library.sql | MySQL |
| 8_bit_ALU | Logisim |
| AI Survey Program | Java JDK、Weka / Java JDK, Weka |
| Searching Text & String Data | Java JDK |
| Creating a Binary Search Tree | Java JDK |
