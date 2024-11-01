Welcome to "Leave Management System" project

1. Hãy đảm bảo trong máy tính đã cài đặt docker
2. Chạy lệnh `docker compose up -d db` để tạo container cho db, quá trình này mất vài phút
3. Sau khi hoàn tất, chạy lệnh `mvn clean package` để tạo file jar. Cần cài đặt maven trước khi chạy lệnh này
4. Chạy lệnh `docker compose up -d backend` để tạo container và khởi chạy server
3. Sau khi hoàn tất, có thể vào link `http://localhost:8080/swagger-ui/index.html#/` để xem hệ thống đã hoạt động chưa