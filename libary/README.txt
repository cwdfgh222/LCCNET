# 書目查詢系統 (Project)

這是一個包含 Java 後端、Python 爬蟲和 MySQL 資料庫的全端應用專案骨架。

## 專案結構

- `backend/`: Spring Boot 後端應用，提供 RESTful API。
- `crawler/`: Python 爬蟲，用於抓取書目資料。
- `docker-compose.yml`: Docker 編排檔，用於一鍵啟動所有服務。
- `Dockerfile.backend`: 後端服務的 Docker 映像設定。
- `Dockerfile.crawler`: 爬蟲服務的 Docker 映像設定。

## 如何啟動

請確保您已安裝 Docker 和 Docker Compose。

1.  在專案根目錄下，執行以下指令來建置並啟動所有容器：

    ```bash
    docker-compose up --build
    ```

2.  服務啟動後：
    -   **後端 API** 將在 `http://localhost:8080` 上提供服務。
    -   **MySQL 資料庫** 將在 `localhost:3306` 上開放連線。
    -   **Python 爬蟲** 會自動執行一次，然後停止。

## API 端點範例

- `GET /api/books`: 查詢所有書本。
- `GET /api/books?keyword=Java`: 查詢書名包含 "Java" 的書本。
- `POST /api/books/update`: 觸發後端模擬呼叫爬蟲進行更新。
- `POST /api/auth/login`: 模擬使用者登入。
- `POST /api/auth/register`: 模擬使用者註冊。