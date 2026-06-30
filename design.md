# Thiết kế kiến trúc RESTful API - Quản lý Task và User

**Quy tắc chung:**
- Sử dụng danh từ số nhiều cho các resource (`/users`, `/tasks`).
- Sử dụng đúng các HTTP Methods (`GET`, `POST`, `PATCH`, `DELETE`).
- Dữ liệu truyền lên qua Body sẽ ở định dạng JSON (xử lý bằng `@RequestBody` trong Spring Boot).

---

## 1. Lấy toàn bộ danh sách công việc, người dùng

* **Lấy danh sách người dùng:**
    * **Method:** `GET`
    * **Endpoint:** `/users`
    * **Mô tả:** Trả về mảng chứa toàn bộ thông tin người dùng.

* **Lấy danh sách công việc:**
    * **Method:** `GET`
    * **Endpoint:** `/tasks`
    * **Mô tả:** Trả về mảng chứa toàn bộ thông tin công việc.

## 2. Tạo mới công việc, tạo mới người dùng

* **Tạo người dùng mới:**
    * **Method:** `POST`
    * **Endpoint:** `/users`
    * **Body (JSON):** Chứa thông tin của User mới.

* **Tạo công việc mới:**
    * **Method:** `POST`
    * **Endpoint:** `/tasks`
    * **Body (JSON):** Chứa thông tin của Task mới.

## 3. Cập nhật trạng thái một công việc, cập nhật vai trò của người dùng
*(Lưu ý: Sử dụng `PATCH` vì chúng ta chỉ muốn cập nhật một phần dữ liệu (trạng thái/vai trò) thay vì toàn bộ Object).*

* **Cập nhật trạng thái công việc:**
    * **Method:** `PATCH`
    * **Endpoint:** `/tasks/{taskId}/status`
    * **Body (JSON):** `{ "status": "DONE" }`

* **Cập nhật vai trò người dùng:**
    * **Method:** `PATCH`
    * **Endpoint:** `/users/{userId}/role`
    * **Body (JSON):** `{ "role": "ADMIN" }`

## 4. Xóa một công việc, xóa một người dùng khỏi hệ thống

* **Xóa người dùng:**
    * **Method:** `DELETE`
    * **Endpoint:** `/users/{userId}`

* **Xóa công việc:**
    * **Method:** `DELETE`
    * **Endpoint:** `/tasks/{taskId}`

## 5. Tìm các công việc có mức độ ưu tiên là "high"

* **Method:** `GET`
* **Endpoint:** `/tasks?priority=high`
* **Mô tả:** Sử dụng Query Parameter `priority` để lọc danh sách `/tasks`.

## 6. Tìm các công việc có độ ưu tiên là "high" và được giao cho người dùng với id là 1

* **Cách 1 (Tiếp cận từ User - Resource lồng nhau):**
    * **Method:** `GET`
    * **Endpoint:** `/users/1/tasks?priority=high`
* **Cách 2 (Tiếp cận từ Task với đa bộ lọc):**
    * **Method:** `GET`
    * **Endpoint:** `/tasks?userId=1&priority=high`
      *(Có thể chọn 1 trong 2 cách, cách 1 thường thể hiện rõ mối quan hệ sở hữu hơn trong RESTful).*

## 7. Liệt kê toàn bộ công việc của 1 người dùng

* **Method:** `GET`
* **Endpoint:** `/users/{userId}/tasks`
* **Mô tả:** Trả về danh sách tất cả các task thuộc về `userId` cụ thể (Sub-resource).

## 8. Gắn công việc cho người dùng

* **Method:** `PATCH`
* **Endpoint:** `/tasks/{taskId}/assign`
* **Body (JSON):** `{ "userId": 1 }`
* **Mô tả:** Cập nhật công việc cụ thể, gán `userId` (Foreign Key) vào công việc đó để xác định người thực hiện.